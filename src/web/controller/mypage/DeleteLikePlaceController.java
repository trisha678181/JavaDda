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

import com.google.gson.Gson;

import web.dto.Anniversary;
import web.dto.Place;
import web.dto.User;
import web.service.face.CoupleService;
import web.service.face.PlaceService;
import web.service.face.UserService;
import web.service.impl.CoupleServiceImpl;
import web.service.impl.PlaceServiceImpl;
import web.service.impl.UserServiceImpl;

/**
 * Servlet implementation class DeleteLikePlaceController
 */
@WebServlet("/mypage/deletelikeplace")
public class DeleteLikePlaceController extends HttpServlet {

	private UserService userService = new UserServiceImpl();
	private CoupleService coupleService = new CoupleServiceImpl();
	private PlaceService placeService = new PlaceServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pname = req.getParameter("placename");
		
		Place place = placeService.GetPlaceByName(pname);
		
		HttpSession session = req.getSession();
		int uno = (int) session.getAttribute("uno");

		User user = userService.GetUserInfo(uno);

		req.setAttribute("user", user);

		coupleService.DeleteLikePlace(place, user);
		
		List<Integer> likeplace2 = coupleService.GetLikePlaceListNo(user);// 찜 장소 조회
		
		List<Integer> top3 = coupleService.GetTop3ListNo(); // 찜 장소 조회
		
		int[] personallp = userService.PersonalLikePlace(likeplace2, top3);
		req.setAttribute("personallp", personallp);
		
		coupleService.SetLikePlaceTogether(user);
		List<Map<String, Object>> likeplace = coupleService.GetLikePlaceList(user);// 찜 장소 조회
		
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().println("{\"likeplace\":"+ new Gson().toJson(likeplace) +", \"personallp\":" + new Gson().toJson(personallp) + ", \"bl\":"+new Gson().toJson(true)+", \"likeplacecnt\":"+new Gson().toJson(likeplace.size())+ "}");
		

	}
	
}
