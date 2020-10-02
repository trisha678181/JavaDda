package web.controller.place;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Report;
import web.service.face.PlaceService;
import web.service.impl.PlaceServiceImpl;


@WebServlet("/place/report")
public class ReportInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	PlaceService placeService = new PlaceServiceImpl(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//클릭시 로그인 확인
		if( req.getSession().getAttribute("userid")  == null) {

			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=UTF-8");

			PrintWriter out = resp.getWriter();

			out.println("<script>");
			out.println("alert('로그인이 필요한 서비스입니다!');");
			out.println("location.href='/login';");
			out.println("</script>");

			out.close();
			return;
		}
				
		//pno받아오기
		Report report = placeService.getReportPno(1);
		
		//쿠폰 적용하기
		req.setAttribute("report", report);
	}

}
