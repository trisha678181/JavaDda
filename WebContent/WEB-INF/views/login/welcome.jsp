<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />
<link rel="stylesheet" type="text/css" href="../../../css/login/welcome.css">
<link rel="stylesheet" type="text/css" href="../../../css/login/account_footer.css">
<meta charset="UTF-8">
<title>내손을 Java-가입완료</title>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"  %>
</head>
<body id="chs_account_type">

<div class="ac_warp" id="ac_warp">

	<div id="header">
		<a href = "/place/main" class = "logo">
			<img src = "../../../images/whiteLogoCool2.png" width="160" height="65"/>
		</a>
	</div>
		
	<div id="ac_01_container">
		<p id="title_choose">가입완료</p>
		
		
		<div id="welcome_id" ><p><a style="font-size: 25px">" ${result.name } "</a> 님<br> 환영합니다!</p></div>
		
		<div id="go_to_login">
		<p><a href="/">우리 데이트 하러 갈래요?</a></p>
		
		</div>
	
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