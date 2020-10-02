package web.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.NTCommentDao;
import web.dao.impl.NTCommentDaoImpl;
import web.dto.BRComment;
import web.dto.NTComment;
import web.dto.Notice;
import web.service.face.NTCommentService;

public class NTCommentServiceImpl implements NTCommentService{
	
	private NTCommentDao ntcommentDao = new NTCommentDaoImpl();
	
	@Override
	public List<Map<String, Object>> getCommentList(Notice showNotice) {
		return ntcommentDao.selecComment(showNotice);
	}
	
	@Override
	public NTComment getComment(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		String noticeno = req.getParameter("noticeno");
		System.out.println("noticeno : " + noticeno);
		String uno = req.getParameter("uno");
		System.out.println("uno : " + uno);
		String nt_con_content = req.getParameter("nt_con_content");
		System.out.println("nt_con_content : " + nt_con_content);
		
		NTComment comment = new NTComment();
		comment.setNoticeno(Integer.parseInt(noticeno));
		comment.setUno(Integer.parseInt(uno));
		comment.setNt_con_content(nt_con_content);

		return comment;
	}
	
	@Override
	public void insertComment(NTComment comment) {
		ntcommentDao.insertComment(comment);		
	}
	
	@Override
	public boolean deleteComment(NTComment comment) {
		ntcommentDao.deleteComment(comment);
		
		if(ntcommentDao.countComment(comment)>0) {
			return false;
		} else {
			return true;
		}
	}

}
