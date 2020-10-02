package web.dao.face;

import java.util.List;
import java.util.Map;

import web.dto.Coupon;
import web.dto.HaveCoupon;
import web.dto.Location;
import web.dto.PImg;
import web.dto.Place;
import web.dto.Report;
import web.dto.Review;
import web.util.Paging;

public interface PlaceDao {
	
	//**문정*****************************************************************//
	
	// 장소 상세보기 **
		public Place SetPlaceNo(int pno); //사용
		
		/**
		 * Pno 장소의 Like 1 증가
		 * @param Pno
		 */
		public void UpdatePlaceLike(int Pno); //사용예정
		
		/**
		 * Pno 장소의 Like 1 감소
		 * @param Pno
		 */
		public void UpdatePlaceUnLike(int Pno); //사용예정

		/**
		 * 이미지에 사용될 Pno로 조회 **
		 * @param pno, pcode
		 * @return
		 */
		public PImg SetPlaceImg(int pno); //사용

		/**
		 * 이미지 슬라이드에 사용될 pimg List조회
		 * @param pno, pcode = 1
		 * @return
		 */
		public List<PImg> SelectPimgslideList(int pno); //사용

		/**
		 * 쿠폰보여줄 cimglist조회
		 * @param pno
		 * @return
		 */
		public List<Coupon> SetCouponImg(int pno); //사용

		/**
		 * 
		 * @param pno
		 * @return
		 */
		public Location selectJoinLoc(int pno); //사용

		/**
		 * pno제외하고 8개 가지고옴
		 * @param pno
		 * @return
		 */
		public List<Map<String, Object>> removePno(int pno); //사용
		
		 /**
	       * pno에해당하는 리뷰1개 갖고옴
	       * @param pno
	       * @return 
	       */
	      public List<Map<String, Object>> selectReviewOne(int pno); //사용
		
		/**
		 * 존재하는 상태 확인
		 * @param havecoupon
		 * @return
		 */
		public int selectResCoupon(HaveCoupon havecoupon);
		
		/**
		 * insert하기
		 * @return 
		 */
		public void insertHaveCoupon(HaveCoupon havecoupon);
		
		/**
		 * 신고추가
		 * @param targetpno
		 * @return
		 */
		public Report insertReport(int targetpno);
		

		
		//**해미*****************************************************************//
	
	/**
	 * 요즘 뜨는 카페
	 * @return - 3개 List에 담아 return
	 */
	public List<Map<String, Object>> SelectCafeTop3();
	
	/**
	 * 요즘 뜨는 액티
	 * @return - 3개 List에 담아 return
	 */
	public List<Map<String, Object>> SelectActTop3();
	
	public List<Map<String, Object>> SelectPick();
	
	//********************************************************//
	

	// 장소 리스트 출력
	public List<Map<String, Object>> SelectPlaceAll(Paging paging);
	
	// 장소 삭제
	public void DeletePlace(Place place);
	

	// 장소 추가
	public void insertPlace(Place place);
	
	public Map<String, Object> selectPlaceByPlaceno(Place placeno);
	
	public int selectPlacePlaceno();

	public void insertFile(PImg p);

	/**
	* 총 게시글 수 조회
	 * @return
	 */
	public int selectCntAll();

	
	/**
	 * 검색어를 이용한 총 게시글 수 조회
	 * 
	 * @return int - 총 게시글 수
	 */
	public int selectCntAll(String search);

	/**
	 * 관리자 5픽
	 * @param pname
	 */
	public void PlacePickList(String pname);
	
	//장소 수정
		public void UpdatePlace(Place place);
		//관리자 픽 삭제
		public void deletePlacePick(String names);


/*******************************지원 문정***********************************/

	/**
    * 쿠폰 존재 여부 확인
    * 
    * @param uno
    * @param cpno
    * @return 1>>존재 , 0>>존재x
    */
   public int selectResCoupon(int uno, int cpno);

   /**
    * 쿠폰이 존재하지 않을떄  저장
    * 
    * @param uno
    * @param cpno
    */
   public void insertHaveCoupon(int uno, int cpno);

   /**
    * lno별 place정보 리스트
    * @param lno -1 >> 강남역
    * @return - List
    */
   public List<Map<String, Object>> selectPlaceOfLocInfo(int lno);

   /**
    * ppcode로 필터된 장소 List select
    * 
    * @param lno - 1 >> 강남역
    * @param ppcode - 1>> cafe, 2>>restaurant, 3>> bar, 4>>실내, 5>>야외  
    * @return List 필터된 장소 list
    */
   public List<Map<String, Object>> selectPlaceOfLocInfoFilter(int lno, int ppcode, String search);
   
   public Place GetPlaceByName(String pname);
   
   public int likeplacebl(int pno, int uno);

}







