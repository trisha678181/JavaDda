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
import web.dao.face.BRCommentDao;
import web.dto.BRComment;
import web.dto.Board;
import web.dto.User;

public class BRCommentDaoImpl implements BRCommentDao {
	
	private Connection conn = null; //DB연결 객체
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체

	
	@Override
	public List<Map<String, Object>> selecComment(Board showBoard) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "    SELECT rownum rnum, BR.* FROM (";
		sql += "        SELECT";
		sql += "             br_comment.*, usertb.userid";
		sql += "       FROM br_comment JOIN usertb ON br_comment.uno = usertb.uno";
		sql += "		WHERE brno = ?";
		sql += "    ) BR";
		sql += " ) ORDER BY rnum";



		//결과 저장할 List
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			ps.setInt(1, showBoard.getBrno());

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장


			//조회 결과 처리
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();

				BRComment comment = new BRComment(); //결과값 저장 객체
				User user = new User();

				
				//결과값 한 행 처리
				comment.setBrno(rs.getInt("brno"));
				comment.setBrcmno(rs.getInt("brcmno"));
				comment.setUno(rs.getInt("uno"));
				comment.setBrcontent(rs.getString("brcontent"));
				comment.setBrdate(rs.getString("brdate"));
				
				user.setId(rs.getString("userid"));

				map.put("comment", comment);
				map.put("user", user);
				
				//리스트에 결과값 저장
				list.add(map);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
		return list;

	}

	@Override
	public void insertComment(BRComment comment) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += "INSERT INTO BR_COMMENT(brcmno, brno, uno, brcontent, brdate)";
		sql += " VALUES(BR_COMMENT_SEQ.nextval, ?, ?, ?, TO_CHAR(sysdate, 'YYYY-MM-DD HH24:MI:SS'))";

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			ps.setInt(1, comment.getBrno());
			ps.setInt(2, comment.getUno());
			ps.setString(3, comment.getBrcontent());

			ps.executeQuery(); //SQL 수행 및 결과집합 저장


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		
	}

	
	@Override
	public void deleteComment(BRComment comment) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += "DELETE BR_COMMENT WHERE brcmno = ?";

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			ps.setInt(1, comment.getBrcmno());
			
			ps.executeQuery(); //SQL 수행 및 결과집합 저장


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
	}
	
	
	@Override
	public int countComment(BRComment comment) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += " SELECT COUNT(*) FROM BR_COMMENT WHERE brcmno=?";
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, comment.getBrcmno());
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			rs.next();
			cnt = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return cnt;
	}
	
	@Override
	public int replyCount(Board showBoard) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += " SELECT COUNT(*) FROM BR_COMMENT WHERE brno=?";

		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, showBoard.getBrno());
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

			rs.next();
			cnt = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return cnt;
	}

}
