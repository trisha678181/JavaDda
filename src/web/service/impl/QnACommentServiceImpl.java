package web.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.NTCommentDao;
import web.dao.face.QnACommentDao;
import web.dao.impl.NTCommentDaoImpl;
import web.dao.impl.QnACommentDaoImpl;
import web.dto.BRComment;
import web.dto.NTComment;
import web.dto.Notice;
import web.dto.QnA;
import web.dto.QnAComment;
import web.service.face.NTCommentService;
import web.service.face.QnACommentService;

public class QnACommentServiceImpl implements QnACommentService{
	
	private QnACommentDao qnacommentDao = new QnACommentDaoImpl();
	
	@Override
	public List<Map<String, Object>> getCommentList(QnA showQnA) {
		return qnacommentDao.selecComment(showQnA);
	}
	
	@Override
	public QnAComment getComment(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		String qnano = req.getParameter("qnano");
		System.out.println("qnano : " + qnano);
		String ano = req.getParameter("ano");
		System.out.println("ano : " + ano);
		String qna_con_content = req.getParameter("qna_con_content");
		System.out.println("qna_con_content : " + qna_con_content);
		
		QnAComment comment = new QnAComment();
		
		comment.setQnano(Integer.parseInt(qnano));
		comment.setAno(Integer.parseInt(ano));
		comment.setQna_con_content(qna_con_content);

		return comment;
	}
	
	@Override
	public void insertComment(QnAComment comment) {
		qnacommentDao.insertComment(comment);		
	}
	
	@Override
	public boolean deleteComment(QnAComment comment) {
		qnacommentDao.deleteComment(comment);
		
		if(qnacommentDao.countComment(comment)>0) {
			return false;
		} else {
			return true;
		}
	}

}
