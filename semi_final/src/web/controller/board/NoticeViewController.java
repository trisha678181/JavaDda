package web.controller.board;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.service.face.NoticeService;
import web.service.impl.NoticeServiceImpl;
import web.util.Paging;

/**
 * notice 목록 servlet
 */
@WebServlet("/notice/view")
public class NoticeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NoticeService noticeService = new NoticeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/notice/view [get]");
		
		//요청파라미터를 전달하여 Paging 객체 생성
		Paging paging = noticeService.getPaging(req);

		// 공지사항리스트 페이징 처리 조회
		List<Map<String, Object>> noticeList = noticeService.getList(paging);

		//페이징계산결화 MODEL값 전달
		req.setAttribute("paging", paging);

		//조회결과 MODEL값 전달
		req.setAttribute("noticeList", noticeList);
//		System.out.println("noticeList : "+ noticeList);

		
		req.getRequestDispatcher("/WEB-INF/views/board/noticeView.jsp").forward(req, resp);
	}
}