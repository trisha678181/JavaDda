package web.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

/**
 * 자유게시판 등록 servlet
 */
@WebServlet("/board/write")
public class BoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/write [get]");
		
		//로그인 되어있지 않으면 리다이렉트 
		if( req.getSession().getAttribute("login") == null ) {
				resp.sendRedirect("/login");

		        return;
		}

		req.getRequestDispatcher("/WEB-INF/views/board/boardWrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/write [post]");
		
		//작성글 삽입
		boardService.write(req);
		
		resp.sendRedirect("/board/view");
	}

}
