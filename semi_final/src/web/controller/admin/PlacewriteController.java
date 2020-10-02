package web.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.NoticeService;
import web.service.face.PlaceService;
import web.service.impl.NoticeServiceImpl;
import web.service.impl.PlaceServiceImpl;

/**
 * Servlet implementation class NoticewriteController
 */
@WebServlet("/admin/placewrite")
public class PlacewriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	//NoticeService 객체	
	private PlaceService placeService = new PlaceServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//로그인 되어있지 않으면 리다이렉트 
		//		if( req.getSession().getAttribute("login") == null ) {
		//			
		//			resp.sendRedirect("/");
		//			
		//			return;
		//		}
				
				//VIEW 지정
				req.getRequestDispatcher("/WEB-INF/views/admin/placeWrite.jsp")
					.forward(req, resp);
				
			}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("cnffur");
		
		//작성글 삽입
		placeService.write(req);
	
		//목록으로 리다이렉션
		resp.sendRedirect("/admin/placelist");
		
		
	}
}
