package web.controller.board;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.BRComment;
import web.dto.Board;
import web.service.face.BRCommentService;
import web.service.face.BoardService;
import web.service.impl.BRCommentServiceImpl;
import web.service.impl.BoardServiceImpl;
import web.util.Paging;

/**
 * 자유게시판 목록 servlet
 */
@WebServlet("/board/view")
public class BoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/view [get]");
		
		//요청파라미터를 전달하여 Paging 객체 생성
		Paging paging = boardService.getPaging(req);
		System.out.println("paging : " + paging);
				
		//게시글 페이징 처리 조회
		List<Map<String, Object>> boardList = boardService.getList(paging);
		
		
		//페이징계산결과 MODEL값 전달
		req.setAttribute("paging", paging);
		
	
		//조회결과 MODEL값 전달
		req.setAttribute("boardList", boardList);
		
	
	
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/board/boardView.jsp").forward(req, resp);
	}
}
