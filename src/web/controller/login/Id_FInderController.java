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
@WebServlet("/find/id")
public class Id_FInderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LogService logService = new LogServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("findid/get");
		req.getRequestDispatcher("/WEB-INF/views/login/find_id.jsp").forward(req, resp);
	
	}
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=UTF-8");
			
			String name = req.getParameter("userName");
			String email = req.getParameter("email");
		
			
			User userResult = logService.findIdUser(name, email);
			Business businessResult = logService.findIdBusiness(name, email);
			
			

			if(userResult != null) {
				req.setAttribute("result", userResult);
				System.out.println("not null : "+ userResult);
			}

			if(businessResult != null) {
				req.setAttribute("result", businessResult);
				System.out.println("not null : "+ businessResult);
			}
			System.out.println("전달값 : " + userResult +  " or " + businessResult);
//			resp.sendRedirect("/find/id/suc");
			req.getRequestDispatcher("/WEB-INF/views/login/find_id_suc.jsp").forward(req, resp);
		}
	
}
