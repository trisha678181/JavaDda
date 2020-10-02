package web.controller.place;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.RVComment;
import web.dto.Review;
import web.service.face.PlaceService;
import web.service.face.ReviewService;
import web.service.impl.PlaceServiceImpl;
import web.service.impl.ReviewServiceImpl;
import web.util.ReviewPaging;


@WebServlet("/place/reviewplus")
public class ReviewListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PlaceService placeService = new PlaceServiceImpl();
	ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청 pno받아서 가지고오기
		Review pno = reviewService.getPlacepno(req);
		System.out.println("pno:" +pno);
		
		//플레이스 리스트
		List<Map<String, Object>> placelist = reviewService.getPlaceNo(pno);
		req.setAttribute("place", placelist);
		
		//페이징처리하기
		ReviewPaging paging = reviewService.getrvPaging(req); 		
		req.setAttribute("paging", paging); //페이징 결과
//		System.out.println("ReviewplusListController - " + paging); //테스트
				
		//리뷰 리스트 가지고오기 
		List<Map<String, Object>> reviewlist = reviewService.getrvList(paging, pno);
		req.setAttribute("reviewlist", reviewlist);  //조회값 전달
		
//		for(int i=0; i<reviewlist.size(); i++) { //테스트
//			System.out.println(reviewlist.get(i));
//		}
		
		//댓글 리스트
		RVComment rvcomment = new RVComment();
		
		List<Map<String,Object>> rvcommentList = reviewService.getCommentList(pno);
		req.setAttribute("rvcommentList", rvcommentList);
		
		System.out.println("ReviewplusListController -" +rvcommentList);
		
		//리뷰 갯수 불러오기
		int cnt = reviewService.replyCountComment(pno);
		System.out.println("count : " + cnt); //테스트
		req.setAttribute("cnt", cnt); 
		
		
		req.getRequestDispatcher("/WEB-INF/views/place/reviewplus.jsp").forward(req, resp);
		
	}

}
