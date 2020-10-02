package web.controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.User;
import web.service.face.CoupleService;
import web.service.face.UserService;
import web.service.impl.CoupleServiceImpl;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class CoupleValidController
 */
@WebServlet("/couple/valid")
public class CoupleValidController extends HttpServlet {

	UserService userService = new UserServiceImpl();
	CoupleService coupleService = new CoupleServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("CoupleValidController GET");

		if (req.getSession().getAttribute("login") == null) {
			resp.sendRedirect("/login2");

			return;
		}
		
		HttpSession session = req.getSession();
		int uno = (int) session.getAttribute("uno");

		User user = userService.GetUserInfo(uno);

		coupleService.MakeCouple(user);
		//coupleService.DeleteCoupleValid(couplevalid);
		
		resp.sendRedirect("/mypage/main");


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("CoupleValidController POST");

		String couplevalid = req.getParameter("couplevalid");

		HttpSession session = req.getSession();
		int uno = (int) session.getAttribute("uno");

		User user = userService.GetUserInfo(uno);

		boolean coupledlsl = userService.MakeCouple(couplevalid, user); // 입력한거랑 같은지
		
		if(coupledlsl) {
			coupleService.MakeCouple(user);
			//coupleService.DeleteCoupleValid(couplevalid);
			
		}
		
		resp.sendRedirect("/mypage/main");
		
		System.out.println("여기");

	}
}
