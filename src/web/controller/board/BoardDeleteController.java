package web.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Board;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

/**
 * 자유게시판 게시물 삭제 servlet
 */
@WebServlet("/board/delete")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/delete [get]");
		
		Board board = boardService.getBoardbrno(req);
		
		boardService.deleteBoard(board);
		
		resp.sendRedirect("/board/view");
	}
}
