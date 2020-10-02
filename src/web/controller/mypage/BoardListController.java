package web.controller.mypage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * Servlet implementation class BoardListController
 */
@WebServlet("/mypage/boardlist")
public class BoardListController extends HttpServlet {

	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		int uno = (int) session.getAttribute("uno");
		User user = userService.GetUserInfo(uno);

		req.setAttribute("user", user);

		List<Map<String, Object>> boardlist = userService.GetBoardListByUser(user);
		req.setAttribute("boardlist", boardlist);
		req.setAttribute("boardlistcnt", boardlist.size());

		req.getRequestDispatcher("/WEB-INF/views/mypage/BoardPlus.jsp").forward(req, resp);

	}

}
