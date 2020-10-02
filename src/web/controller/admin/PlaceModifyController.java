package web.controller.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import web.dto.Place;
import web.service.face.PlaceService;
import web.service.impl.PlaceServiceImpl;

/**
 * Servlet implementation class NoticeModifyController
 */
@WebServlet("/admin/placemodify")
public class PlaceModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private  PlaceService placeService = new PlaceServiceImpl();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if(req.getSession().getAttribute("admin") !=null) {  
			System.out.println("/admin/update [get]");

			//게시글 번호 파싱
			Place pno = placeService.getPlaceNo(req);

			//게시글 조회
			Map<String, Object> list = placeService.show(pno);

			//MODEL로 게시글 전달
			req.setAttribute("list", list);

			//VIEW 지정
			req.getRequestDispatcher("/WEB-INF/views/admin/placeUpdate.jsp").forward(req, resp);
		}else {
			resp.sendRedirect("/login");

		}


	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("admin") !=null) {
			req.setCharacterEncoding("utf-8");
			placeService.UpdatePlaceNo(req);

			resp.sendRedirect("/admin/placelist");
		}else {
			resp.sendRedirect("/login");

		}
	}
}
