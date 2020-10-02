package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import web.DBUtil.JDBCTemplate;
import web.dao.face.LogDao;
import web.dto.Admin;
import web.dto.Business;
import web.dto.User;



public class LogDaoImpl implements LogDao {
	private Connection conn = null; //DB연결 객체
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체

	@Override
	public int selectCntMemberByUseridUserpw(User userTb) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();
				
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM usertb";
		sql += " WHERE 1=1";
		sql += "	AND userid = ?";
		sql += "	AND upw = ?";
		
		//결과 저장할 변수
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
				ps.setString(1, userTb.getId());
				ps.setString(2, userTb.getPw());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return cnt;
	}

	@Override
	public User selectMemberByUserid(User userTb) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM usertb";
		sql += " WHERE 1=1";
		sql += "	AND userid = ?";
		
		//조회결과를 저장할 객체
		User result = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, userTb.getId());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				result = new User();
				
				result.setUno(rs.getInt("uno"));
				result.setId( rs.getString("userid") );
				result.setPw( rs.getString("upw") );
				result.setNick( rs.getString("unick") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return result;
	}

	@Override
	public int selectCntBusMemberByUseridUserpw(Business business) {
		conn = JDBCTemplate.getConnection();
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM business";
		sql += " WHERE 1=1";
		sql += "	AND id = ?";
		sql += "	AND pw = ?";
		
		//결과 저장할 변수
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, business.getId());
			ps.setString(2, business.getPw());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return cnt;
	}

	@Override
	public Business selectBusMemberByUserid(Business business) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM business";
		sql += " WHERE 1=1";
		sql += "	AND id = ?";
		
		//조회결과를 저장할 객체
		Business result = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, business.getId());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				result = new Business();
				
				result.setBno( rs.getInt("bno"));
				result.setId( rs.getString("id") );
				result.setPw( rs.getString("pw") );
				result.setName( rs.getString("name") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return result;
	}


	@Override
	public int selectCntUser(String name, String email) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();
//		System.out.println("dao까지 잘들어 왓나? user : " +name +","+email);
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM usertb";
		sql += " WHERE 1=1";
		sql += "	AND uname = ?";
		sql += "	AND umail = ?";
		
		//결과 저장할 변수
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, name);
			ps.setString(2, email);
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				cnt = rs.getInt(1);
//				System.out.println("아이디 찾기 조회 결과 user : "+cnt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return cnt;
	}

	@Override
	public User selectUser(String name, String email) {
		conn = JDBCTemplate.getConnection();
//		System.out.println("selectUserinfo : " + name +","+ email);
		//SQL 작성
		String sql = "";
		sql += "SELECT userid, uname FROM userTb";
		sql += " WHERE 1=1";
		sql += "	AND uname = ?";
		sql += "	AND umail = ?";
		
		//조회결과를 저장할 객체
		User result = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, name);
			ps.setString(2, email);
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				result = new User();
				
				result.setId( rs.getString("userid") );
				result.setName( rs.getString("uname") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
//		System.out.println("일반회원 정보 : " + result);
		return result;
	}

	@Override
	public int selectCntBusiness(String name, String email) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();
//		System.out.println("dao까지 잘들어 왓나? business : " +name +","+email);
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM business";
		sql += " WHERE 1=1";
		sql += "	AND name = ?";
		sql += "	AND mail = ?";
		
		//결과 저장할 변수
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, name);
			ps.setString(2, email);
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				cnt = rs.getInt(1);
//				System.out.println("아이디 찾기 조회 결과 business : " + cnt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return cnt;
	}

	@Override
	public Business selectBusiness(String name, String email) {
		conn = JDBCTemplate.getConnection();
//		System.out.println("selectBusinessinfo : " + name +","+ email);
		//SQL 작성
		String sql = "";
		sql += "SELECT id, name FROM business";
		sql += " WHERE 1=1";
		sql += "	AND name = ?";
		sql += "	AND mail = ?";
		
		//조회결과를 저장할 객체
		Business result = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, name);
			ps.setString(2, email);
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				result = new Business();
				
				result.setId( rs.getString("id") );
				result.setName( rs.getString("name") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
//		System.out.println("사업자회원 정보 : " + result);
		return result;
	}


	@Override
	public int selectCntUserPw(User user) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();
		System.out.println("dao user pw : " + user);
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM usertb";
		sql += " WHERE 1=1";
		sql += "	AND userid = ?";
		sql += "	AND uname = ?";
		sql += "	AND umail = ?";
		sql += "	AND uques = ?";
		sql += "	AND uansw = ?";
		
		//결과 저장할 변수
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getMail());
			ps.setString(4, user.getUques());
			ps.setString(5, user.getUansw());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				cnt = rs.getInt(1);
				System.out.println("비밀번호 찾기 조회 결과 user : "+ cnt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return cnt;
	}


	
	@Override
	public User selectUserPwInfo(User user) {
		conn = JDBCTemplate.getConnection();
//		System.out.println("selectUserinfo : " + name +","+ email);
		//SQL 작성
		String sql = "";
		sql += "SELECT upw, userid FROM userTb";
		sql += " WHERE 1=1";
		sql += "	AND userid = ?";
		sql += "	AND uname = ?";
		sql += "	AND umail = ?";
		sql += "	AND uques = ?";
		sql += "	AND uansw = ?";
		
		//조회결과를 저장할 객체
		User result = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getMail());
			ps.setString(4, user.getUques());
			ps.setString(5, user.getUansw());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				result = new User();
				
				result.setPw(rs.getString("upw"));
				result.setId( rs.getString("userid") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		System.out.println("일반회원 아이디 비밀번호: " + result);
		return result;
	}
	
	
	@Override
	public int selectCntBusinessPw(Business business) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();
		System.out.println("dao business pw : " + business);
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM business";
		sql += " WHERE 1=1";
		sql += "	AND id = ?";
		sql += "	AND name = ?";
		sql += "	AND mail = ?";
		sql += "	AND ques = ?";
		sql += "	AND answ = ?";
		
		//결과 저장할 변수
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, business.getId());
			ps.setString(2, business.getName());
			ps.setString(3, business.getMail());
			ps.setString(4, business.getQues());
			ps.setString(5, business.getAnsw());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				cnt = rs.getInt(1);
				System.out.println("비밀번호 찾기 조회 결과 business : "+ cnt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return cnt;
	}


	@Override
	public Business selecttBusinessPwInfo(Business business) {
		conn = JDBCTemplate.getConnection();
//		System.out.println("selectUserinfo : " + name +","+ email);
		//SQL 작성
		String sql = "";
		sql += "SELECT pw, id FROM business";
		sql += " WHERE 1=1";
		sql += "	AND id = ?";
		sql += "	AND name = ?";
		sql += "	AND mail = ?";
		sql += "	AND ques = ?";
		sql += "	AND answ = ?";
		
		//조회결과를 저장할 객체
		Business result = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, business.getId());
			ps.setString(2, business.getName());
			ps.setString(3, business.getMail());
			ps.setString(4, business.getQues());
			ps.setString(5, business.getAnsw());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				result = new Business();
				
				result.setPw(rs.getString("pw"));
				result.setId( rs.getString("id") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		System.out.println("사업자회원 아이디 비밀번호: " + result);
		return result;
	}

	@Override
	public int selectAdminLog(Admin admin) {
		//DB연결 객체
				conn = JDBCTemplate.getConnection();
						
				//SQL 작성
				String sql = "";
				sql += "SELECT count(*) FROM admin";
				sql += " WHERE 1=1";
				sql += "	AND id = ?";
				sql += "	AND pw = ?";
				
				//결과 저장할 변수
				int cnt = 0;
				
				try {
					ps = conn.prepareStatement(sql); //SQL수행 객체
					
						ps.setString(1, admin.getId());
						ps.setString(2, admin.getPw());
					
					rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
					
					//조회 결과 처리
					while(rs.next()) {
						cnt = rs.getInt(1);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					//DB객체 닫기
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}
				System.out.println("cnt >> " + cnt);
				//최종 결과 반환
				return cnt;
	}

}
