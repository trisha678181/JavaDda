package web.service.face;

import javax.servlet.http.HttpServletRequest;

import web.dto.Business;
import web.dto.User;

public interface AccountService {

   /**
    * 회원가잊 정보 추출하기
    * 
    * @param req - 요청객체
    * @return UserTb - 추출한 회원가입 정보
    */
   public User getAccountMember(HttpServletRequest req);

   /**
    * 회원 가입 처리
    * 
    * @param acparam - 뢰원 가입 정보 객채
    */
   public void account(User acparam);

   /**
    * 사업자 회원 가입정보 추출하기
    * 
    * @param req - 요청갹채
    * @return Business - 추출한 사업자 회원가입 정보
    */
   public Business getAccountBusMember(HttpServletRequest req);

   /**
    * 사업자 회원 가입처리
    * 
    * @param acparam - 뢰원가입 정보 객체
    */
   public void busaccount(Business acparam);

   /**
    * 중복체크 아이디 받기
    * 
    * @param req - 요청 정보 객체
    * @return  UserTb - 중복 정보
    */
   public User getCheck(HttpServletRequest req);
   
   
   
   /**
    * 중복체트 아이디  정보 받기
    * @param req - 입력받은 아이디
    * @return Business - dto에 set한 아이디
    */
   public Business getBCheck(HttpServletRequest req);

   /**
    * 닉네임 중복 체크
    * 
    * @param usertb - 닉네임 정보
    * @return true - 인증됨, false - 인증되지 않음
    * 
    */
   public boolean checkNick(User usertb);


  
   /**
    * 
    * 두테이블에서 중복 찾아서 불린값으로 받아오기
    * 
    * @param userid
    * @param businessid
    * @return
    */
   public boolean checkId(String userid, String businessid);

   /**
    * 두테이블에서 중복 이메일 찾아 불린값으로 받아오기
    * 
    * @param mail
    * @param mail2
    * @return
    */
   public boolean checkEmail(String usermail, String busmail);

   
   /**
    * 사업자 번호 중복 체크
    * @param string
    * @return true >> 중복된 . false >> 중복되지 않은
    */
   public boolean checkBsnum(String bsnum);
}