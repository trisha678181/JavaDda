<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<c:import url="../layout/favicon.jsp" />
<title>Insert title here</title>

<link href="../../../css/mypage/CouponPlus.css" rel="stylesheet"
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

		<p id="edp2">내 쿠폰</p>

		<hr id="ed2hr">
		<div id="edtable">
			<table id="edtb">
				<tr>
					<th style="width: 40px;">번호</th>
					<th style="width: 130px;">장소명</th>
					<th style="width: 250px;">쿠폰명</th>
					<th style="width: 100px;">유효기간</th>
					<th style="width: 100px;">상태</th>
				</tr>
				<c:set var="i" value="1" />
				<c:forEach items="${couponlist }" var="couponlist">
					<c:if test="${couponlist.coupon.expdate < today }">
					<tr style="background: #eee">
						<td>${i }</td>
						<td><a id="couponname" href="/place/detail?pno=${couponlist.get(0)['coupon'].pno }">${couponlist.place.pname}</a></td>
						<c:if test="${couponlist.coupon.originname eq 'coupon_first.png'}">
							<td>첫 구매 쿠폰</td>
						</c:if>
						<c:if test="${couponlist.coupon.originname eq 'coupon_app.png'}">
							<td>앱 출시 기념 쿠폰</td>
						</c:if>
						<c:if test="${couponlist.coupon.originname eq 'coupon_over10.png'}">
							<td>10만원 이상 구매 시 할인 쿠폰</td>
						</c:if>
						<td>${couponlist.coupon.expdate}</td>
						<c:if test="${couponlist.havecoupon.cpstate eq '' || couponlist.havecoupon.cpstate eq null}">
							<td>기한만료</td>
						</c:if>
						<c:if test="${couponlist.havecoupon.cpstate != '' || couponlist.havecoupon.cpstate ne null}">
							<td>${couponlist.havecoupon.cpstate}</td>
						</c:if>
					</tr>
					</c:if>
					<c:if test="${couponlist.coupon.expdate >= today }">
					<tr>
						<td>${i }</td>
						<td><a id="couponname" href="/place/detail?pno=${couponlist.get(0)['coupon'].pno }">${couponlist.place.pname}</a></td>
						<c:if test="${couponlist.coupon.originname eq 'coupon_first.png'}">
							<td>첫 구매 쿠폰</td>
						</c:if>
						<c:if test="${couponlist.coupon.originname eq 'coupon_app.png'}">
							<td>앱 출시 기념 쿠폰</td>
						</c:if>
						<c:if test="${couponlist.coupon.originname eq 'coupon_over10.png'}">
							<td>10만원 이상 구매 시 할인 쿠폰</td>
						</c:if>
						<td>${couponlist.coupon.expdate}</td>
						<td>${couponlist.havecoupon.cpstate}</td>
					</tr>
					</c:if>
				<c:set var="i" value="${i+1 }" />
				</c:forEach>

			</table>



		</div>
		<!-- table + title END -->

	</div>

	<c:import url="../layout/footer.jsp" />

</body>
</html>