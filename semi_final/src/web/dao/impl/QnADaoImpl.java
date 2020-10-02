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
import web.dao.face.QnADao;
import web.dto.Business;
import web.dto.QnA;
import web.util.Paging;

public class QnADaoImpl implements QnADao {
   
   private Connection conn = null; //DB연결 객체
   private PreparedStatement ps = null; //SQL수행 객체
   private ResultSet rs = null; //SQL조회 결과 객체
   
   @Override
      public List<Map<String, Object>> SelectQnAAll(Paging paging) {
      conn = JDBCTemplate.getConnection();

  	String sql="";
    sql += "SELECT * FROM(";
    sql += " SELECT ROWNUM rnum, R.* FROM(";
    sql += "    SELECT q.QNANO, b.name, q.title, q.qdate";
    sql += "       FROM qna q join business b on q.bno = b.bno ";
    sql += "      WHERE q.title LIKE '%'||?||'%'"; 
    sql   += "ORDER BY QNANO DESC )";
    sql += "    R)";
    sql += " QNA WHERE rnum BETWEEN ? AND ?";

      List<Map<String, Object>> list = new ArrayList<>();

      try {
         ps = conn.prepareStatement(sql);

         ps.setString(1, paging.getSearch());
         ps.setInt(2, paging.getStartNo()); 
         ps.setInt(3, paging.getEndNo());

         rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

         while(rs.next()) {

            QnA qna = new QnA();
            Business business = new Business(); 
            
            //결과값 한 행 처리
            qna.setQnano( rs.getInt("qnano") );
            business.setName(rs.getString("name"));
            qna.setTitle( rs.getString("title") );
            qna.setQdate( rs.getString("qdate") );

            Map<String, Object> m = new HashMap<>();
            m.put("qna", qna);
            m.put("business", business);
            list.add(m);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         try {
            if(rs != null)   rs.close();
            if(ps != null)   ps.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return list;
   }
   
   
   
   
   //***************************************************
   
   @Override
   public void InsertQnA(QnA Qna) {
      conn = JDBCTemplate.getConnection(); //DB 연결

      //다음 게시글 번호 조회 쿼리
      String sql = "";
      sql += "INSERT INTO QNA(qnano, bno, title, qcontent, qdate) ";
      sql += " VALUES (QNA_SEQ.nextval, ?, ?, ?, TO_CHAR(sysdate, 'YYYY-MM-DD HH24:MI:SS'))";
      
      try {
         //DB작업
         ps = conn.prepareStatement(sql);
         
         ps.setInt(1, Qna.getBno());
         ps.setString(2, Qna.getTitle());
         ps.setString(3, Qna.getQcontent());
         

         ps.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            //DB객체 닫기
            if(ps!=null)   ps.close();
            
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
   
   @Override
   public int selectCntAll() {
      conn = JDBCTemplate.getConnection(); //DB연결

      //SQL 작성
      String sql = "";
      sql += "SELECT count(*) FROM QNA";

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
   public QnA selectQnabyQnano(QnA qnano) {
      //DB연결 객체
      conn = JDBCTemplate.getConnection();

      //SQL 작성
      String sql = "";
      sql += "SELECT * FROM QNA";
      sql += " WHERE qnano = ?";

      //결과 저장할 Board객체
      QnA showQna = null;

      try {
         ps = conn.prepareStatement(sql); //SQL수행 객체

         ps.setInt(1, qnano.getQnano()); //조회할 게시글 번호 적용

         rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장


         //조회 결과 처리
         while(rs.next()) {
            showQna = new QnA(); //결과값 저장 객체

            //결과값 한 행 처리
            showQna.setQnano(rs.getInt("qnano"));
            showQna.setBno(rs.getInt("bno"));
            showQna.setTitle(rs.getString("title"));
            showQna.setQcontent(rs.getString("qcontent"));
            showQna.setQdate(rs.getString("qdate"));
            

         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         //DB객체 닫기
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }

      //최종 결과 반환
      return showQna;
   }
   
   @Override
   public String selectNameByBusinessid(QnA showQna) {
      //DB연결 객체
      conn = JDBCTemplate.getConnection();

      //SQL 작성
      String sql = "";
      sql += "SELECT name FROM BUSINESS";
      sql += " WHERE bno = ?";

      //결과 저장할 String 변수
      String businessName = null;

      try {
         ps = conn.prepareStatement(sql); //SQL수행 객체

         ps.setInt(1, showQna.getBno()); //조회할 id 적용

         rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

         //조회 결과 처리
         while(rs.next()) {
            businessName = rs.getString("name");
         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         //DB객체 닫기
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }

      //최종 결과 반환
      return businessName;
   }
   
   @Override
   public void update(QnA qna) {
      //DB연결 객체
      conn = JDBCTemplate.getConnection();

      //다음 게시글 번호 조회 쿼리
      String sql = "";
      sql += "UPDATE QNA";
      sql += " SET title = ?";
      sql += " ,qcontent = ?";
      sql += " WHERE qnano = ?";

      //DB 객체
      PreparedStatement ps = null; 

      try {
         //DB작업
         ps = conn.prepareStatement(sql);
         ps.setString(1, qna.getTitle());
         ps.setString(2, qna.getQcontent());
         ps.setInt(3, qna.getQnano());

         ps.executeUpdate();

//         System.out.println("qna UPDATE : " + qna);

      } catch (SQLException e) {
         e.printStackTrace();

      } finally {
         JDBCTemplate.close(ps);
      }
   }
   
   @Override
   public void delete(QnA qna) {

      //DB연결 객체
      conn = JDBCTemplate.getConnection();

      //다음 게시글 번호 조회 쿼리
      String sql = "";
      sql += "DELETE QNA";
      sql += " WHERE qnano = ?";
      
      //DB 객체
      PreparedStatement ps = null; 
      
      try {
         //DB작업
         ps = conn.prepareStatement(sql);
         ps.setInt(1, qna.getQnano());

         ps.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
         
      } finally {
         JDBCTemplate.close(ps);
      }      
   }



   @Override
   public Map<String, Object> selectQnAByQnAno(QnA qnano) {

      conn =JDBCTemplate.getConnection();//DB연결

      //SQL 작성
      String sql = "";
      sql += "SELECT q.*,  b.name";
      sql += " FROM qna q join business b on q.bno = b.bno";
      sql += " where q.qnano = ?";

      Map<String, Object> map = new HashMap<String, Object>();

      //결과 저장할 qna객체
      try {
         //SQL 수행 객체
         ps=conn.prepareStatement(sql);
         //조죄할 게스글 번호 적용
         ps.setInt(1, qnano.getQnano()); 

         //SQL
         rs= ps.executeQuery();

         while(rs.next()) {
            QnA qna = new QnA();
            Business business = new Business();

            qna.setQnano(rs.getInt("qnano"));
            qna.setTitle(rs.getString("title"));
            business.setName(rs.getString("name"));
            qna.setQdate(rs.getString("qdate"));
            qna.setQcontent( rs.getString("qcontent"));

            map.put("qna", qna);
            map.put("business",business);

         }
      }catch (SQLException e) {
         e.printStackTrace();
      }finally {

         try {
            if(rs != null)   rs.close();
            if(ps != null)   ps.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }

      return map;
   }


   
   

}