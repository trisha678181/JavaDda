<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />
<link rel="stylesheet" type="text/css" href="../../../css/login/find_log.css">
<link rel="stylesheet" type="text/css" href="../../../css/login/account_footer.css">
<meta charset="UTF-8">

<title>비밀번호 찾기</title>

</head>
<body id="find" class="find">
   <div class="warp" id="warp">
      <div id="header">
         <a href="/place/main" class="logo"> <img
            src="../../../images/whiteLogoCool2.png" width="160" height="65" />
         </a>
      </div>

      <div id="find_container">
         <form id="find_form" action="/find/password" method="post">
            <p id="find_title">비밀번호 찾기</p>
            
            <div class="box_info_write">
               <dl class="write_input">
               
               <dt class="info_id">
                        <label class="title" for="inputInfoID">아이디</label> <span
                           class="red_bullet">필수 항목</span>
                     </dt>
                     <dd>
                        <span class="text_box"> <!-- on : 활성화 --> 
                        <input type="text" id="inputInfoID" class="input_text valid mustValid"
                           maxlength="12" name="userId" placeholder="6-12자의 영문 소문자와 숫자">

                        </span>

                        <div class="text_alert" style="display: none;">
                           <p>6~12자의 영문 소문자와 숫자를 사용해주세요</p>
                        </div>
                     </dd>
               
                  <dt class="info_id">
                     <label class="title" for="inputInfoID">이름</label> <span
                        class="red_bullet">필수 항목</span>
                  </dt>
                  <dd>
                     <span class="text_box"> <input type="text"
                           id="inputInfoName" class="input_text valid mustValid"
                           name="userName" placeholder="이름을 적어주세요.">
                        </span>
                        <div class="text_alert" style="display: none;">
                           <p>이름을 입력해 주세요</p>
                        </div>
                  </dd>

                  
                  <dt class="info_email">
                        <label class="title" for="inputInfoEmail">이메일</label> <span
                           class="red_bullet">필수 항목</span>
                     </dt>
                     <dd>
                        <span class="text_box"> 
                        <input type="text"id="inputInfoEmail" class="input_text valid mustValid"
                           name="email" placeholder="이메일 주소를입력하세요"/> 
                        </span>
                        <div class="text_alert" style="display: none;">
                           <p>이메일 형식이 잘못되었습니다</p>
                        </div>
                     </dd>
                     
                     
                     <dt class="info_email">
                        <label class="title" for="inputInfoQues">나의 질문 :</label> <span
                           class="red_bullet">필수 항목</span>
                     </dt>
                     <dd class = "my_Q">
                        <span class="text_box">
                           <select class="select_Q" id="select_Q" name="select_Q">
                           <option selected="selected">질문을 입력해주세요.</option>
                           <option>내가 태어난 곳은?</option>
                           <option>나의 가장 친한친구는?</option>
                           <option>나의 소중한 보물 1호는?</option>
                           <option>내가 가장 좋아하는 음식은?</option>
                           <option>나의 꿈은?</option>
                           
                           </select>
                        </span>
                        
                     </dd>
                     <dd>
                        <span class="text_box"> 
                        <input type="text"id="inputInfoansw" class="input_text valid mustValid"
                           name="answ" placeholder="답변을 입력해 주세요."/> 
                        </span>
                     
                     </dd>
               </dl>

            </div>
            <div class="btn_bottom">
                  <button type="submit" class="btn_submit" id="btnNext">비밀번호 찾기</button>
            </div>
         </form>
      </div>



      <div id="footer">

         <ul class="footer_menu">
            <li><a href="http://policy..com/terms/">이용약관</a></li>
            <li><a href="http://policy..com/info"><strong>개인정보처리방침</strong></a></li>
            <li><a href="http://www.rnet.com/" target="_blank"><strong>©ZUMinternet</strong></a></li>
         </ul>
      </div>
   </div>

</body>
</html>