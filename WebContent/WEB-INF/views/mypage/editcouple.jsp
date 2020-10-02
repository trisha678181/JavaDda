<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<c:import url="../layout/favicon.jsp" />
<title></title>

<link href="../../../css/mypage/editcouple.css" rel="stylesheet"
	type="text/css" />

<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	var modal = document.querySelector(".exitmodal"); 
	
	$("#mp1button").click(function() { // 끊기
		modal.classList.toggle("show-modal"); 
	});
	
	$(".close-button").click(function() {
		modal.classList.toggle("show-modal"); 
	});
	
	$(window).click(function(e) {
		if(e.target === modal) {
			modal.classList.toggle("show-modal"); 
		}
	})
})

</script>

</head>
<body>

	<c:import url="../layout/header.jsp" />

	<div id="mp2wrapper">

		<div id="mp2title">커플 설정</div>
		<hr id="mp2hr">

		<div id="coupleimg">
			<img id="coupleicon" class="edheart" alt=""
				src="../../../images/heart.svg"> <img id="coupleicon1"
				class="edheart" alt="" src="../../../images/heart.svg"> <img
				id="coupleicon2" class="edheart" alt=""
				src="../../../images/heart.svg"> <img id="coupleicon3"
				class="edheart" alt="" src="../../../images/heart.svg"> <img
				id="coupleicon4" class="edheart" alt=""
				src="../../../images/heart.svg"> <img id="coupleicon5"
				class="edheart" alt="" src="../../../images/heart.svg"> <img
				id="coupleicon6" class="edheart" alt=""
				src="../../../images/heart.svg"> <img id="coupleicon7"
				class="edheart" alt="" src="../../../images/heart.svg">
			<div id="mimg">
				<img
					src="<%=request.getContextPath() %>/upload/${user.uprofileimg }">
			</div>

			<div id="wimg">
				<img
					src="<%=request.getContextPath() %>/upload/${oppo.uprofileimg }">
			</div>
		</div>

		<p id="edp2">우리 기념일♥</p>
		<span OnClick="location.href ='../mypage/annilist'" id="edbtn">관리</span>
		<hr id="ed2hr">
		<div id="edtable">
			<table id="edtb">
				<tr>
					<th style="width: 150px;">날짜</th>
					<th>제목</th>
				</tr>
				<c:forEach items="${anni_list }" var="anni_list">

					<tr>
						<td>${anni_list.av_date}</td>
						<td>${anni_list.title}</td>
					</tr>

				</c:forEach>

			</table>



		</div>
		<!-- table + title END -->

		<button id="mp1button">커플 끊기</button>

		<div class="exitmodal">
			<div class="cmodal-content">
				<span class="close-button">&times;</span>
				<h1 class="title">커플 끊기</h1>

				<div id="circle">
					<p>
						정말 ${oppo.name }님과<br> 커플을 끊겠습니까?</span>
					</p>
				</div>

				<hr id="edhr">
				<p id="ededp">커플을 끊으시면 저장한 자료는 복원되지 않습니다.</p>
				<form action="/mypage/editcouple" method="post" id="edcform" name="edcform">
				<button id="exitsubmit">끊기</button>
				</form>
			</div>
		</div>


		<!-- ************************************************************************************ -->

	</div>


	<c:import url="../layout/footer.jsp" />

</body>
</html>