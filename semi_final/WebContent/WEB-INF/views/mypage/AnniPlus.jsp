<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<c:import url="../layout/favicon.jsp" />
<title>Insert title here</title>

<link href="../../../css/mypage/AnniPlus.css" rel="stylesheet"
	type="text/css" />

	
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>


<script type="text/javascript">

$(document).ready( function() {
	
	var modal = document.querySelector(".addmodal"); 
	var old_annititle
	
	$("#addsubmit").click(function() { // 추가 누르면

		$.ajax({
			type : 'POST'
			, url : "/mypage/couple/addanni" 
			, data : {annititle : $("#lptitle").val(), 
					  annidate : $("#lpdate").val()}
			, dataType: "json"
			, success : function(result){
				
				location.reload();
				
				
			}
			, error: function() {
				console.log('AJAX fail')
			}
			
		})	
	})
	
	$("#editsubmit").click(function() { // 수정 누르면
		
		console.log(old_annititle)

		$.ajax({
			type : 'POST'
			, url : "/mypage/couple/editanni" 
			, data : {annititle : $("#lptitle2").val(), 
					  annidate : $("#lpdate2").val(),
					  old_annititle : old_annititle}
			, dataType: "json"
			, success : function(result){
				
				location.reload();
				
				
			}
			, error: function() {
				console.log('AJAX fail')
			}
			
		})	
	})
	
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

		<p id="edp2">우리 기념일♥</p>

		<span id="edbtnadd">추가</span>
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

		<div class="addmodal">
			<div class="cmodal-content">
				<span class="close-button">&times;</span>
				<h1 class="title">기념일 추가</h1>
				<p id="edp">기념일 타이틀과 날짜를 입력해주세요</p>
				
				<table id="lptable">
					<tr>
						<td>타이틀</td>
						<td><input id="lptitle" type="text"
					name="lptitle" required="required"></td>
					</tr>
					<tr>
						<td>날짜</td>
						<td><input id="lpdate" type="date"
					name="lptitle" required="required"></td>
					</tr>
				</table>
				<button id="addsubmit">추가</button>
			</div>
		</div>
		
		<div class="editmodal">
			<div class="cmodal-content">
				<span class="close-button2">&times;</span>
				<h1 class="title">기념일 수정</h1>
				<p id="edp">기념일 타이틀과 날짜, 수정 가능합니다</p>
				
				<table id="lptable">
					<tr>
						<td>타이틀</td>
						<td><input id="lptitle2" type="text"
					name="lptitle" required="required" value=""></td>
					</tr>
					<tr>
						<td>날짜</td>
						<td><input id="lpdate2" type="date"
					name="lptitle" required="required" value=""></td>
					</tr>
				</table>
				<button id="editsubmit">수정</button>
				<button id="deletesubmit">삭제</button>
			</div>
		</div>





	</div>

	<c:import url="../layout/footer.jsp" />

</body>
</html>