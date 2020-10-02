package web.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import web.dao.face.ReviewDao;
import web.dao.impl.ReviewDaoImpl;
import web.dto.Notice;
import web.dto.QnA;
import web.dto.RVComment;
import web.dto.RVImg;
import web.dto.Review;
import web.service.face.ReviewService;
import web.util.Paging;
import web.util.ReviewPaging;

public class ReviewServiceImpl implements ReviewService {

   private ReviewDao reviewDao = new ReviewDaoImpl();

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
      int totalCount = reviewDao.selectCntAll();

      //Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수(totalCount) 활용
      Paging paging = new Paging(totalCount, curPage);

      //검색어
      paging.setSearch(search);

      //Paging 객체 반환
      return paging;
   }

   @Override
   public List<Map<String, Object>> getList(Paging paging) {
      return reviewDao.SelectReviewAll(paging);
   }

   @Override
   public Review getReviewno(HttpServletRequest req) {
      //rno를 저장할 객체 생성
      Review rno = new Review();

      //rno 전달파라미터 검증 - null, ""
      String param = req.getParameter("rno");

      if(param != null && !"".equals(param)) {

         //rno 전달파라미터 추출
         rno.setRno(Integer.parseInt(param));

      }

      //결과 객체 반환
      return rno;
   }

   @Override
   public void DeleteReview(Review review) {
      reviewDao.delete(review);

   }
   @Override
   public Map<String, Object> show(Review review) {
      //게시글 조회
      return reviewDao.selectReviewByReviewno(review);
   
   }
   
   //=======================문정추가=========================
   
	@Override
	public ReviewPaging getrvPaging(HttpServletRequest req) {
		
		//전달파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}

		//검색어 
		String search = (String)req.getParameter("search");

		param = req.getParameter("pno");
		int pno = 0;
		if( param != null && !"".equals(param) ) {
	         pno = Integer.parseInt(param);
	      }

		
		//Board 테이블의 총 게시글 수를 조회한다
		int totalCount = reviewDao.selectCntAll(pno);

		//Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수(totalCount) 활용
		ReviewPaging paging = new ReviewPaging(totalCount, curPage);

		//검색어
		paging.setSearch(search);

		//Paging 객체 반환
		return paging;
		
	}
	
	//리뷰 페이지 조회
	@Override
	public List<Map<String, Object>> getrvList(ReviewPaging paging, Review pno) {
		
		return reviewDao.SelectReviewAll(paging, pno);
		
	}
	
	
	@Override
	public Review getPlacepno(HttpServletRequest req) {
		
		Review pno = new Review();
		String param = req.getParameter("pno");
		
		if( param != null && !"".equals(param) ) {
	         pno.setPno(Integer.parseInt(param));
	      }
		
		return pno;
	}
	
	
	
	
	//--------------------------------------------------리뷰등록
	
	@Override
	public void insertReview(Review review) {
		reviewDao.insertReviewAll(review);
	}
	
	
	@Override
	public Review getReviewWrite(HttpServletRequest req) {
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		//세션 데이터타입 유지, 파라미터는 string
		int uno = (int) req.getSession().getAttribute("uno");
		String pno = req.getParameter("pno");
		String rvcontent = req.getParameter("rvcontent");
		String rvstar = req.getParameter("rvstar");
		
		//테스트
		System.out.println("uno : "+uno);
		System.out.println("pno : "+pno);
		System.out.println("rvcontent : "+rvcontent);
		System.out.println("rvstar : "+rvstar);
		
		Review review = new Review();
		review.setUno(uno);
		review.setPno(Integer.parseInt(pno));
		review.setRvstar(Integer.parseInt(rvstar));
		review.setRvcontent(rvcontent);
		
		return review;
		
	}
	
	@Override
	public int getReviewFile(HttpServletRequest req) {
	      
		Review review = null;
		RVImg rvImg = null;
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if(!isMultipart) { //multipart/form-data 형식인지 검사
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			return 0;
		}
		
		//게시글 정보 저장할 객체 생성
		review = new Review();

		//디스트기반 아이템 팩토리
		DiskFileItemFactory factory = new DiskFileItemFactory();

		//메모리 처리 사이즈 지정
		factory.setSizeThreshold(1 * 1024 *1024);		
		
		//임시저장소 설정
		File repository = new File(req.getServletContext().getRealPath("tmp")); // 임시 저장 폴더	
		factory.setRepository(repository);
		
		//파일업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		upload.setFileSizeMax(10 * 1024 * 1024);
		
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		//파싱된 데이터 처리 반복자
		Iterator<FileItem> iter = items.iterator();

		//모든 요청정보 처리하기
		while(iter.hasNext()) {
			FileItem item = iter.next();
			
			// 1) 빈 파일 처리
			if(item.getSize() <= 0) continue;

			// 2) 일반적인 요청 데이터 처리
			if(item.isFormField()) {
				try {
					
					if( "rno".equals( item.getFieldName() ) ) {
						review.setRno(Integer.parseInt(item.getString()));
					}
					
					if( "pno".equals( item.getFieldName() ) ) {
						review.setPno(Integer.parseInt(item.getString()));
					}
					
					if( "rvcontent".equals( item.getFieldName() ) ) {
						review.setRvcontent( item.getString("utf-8") );
					}
					
					if( "rvdate".equals( item.getFieldName() ) ) {
						review.setRvdate( item.getString("utf-8") );
					}
					
					if( "rvstar".equals( item.getFieldName() ) ) {
						review.setRvstar( Integer.parseInt(item.getString()));
					}
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

			} //if(item.isFormField()) end - 폼필드 확인(일반적인 요청 데이터 처리)
			
			// 3) 파일 처리
			if(!item.isFormField()) {

				//---- UUID 생성 ------
				UUID uuid = UUID.randomUUID(); //랜덤 UID 생성
				String u = uuid.toString().split("-")[4];

				//로컬저장소
				String stored = item.getName() + "_" + u;
				File up = new File(
						req.getServletContext().getRealPath("upload") //업로드될 폴더 경로
						, stored );//원본파일명_uid
						
				//-----------------------------------------

				//--- 첨부파일 정보 객체 ---
				rvImg = new RVImg();
				rvImg.setOriginname(item.getName()); //원본파일명
				rvImg.setStoredname(item.getName() + "_" + u); //저장파일명
				rvImg.setFilesize((int)item.getSize());
				

				try {
					item.write(up); //실제 업로드
					item.delete(); //임시 파일 삭제
				} catch (Exception e) {
					e.printStackTrace();
				} 

			}// 파일 처리 end

		} //while(iter.hasNext()) end - FileItem 반복 처리

		//DB데이터 입력
		//게시글 작성자 id 입력
		review.setUno((int)req.getSession().getAttribute("uno"));
		String pno = req.getParameter("pno");
		
		//게시글 번호 생성 - Dao 이용
		int rno = reviewDao.selectReviewrno();

		//게시글 정보가 없을 경우
		if(review != null) {
			review.setRno(rno);
			reviewDao.insertReviewAll(review);
		}

		//첨부파일 정보가 없을 경우 처리
		if(rvImg != null) {
			rvImg.setRno(rno); //첨부파일에 게시글 번호 입력
			reviewDao.insertFile(rvImg); //첨부파일 정보 삽입
		}

		return review.getPno();
	}
	
	@Override
	public RVComment getRVComment(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		int uno = (int) req.getSession().getAttribute("uno");
		String rno = (String)req.getParameter("rno");
		String comcontent = (String)req.getParameter("rv_com_content");
		String comdate = (String)req.getParameter("rv_con_date");
		
		RVComment rvcomment = new RVComment();
		
		rvcomment.setUno(uno);
		rvcomment.setRno(Integer.parseInt(rno));
		rvcomment.setRv_com_content(comcontent);
		rvcomment.setRv_com_date(comdate);
		
		//테스트
		System.out.println("uno :" +uno);
		System.out.println("rno :" +rno);
		System.out.println("comcontent : "+comcontent);
		System.out.println("comdate :"+comdate);
		
		return rvcomment;
	}
	
	@Override
	public void insertRVComment(RVComment rvcomment) {
		reviewDao.insertComment(rvcomment);
	}
	
	@Override
	public List<Map<String,Object>> getCommentList(Review pno) {
		System.out.println("ReivewSerview : "+pno);
		return reviewDao.selectComment(pno);
	}
	
	@Override
	public int replyCountComment(int pno) {
		return reviewDao.reviewTotalCount(pno);
	}
	
	@Override
	public int replyCountComment(Review pno) {
		return reviewDao.reviewTotalCount(pno);
	}
	
	@Override
	public List<Map<String, Object>> getPlaceNo(Review pno) {
		return reviewDao.selectPlaceName(pno);
	}


}