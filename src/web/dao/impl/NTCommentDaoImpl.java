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
import web.dao.face.NTCommentDao;
import web.dto.NTComment;
import web.dto.Notice;
import web.dto.User;

public class NTCommentDaoImpl implements NTCommentDao {

	private Connection conn = null; //DB연결 객체
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체

	
	@Override
	public List<Map<String, Object>> selecComment(Notice showNotice) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "    SELECT rownum rnum, NTC.* FROM (";
		sql += "        SELECT";
		sql += "             NT_COMMENT.*, usertb.userid";
		sql += "       FROM NT_COMMENT JOIN usertb ON NT_COMMENT.uno = usertb.uno";
		sql += "		WHERE noticeno = ?";
		sql += "    ) NTC";
		sql += " ) ORDER BY rnum";



		//결과 저장할 List
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();


		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			ps.setInt(1, showNotice.getNoticeno());

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장


			//조회 결과 처리
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				NTComment comment = new NTComment(); //결과값 저장 객체
				User user = new User();

				
				//결과값 한 행 처리
				comment.setNoticeno(rs.getInt("noticeno"));
				comment.setNtcmno(rs.getInt("ntcmno"));
				comment.setUno(rs.getInt("uno"));
				comment.setNt_con_content(rs.getString("nt_com_content"));
				comment.setNt_con_date(rs.getString("nt_com_date"));

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
	public void insertComment(NTComment comment) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += "INSERT INTO NT_COMMENT(ntcmno, noticeno, uno, nt_com_content, nt_com_date)";
		sql += " VALUES(NT_COMMENT_SEQ.nextval, ?, ?, ?, TO_CHAR(sysdate, 'YYYY-MM-DD HH24:MI:SS'))";

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			ps.setInt(1, comment.getNoticeno());
			ps.setInt(2, comment.getUno());
			ps.setString(3, comment.getNt_con_content());

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
	public void deleteComment(NTComment comment) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += "DELETE nt_comment WHERE ntcmno = ?";

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			ps.setInt(1, comment.getNtcmno());

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
	public int countComment(NTComment comment) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += " SELECT COUNT(*) FROM nt_comment WHERE ntcmno=?";

		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, comment.getNtcmno());
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
