package web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.QnA;
import web.dto.QnAComment;
import web.service.face.BusinessService;
import web.service.face.QnACommentService;
import web.service.face.QnAService;
import web.service.impl.BusinessServiceImpl;
import web.service.impl.QnACommentServiceImpl;
import web.service.impl.QnAServiceImpl;

/**
 * Servlet implementation class QnAListInfoController
 */
@WebServlet("/admin/qnalistinfo")
public class QnAListInfoController extends HttpServlet {

	private QnAService qnaService = new QnAServiceImpl();
	private QnACommentService qnacommentService = new QnACommentServiceImpl();
	private BusinessService businessService = new BusinessServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {	
			QnA qnano = qnaService.getQnANo(req);

			Map<String, Object> list = qnaService.listshow(qnano);

			req.setAttribute("list", list);

			//댓글 리스트 전달
			QnAComment qnacomment = new QnAComment();
			List<Map<String, Object>> commentList = qnacommentService.getCommentList(qnano);
			req.setAttribute("commentList", commentList);

			req.setAttribute("list", list);

			req.getRequestDispatcher("/WEB-INF/views/admin/qnaShow.jsp").forward(req, resp);

		}else {
			resp.sendRedirect("/login");

		}


	}
}
