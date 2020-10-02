package web.controller.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Admin;
import web.service.impl.LogServiceImpl;


@WebServlet("/admin/login")
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LogServiceImpl logService = new LogServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/login/adminLogin.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//전달 파라미터 얻기 - 관리자로그인
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
//		System.out.println("id + pw : "+id+","+pw);
		
		Boolean admin = logService.loginAdmin(id, pw);
		
		if(admin) {
			HttpSession session = req.getSession();
			
			session.setAttribute("admin", admin);
			session.setAttribute("id", id);
			session.setAttribute("pw", pw);
			session.setMaxInactiveInterval(0);//세션시간 무제한
			
			
		}else {
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=UTF-8");

			PrintWriter out = resp.getWriter();

			out.println("<script>");
			out.println("alert('로그인 정보가 없습니다');");
			out.println("location.href='/admin/login';");
			out.println("</script>");
			out.close();
			return;
			
			
		}
		resp.sendRedirect("/admin/main");
	}
	
}
