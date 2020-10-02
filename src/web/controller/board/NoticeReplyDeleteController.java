package web.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.NTComment;
import web.service.face.NTCommentService;
import web.service.impl.NTCommentServiceImpl;

/**
 * Servlet implementation class NoticeReplyDeleteController
 */
@WebServlet("/notice/replyDelete")
public class NoticeReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NTCommentService ntcommentService = new NTCommentServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/notice/replyDelete [post]");
		
		NTComment comment = new NTComment();
		
		String ntcmno = req.getParameter("ntcmno");
		System.out.println("ntcmono : " + ntcmno);
		
		comment.setNtcmno(Integer.parseInt(ntcmno));
		
		boolean success = ntcommentService.deleteComment(comment);
		
		resp.getWriter().append("{\"success\":"+success+"}");

	}

}
