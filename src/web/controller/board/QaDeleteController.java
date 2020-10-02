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
 * Servlet implementation class QaDeleteController
 */
@WebServlet("/qa/delete")
public class QaDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private QnAService qnaService = new QnAServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/qa/delete [get]");
		
		QnA qna = qnaService.getQnANo(req);
		
		qnaService.DeleteQnA(qna);
		
		
		resp.sendRedirect("/qa/view");
	}
}
