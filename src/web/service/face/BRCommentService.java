package web.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import web.dto.BRComment;
import web.dto.Board;

public interface BRCommentService {
	
	/**
	 * 댓글 리스트
	 * 
	 * @param showBoard - 댓글이 조회될 게시글
	 * @return List - 댓글리스트
	 */
	public List<Map<String, Object>> getCommentList(Board showBoard);
	
	/**
	 * 댓글 전달파라미터 꺼내기 
	 * @param req 
	 * @return
	 */
	public BRComment getComment(HttpServletRequest req);
	
	/**
	 * 댓글 입력
	 * @param comment - 삽입될 댓글
	 */
	public void insertComment(BRComment comment);
	
	/**
	 * 댓글 삭제
	 * 
	 * @param comment - 삭제할 댓글
	 * @return boolean - 삭제 성공 여부
	 */
	public boolean deleteComment(BRComment comment);
	
 	/**
	 * 댓글 갯수 세기
	 * @param showBoard
	 * @return
	 */
	public int replyCountComment(Board showBoard);

}
