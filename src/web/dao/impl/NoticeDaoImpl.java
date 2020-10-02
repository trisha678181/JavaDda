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
import web.dao.face.NoticeDao;
import web.dto.Notice;
import web.util.Paging;

public class NoticeDaoImpl implements NoticeDao {
	
	private Connection conn = null; //DB연결 객체
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	//예지
	 @Override
		public List<Map<String, Object>> SelectAll(Paging paging) {
			conn = JDBCTemplate.getConnection();

			
			String sql="";
		      sql += "SELECT * from(";
		      sql += "   SELECT ROWNUM rnum,N.*From( ";
		      sql += "      select";
		      sql += "		(select count(*) from nt_comment ntc where ntc.noticeno = notice.noticeno) cnt_comment";
		      sql += "		,noticeno,title, ntcontent, ntdate, hit";
		      sql += "           FROM NOTICE";
		      sql += "		WHERE title LIKE '%'||?||'%'";
		      sql += "       ORDER BY noticeno DESC";
		      sql += "   ) N";
		      sql += " )NOTICE";
		      sql += " where rnum between ? and ?";
		      
		      List<Map<String, Object>> noticelist = new ArrayList<Map<String,Object>>();

		      try {
		         ps = conn.prepareStatement(sql);
		         
		         ps.setString(1, paging.getSearch());
		         ps.setInt(2, paging.getStartNo()); 
		         ps.setInt(3, paging.getEndNo());
		         
		         rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
		         
		         while(rs.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
	  
		            Notice notice = new Notice();
		            
		            
		            //결과값 한 행 처리
		            notice.setNoticeno( rs.getInt("noticeno") );
		            notice.setTitle( rs.getString("title") );
		            notice.setNtcontent( rs.getString("ntcontent") );
		            notice.setNtdate( rs.getString("ntdate") );
		            notice.setHit(rs.getInt("hit"));

		            //리스트에 결과값 저장
			    map.put("cnt_comment", rs.getInt("cnt_comment"));
		            map.put("notice", notice);
		            
		            noticelist.add(map);
		          
		         }
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
					//DB객체 닫기
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}
		      return noticelist;
		}
	
	@Override
	public List<Notice> SelectNoticeAll(Paging paging) {
		conn = JDBCTemplate.getConnection();

	      String sql="";
	      sql += "SELECT * from(";
	      sql += "   SELECT ROWNUM rnum,N.*From( ";
	      sql += "      select  noticeno,title, ntcontent, ntdate, hit";
	      sql += "           FROM NOTICE";
	      sql += "		WHERE title LIKE '%'||?||'%'";
	      sql += "       ORDER BY noticeno DESC";
	      sql += "   ) N";
	      sql += " )NOTICE";
	      sql += " where rnum between ? and ?";

	      List<Notice> nlist = new ArrayList<>();

	      try {
	         ps = conn.prepareStatement(sql);
	         
	         ps.setString(1, paging.getSearch());
	         ps.setInt(2, paging.getStartNo()); 
	         ps.setInt(3, paging.getEndNo());
	         
	         rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
	         
	         while(rs.next()) {
	            Notice notice = new Notice();
	            //결과값 한 행 처리
	            notice.setNoticeno( rs.getInt("noticeno") );
	            notice.setTitle( rs.getString("title") );
	            notice.setNtcontent( rs.getString("ntcontent") );
	            notice.setNtdate( rs.getString("ntdate") );
	            notice.setHit(rs.getInt("hit"));

	            //리스트에 결과값 저장
	            nlist.add(notice);
	          
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
				//DB객체 닫기
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
	      return nlist;
	}
	
	@Override
	public Notice selectNoticeByNoticeno(Notice notice) {
		  conn =JDBCTemplate.getConnection();//DB연결

	         //SQL 작성
	         String sql = "";
	         sql += "SELECT * FROM notice";
	         sql += " WHERE noticeno = ?";


	         //결과 저장할 Board객체
	         try {
	            //SQL 수행 객체
	            ps=conn.prepareStatement(sql);
	            //조회할 게시글 번호 적용
	            ps.setInt(1, notice.getNoticeno()); 
	            //SQL
	            rs= ps.executeQuery();

	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }

	         Notice noticeview = new Notice();
	         
	         try {
	            while(rs.next()) {
	               noticeview.setNoticeno(rs.getInt("noticeno"));
	               noticeview.setAno(rs.getInt("ano"));
	               noticeview.setTitle(rs.getString("title"));
	               noticeview.setNtcontent( rs.getString("ntcontent"));
	               noticeview.setNtdate(rs.getString("ntdate"));
	               noticeview.setHit(rs.getInt("hit"));
	            }
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         } finally {
					//DB객체 닫기
					JDBCTemplate.close(rs);
					JDBCTemplate.close(ps);
				}

	         return noticeview;
	}
	
	
	@Override
	public int selectCntAll() {
		conn = JDBCTemplate.getConnection();
	      String sql="";
	      sql +="SELECT count(*) FROM notice";
	      
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
	      }finally {
				//DB객체 닫기
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
	      return cnt;
	}
	
	
	//======================= 혜연 =====================================

	@Override
	public void updateHit(Notice noticeno) {
		String sql=""; 
		sql += "update notice";
		sql += " set hit= hit +1";
		sql += " where noticeno = ?";

		try {
			ps= conn.prepareStatement(sql);
			ps.setInt(1, noticeno.getNoticeno());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

			try {
				if(ps !=null) ps.close();
				if(rs !=null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}


	@Override
	public void UpdateNotice(Notice notice) {
		conn =JDBCTemplate.getConnection();//DB연결
		//SQL 작성
		String sql = "";
		sql += "UPDATE notice SET title=?, ntcontent=? where noticeno = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, notice.getTitle());
			ps.setString(2, notice.getNtcontent());
			ps.setInt(3, notice.getNoticeno());


			ps.executeUpdate();

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//DB객체 닫기
				if(ps!=null)	ps.close();
				if(rs !=null) 	rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


	}

	@Override
	public void delete(Notice notice) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE NOTICE";
		sql += " WHERE noticeno = ?";

		//DB 객체
		PreparedStatement ps = null; 

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, notice.getNoticeno());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCTemplate.close(ps);
		}      
	}


	@Override
	public void insertBoard(Notice notice) {
		conn = JDBCTemplate.getConnection(); //DB 연결

		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "INSERT INTO notice(NOTICENO, ANO, TITLE, NTCONTENT, NTDATE, HIT) ";
		sql += " VALUES (notice_seq.nextval, ?, ?, ?, TO_CHAR(sysdate, 'YYYY-MM-DD HH24:MI:SS'),0)";

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, notice.getAno());
			ps.setString(2, notice.getTitle());
			ps.setString(3, notice.getNtcontent());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(ps!=null)	ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	@Override
	public int replyCount(Notice showNotice) {
		//DB연결 객체
		conn = JDBCTemplate.getConnection();

		//SQL 작성
		String sql = "";
		sql += " SELECT COUNT(*) FROM NT_COMMENT WHERE noticeno=?";

		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, showNotice.getNoticeno());
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
