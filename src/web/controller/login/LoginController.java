package web.controller.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Business;
import web.dto.User;
import web.service.face.LogService;
import web.service.impl.LogServiceImpl;


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LogServiceImpl logService = new LogServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(1122);
		
		String backUrl = req.getParameter("backUrl");
//		System.out.println("referer : " + req.getParameter("backUrl"));
	
		//전달 파라미터 얻기 - 일반회원 로그인 정보
		User userTb = logService.getLogingenMember(req);
		
		String url = null;
		//로그인 인증
		boolean genlogin = logService.genLogin(userTb);
		
		if(genlogin == false) {
			//전달 파라미터 얻기 - 사업자회원 로그인 정보
//			System.out.println(genlogin);
			Business business = logService.getLoginBusMember(req);
			
			//로그인 인증
			boolean buslogin = logService.BusLogin(business);
			
			
			if(buslogin) {
				//로그인 사용자의 정보 얻어오기 +usernick etc.
				business = logService.busInfo(business);
				
				//세션정보 저장하기
				HttpSession session = req.getSession();
				
				session.setAttribute("login", buslogin);// 사업자 로그인
				session.setAttribute("bno", business.getBno());
				session.setAttribute("id", business.getId());
				session.setAttribute("pw",business.getPw());
				session.setMaxInactiveInterval(0);//세션시간 무제한

			}else {
				
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("text/html; charset=UTF-8");

				PrintWriter out = resp.getWriter();

				out.println("<script>");
				out.println("alert('로그인 정보가 없습니다');");
				out.println("location.href='/login';");
				out.println("</script>");
				out.close();
				return;
				
			}
			
			
		}else if(genlogin) {
			//로그인 사용자의 정보 얻어오기 +usernick
			userTb = logService.info(userTb);
			
			//세션정보 저장하기
			HttpSession session = req.getSession();
			
			session.setAttribute("login", genlogin);// 일반 로그인
			session.setAttribute("uno", userTb.getUno());
			session.setAttribute("id",userTb.getId() );
			session.setAttribute("pw",userTb.getPw() );
			session.setAttribute("Nick",userTb.getNick() );

			session.setMaxInactiveInterval(0);
			
		}else {
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=UTF-8");

			PrintWriter out = resp.getWriter();

			out.println("<script>");
			out.println("alert('로그인 정보가 없습니다');");
			out.println("location.href='/login';");
			out.println("</script>");
			out.close();
			return;
			
			
		}
		
		System.out.println("LoginController TEST");
		System.out.println(req.getHeader("referer"));
		//메인페이지로 리다이렉트
		resp.sendRedirect(backUrl);
		
	}
	
}
