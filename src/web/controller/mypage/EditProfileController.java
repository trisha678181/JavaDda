package web.controller.mypage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import web.dto.User;
import web.service.face.UserService;
import web.service.impl.UserServiceImpl;

/**
 * 마이페이지 회원정보수정 페이지 servlet
 */
@WebServlet("/mypage/editprofile")
public class EditProfileController extends HttpServlet {
	
	private UserService userService = new UserServiceImpl();
	
	User user = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		int uno = (int)session.getAttribute("uno");
		
		User user = userService.GetUserInfo(uno);
		
		req.setAttribute("user", user);
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/editprofile.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		userService.SetUserInfo(req);
		
	 	resp.sendRedirect("/mypage/main");
		
	}
	
}
