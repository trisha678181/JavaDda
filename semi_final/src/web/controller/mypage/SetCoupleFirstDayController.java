package web.controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.User;
import web.service.face.CoupleService;
import web.service.face.UserService;
import web.service.impl.CoupleServiceImpl;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class SetCoupleFirstDayController
 */
@WebServlet("/couple/setfirstday")
public class SetCoupleFirstDayController extends HttpServlet {
	
	private UserService userService = new UserServiceImpl();
	private CoupleService coupleService = new CoupleServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("SetCoupleFirstDayController doPOST");
		
		String firstday = req.getParameter("firstday");
		
		HttpSession session = req.getSession();
		int uno = (int)session.getAttribute("uno");
		
		User user = userService.GetUserInfo(uno);
		
		coupleService.MakeCoupleFirstday(firstday, user);
		
	}

}
