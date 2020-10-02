package web.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Notice;
import web.service.face.NoticeService;
import web.service.impl.NoticeServiceImpl;

/**
 * Servlet implementation class NoticeDeleteController
 */
@WebServlet("/admin/noticedelete")
public class NoticeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NoticeService noticeService = new NoticeServiceImpl();

	@Override
	   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {  
		System.out.println("/admin/delete [get]");
	      
	      Notice notice = noticeService.getNoticeNo(req);
	      System.out.println("delete noticeno : " + notice);
	      
	      noticeService.DeleteNotice(notice);
	      
	      
	      resp.sendRedirect("/admin/noticelist");
	   }else {
			resp.sendRedirect("/login");
			
		}
			
		
	}
}
