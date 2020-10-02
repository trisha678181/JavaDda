package web.controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import web.dto.Business;
import web.service.face.AccountService;
import web.service.impl.AccountServiceImpl;




@WebServlet("/account/business")
public class AccountBusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService accountService = new AccountServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//view지정 - forward
		req.getRequestDispatcher("/WEB-INF/views/login/business_member.jsp").forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청파라미터 처리 아이디 패스워드 닉네임
		Business acparam = accountService.getAccountBusMember(req);
		
		//회원 가입
		accountService.busaccount(acparam);
//		String userId = req.getParameter("userId");	
		//가입축하 화면
		System.out.println("acparam : " + acparam);
		req.setAttribute("result", acparam);
		req.getRequestDispatcher("/WEB-INF/views/login/welcome.jsp").forward(req, resp);
		
		
	}

}
