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
 * Servlet implementation class NoticeListInfoController
 */
@WebServlet("/admin/noticelistinfo")
public class NoticeListInfoController extends HttpServlet {

	private NoticeService noticeService = new NoticeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {
			System.out.println("/admin/noticelistinfo [get]");

			Notice noticeno = noticeService.getNoticeNo(req);

			System.out.println("noticenno : " + noticeno);

			//상세보기 결과 조회 
			Notice viewNotice = noticeService.show(noticeno);

			req.setAttribute("list", viewNotice);
			req.getRequestDispatcher("/WEB-INF/views/admin/noticeShow.jsp").forward(req, resp);
		}else {
			resp.sendRedirect("/login");

		}


	}


}
