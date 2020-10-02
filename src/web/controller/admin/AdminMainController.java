package web.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.User;
import web.service.face.AdminService;
import web.service.impl.AdminServiceImpl;
import web.util.Paging;

/**
 * Servlet implementation class AdminMain
 */
@WebServlet("/admin/main")
public class AdminMainController extends HttpServlet {
	
	private AdminService adminService = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {
			
			// 요청 파라미터를 전달하여 Paging 객체 생성하기
			Paging paging = adminService.getPaging(req);
			
			System.out.println("UserListController - " + paging);
			
			// 회원리스트 페이징 처리 조회
			List<User> list = adminService.getList(paging);
			//페이징계산결화 MODEL값 전달
			req.setAttribute("paging", paging);
			
			//조회결과 MODEL값 전달
			req.setAttribute("list", list);
			req.getRequestDispatcher("/WEB-INF/views/admin/main.jsp").forward(req, resp);
		}else {
			resp.sendRedirect("/login");
		}
				
	}

}
