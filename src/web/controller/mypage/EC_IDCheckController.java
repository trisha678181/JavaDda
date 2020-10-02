package web.controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.dto.User;
import web.service.face.UserService;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class EC_IDCheckController
 */
@WebServlet("/editcouple/idcheck")
public class EC_IDCheckController extends HttpServlet {
	
	UserService userService = new UserServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("EC_IDCheckController POST");
		
		String ooppoID = req.getParameter("oppoID");
		
		User oppo = userService.GetUserById(ooppoID);
	 			
		boolean oppoID = userService.checkOppoID(ooppoID);
		
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().println("{\"oppoID\":"+ new Gson().toJson(oppoID) +", \"oppo\":"+new Gson().toJson(oppo)+"}");
		
	}
	

}
