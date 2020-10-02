package web.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import web.DBUtil.JDBCTemplate;
import web.dao.face.AccountDao;
import web.dto.Business;
import web.dto.User;



public class AccountDaoImpl implements AccountDao{

   private Connection conn = null;
   private PreparedStatement ps = null;
   private ResultSet rs = null;
   
   @Override
   public void acinsert(User acparam) {
      
      //DB연결 겍체
      conn = JDBCTemplate.getConnection();
      
      String sql = "";
      
      sql += "INSERT INTO usertb (uno, uname, userid, upw, ubirth, unick,";
      sql += " ugender, uphone, umail, uhot, ucold, urain, usnow, uprofileimg, ubgimg, uques, uansw)";
      sql += "   VALUES(usertb_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      
      try {
         ps = conn.prepareStatement(sql);
         
         ps.setString(1, acparam.getName());
         ps.setString(2, acparam.getId());
         ps.setString(3, acparam.getPw());
         ps.setString(4, acparam.getBirth());
         ps.setString(5, acparam.getNick());
         ps.setString(6, acparam.getGender());
         ps.setString(7, acparam.getPhone());
         ps.setString(8, acparam.getMail());
         ps.setInt(9, acparam.getHot());
         ps.setInt(10, acparam.getCold());
         ps.setInt(11, acparam.getRain());
         ps.setInt(12, acparam.getSnow());
         ps.setString(13, acparam.getUprofileimg());
         ps.setString(14, acparam.getUbgimg());
         ps.setString(15, acparam.getUques());
         ps.setString(16, acparam.getUansw());

         
         ps.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         JDBCTemplate.close(ps);
      }

      
   }

   @Override
   public void businsert(Business acparam) {
      //DB연결 겍체
            conn = JDBCTemplate.getConnection();
            
            String sql = "";
            
            sql += "INSERT INTO business (bno, id, pw, name, bsnum, phone, birth, gender, mail, ques, answ)";
            sql += "   VALUES(business_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
            
            try {
               ps = conn.prepareStatement(sql);
               
               ps.setString(1, acparam.getId());
               ps.setString(2, acparam.getPw());
               ps.setString(3, acparam.getName());
               ps.setString(4, acparam.getBsnum());
               ps.setString(5, acparam.getPhone());
               ps.setString(6, acparam.getBirth());
               ps.setString(7, acparam.getGender());
               ps.setString(8, acparam.getMail());
               ps.setString(9, acparam.getQues());
               ps.setString(10, acparam.getAnsw());
               
               ps.executeUpdate();
               
            } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }finally {
               JDBCTemplate.close(ps);
            }

      
   }

   @Override
   public int CheckcntByUserId(User usertb) {
      conn = JDBCTemplate.getConnection();   
      System.out.println(usertb );
      
      String sql = "";
      sql += "SELECT count(*) FROM usertb";
      sql += " WHERE 1=1";
      sql += "   AND userid = ?";
      
      int cnt = 0;
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, usertb.getId());
         rs = ps.executeQuery();
         
         
         //조회 결과 처리
         while(rs.next()) {
            cnt = rs.getInt(1);
//            System.out.println(cnt);
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
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
   public int CheckcntByUserNick(User usertb) {
      conn = JDBCTemplate.getConnection();
      System.out.println("myNICK : "+usertb );
      
      String sql = "";
      sql += "SELECT count(*) FROM usertb";
      sql += " WHERE 1=1";
      sql += "   AND unick = ?";
      
      
      
      int cnt = 0;
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, usertb.getNick());
         rs = ps.executeQuery();
         
         
         //조회 결과 처리
         while(rs.next()) {
            cnt = rs.getInt(1);
//            System.out.println("nickname cnt : "+cnt);
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
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
   public int CheckcntById(Business business) {
      conn = JDBCTemplate.getConnection();
      System.out.println(business);
      
      String sql = "";
      sql += "SELECT count(*) FROM business";
      sql += " WHERE 1=1";
      sql += "   AND id = ?";
      
      int cnt = 0;
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, business.getId());
         rs = ps.executeQuery();
         
         
         //조회 결과 처리
         while(rs.next()) {
            cnt = rs.getInt(1);
            
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
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
   public int CheckMailcntById(User usertb) {
      conn = JDBCTemplate.getConnection();
      System.out.println(usertb );
      
      String sql = "";
      sql += "SELECT count(*) FROM usertb";
      sql += " WHERE 1=1";
      sql += "   AND umail = ?";
      
      
      
      int cnt = 0;
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, usertb.getMail());
         rs = ps.executeQuery();
         
         
         //조회 결과 처리
         while(rs.next()) {
            cnt = rs.getInt(1);
            
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         //DB객체 닫기
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }
      System.out.println("mail cnt: "+cnt);      
      //최종 결과 반환
      return cnt;
   }

   @Override
   public int CheckMailcntById(Business business) {
      conn = JDBCTemplate.getConnection();   
      System.out.println(business);
      
      String sql = "";
      sql += "SELECT count(*) FROM business";
      sql += " WHERE 1=1";
      sql += "   AND mail = ?";
      
      int cnt = 0;
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, business.getMail());
         rs = ps.executeQuery();
         
         
         //조회 결과 처리
         while(rs.next()) {
            cnt = rs.getInt(1);
            
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         //DB객체 닫기
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }
      System.out.println("bmail cnt : " + cnt);      
      //최종 결과 반환
      return cnt;
   }

   @Override
   public int cntUserId(String userid) {
      conn = JDBCTemplate.getConnection();   
      System.out.println("dao/ userid : " + userid);
      
      String sql = "";
      sql += "SELECT count(*) FROM usertb";
      sql += " WHERE 1=1";
      sql += "   AND userid = ?";
      
      int cnt = 0;
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, userid);
         rs = ps.executeQuery();
         
         
         //조회 결과 처리
         while(rs.next()) {
            cnt = rs.getInt(1);
            System.out.println("doa/cnt : "+cnt);
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
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
   public int cntBusId(String businessid) {
      conn = JDBCTemplate.getConnection();
      System.out.println("dao/ userid : "+ businessid);
      
      String sql = "";
      sql += "SELECT count(*) FROM business";
      sql += " WHERE 1=1";
      sql += "   AND id = ?";
      
      int cnt = 0;
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, businessid);
         rs = ps.executeQuery();
                  
         //조회 결과 처리
         while(rs.next()) {
            cnt = rs.getInt(1);
            System.out.println("doa/bcnt : "+cnt);
            
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
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
   public int cntMail(String usermail) {
      conn = JDBCTemplate.getConnection();
      System.out.println("cntMail : "+usermail );
      
      String sql = "";
      sql += "SELECT count(*) FROM usertb";
      sql += " WHERE 1=1";
      sql += "   AND umail = ?";
      
      
      
      int cnt = 0;
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, usermail);
         rs = ps.executeQuery();
         
         
         //조회 결과 처리
         while(rs.next()) {
            cnt = rs.getInt(1);
            
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         //DB객체 닫기
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }
      System.out.println("mail cnt: "+cnt);      
      //최종 결과 반환
      return cnt;
   }

   @Override
   public int cntBmail(String busmail) {
      conn = JDBCTemplate.getConnection();   
      System.out.println("cnt Bmail :" + busmail);
      
      String sql = "";
      sql += "SELECT count(*) FROM business";
      sql += " WHERE 1=1";
      sql += "   AND mail = ?";
      
      int cnt = 0;
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, busmail);
         rs = ps.executeQuery();
         
         
         //조회 결과 처리
         while(rs.next()) {
            cnt = rs.getInt(1);
            
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         //DB객체 닫기
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }
      System.out.println("bmail cnt : " + cnt);      
      //최종 결과 반환
      return cnt;
   }

   @Override
   public int checkcntbusum(String bsnum) {
      conn = JDBCTemplate.getConnection();
      System.out.println("bsnum Dao >> " + bsnum);
      String sql = "";
      sql += "SELECT count(*) FROM business";
      sql += " WHERE 1=1";
      sql += "   AND bsnum = ?";
      
      int cnt = 0;
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, bsnum);
         rs = ps.executeQuery();
                  
         //조회 결과 처리
         while(rs.next()) {
            cnt = rs.getInt(1);
            System.out.println("doa/bsumcnt : "+cnt);
            
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         //DB객체 닫기
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }
            
      //최종 결과 반환
      return cnt;
   }

}