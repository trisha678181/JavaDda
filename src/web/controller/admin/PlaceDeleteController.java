package web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Place;
import web.service.face.PlaceService;
import web.service.impl.PlaceServiceImpl;


/**
 * Servlet implementation class NoticeDeleteController
 */
@WebServlet("/admin/placedelete")
public class PlaceDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PlaceService placeService = new PlaceServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {
			System.out.println("/admin/delete [get]");

			Place place = placeService.getPlaceNo(req);
			System.out.println("delete noticeno : " + place);

			placeService.DeletePlace(place);


			resp.sendRedirect("/admin/placelist");
		}
		else {
			resp.sendRedirect("/login");

		}
	}
}
