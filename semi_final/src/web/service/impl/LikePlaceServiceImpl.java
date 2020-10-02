package web.service.impl;

import web.dao.face.LikePlaceDao;
import web.dao.impl.LikePlaceDaoImpl;
import web.service.face.LikePlaceService;

public class LikePlaceServiceImpl implements LikePlaceService {
	
	private LikePlaceDao likePlaceDao = new LikePlaceDaoImpl();
	
	@Override
	public int SelectLikePlacePno(int Pno) {
		// TODO Auto-generated method stub
		return 0;
	}

}
