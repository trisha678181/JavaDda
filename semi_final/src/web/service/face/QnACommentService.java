package web.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dto.BRComment;
import web.dto.NTComment;
import web.dto.Notice;
import web.dto.QnA;
import web.dto.QnAComment;

public interface QnACommentService {
	
	/**
	 * 댓글 리스트
	 * 
	 * @param showQnA - 댓글이 조회될 QnA
	 * @return List - 댓글리스트
	 */
	public List<Map<String, Object>> getCommentList(QnA showQnA);

	/**
	 * 댓글 전달파라미터 꺼내기 
	 * @param req 
	 * @return
	 */
	public QnAComment getComment(HttpServletRequest req);
	
	/**
	 * 댓글 입력
	 * @param comment - 삽입될 댓글
	 */
	public void insertComment(QnAComment comment);
	
	/**
	 * 댓글 삭제
	 * 
	 * @param comment - 삭제할 댓글
	 * @return boolean - 삭제 성공 여부
	 */
	public boolean deleteComment(QnAComment comment);

}
