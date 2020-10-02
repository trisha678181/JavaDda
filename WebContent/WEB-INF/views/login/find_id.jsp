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

<title>아이디 찾기</title>

</head>
<body id="find" class="find">
	<div class="warp" id="warp">
		<div id="header">
			<a href="/place/main" class="logo"> <img
				src="../../../images/whiteLogoCool2.png" width="160" height="65" />
			</a>
		</div>

		<div id="find_container">
			<form id="find_form" action="/find/id" method="post">
				<p id="find_title">아이디 찾기</p>
				<div class="box_info_write">
					<dl class="write_input">
						<dt class="info_id">
							<label class="title" for="inputInfoID">이름</label> <span
								class="red_bullet">필수 항목</span>
						</dt>
						<dd>
							<span class="text_box"> 
							<input type="text"
									id="inputInfoName" class="input_text valid mustValid"
									name="userName" placeholder="이름을 적어주세요.">
								</span>
								<div class="text_alert" style="display: none;">
									<p>한글 1~16자, 영문 대·소문자 2~30자 가능합니다</p>
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
					</dl>

				</div>
				<div class="btn_bottom">
						<button type="submit" class="btn_submit" id="btnNext">아이디 찾기</button>
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