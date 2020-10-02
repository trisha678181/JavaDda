package web.dao.face;

import java.util.List;
import java.util.Map;

import web.dto.RVComment;
import web.dto.RVImg;
import web.dto.Review;
import web.util.Paging;
import web.util.ReviewPaging;

public interface ReviewDao {

	// 리뷰 리스트 출력
	public List<Map<String, Object>> SelectReviewAll(Paging paging);
	
	public int selectCntAll();	
	
	// 리뷰 삭제
	public void delete(Review review);

	//리뷰상세보기
	public Map<String, Object> selectReviewByReviewno(Review review);

	
	//----------------문정추가
	
	public List<Map<String, Object>> SelectReviewAll(ReviewPaging paging, Review pno);

	public void insertReviewAll(Review review);
	
	public int selectCntAll(int pno);

	public int selectReviewrno();

	public void insertFile(RVImg rvi);

	public void insertComment(RVComment rvcomment);

	public List<Map<String,Object>> selectComment(Review pno);

	public int reviewTotalCount(int pno);
	
	public int reviewTotalCount(Review pno);

	public List<Map<String, Object>> selectPlaceName(Review pno);
	
	
}
