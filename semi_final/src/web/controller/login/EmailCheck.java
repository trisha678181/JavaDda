package web.controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Business;
import web.dto.User;
import web.service.face.AccountService;
import web.service.impl.AccountServiceImpl;




/**
 * Servlet implementation class IdCheck
 */
@WebServlet("/email/check")
public class EmailCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService accountservice = new AccountServiceImpl();
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 	req.setCharacterEncoding("UTF-8");
		 	resp.setContentType("application/json;charset=UTF-8");
		 	
		
			System.out.println("/email/check");
			User usertb = accountservice.getCheck(req);
			Business business = accountservice.getBCheck(req);
		
			System.out.println("usertb : " + usertb + "business : " + business);
			boolean checkEmail = accountservice.checkEmail(usertb.getMail(), business.getMail());

			System.out.println("email/check : "+ checkEmail);
				
			resp.getWriter().println("{\"checkEmail\":" + checkEmail +"}");
			
			
			
			
		}
}	
