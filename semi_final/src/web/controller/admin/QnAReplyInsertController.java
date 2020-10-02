package web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.QnAComment;
import web.service.face.QnACommentService;
import web.service.impl.QnACommentServiceImpl;

/**
 * 
 */
@WebServlet("/admin/replyInsert")
public class QnAReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QnACommentService qnacommentService = new QnACommentServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {
			System.out.println("/admin/replyInsert [post]");

			QnAComment comment = qnacommentService.getComment(req);

			qnacommentService.insertComment(comment);

			resp.sendRedirect("/admin/qnalistinfo?qnano="+comment.getQnano());
		}else {
			resp.sendRedirect("/login");

		}

	}

}
