<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<c:import url="../layout/favicon.jsp" />
<title>Insert title here</title>

<link href="../../../css/mypage/ReviewPlus.css" rel="stylesheet"
	type="text/css" />

	
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>


<script type="text/javascript">

$(document).ready( function() {
	
	$("#deletesubmit").click(function() { // 삭제 누르면
		
		console.log(old_annititle)

		$.ajax({
			type : 'POST'
			, url : "/mypage/couple/deleteanni" 
			, data : {annititle : $("#lptitle2").val(), 
					  annidate : $("#lpdate2").val()}
			, dataType: "json"
			, success : function(result){
				
				location.reload();
				
				
			}
			, error: function() {
				console.log('AJAX fail')
			}
			
		})	
	})
	
	$("#edtb td").click(function() {
		
		old_annititle = $(this).text()
		
		$("#lptitle2").attr('value', $(this).text());
		$("#lpdate2").attr('value', $(this).parent("tr").children().eq(0).text());
		
	})
	
})



</script>

<script type="text/javascript">
$(document).ready(function() {
	
	var modal = document.querySelector(".addmodal"); // 추가 모달
	var modal2 = document.querySelector(".editmodal"); // 수정 모달
	var mp1button = document.querySelector("#edbtnadd"); 
	
	$("#edbtnadd").click(function() {
		modal.classList.toggle("show-modal"); 
	});
	
	$("#edtb td").click(function() {
		modal2.classList.toggle("show-modal"); 
	});
	
	$(".close-button").click(function() {
		modal.classList.toggle("show-modal"); 
	});
	
	$(".close-button2").click(function() {
		modal2.classList.toggle("show-modal"); 
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
	<div id="mp2wrapper">
		<c:import url="../layout/header.jsp" />

		<p id="edp2">내 리뷰</p>

		<span id="edbtndelete">삭제</span>
		<hr id="ed2hr">
		<div id="edtable">
			<table id="edtb">
				<tr>
					<th style="width: 90px;">날짜</th>
					<th style="width: 130px;">장소명</th>
					<th>내용</th>
					<th style="width: 90px;">별점</th>
				</tr>
				<c:forEach items="${reviewlist }" var="reviewlist">
					<tr>
						<td>${reviewlist.review.rvdate }</td>
						<td><a id="reviewname" href="/place/reviewplus?pno=${reviewlist.review.pno }">${reviewlist.place.pname }</a></td>
						<td>${reviewlist.review.rvcontent }</td>
						<c:if test="${reviewlist.review.rvstar eq 1}">
							<td>★☆☆☆☆</td>
						</c:if>
						<c:if test="${reviewlist.review.rvstar eq 2}">
							<td>★★☆☆☆</td>
						</c:if>
						<c:if test="${reviewlist.review.rvstar eq 3}">
							<td>★★★☆☆</td>
						</c:if>
						<c:if test="${reviewlist.review.rvstar eq 4}">
							<td>★★★★☆</td>
						</c:if>
						<c:if test="${reviewlist.review.rvstar eq 5}">
							<td>★★★★★</td>
						</c:if>
					</tr>
				</c:forEach>

			</table>



		</div>
		<!-- table + title END -->

	</div>

	<c:import url="../layout/footer.jsp" />

</body>
</html>