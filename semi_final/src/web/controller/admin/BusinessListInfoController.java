package web.controller.admin;

import java.io.IOException;
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


/**
 * Servlet implementation class NoticeListInfoController
 */
@WebServlet("/admin/businesslistinfo")
public class BusinessListInfoController extends HttpServlet {

	private BusinessService businessService = new BusinessServiceImpl();
	private PlaceService placeService = new PlaceServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {	
			System.out.println("/admin/Businesslistinfo [get]");

			Business businessno = businessService.getBusinessNo(req);

			// 여기 바꾸기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
			Map<String, Object> list = businessService.show(businessno);

			req.setAttribute("list", list);
			
			String path = req.getServletContext().getRealPath("upload");
            req.setAttribute("path", path);
			
			req.getRequestDispatcher("/WEB-INF/views/admin/business_list_info.jsp").forward(req, resp);
		}
		else {
			resp.sendRedirect("/login");

		}
	}

}
