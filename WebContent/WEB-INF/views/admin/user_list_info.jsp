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

<link href="../../../css/admin/user_list_info.css" rel="stylesheet" type="text/css" media="all" />
<link href="../../../css/admin/main.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="../../../js/admin/admin_main.js"></script>

</head>

<body>
<c:import url="../layout/header.jsp" />

<div id="adminBox"> <!-- 헤더와 푸터 사이에 공간 잡아주는 div -->

	<div id="admin_menu"> <!-- 메뉴바 div -->
		<ul>
		<li> <!-- class="current_page_item"> -->
		<a href="/admin/main"  ><img src="../../../images/profile.png" alt="profile" /> 회원관리 </a></li>
		<li><a href="#"><img src="../../../images/pencil.png" alt="pencil" /> 글관리</a>
		<ul>
		<li><a href="/admin/noticelist" >공지사항</a></li>
		<li><a href="/admin/reviewlist" >REVIEW</a></li>
		<li><a href="/admin/qnalist">1:1문의</a></li>
		</ul></li>
		<li><a href="#"><img src="../../../images/navigation.png" alt="navigation" /> 장소관리</a>
		<ul>
		<li><a href="/admin/placelist" >장소리스트</a></li>
		<li><a href="/admin/businesslist">사업자리스트</a></li>

		</ul></li>
		</ul>
	</div> <!-- 메뉴바 div END -->
	
	
	<div id="admin_main"> <!-- 네모 3개 div -->
			
		<div id="user_info"> <!-- 회원 정보 div -->
			<img id="info_img" src="<%request.getContextPath(); %>/upload/${list.uprofileimg }" /><br>
			<div id="info_nik">${list.name }</div>
				<b>아이디</b><br>
				${list.id }
				<hr id="info_hr"><br>
				<b>닉네임</b><br>
				${list.nick}
				<hr id="info_hr"><br>
				<b>성별</b><br>
				<c:choose>
					<c:when test="${list.gender eq 'm'}">
						남자
					</c:when>
					<c:when test="${list.gender eq 'f'}">
						여자
					</c:when>
				</c:choose>
				<hr id="info_hr"><br>
				<b>폰 번호</b><br>
				${list.phone }
				<hr id="info_hr"><br>
				<b>생일</b><br>
				${list.birth}
				<hr id="info_hr"><br>
				<b>이메일</b><br>
				${list.mail}
				<hr id="info_hr"> 
			
		</div> <!-- 회원 정보 div END -->
			
        <div id="user_info2"> <!-- 커플 정보 + 활동 내역 div -->
        
           <div id="info_couple">커플정보<br> <!-- 커플 정보 div -->
          		 상대방 이름 :<a href="/admin/userlistinfo?uno=${map['user'].uno }">${map["user"].name }</a><br>
              	상대방 ID :<a href=""> ${map["user"].id }</a>
              	<br>
              	사귄 날짜<br>
              	 ${map["couple"].firstday }<br>
              	<hr id="info_couple_hr">
           </div> <!-- 커플 정보 div END -->
           
           <div id="info_acti"> ${list.name }님 활동 내용<br> <!-- 활동 내역 div -->
     <hr id="info_hr">
            	게시글 쓴 개수 : ${boardcount }<br>
           		리뷰 쓴 개수 :  ${reviewcount } <br>
           		쿠폰 개수 :	${havecouponcount } <br>
           		찜 개수 : 	${like_placecount } <br>
           			
           </div> <!-- 활동 내역 div END -->
           
        </div> <!-- 커플 정보 + 활동 내역 div END-->
            
            
    </div> <!-- 네모 3개 div END-->
    
    
    <div id="admin_back_list"> <!-- 목록 버튼 div -->
	   <button type="button" onclick="location.href='/admin/main' ">목록</button>
	</div> <!-- 목록 버튼 div END -->
        
</div> <!-- END -->
    
<c:import url="../layout/footer.jsp" />

</body>

</html>


