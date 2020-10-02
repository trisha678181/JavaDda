package web.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.BRCommentDao;
import web.dao.impl.BRCommentDaoImpl;
import web.dto.BRComment;
import web.dto.Board;
import web.service.face.BRCommentService;

public class BRCommentServiceImpl implements BRCommentService{
	
	private BRCommentDao brcommentDao = new BRCommentDaoImpl();
	
	@Override
	public List<Map<String, Object>> getCommentList(Board showBoard) {
		return brcommentDao.selecComment(showBoard);
	}
	
	@Override
	public BRComment getComment(HttpServletRequest req) {
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		String brno = req.getParameter("brno");
//		System.out.println("brno : " + brno);
		String uno = req.getParameter("uno");
//		System.out.println("uno : " + uno);
		String brcontent = req.getParameter("brcontent");
//		System.out.println("brcontent : " + brcontent);
		
		BRComment comment = new BRComment();
		comment.setBrno(Integer.parseInt(brno));
		comment.setUno(Integer.parseInt(uno));
		comment.setBrcontent(brcontent);
		
		return comment;
	}
	
	@Override
	public void insertComment(BRComment comment) {
		brcommentDao.insertComment(comment);
	}
	
	@Override
	public boolean deleteComment(BRComment comment) {
		brcommentDao.deleteComment(comment);
		
		if( brcommentDao.countComment(comment) > 0 ) {
			return false;
		} else {
			return true;
		}
		
	}
	
	@Override
	public int replyCountComment(Board showBoard) {
		return brcommentDao.replyCount(showBoard);
	}

}










































