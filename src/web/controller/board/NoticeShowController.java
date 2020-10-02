package web.controller.board;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.NTComment;
import web.dto.Notice;
import web.service.face.NTCommentService;
import web.service.face.NoticeService;
import web.service.impl.NTCommentServiceImpl;
import web.service.impl.NoticeServiceImpl;

/**
 * Servlet implementation class NoticeShowController
 */
@WebServlet("/notice/show")
public class NoticeShowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NoticeService noticeService = new NoticeServiceImpl();
	private NTCommentService ntcommentService = new NTCommentServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/notice/show [get]");
		
		Notice noticeno = noticeService.getNoticeNo(req);


//		System.out.println("noticenno : " + noticeno);

		//상세보기 결과 조회 
		Notice showNotice = noticeService.show(noticeno);

		req.setAttribute("showNotice", showNotice);
		System.out.println("shownotcie : " + showNotice);
		
		//총 댓글의 수
		int replyCount = noticeService.replyCountComment(showNotice);
		System.out.println("replycount : " + replyCount);
			
		req.setAttribute("replyCount", replyCount);
		
		//댓글 리스트 전달
		NTComment ntcomment = new NTComment();
		List<Map<String, Object>> commentList = ntcommentService.getCommentList(showNotice);
		req.setAttribute("commentList", commentList);

		
		
		req.getRequestDispatcher("/WEB-INF/views/board/noticeShow.jsp").forward(req, resp);

	}

}
