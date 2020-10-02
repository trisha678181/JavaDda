package web.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.BusinessDao;
import web.dao.impl.BusinessDaoImpl;
import web.dto.Business;
import web.service.face.BusinessService;
import web.util.Paging;

public class BusinessServiceImpl implements BusinessService {

	private BusinessDao businessDao = new BusinessDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		String  param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && ! "".equals(param)) {
			curPage = Integer.parseInt(param);
		}
		
		  String search = (String)req.getParameter("search");
		
		//Board테이블의 총 게시글 수를 조회한다
		int totalCount = businessDao.selectCntAll();

		//Paging 객체 생성 - 현재 페이지 ,총 게시글  활용
		Paging paging = new Paging(totalCount, curPage);

		//검색어
		 paging.setSearch(search);
		 
	
		return paging;
	}

	@Override
	public List<Map<String, Object>> getList(Paging paging) {
		
		return businessDao.SelectBusinessAll(paging);
	}

	@Override
	public Business getBusinessNo(HttpServletRequest req) {
		//businessno를 저장할 객체 생성
		Business business = new Business();

		//businessno 전달파라미터 검증 - null ,""
		String param = req.getParameter("bno");
		if(param !=null && !"".equals(param)) { 

			business.setBno(Integer.parseInt(param));
		}
		//결과 객체 반환
		return business;
	}

	@Override
	public Map<String, Object> show(Business businessno) {
	    //게시글 조회
	     return businessDao.selectBusinessnoByBno(businessno);

	}

	
	

}
