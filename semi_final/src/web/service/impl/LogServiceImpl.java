package web.service.impl;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;


import web.dao.face.LogDao;
import web.dao.impl.LogDaoImpl;
import web.dto.Admin;
import web.dto.Business;
import web.dto.User;
import web.service.face.LogService;



public class LogServiceImpl implements LogService {
   private LogDao logDao = new LogDaoImpl();

   @Override
   public User getLogingenMember(HttpServletRequest req) {
      try {
         req.setCharacterEncoding("UTF-8");
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }
      
      User userTb = new User();
            
      userTb.setId( req.getParameter("id") );
      userTb.setPw( req.getParameter("pw") );
      
      return userTb;
   }

   @Override
   public boolean genLogin(User userTb) {
      if( logDao.selectCntMemberByUseridUserpw(userTb) > 0 ) {
         return true; //로그인 성공
      } else {
         return false; //로그인 실패
      }
   }

   @Override
   public User info(User userTb) {
      return logDao.selectMemberByUserid(userTb);
   }

   @Override
   public Business getLoginBusMember(HttpServletRequest req) {
      try {
         req.setCharacterEncoding("UTF-8");
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }
      
      Business business = new Business();
            
      business.setId( req.getParameter("id") );
      business.setPw( req.getParameter("pw") );
      
      return business;
   }

   @Override
   public boolean BusLogin(Business business) {
      if( logDao.selectCntBusMemberByUseridUserpw(business) > 0 ) {
         return true; //로그인 성공
      } else {
         return false; //로그인 실패
      }
   }

   @Override
   public Business busInfo(Business business) {
      return logDao.selectBusMemberByUserid(business);
   }


   @Override
   public User findIdUser(String name, String email) {
//      System.out.println("서비스에 값이 잘 들어왔나요? : " + name + email );
      int res = logDao.selectCntUser(name, email);
//      System.out.println("서비스에 카운트 일반 : "+res);
      if(res>0) {//테이블에 존재할 때
         //User테이블에서 사용자 정보 조회해서 반환
//         System.out.println("서비스에 들어온 정보 : "+logDao.selectUser(name, email));
         
         return logDao.selectUser(name, email);
      }
      //User테이블에 존재하지 않을때 null반환
      return null;
   }

   @Override
   public Business findIdBusiness(String name, String email) {
      System.out.println("서비스에 값이 잘 들어왔나요? : " + name + email );
      int res = logDao.selectCntBusiness(name, email);
//      System.out.println("서비스에 카운트 사업자 : "+res);
      if(res>0) {//테이블에 존재할 때
         //User테이블에서 사용자 정보 조회해서 반환
//         System.out.println("서비스에 들어온 정보 : "+logDao.selectBusiness(name, email));
         return logDao.selectBusiness(name, email);
      }
      //User테이블에 존재하지 않을때 null반환
      return null;
   }

   @Override
   public User findPwUser(HttpServletRequest req) {
      try {
         req.setCharacterEncoding("UTF-8");
      } catch (UnsupportedEncodingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      User user = new User();
      user.setId(req.getParameter("userId"));
      user.setName(req.getParameter("userName"));
      user.setMail(req.getParameter("email"));
      user.setUques(req.getParameter("select_Q"));
      user.setUansw(req.getParameter("answ"));
      
      System.out.println("서비스에 값이 잘 들어왔나요? user  : " + user );
            
      int res = logDao.selectCntUserPw(user);
      if(res > 0) {
         System.out.println("서비스에 들어온 정보 user : "+ logDao.selectUserPwInfo(user));
         return logDao.selectUserPwInfo(user);
      }
      
      return null;
   }

   @Override
   public Business findPwBusiness(HttpServletRequest req) {
      
      try {
         req.setCharacterEncoding("UTF-8");
      } catch (UnsupportedEncodingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      Business business = new Business();
      business.setId(req.getParameter("userId"));
      business.setName(req.getParameter("userName"));
      business.setMail(req.getParameter("email"));
      business.setQues(req.getParameter("select_Q"));
      business.setAnsw(req.getParameter("answ"));
      
      
      System.out.println("서비스에 값이 잘 들어왔나요? business : " + business );
      int res = logDao.selectCntBusinessPw(business);
      if(res > 0) {
         System.out.println("서비스에 들어온 정보  pw: " + logDao.selecttBusinessPwInfo(business));
         return logDao.selecttBusinessPwInfo(business);
      }
      
      return null;
   }

@Override
public Boolean loginAdmin(String id, String pw) {
	Admin admin = new Admin();
	admin.setId(id);
	admin.setPw(pw);
	
	System.out.println(admin);

	if( logDao.selectAdminLog(admin) > 0 ) {
        return true; //로그인 성공
     } else {
        return false; //로그인 실패
     }
}



}