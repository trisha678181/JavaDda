package web.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.QnAService;
import web.service.impl.QnAServiceImpl;

/**
 * QnA 등록 servlet
 */
@WebServlet("/qa/write")
public class QaWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private QnAService qnaService = new QnAServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/qa/write [get]");
		
		
		
		//로그인 되어있지 않으면 리다이렉트 
		if( req.getSession().getAttribute("bno") == null ) {
					
			resp.sendRedirect("/login");
					
			return;
		}
		
		
		req.getRequestDispatcher("/WEB-INF/views/board/qaWrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/qa/write [post]");
		
		req.setCharacterEncoding("utf-8");
		
		//작성글 삽입
		qnaService.write(req);

		
		resp.sendRedirect("/qa/view");
	}
}
