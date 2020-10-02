package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.DBUtil.JDBCTemplate;
import web.dao.face.AdminDao;
import web.dto.User;
import web.util.Paging;

public class AdminDaoImpl implements AdminDao {
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public List<User> SelectUserAll(Paging paging) {
		// DB연결
		conn = JDBCTemplate.getConnection();

		String sql="";
		sql += "SELECT * from(";
		sql += "	SELECT ROWNUM rnum,U.*From( ";
		sql += "		select  uno,uname,userid, uphone,unick";
		sql += " 			 FROM USERTB";
		sql += "		WHERE uname LIKE '%'||?||'%'"; 
		sql += " 		ORDER BY uno DESC";
		sql += "	) U";
		sql += " )USERTB";
		sql += " where rnum between ? and ?";
		
		List<User> list = new ArrayList<>();
		
		try {
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, paging.getSearch());
			ps.setInt(2, paging.getStartNo()); 
			ps.setInt(3, paging.getEndNo());

			 rs = ps.executeQuery();
			 
			while (rs.next()) {
				
				User user = new User();
				
				user.setUno(rs.getInt("uno"));
				user.setId(rs.getString("userid"));
				user.setName(rs.getString("uname"));
				user.setPhone(rs.getString("uphone"));
				user.setNick(rs.getString("unick"));

				list.add(user);

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

	
	@Override
	public int selectCntAll() {
		conn = JDBCTemplate.getConnection();
		String sql="";
		sql +="SELECT count(*) FROM usertb";
		
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
				sql += " FROM usertb";
				sql += " WHERE title LIKE '%'||?||'%'";

		
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
