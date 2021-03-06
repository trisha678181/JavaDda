<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />
<meta charset="UTF-8">
<link rel="stylesheet" href="../../../css/board3/bootstrap.css">
<link rel="stylesheet" href="../../../css/board3/board.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<title>관리자메인</title>
<script type="text/javascript">
$(document).ready(function() {
	
	//글쓰기 버튼 누르면 이동
	$("#btnWrite").click(function() {
		location.href="/admin/notice/write";
	});
	//검색 버틀 클릭
	$("#btnSearch").click(function() {
		location.href="/admin/main?search="+$("#search").val();
	});
	
});
</script>

<link href="../../../css/admin/main.css" rel="stylesheet"
	type="text/css" media="all" />
<script type="text/javascript" src="../../../js/admin/admin_main.js"></script>

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
				<li><a href="#"  ><img src="../../../images/pencil.png"
						alt="profile" /> 글관리</a>
					<ul>
						<li><a href="/admin/noticelist"">공지사항</a></li>
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




		<div class="container" style="margin-bottom: 15px;">



			<table class="table table-bordered table-hover" id="user=-table"
				style="text-align: center; width: 730px; border: 1px solid #dddddd; float: right;">
				<thead>
					<tr>
						<th colspan="5"><h4 class="board-Title">회원리스트</h4></th>
					</tr>
					<tr>
						<th
							style="background-color: #fafafa; color: #000000; width: 70px;"><h5>회원번호</h5></th>
						<th style="background-color: #fafafa; color: #000000;"><h5>이름</h5></th>
						<th style="background-color: #fafafa; color: #000000;"><h5>아이디</h5></th>
						<th
							style="background-color: #fafafa; color: #000000; width: 100px;"><h5>번호</h5></th>
						<th
							style="background-color: #fafafa; color: #000000; width: 70px;"><h5>닉네임</h5></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="users">
						<tr>
							<td>${users.uno }</td>
							<td><a href="/admin/userlistinfo?uno=${users.uno }">${users.name }</a></td>
							<td>${users.id }</td>
							<td>${users.phone }</td>
							<td>${users.nick }</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="5">
							<div class="btn-toolbar form-inline" role="toolbar"
								aria-label="Toolbar with button groups">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="검색어를 입력" id="search">
									<span class="input-group-btn">
										<button id="btnSearch" class="btn">검색</button>
									</span>
								</div>

								<ul class="pagination" style="margin: 0 auto;"></ul>
							</div>
						</td>
					</tr>
				</tbody>
			</table>

		</div>


<div style="margin-left: 25%;">
		<c:import url="./paging.jsp" />
		</div>
	</div>
	<!-- 회원 리스트 div END -->

	<!-- 끝 -->

	<c:import url="../layout/footer.jsp" />

</body>

</html>


