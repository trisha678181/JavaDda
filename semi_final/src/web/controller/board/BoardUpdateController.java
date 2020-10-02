package web.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.BRImg;
import web.dto.Board;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

/**
 * 자유 게시판 게시물 수정 servlet
 */
@WebServlet("/board/update")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/update [get]");
		
		//로그인한 사람의 굴이 아니면 중단하고 목록으로 리다이렉트
		if(!boardService.checkId(req)) {
			resp.sendRedirect("/board/view");		
			return;
		}
		
		//게시글 번호 파싱
		Board showBoard = boardService.getBoardbrno(req);
		
		//게시글 조회
		showBoard = boardService.show(showBoard);
		
		req.setAttribute("nick", boardService.getNick(showBoard));

		
		//MODEL로 게시글 전달
		req.setAttribute("showBoard", showBoard);
		
		//첨부파일 전달
		List<BRImg> brImg = boardService.showFile(showBoard);
		req.setAttribute("brImgList", brImg);
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/board/boardUpdate.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/update [post]");
		
		boardService.updateBoard(req);
		
		
		
		resp.sendRedirect("/board/view");
	}
}
