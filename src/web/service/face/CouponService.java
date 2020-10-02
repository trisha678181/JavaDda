package web.service.face;

import java.util.List;

import web.dto.Coupon;

public interface CouponService {

	/**
	 * 장소에서 제공하는 쿠폰 조회
	 * @param pno
	 * @return - 하나여도 일단 List에 담아서 return
	 */
	public List<Coupon> SelectCouponPno(int pno);
	
	/**
	 * 쿠폰 추가
	 * @param coupon - 모든 내용 입력해서 집어넣도록
	 */
	public void InsertCoupon(Coupon coupon);
	
	/**
	 * 쿠폰 삭제
	 * @param coupon - 모든 내용 입력해서 집어넣도록
	 */
	public void DeleteCoupon(int cpno);
	

}
