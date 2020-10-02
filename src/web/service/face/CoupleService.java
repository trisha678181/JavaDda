package web.service.face;

import java.util.List;
import java.util.Map;

import web.dto.Anniversary;
import web.dto.Place;
import web.dto.User;

public interface CoupleService {
	
	/* DDAY */
	/**
	 * 사귀고 있는 날짜 계산
	 * @param uno - 회원번호
	 */
	public int SelectCoupleFirstDay(User user);
	
	/**
	 * 커플 이름 조회
	 * 
	 */
	public String[] SelectCoupleName(User user);
	
	/**
	 * 디데이에 출력할 날짜들 계산
	 * @param
	 * @return - 배열로 담아 return
	 */
	public List<Anniversary> CalcDday(String[] names, String[] births, User user);
	
	/**
	 * 커플 두 명의 생일 조회
	 * @param uno 
	 * @return
	 */
	public String[] SelectCoupleBirth();
	
	/**
	 * 커플 맺기
	 * @param Mno - 여자 번호
	 * @param Wno - 남자 번호
	 */
	public void MakeCouple(User user);
	
	public void MakeCoupleFirstday(String firstday, User user);
	
	/**
	 * 기념일 가져오기
	 */
	public List<Anniversary> GetAnni(User user);
	
	public int AreYouCouple(User user);
	
	public int RequiredCouple(int uno);
	
	public void DeleteCoupleValid(String couplevalid);
	
	public void SetLikePlaceTogether(User user);
	
	public List<Map<String, Object>> GetLikePlaceList(User user);
	
	public List<Integer> GetLikePlaceListNo(User user);
	
	public List<Integer> GetTop3ListNo();
	
	public void ExitCouple(User user);

	public boolean AddAnni(String annititle, String annidate, User user);
	
	public boolean EditAnni(String annititle, String annidate, String old_annititle, User user);
	
	public boolean DeleteAnni(String annititle, String annidate, User user);
	
	public void DeleteLikePlace(Place place, User user);
}
