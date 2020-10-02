package web.controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.CoupleService;
import web.service.impl.CoupleServiceImpl;

/**
 * Servlet implementation class MakeCoupleController
 */
@WebServlet("/couple/make")
public class MakeCoupleController extends HttpServlet {
	
	private CoupleService coupleService = new CoupleServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String couplevalid = req.getParameter("couplevalid");
		
		//coupleService.MakeCouple(user);
		
		resp.sendRedirect("/mypage/main");
		
	}

}
