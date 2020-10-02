package web.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.LocationDao;
import web.dao.face.PlaceDao;
import web.dao.impl.LocationDaoImpl;
import web.dao.impl.PlaceDaoImpl;
import web.dto.Coupon;
import web.dto.HaveCoupon;
import web.dto.Location;
import web.dto.PImg;
import web.dto.Place;
import web.dto.Report;
import web.dto.Review;
import web.service.face.PlaceService;
import web.util.Paging;

public class PlaceServiceImpl implements PlaceService{
   
   private PlaceDao placeDao = new PlaceDaoImpl();
   private LocationDao locationDao = new LocationDaoImpl();
   
   //***문정************************************************************************//
   
   @Override
   public List<Map<String, Object>> getPlaceList(int pno) {
      return placeDao.removePno(pno);
   }
   
   @Override
   public Place getPlaceNo(int pno) { 
      return placeDao.SetPlaceNo(pno); 
   }
   
   @Override
   public PImg SelectMenuImg(int pno) {
      return placeDao.SetPlaceImg(pno); 
   }
   
   @Override
   public List<Coupon> selectCouponImg(int pno) {
      return placeDao.SetCouponImg(pno);
   }
   
   @Override
   public Location getLocationXY(int pno) {
      return placeDao.selectJoinLoc(pno);
   }
   
   @Override
   public List<Map<String, Object>> getReviewpno(int pno) {
      return placeDao.selectReviewOne(pno);
   }
   
   
   @Override 
   public List<PImg> SelectPimgSlide(int pno) { //**
         return placeDao.SelectPimgslideList(pno);
      
   }
   
   //***해미************************************************************************//
   
   @Override
   public List<Map<String, Object>> SelectCafeTop3() {
      
      return placeDao.SelectCafeTop3();
      
   }
   
   @Override
   public List<Map<String, Object>> SelectActTop3() {
      
      return placeDao.SelectActTop3();
   }
   
   @Override
   public List<Map<String, Object>> SelectPick() {
      
      return placeDao.SelectPick();
   }
   
   //***혜연**************************************************************************//
   
   @Override
   public Paging getPaging(HttpServletRequest req) {
      //전달파라미터 curPage 를 파싱한다
      String  param = req.getParameter("curPage");
      int curPage = 0;
      if(param != null && ! "".equals(param)) {
         curPage = Integer.parseInt(param);
      }

      //검색어
      String search = (String)req.getParameter("search");

      //Board테이블의 총 게시글 수를 조회한다
      int totalCount = placeDao.selectCntAll();

      //Paging 객체 생성 - 현재 페이지 ,총 게시글  활용
      Paging paging = new Paging(totalCount, curPage);

      //검색어
      paging.setSearch(search);

      //paging 리턴
      return paging;
   }

   @Override
   public List<Map<String, Object>> getList(Paging paging) {
      return placeDao.SelectPlaceAll(paging);
   }

   @Override
   public Place getPlaceNo(HttpServletRequest req) {
      //pno를 저장할 객체 생성
      Place pno = new Place();

      //pno 전달파라미터 검증 - null, ""
      String param = req.getParameter("pno");

      if(param != null && !"".equals(param)) {

         //pno 전달파라미터 추출
         pno.setPno(Integer.parseInt(param));
      }
      //결과 객체 반환
      return pno;
   }

   @Override
   public Map<String, Object> show(Place placeno) {
      //게시글 조회
      return placeDao.selectPlaceByPlaceno(placeno);
   }

   @Override
   public void DeletePlace(Place place) {
      placeDao.DeletePlace(place);

   }
   //쓰기
   @Override
   public void write(HttpServletRequest req) {
      Place place = new Place();

      try {
         req.setCharacterEncoding("utf-8");
      } catch (UnsupportedEncodingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      //      place.setPno( Integer.parseInt(req.getParameter("pno")));
      place.setLno( Integer.parseInt(req.getParameter("lno")));
      place.setPcode( Integer.parseInt(req.getParameter("pcode")));
      place.setPname( req.getParameter("pname") );
      place.setPcontent( req.getParameter("pcontent") );
      place.setPphone( req.getParameter("pphone") );
      place.setPloc( req.getParameter("ploc") );
      place.setPmenu_txt( req.getParameter("pmenu_txt"));
      place.setPoperate( req.getParameter("poperate"));
      place.setPparking( req.getParameter("pparking"));

      //      System.out.println("place servieimpl write: " + place);

      //작성자id 처리
      //      req.getSession().setAttribute("ano", 1);
      //      place.setAno((int)req.getSession().getAttribute("ano")); 
      placeDao.insertPlace(place);

      //   place.setPno( Integer.parseInt(req.getParameter("pno")));
      }

   @Override
   public PImg viewFile(Place placeno) {
      // TODO Auto-generated method stub
      return null;
   }
   
   //관리자 5픽
   @Override
   public void placeListPick(String pname) {
      placeDao.PlacePickList(pname);

   }
   
   @Override
   public boolean existcheck(HaveCoupon havecoupon) {
      int res = placeDao.selectResCoupon(havecoupon);
      
      System.out.println("placeservice-res : " +res);
      
      if(res>0) { //존재함
         return true; 
         
      } else { //존재안함
         return false;
      }
      
   }
   
   @Override
   public HaveCoupon getCoupon(HttpServletRequest req) {
      
      //이거는 coupon에서 갖고오는건데...
      int cpno = 0;
      String param = req.getParameter("cpno");
      if( param != null && !"".equals(param) ) {
         cpno = Integer.parseInt(param);
      }
      
      //로그인한 값 
      int uno = (int) req.getSession().getAttribute("uno");
      
      HaveCoupon havecoupon = new HaveCoupon();
      havecoupon.setCpno(cpno);
      havecoupon.setUno(uno); //세션
      
      System.out.println("placeService : " +cpno);
      System.out.println("placeService : " +uno);
      
      return havecoupon;
      
   }
   
   
   @Override
   public boolean couponHave(HaveCoupon havecoupon) {
      if(existcheck(havecoupon)) { //존재하는 상태
         //alert 출력하기
         System.out.println("placeservice - 이미 존재한 상태"); //테스트용
         return false;
         
      } else { //존재하지않은 상태
         placeDao.insertHaveCoupon(havecoupon);
         return true;
      }
      
   }
   
   //신고버튼
   @Override
   public Report getReportPno(int targetpno) {
      return placeDao.insertReport(targetpno);
   }
   
   //장소 수정
      @Override
      public void UpdatePlaceNo(HttpServletRequest req) {
         Place place = new Place();
          
         place.setPno(Integer.parseInt(req.getParameter("pno")));
         place.setPcontent( req.getParameter("pcontent") );
         place.setPphone( req.getParameter("pphone") );
         place.setPloc( req.getParameter("ploc") );
         place.setPmenu_txt( req.getParameter("pmenu_txt"));
         place.setPoperate( req.getParameter("poperate"));
         place.setPparking( req.getParameter("pparking"));
         if(place != null) {
         placeDao.UpdatePlace(place);   
         }
         
      }

      //관리자픽 삭제
      @Override
      public void placeDelectPick(String names) {
         placeDao.deletePlacePick(names);
         
      }
      /*****************************200625****************************/
      @Override
      public boolean logchk(HttpServletRequest req) {
         System.out.println("2-1 session확인 : "+ req.getSession().getAttribute("login"));
         if( req.getSession().getAttribute("login") != null) {
       	  return (boolean) req.getSession().getAttribute("login");
         }
   	  return false;
       }

      @Override
      public int couponHave(int uno, int cpno) {
         System.out.println("6 couponhave service >> " + uno +"," + cpno);
         
         if(existcheck(uno, cpno)) { //존재하는 상태
            //alert 출력하기
            System.out.println("11 placeservice - 쿠폰 이미 존재한 상태"); //테스트용
            return 2;
            
         } else { //존재하지않은 상태
            System.out.println("11 placeservice - 쿠폰  없음 >> 저장(insert)"); 
            placeDao.insertHaveCoupon(uno, cpno);
            return 3;
         }
         
      }

      private boolean existcheck(int uno, int cpno) {
         //쿠폰 존재 여부 확인
         System.out.println("7 service/existcheck uno, cpno>> "+uno+","+cpno);
         
         int res = placeDao.selectResCoupon(uno, cpno);
         
         System.out.println("9 쿠폰 존재 여부 : " +res);
         
         if(res>0) { //존재함
            System.out.println("10 쿠폰 존재 " );
            return true; 
            
         } else { //존재안함
            System.out.println("10 쿠폰 존재하지 않음" );
            return false;
         }
        
         
         
      }

   //********************************main2****************************//
      
      //Lno별 장소 정보 리스트 가져오기
   	@Override
   	public List<Map<String, Object>> getPlaceOfLoc(int lno) {
//   		System.out.println("lno >> " + lno);
   		return placeDao.selectPlaceOfLocInfo(lno);
   	}

   	//lno 카테고리별 정보 리스트
   	@Override
   	public List<Map<String, Object>> getPlaceOfLocFilter(int lno, int ppcode, String search) {
//   		System.out.println("lno >> " + lno + ", ppcode >> "+ ppcode);
   		return placeDao.selectPlaceOfLocInfoFilter(lno, ppcode,search);
   	}
   	
   	
   	@Override
	public Place GetPlaceByName(String pname) {
		return placeDao.GetPlaceByName(pname);
	}
   	
   	@Override
   		public boolean likeplacebl(int pno, int uno) {
   	      if(placeDao.likeplacebl(pno, uno)>0) { //존재함
   	         return true; 
   	      } else { //존재안함
   	         return false;
   	      }
   		}

}