package web.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.QnA;
import web.service.face.QnAService;
import web.service.impl.QnAServiceImpl;

/**
 * Servlet implementation class QaShowController
 */
@WebServlet("/qa/show")
public class QaShowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private QnAService qnaService = new QnAServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/qa/show [get]");
		
		//전달파라미터 얻기 - qnano
		QnA qnano = qnaService.getQnANo(req);
		
		//상세보기 결과 조회
		QnA showQna = qnaService.show(qnano);
		
		//사업자이름 전달
		req.setAttribute("name", qnaService.getName(showQna));
		
		//조회결과 MODEL 값 전달
		req.setAttribute("showQna", showQna);
		
		
		req.getRequestDispatcher("/WEB-INF/views/board/qaShow.jsp").forward(req, resp);

	}
}
