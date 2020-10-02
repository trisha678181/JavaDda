package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import web.dao.face.BRImgDao;
import web.dto.BRImg;

public class BRImgDaoImpl implements BRImgDao {
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public void InsertBoardImg(BRImg brImg) {
		// TODO Auto-generated method stub
		
	}

}
