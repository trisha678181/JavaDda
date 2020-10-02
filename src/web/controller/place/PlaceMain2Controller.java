package web.controller.place;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Location;
import web.dto.PImg;
import web.dto.Place;
import web.service.face.PlaceService;
import web.service.face.WeatherService;
import web.service.impl.PlaceServiceImpl;
import web.service.impl.WeatherServiceImpl;

/**
 * Servlet implementation class PlaceMain2Controller
 */
@WebServlet("/place/main2")
public class PlaceMain2Controller extends HttpServlet {
	
	PlaceService placeService = new PlaceServiceImpl();
	WeatherService weatherService = new WeatherServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<String, String> weather = weatherService.GetWeatherInfo(); // 날씨 정보 조회
		req.setAttribute("weather", weather);
		
		int air = weatherService.GetAirInfo(); // 미세먼지 정보 조회
		req.setAttribute("air", air);

		String param = req.getParameter("lno");
		
		int lno = 0;
		if( param != null && !"".equals(param) ) {
			lno = Integer.parseInt(req.getParameter("lno"));	
		}

		param = req.getParameter("ppcode");
		int ppcode = 0;
		if( param != null && !"".equals(param) ) {
			ppcode = Integer.parseInt(req.getParameter("ppcode"));	
		}
		
		param = req.getParameter("search");
		String search = "";
		if( param != null && !"".equals(param)) {
			search = req.getParameter("search");
		}
		
//		lno = 1; //강남역 테스트
		List<Map<String, Object>> map = placeService.getPlaceOfLocFilter(lno, ppcode, search);
		System.out.println("맵봐보자" + map.size());
		req.setAttribute("PlaceOfLoc", map);

		String path = req.getServletContext().getRealPath("/upload");
	    req.setAttribute("path", path);
	
		req.getRequestDispatcher("/WEB-INF/views/place/main2.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
