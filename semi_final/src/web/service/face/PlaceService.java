package web.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dto.Coupon;
import web.dto.HaveCoupon;
import web.dto.Location;
import web.dto.PImg;
import web.dto.Place;
import web.dto.Report;
import web.dto.Review;
import web.util.Paging;

public interface PlaceService {
   
   //장소 리스트
   public List<Map<String, Object>> getPlaceList(int pno); //사용
   
   // 장소 상세보기
   public Place getPlaceNo(int pno); //사용
   
   /**
    * PNO 장소의 이미지 조회 - PLACE의 PNO와 P_IMAGE의 PNO 조인해서
    * @param pno
    * @return - 객체를 List에 담아 return
    */
   public List<PImg> SelectPimgSlide(int pno); //사용
   
   /**
    * PNO 장소의  메뉴 이미지(pcode=2인거) 조회
    * @param Pno
    * @return
    */
   public PImg SelectMenuImg(int pno); //사용

   /**
    * pno가지고 와서 쿠폰 이미지 조회
    * @param pno
    * @return
    */
   public List<Coupon> selectCouponImg(int pno); //사용

   /**
    * place랑 location 조인해서 테이블 조회
    * @param pno
    * @return
    */
   public Location getLocationXY(int pno); //사용

   /**
    * 리뷰가지고오기
    * @param pno
    * @return
    */
   public List<Map<String, Object>> getReviewpno(int pno); //사용
   
   /**
    * 쿠폰 존재 상태 조회
    * @param havecoupon - 테이블에서 쿠폰 존재여부 확인
    * @return boolean - true: 존재함 false : 존재안함
    */
   public boolean existcheck(HaveCoupon havecoupon);
   
   /**
    * 
    * @param req - 요청정보 객체
    * @return Coupon(HttpServletRequest req)
    */
   public HaveCoupon getCoupon(HttpServletRequest req);
   
   /**
    * 쿠폰 저장
    * @param havecoupon
    * @return 
    */
   public boolean couponHave(HaveCoupon havecoupon);
   
   /**
    * 신고하기 보냄
    * @param 
    * @return
    */
   public Report getReportPno(int targetpno);
   
   //****************************************************************************//
   
   public List<Map<String, Object>> SelectCafeTop3();
   
   public List<Map<String, Object>> SelectActTop3();

   public List<Map<String, Object>> SelectPick();
   
   //****************************************************************************//
   
   //**************************************************** 관리자
   
   // 페이지 객체 생성
   public Paging getPaging(HttpServletRequest req);
    
   // 장소 리스트
   public List<Map<String, Object>> getList(Paging paging);

   // 장소 상세보기
   public Map<String, Object> show(Place placeno);
   
   //
   public Place getPlaceNo(HttpServletRequest req);
   
   
   public Place GetPlaceByName(String pname);
   
   // 장소 삭제
   public void DeletePlace(Place place);
   
   //장소 추가
   public void write(HttpServletRequest req);

   public PImg viewFile(Place placeno);
   
   //관리자 장소 5개 픽
   public void placeListPick(String pname);
   /**
    * 로그인확인
    * @return true >>  로그인상태   false>> 비로그인상태
    */
   public boolean logchk(HttpServletRequest req);

   /**
    * 쿠폰 상태 확인
    * 
    * @param uno - 현재 로그인한 uno
    * @param cpno - 현재 페이지의  쿠폰
    * @return 2 >> 이미 존재하는 쿠폰 , 3>> 쿠폰 insert
    */
   public int couponHave(int uno, int cpno);

   /**
    * Lno 별 장소 가져오기
    * 
    * @param i - 1 >> 강남역
    * @return List
    */
   public List<Map<String, Object>> getPlaceOfLoc(int lno);
   
   /**
    * 카테고리 별 장소 
    * 
    * @param lno - 지역코드
    * @param ppcode - 카테고리 no. : 1>> cafe, 2>>restaurant, 3>> bar, 4>>실내, 5>>야외  
    * @param`search - 검색
    * @return List - 장소정보 list
    */
   public List<Map<String, Object>> getPlaceOfLocFilter(int lno, int ppcode, String search);
   
   //****************************************************
   
   //장소 수정
      public void UpdatePlaceNo(HttpServletRequest req);
      //관리자 픽 삭제
      public void placeDelectPick(String names);
      
      public boolean likeplacebl(int pno, int uno);

}