package web.controller.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import web.dto.Anniversary;
import web.dto.User;
import web.service.face.CoupleService;
import web.service.face.UserService;
import web.service.impl.CoupleServiceImpl;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class EditAnniController
 */
@WebServlet("/mypage/couple/editanni")
public class EditAnniController extends HttpServlet {
	
	private UserService userService = new UserServiceImpl();
	private CoupleService coupleService = new CoupleServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AddAnniController doPost");
		
		String annititle = req.getParameter("annititle");
		String annidate = req.getParameter("annidate");
		String old_annititle = req.getParameter("old_annititle");

		HttpSession session = req.getSession();
		int uno = (int) session.getAttribute("uno");

		User user = userService.GetUserInfo(uno);

		req.setAttribute("user", user);

		boolean bl = coupleService.EditAnni(annititle, annidate, old_annititle, user);
		List<Anniversary> list = coupleService.GetAnni(user);
		
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().println("{\"list\":"+ new Gson().toJson(list) +", \"bl\":"+new Gson().toJson(bl)+ "}");
		

	}

}
