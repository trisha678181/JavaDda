package web.dao.face;

import web.dto.RVComment;

public interface RVCommentDao {
	
	// 리뷰 댓글 상세보기
	public RVComment SetRVCommentNo(int reviewNo);
	
	// 리뷰 댓글 삭제
	public void DeleteRVCommentNo(int rvCommentNo);

}
