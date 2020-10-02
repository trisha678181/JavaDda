<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
 response.setHeader("Cache-Control","no-store"); 
 response.setHeader("Pragma","no-cache"); 
%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />
<link rel="stylesheet" type="text/css" href="../../../css/layout/header.css">
<link rel="stylesheet" type="text/css" href="../../../css/login/login.css">
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<title>Login</title>
<style type="text/css">
#naver_id_login {
	padding-top: 10px;
	padding-bottom: 20px;
    padding-left: 118px;
    padding-right: 118px;
}

#naver_id_login img{
	position: relative;
    width: 260px;
    height: 53px;
}
</style>

<script type="text/javascript">

$(document).ready( function() {
	
	var modal = document.querySelector(".cmodal"); 
	var modal3 = document.querySelector(".cmodal3"); 
	
$("#cvbtn").click(function() { // 확인 누르면

	$.ajaxSettings.traditional = true;
	
	$.ajax({
	type : 'POST'
	, url : "/couple/valid" 
	, data : {couplevalid : $("#couplevalid").val()}
	, dataType: "json"
	, success : function(result){
		
		if(result.makecouple == false){ // 인증번호 안 맞으면
			$("#edp2").css("display", "inline-block");
			$("#couplevalid").val("");
			$("#couplevalid").focus();

		} else {
			window.location.href='/couple/make?couplevalid='+result.couplevalid;
		}
		
		
	}
	, error: function() {
		console.log('AJAX fail')
	}
	
})	
})

})

</script>




</head>
<body style = "background :#f7f7f7">
<div id="header" style="background: #F8CAC6 ">

	<a href="/place/main" class="logo">	
	<img id="headerimg" src = "../../images/whiteLogoCool2.png" width="160" height="65"/></a>
	
</div>
<div id="container">
		<div class="login_page">
			<p>커플을 맺읍시당~!</p>
			<div class="login_box">
					<fieldset>
						<dl>
							<dt class="blind">인증번호 </dt>
							<dd>
							<span class="input_area" id="input_box">
							<input type="text" id= "couplevalid" name="couplevalid" placeholder="인증번호를 입력하세요"/>
							<span id="edp2" style="display: none">인증번호가 일치하지 않습니다. 메일을 다시 확인해주세요!</span>
							<button type="button" class="btn_clear d_clear" style="display:none" tabindex="-1"></button>
							</span>		
							</dd>
						</dl>
						<div class="btn_login">
						<button id="cvbtn" type="submit" style="margin-bottom: 50px;">확인</button>
						</div>
					</fieldset>
				
			
			</div>

		</div>
	
	</div>

</body>
</html>