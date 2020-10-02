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
 * 자유게시판 좋아요 servlet
 */
@WebServlet("/board/like")
public class BoardLikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("/board/like [Post]");
			
			int brno = Integer.parseInt(req.getParameter("brno"));
			
			// 테이블에 좋아요 1 추가
			boardService.updateLike(brno);
			
			//테이블에서 좋아요 수 가져옥; 
			int likecount = boardService.selectLike(brno);
			
			
			resp.getWriter().println(likecount);
				
			
		}
	
}
