package web.controller.mypage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class SendMail
 */
@WebServlet("/mypage/sendmail")
public class SendMail extends HttpServlet {
	
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		int uno = (int)session.getAttribute("uno");
		
		String ooppoID = req.getParameter("oppoID");
		
		User user = userService.GetUserInfo(uno);
		User oppo = userService.GetUserById(ooppoID);

		List<User> list = new ArrayList<>();

		list.add(user);
		list.add(oppo);
		
		System.out.println("SendMail POST : " + list.toString());

		userService.SendMail(list);

	}

}
