package web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import web.service.face.BusinessService;
import web.service.face.QnAService;
import web.service.impl.BusinessServiceImpl;
import web.service.impl.QnAServiceImpl;
import web.util.Paging;

/**
 * Servlet implementation class QnAListController
 */
@WebServlet("/admin/qnalist")
public class QnAListController extends HttpServlet {

	private QnAService qnaService = new QnAServiceImpl();
	private BusinessService businessService = new BusinessServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {
			// 요청 파라미터를 전달하여 Paging 객체 생성하기
			Paging paging = qnaService.getPaging(req);

			// 공지사항리스트 페이징 처리 조회
			List<Map<String, Object>> list = qnaService.getList(paging);

			//페이징계산결화 MODEL값 전달
			req.setAttribute("paging", paging);

			//조회결과 MODEL값 전달
			req.setAttribute("list", list);

			//VIEW
			req.getRequestDispatcher("/WEB-INF/views/admin/qnaView.jsp").forward(req, resp);
		}else {
			resp.sendRedirect("/login");

		}


	}

}
