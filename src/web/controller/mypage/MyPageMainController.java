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

import web.dto.Coupon;
import web.dto.User;
import web.service.face.CoupleService;
import web.service.face.UserService;
import web.service.impl.CoupleServiceImpl;
import web.service.impl.UserServiceImpl;

/**
 * 마이페이지 메인화면 servlet
 */
@WebServlet("/mypage/main")
public class MyPageMainController extends HttpServlet {

	private UserService userService = new UserServiceImpl();
	private CoupleService coupleService = new CoupleServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 세션 --------------------------------------------- 
		
		HttpSession session = req.getSession();
		
		if( req.getSession().getAttribute("login") == null ) {
	         resp.sendRedirect("/login");

	         return;
	      }

		int uno = (int)session.getAttribute("uno");
		
		User user = userService.GetUserInfo(uno);

		req.setAttribute("user", user);
		
		//  커플인지 ----------------------------------------
		
		int res = coupleService.AreYouCouple(user); // 커플이니?
		req.setAttribute("res", res);
		
		if(res == 1) { // 커플이면
			String[] names = coupleService.SelectCoupleName(user); // 커플 이름 두개 조회
			req.setAttribute("names", names);
			
			int firstday = coupleService.SelectCoupleFirstDay(user); // 사귄 날 조회
			req.setAttribute("firstday", firstday);
			
			User oppo = userService.GetOppoInfo(user); // 상대방 정보 조회
			req.setAttribute("oppo", oppo);
			
		}
		
		coupleService.SetLikePlaceTogether(user);
		List<Map<String, Object>> likeplace = coupleService.GetLikePlaceList(user); // 찜 장소 조회
		req.setAttribute("likeplace", likeplace);
		req.setAttribute("likeplacecnt", likeplace.size());
		System.out.println("likeplace " + likeplace.toString());
		
		List<Map<String, Object>> couponlist = userService.GetCouponList(user);
		req.setAttribute("couponlist", couponlist);
		req.setAttribute("couponlistcnt", couponlist.size());
		
		List<Map<String, Object>> boardlist = userService.GetBoardListByUser(user);
		req.setAttribute("boardlist", boardlist);
		req.setAttribute("boardlistcnt", boardlist.size());
		
		List<Map<String, Object>> reviewlist = userService.GetReviewListByUser(user);
		req.setAttribute("reviewlist", reviewlist);
		req.setAttribute("reviewlistcnt", reviewlist.size());
		
		String path = req.getServletContext().getRealPath("upload");
	    req.setAttribute("path", path);   

		req.getRequestDispatcher("/WEB-INF/views/mypage/main.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();

		int uno = (int)session.getAttribute("uno");

		User user = userService.GetUserInfo(uno);

		req.setAttribute("user", user);

		String[] names = coupleService.SelectCoupleName(user); // 커플 이름 두개 조회
		req.setAttribute("names", names);

		int firstday = coupleService.SelectCoupleFirstDay(user); // 사귄 날 조회
		req.setAttribute("firstday", firstday);
		
		String path = req.getServletContext().getRealPath("upload");
	    req.setAttribute("path", path);

		req.getRequestDispatcher("/WEB-INF/views/mypage/main.jsp").forward(req, resp);
	}

}
