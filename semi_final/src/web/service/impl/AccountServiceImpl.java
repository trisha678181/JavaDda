package web.service.impl;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.AccountDao;
import web.dao.impl.AccountDaoImpl;
import web.dto.Business;
import web.dto.User;
import web.service.face.AccountService;


public class AccountServiceImpl implements AccountService {

   private AccountDao accountDao =  new AccountDaoImpl();
   @Override
   public User getAccountMember(HttpServletRequest req) {
      try {
         req.setCharacterEncoding("UTF-8");
      } catch (UnsupportedEncodingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      User userTb = new User();
      userTb.setName(req.getParameter("inputInfoName"));
      userTb.setId(req.getParameter("userId"));
      userTb.setPw(req.getParameter("userPass"));
      userTb.setBirth(req.getParameter("birthYear")+"-"+req.getParameter("birthMonth")+"-"+req.getParameter("birthDate"));
      userTb.setNick(req.getParameter("userNick"));
      userTb.setGender(req.getParameter("inputInfoGender"));
      userTb.setPhone(req.getParameter("Phone"));
      userTb.setMail(req.getParameter("email"));
      userTb.setHot(Integer.parseInt(req.getParameter("hot")));
      userTb.setCold(Integer.parseInt(req.getParameter("cold")));
      userTb.setRain(Integer.parseInt(req.getParameter("rain")));
      userTb.setSnow(Integer.parseInt(req.getParameter("snow")));
      userTb.setUprofileimg(req.getParameter(""));
      userTb.setUbgimg(req.getParameter(""));
      userTb.setUques(req.getParameter("select_Q"));
      userTb.setUansw(req.getParameter("select_A"));
      
      
      
      
      return userTb;   
   }

   @Override
   public void account(User acparam) {
      accountDao.acinsert(acparam);
      
   }

   @Override
   public Business getAccountBusMember(HttpServletRequest req) {
      try {
         req.setCharacterEncoding("UTF-8");
      } catch (UnsupportedEncodingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      Business business = new Business();
      
      business.setId(req.getParameter("userId"));
      business.setPw(req.getParameter("userPass"));
      business.setName(req.getParameter("userName"));
      business.setBsnum(req.getParameter("bsnum"));
      business.setPhone(req.getParameter("Phone"));
      business.setBirth(""+ req.getParameter("birthYear")+"-"+req.getParameter("birthMonth")+"-"+req.getParameter("birthDate"));
      business.setGender(req.getParameter("inputInfoGender"));
      business.setMail(req.getParameter("email"));
      business.setQues(req.getParameter("select_Q"));
      business.setAnsw(req.getParameter("select_A"));
      
      
      
      
      return business;
   }

   @Override
   public void busaccount(Business acparam) {
      // TODO Auto-generated method stub
      accountDao.businsert(acparam);
      
   }

   @Override
   public User getCheck(HttpServletRequest req) {
      try {
         req.setCharacterEncoding("UTF-8");
      } catch (UnsupportedEncodingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      User usertb = new User();
      
      usertb.setId(req.getParameter("userId"));
      usertb.setNick(req.getParameter("userNick"));
      usertb.setMail(req.getParameter("email"));
//      System.out.println("req.getParameter(\"userNick\") : " +req.getParameter("userNick"));
//      System.out.println("req.getParameter(\"userNick\") : " +usertb);
               
      
      return usertb;
   }
   
   
   @Override
   public Business getBCheck(HttpServletRequest req) {
      try {
         req.setCharacterEncoding("UTF-8");
      } catch (UnsupportedEncodingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      Business business = new Business();
      
      business.setId(req.getParameter("userId"));
      business.setMail(req.getParameter("email"));
      business.setBsnum(""+req.getParameter("bsnum"));
      
      
      return business;
   }
   
   
   @Override
   public boolean checkId(String userid, String businessid) {
//	   System.out.println("서비스에 값이 잘 들어왔나요? : " + userid +","+ businessid );
	   int res = accountDao.cntUserId(userid);
	   int resb = accountDao.cntBusId(businessid);
//	   System.out.println("서비스에 카운트 일반 : "+res);
//	   System.out.println("서비스에 카운트 사업자 : "+resb);
	   if(res>0) {//테이블에 존재 할때
		   boolean exist = true;
		   return exist;
	   }else if(resb>0) {
		   boolean existb = true;
		   return existb;
	   }else {
		   return false;		   
	   }	   	  
   }


 

   @Override
   public boolean checkNick(User usertb) {
      if(accountDao.CheckcntByUserNick(usertb)>0) {
         return false;// 중복된 아이디
      }else { 
         return true;// 중복되지 않는 아이디 
      }
   }

@Override
public boolean checkEmail(String usermail, String busmail) {
	System.out.println("서비스에 값이 잘 들어왔나요? : " + usermail +","+ busmail );
	int res = accountDao.cntMail(usermail);
	int resb = accountDao.cntBmail(busmail);
	//	   System.out.println("서비스에 카운트 일반 : "+res);
	//	   System.out.println("서비스에 카운트 사업자 : "+resb);
	if(res>0) {//테이블에 존재 할때
		boolean exist = true;
		return exist;
	}else if(resb>0) {
		boolean existb = true;
		return existb;
	}else {
		return false;		   
	}
}

public boolean checkBsnum(String bsnum) {
//	System.out.println("service/bsnum : " + bsnum);
	if(accountDao.checkcntbusum(bsnum)>0) {
		System.out.println(bsnum);
        return true;// 중복된 아이디
     }else { 
        return false;// 중복되지 않는 아이디 
     }
}



//   @Override
//   public boolean checkBId(Business business) {
//      if(accountDao.CheckcntById(business)>0) {
    

}