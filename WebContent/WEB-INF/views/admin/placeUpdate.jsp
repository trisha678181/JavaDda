<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>





<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<title></title>
<script type="text/javascript">
	$(document).ready(function() {

		console.log("a");

		//작성버튼 동작
		$("#btnWrite").click(function() {

			//<form>태그 submit
			$("#place").submit();
		});

		//취소버튼 동작
		$("#btnCancel").click(function() {
			history.go(-1);
		});
	});
</script>
<link rel="stylesheet" href="../../../css/board3/bootstrap.css">
<link rel="stylesheet" href="../../../css/board3/board.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<title>관리자메인</title>

<link href="../../../css/admin/main.css" rel="stylesheet"
	type="text/css" media="all" />
<script type="text/javascript" src="../../../js/admin/admin_main.js"></script>
<meta charset="UTF-8">
</head>


<body>
	<c:import url="../layout/header.jsp" />

	<div id="adminBox">
		<!-- 헤더와 푸터 사이에 공간 잡아주는 div -->

		<div id="admin_menu">
			<!-- 메뉴바 div -->
			<ul>
				<li>
					<!-- class="current_page_item"> --> <a href="/admin/main"><img
						src="../../../images/profile.png" alt="profile" /> 회원관리 </a>
				</li>
				<li><a href="#"><img src="../../../images/pencil.png"
						alt="profile" /> 글관리</a>
					<ul>
						<li><a href="/admin/noticelist">공지사항</a></li>
						<li><a href="/admin/reviewlist">REVIEW</a></li>
						<li><a href="/admin/qnalist">1:1문의</a></li>
					</ul></li>
				<li><a href="#"><img src="../../../images/navigation.png"
						alt="profile" /> 장소관리</a>
					<ul>
						<li><a href="/admin/placelist">장소리스트</a></li>
						<li><a href="/admin/businesslist">사업자리스트</a></li>

					</ul></li>
			</ul>
		</div>
		<!-- 메뉴바 div END -->

		<div class="container">
			<form method="post" action="/admin/placemodify" id="place">			
			<input type="hidden" name="pno" value="${list['place'].pno }" />
				<table class="table table-bordered table-hover"
					style="text-align: center; border: 1px solid #dddddd; width: 735px; float: right;">
					<thead>
						<tr>
							<th colspan="8"><h4 class="board-Title">store 수정</h4></th>
						</tr>
						<tr>
							<td
								style="background-color: #fafafa; color: #000000; width: 80px;"><h5>Store</h5></td>
							<td colspan="8"><h5>${list['place'].pname }</h5></td>
						</tr>
						<tr>
							<td
								style="background-color: #fafafa; color: #000000; width: 100px;"><h5>지역</h5></td>
							<td><h5>
									<c:choose>
										<c:when test="${list['place'].lno eq '1'}">
										강남
									</c:when>
										<c:when test="${list['place'].lno eq '2'}">
										강북
									</c:when>
										<c:when test="${list['place'].lno eq '3'}">
										강서
									</c:when>
										<c:when test="${list['place'].lno eq '4'}">
										강동
									</c:when>
									</c:choose>
								</h5></td>
							<td
								style="background-color: #fafafa; color: #000000; width: 70px;"><h5>카테고리</h5></td>
							<td><h5>
									<c:choose>
										<c:when test="${list['place'].pcode eq '1'}">
										카페
									</c:when>
										<c:when test="${list['place'].pcode eq '2'}">
										식당
									</c:when>
										<c:when test="${list['place'].pcode eq '3'}">
										술집
									</c:when>
										<c:when test="${list['place'].pcode eq '4'}">
										실내놀거리
									</c:when>
										<c:when test="${list['place'].pcode eq '5'}">
										야외놀거리
									</c:when>
									</c:choose>
								</h5></td>
							<td
								style="background-color: #fafafa; color: #000000; width: 80px;"><h5>주차여부</h5></td>
							<td><h5>
									<input class="form-control" type="text" maxlength="10"
										name="pparking" size="1" value="${list['place'].pparking }">
								</h5></td>
						</tr>
						<tr>
							<td
								style="background-color: #fafafa; color: #000000; width: 80px;"><h5>사업자아이디</h5></td>
							<td><h5>${list["business"].name }</h5></td>
							<td
								style="background-color: #fafafa; color: #000000; width: 80px;"><h5>주소</h5></td>
							<td colspan="3"><h5><input class="form-control" type="text" maxlength="50"
										name="ploc" size="1" value="${list['place'].ploc }">
								</h5></td>
						</tr>
						<tr>
							<td
								style="background-color: #fafafa; color: #000000; width: 70px;"><h5>전화번호</h5></td>
							<td><h5>
									<input class="form-control" type="text" maxlength="11"
										name="pphone" size="1" value="${list['place'].pphone }">
								</h5></td>
							<td
								style="background-color: #fafafa; color: #000000; width: 70px;"><h5>운영시간</h5></td>
							<td colspan="3"><h5>
									<input class="form-control" type="text" maxlength="100"
										name="poperate" size="1" value="${list['place'].poperate }">
								</h5></td>

						</tr>
						<tr>
							<td
								style="vertical-align: middle; min-height: 150px; background-color: #fafafa; color: #000000; width: 80px;"><h5>메뉴</h5></td>
							<td colspan="8" style="text-align: left;"><h5>
									<input class="form-control" type="text" maxlength="100"
										name="pmenu_txt" size="1" value="${list['place'].pmenu_txt }">
								</h5></td>
						</tr>
						<tr>
							<td
								style="vertical-align: middle; min-height: 150px; background-color: #fafafa; color: #000000; width: 80px;"><h5>홍보내용</h5></td>
							<td colspan="8" style="text-align: left;"><h5>
									<input class="form-control" type="text" maxlength="100"
										name="pcontent" size="1" value="${list['place'].pcontent }">
								</h5></td>
						</tr>

					</thead>

				</table>
			</form>
		</div>
		<div style="text-align: right;">
			<button class="btn btn-primary" id="btnWrite" type="button">등록</button>
			<button class="btn btn-primary" id="btnCancel" type="button">취소</button>
		</div>
	</div>
	<!-- 회원 리스트 div END -->
	<c:import url="../layout/footer.jsp" />



</body>
</html>