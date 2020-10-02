package web.controller.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import sun.rmi.server.Dispatcher;


@WebServlet("/welcome/bus")
public class WelcomeBus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("get : "+req);
		req.getRequestDispatcher("/WEB-INF/views/login/welcome.jsp").forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("post : "+req);
		req.setCharacterEncoding("UTF-8");
	 	resp.setContentType("application/json;charset=UTF-8");
	 	String userId = req.getParameter("userId");
	 	req.setAttribute("userId", userId);
	 	
	 	
		
		}
	
}
