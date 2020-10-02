package web.dao.face;

import java.util.List;
import java.util.Map;

import web.dto.Board;
import web.dto.Coupon;
import web.dto.Place;
import web.dto.Review;
import web.dto.User;

public interface UserDao {
	
	/**
	 * UPDATE 특정 회원 정보
	 * @param User // Controller에서 회원 정보 저장 처리한 User 객체
	 * @return int // 0은 처리 X, 1은 처리 O
	 */
	public void UpdateUserOne(User user);
	
	/**
	 * 가지고 있는 쿠폰 가져오기
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
	 * @return List<Coupon>
	 */
	public List<Board> SelectBoardUno(int Uno);
	
	public User selectUserByUno(User uno);
	
	public Map<String, Object> selectCoupleByUno(User uno);
	
	public User GetUserInfo(int uno);
	
	public User GetOppoInfo(User user);
	
	public int checkPW(User user);
	
	public int checkOppoID(String ooppoID);
	
	public void SetLikePlace(User user, Place place);
	
	public void setNewPW(User user);
	
	public void setNewInfo(User user);
	
	public void setPFImg(User user);
	
	public int Boardcount(User uno);

	public int Reviewcount(User uno);

	public int Like_placecount(User uno);

	public int Have_couponcount(User uno);
	
	public User GetUserById(String userid);
	
	public int MakeCouple(String couplevalid,User user);

	public List<Map<String, Object>> GetCouponList(User user);
	
	public List<Map<String, Object>> GetBoardListByUser(User user);
	
	public List<Map<String, Object>> GetReviewListByUser(User user);

	
	

}
