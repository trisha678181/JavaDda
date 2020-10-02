package web.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.QnA;
import web.service.face.QnAService;
import web.service.impl.QnAServiceImpl;

/**
 * QnA 수정 서블릿
 */
@WebServlet("/qa/update")
public class QaUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private QnAService qnaService = new QnAServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/qa/update [get]");
		
		//로그인한 사람의 글이 아니면 중단하고 목록으로 리다이렉트
//		if(!qnaService.checkId(req)) {
//			resp.sendRedirect("/qa/view");
//			
//			return;
//		}
		
		//게시글 번호 파싱
		QnA showQna = qnaService.getQnANo(req);
		
		//게시글 조회
		showQna = qnaService.show(showQna);
		
		req.setAttribute("name", qnaService.getName(showQna));
		
		//MODEL로 게시글 전달
		req.setAttribute("showQna", showQna);
		
		System.out.println("잘들어옴");
		
		req.getRequestDispatcher("/WEB-INF/views/board/qaUpdate.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/qa/update [post]");
		
		req.setCharacterEncoding("utf-8");
		
		
		
		qnaService.UpdateQnA(req);
		
		resp.sendRedirect("/qa/view");
	}
}














