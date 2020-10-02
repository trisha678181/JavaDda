package web.service.face;

import javax.servlet.http.HttpServletRequest;

import web.dto.Admin;
import web.dto.Business;
import web.dto.User;


public interface LogService {

   /**
    * 로그인 정보 추출
    * 
    * @param req - 요청 정보 객채
    * @return UserTb - 로그인 정보
    */
   public User getLogingenMember(HttpServletRequest req);

   
   /**
    * 일반회원 로그인 처리
    * 
    * @param userTb - 일반회원 로그인 정보
    * @return true - 인증됨, false - 인증되지 않음
    */
   public boolean genLogin(User userTb);

   /**
    * 유저정보 가져오기
    * 
    * @param userTb - 일반회원 아이디를 가진 객체
    * @return UserTb - 조회된 일반회원 정보
    */
   public User info(User userTb);

   
   /**
    * 사업자로그인 정보 추출
    * @param req
    * @return Business - 사업자 로그인 정보
    */
   public Business getLoginBusMember(HttpServletRequest req);


   /**
    * 사업자회원 로그인 처리
    * 
    * @param business - 사업자회원 로그인 정보
    * @return true - 인증됨, false - 인증되지 않음
    */
   public boolean BusLogin(Business business);


   /**
    * 사업자회원 정보 가져오기
    * 
    * @param business - 사업자회원 아이디를 가진 객체
    * @return Business - 조회된 사업자회원 정보
    */
   public Business busInfo(Business business);



   /**
    * 이름과 이메일을 통해 User의 id를 찾음
    * @param name - 입력받은 이름
    * @param email - 입력받은 이메일
    * @return User - 이름, 이메일
    */
   public User findIdUser(String name, String email);


   /**
    * 이름과 이메일을 통해 Business의 id를 찾음
    * @param name - 입력받은 이름
    * @param email - 입력받은 이메일
    * @return Business -  조회된 이름 , 이메일
    */
   public Business findIdBusiness(String name, String email);


   /**
    * 입력값을통해 User에서 pw를 찾음
    * @param req - 입력정보
    * @return User - 조회된 pw 아이디
    */
   public User findPwUser(HttpServletRequest req);

   /**
    * 입력값을 통해 Business에서 pw를 찾음
    * @param req - 입력 정보
    * @return Business - 조회된  pw아이디
    */
   public Business findPwBusiness(HttpServletRequest req);

   public Boolean loginAdmin(String id, String pw);
     

}