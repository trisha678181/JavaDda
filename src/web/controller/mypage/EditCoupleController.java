package web.controller.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Anniversary;
import web.dto.User;
import web.service.face.CoupleService;
import web.service.face.UserService;
import web.service.impl.CoupleServiceImpl;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class EditCoupleController
 */
@WebServlet("/mypage/editcouple")
public class EditCoupleController extends HttpServlet {
	
	private UserService userService = new UserServiceImpl();
	private CoupleService coupleService = new CoupleServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		int uno = (int)session.getAttribute("uno");
		
		User user = userService.GetUserInfo(uno);
		
		User oppo = userService.GetOppoInfo(user);
		
		req.setAttribute("user", user);
		req.setAttribute("oppo", oppo);
		
		System.out.println("oppo : " + oppo.toString());
		
		List<Anniversary> anni_list = coupleService.GetAnni(user);
		
		req.setAttribute("anni_list", anni_list);
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/editcouple.jsp").forward(req, resp);
		
		
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("EditCoupleController POST");
		
		HttpSession session = req.getSession();
		int uno = (int) session.getAttribute("uno");

		User user = userService.GetUserInfo(uno);
		
		coupleService.ExitCouple(user);
		
		resp.sendRedirect("/mypage/main");
		
		
	}

}
