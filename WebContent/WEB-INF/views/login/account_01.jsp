<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />
<link rel="stylesheet" type="text/css" href="../../../css/login/account_01.css" />
<link rel="stylesheet" type="text/css" href="../../../css/login/account_footer.css" />
<meta charset="UTF-8">

<title>내손을 Java-회원유형</title>

</head>

<body id="chs_account_type">

<div class="ac_warp" id="ac_warp">

	<div id="header">
		<a href = "/place/main" class = "logo">
			<img src = "../../../images/whiteLogoCool2.png" width="160" height="65"/>
		</a>
	</div>

	<div id="ac_01_container">
		<p id="title_choose">회원유형</p>
		<div><p id="choose_typyof">회원유형을 선택해 주세요.</p></div>
		<div class="account_step_01">
		<table>
			<tr>
			<td>
			<div>
			<a href = "/account/general" class = "choose_mem_type">
			<img src = "../../../images/General_Membership.png" width="200" height="200"/>
			</a>
			<p>일반회원</p>
			</div>
			</td>
			
			<td>
			<div>
			<a href = "/account/business" class = "choose_mem_type">
			<img src = "../../../images/business_membership.png" width="200" height="200"/>
			</a>
			<p>사업자</p>
			</div>
			</td>
			</tr>
		</table>	
		</div>
	<div id="footer">
        
		<ul class="footer_menu">
			<li><a href="http://policym.com/terms/">이용약관</a></li>
			<li><a href="http://policym.com/info"><strong>개인정보처리방침</strong></a></li>
			<li><a href="http://wwwernet.com/" target="_blank"><strong>©ZUMinternet</strong></a></li>
		</ul>


    </div>
	</div>

</div>



</body>
</html>