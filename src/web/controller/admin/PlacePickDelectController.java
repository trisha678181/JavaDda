package web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.PlaceService;
import web.service.impl.PlaceServiceImpl;

/**
 * Servlet implementation class PlacePickListController
 */
@WebServlet("/admin/listPickdelect")
public class PlacePickDelectController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PlaceService placeService = new PlaceServiceImpl();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {
			String names = req.getParameter("names");
			System.out.println("names : " + names);

			if( !"".equals(names) && names != null) {
				placeService.placeDelectPick(names);
				//System.out.println("names : " + names);
				System.out.println("ppoick" + getInitParameter("ppick"));
			}

			resp.sendRedirect("/admin/placelist");

		}else {
			resp.sendRedirect("/login");

		}
	}

}
