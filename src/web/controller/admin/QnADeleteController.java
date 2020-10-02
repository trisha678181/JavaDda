package web.controller.admin;

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
 * Servlet implementation class NoticeDeleteController
 */
@WebServlet("/admin/qnadelete")
public class QnADeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QnAService qnaService = new QnAServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {
			System.out.println("/admin/delete [get]");

			QnA qna = qnaService.getQnANo(req);


			qnaService.DeleteQnA(qna);


			resp.sendRedirect("/admin/qnalist");
		}else {
			resp.sendRedirect("/login");

		}


	}
}
