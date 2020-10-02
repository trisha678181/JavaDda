<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css"
	href="../../css/layout/header.css">

<script src="../../../js/header.js"></script>


<div id="header">

	<a href="/place/main"><img id="headerimg"
		src="../../images/whiteLogoCool.png" width="160" height="65" /></a>
	<div id="hamburger_container">
		<div id="hamburger" class="icon nav-icon-5">
			<span></span> <span></span> <span></span>
		</div>
	
		<nav id="slide_menu">
			<ul>
			<c:choose>
				<c:when test="${empty login}">
					<li class="hli"><a href="/login">로그인/회원가입</a></li>
				</c:when>
				<c:when test="${not empty login}">
					<li class="hli"><a href="/logout">로그아웃</a></li>
				</c:when>
			</c:choose>
<%-- 				<c:if test = "${empty login}"> --%>
<!-- 					<li class="hli"><a href="/login">로그인/회원가입</a></li>  -->
<%-- 				</c:if> --%>
<%-- 				<c:if test = "${not empty login}"> --%>
<!-- 					<li class="hli"><a href="/logout">로그아웃</a></li>  -->
<%-- 				</c:if>			 --%>

		
				<li class="hli"><a href="/mypage/main">마이페이지</a></li> 
				<li class="hli"><a href="/notice/view">공지사항</a></li> 
				<li class="hli"><a href="/board/view">커뮤니티</a></li> 
				<li class="hli"><a href="/qa/view">1:1 문의</a></li> 
	<!-- 			<li class="hli"><a href="#">ABOUT US</a></li>  -->
			</ul>
		</nav>

	</div>
</div>