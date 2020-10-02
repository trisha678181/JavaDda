<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />

<title></title>

<link href="../../../css/admin/license_main.css" rel="stylesheet" type="text/css"
	media="all" />

</head>

<body>
	<c:import url="../layout/header.jsp" />

	<div id="mp1wrapper">
		<div id="mp1title">LICENSE PAGE</div>
		
		<div id="imgs">
		<img id="bgimg" src="../../../images/bgimg.jpg" alt="" />
		
		<div id="pfimgform">
			<img src="../../../images/profileimg.jfif" alt="" />
			   
			<div id="mp1btn">
				<a href="#" id="mp1button">회원정보수정</a>
			</div>
		
		</div>
		</div> <!-- imgs div END -->
		
		<br>
		
		<div id="mp1title">나의 가게 현황</div>
			<div id="mp1wrapper1">
				<div id="mp1wrapper2">
				<div id="mp1column1">
					<img id="mp1couponicon" src="../../../images/couponicon.svg" alt="" />
					<div id="mp1count">3</div>
				</div>
					<div id="mp1columntt">나의 쿠폰</div>
				</div>
				<div id="mp1wrapper2">
				<div id="mp1column2">
					<img id="mp1couponicon" src="../../../images/likeicon.svg" alt="" />
					<div id="mp1count">10</div>
				</div>
					<div id="mp1columntt">나의 가게 찜</div>
				</div>
				<div id="mp1wrapper2">
				<div id="mp1column3">
					<img id="mp1couponicon" src="../../../images/reviewicon.svg" alt="" />
					<div id="mp1count">2</div>
				</div>
					<div id="mp1columntt">나의 가게 리뷰</div>
				</div>

			</div>
		<br />
		<br />
		
	
	<div id="mp1title"> 우리 가게 쿠폰</div>
	<button id="mp1button3">+</button>
	<div id="mp1wrapper3">
		<table id="mp1table" style="text-align: center;">
			<thead>
				<tr>
					<th style="width: 70px;">번호</th>
					<th>쿠폰 이름</th>
					<th style="width: 150px;">유효기간</th>
					<th style="width: 100px;">할인율</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>사장님이 미쳤어요</td>
					<td>2020-07-01</td>
					<td>10%할인</td>
				</tr>
				<tr>
					<td>2</td>
					<td>즉시할인</td>
					<td>2020-07-01</td>
					<td>4%할인</td>
				</tr>
				<tr>
				<td>3</td>
				<td>처음 방문손님어서오세요</td>
				<td>2020-07-01</td>
				<td>10%할인</td>
				</tr>

			</tbody>
		</table>
	</div>
	
	<br><br>
	
	<div id="mp1title">우리 가게 리뷰</div>
	<button id="mp1button3">+</button>
	<div id="mp1wrapper3">
		<table id="mp1table" style="text-align: center;">
			<thead>
				<tr>
					<th style="width: 100px;">날짜</th>
					<th style="width: 380px;">내용</th>
					<th style="width: 100px;">별점</th>
					<th>작성자</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>2020-06-01</td>
					<td>공부하기에 딱</td>
					<td>★★★★☆</td>
					<td>hhy***</td>
				</tr>
				<tr>
					<td>2020-02-01</td>
					<td>좋아요</td>
					<td>★★★★☆</td>
					<td>ㅋㅋㅋy***</td>
				</tr>
				<tr>
					<td>2020-05-01</td>
					<td>굳굳</td>
					<td>★★★★☆</td>
					<td>2454***</td>
				</tr>
				

			</tbody>
		</table>
	</div>		
	<br />
	<br />

		
		
	</div>
	<!-- mp1container END -->
	<c:import url="../layout/footer.jsp" />
	
</body>
</html>
