package web.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.AdminDao;
import web.dao.impl.AdminDaoImpl;
import web.dto.User;
import web.service.face.AdminService;
import web.util.Paging;

public class AdminServiceImpl implements AdminService {

	private AdminDao adminDao = new AdminDaoImpl();

	@Override
	public List<User> getList(Paging paging) {
		
		return adminDao.SelectUserAll(paging);
	}
	@Override
	public Paging getPaging(HttpServletRequest req) {
		String  param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && ! "".equals(param)) {
			curPage = Integer.parseInt(param);
		}
		//검색어
		String search = (String)req.getParameter("search");
		
		//Board테이블의 총 게시글 수를 조회한다
		int totalCount = adminDao.selectCntAll();

		//Paging 객체 생성 - 현재 페이지 ,총 게시글  활용
		Paging paging = new Paging(totalCount, curPage);
		//검색어
		 paging.setSearch(search);
	
		return paging;
	}

}
