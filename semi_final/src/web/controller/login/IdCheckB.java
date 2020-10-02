//package web.controller.login;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import web.dto.Business;
//import web.service.face.AccountService;
//import web.service.impl.AccountServiceImpl;
//
//
//
//
///**
// * Servlet implementation class IdCheck
// */
//@WebServlet("/id/check/b")
//public class IdCheckB extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private AccountService accountservice = new AccountServiceImpl();
//	
//	@Override
//		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		 	req.setCharacterEncoding("UTF-8");
//		 	resp.setContentType("application/json;charset=UTF-8");
//		 	
//		
//			System.out.println("/id/check/b");
//			
//			System.out.println(req);
//			Business business = accountservice.getBCheck(req);
//			
//			
//			boolean checkBId = accountservice.checkBId(business);
//			resp.getWriter().println("{\"checkBId\":" + checkBId +"}");
//			
//			
//			
//			
//		}
//}	
