package web.controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.User;
import web.service.face.UserService;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class EP_PWCheckController
 */
@WebServlet("/editprofile/pwcheck")
public class EP_PWCheckController extends HttpServlet {
	
	UserService userService = new UserServiceImpl();
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 	
	 	User user = new User();
	 	
		HttpSession session = req.getSession();
		int uno = (int)session.getAttribute("uno");
		user.setUno(uno);
	 	
	 	String pw = req.getParameter("userPW");
	 	user.setPw(pw);
	 			
		boolean checkPW = userService.checkPW(user);
		
		System.out.println("checkPW : " + checkPW);
		
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().println("{\"checkPW\":"+ checkPW +"}");
		}
}
