<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<c:import url="../layout/favicon.jsp" />
<title>Insert title here</title>

<link href="../../../css/mypage/BoardPlus.css" rel="stylesheet"
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

		<p id="edp2">내 게시글</p>

		<hr id="ed2hr">
		<div id="edtable">
			<table id="edtb">
				<tr>
					<th style="width: 20%;">날짜</th>
					<th>제목</th>
					<th style="width: 10%;">댓글</th>
					<th style="width: 10%;">조회수</th>
				</tr>
				<c:forEach items="${boardlist }" var="boardlist">
					<tr>
						<td>${boardlist.board.brdate }</td>
						<td><a id="boardname" href="/board/show?brno=${boardlist.board.brno }">${boardlist.board.brtitle }</a></td>
						<td>${boardlist.cnt }</td>
						<td>${boardlist.board.brviewcnt }</td>
					</tr>
				</c:forEach>

			</table>



		</div>
		<!-- table + title END -->

	</div>

	<c:import url="../layout/footer.jsp" />

</body>
</html>