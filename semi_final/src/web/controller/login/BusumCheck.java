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
@WebServlet("/bsnum/check")
public class BusumCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountServiceImpl accountservice = new AccountServiceImpl();
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 	req.setCharacterEncoding("UTF-8");
		 	
		 	resp.setContentType("application/json;charset=UTF-8");
		 	
		
			System.out.println("/id/bsum");
			
		 	Business bsnum = accountservice.getBCheck(req);
			System.out.println("req 값 : "+ bsnum.getBsnum());
			
			boolean checkBsnum = accountservice.checkBsnum(bsnum.getBsnum());
			System.out.println(checkBsnum);
			resp.getWriter().println("{\"checkBsnum\":"+ checkBsnum +"}");
			
			
			
			
		}
}	
