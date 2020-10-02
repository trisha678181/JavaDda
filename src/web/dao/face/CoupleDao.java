package web.dao.face;

import java.util.List;
import java.util.Map;

import web.dto.Anniversary;
import web.dto.Place;
import web.dto.User;

public interface CoupleDao {
	
	/* DDAY */
	/**
	 * 사귄 첫 날 조회
	 * @param 
	 */
	public String SelectCoupleFirstDay(User user);
	
	/**
	 * 커플 이름 2개 조회
	 * @param 
	 */
	public String[] SelectCoupleName(User user);
	
	/**
	 * 커플 두 명의 생일 조회
	 * @param uno 
	 * @return
	 */
	public String[] SelectCoupleBirth();
	
	/**
	 * 커플 맺기
	 * @param mno - 남자 번호
	 * @param wno - 여자 번호
	 */
	public void MakeCouple(User user);
	
	public void MakeCoupleFirstday(String firstday, User user);
	
	/**
	 * 커플 끊기
	 * @param Uno - 여자든 남자든 한명의 회원의 번호로
	 */
	public void BreakCouple(int uno);
	
	public List<Anniversary> GetAnni(int uno);
	
	public int AreYouCouple(User user);
	
	public void setValidNum(String u, List<User> list);
	
	public int RequiredCouple(int uno);
	
	public void DeleteCoupleValid(String couplevalid);
	
	public List<Map<String, Object>> GetLikePlaceList(User user);
	
	public List<Integer> GetLikePlaceListNo(User user);
	
	public List<Integer> GetTop3ListNo();
	
	public List<Integer> GetLikePlace(User user);
	
	public void SetLikePlaceTogether(int pno);
	
	public void DeleteLikePlaceTogether(int pno, User user);
	
	public List<Anniversary> GetAnni(User user);
	
	public void ExitCouple(User user);
	
	
	public int AddAnni(String annititle, String annidate, User user);
	
	public int EditAnni(String annititle, String annidate, String old_annititle, User user);

	public int DeleteAnni(String annititle, String annidate, User user);
	
	public void DeleteLikePlace(Place place, User user);
}
