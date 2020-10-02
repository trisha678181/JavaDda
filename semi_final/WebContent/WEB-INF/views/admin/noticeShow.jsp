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
<script type="text/javascript">
$(document).ready(function() {
	
	//글쓰기 버튼 누르면 이동
	$("#btnWrite").click(function() {
		location.href="/board/write";
	});
	
});
</script>
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
						<li><a href="/admin/business">사업자리스트</a></li>
					
					</ul></li>
			</ul>
		</div>
		<!-- 메뉴바 div END -->
	
<div class="container" >
		<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd; width:730px; float: right; ">
		<thead>
		
			<tr>
				<th colspan="8"><h4 class="board-Title">공지사항</h4></th>
			</tr>
			<tr>
				<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>제목</h5></td>
				<td colspan="8"><h5>${list.title }</h5></td>
			</tr>
			<tr>
				<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성자</h5></td>
				<td colspan="8"><h5>관리자</h5></td>
			</tr>
			<tr>
				<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성날짜</h5></td>
				<td><h5>${list.ntdate }</h5></td>
				<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>조회수</h5></td>
				<td><h5>${list.hit }</h5></td>
			</tr>
			<tr>
				<td style="vertical-align: middle; min-height: 150px; background-color: #fafafa; color: #000000; width: 80px;"><h5>글 내용</h5></td>
				<td colspan="8" style="text-align: left;"><h5>${list.ntcontent }</h5></td>
			</tr>
	
		</thead>
		<tbody>
		<tr>
			<td colspan="8" style="text-align: right;">
				<a href="/admin/noticelist" class="btn btn-primary">목록</a>
				<a href="/admin/noticemodify?noticeno=${list.noticeno }" class="btn btn-primary">수정</a>
				<a href="/admin/noticedelete?noticeno=${list.noticeno }" class="btn btn-primary" onclick="return confirm('정말로 삭제하시겠습니까?');">삭제</a>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
		</div>
	<!-- 회원 리스트 div END -->
	<c:import url="../layout/footer.jsp" />
	
	
	
</body>
</html>