<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />
<meta charset="UTF-8">

<script type="text/javascript"
   src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<title></title>

<link href="../../../css/admin/user_list_info.css" rel="stylesheet"
   type="text/css" media="all" />
<link href="../../../css/admin/main.css" rel="stylesheet"
   type="text/css" media="all" />
<script type="text/javascript" src="../../../js/admin/admin_main.js"></script>
<style type="text/css">
/* th, td {
   border-bottom: 1px solid #cccc;
} */

  table :before{
    width: 100%;
      border-left : 1px solid #ccc; 
    
  }
  th, td {
    padding: 15px;
  }
  td::after {
  border-left : 1px solid #ccc; 
 }

</style>

</head>

<body>
   <c:import url="../layout/header.jsp" />

   <div id="adminBox">
      <!-- 헤더와 푸터 사이에 공간 잡아주는 div -->

      <div id="admin_menu">
         <!-- 메뉴바 div -->
         <ul>
            <li>
               <!-- class="current_page_item"> --> <a href="/admin/main"><img
                  src="../../../images/profile.png" alt="profile" /> 회원관리 </a>
            </li>
            <li><a href="#"><img src="../../../images/pencil.png"
                  alt="pencil" /> 글관리</a>
               <ul>
                  <li><a href="/admin/noticeView">공지사항</a></li>
                  <li><a href="/admin/reviewlist">REVIEW</a></li>
                  <li><a href="/admin/qnalist">1:1문의</a></li>
               </ul></li>
            <li><a href="#"><img src="../../../images/navigation.png"
                  alt="navigation" /> 장소관리</a>
               <ul>
                  <li><a href="/admin/placelist">장소리스트</a></li>
                  <li><a href="/admin/businesslist">사업자리스트</a></li>

               </ul></li>
         </ul>
      </div>
      <!-- 메뉴바 div END -->


      <div id="admin_main">
         <!-- 네모 3개 div -->


         <div id="user_info" style="width: 600px" >
            <!-- 회원 정보 div -->
            <img id="info_img" src="<%request.getContextPath(); %>/upload/${list.pimg.storedName }" style="margin-left: 200px" /><br>
            <%-- <img id="info_img" src="<%request.getContextPath(); %>/upload/${list.uprofileimg }" /> --%>
            <br>
            <div id="info_nik">${list['business'].name }</div>
            <br>
            
            <table >
               <tr>
                  <th>아이디</th>
                  <td>${list['business'].id }</td>
                  <th>폰 번호</th>
                  <td>${list['business'].phone }</td>
               </tr>
               <tr>
                  <th>사업자 번호</th>
                  <td>${list['business'].bsnum }</td>
                  <th>사업자 생일</th>
                  <td>${list['business'].birth }</td>
               </tr>
               <tr>
                  <th>사업자 성별</th>
                  <td><c:choose>
                        <c:when test="${list['business'].gender eq 'f'}">
                              여성
                           </c:when>
                        <c:when test="${list['business'].gender eq 'm'}">
                              남성
                           </c:when>
                     </c:choose></td>
                  <th>사업자 메일</th>
                  <td>${list['business'].mail }</td>
               </tr>
               <tr>
                  <th> store 이름</th>
                  <td colspan="2">${list['place'].pname }</td>
               </tr>
            </table>
         </div>
         <!-- 회원 정보 div END -->




      </div>
      <!-- 네모 3개 div END-->

      <br>
      <div id="admin_back_list">
         <!-- 목록 버튼 div -->
         <button type="button" onclick="location.href='/admin/businesslist' ">목록</button>
      </div>
      <!-- 목록 버튼 div END -->

   </div>
   <!-- END -->

   <c:import url="../layout/footer.jsp" />

</body>

</html>

