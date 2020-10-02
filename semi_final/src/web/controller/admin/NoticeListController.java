package web.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Notice;
import web.service.face.NoticeService;
import web.service.impl.NoticeServiceImpl;
import web.util.Paging;

/**
 * Servlet implementation class NoticeListController
 */
@WebServlet("/admin/noticelist")
public class NoticeListController extends HttpServlet {

	private NoticeService noticeService = new NoticeServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("NoticeListController GET");
		
		if(req.getSession().getAttribute("admin") !=null) {	
			// 요청 파라미터를 전달하여 Paging 객체 생성하기
			Paging paging = noticeService.getPaging(req);

			// 공지사항리스트 페이징 처리 조회
			List<Notice> list = noticeService.getNoticeList(paging);
			
			//페이징계산결화 MODEL값 전달
			req.setAttribute("paging", paging);

			//조회결과 MODEL값 전달
			req.setAttribute("list", list);

			//VIEW
			req.getRequestDispatcher("/WEB-INF/views/admin/noticeView.jsp").forward(req, resp);

		}else {
			resp.sendRedirect("/login");

		}


	}

}
