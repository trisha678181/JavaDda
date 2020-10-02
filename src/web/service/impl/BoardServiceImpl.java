package web.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import web.dao.face.BRCommentDao;
import web.dao.face.BRImgDao;
import web.dao.face.BoardDao;
import web.dao.impl.BRCommentDaoImpl;
import web.dao.impl.BRImgDaoImpl;
import web.dao.impl.BoardDaoImpl;
import web.dto.BRImg;
import web.dto.Board;
import web.service.face.BoardService;
import web.util.Paging;

public class BoardServiceImpl implements BoardService{
	
	private BoardDao boardDao = new BoardDaoImpl();
	private BRCommentDao brCommentDao = new BRCommentDaoImpl();
	private BRImgDao brImgDao = new BRImgDaoImpl();

	@Override
	public void write(HttpServletRequest req) {
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//게시글 정보 저장할 객체
		Board board = null;
		
		//첨부파일 정보 저장할 객체
		BRImg brImg = null;
		List<BRImg> brImgList = new ArrayList<>();
		
		//1. 파일 업로드 형태의 테이터가 맞는 지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		// 1-1. multipart/form-data 인코딩으로 전송되지 않았을 경우
		if (!isMultipart) {
			System.out.println("[error] enctype이 multipart/form-data가 아닙니다");

			return; // fileupload() 매소드 실행 중지
		}
		
		//게시글 정보 저장할 객체 생성
		board = new Board();

		//디스트기반 아이템 팩토리
		DiskFileItemFactory factory = new DiskFileItemFactory();

		//메모리 처리 사이즈 지정
		factory.setSizeThreshold(1 * 1024 *1024);


		//임시 저장소 설정		
		File repository = new File(req.getServletContext().getRealPath("tmp")); // 임시 저장 폴더	
		factory.setRepository(repository);

		//파일업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);

		//업로드 용량 제한
		upload.setFileSizeMax(10 * 1024 * 1024);

		//전달 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}


		//추출된 전달파라미터 처리 반복자
		Iterator<FileItem> iter = items.iterator();

		//모든 요청정보 처리하기
		while(iter.hasNext()) {
			FileItem item = iter.next();

			// 1) 빈 파일 처리
			if(item.getSize() <= 0) continue;

			// 2) 일반적인 요청 데이터 처리
			if(item.isFormField()) {

				String key = item.getFieldName(); //키 추출
				if("brtitle".equals(key)) { //전달파라미터 name이 "title"
					try {
						board.setBrtitle(item.getString("UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}

				} else if("brcontent".equals(key)) { //전달파라미터 name이 "data1"
					try {
						board.setBrcontent(item.getString("UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				} //key값 비교 if end

			} //if(item.isFormField()) end - 폼필드 확인(일반적인 요청 데이터 처리)

			// 3) 파일 처리
			if(!item.isFormField()) {

				//---- UUID 생성 ------
				UUID uuid = UUID.randomUUID(); //랜덤 UID 생성

				String u = uuid.toString().split("-")[0];//8자리 uid 
				//-------------------

				//------ 로컬 저장소의 파일 객체 생성 ----------------
				File up = new File(
						req.getServletContext().getRealPath("upload") //업로드될 폴더 경로
						, item.getName() + "_" + u //원본파일명_uid
						);
				//-----------------------------------------

				//--- 첨부파일 정보 객체 ---
				brImg = new BRImg();
				brImg.setOriginName(item.getName()); //원본파일명
				brImg.setStoreName( item.getName() + "_" + u); //저장파일명
				brImg.setFileSize((int)item.getSize());

				brImgList.add(brImg);
				//---------------------

				//--- 처리 ㅇ완료된 파일 업로드 하기 ------------
				try {
					item.write(up); //실제 업로드
					item.delete(); //임시 파일 삭제
				} catch (Exception e) {
					e.printStackTrace();
				} 
				//-----------------------------

			}// 파일 처리 end

		} //while(iter.hasNext()) end - FileItem 반복 처리


		//DB데이터 입력
		//게시글 작성자 id 입력
//		req.getSession().setAttribute("uno", 1);
		board.setUno((int)req.getSession().getAttribute("uno"));

		//게시글 번호 생성 - Dao 이용
		int brno = boardDao.selectBoardbrno();
				//게시글 정보가 있을 경우
		if(board != null) {

			//게시글 번호 입력
			board.setBrno(brno);

			boardDao.InsertBoard(board);
		}

		for(BRImg br : brImgList) {
			if(br != null) {
				
				//게시글 번호 입려
				br.setBrno(brno);
				
				//첨부파일 삽입
				boardDao.insertFile(br);
			}
		}
	
	}
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
		
		//검색어 
		String search = (String)req.getParameter("search");
		
		//Board 테이블의 총 게시글 수를 조회한다
		int totalCount = boardDao.selectCntAll();

		//Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수(totalCount) 활용
		Paging paging = new Paging(totalCount, curPage);
		
		//검색어
		paging.setSearch(search);
		
		//Paging 객체 반환
		return paging;
		
	}

	@Override
	public List<Map<String, Object>> getList(Paging paging) {
		return boardDao.selectAll(paging);
	}



	@Override
	public Board getBoardbrno(HttpServletRequest req) {
		
		//brno를 저장할 객체 생성
		Board brno = new Board();
		
		//brno 전달파라미터 검증 - null, ""
		String param = req.getParameter("brno");
				
		if(param != null && !"".equals(param)) {
			
			//brno 전달파라미터 추출
			brno.setBrno(Integer.parseInt(param));
			
		}
		
		//결과 객체 반환
		return brno;
		
	}

	@Override
	public Board show(Board brno) {
		
		if(brno != null) {
			
			//게시글 조회수 증가
			boardDao.updateBRviewcnt(brno);
			
		}
		
		//게시글 조회
		Board board = boardDao.selectBoardByBoardno(brno);
		
		return board;
	}
	
	@Override
	public List<BRImg> showFile(Board showBoard) {
		return boardDao.selectFile(showBoard);
	}
	
	@Override
	public boolean checkId(HttpServletRequest req) {
		
		//로그인한 세션 ID 얻기
//		req.getSession().setAttribute("uno", 1);
		int loginId= (int)req.getSession().getAttribute("uno");
		
		//작성한 게시글 번호 얻기
		Board board = getBoardbrno(req);
		
		//게시글 얻어오기
		board = boardDao.selectBoardByBoardno(board);
		
		//게시글의 작성자와 로그인 아이디 비교
		if( !(loginId == board.getUno())) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public void updateBoard(HttpServletRequest req) {
		
		List<String> delitems = new ArrayList<>();
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//게시글 정보 저장할 객체
		Board board = null;
		
		
		//첨부파일 정보 저장할 객체
		BRImg brImg = null;
		List<BRImg> brImgList = new ArrayList<>();
		
		//1. 파일 업로드 형태의 테이터가 맞는 지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);
		
		// 1-1. multipart/form-data 인코딩으로 전송되지 않았을 경우
		if (!isMultipart) {
			System.out.println("[error] enctype이 multipart/form-data가 아닙니다");

			return; // fileupload() 매소드 실행 중지
		}
		
		//게시글 정보 저장할 객체 생성
		board = new Board();
//		board.setBrno(Integer.parseInt(req.getParameter("brno")));

		//디스트기반 아이템 팩토리
		DiskFileItemFactory factory = new DiskFileItemFactory();

		//메모리 처리 사이즈 지정
		factory.setSizeThreshold(1 * 1024 *1024);


		//임시 저장소 설정		
		File repository = new File(req.getServletContext().getRealPath("tmp")); // 임시 저장 폴더	
		factory.setRepository(repository);

		//파일업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);

		//업로드 용량 제한
		upload.setFileSizeMax(10 * 1024 * 1024);

		//전달 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}


		//추출된 전달파라미터 처리 반복자
		Iterator<FileItem> iter = items.iterator();

		//요청정보 처리
		while(iter.hasNext()) {
			FileItem item = iter.next();

			// 빈 파일 처리
			if( item.getSize() <= 0 )	continue;

			// 빈 파일이 아닐 경우
			if( item.isFormField() ) {
				try {
					
					if( "delitem".equals(item.getFieldName()) ) {
						delitems.add(item.getString());
					}
					
					if( "brno".equals(item.getFieldName() )) {
						board.setBrno(Integer.parseInt(item.getString()));
					}
					if( "brtitle".equals(item.getFieldName())) {
						board.setBrtitle(item.getString("utf-8")); 
					}
					if( "brcontent".equals(item.getFieldName())) {
						board.setBrcontent(item.getString("utf-8"));
					}

//					req.getSession().setAttribute("uno", 1);
					board.setUno((int)req.getSession().getAttribute("uno"));

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

			} else {
				UUID uuid = UUID.randomUUID();
				String u = uuid.toString().split("-")[4];

				//로컬 저장소 파일
				String stored = item.getName() + "_" + u;
				File up = new File(
						req.getServletContext().getRealPath("upload")
						, stored);

				//--- 첨부파일 정보 객체 ---
				brImg = new BRImg();
				brImg.setOriginName(item.getName()); //원본파일명
				brImg.setStoreName( item.getName() + "_" + u); //저장파일명
				brImg.setFileSize((int)item.getSize());

				brImgList.add(brImg);
				//------------------

				try {
					// 실제 업로드
					item.write(up);

					// 임시 파일 삭제
					item.delete();

				} catch (Exception e) {
					e.printStackTrace();
				} // try end
			} //if end
		} //while end
		
		
		
		if(board != null) {
			boardDao.update(board);
		}


		for(BRImg br : brImgList) {
			if(br != null) {
				
				//게시글 번호 입려
				br.setBrno(board.getBrno());
				
				//첨부파일 삽입
				boardDao.insertFile(br);
			}
		}
		
		
		for (Iterator iterator = delitems.iterator(); iterator.hasNext();) {
			
//			System.out.println("삭제할 파일번호 : " + iterator.next());

			boardDao.deleteitemFile((String) iterator.next());
		}
		
		
	}

	@Override
	public void deleteBoard(Board board) {

		boardDao.deleteFile(board);

		boardDao.delete(board);
	}

	@Override
	public void updateLike(int brno) {
		 boardDao.upadateLikeCount(brno);
	}
	
	@Override
	public int selectLike(int brno) {
		return boardDao.selectLike(brno);
	}


	@Override
	public String getNick(Board showBoard) {
		return boardDao.selectNickByUserid(showBoard);
	}
	
	@Override
	public String getName(Board board) {
		return boardDao.selectNameByUserid(board);
	}

	
}
