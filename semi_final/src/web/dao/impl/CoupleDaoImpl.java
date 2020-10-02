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
import web.dao.face.CoupleDao;
import web.dto.Anniversary;
import web.dto.LikePlace;
import web.dto.PImg;
import web.dto.Place;
import web.dto.User;

public class CoupleDaoImpl implements CoupleDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public String[] SelectCoupleBirth() {

		String[] births = new String[2];

		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "select usertb.ubirth B1, T.ubirth B2 from usertb join (select usertb.ubirth, couple.mno from usertb join couple on usertb.uno = couple.wno where usertb.uno = 1) T on usertb.uno = T.mno";

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				births[1] = rs.getString("B1"); // 상대방 생일
				births[0] = rs.getString("B2"); // 내 생일
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return births;
	}

	@Override
	public String SelectCoupleFirstDay(User user) {

		String firstday = "";

		conn = JDBCTemplate.getConnection();

		String sql = "";

		if (user.getGender().equals("f")) {
			sql = "select couple.firstday from usertb join couple on usertb.uno = couple.wno where usertb.uno = ?";
		} else if (user.getGender().equals("m")) {
			sql = "select couple.firstday from usertb join couple on usertb.uno = couple.mno where usertb.uno = ?";
		}

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getUno());

			rs = ps.executeQuery();

			while (rs.next()) {
				firstday = rs.getString("firstday");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return firstday;
	}

	@Override
	public String[] SelectCoupleName(User user) {

		String[] names = new String[2];

		conn = JDBCTemplate.getConnection();

		String sql = "";

		if (user.getGender().equals("f")) {
			sql += "select T.uname N1, usertb.uname N2 from usertb join";
			sql += " (select usertb.uname, couple.mno from usertb join couple on usertb.uno = couple.wno";
			sql += " where usertb.uno = ?) T on usertb.uno = T.mno";
		} else if (user.getGender().equals("m")) {
			sql += "select T.uname N1, usertb.uname N2 from usertb join";
			sql += " (select usertb.uname, couple.wno from usertb join couple on usertb.uno = couple.mno";
			sql += " where usertb.uno = ?) T on usertb.uno = T.wno";
		}

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getUno());

			rs = ps.executeQuery();

			while (rs.next()) {
				names[0] = rs.getString("N1");
				names[1] = rs.getString("N2");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return names;
	}

	@Override
	public void BreakCouple(int uno) {
		// TODO Auto-generated method stub

	}

	@Override
	public void MakeCouple(User user) {
		
		conn = JDBCTemplate.getConnection();

		int res = 0;

		String sql = "insert into couple(cno,wno,mno,firstday) values(";
		sql += " couple_seq.nextval";
		if (user.getGender().equals("f")) {
			sql += ", ?";
			sql += ", (select mno from couplevalid where wno = ?)";
			sql += ", (select firstday from couplevalid where wno = ?))";
		} else if (user.getGender().equals("m")) {
			sql += ", (select wno from couplevalid where mno = ?)";
			sql += ", ?";
			sql += ", (select firstday from couplevalid where mno = ?))";
		}
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getUno());
			ps.setInt(2, user.getUno());
			ps.setInt(3, user.getUno());

			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

	}

	@Override
	public List<Anniversary> GetAnni(int uno) {
		return null;
	}

	@Override
	public int AreYouCouple(User user) {

		conn = JDBCTemplate.getConnection();

		int res = 0;

		String sql = "";
		
		System.out.println("areyoucoupleDAO : " + user.getUno());

		if (user.getGender().equals("f")) {
			sql = "select count(*) from couple where wno=?";
		} else if (user.getGender().equals("m")) {
			sql = "select count(*) from couple where mno=?";
		}

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getUno());

			rs = ps.executeQuery();

			while (rs.next()) {
				res = rs.getInt(1);
			}
			
			System.out.println("resDAO : " + res);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return res;

	}

	@Override
	public void setValidNum(String u, List<User> list) {

		conn = JDBCTemplate.getConnection();

		String sql = "";

		if (list.get(0).getGender().equals("f")) {
			sql = "INSERT INTO couplevalid(wno,mno,couplevalid,requiredno) VALUES (?, ?, ?, ?)";
		} else if (list.get(0).getGender().equals("m")) {
			sql = "INSERT INTO couplevalid(mno,wno,couplevalid,requiredno) VALUES (?, ?, ?, ?)";
		}

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, list.get(0).getUno());
			ps.setInt(2, list.get(1).getUno());
			ps.setString(3, u);
			ps.setInt(4, list.get(1).getUno());
			
			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
	}
	
	@Override
	public void MakeCoupleFirstday(String firstday, User user) {

		conn = JDBCTemplate.getConnection();
		
		String sql = "";
		
		if (user.getGender().equals("f")) {
			sql = "update couplevalid set firstday=? where wno=?";
		} else if (user.getGender().equals("m")) {
			sql = "update couplevalid set firstday=? where mno=?";
		}

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, firstday);
			ps.setInt(2, user.getUno());

			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			JDBCTemplate.commit(conn);
		}
		
	}
	
	@Override
	public int RequiredCouple(int uno) {

		conn = JDBCTemplate.getConnection();
		
		String sql = "select count(*) from couplevalid where requiredno = ?";
		
		int res = 0;

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, uno);

			rs = ps.executeQuery();

			while (rs.next()) {
				res = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			JDBCTemplate.commit(conn);
		}
		return res;
	}
	
	@Override
	public void DeleteCoupleValid(String couplevalid) {
		
		conn = JDBCTemplate.getConnection();
		
		String sql = "delete from couplevalid where couplevalid=?";
		

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, couplevalid);

			ps.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			JDBCTemplate.commit(conn);
		}
		
	}
	
	@Override
	public void DeleteLikePlaceTogether(int pno, User user) {
		conn = JDBCTemplate.getConnection();

		String sql = "delete from like_place where pno =? and uno=?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, pno);
			ps.setInt(2, user.getUno());

			rs = ps.executeQuery();


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			JDBCTemplate.commit(conn);
		}
	}

	@Override
	public List<Map<String, Object>> GetLikePlaceList(User user) {

		conn = JDBCTemplate.getConnection();

		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		String sql = "select T.uno, T.pname, T.pno, T.together, p_img.storedname from (select like_place.uno, place.pno, place.pname, like_place.together";
		sql += " from like_place join place on like_place.pno = place.pno where like_place.uno = ?";
		
		if (user.getGender().equals("f")) {
			sql += " or like_place.uno = (select mno from couple where wno=?))T";
		} else {
			sql += " or like_place.uno = (select wno from couple where mno=?))T";
		}
		
		sql += " join p_img on T.pno = p_img.pno where p_img.pcode=3";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getUno());
			ps.setInt(2, user.getUno());

			rs = ps.executeQuery();

			while (rs.next()) {
				
				Map<String, Object> map = new HashMap<String, Object>();

				LikePlace likeplace = new LikePlace();
				PImg pimg = new PImg();
				Place place = new Place();

				likeplace.setUno(rs.getInt("uno"));
				likeplace.setTogether(rs.getString("together"));
				pimg.setStoredName(rs.getString("storedname"));
				place.setPname(rs.getString("pname"));
				place.setPno(rs.getInt("pno"));
				
				map.put("likeplace", likeplace);
				map.put("pimg", pimg);
				map.put("place", place);
				
				list.add(map);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			JDBCTemplate.commit(conn);
		}
		return list;
	}
	
	@Override
	public List<Integer> GetLikePlaceListNo(User user) {

		conn = JDBCTemplate.getConnection();

		List<Integer> list = new ArrayList<Integer>();
		
		String sql = "select * from like_place where uno = ?";
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getUno());

			rs = ps.executeQuery();

			while (rs.next()) {
				
				list.add(rs.getInt("pno"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			JDBCTemplate.commit(conn);
		}
		return list;
	}

	@Override
	public List<Integer> GetTop3ListNo() {

		conn = JDBCTemplate.getConnection();

		List<Integer> list = new ArrayList<Integer>();
		
		String sql = "select * from (select place.pno from place join p_img on place.pno = p_img.pno where place.pcode=1 order by plike desc)";
		sql += " where rownum <= 3";
		sql += " UNION";
		sql += " select * from (select place.pno from place join p_img on place.pno = p_img.pno where place.pcode=4 or place.pcode=5 order by plike desc)";
		sql += " where rownum <= 3";
		
		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				list.add(rs.getInt("pno"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			JDBCTemplate.commit(conn);
		}
		return list;
	}
	
	@Override
	public List<Integer> GetLikePlace(User user) {
		conn = JDBCTemplate.getConnection();

		List<Integer> list = new ArrayList<>();

		String sql = "";

		if (user.getGender().equals("f")) {
			sql += "select * from like_place where uno = ? or uno = (select mno from couple where wno=?) order by pno";
		} else {
			sql += "select * from like_place where uno = ? or uno = (select wno from couple where mno=?) order by pno";
		}

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getUno());
			ps.setInt(2, user.getUno());

			rs = ps.executeQuery();

			while (rs.next()) {
				
				list.add(rs.getInt("pno"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			JDBCTemplate.commit(conn);
		}
		return list;
	}
	
	@Override
	public void SetLikePlaceTogether(int pno) {
		conn = JDBCTemplate.getConnection();

		String sql = "update like_place set together='O' where pno=?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, pno);

			rs = ps.executeQuery();


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			JDBCTemplate.commit(conn);
		}
		
	}
	

	@Override
	public List<Anniversary> GetAnni(User user) {
		conn = JDBCTemplate.getConnection();

		List<Anniversary> list = new ArrayList<Anniversary>();

		String sql = "";

		sql = "SELECT * FROM anniversary where cno = (select couple.cno from couple join usertb on ";
		
		if (user.getGender().equals("f")) {
			sql += " couple.wno = usertb.uno where usertb.uno = ?)";
		} else if (user.getGender().equals("m")) {
			sql += " couple.mno = usertb.uno where usertb.uno = ?)";
		}
		
		sql += "order by av_date";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getUno());

			rs = ps.executeQuery();

			while (rs.next()) {

				Anniversary anni = new Anniversary();
				
				anni.setTitle(rs.getString("title"));
				anni.setAv_date(rs.getString("av_date"));
				
				list.add(anni);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			JDBCTemplate.commit(conn);
		}
		return list;
	}
	
	@Override
	public void ExitCouple(User user) {
		
		System.out.println("ExitCouple");
		
		conn = JDBCTemplate.getConnection();

		String sql = "";

		if (user.getGender().equals("f")) {
			sql += "delete from couple where cno=(select couple.cno from couple join usertb on couple.wno = usertb.uno where usertb.uno = ?)";
		} else if (user.getGender().equals("m")) {
			sql += "delete from couple where cno=(select couple.cno from couple join usertb on couple.mno = usertb.uno where usertb.uno = ?)";
		}

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getUno());

			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			JDBCTemplate.commit(conn);
		}
		
	}
	

	@Override
	public int AddAnni(String annititle, String annidate, User user) {
		
		System.out.println("AddAnni");
		
		conn = JDBCTemplate.getConnection();

		String sql = "";
		
		int res = 0;

		sql = "insert into anniversary values(";
		
		if (user.getGender().equals("f")) {
			sql += " (select couple.cno from couple join usertb on couple.wno = usertb.uno where usertb.uno = ?),";
		} else if (user.getGender().equals("m")) {
			sql += " (select couple.cno from couple join usertb on couple.mno = usertb.uno where usertb.uno = ?),";
		}
		
		sql += " ?, ?, 0)";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getUno());
			ps.setString(2, annititle);
			ps.setString(3, annidate);

			res = ps.executeUpdate();
			
			System.out.println("AddAnni res" + res);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			JDBCTemplate.commit(conn);
		}
		
		return res;
		
	}
	
	@Override
	public int EditAnni(String annititle, String annidate, String old_annititle, User user) {
		System.out.println("AddAnni");
		
		conn = JDBCTemplate.getConnection();

		String sql = "";
		
		int res = 0;

		sql = "update anniversary set av_date=?, title=? where";
		
		if (user.getGender().equals("f")) {
			sql += " cno = (select couple.cno from couple join usertb on couple.wno = usertb.uno where usertb.uno = ?)";
		} else if (user.getGender().equals("m")) {
			sql += " cno = (select couple.cno from couple join usertb on couple.mno = usertb.uno where usertb.uno = ?)";
		}
		
		sql += " and title=?";
					

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, annidate);
			ps.setString(2, annititle);
			ps.setInt(3, user.getUno());
			ps.setString(4, old_annititle);

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			JDBCTemplate.commit(conn);
		}
		
		return res;
	}
	
	@Override
	public int DeleteAnni(String annititle, String annidate, User user) {
		
		conn = JDBCTemplate.getConnection();

		String sql = "";
		
		int res = 0;

		sql = "delete from anniversary where title=? and ";
		
		if (user.getGender().equals("f")) {
			sql += " cno=(select couple.cno from couple join usertb on couple.wno = usertb.uno where usertb.uno = ?)";
		} else if (user.getGender().equals("m")) {
			sql += " cno=(select couple.cno from couple join usertb on couple.mno = usertb.uno where usertb.uno = 1)";
		}

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, annititle);
			ps.setInt(2, user.getUno());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			JDBCTemplate.commit(conn);
		}
		
		return res;
		
	}
	

	@Override
	public void DeleteLikePlace(Place place, User user) {
		
		conn = JDBCTemplate.getConnection();
		
		System.out.println("DeleteLikePlace" + place.getPno() + " " + user.getUno());

		String sql = "delete from like_place where pno =? and uno=?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, place.getPno());
			ps.setInt(2, user.getUno());

			ps.executeQuery();


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			JDBCTemplate.commit(conn);
		}
	}

}
