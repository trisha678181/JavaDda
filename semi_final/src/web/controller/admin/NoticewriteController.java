package web.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.NoticeService;
import web.service.impl.NoticeServiceImpl;

/**
 * Servlet implementation class NoticewriteController
 */
@WebServlet("/admin/noticewrite")
public class NoticewriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//NoticeService 객체	
	private NoticeService noticeService = new NoticeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {
			//로그인 되어있지 않으면 리다이렉트 
			//		if( req.getSession().getAttribute("login") == null ) {
			//			
			//			resp.sendRedirect("/");
			//			
			//			return;
			//		}

			//VIEW 지정
			req.getRequestDispatcher("/WEB-INF/views/admin/noticeWrite.jsp")
			.forward(req, resp);

		}else {
			resp.sendRedirect("/login");

		}


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {
			System.out.println("cnffur");

			//작성글 삽입
			noticeService.write(req);

			//목록으로 리다이렉션
			resp.sendRedirect("/admin/noticelist");
		}else {
			resp.sendRedirect("/login");

		}


	}
}
