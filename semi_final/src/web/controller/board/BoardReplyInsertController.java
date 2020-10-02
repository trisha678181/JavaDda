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
 * 자유게시판 게시물 댓글 servlet
 */
@WebServlet("/board/replyInsert")
public class BoardReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BRCommentService brcommentService = new BRCommentServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/replyInsert [post]");
		
		if (req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/login");

			return;
		}

		BRComment comment = brcommentService.getComment(req);

		brcommentService.insertComment(comment);

		resp.sendRedirect("/board/show?brno=" + comment.getBrno());

	}

}
