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
import web.dao.face.ReviewDao;
import web.dto.Business;
import web.dto.PImg;
import web.dto.Place;
import web.dto.RVComment;
import web.dto.RVImg;
import web.dto.Review;
import web.dto.User;
import web.util.Paging;
import web.util.ReviewPaging;

public class ReviewDaoImpl implements ReviewDao {

	private Connection conn = null; // DB연결 객체
	private PreparedStatement ps = null; // SQL수행 객체
	private ResultSet rs = null; // SQL조회 결과 객체

	@Override
	public List<Map<String, Object>> SelectReviewAll(Paging paging) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "   SELECT * FROM(";
		sql += " SELECT ROWNUM rnum, RO.* FROM( ";
		sql += "    SELECT r.rno, p.pname, u.uname, r.rvdate  ";
		sql += "     FROM place P";
		sql += "        JOIN review R";
		sql += "           ON P.pno = R.pno ";
		sql += "      JOIN usertb U";
		sql += "        ON R.uno = U.uno";
		sql += "        WHERE P.pname LIKE '%'||?||'%'";
		sql += "        )";
		sql += "          RO) ";
		sql += "      review";
		sql += "   WHERE rnum BETWEEN ? AND ?";

		List<Map<String, Object>> list = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, paging.getSearch());
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			while (rs.next()) {

				Review review = new Review();
				Place place = new Place();
				User user = new User();

				// 결과값 한 행 처리
				review.setRno(rs.getInt("rno"));
				// System.out.println("rno :" + rs.getInt("rno") );
				place.setPname(rs.getString("pname"));
				// System.out.println("pname :" + rs.getString("pname") );
				user.setName(rs.getString("uname"));
				// System.out.println("uname :" + rs.getString("uname") );
				review.setRvdate(rs.getString("rvdate"));
				// System.out.println("rvdate :" + rs.getString("rvdate") );

				Map<String, Object> m = new HashMap<>();
				m.put("review", review);
				m.put("place", place);
				m.put("user", user);

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

	@Override
	public int selectCntAll() {
		conn = JDBCTemplate.getConnection(); // DB연결

		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM Review";

		// 최종 결과값
		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			rs = ps.executeQuery(); // SQL수행 및 결과집합 반환

			// 조회결과 처리
			while (rs.next()) {
				cnt = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return cnt;

	}

	// -------------------------새로 추가한 것
	@Override
	public Map<String, Object> selectReviewByReviewno(Review review) {
		conn = JDBCTemplate.getConnection();// DB연결

		String sql = "";
		sql += " SELECT P.pname, PI.storedname ,R.rvcontent ,R.rvdate, R.rvstar,U.uname, R.rno";
		sql += "    FROM place P ";
		sql += "       JOIN P_IMG PI ";
		sql += "      ON P.pno = PI.pno";
		sql += "         JOIN review R";
		sql += "      ON P.pno = R.pno";
		sql += "   JOIN usertb U";
		sql += " ON R.uno = U.uno";
		sql += " where R.rno = ?";

		Map<String, Object> map = new HashMap<String, Object>();

		// 결과 저장할 Board객체
		try {
			// SQL 수행 객체
			ps = conn.prepareStatement(sql);
			// 조죄할 게스글 번호 적용
			ps.setInt(1, review.getRno());
			// SQL
			rs = ps.executeQuery();

			Place placeview = new Place();
			Review reviewview = new Review();
			PImg p_imgview = new PImg();
			User userview = new User();

			while (rs.next()) {
				placeview.setPname(rs.getString("pname"));

				p_imgview.setStoredName(rs.getString("storedName"));

				userview.setName(rs.getString("uname"));

				reviewview.setRvcontent(rs.getString("rvcontent"));
				reviewview.setRvdate(rs.getString("rvdate"));
				reviewview.setRvstar(rs.getInt("rvstar"));
				reviewview.setRno(rs.getInt("rno"));

				map.put("placeview", placeview);
				map.put("p_imgview", p_imgview);
				map.put("userview", userview);
				map.put("reviewview", reviewview);

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
		return map;
	}

	@Override
	public void delete(Review reviewNo) {

		// DB연결 객체
		conn = JDBCTemplate.getConnection();

		// 다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE Review";
		sql += " WHERE rno = ?";

		// DB 객체
		PreparedStatement ps = null;

		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewNo.getRno());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}

	}

	// ------------------------------------문정추가

	@Override
	public int selectCntAll(int pno) { // 리뷰의 총 개수
		conn = JDBCTemplate.getConnection(); // DB연결

		// SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM review";
		sql += " WHERE pno = ?";

		// 최종 결과값
		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, pno);

			rs = ps.executeQuery(); // SQL수행 및 결과집합 반환

			// 조회결과 처리
			while (rs.next()) {
				cnt = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return cnt;
	}

	@Override
	public List<Map<String, Object>> SelectReviewAll(ReviewPaging paging, Review pno) {
		// DB연결 객체
		conn = JDBCTemplate.getConnection();

		// SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "    SELECT rownum rnum, R.* FROM (";
		sql += "        SELECT r.rno, r.pno, r.rvstar, r.rvcontent, r.rvdate, ri.rino, ri.originname, ri.storedname, u.uno, u.userid, u.uname, u.unick";
		sql += "        from review r, rv_img ri, usertb u";
		sql += "		where r.uno = u.uno and r.rno = ri.rno(+) and pno= ?";
		sql += "        order by r.rno desc";
		sql += "    ) R";
		sql += "  ) review";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체

			ps.setInt(1, pno.getPno());
			ps.setInt(2, paging.getStartNo()); // 페이징 게시글 시작 번호
			ps.setInt(3, paging.getEndNo()); // 페이징 게시글 끝 번호

			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			// 조회 결과 처리
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();

				Review r = new Review();
				RVImg ri = new RVImg();
				User u = new User();

				r.setRno(rs.getInt("rno"));
				r.setPno(rs.getInt("pno"));
				r.setRvcontent(rs.getString("rvcontent"));
				r.setRvdate(rs.getString("rvdate"));
				r.setRvstar(rs.getInt("rvstar"));

				ri.setRino(rs.getInt("rino"));
				ri.setRno(rs.getInt("rno"));
				ri.setOriginname(rs.getString("originname"));
				ri.setStoredname(rs.getString("storedname"));

				u.setUno(rs.getInt("uno"));
				u.setId(rs.getString("userid"));
				u.setName(rs.getString("uname"));
				u.setNick(rs.getString("unick"));

				map.put("review", r);
				map.put("rvimg", ri);
				map.put("user", u);

				list.add(map);

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return list;

	}

	@Override
	public void insertReviewAll(Review review) {

		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "insert into review(rno, pno, uno, rvcontent, rvdate, rvstar)";
		sql += " values(?, ?, ?, ?, TO_CHAR(sysdate, 'YYYY-MM-DD HH24:MI:SS'), ?)";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, review.getRno());
			ps.setInt(2, review.getPno());
			ps.setInt(3, review.getUno());
			ps.setString(4, review.getRvcontent());
			ps.setInt(5, review.getRvstar());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

	}

	@Override
	public int selectReviewrno() {

		// DB연결 객체
		conn = JDBCTemplate.getConnection();

		// SQL 작성
		String sql = "";
		sql += "SELECT REVIEW_SEQ.NEXTVAL FROM dual";

		// 결과 저장할 변수
		int rvno = 0;

		try {
			ps = conn.prepareStatement(sql); // SQL수행 객체
			rs = ps.executeQuery(); // SQL 수행 및 결과집합 저장

			// 조회 결과 처리
			while (rs.next()) {
				rvno = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		// 최종 결과 반환
		return rvno;

	}

	@Override
	public void insertFile(RVImg rvi) {
		System.out.println("insertFile 테스트");
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "insert into RV_IMG(rino, rno, originname, storedname, filesize)";
		sql += " values(RV_IMG_SEQ.NEXTVAL,?,?,?,?)";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, rvi.getRno());
			ps.setString(2, rvi.getOriginname());
			ps.setString(3, rvi.getStoredname());
			ps.setInt(4, rvi.getFilesize());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}
	}

	// --------------------------------------------답글
	@Override
	public void insertComment(RVComment rvcomment) {
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "insert into RV_COMMENT(rvcmno, rno, uno, rv_com_content, rv_com_date)";
		sql += " values(RV_COMMENT_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(sysdate, 'YYYY-MM-DD HH24:MI:SS'))";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, rvcomment.getRno());
			ps.setInt(2, rvcomment.getUno());
			ps.setString(3, rvcomment.getRv_com_content());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

	}

	@Override
	public List<Map<String, Object>> selectComment(Review pno) {
//		System.out.println("reivew dao / pno >> " + pno);
		conn = JDBCTemplate.getConnection();

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "    SELECT rownum rnum, BR.* FROM (";
		sql += "        select RV_COMMENT.*, usertb.unick, review.pno";
		sql += "          from review, rv_comment, usertb";
		sql += "          where review.rno = rv_comment.rno (+)";
		sql += "		and rv_comment.uno = usertb.uno";
		sql += "		and review.pno = ?";
		sql += "		order by review.rno desc";
		sql += "    ) BR";
		sql += " ) ORDER BY rnum";

		List<Map<String, Object>> rvcommentList = new ArrayList<Map<String, Object>>();

		try {

			ps = conn.prepareStatement(sql);

			ps.setInt(1, pno.getPno());
//			ps.setInt(2, pno.getRno());

			rs = ps.executeQuery();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();

				RVComment rvcomment = new RVComment();
				User user = new User();

				rvcomment.setRvcmno(rs.getInt("rvcmno"));
				rvcomment.setRno(rs.getInt("rno"));
				rvcomment.setUno(rs.getInt("uno"));
				rvcomment.setRv_com_content(rs.getString("rv_com_content"));
				rvcomment.setRv_com_date(rs.getString("rv_com_date"));

//				user.setId(rs.getString("uid"));
				user.setNick(rs.getString("unick"));

				map.put("rvcomment", rvcomment);
				map.put("user", user);

				rvcommentList.add(map);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		System.out.println("ReveiwDao - 리스트 : " + rvcommentList);
		return rvcommentList;
	}

	@Override
	public int reviewTotalCount(int pno) { // 리뷰갯수
		conn = JDBCTemplate.getConnection();

		String sql = "select count(*) from review where pno = ?";

		int cnt = 0;

		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1, pno);
			rs = ps.executeQuery();

			rs.next();
			cnt = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return cnt;
	}

	@Override
	public int reviewTotalCount(Review pno) { // 리뷰총갯수

		conn = JDBCTemplate.getConnection();

		String sql = "select count(*) from review where pno = ?";

		int cnt = 0;

		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1, pno.getPno());
			rs = ps.executeQuery();

			rs.next();
			cnt = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return cnt;
	}

	@Override
	public List<Map<String, Object>> selectPlaceName(Review pno) {
		// 기본 리뷰리스트 불러오기
		conn = JDBCTemplate.getConnection();

		String sql = "select * from review r, place p where r.pno(+) = p.pno and p.pno=?";

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pno.getPno());

			rs = ps.executeQuery();

			while (rs.next()) {

				Map<String, Object> map = new HashMap<>();

				Review review = new Review();
				Place place = new Place();

				review.setPno(rs.getInt("pno"));
				review.setRno(rs.getInt("rno"));
				review.setRvcontent(rs.getString("rvcontent"));
				review.setRvdate(rs.getString("rvdate"));
				review.setRvstar(rs.getInt("rvstar"));

				place.setPno(rs.getInt("pno"));
				place.setLno(rs.getInt("lno"));
				place.setPcode(rs.getInt("pcode"));
				place.setPname(rs.getString("pname"));
				place.setPcontent(rs.getString("pcontent"));
				place.setPphone(rs.getString("pphone"));
				place.setPloc(rs.getString("ploc"));
				place.setPmenu_txt(rs.getString("pmenu_txt"));
				place.setPoperate(rs.getString("poperate"));
				place.setPparking(rs.getString("pparking"));
				place.setPlike(rs.getInt("plike"));
				place.setPpick(rs.getString("ppick"));
				place.setPsubtitle(rs.getString("psubtitle"));

				map.put("place", place);
				map.put("review", review);

				list.add(map);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return list;

	}

}