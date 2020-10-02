package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import web.DBUtil.JDBCTemplate;
import web.dao.face.BusinessDao;
import web.dto.Business;
import web.dto.PImg;
import web.dto.Place;
import web.util.Paging;

public class BusinessDaoImpl implements BusinessDao {
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public List<Map<String, Object>> SelectBusinessAll(Paging paging) {
		// DB연결
		conn = JDBCTemplate.getConnection();

		String sql="";
		sql += "SELECT * FROM(";
		sql += " SELECT ROWNUM rnum, R.* FROM(";
		sql += " 	SELECT b.bno,b.name,b.id, p.pname, b.phone";
		sql += " 		FROM place p join business b on p.pno = b.pno ";
		sql += "		WHERE name LIKE '%'||?||'%'"; 
		sql +=  " ORDER BY bno DESC )";
		sql += " 	R)";
		sql += " business WHERE rnum BETWEEN ? AND ?";
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, paging.getSearch());
			ps.setInt(2, paging.getStartNo()); 
			ps.setInt(3, paging.getEndNo());

			 rs = ps.executeQuery();
			 
			while (rs.next()) {
				Place place = new Place(); //결과값 저장 객체
				Business business = new Business();
				
				business.setBno(rs.getInt("bno"));
				business.setName(rs.getString("name"));
				business.setId(rs.getString("id"));
				business.setPhone(rs.getString("phone"));
		           
				place.setPname(rs.getString("pname"));
				
				Map<String, Object> m = new HashMap<>();
				m.put("business", business);
				m.put("place", place);
				
				list.add(m);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
	//여기부붠!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	   @Override
	   public Map<String, Object> selectBusinessnoByBno(Business businessno) {
	      conn =JDBCTemplate.getConnection();//DB연결

	      //SQL 작성
	      String sql = "";
	      sql += "SELECT B.*,  P.pname, PI.storedname" ;
	      sql += " FROM place P";
	      sql += " join p_img PI";
	      sql += " on p.pno = PI.pno";
	      sql += " join business B";
	      sql += "  ON P.pno = B.pno";
	      sql += "   where b.bno = ? and pi.pcode=3";

	      Map<String, Object> map = new HashMap<String, Object>();

	      //결과 저장할 Board객체
	      try {
	         //SQL 수행 객체
	         ps=conn.prepareStatement(sql);
	         //조죄할 게스글 번호 적용
	         ps.setInt(1, businessno.getBno()); 
	         //SQL
	         rs= ps.executeQuery();

	         Place placeview = new Place();
	         Business businessview = new Business();
	         PImg pimgview = new PImg();

	         while(rs.next()) {
	            businessview.setBno(rs.getInt("bno"));
	            businessview.setPno(rs.getInt("pno"));
	            businessview.setId(rs.getString("id"));
	            businessview.setName( rs.getString("name"));
	            businessview.setBsnum(rs.getString("bsnum"));
	            businessview.setPhone(rs.getString("phone"));
	            businessview.setBirth(rs.getString("birth"));
	            businessview.setGender(rs.getString("gender"));
	            businessview.setMail(rs.getString("mail"));

	            placeview.setPname(rs.getString("pname"));

	            pimgview.setStoredName(rs.getString("storedName"));

	            map.put("place",placeview);
	            map.put("business",businessview);
	            map.put("pimg", pimgview);

	         }
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	         try {
	            if(rs != null)   rs.close();
	            if(ps != null)   ps.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return map;
	   }

	@Override
	public int selectCntAll() {
		conn = JDBCTemplate.getConnection();
		String sql="";
		sql +="SELECT count(*) FROM business";
		
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();// SQL 
			
			//조회결과 처리
			while (rs.next()) {
				cnt = rs.getInt(1);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {//
		
			try {
				if(rs != null)	rs.close();
				if(ps != null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	@Override
	public int selectCntAll(String search) {
		conn = JDBCTemplate.getConnection();
		//수행할 SQL
				String sql = "";
				sql += "SELECT ";
				sql += "	count(*)";
				sql += " FROM business";
				sql += " WHERE name LIKE '%'||?||'%'";

		
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();// SQL 
			
			//조회결과 처리
			while (rs.next()) {
				cnt = rs.getInt(1);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {//
			try {
				if(rs != null)	rs.close();
				if(ps != null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}


}
