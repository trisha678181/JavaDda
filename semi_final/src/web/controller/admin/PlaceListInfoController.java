package web.controller.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Notice;
import web.dto.PImg;
import web.dto.Place;
import web.service.face.BusinessService;
import web.service.face.PlaceService;
import web.service.impl.BusinessServiceImpl;
import web.service.impl.PlaceServiceImpl;

/**
 * Servlet implementation class PlaceListInfoController
 */
@WebServlet("/admin/placelistinfo")
public class PlaceListInfoController extends HttpServlet {

	private PlaceService placeService = new PlaceServiceImpl();
	private BusinessService businessService = new BusinessServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {
			System.out.println("/admin/placelistinfo [get]");

			Place placeno = placeService.getPlaceNo(req);

			Map<String, Object> list = placeService.show(placeno);

			//상세보기 결과 조회 
			req.setAttribute("list", list);

			//첨부파일 전달
			PImg placeFile = placeService.viewFile(placeno);
			req.setAttribute("placeFile", placeFile);

			req.getRequestDispatcher("/WEB-INF/views/admin/placeShow.jsp").forward(req, resp);
		}else {
			resp.sendRedirect("/login");

		}


	}
}
