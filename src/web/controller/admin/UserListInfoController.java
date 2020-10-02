package web.controller.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.User;
import web.service.face.UserService;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserListInfoController
 */
@WebServlet("/admin/userlistinfo")
public class UserListInfoController extends HttpServlet {

	private UserService userService = new UserServiceImpl();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {	
			User uno = userService.getUserNo(req);

			User user = userService.show(uno); // 유저 정보 가져오기
			req.setAttribute("list", user);

			Map<String, Object> map = userService.coupleshow(uno); // 커플 정보 가져오기

			req.setAttribute("map", map);
//=------------------------새로 수정한 부분------------------------------
			int boardcount = userService.Boardcount(uno); // 게시판 작성 개수 가져오기
			req.setAttribute("boardcount", boardcount);

			int reviewcount = userService.Reviewcount(uno);
			req.setAttribute("reviewcount", reviewcount);
			
			int like_placecount = userService.Like_placecount(uno);
			req.setAttribute("like_placecount", like_placecount);
			
			int have_couponcount = userService.Have_couponcount(uno);
			req.setAttribute("havecouponcount", have_couponcount);
			
			req.getRequestDispatcher("/WEB-INF/views/admin/user_list_info.jsp").forward(req, resp);

		}else {
			resp.sendRedirect("/login");

		}
	}

}
