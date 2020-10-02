package web.controller.place;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.service.face.PlaceService;
import web.service.impl.PlaceServiceImpl;

@WebServlet("/coupon/insert")
public class CouponInsertController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   PlaceService placeService = new PlaceServiceImpl(); 
   
   @Override
     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
      
     }
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      resp.setCharacterEncoding("UTF-8");
      resp.setContentType("application/json;charset=utf-8");
      System.out.println("1. CouponInsertcontroller : "+req.getParameter("cpno"));
      System.out.println("2 session확인 : "+req.getSession().getAttribute("genlogin") );


      //cpno 받아오기
      int cpno = Integer.parseInt(req.getParameter("cpno"));

      //로그인 여부 확인하기
      boolean checklog = placeService.logchk(req);
      System.out.println("4 service 로그인 상태 : " + checklog);

      int res = 0;
      String msg = "";
      if(checklog) {
         int uno = (int) req.getSession().getAttribute("uno");
         System.out.println("5 service/ uno >> " + uno);

         //쿠폰 상태 검사  
         res = placeService.couponHave(uno, cpno);

         //쿠폰 저장
         System.out.println("12  쿠폰상태  >> " + res);

         if(res == 2) { //쿠폰이미 존재
            msg= "이미 존재하는 쿠폰입니다!";
         } else if(res == 3) { //쿠폰 존재 안하고 insert
            msg= "발급완료!";
         }

      }else {
         System.out.println("5 sevice 비로그인 상태");
         res=1;
         msg = "로그인이 필요한 서비스입니다!";

      }


      Map<String, Object> jsonMap = new HashMap<>();
      jsonMap.put("checklog", checklog);
      jsonMap.put("msg", msg);
      jsonMap.put("res", res);
      
      resp.getWriter().println(new Gson().toJson(jsonMap));

   }


}