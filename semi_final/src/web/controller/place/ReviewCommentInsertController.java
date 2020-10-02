package web.controller.place;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.RVComment;
import web.service.face.ReviewService;
import web.service.impl.ReviewServiceImpl;

@WebServlet("/review/comment")
public class ReviewCommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReviewService reviewService = new ReviewServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RVComment rvcomment = reviewService.getRVComment(req);
		System.out.println(rvcomment);
		reviewService.insertRVComment(rvcomment);
		
		
		resp.sendRedirect("/place/reviewplus?pno="+req.getParameter("pno"));
	}

}
