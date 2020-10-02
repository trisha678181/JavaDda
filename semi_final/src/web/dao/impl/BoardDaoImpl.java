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
import web.dao.face.BoardDao;
import web.dto.BRImg;
import web.dto.Board;
import web.dto.User;
import web.util.Paging;

public class BoardDaoImpl implements BoardDao {
	
	private Connection conn = null; //DB연결 객체
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체

	@Override
	public int selectCntAll() {
		conn = JDBCTemplate.getConnection(); //DB연결
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM board";
		
		//최종 결과값
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 반환
			
			//조회결과 처리
			while( rs.next() ) {
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
	public List<Map<String, Object>> selectAll(Paging paging) {
		
		//DB연결 객체
		conn = JDBCTemplate.getConnection();
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "    SELECT rownum rnum, B.* FROM (";
		sql += "        SELECT";
		sql += "			(SELECT COUNT(*) FROM BR_COMMENT BRC WHERE BRC.brno = board.brno ) cnt_comment ";
		sql += "           ,board.*, usertb.userid";
		sql += "        FROM board JOIN usertb ON board.uno = usertb.uno ";
		sql += "		WHERE board.brtitle LIKE '%'||?||'%'"; 
		sql += "        ORDER BY board.brno DESC";
		sql += "    ) B";
		sql += " ) BOARD";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		
		
		//결과 저장할 List
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, paging.getSearch());
			ps.setInt(2, paging.getStartNo());	//페이징 게시글 시작 번호
			ps.setInt(3, paging.getEndNo());	//페이징 게시글 끝 번호
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			
			//조회 결과 처리
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				
				Board b = new Board(); //결과값 저장 객체
				User user = new User();
				
				//결과값 한 행 처리
				b.setBrno(rs.getInt("brno"));
				b.setUno(rs.getInt("uno"));
				b.setBrtitle(rs.getString("brtitle"));
				b.setBrcontent(rs.getString("brcontent"));
				b.setBrdate(rs.getString("brdate"));
				b.setBrviewcnt(rs.getInt("brviewcnt"));
				b.setBrlike(rs.getInt("brlike"));
				
				user.setId(rs.getString("userid"));
				
				map.put("cnt_comment", rs.getInt("cnt_comment"));
				
				map.put("board", b);
				map.put("user", user);
				
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
	public int selectBoardbrno() {
		
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += "SELECT board_seq.nextval FROM dual";

		//결과 저장할 변수
		int brno = 0;

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

			//조회 결과 처리
			while(rs.next()) {
				brno = rs.getInt(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
		return brno;

	
	}
	
	@Override
	public void updateBRviewcnt(Board brno) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += "UPDATE board";
		sql += " SET brviewcnt = brviewcnt + 1";
		sql += " WHERE brno = ?";

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			ps.setInt(1, brno.getBrno()); //조회할 게시글 번호 적용

			ps.executeUpdate(); //SQL 수행

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(ps);
		}
	}
	
	@Override
	public void InsertBoard(Board board) {
		
		conn = JDBCTemplate.getConnection(); //DB 연결

		String sql = "";
		sql += "INSERT INTO board(brno, uno, brtitle, brcontent, brdate, brviewcnt, brlike) ";
		sql += " VALUES (?, ?, ?, ?, TO_CHAR(sysdate, 'YYYY-MM-DD'), 0, 0)";
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, board.getBrno());
			ps.setInt(2, board.getUno());
			ps.setString(3, board.getBrtitle());
			ps.setString(4, board.getBrcontent());	
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

	}
	
	@Override
	public void insertFile(BRImg brImg) {
		
		System.out.println("여기까지오니");
		conn = JDBCTemplate.getConnection(); //DB 연결

		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "INSERT INTO BR_IMG(brino, brno, originname, storedname, filesize)";
		sql += " VALUES (BR_IMG_SEQ.nextval, ?, ?, ?, ?)";
		
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, brImg.getBrno());
			ps.setString(2, brImg.getOriginName());
			ps.setString(3, brImg.getStoreName());
			ps.setInt(4, brImg.getFileSize());

			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(ps);
		}
		
	}
	

	@Override
	public Board selectBoardByBoardno(Board brno) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM board";
		sql += " WHERE brno = ?";

		//결과 저장할 Board객체
		Board showBoard = null;

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			ps.setInt(1, brno.getBrno()); //조회할 게시글 번호 적용

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			
			//조회 결과 처리
			while(rs.next()) {
				showBoard = new Board(); //결과값 저장 객체

				//결과값 한 행 처리
				showBoard.setBrno(rs.getInt("brno"));
				showBoard.setUno(rs.getInt("uno"));
				showBoard.setBrtitle(rs.getString("brtitle"));
				showBoard.setBrcontent(rs.getString("brcontent"));
				showBoard.setBrdate(rs.getString("brdate"));
				showBoard.setBrviewcnt(rs.getInt("brviewcnt"));
				showBoard.setBrlike(rs.getInt("brlike"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
		return showBoard;
	}
	
	
	@Override
	public String selectNickByUserid(Board showBoard) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();
		
		//SQL 작성
		String sql = "";
		sql += "SELECT unick FROM usertb";
		sql += " WHERE uno = ?";
		
		//결과 저장할 String 변수
		String usernick = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, showBoard.getUno()); //조회할 id 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				usernick = rs.getString("unick");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return usernick;
	}
	
	@Override
	public List<BRImg> selectFile(Board showBoard) {
		
		//DB연결 객체
		conn = JDBCTemplate.getConnection();
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM BR_IMG";
		sql += " WHERE brno = ?";
		
		List<BRImg> imgList = new ArrayList<>();

		
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, showBoard.getBrno()); //조회할 id 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				BRImg brImg = new BRImg();
				
				brImg.setBrino(rs.getInt("brino"));
				brImg.setBrno(rs.getInt("brno"));
				brImg.setOriginName(rs.getString("originName"));
				brImg.setStoreName(rs.getString("storedName"));
				brImg.setFileSize(rs.getInt("filesize"));
				
				imgList.add(brImg);
//				System.out.println("imgList : " + imgList);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return imgList;
	}
	
	@Override
	public void update(Board board) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "UPDATE board";
		sql += " SET brtitle = ?";
		sql += " ,brcontent = ?";
		sql += " WHERE brno = ?";

		//DB 객체
		PreparedStatement ps = null; 

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, board.getBrtitle());
			ps.setString(2, board.getBrcontent());
			ps.setInt(3, board.getBrno());
			
			ps.executeUpdate();
			
			System.out.println("BOARD UPDATE : " + board);

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}
	}
	
	@Override
	public void deleteitemFile(String next) {
		
		System.out.println("next : " + next);
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE BR_IMG";
		sql += " WHERE brino = ?";

		//DB 객체
		PreparedStatement ps = null; 

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(next));

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}
		
	}
	
	@Override
	public void deleteFile(Board board) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE BR_IMG";
		sql += " WHERE brno = ?";

		//DB 객체
		PreparedStatement ps = null; 

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getBrno());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}
	}
	
	@Override
	public void delete(Board board) {

		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE board";
		sql += " WHERE brno = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, board.getBrno());

			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
	}
	
	
	@Override
	public void upadateLikeCount(int brno) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "UPDATE board";
		sql += " SET brlike = brlike + 1";
		sql += " WHERE brno = ?";

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, brno);
			
			
			ps.executeUpdate();	


		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}
		
	}
	
	@Override
	public int selectLike(int brno) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += "SELECT brlike FROM board";
		sql += " WHERE brno = ?";

		int likecount = 0;

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			ps.setInt(1, brno); //조회할 게시글 번호 적용

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			while(rs.next()) {
				likecount = rs.getInt("brlike");
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
		System.out.println("likecount : " + likecount);
		return likecount;
		
	}
	
	@Override
	public String selectNameByUserid(Board board) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += "SELECT uid FROM usertb";
		sql += " WHERE uno = ?";

		//결과 저장할 String 변수
		String userid = null;

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			ps.setInt(1, board.getUno()); //조회할 id 적용

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

			//조회 결과 처리
			while(rs.next()) {
				userid = rs.getString("unick");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
		return userid;
	}

}
