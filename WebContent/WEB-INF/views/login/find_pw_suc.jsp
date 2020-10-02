<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />
<link rel="stylesheet" type="text/css" href="../../../css/login/find_log_suc.css">
<link rel="stylesheet" type="text/css" href="../../../css/login/account_footer.css">
<meta charset="UTF-8">

<title>비밀번호 찾기</title>

</head>
<body id="find_log_suc">

<div class="ac_warp" id="ac_warp">

	<div id="header">
		<a href = "/place/main" class = "logo">
			<img src = "../../../images/whiteLogoCool2.png" width="160" height="65"/>
		</a>
	</div>

	<div id="find_container">
		<p id="title_choose">비밀번호 찾기</p>
		<c:if test="${result ne null}">
		<div id="welcome_id" ><p>" <a style="color : #DE6562">${result.id}</a> " 님의 비밀번호는 <br>
		" <a style="color : #DE6562"> ${result.pw}</a> " 입니다</p></div>
		</c:if>
				
		<c:if test="${result eq null}">
			<script type="text/javascript">
				alert('회원 정보가 없습니다 ㅠㅅㅠ');
				location.href='/login';
			</script>
		</c:if>
				
		<div class="btn_bottom">
			<a href="/"><button type="submit" class="btn_submit" id="btnNext">Login</button></a>
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