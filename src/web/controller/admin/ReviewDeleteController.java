package web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.QnA;
import web.dto.Review;
import web.service.face.QnAService;
import web.service.face.ReviewService;
import web.service.impl.QnAServiceImpl;
import web.service.impl.ReviewServiceImpl;

/**
 * Servlet implementation class NoticeDeleteController
 */
@WebServlet("/admin/reviewdelete")
public class ReviewDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ReviewService reviewService = new ReviewServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {
			System.out.println("/admin/delete [get]");

			Review review = reviewService.getReviewno(req);


			reviewService.DeleteReview(review);


			resp.sendRedirect("/admin/reviewlist");
		}else {
			resp.sendRedirect("/login");

		}


	}
}
