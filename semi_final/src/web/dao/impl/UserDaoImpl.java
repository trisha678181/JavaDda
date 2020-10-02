package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import web.DBUtil.JDBCTemplate;
import web.dao.face.UserDao;
import web.dto.Board;
import web.dto.Couple;
import web.dto.Coupon;
import web.dto.HaveCoupon;
import web.dto.Place;
import web.dto.Review;
import web.dto.User;

public class UserDaoImpl implements UserDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public void UpdateUserOne(User user) {
		
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "UPDATE usertb SET";

		sql += " upw = ?";
		sql += ", unick = ?";
		sql += ", uphone = ?";
		sql += ", umail = ?";
		
		sql += ", ucold = ?";
		sql += ", uhot = ?";
		sql += ", urain = ?";
		sql += ", usnow = ?";

		// session 나오면 수정할 부분
		sql += " WHERE uno = ?";

		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getPw());
			ps.setString(2, user.getNick());
			ps.setString(3, user.getPhone());
			ps.setString(4, user.getMail());
			ps.setInt(5, user.getCold());
			ps.setInt(6, user.getHot());
			ps.setInt(7, user.getRain());
			ps.setInt(8, user.getSnow());
			
			ps.setInt(9, user.getUno());
			
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
	}
	
	@Override
	public List<Board> SelectBoardUno(int Uno) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Coupon> SelectCouponUno(int Uno) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Review> SelectReviewUno(int Uno) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User selectUserByUno(User uno) {
		conn =JDBCTemplate.getConnection();//DB연결

		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM Usertb";
		sql += " WHERE uno = ?";

		User userview = new User();
		
		//결과 저장할 user객체
		try {
			//SQL 수행 객체
			ps=conn.prepareStatement(sql);
			//조회할 게시글 번호 적용
			ps.setInt(1, uno.getUno()); 
			
			rs= ps.executeQuery();
			
			while(rs.next()) {
				userview.setUno(rs.getInt("uno"));
				userview.setName( rs.getString("uname"));
				userview.setId(rs.getString("userid"));
				userview.setBirth(rs.getString("ubirth"));
				userview.setNick(rs.getString("unick"));
				userview.setGender(rs.getString("ugender"));
				userview.setPhone(rs.getString("uphone"));
				userview.setMail(rs.getString("umail"));
				userview.setUprofileimg(rs.getString("uprofileimg"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return userview;
	}
	
	@Override
	public Map<String, Object> selectCoupleByUno(User uno) {
		conn =JDBCTemplate.getConnection();//DB연결

		// ****************** sesseion gender 정보에 따라서 if문 두개 걸어야 한다!
		
		//SQL 작성
		String sql="";
		sql += "SELECT * FROM usertb JOIN (SELECT couple.mno, couple.firstday ";
		sql += "	FROM usertb JOIN couple ON usertb.uno =  ";
		sql += "		couple.wno WHERE usertb.uno = ?) T";
		sql += " 			ON usertb.uno = T.mno WHERE ";
		sql += " 		usertb.uno=(SELECT couple.mno";
		sql += "	FROM usertb JOIN couple ON usertb.uno  ";
		sql += "=  couple.wno WHERE usertb.uno = ?) ";

		Map<String, Object> map = new HashMap<String, Object>();
		
		//결과 저장할 user객체
		try {
			//SQL 수행 객체
			ps=conn.prepareStatement(sql);
			//조회할 게시글 번호 적용
			ps.setInt(1, uno.getUno()); 
			ps.setInt(2, uno.getUno()); 
			
			rs= ps.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				Couple couple = new Couple();
				user.setUno(rs.getInt("uno"));
				user.setName( rs.getString("uname"));
				user.setId(rs.getString("userid"));
				user.setBirth(rs.getString("ubirth"));
				user.setPhone(rs.getString("uphone"));

				couple.setFirstday(rs.getString("firstday"));
				
				map.put("user",user);
				map.put("couple",couple);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return map;
	}
	
	@Override
	public User GetUserInfo(int uno) {
		conn =JDBCTemplate.getConnection();//DB연결
		
		String sql = "select * from usertb where uno = ?";
		
		User user = new User();
		
		try {
			//SQL 수행 객체
			ps=conn.prepareStatement(sql);
			//조회할 게시글 번호 적용
			ps.setInt(1, uno); 
			
			rs= ps.executeQuery();
			
			while(rs.next()) {
				
				user.setUno(rs.getInt("uno"));
				user.setName( rs.getString("uname"));
				user.setId(rs.getString("userid"));
				user.setPw(rs.getString("upw"));
				user.setBirth(rs.getString("ubirth"));
				user.setNick(rs.getString("unick"));
				user.setGender(rs.getString("ugender"));
				user.setPhone(rs.getString("uphone"));
				user.setMail(rs.getString("umail"));
				
				user.setHot(rs.getInt("uhot"));
				user.setCold(rs.getInt("ucold"));
				user.setRain(rs.getInt("urain"));
				user.setSnow(rs.getInt("usnow"));
				
				
				user.setUprofileimg(rs.getString("uprofileimg"));
				user.setUbgimg(rs.getString("ubgimg"));
				user.setUques(rs.getString("uques"));
				user.setUansw(rs.getString("uansw"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return user;
	}
	
	@Override
	public User GetOppoInfo(User user) {
		conn =JDBCTemplate.getConnection();//DB연결
		
		String sql ="";
		
		if (user.getGender().equals("f")) {
			sql = "select usertb.* from usertb where uno = (select couple.mno from usertb join couple on usertb.uno = couple.wno where couple.wno = ?)";
		} else if (user.getGender().equals("m")) {
			sql = "select usertb.* from usertb where uno = (select couple.wno from usertb join couple on usertb.uno = couple.mno where couple.mno = ?)";
		}
		
		User user2 = new User();
		
		try {
			//SQL 수행 객체
			ps=conn.prepareStatement(sql);
			//조회할 게시글 번호 적용
			ps.setInt(1, user.getUno()); 
			
			rs= ps.executeQuery();
			
			while(rs.next()) {
				
				user2.setUno(rs.getInt("uno"));
				user2.setName( rs.getString("uname"));
				user2.setId(rs.getString("userid"));
				user2.setPw(rs.getString("upw"));
				user2.setBirth(rs.getString("ubirth"));
				user2.setNick(rs.getString("unick"));
				user2.setGender(rs.getString("ugender"));
				user2.setPhone(rs.getString("uphone"));
				user2.setMail(rs.getString("umail"));
				
				user2.setHot(rs.getInt("uhot"));
				user2.setCold(rs.getInt("ucold"));
				user2.setRain(rs.getInt("urain"));
				user2.setSnow(rs.getInt("usnow"));
				
				user2.setUprofileimg(rs.getString("uprofileimg"));
				user2.setUbgimg(rs.getString("ubgimg"));
				user2.setUques(rs.getString("uques"));
				user2.setUansw(rs.getString("uansw"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return user2;
	}
	
	
	@Override
	public int checkPW(User user) {
		conn =JDBCTemplate.getConnection();//DB연결
		
		String sql = "select count(*) from usertb where uno =? and upw = ?";
		
		int res = 0;
		
		try {
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUno()); 
			ps.setString(2, user.getPw());
			
			rs= ps.executeQuery();
			
			while(rs.next()) {
				res = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return res;
	}
	
	@Override
	public int checkOppoID(String ooppoID) {
		conn =JDBCTemplate.getConnection();//DB연결
		
		String sql = "select count(*) from usertb where userid =?";
		
		int res = 0;
		
		try {
			ps=conn.prepareStatement(sql);
			
			ps.setString(1, ooppoID); 
			
			rs= ps.executeQuery();
			
			while(rs.next()) {
				res = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return res;
	}
	
	@Override
	public void setNewPW(User user) {
		conn =JDBCTemplate.getConnection();//DB연결
		
		String sql = "update USERTB set upw=? WHERE uno=?";
		
		try {
			ps=conn.prepareStatement(sql);
			
			ps.setString(1, user.getPw());
			ps.setInt(2, user.getUno());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
	}
	
	@Override
	public void setNewInfo(User user) {
		conn =JDBCTemplate.getConnection();//DB연결
		
		String sql = "update USERTB set unick=?, "
				+ " uphone=?, umail=?, uhot=?, ucold=?, urain=?, usnow=?, uprofileimg=?, ubgimg=?"
				+ " WHERE uno=?";
		
		try {
			ps=conn.prepareStatement(sql);
			
			ps.setString(1, user.getNick());
			ps.setString(2, user.getPhone());
			ps.setString(3, user.getMail());
			ps.setInt(4, user.getHot());
			ps.setInt(5, user.getCold());
			ps.setInt(6, user.getRain());
			ps.setInt(7, user.getSnow());
			
			ps.setString(8, user.getUprofileimg());
			ps.setString(9, user.getUbgimg());
			
			ps.setInt(10, user.getUno());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
	}
	
	@Override
	public void setPFImg(User user) {
		conn =JDBCTemplate.getConnection();//DB연결
		
		String sql = "update USERTB set uprofileimg=? "
				+ " WHERE uno=?";
		
		try {
			ps=conn.prepareStatement(sql);
			
			ps.setString(1, user.getUprofileimg());
			ps.setInt(2, user.getUno());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
	}
	
	@Override
	public int Boardcount(User uno) {
		conn =JDBCTemplate.getConnection();//DB연결

		String sql ="";
		sql +=  " select count(*) from usertb join board on usertb.uno = ";
		sql += " board.uno where usertb.uno =?";

		int res = 0;

		try {
			ps=conn.prepareStatement(sql);

			ps.setInt(1, uno.getUno()); 

			rs= ps.executeQuery();

			while(rs.next()) {
				res = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return res;

	}
	@Override
	public int Reviewcount(User uno) {
		conn =JDBCTemplate.getConnection();//DB연결

		String sql ="";
		sql +=  " select count(*) from usertb join Review on usertb.uno = ";
		sql += " Review.uno where usertb.uno =?";

		int res = 0;

		try {
			ps=conn.prepareStatement(sql);

			ps.setInt(1, uno.getUno()); 

			rs= ps.executeQuery();

			while(rs.next()) {
				res = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return res;

	}
	@Override
	public int Like_placecount(User uno) {
		conn =JDBCTemplate.getConnection();//DB연결

		String sql ="";
		sql +=  " select count(*) from usertb join Like_place on usertb.uno = ";
		sql += " Like_place.uno where usertb.uno =?";

		int res = 0;

		try {
			ps=conn.prepareStatement(sql);

			ps.setInt(1, uno.getUno()); 

			rs= ps.executeQuery();

			while(rs.next()) {
				res = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return res;
	}
	@Override
	public int Have_couponcount(User uno) {
		conn =JDBCTemplate.getConnection();//DB연결

		String sql ="";
		sql +=  " select count(*) from usertb join have_coupon on usertb.uno = ";
		sql += " have_coupon.uno where usertb.uno =?";

		int res = 0;

		try {
			ps=conn.prepareStatement(sql);

			ps.setInt(1, uno.getUno()); 

			rs= ps.executeQuery();

			while(rs.next()) {
				res = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return res;
	}
	
	@Override
	public User GetUserById(String userid) {
		conn =JDBCTemplate.getConnection();//DB연결
		
		String sql = "select * from usertb where userid=?";
		
		User user = new User();
		
		try {
			//SQL 수행 객체
			ps=conn.prepareStatement(sql);
			//조회할 게시글 번호 적용
			ps.setString(1, userid); 
			
			rs= ps.executeQuery();
			
			while(rs.next()) {
				
				user.setUno(rs.getInt("uno"));
				user.setName( rs.getString("uname"));
				user.setId(rs.getString("userid"));
				user.setPw(rs.getString("upw"));
				user.setBirth(rs.getString("ubirth"));
				user.setNick(rs.getString("unick"));
				user.setGender(rs.getString("ugender"));
				user.setPhone(rs.getString("uphone"));
				user.setMail(rs.getString("umail"));
				
				user.setHot(rs.getInt("uhot"));
				user.setCold(rs.getInt("ucold"));
				user.setRain(rs.getInt("urain"));
				user.setSnow(rs.getInt("usnow"));
				
				user.setUprofileimg(rs.getString("uprofileimg"));
				user.setUbgimg(rs.getString("ubgimg"));
				user.setUques(rs.getString("uques"));
				user.setUansw(rs.getString("uansw"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return user;
	}
	
	@Override
	public int MakeCouple(String couplevalid, User user) {
		conn =JDBCTemplate.getConnection();//DB연결
		
		System.out.println("*"+couplevalid);
		System.out.println("*"+user.getUno());
		
		String sql = "";
		
		int res = 0;
		
		if (user.getGender().equals("f")) {
			sql = "select count(*) from couplevalid where wno=? and couplevalid=?";
		} else if (user.getGender().equals("m")) {
			sql = "select count(*) from couplevalid where mno=? and couplevalid=?";
		}
		
		try {
			//SQL 수행 객체
			ps=conn.prepareStatement(sql);
			//조회할 게시글 번호 적용
			ps.setInt(1, user.getUno()); 
			ps.setString(2, couplevalid);
			
			rs= ps.executeQuery();
			
			while(rs.next()) {
				res = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}

		return res;
	}
	

	@Override
	public List<Map<String, Object>> GetCouponList(User user) {
		
		conn =JDBCTemplate.getConnection();//DB연결
		
		String sql = "select place.pname, C.* from place join (select coupon.*, have_coupon.cpstate from have_coupon join coupon ";
		sql += " on have_coupon.cpno = coupon.cpno where have_coupon.uno = ?) C on place.pno = C.pno order by expdate";

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		try {
			//SQL 수행 객체
			ps=conn.prepareStatement(sql);
			//조회할 게시글 번호 적용
			ps.setInt(1, user.getUno()); 
			
			rs= ps.executeQuery();
			
			while(rs.next()) {
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				HaveCoupon haveCoupon = new HaveCoupon();
				Coupon coupon = new Coupon();
				Place place = new Place();
				
				haveCoupon.setCpstate(rs.getString("cpstate"));
				
				coupon.setOriginname(rs.getString("originname"));
				coupon.setOriginname(rs.getString("originname"));
				coupon.setStoredname(rs.getString("storedname"));
				coupon.setExpdate(rs.getString("expdate"));
				coupon.setPno(rs.getInt("pno"));
				
				place.setPname(rs.getString("pname"));
				
				map.put("havecoupon", haveCoupon);
				map.put("coupon", coupon);
				map.put("place", place);
				
				list.add(map);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> GetBoardListByUser(User user) {
		
		conn =JDBCTemplate.getConnection();//DB연결
		
		String sql = "";
		
		sql += "SELECT * FROM (SELECT B.* FROM (SELECT";
		sql += " (SELECT COUNT(*) FROM BR_COMMENT BRC WHERE BRC.brno = board.brno ) cnt_comment";
		sql += " ,board.*, usertb.userid";
		sql += " FROM board JOIN usertb ON board.uno = usertb.uno";
		sql += " WHERE board.uno = ? ORDER BY board.brdate) B) BOARD";  

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		try {
			//SQL 수행 객체
			ps=conn.prepareStatement(sql);
			//조회할 게시글 번호 적용
			ps.setInt(1, user.getUno()); 
			
			rs= ps.executeQuery();
			
			while(rs.next()) {
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				Board board = new Board();
				
				board.setBrno(rs.getInt("brno"));
				board.setBrtitle(rs.getString("brtitle"));
				board.setBrcontent(rs.getString("brcontent"));
				board.setBrdate(rs.getString("brdate"));
				board.setBrviewcnt(rs.getInt("brviewcnt"));
				board.setBrlike(rs.getInt("brlike"));
				
				map.put("cnt", rs.getInt("cnt_comment"));
				map.put("board", board);
				
				list.add(map);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return list;
	}
	
	@Override
	public List<Map<String, Object>> GetReviewListByUser(User user) {

		conn =JDBCTemplate.getConnection();//DB연결
		
		String sql = "";
		
		sql += "select place.pname, R.* from place join (SELECT * FROM review where uno = ? order by rvdate)R "; 
	    sql += " on place.pno = R.pno where R.uno = ?";

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		try {
			//SQL 수행 객체
			ps=conn.prepareStatement(sql);
			//조회할 게시글 번호 적용
			ps.setInt(1, user.getUno()); 
			ps.setInt(2, user.getUno()); 
			
			rs= ps.executeQuery();
			
			while(rs.next()) {
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				Review review = new Review();
				Place place = new Place();
				
				place.setPname(rs.getString("pname"));
				
				review.setRno(rs.getInt("rno"));
				review.setPno(rs.getInt("pno"));
				review.setRvcontent(rs.getString("rvcontent"));
				review.setRvdate(rs.getString("rvdate").substring(0, 10));
				review.setRvstar(rs.getInt("rvstar"));
				
				map.put("place", place);
				map.put("review", review);
				
				list.add(map);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		return list;
	}
	
	@Override
	public void SetLikePlace(User user, Place place) {
		
		conn =JDBCTemplate.getConnection();//DB연결
		
		String sql = "INSERT INTO like_place values(?, ?, '')";

		
		try {
			//SQL 수행 객체
			ps=conn.prepareStatement(sql);
			//조회할 게시글 번호 적용
			ps.setInt(1, user.getUno()); 
			ps.setInt(2, place.getPno());
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
			JDBCTemplate.commit(conn);
		}
		
	}

}
