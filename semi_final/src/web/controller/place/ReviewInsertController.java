package web.controller.place;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import web.dto.Review;
import web.service.face.ReviewService;
import web.service.impl.ReviewServiceImpl;


@WebServlet("/review/insert")
public class ReviewInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[POST]");
		
//		Review review = reviewService.getReviewWrite(req);
//		
//		reviewService.insertReview(review); 
//		System.out.println(review);
		
		int pno = reviewService.getReviewFile(req); //리뷰파일 설정
		resp.sendRedirect("/place/reviewplus?pno="+pno);
	
	
	}

}
