package web.controller.place;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import web.dto.Anniversary;
import web.dto.Place;
import web.dto.User;
import web.service.face.PlaceService;
import web.service.face.UserService;
import web.service.impl.PlaceServiceImpl;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class AddLikePlaceController
 */
@WebServlet("/addlikeplace")
public class AddLikePlaceController extends HttpServlet {

	private UserService userService = new UserServiceImpl();
	private PlaceService placeService = new PlaceServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String placename = req.getParameter("placename");
		
		HttpSession session = req.getSession();
		int uno = (int) session.getAttribute("uno");

		User user = userService.GetUserInfo(uno);

		req.setAttribute("user", user);
		
		Place place = placeService.GetPlaceByName(placename);
		
		userService.SetLikePlace(user, place);

		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().println("{\"bl\":"+ new Gson().toJson(true)+ "}");
		

	}

}
