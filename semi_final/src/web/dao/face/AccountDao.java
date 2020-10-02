package web.dao.face;


import web.dto.Business;
import web.dto.User;

public interface AccountDao {

	/**
	 * 회원 가입정보 삽입하기
	 * 
	 * @param acparam - 회원 가입정보 객체
	 */
	public void acinsert(User acparam);

	/**
	 * 사업자회+원 가입정보 삽입하기
	 * 
	 * @param acparam - 사업자회원 가입정보 객체
	 */
	public void businsert(Business acparam);

	/**
	 * userid가  일치하는 회원 수 조회
	 * 
	 * @param usertb - 조회할 회원 아이디
	 * @return int - 1존재하는 회원, 0존재하지 않는 회원, -1(에러)
	 */
	public int CheckcntByUserId(User usertb);

	/**
	 * usernick이  일치하는 회원 수 조회
	 * 
	 * @param usertb - 조회할 회원 아이디
	 * @return int - 1존재하는 회원, 0존재하지 않는 회원, -1(에러)
	 */
	public int CheckcntByUserNick(User usertb);

	/**
	 * 
	 * @param business
	 * @return
	 */
	public int CheckcntById(Business business);

	/**
	 * 
	 * @param usertb
	 * @return
	 */
	public int CheckMailcntById(User usertb);

	/**
	 * 
	 * @param business
	 * @return
	 */
	public int CheckMailcntById(Business business);

	/**
	 * user테이블에서 id가  존재하는지 확인
	 * 
	 * @param userid
	 * @return
	 */
	public int cntUserId(String userid);

	/**
	 * business테이블에서 id가 존재하는지 확인
	 * 
	 * @param businessid
	 * @return
	 */
	public int cntBusId(String businessid);

	/**
	 * user테이블에서 입력받은 메일이 존재하는지 확인
	 * 
	 * @param usermail - 입력받은 이메일
	 * @return 1 >> 존재 ,  0>> 존재하지 않음
	 */
	public int cntMail(String usermail);

	/**
	 * user테이블에서 입력받은 메일이 존재하는지 확인
	 * 
	 * @param busmail - 입력받은 이메일
	 * @return 1 >> 존재 ,  0>> 존재하지 않음
	 */
	public int cntBmail(String busmail);

	/**
	 * 사업자 번호 중복 확인
	 * @param bsnum
	 * @return 1>>존재, 0
	 */
	public int checkcntbusum(String bsnum);

	
	

}
