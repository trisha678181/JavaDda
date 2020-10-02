<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- <script src="../../../js/jquery.min.js"></script> -->
<!-- <script src="../../../js/jquery.poptrox.min.js"></script> -->
<!-- <script src="../../../js/skel.min.js"></script> -->
<!-- <script src="../../../js/main.js"></script> -->
<title>장소추천</title>
<link href="../../../css/place/main2.css" rel="stylesheet" type="text/css"
	media="all" />
<c:import url="../layout/header.jsp" />

<script type="text/javascript">
$(document).ready( function() {

	<c:if test='${weather["SKY"] eq 1 && weather["PTY"] eq 0}'> // 맑음
		<c:set var="sky" value="맑음" />
		$('#weathericon').prop('src', '<%=request.getContextPath()%>/images/weather/sun.svg');

	</c:if>

	<c:if test='${weather["SKY"] eq 3 && weather["PTY"] eq 0}'> // 구름 많음
		<c:set var="sky" value="구름 많음" />
		$('#weathericon').attr('src', '<%=request.getContextPath()%>/images/weather/cas.svg');

	</c:if>

	<c:if test='${weather["SKY"] eq 4 && weather["PTY"] eq 0}'> // 흐림
		<c:set var="sky" value="흐림" />
		$('#weathericon').attr('src', '<%=request.getContextPath()%>/images/weather/cloud.svg');

	</c:if>

	<c:if test='${weather["PTY"] eq 1 || weather["PTY"] eq 4}'> // 비, 소나기
		<c:set var="sky" value="비" />
	$('#weathericon').attr('src', '<%=request.getContextPath()%>/images/weather/rain.svg');

	</c:if>

	<c:if test='${weather["PTY"] eq 2}'> // 비눈
		<c:set var="sky" value="진눈깨비" />
	$('#weathericon').attr('src', '<%=request.getContextPath()%>/images/weather/sleet.svg');

	</c:if>

	<c:if test='${weather["PTY"] eq 3}'> // 눈
		<c:set var="sky" value="눈" />
	$('#weathericon').attr('src', '<%=request.getContextPath()%>/images/weather/snow.svg');

</c:if>

<c:if test='${air eq 1}'> // 미세먼지 좋음
<c:set var="airinfo" value="좋음" />
$('#airicon').attr('src', '<%=request.getContextPath()%>/images/weather/agood.svg');

</c:if>

<c:if test='${air eq 2}'> // 미세먼지 보통
<c:set var="airinfo" value="보통" />
$('#airicon').attr('src', '<%=request.getContextPath()%>/images/weather/asoso.svg');

</c:if>

<c:if test='${air eq 3}'> // 미세먼지 나쁨
<c:set var="airinfo" value="나쁨" />
$('#airicon').attr('src', '<%=request.getContextPath()%>/images/weather/abad.svg');

</c:if>

<c:if test='${air eq 4}'> // 미세먼지 매우나쁨
<c:set var="airinfo" value="매우나쁨" />
$('#airicon').attr('src', '<%=request.getContextPath()%>
	/images/weather/aworst.svg');

						</c:if>

					})
</script>

<script type="text/javascript">
	// 왼쪽 플로팅배너
	var startTop = 110;
	$(window)
			.scroll(
					function() {
						var cTop = document.body.scrollTop == 0 ? document.documentElement.scrollTop
								: document.body.scrollTop;

						if (cTop <= startTop) {
							$(".main_left #left").animate({
								'top' : 110 + 'px'
							}, {
								queue : false,
								duration : 450
							});
						} else {
							var endTop = $('#main2-wrapper').outerHeight();
							if (cTop < endTop) {
								$(".main_left #left").animate({
									'top' : cTop + 20 + 'px'
								}, {
									queue : false,
									duration : 450
								});
							} else {
								$(".main_left #left").animate({
									'top' : endTop + 20 + 'px'
								}, {
									queue : false,
									duration : 450
								});
							}
							startTop = 110;
						}
					});

</script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#weathericon").click(function() {
			$("#weatherinfo").toggle();
		});

		$("#airicon").click(function() {
			$("#airinfo").toggle();
		});

		$("#ddday").click(function() {
			$("#ddaylist").toggle();
		});
	})
</script>
<script type="text/javascript">
$(document).ready(function(){    
    
 //--------------------------------검색---------------------------------
    	   console.log("search");
    	   //검색 버틀 클릭
    	   $("#m2btn").click(function() {
    	      location.href="/place/main2?lno=" + ${PlaceOfLoc[0]['place'].lno}+"&search="+$("#m2input").val();
    	   });
    
})
</script>
<script type="text/javascript">

function checkPpcode(pcode) {
	location.href="/place/main2?lno=" + ${PlaceOfLoc[0]['place'].lno} + "&ppcode="+pcode;
}

</script>

  
</head>
<body>
<div id = "select_box">	
			<div id = placeCategory>
				
				<div class ="0" id="all_m" style="font-size: 23px; margin: 0;" onclick="checkPpcode(0);">ALL</div>
				<ul id = "catg_m">
					<li id="cafe" onclick="checkPpcode(1);">cafe</li>
					<li id="restaurant" onclick="checkPpcode(2);">restaurant</li>
					<li id="bar" onclick="checkPpcode(3);">bar</li>
					<li id="inside" onclick="checkPpcode(4);">inside</li>
					<li id="outside" onclick="checkPpcode(5);">outside</li>
				</ul>
			</div>				
			<div id="m2header">
				<p>
					
					<c:choose>
						<c:when test="${PlaceOfLoc[0]['place'].lno eq 1}">
							<strong>강남역</strong>&nbsp;&nbsp;&nbsp;
						</c:when>
						<c:when test="${PlaceOfLoc[0]['place'].lno eq 2}">
							<strong>명동역</strong>&nbsp;&nbsp;&nbsp;
						</c:when>
						<c:when test="${PlaceOfLoc[0]['place'].lno eq 3}">
							<strong>홍대입구역</strong>&nbsp;&nbsp;&nbsp;
						</c:when>
					</c:choose>
					
				</p>
				<div id="m2div">
					<input id="m2input" type="text" placeholder="장소명을 입력하세요">
					<button id="m2btn">검색</button>
				</div>
			</div>
		
		</div>
<div id="main2-wrapper">

<!-- 오른쪽 플로팅바 -->
<div id="plheader-side">
   
		<!-- 날씨바 -->
		<div class="main_left">
			<div id="left">
				<table id="weathertb">
					<tr>
						<td><img id="weathericon" src="" /></td>
						<td id="weatherinfo" style="display: none;">${weather["T3H"]}℃
							${sky } <br>
						</td>
					</tr>
					<tr>
						<td><img id="airicon" src="" /></td>
						<td id="airinfo" style="display: none;">${airinfo }<br>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>	
					

	<!--이미지박스-->
	<div id="maimgbox">
		
				<!-- Main -->
					<div id="mainn">

						<!-- Thumbails -->
							<div class="thumbnails">
							<div>
								<div id = "arrayBox">
								<c:forEach var="pol" items="${PlaceOfLoc}"  begin="0" step = "3">
<!-- 								   <a href="#"> -->
<%-- 										<img src="<%=request.getContextPath()%>/upload/${pol['img'].storedName }" alt="" /> --%>
<%-- 										<div>${pol['place'].pname }</div> --%>
<!-- 									</a> -->
									
									<figure class = "snip1361">
									<img src="<%=request.getContextPath()%>/upload/${pol['img'].storedName }" alt="" />
									<figcaption>
									<h3>${pol['place'].pname }</h3>
<%-- 									<p>${pol['place'].psubtitle }</p> --%>
									<p><img id="car" src="../../images/car.png" width="15px" height="15px">&nbsp; ${pol['place'].pparking }</p>
									<p><c:choose>
					                     <c:when test="${pol.avg_rvstar eq '0.0' }" >☆☆☆☆☆</c:when>
					                     <c:when test="${0.01 <= pol.avg_rvstar && pol.avg_rvstar < 1.5 }" >★☆☆☆☆</c:when>
					                     <c:when test="${1.5 <= pol.avg_rvstar && pol.avg_rvstar < 2.5 }" >★★☆☆☆</c:when>
					                     <c:when test="${2.5 <= pol.avg_rvstar && pol.avg_rvstar < 3.5 }" >★★★☆☆</c:when>
					                     <c:when test="${3.5 <= pol.avg_rvstar && pol.avg_rvstar < 4.5 }" >★★★★☆</c:when>
					                     <c:when test="${4.5 <= pol.avg_rvstar && pol.avg_rvstar <= 5.0 }" >★★★★★</c:when>
					                  </c:choose>					
									${pol.avg_rvstar }</p>
									</figcaption>
									<a href="/place/detail?pno=${pol['place'].pno }"></a>
								</figure>
								</c:forEach>


								</div>
							</div>	
								<div>
								<div id = "arrayBox">
									<c:forEach var="pol" items="${PlaceOfLoc}"  begin="1" step = "3">
<!-- 								   <a href="#"> -->
<%-- 										<img src="<%=request.getContextPath()%>/upload/${pol['img'].storedName }" alt="" /> --%>
<%-- 										<div>${pol['place'].pname }</div> --%>
<!-- 									</a> -->
									
									<figure class = "snip1361">
									<img src="<%=request.getContextPath()%>/upload/${pol['img'].storedName }" alt="" />
									<figcaption>
									<h3>${pol['place'].pname }</h3>
<%-- 									<p>${pol['place'].psubtitle }</p> --%>
									<p><img id="car" src="../../images/car.png" width="15px" height="15px">&nbsp; ${pol['place'].pparking }</p>
									<p><c:choose>
					                     <c:when test="${pol.avg_rvstar eq '0.0' }" >☆☆☆☆☆</c:when>
					                     <c:when test="${0.01 <= pol.avg_rvstar && pol.avg_rvstar < 1.5 }" >★☆☆☆☆</c:when>
					                     <c:when test="${1.5 <= pol.avg_rvstar && pol.avg_rvstar < 2.5 }" >★★☆☆☆</c:when>
					                     <c:when test="${2.5 <= pol.avg_rvstar && pol.avg_rvstar < 3.5 }" >★★★☆☆</c:when>
					                     <c:when test="${3.5 <= pol.avg_rvstar && pol.avg_rvstar < 4.5 }" >★★★★☆</c:when>
					                     <c:when test="${4.5 <= pol.avg_rvstar && pol.avg_rvstar <= 5.0 }" >★★★★★</c:when>
					                  </c:choose>
					                  					
									${pol.avg_rvstar }</p>
									</figcaption>
									<a href="/place/detail?pno=${pol['place'].pno }"></a>
								</figure>
								</c:forEach>
									
									
									</div>
								</div>
								<div>
								<div id = "arrayBox">
									
									<c:forEach var="pol" items="${PlaceOfLoc}"  begin="2" step = "3">									
									<figure class = "snip1361">
									<img src="<%=request.getContextPath()%>/upload/${pol['img'].storedName }" alt="" />
									<figcaption>
									<h3>${pol['place'].pname }</h3>
<%-- 									<p>${pol['place'].psubtitle }</p> --%>
									<p><img id="car" src="../../images/car.png" width="15px" height="15px">&nbsp; ${pol['place'].pparking }</p>
									
									<p><c:choose>
					                     <c:when test="${pol.avg_rvstar eq '0.0' }" >☆☆☆☆☆</c:when>
					                     <c:when test="${0.01 <= pol.avg_rvstar && pol.avg_rvstar < 1.5 }" >★☆☆☆☆</c:when>
					                     <c:when test="${1.5 <= pol.avg_rvstar && pol.avg_rvstar < 2.5 }" >★★☆☆☆</c:when>
					                     <c:when test="${2.5 <= pol.avg_rvstar && pol.avg_rvstar < 3.5 }" >★★★☆☆</c:when>
					                     <c:when test="${3.5 <= pol.avg_rvstar && pol.avg_rvstar < 4.5 }" >★★★★☆</c:when>
					                     <c:when test="${4.5 <= pol.avg_rvstar && pol.avg_rvstar <= 5.0 }" >★★★★★</c:when>
					                  </c:choose>					
									${pol.avg_rvstar }</p>
									</figcaption>
									<a href="/place/detail?pno=${pol['place'].pno }"></a>
								</figure>
								</c:forEach>
									
								</div>	
								</div>
							</div>

					</div>
		
		</div>
	</div>
	<div></div>
	<c:import url="../layout/footer.jsp" />
	
</body>
</html>