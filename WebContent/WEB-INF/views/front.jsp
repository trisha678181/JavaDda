<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/index.css" />
<c:import url="layout/favicon.jsp" />
<title>내손을 Java♥</title>
</head>

<body>
	<div>
	<video id="videobcg" preload="auto" autoplay="true" loop="loop" muted="muted">
     	<source src="/images/index.webm" type="video/webm">
    </video>
	</div>
	
	<div id="inwrapper">
	<div id="intext">
     	<img id="headerimg" src = "../../images/whiteLogoCool2.png" width="160" height="65"/></a>
		<br>
		<p>
		<c:if test = "${empty login}">
			<a class="button red" href="/login">로그인</a>
		</c:if>
		<c:if test = "${not empty login}">
			<a class="button red" href="/logout">로그아웃</a>
		</c:if>

			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="button" href="/place/main">둘러보기</a>
		</p>
	</div>
	</div>

</body>
</html>