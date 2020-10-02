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
</head>
<body style = "background :#f7f7f7">
<div id="header" style="background: #F8CAC6 ">

	<a href="/place/main" class="logo">	
	<img id="headerimg" src = "../../images/whiteLogoCool2.png" width="160" height="65"/></a>
	
</div>
<div id="container">
		<div class="login_page">
			<p>Login</p>
			<div class="login_box">
				<form id="login_form" name="login_form" action="/login" method="post">
					<input type="hidden" name="backUrl" value='<%=request.getHeader("referer") %>' />
					<fieldset>
						<dl>
							<dt class="blind">아이디 </dt>
							<dd>
							<span class="input_area" id="input_box">
							<input type="text" id= "id" name="id" maxlength="15" placeholder="ID"/>
							<button type="button" class="btn_clear d_clear" style="display:none" tabindex="-1"></button>
							</span>		
							</dd>
							
							<dt class="blind">비밀번호 </dt>
							<dd>
							<span class="input_area">
							<input type="password" id= "pw" name="pw" maxlength="15" placeholder="PASSWORD"/>
							</span>		
							</dd>
						</dl>
						<div class="btn_login">
						<button type="submit">Login</button>
						</div>
						
						<div class="check_keep_login">
							<input type="checkbox" id="log_ckb" name="log_ckb"/>
							<label for="log_ckb">로그인상태 유지</label>
						</div>
					</fieldset>
				
				</form>
			
			</div>
			<div class="login_menu">
				<ul>
					<li><a id="btn_find_id" href="/find/id">아이디 찾기</a></li>
					<li><a id="btn_find_pass" href="/find/password">비밀번호 찾기</a></li>
					<li><a id="btn_join" href="/agreement">회원가입</a></li>
				</ul>
			</div>
			
			<div id = social_log style="">
				<div id ="naver_id_login"></div>
				<script type="text/javascript">
// 					var naver_id_login = new naver_id_login("HEOG93kkji0QYQ5_5Q3Q", "http://localhost:8088/place/main");
// 					var state = naver_id_login.getUniqState();
// 					naver_id_login.setButton("white", 8,40);
// 					naver_id_login.setDomain("http://localhost:8088/");
// 					naver_id_login.setState(state);
// 					naver_id_login.setPopup();
// 					naver_id_login.init_naver_id_login();
				</script>
			</div>
			
			
		</div>
	
	</div>
	
	<div id="footer">
		
		<ul class="links">
			<li><a href="http://inm/brand/">서비스소개</a></li>
			<li><a href="http://polm.com/terms/">이용약관</a></li>
			<li><a href="http://poliom/info/"><strong>개인정보처리방침</strong></a></li>
			<li><a href="mailm">제휴</a></li>
			<li><a href="http://w/?wbs=5.0.3" target="_blank" title="새 창으로 링크열기">채용</a></li>
			<li><a href="http://hel.com/">고객센터</a></li>
			<li><a href="http://dev.com/">개발자센터</a></li>
			<li><a href="http:/ternet.com/" target="_blank"><strong>ⓒ JAVADDA internet Corp.</strong></a></li>
		</ul>

	</div>
</body>
</html>