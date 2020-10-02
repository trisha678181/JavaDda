package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import web.dao.face.LocationDao;
import web.dto.Location;

public class LocationDaoImpl implements LocationDao{
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public Location GetLocationInfo(int Pno) {
		
		Location loc = new Location();
		// TODO Auto-generated method stub
		return null;
	}

}
