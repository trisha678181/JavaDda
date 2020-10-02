package web.controller.place;

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
import web.service.face.PlaceService;
import web.service.face.UserService;
import web.service.face.WeatherService;
import web.service.impl.CoupleServiceImpl;
import web.service.impl.PlaceServiceImpl;
import web.service.impl.UserServiceImpl;
import web.service.impl.WeatherServiceImpl;

/**
 * Servlet implementation class PlaceMainController
 */
@WebServlet("/place/main")
public class PlaceMainController extends HttpServlet {
	
	private UserService userService = new UserServiceImpl();
	PlaceService placeService = new PlaceServiceImpl();
//	WeatherService weatherService = new WeatherServiceImpl();
	CoupleService coupleService = new CoupleServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			int res = 0;
			
			List<Integer> likeplace = null;
			
			HttpSession session = req.getSession();
			
			if(session.getAttribute("uno") == null) {
				res = 0;
			}
			
			List<Integer> top3 = coupleService.GetTop3ListNo(); // 찜 장소 조회
			
			if(session.getAttribute("uno") != null) {
				int uno = (int)session.getAttribute("uno");
				User user = userService.GetUserInfo(uno);
				
				res = coupleService.AreYouCouple(user); // 커플이니?
				
				if(res == 1) {
					String[] names = coupleService.SelectCoupleName(user); // 커플 이름 두개 조회
					req.setAttribute("names", names);
					
					int firstday = coupleService.SelectCoupleFirstDay(user); // 사귄 날 조회
					req.setAttribute("firstday", firstday);
					
					String[] births = coupleService.SelectCoupleBirth(); // 커플 생일 두개 조회
					
					List<Anniversary> list = coupleService.CalcDday(names, births, user);// 디데이 목록 만들기
					req.setAttribute("list", list);
					
				}
				likeplace = coupleService.GetLikePlaceListNo(user);// 찜 장소 조회
				
				int[] personallp = userService.PersonalLikePlace(likeplace, top3);
				req.setAttribute("personallp", personallp);
				
			}
			
			req.setAttribute("res", res);
		
//			Map<String, String> map = weatherService.GetWeatherInfo(); // 날씨 정보 조회
//			req.setAttribute("map", map);
			
//			int air = weatherService.GetAirInfo(); // 미세먼지 정보 조회
//			req.setAttribute("air", air);
			
			List<Map<String, Object>> cafelist = placeService.SelectCafeTop3();
			req.setAttribute("cafelist", cafelist);
			
			List<Map<String, Object>> actlist = placeService.SelectActTop3();
			req.setAttribute("actlist", actlist);
			
			
			List<Map<String, Object>> pickmap = placeService.SelectPick();			
			req.setAttribute("pickmap", pickmap);
			
			String path = req.getServletContext().getRealPath("/upload");
		    req.setAttribute("path", path);
			
			req.getRequestDispatcher("/WEB-INF/views/place/main.jsp").forward(req, resp);
			
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}

}
