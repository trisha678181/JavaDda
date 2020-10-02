package web.controller.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FindIdSucess
 */
@WebServlet("/find/id/suc")
public class FindIdSucess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/find/id/suc - get");
		req.setCharacterEncoding("UTF-8");
		req.getRequestDispatcher("/WEB-INF/views/login/find_id_suc.jsp").forward(req, resp);
		
		}
	
}
