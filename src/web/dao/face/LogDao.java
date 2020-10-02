package web.dao.face;


import javax.servlet.http.HttpServletRequest;

import web.dto.Admin;
import web.dto.Business;
import web.dto.User;

public interface LogDao {

	/**
	 * userid와 upw가 일치하는 회원의 수 조회
	 * 
	 * @param userTb - 조회할 회원의 정보
	 * @return int - 1(존재하는회원), 0(존재하지 않는 회원), -1(에러)
	 */
	public int selectCntMemberByUseridUserpw(User userTb);

	/**
	 * userid를 이용해 회원 정보 조회 >> 닉네임 등 세션 유지시 필요한 정보들
	 * 
	 * @param userTb - 조회할 회원
	 * @return UserTb - 조회된 결과 객체
	 */
	public User selectMemberByUserid(User userTb);
	
	/**
	 * bid와 bpw가 일치하는 회원의 수 조회
	 * 
	 * @param userTb - 조회할 회원의 정보
	 * @return int - 1(존재하는회원), 0(존재하지 않는 회원), -1(에러)
	 */
	public int selectCntBusMemberByUseridUserpw(Business business);

	
	/**
	 * bid를 이용해 회원 정보 조회 
	 * @param business - 조회할 회원
	 * @return Business - 조회된 결과 객체
	 */
	public Business selectBusMemberByUserid(Business business);

//	/**
//	 * userid와 uemail이 일치하는 회원의 수 조회
//	 * 
//	 * @param userTb - 조회할 회원의 정보
//	 * @return int - 1(존재하는회원), 0(존재하지 않는 회원), -1(에러)
//	 */
//	public int selectCntFindIdByUnameUemail(User userTb);
//	
//	
//	/**
//	 * bid와 bemail이 일치하는 회원의 수 조회
//	 * 
//	 * @param business - 조회할 회원의 정보
//	 * @return int - 1(존재하는회원), 0(존재하지 않는 회원), -1(에러)
//	 */
//	public int selectCntFindBusIdByBnameBemail(Business business);
//
//	/**
//	 * bid를 이용해 회원 이름 아이디 조회
//	 * @param business - 조회할 회원 
//	 * @return Business - 조회된 결과 객체
//	 */
//	public Business selectBusIDNameInfoByBid(Business business);
//
//	/**
//	 * userid를 이용해 회원 이름 이메일조회
//	 * 
//	 * @param userTb - 조회할 회원
//	 * @return UserTb - 조회된 결과 객체
//	 */
//	public User selectIDNameInfoById(User userTb);
//
//	/**
//	 * uname, userid, umail, uansw, uques이 일치하는 회원의 수 조회
//	 * 
//	 * @param userTb - 조회할 회원의 정보
//	 * @return int - 1(존재하는회원), 0(존재하지 않는 회원), -1(에러)
//	 */
//	public int selectCntFindPw(User userTb);
//
//	/**
//	 * bname, bid, bmail, bansw, bques이 일치하는 회원의 수 조회
//	 * 
//	 * @param business - 조회할 회원의 정보
//	 * @return int - 1(존재하는회원), 0(존재하지 않는 회원), -1(에러)
//	 */
//	public int selectCntFindBusPw(Business business);
//
//	
//	/**
//	 * bid를 이용해 회원 이름 패스워드 조회
//	 * @param business - 조회할 회원 
//	 * @return Business - 조회된 결과 객체
//	 */
//	public Business selectBusPwNameInfoByBid(Business business);
//
//	
//	/**
//	 * userid를 이용해 회원 이름 패스워드조회
//	 * 
//	 * @param userTb - 조회할 회원
//	 * @return UserTb - 조회된 결과 객체
//	 */
//	public User selectPwNameInfoById(User userTb);

	/**
	 * 존재하는 User인지 검사
	 * @param name - 조회할 이름
	 * @param email - 조회할  이메일
	 * @return int - 중복회원이 있는가? 0->x 1->o
	 */
	public int selectCntUser(String name, String email);

	/**
	 * User테이블에서 이름과 이메일로 사용자 정보조회해서 id까지 같이 반환
	 * @param name - 조회할 이름
	 * @param email - 조회할 이메일
	 * @return User - 조회된 정보 
	 */
	public User selectUser(String name, String email);

	/**
	 * 존재하는 Business인지 검사
	 * @param name - 조회할 이름
	 * @param email - 조회할  이메일
	 * @return int - 중복회원이 있는가? 0->x 1->o
	 */
	public int selectCntBusiness(String name, String email);

	/**
	 * Business테이블에서 이름과 이메일로 사용자 정보조회해서 id까지 같이 반환
	 * @param name - 조회할 이름
	 * @param email - 조회할 이메일
	 * @return Business - 조회된 정보 
	 */
	public Business selectBusiness(String name, String email);

	/**
	 * User에서 입력정보와 일치하는 데이터 찾기
	 * @param user - 입력정보
	 * @return User - 조회된 정보
	 */
	public int selectCntUserPw(User user);

	/**
	 * Business 에서 입력정보와 일치하는 데이터 찾기
	 * @param business - 입력정보
	 * @return Business - 조회된 정보
	 */
	public int selectCntBusinessPw(Business business);

	/**
	 * User 정보 조회 id pw
	 * @param user - 입력값 
	 * @return User - 조회된 정보
	 */
	public User selectUserPwInfo(User user);

	/**
	 * Business 정보 조회 id pw
	 * @param business - 입력값 
	 * @return Business - 조회된 정보
	 */
	public Business selecttBusinessPwInfo(Business business);

	
	public int selectAdminLog(Admin admin);
	
	

}
