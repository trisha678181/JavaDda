<!-- 장소 리스트  -->
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
<script type="text/javascript">
$(document).ready(function() {
	
	//검색 버틀 클릭
	$("#btnSearch").click(function() {
		location.href="/admin/placelist?search="+$("#search").val();
	});

});
</script>

<script type="text/javascript">
$(document).ready(function() {
	
	$("input[name='checkRow']").change(function(){
		if( $("input[name='checkRow']:checked").length > 5 ) {
			alert('5개 이하만 선택하세요')
			
			$(this).prop("checked", false);
		}
	})
	

	
	// 선택체크 픽하기
	$("#btnPick").click(function() {
		// 선택된 체크박스
		var $checkboxes = $("input:checkbox[name='checkRow']:checked");
		
		//방법2
		// 체크된 대상들을 map으로 만들고 map을 문자열로 만들기
		var map = $checkboxes.map(function() {
			return $(this).val();
		});
		var names = map.get().join(",");
		
if( $("input[name='checkRow']:checked").length > 5 ) {
	alert('nono')
	return false;
}
	
// 			console.log($checkboxes);
// 			console.log( "map:" + map );	// 맵
// 			console.log( "map->array : " + map.get() );	// 맵->배열
// 			console.log( "array tostring : " + map.get().join(",") ); // toString
		
		// 전송 폼
		var $form = $("<form>")
			.attr("action", "/admin/listPick")
			.attr("method", "post")
			.append(
				$("<input>")
					.attr("type", "hidden")
					.attr("name", "names")
					.attr("value", names)
			);
			
		console.log("$form : " + $form);
		
		$(document.body).append($form);
		$form.submit();
	
	});
	
	
	// 선택체크 삭제
	$("#Pickdelect").click(function() {
		// 선택된 체크박스
		var $checkboxes = $("input:checkbox[name='checkRow']:checked");
		

		//방법2
		// 체크된 대상들을 map으로 만들고 map을 문자열로 만들기
		var map = $checkboxes.map(function() {
			return $(this).val();
		});
		var names = map.get().join(",");
	//		console.log("names : " + names);
	
	//		console.log($checkboxes);
	//		console.log( "map:" + map );	// 맵
	//		console.log( "map->array : " + map.get() );	// 맵->배열
	//		console.log( "array tostring : " + map.get().join(",") ); // toString
		
	
		// 전송 폼
		var $form = $("<form>")
			.attr("action", "/admin/listPickdelect")
			.attr("method", "post")
			.append(
				$("<input>")
					.attr("type", "hidden")
					.attr("name", "names")
					.attr("value", names)
			);
		$(document.body).append($form);
		$form.submit();
	
	});
	
});
</script>
<title>관리자메인</title>

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




		<div class="container" style="margin-bottom: 15px;">



			<table class="table table-bordered table-hover"
				style="text-align: center; width: 730px; border: 1px solid #dddddd; float: right;">
				<thead>
					<tr>
						<th colspan="6"><h4 class="board-Title">장소 리스트</h4></th>
					</tr>
					<tr>
					<th
							style="background-color: #fafafa; color: #000000; width: 60px;"><h5>체크</h5></th>
						<th
							style="background-color: #fafafa; color: #000000; width: 60px;"><h5>번호</h5></th>
						<th
							style="background-color: #fafafa; color: #000000; width: 100px;"><h5>카테고리</h5></th>
						<th
							style="background-color: #fafafa; color: #000000; width: 100px;"><h5>지역</h5></th>
						<th style="background-color: #fafafa; color: #000000;"><h5>상점이름</h5></th>
						<th
							style="background-color: #fafafa; color: #000000; width: 100px;"><h5>사업자이름</h5></th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="i">
						<tr>
						<td><input type="checkbox" name="checkRow" value="${i.place.pno }" /></td>
							<td>${i.place.pno }</td>
							<td><c:choose>
									<c:when test="${i.place.pcode eq '1'}">
										음식점
									</c:when>
									<c:when test="${i.place.pcode eq '2'}">
										카페
									</c:when>
									<c:when test="${i.place.pcode eq '3'}">
										술집
									</c:when>
									<c:when test="${i.place.pcode eq '4'}">
										실내 놀거리
									</c:when>
									<c:when test="${i.place.pcode eq '5'}">
										야외놀거리
									</c:when>
								</c:choose></td>
							<td><c:choose>                       
									<c:when test="${i.place.lno eq '1'}">
										강남역
									</c:when>
									<c:when test="${i.place.lno eq '2'}">
										명동역
									</c:when>
									<c:when test="${i.place.lno eq '3'}">
										홍대입구역
									</c:when>
								</c:choose></td>
							<td><a href="/admin/placelistinfo?pno=${i.place.pno }">${i.place.pname }</a></td>
							<td>${i.business.name }</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="6">
							<div class="btn-toolbar form-inline" role="toolbar"
								aria-label="Toolbar with button groups">

								<button id="btnPick" class="btn btn-primary pull-left" onclick="alert('장소가 선택 되었습니다');">PICK</button>
								<div class="input-group">
									<input type="text" class="form-control" placeholder="검색어를 입력"
										id="search"> <span class="input-group-btn">
										<button id="btnSearch" class="btn">검색</button>
									</span>
								</div>
								<button id="Pickdelect" class="btn btn-primary pull-right" onclick="alert('장소가 삭제 되었습니다');">PICK 취소</button>
								<ul class="pagination" style="margin: 0 auto;"></ul>
							</div>
						</td>
					</tr>
				</tbody>
			</table>

		</div>


<div style="margin-left: 25%;">
		<c:import url="./place_paging.jsp" />
		</div>
	</div>
	<!-- 장소리스트 div END -->

	<!-- 끝 -->

	<c:import url="../layout/footer.jsp" />

</body>

</html>


