package web.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.BRComment;
import web.service.face.BRCommentService;
import web.service.impl.BRCommentServiceImpl;

/**
 * 댓글 삭제 servlet
 */
@WebServlet("/board/replyDelete")
public class BoardReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BRCommentService brcommentService = new BRCommentServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/replyDelete [post]");

		if (req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/login");

			return;
		}
		
		BRComment comment = new BRComment();

		String brcmno = req.getParameter("brcmno");
//		System.out.println("brcmno : " + brcmno);

		comment.setBrcmno(Integer.parseInt(brcmno));

		boolean success = brcommentService.deleteComment(comment);

		resp.getWriter().append("{\"success\":" + success + "}");

	}

}
