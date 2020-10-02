package web.controller.mypage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class LikePlaceListController
 */
@WebServlet("/mypage/likeplacelist")
public class LikePlaceListController extends HttpServlet {

	private UserService userService = new UserServiceImpl();
	private CoupleService coupleService = new CoupleServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("LikePlaceListController GET");
		
		HttpSession session = req.getSession();

		int uno = (int) session.getAttribute("uno");

		User user = userService.GetUserInfo(uno);

		User oppo = userService.GetOppoInfo(user);

		req.setAttribute("user", user);
		req.setAttribute("oppo", oppo);

		coupleService.SetLikePlaceTogether(user);
		List<Map<String, Object>> likeplace = coupleService.GetLikePlaceList(user);// 찜 장소 조회

		req.setAttribute("likeplace", likeplace);
		req.setAttribute("likeplacecnt", likeplace.size());
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/LikePlacePlus.jsp").forward(req, resp);

	}
	
}
