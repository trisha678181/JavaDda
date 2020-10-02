package web.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dto.BRImg;
import web.dto.Board;
import web.util.Paging;

public interface BoardService {
	/**
	 * 페이징 객체 생성
	 * 
	 * 요청정보를 활용하여 curPage를 구하고
	 * Board 테이블과 curPage 값을 이용한 Paging 객체를 생성하여 반환한다
	 * 
	 * @param req - curPage정보를 담고 있는 요청정보 객체
	 * @return Paging - 페이징 계산이 완료된 결과 객체
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 * 페이징 처리하여 보여질 게시글 목록만 조회하기
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Board> - 조회된 게시글 목록
	 */
	public List<Map<String, Object>> getList(Paging paging);
	
	/**
	 * 게시글 작성
	 * 	입력한 게시글 내용을 DB에 저장
	 * 
	 *  첨부파일을 함께 업로드 할 수 있도록 처리
	 * 
	 * @param req - 요청정보 객체(게시글내용 + 첨부파일)
	 * 
	 */
	public void write(HttpServletRequest req);

	
	/**
	 * 게시판 번호 요청파라미터 얻기
	 * 
	 * @param req - 요청정보객체
	 * @return Board - 전달파라미터 brno를 포함한 객체
	 */
	public Board getBoardbrno(HttpServletRequest req);
	
	/**
	 * 주어진 brn를 이용하여 게시글을 조회한다
	 * 조회된 게시글의 조회수를 1 증가시킨다
	 * 
	 * @param brn - brn를 가지고 있는 객체
	 * @return Board - 조회된 게시글
	 */
	public Board show(Board brno);
	
	/**
	 * Board 객체의 id를 이용한 닉네임 조회
	 * 
	 * @param showBoard - 조회할 게시글 정보 
	 * @return String - 게시글 작성장의 닉네임
	 */
	public String getNick(Board showBoard);
	/**
	 * 게시글 삭제
	 * 
	 * @param brno - 요청 정보 객체
	 */
	public void deleteBoard(Board board);
	
	/**
	 * 게시글 좋아요
	 * @param showBoard
	 */
	public void updateLike(int brno);
	
	/**
	 * 게시글 수정
	 *  
	 * @param req - 요청 정보 객체
	 */
	public void updateBoard(HttpServletRequest req);
	
	
	/**
	 * 첨부파일의  정보 얻기
	 * 
	 * @param showBoard - 첨부파일 포함된 게시글 번호
	 * @return BRImg - 첨부파일 정보 객체
	 */
	public List<BRImg> showFile(Board showBoard);
	
	/**
	 * 글 작성자인지 판단하기
	 * 
	 * @param req - 요청 정보 객체
	 * @return boolean - true : 로그인한 사람이 글 작성자
	 */
	public boolean checkId(HttpServletRequest req);

	
	/**
	 * 디비에서 좋아요 가져오는 함수
	 * @param brno
	 * @return
	 */
	public int selectLike(int brno);
	
	/**
	 * Board 객체의 id를 이용한 닉네임 조회
	 * 
	 * @param showBoard - 조회할 게시글 정보 
	 * @return String - 게시글 작성장의 닉네임
	 */
	public String getName(Board board);
	
	
	
}