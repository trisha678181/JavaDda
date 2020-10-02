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
import web.dao.face.QnACommentDao;
import web.dto.Admin;
import web.dto.NTComment;
import web.dto.Notice;
import web.dto.QnA;
import web.dto.QnAComment;
import web.dto.User;

public class QnACommentDaoImpl implements QnACommentDao {

	private Connection conn = null; //DB연결 객체
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체

	
	@Override
	public List<Map<String, Object>> selecComment(QnA showQnA) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "    SELECT rownum rnum, QNAC.* FROM (";
		sql += "        SELECT";
		sql += "             QnA_COMMENT.*, admin.id";
		sql += "       FROM QnA_COMMENT JOIN admin ON QnA_COMMENT.ano = admin.ano";
		sql += "		WHERE qnano = ?";
		sql += "    ) QNAC";
		sql += " ) ORDER BY rnum";



		//결과 저장할 List
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();


		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			ps.setInt(1, showQnA.getQnano());

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장


			//조회 결과 처리
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				QnAComment comment = new QnAComment(); //결과값 저장 객체
				Admin admin = new Admin();

				
				//결과값 한 행 처리
				comment.setQnano(rs.getInt("Qnano"));
				comment.setQnacmno(rs.getInt("Qnacmno"));
				comment.setAno(rs.getInt("ano"));
				comment.setQna_con_content(rs.getString("Qna_com_content"));
				comment.setQna_con_date(rs.getString("Qna_com_date"));

				admin.setId(rs.getString("id"));

				map.put("comment", comment);
				map.put("admin", admin);
				
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
	public void insertComment(QnAComment comment) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += "INSERT INTO QnA_COMMENT(qnacmno, qnano, ano, qna_com_content, qna_com_date)";
		sql += " VALUES(QnA_COMMENT_SEQ.nextval, ?, ?, ?, TO_CHAR(sysdate, 'YYYY-MM-DD HH24:MI:SS'))";

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			ps.setInt(1, comment.getQnano());
			ps.setInt(2, comment.getAno());
			ps.setString(3, comment.getQna_con_content());

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
	public void deleteComment(QnAComment comment) {
		System.out.println("deleteComment - " + comment);
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += "DELETE QnA_comment WHERE qnacmno = ?";

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			ps.setInt(1, comment.getQnacmno());

			int res = ps.executeUpdate(); //SQL 수행 및 결과집합 저장
			System.out.println(res);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
	}
	
	@Override
	public int countComment(QnAComment comment) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += " SELECT COUNT(*) FROM qna_comment WHERE qnacmno=?";

		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, comment.getQnano());
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
