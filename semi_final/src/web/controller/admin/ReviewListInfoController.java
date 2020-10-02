package web.controller.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Business;
import web.dto.Review;
import web.service.face.ReviewService;
import web.service.impl.ReviewServiceImpl;

/**
 * Servlet implementation class ReviewListInfoController
 */
@WebServlet("/admin/reviewlistinfo")
public class ReviewListInfoController extends HttpServlet {

	private ReviewService reviewService = new ReviewServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {	
			System.out.println("/admin/Reviewlistinfo [get]");

			Review review = reviewService.getReviewno(req);
			System.out.println("review controller : " + review);
			
			

			// 여기 바꾸기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
			Map<String, Object> list = reviewService.show(review);


			req.setAttribute("list", list);
			
			String path = req.getServletContext().getRealPath("upload");
		       req.setAttribute("path", path);
			req.getRequestDispatcher("/WEB-INF/views/admin/ReviewShow.jsp").forward(req, resp);
		}
		else {
			resp.sendRedirect("/login");

		}
	}

}
