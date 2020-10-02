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

import web.dto.Coupon;
import web.dto.HaveCoupon;
import web.dto.Location;
import web.dto.PImg;
import web.dto.Place;
import web.dto.Review;
import web.service.face.PlaceService;
import web.service.face.ReviewService;
import web.service.face.WeatherService;
import web.service.impl.PlaceServiceImpl;
import web.service.impl.ReviewServiceImpl;
import web.service.impl.WeatherServiceImpl;


@WebServlet("/place/detail")
public class PlaceDetailController extends HttpServlet {
			
	PlaceService placeService = new PlaceServiceImpl();
	WeatherService weatherService = new WeatherServiceImpl();
	ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<String, String> weather = weatherService.GetWeatherInfo(); // 날씨 정보 조회
		req.setAttribute("weather", weather);
		
		int air = weatherService.GetAirInfo(); // 미세먼지 정보 조회
		req.setAttribute("air", air);
		
		//pno값 가져오기
		String param = req.getParameter("pno");
		int pno = Integer.parseInt(param);
		
		int uno = 0;
		
		HttpSession session = req.getSession();
		if(session.getAttribute("uno") != null) {
			uno = (int)session.getAttribute("uno");
		}
		
		boolean likeplacebl = placeService.likeplacebl(pno,uno);
		req.setAttribute("likeplacebl", likeplacebl);
		
		
		//리스트 불러오기
		List<Map<String, Object>> map = placeService.getPlaceList(pno);
		req.setAttribute("placelist", map);
		
		//내용 불러오기
		Place place = placeService.getPlaceNo(pno);
		req.setAttribute("place", place);
		
		//이미지 path가지고오기
		String path = req.getServletContext().getRealPath("upload");
		req.setAttribute("path", path);
		
		//메뉴이미지 불러오기
		PImg pimg = placeService.SelectMenuImg(pno);
		req.setAttribute("pimg", pimg);

		//슬라이드 이미지 불러오기
		List<PImg> pimglist = placeService.SelectPimgSlide(pno);
		req.setAttribute("pimglist", pimglist);
		
		//쿠폰사진 불러오기
		List<Coupon> cimglist = placeService.selectCouponImg(pno);
		req.setAttribute("cimglist", cimglist);
		
		//리뷰리스트 불러오기
		List<Map<String, Object>> review = placeService.getReviewpno(pno);
		req.setAttribute("review", review);
		
		//리뷰 갯수 불러오기
		int cnt = reviewService.replyCountComment(pno);
	    System.out.println("count : " + cnt); //테스트
	    req.setAttribute("cnt", cnt); 
		
		//forward
		req.getRequestDispatcher("/WEB-INF/views/place/detail.jsp").forward(req, resp);
		
		}

}
