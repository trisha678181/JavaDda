package web.dao.face;

import java.util.List;
import java.util.Map;

import web.dto.Notice;
import web.util.Paging;

public interface NoticeDao {
	
	// 공지사항 리스트 출력 - 혜연
	public List<Notice> SelectNoticeAll(Paging paging); 

	// 공지사항 리스트 출력 - 예지
	public List<Map<String, Object>> SelectAll(Paging paging); 

	
	/**
	 * 총 게시글 수 조회
	 * @return
	 */
	public int selectCntAll(); //ok
	
	
	
	/**
	 * 
	 * 
	 * @param noticeno
	 * @return
	 */
	public Notice selectNoticeByNoticeno(Notice notice); //ok
	
	/**
	 * 글 번호로 게시글 조회
	 * @param noticeno
	 */
	public void updateHit(Notice noticeno); //ok
	
	//=================== 혜연 ======================================

	// 공지사항 수정
	public void UpdateNotice(Notice notice);
	
	// 공지사항 삭제
	public void delete(Notice notice);
	

	// 공지사항 입력
	public void insertBoard(Notice notice);
	
	/**
	 * 댓글 개수 세기
	 * 
	 * @param showNotice
	 * @return
	 */
	public int replyCount(Notice showNotice);
	
}
