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

import web.dto.User;
import web.service.face.UserService;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class ReviewListController
 */
@WebServlet("/mypage/reviewlist")
public class ReviewListController extends HttpServlet {

	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		int uno = (int) session.getAttribute("uno");
		User user = userService.GetUserInfo(uno);

		req.setAttribute("user", user);

		List<Map<String, Object>> reviewlist = userService.GetReviewListByUser(user);
		req.setAttribute("reviewlist", reviewlist);
		req.setAttribute("reviewlistcnt", reviewlist.size());

		req.getRequestDispatcher("/WEB-INF/views/mypage/ReviewPlus.jsp").forward(req, resp);

	}

}
