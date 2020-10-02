package web.controller.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Business;
import web.dto.User;
import web.service.face.LogService;
import web.service.impl.LogServiceImpl;

/**
 * Servlet implementation class Id_FInderController
 */
@WebServlet("/find/password")
public class pw_FInderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LogService logService = new LogServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/login/find_pw.jsp").forward(req, resp);
	
	}
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=UTF-8");
			
			User userPwResult = logService.findPwUser(req);
			Business businessPwResult = logService.findPwBusiness(req);
			

			if(userPwResult != null) {
				req.setAttribute("result", userPwResult);
			}
			if(businessPwResult != null) {
				System.out.println(userPwResult);
				req.setAttribute("result", businessPwResult);
				
			}
			System.out.println("전달값 : " + userPwResult +  " or " + businessPwResult);
			req.getRequestDispatcher("/WEB-INF/views/login/find_pw_suc.jsp").forward(req, resp);
		}
	
}
