package web.controller.board;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.QnA;
import web.service.face.QnAService;
import web.service.impl.QnAServiceImpl;
import web.util.Paging;

/**
 * QnA 목록 servlet
 */
@WebServlet("/qa/view")
public class QaViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private QnAService qnaService = new QnAServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/qa/view [get]");
		
		//요청파라미터를 전달하여 Paging 객체 생성
		Paging paging = qnaService.getPaging(req);
//		System.out.println("paging : " + paging);
		
		
		//게시글 페이징 처리 조회
		List<Map<String, Object>> qnaList = qnaService.getList(paging);
//		System.out.println("qnaList : " + qnaList);
		
		//페이징 계산 결과 MODEL 값 전달
		req.setAttribute("paging", paging);
		
		
		
		//조회결과 MODEL값 전달
		req.setAttribute("qnaList", qnaList);
		
		req.getRequestDispatcher("/WEB-INF/views/board/qaView.jsp").forward(req, resp);
	}
}











