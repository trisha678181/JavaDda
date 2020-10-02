<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>





<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />

<title></title>

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
			<table class="table table-bordered table-hover"
				style="text-align: center; border: 1px solid #dddddd; width: 735px; float: right;">
				<thead>
					<tr>
						<th colspan="8"><h4 class="board-Title">Review 상세보기</h4></th>
					</tr>
					<tr>
						<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>아이디</h5></td>
						<td colspan="8"><h5>${list['userview'].name }</h5></td>
					</tr>
					<tr>
						<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>store</h5></td>
						<td colspan="5"><h5>${list['placeview'].pname }</h5></td>
						<td
							style="background-color: #fafafa; color: #000000; width: 80px;"><h5>리뷰날짜</h5></td>
						<td colspan="3"><h5>${list["reviewview"].rvdate }</h5></td>
					</tr>
					<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>리뷰 별점</h5></td>
						<td colspan="8"><h5>
						<c:choose>
							<c:when test="${list['reviewview'].rvstar eq '1' }" >★</c:when>
							<c:when test="${list['reviewview'].rvstar eq '2' }" >★★</c:when>
							<c:when test="${list['reviewview'].rvstar eq '3' }" >★★★</c:when>
							<c:when test="${list['reviewview'].rvstar eq '4' }" >★★★★</c:when>
							<c:when test="${list['reviewview'].rvstar eq '5' }" >★★★★★</c:when>
							
						</c:choose>
						</h5></td></tr>
					<tr>
						<td style="vertical-align: middle; min-height: 150px; background-color: #fafafa; color: #000000; width: 80px;"><h5>내용</h5></td>
						<td colspan="8" style="text-align: left;"><h5>${list["reviewview"].rvcontent }</h5></td>
					</tr>
					<tr>
						<td style="vertical-align: middle; min-height: 150px; background-color: #fafafa; color: #000000; width: 80px;"><h5>이미지 </h5></td>
						<td colspan="8" style="text-align: left;"><img id="bgimg" style="width:300px" src="<%=request.getContextPath() %>/upload/${list['p_imgview'].storedName }" alt="" style="cursor:pointer;"/></td>
		
					</tr>
					
				</thead>
				<tbody>
					<tr>
						<td colspan="8" style="text-align: right;">
							<a href="/admin/reviewlist" class="btn btn-primary">목록</a>
							<a href="/admin/reviewdelete?rno=${list['reviewview'].rno }" class="btn btn-primary" onclick="return confirm('정말로 삭제하시겠습니까?');">삭제</a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- 회원 리스트 div END -->
	<c:import url="../layout/footer.jsp" />



</body>
</html>