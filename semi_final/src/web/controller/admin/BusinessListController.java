package web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Business;
import web.service.face.BusinessService;
import web.service.face.PlaceService;
import web.service.impl.BusinessServiceImpl;
import web.service.impl.PlaceServiceImpl;
import web.util.Paging;

/**
 * Servlet implementation class AdminMain
 */
@WebServlet("/admin/businesslist")
public class BusinessListController extends HttpServlet {

	private BusinessService businessService = new BusinessServiceImpl();
	private PlaceService placeService = new PlaceServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {
			// 요청 파라미터를 전달하여 Paging 객체 생성하기
			Paging paging = businessService.getPaging(req);

			// 사업자리스트 페이징 처리 조회
			//			List<Business> list = businessService.getList(paging);
			List<Map<String, Object>> list = businessService.getList(paging);
			//페이징계산결화 MODEL값 전달
			req.setAttribute("paging", paging);

			//조회결과 MODEL값 전달
			req.setAttribute("list", list);

			req.getRequestDispatcher("/WEB-INF/views/admin/businessView.jsp").forward(req, resp);


		}else {
			resp.sendRedirect("/login");

		}

	}


}
