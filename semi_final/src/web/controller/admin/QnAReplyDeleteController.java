package web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.NTComment;
import web.dto.QnAComment;
import web.service.face.NTCommentService;
import web.service.face.QnACommentService;
import web.service.impl.NTCommentServiceImpl;
import web.service.impl.QnACommentServiceImpl;

/**
 * Servlet implementation class NoticeReplyDeleteController
 */
@WebServlet("/admin/replyDelete")
public class QnAReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QnACommentService qnacommentService = new QnACommentServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {
			System.out.println("/admin/replyDelete [post]");

			QnAComment comment = new QnAComment();

			String qnacmno = req.getParameter("qnacmno");
			System.out.println("qnacmno : " + qnacmno);

			comment.setQnacmno(Integer.parseInt(qnacmno));

			System.out.println(comment);
			boolean success = qnacommentService.deleteComment(comment);

			resp.getWriter().append("{\"success\":"+success+"}");

		}else {
			resp.sendRedirect("/login");

		}
	}
}
