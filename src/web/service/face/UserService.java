package web.service.face;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dto.Board;
import web.dto.Coupon;
import web.dto.Place;
import web.dto.Review;
import web.dto.User;

public interface UserService {
	
	/**
	 * 회원 정보 수정 처리 
	 * @param User // Controller에서 회원 정보 저장 처리한 User 객체
	 * @return int // 0은 처리 X, 1은 처리 O
	 */
	public void UpdateUserInfo(User user);
	
	/**
	 *  회원 상세보기
	 * @param uno
	 * @return
	 */
	public User show(User uno);
	
	public void SetLikePlace(User user, Place place);
 
	
	/**
	 *  회원의 커플 상세보기
	 * @param uno
	 * @return
	 */
	public Map<String, Object> coupleshow(User uno);
	
	public User getUserNo(HttpServletRequest req);
	
	/**
	 * 가지고 있는 쿠폰 조회
	 * @param Uno
	 * @return List<Coupon>
	 */
	public List<Coupon> SelectCouponUno(int Uno);
	
	/**
	 * 작성한 리뷰 조회
	 * @param Uno
	 * @return List<Coupon>
	 */
	public List<Review> SelectReviewUno(int Uno);
	
	/**
	 * 작성한 게시글 조회
	 * @param Uno
	 * @return List<Board>
	 */
	public List<Board> SelectBoardUno(int Uno);
	
	/**
	 *  회원정보 가져오기
	 * @param uno
	 * @return
	 */
	public User GetUserInfo(int uno);
	
	public void SetUserInfo(HttpServletRequest req);
	
	/**
	 *  회원정보 가져오기
	 * @param uno
	 * @return
	 */
	public User GetOppoInfo(User user);
	
	/**
	 * 비번 체크
	 * @param pw
	 * @return
	 */
	public boolean checkPW(User user);
	
	/**
	 * 상대방 아이디 존재 체크
	 * @param pw
	 * @return
	 */
	public boolean checkOppoID(String ooppoID);
	
	//회원 상세내역 가져오기<board>
	public int Boardcount(User uno); 
	//회원 상세내역 가져오기<Review>
	public int Reviewcount(User uno);
	//회원 상세내역 가져오기 < like_place>
	public int Like_placecount(User uno);
	//회원 상세내역 가져오기< have_coupon>
	public int Have_couponcount(User uno); 
	
	public User GetUserById(String userid);
	
	public void SendMail(List<User> list);
	
	
	public boolean MakeCouple(String couplevalid, User user);
	
	public List<Map<String, Object>> GetCouponList(User user);
	
	public List<Map<String, Object>> GetBoardListByUser(User user);
	
	public List<Map<String, Object>> GetReviewListByUser(User user);
	
	public int[] PersonalLikePlace(List<Integer> likeplace, List<Integer> top3);


}
