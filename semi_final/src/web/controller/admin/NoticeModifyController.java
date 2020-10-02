package web.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dao.face.NoticeDao;
import web.dao.impl.NoticeDaoImpl;
import web.dto.Business;
import web.dto.Notice;
import web.service.face.NoticeService;
import web.service.impl.NoticeServiceImpl;

/**
 * Servlet implementation class NoticeModifyController
 */
@WebServlet("/admin/noticemodify")
public class NoticeModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private  NoticeService noticeService = new NoticeServiceImpl();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {  
			System.out.println("/notice/update [get]");

			//게시글 번호 파싱
			Notice noticeno = noticeService.getNoticeNo(req);

			//게시글 조회
			noticeno = noticeService.show(noticeno);


			//MODEL로 게시글 전달
			req.setAttribute("list", noticeno);


			//VIEW 지정
			req.getRequestDispatcher("/WEB-INF/views/admin/noticeUpdate.jsp").forward(req, resp);
		}else {
			resp.sendRedirect("/login");

		}


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {
			req.setCharacterEncoding("utf-8");
			noticeService.UpdateNoticeNo(req);

			resp.sendRedirect("/admin/noticelist");
		}else {
			resp.sendRedirect("/login");

		}
	}
}
