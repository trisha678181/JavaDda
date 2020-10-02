package web.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dto.Notice;
import web.dto.RVComment;
import web.dto.Review;
import web.util.Paging;
import web.util.ReviewPaging;

public interface ReviewService {
   
	// 페이지 객체 생성
	public Paging getPaging(HttpServletRequest req);
		
	// 리뷰 리스트
//	public List<Map<String, Object>> getList(Paging paging, Review pno);
	List<Map<String, Object>> getList(Paging paging);
	
   // 리뷰 상세보기
   public Review getReviewno(HttpServletRequest req);
   
   //--------------새로 추가한 목록---------------------------------------------------------
  
   // 리뷰 삭제
   public void DeleteReview(Review reviewNo);
   
   //리뷰 상세보기
   Map<String, Object> show(Review review);
   
   
   //----------------------문정추가--------------------------
   
	// 페이지 객체 생성
	public ReviewPaging getrvPaging(HttpServletRequest req);
	
	// 리뷰 리스트
	public List<Map<String, Object>> getrvList(ReviewPaging paging, Review pno);
	
	/**
	 * placeno 갖고오기
	 * @param req
	 * @return
	 */
	public Review getPlacepno(HttpServletRequest req);

	/**
	 * 문서전달 파라미터 꺼내기
	 * @param req
	 * @return
	 */
	public Review getReviewWrite(HttpServletRequest req);

	/**
	 * 리뷰 입력
	 */
	public void insertReview(Review review);

	/**
	 * 파일 받기
	 * @param req
	 * @return
	 */
	public int getReviewFile(HttpServletRequest req);

	/**
	 * 리뷰답글 파라미터값
	 * @param 
	 * @return req
	 */
	public RVComment getRVComment(HttpServletRequest req);

	/**
	 * 리뷰 답글 추가하기
	 * @param rvcomment
	 */
	public void insertRVComment(RVComment rvcomment);

	/**
	 * 리뷰답글 리스트
	 * @param reviewlist
	 * @return
	 */
	public List<Map<String,Object>> getCommentList(Review pno);

	/**
	 * 리뷰갯수 
	 * @param pno
	 * @return 정수형으로
	 */
	public int replyCountComment(int pno);

	/**
	 * 리뷰갯수
	 * @param pno
	 * @return 리뷰 객체로
	 */
	public int replyCountComment(Review pno);

	/**
	 * 리뷰장소이름 갖고오기
	 * @param pno
	 * @return
	 */
	public List<Map<String, Object>> getPlaceNo(Review pno);



	

}