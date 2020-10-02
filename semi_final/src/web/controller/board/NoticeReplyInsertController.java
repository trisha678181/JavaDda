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
 * 
 */
@WebServlet("/notice/replyInsert")
public class NoticeReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NTCommentService ntcommentService = new NTCommentServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/notice/replyInsert [post]");
		
		NTComment comment = ntcommentService.getComment(req);
		
		ntcommentService.insertComment(comment);
		
		resp.sendRedirect("/notice/show?noticeno="+comment.getNoticeno());

	
	}
	
}
