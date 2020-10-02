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
import web.dto.BRImg;
import web.dto.Board;
import web.service.face.BRCommentService;
import web.service.face.BoardService;
import web.service.impl.BRCommentServiceImpl;
import web.service.impl.BoardServiceImpl;

/**
 * 자유게시판 상세 보기 servlet
 */
@WebServlet("/board/show")
public class BoardShowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService boardService = new BoardServiceImpl();
	private BRCommentService brcommentService = new BRCommentServiceImpl();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/boarbd/show [get]");
		
		//전달파라미터 얻기 - brno
		Board brno = boardService.getBoardbrno(req);
		
		//상세보기 결과 조회
		Board showBoard = boardService.show(brno);
		
		//첨부파일 정보 VIEW에 전달
		List<BRImg> brImg = boardService.showFile(showBoard);
//		System.out.println("brImg : " + brImg);
		
		req.setAttribute("brImgList", brImg);
		
		//닉네임 전달
		req.setAttribute("nick", boardService.getNick(showBoard));
		
		//조회결과 MODEL값 전달
		req.setAttribute("showBoard", showBoard);
		
		//총 댓글의 수
		int replyCount = brcommentService.replyCountComment(showBoard);
		System.out.println("replycount : " + replyCount);
			
		req.setAttribute("replyCount", replyCount);
		
		//댓글 리스트 전달
		BRComment brcomment = new BRComment();
		List<Map<String, Object>> commentList = brcommentService.getCommentList(showBoard);
		req.setAttribute("commentList", commentList);
		
		
		req.getRequestDispatcher("/WEB-INF/views/board/boardShow.jsp").forward(req, resp);

	}
}
