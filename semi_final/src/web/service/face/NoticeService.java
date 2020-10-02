package web.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dto.Notice;
import web.util.Paging;

public interface NoticeService {
	
	// 페이지 객체 생성
	public Paging getPaging(HttpServletRequest req); //ok
	
	// 공지사항 목록
	public List<Map<String, Object>> getList(Paging paging); 
	
	
	// 공지사항 상세보기
	public Notice getNoticeNo(HttpServletRequest req); //ok

	/**
	 * 상세보기 결과 조회
	 * @param Noticeno
	 * @return
	 */
	public Notice show(Notice Noticeno); //ok
	
	//*****혜연**************************************************************//
	// 공지사항 목록
	public List<Notice> getNoticeList(Paging paging);

	// 공지사항 수정
	public void  UpdateNoticeNo(HttpServletRequest req);

	//공지사항 삭제
	public void DeleteNotice(Notice notice);


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
	
	/***
	 * 댓글의 갯수 세기
	 * 
	 * @param showNotice
	 * @return
	 */
	public int replyCountComment(Notice showNotice);

}
