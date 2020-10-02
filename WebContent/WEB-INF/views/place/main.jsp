<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />

<title></title>

<link href="../../../css/place/main.css" rel="stylesheet"
	type="text/css" media="all" />

<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">

$(document).ready( function() {
	
	<c:if test= '${res eq 0}'> // 커플아니면
	$("#dday").css("display", "none");
	</c:if>
	
<c:if test='${map["SKY"] eq 1 && map["PTY"] eq 0}'> // 맑음
	<c:set var="sky" value="맑음" />
	$('#weathericon').prop('src', '<%=request.getContextPath()%>/images/weather/sun.svg');

</c:if>

<c:if test='${map["SKY"] eq 3 && map["PTY"] eq 0}'> // 구름 많음
	<c:set var="sky" value="구름 많음" />
	$('#weathericon').attr('src', '<%=request.getContextPath()%>/images/weather/cas.svg');

</c:if>

<c:if test='${map["SKY"] eq 4 && map["PTY"] eq 0}'> // 흐림
	<c:set var="sky" value="흐림" />
	$('#weathericon').attr('src', '<%=request.getContextPath()%>/images/weather/cloud.svg');

</c:if>

<c:if test='${map["PTY"] eq 1 || map["PTY"] eq 4}'> // 비, 소나기
	<c:set var="sky" value="비" />
$('#weathericon').attr('src', '<%=request.getContextPath()%>/images/weather/rain.svg');

</c:if>

<c:if test='${map["PTY"] eq 2}'> // 비눈
	<c:set var="sky" value="진눈깨비" />
$('#weathericon').attr('src', '<%=request.getContextPath()%>/images/weather/sleet.svg');

</c:if>

<c:if test='${map["PTY"] eq 3}'> // 눈
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
							var endTop = $('#main-wrapper').outerHeight();
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
$(document).ready(function() {
	$(".maheart").click(function() {
		console.log($(this).parent().parent().parent().children().eq(1).children().eq(0).text());
		
		var i = $(this)
		
		if($(this).context.src == 'http://localhost:8083/images/pl-heart-unpick.png') {

		$.ajax({
			type : 'POST'
			, url : "/addlikeplace" 
			, data : {placename : $(this).parent().parent().parent().children().eq(1).children().eq(0).text()}
			, dataType: "json"
			, success : function(result){
				
				console.log("돌아옴")
				console.log($(i))
				
				$(i).attr("src", "../../../images/pl-heart-pick.png");
				
			}
			, error: function() {
				console.log('AJAX fail')
			}
			
		})	
		} else if($(this).context.src == 'http://localhost:8083/images/pl-heart-pick.png') {

			$.ajax({
				type : 'POST'
				, url : "/mypage/deletelikeplace" 
				, data : {placename : $(this).parent().parent().parent().children().eq(1).children().eq(0).text()}
				, dataType: "json"
				, success : function(result){
					
					console.log("여기도 가니?")
					
					$(i).attr("src", "../../../images/pl-heart-unpick.png");
				}
				, error: function() {
					console.log('AJAX fail')
				}
				
			})	
		}
		
	});
})
</script>

<script type="text/javascript">
	$(document).ready(function() {
		var bannerLeft = 0;
		var first = 1;
		var last;
		var imgCnt = 0;
		var $img = $(".mapickimg");
		var $first;
		var $last;

		$img.each(function() {
			$(this).css("left", bannerLeft);
			bannerLeft += $(this).width();
			$(this).attr("id", "banner" + (++imgCnt));
		});

		if (imgCnt > 4) {

			last = imgCnt;

			setInterval(function() {
				$img.each(function() {
					$(this).css("left", $(this).position().left - 1);
				});
				$first = $("#banner" + first);
				$last = $("#banner" + last);
				if ($first.position().left < -200) {
					$first.css("left", $last.position().left + $last.width());
					first++;
					last++;
					if (last > imgCnt) {
						last = 1;
					}
					if (first > imgCnt) {
						first = 1;
					}
				}
			}, 50);
		}
	})
</script>

<script type="text/javascript">
window.onclick = function(event) {
	
	console.log(event.target);
	
}
</script>

<script type="text/javascript">
	$("#maheart1").click(function() {
		console.log("클릭!");
		
	});
</script>

	<c:import url="../layout/header.jsp" />

</head>
<!-- ************************************************************************************  -->

<body>

	<!--공통헤더-->


<!-- 본문 -->
<div id="main-wrapper">

		<!--dday-->
		<div id="dday" >
			<div id="ddday">${names[0] }
				<font style="color: red">♥</font> ${names[1] } &emsp;D + ${firstday }
			</div>

			<div id="ddaylist">
				<ul class="hide">
					<c:forEach var="anni" items="${list}">
						<li id="annili">
							<div id="annititle">${anni.title }</div>
							<div id="annirestday">D-${anni.rest_day }</div>
							<div id="anniday">${anni.av_date }</div>
						</li>
					</c:forEach>
				</ul>
			</div>

		</div>



		<!-- 날씨바 -->
		<div class="main_left">
			<div id="left">
				<table id="weathertb">
					<tr>
						<td><img id="weathericon" src="" /></td>
						<td id="weatherinfo" style="display: none;">${map["T3H"]}℃
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


		<!-- 지도오오오오 -->
<!-- 		<img id="mapimg" src="../../../images/map.png"> -->
		<div id="map">
			<div id="mm1" data-md-tooltip="강남역" OnClick="location.href='/place/main2?lno=1';"></div>
			<div id="mm2" data-md-tooltip="명동역" OnClick="location.href='/place/main2?lno=2';"></div>
			<div id="mm3" data-md-tooltip="홍대입구역" OnClick="location.href='/place/main2?lno=3';"></div>
		</div>
		<div id="b1-title">
				<div>
					<h3>관리자 pick♥</h3>
				</div>
			</div>
			

		<div id="slider">
			<!-- 관리자픽 -->
			<div id="mapick">
				<a href="/place/detail?pno=${pickmap[0]['place'].pno }"><img
					class="mapickimg"
					src="<%request.getContextPath(); %>/upload/${pickmap[0]['pimg'].storedName }" /></a>
				<a href="/place/detail?pno=${pickmap[1]['place'].pno }"><img
					class="mapickimg"
					src="<%request.getContextPath(); %>/upload/${pickmap[1]['pimg'].storedName }" /></a>
				<a href="/place/detail?pno=${pickmap[2]['place'].pno }"><img
					class="mapickimg"
					src="<%request.getContextPath(); %>/upload/${pickmap[2]['pimg'].storedName }" /></a>
				<a href="/place/detail?pno=${pickmap[3]['place'].pno }"><img
					class="mapickimg"
					src="<%request.getContextPath(); %>/upload/${pickmap[3]['pimg'].storedName }" /></a>
				<a href="/place/detail?pno=${pickmap[4]['place'].pno }"><img
					class="mapickimg"
					src="<%request.getContextPath(); %>/upload/${pickmap[4]['pimg'].storedName }" /></a>
			</div>
		</div>


		<!-- BEST 카페-->
		<!-- 카테고리 카페인거 찜목록 순으로 정렬 -->
		<div id="mabest1">
			<div id="b1-title">
				<div>
					<h3>요즘 뜨는 카페</h3>
				</div>
			</div>
			<form id="mmainform" name="mmainform" method="post" action="/place/main">
			<div id="b1-content">
				<div class="bestcolumn">
					<div class="best-img">
						<img
							src="<%request.getContextPath(); %>/upload/${cafelist[0]['pimg'].storedName}" />
						<div class="best-btn">
						
						<c:if test="${personallp[0] eq 1 }">
							<img class="maheart" style="cursor:pointer" src="../../../images/pl-heart-pick.png" />
        				</c:if>
        				<c:if test="${personallp[0] eq 0 }">
							<img class="maheart" style="cursor:pointer" src="../../../images/pl-heart-unpick.png" />
        				</c:if>
        				
						</div>
					</div>
					<div class="best-content">
						<div class="con1" style="cursor:pointer" OnClick="location.href='/place/detail?pno=${cafelist[0]['place'].pno}';">${cafelist[0]['place'].pname}</div>
						<div class="con2">${cafelist[0]['place'].psubtitle }</div>
						<div class="con3">${cafelist[0]['place'].ploc }</div>
						<div class="con4">
							<img src="../../../images/pl-heart-pick.png" />
							<div>${cafelist[0]['place'].plike }</div>
						</div>
					</div>
				</div>
				<div class="bestcolumn">
					<div class="best-img">
						<img
							src="<%request.getContextPath(); %>/upload/${cafelist[1]['pimg'].storedName}" />
						<div class="best-btn">
						
						<c:if test="${personallp[1] eq 1 }">
							<img class="maheart" style="cursor:pointer" src="../../../images/pl-heart-pick.png" />
        				</c:if>
        				<c:if test="${personallp[1] eq 0 }">
							<img class="maheart" style="cursor:pointer" src="../../../images/pl-heart-unpick.png" />
        				</c:if>
        				
						</div>
					</div>
					<div class="best-content">
						<div class="con1" style="cursor:pointer" OnClick="location.href='/place/detail?pno=${cafelist[1]['place'].pno}';">${cafelist[1]['place'].pname}</div>
						<div class="con2">${cafelist[1]['place'].psubtitle }</div>
						<div class="con3">${cafelist[1]['place'].ploc }</div>
						<div class="con4">
							<img src="../../../images/pl-heart-pick.png" />
							<div>${cafelist[1]['place'].plike }</div>
						</div>
					</div>
				</div>
				<div class="bestcolumn">
					<div class="best-img">
						<img
							src="<%request.getContextPath(); %>/upload/${cafelist[2]['pimg'].storedName}" />
						<div class="best-btn">
						
						<c:if test="${personallp[2] eq 1 }">
							<img class="maheart" style="cursor:pointer" src="../../../images/pl-heart-pick.png" />
        				</c:if>
        				<c:if test="${personallp[2] eq 0 }">
							<img class="maheart" style="cursor:pointer" src="../../../images/pl-heart-unpick.png" />
        				</c:if>
        				
						</div>
					</div>
					<div class="best-content">
						<div class="con1" style="cursor:pointer" OnClick="location.href='/place/detail?pno=${cafelist[2]['place'].pno}';">${cafelist[2]['place'].pname}</div>
						<div class="con2">${cafelist[2]['place'].psubtitle }</div>
						<div class="con3">${cafelist[2]['place'].ploc }</div>
						<div class="con4">
							<img src="../../../images/pl-heart-pick.png" />
							<div>${cafelist[2]['place'].plike }</div>
						</div>
					</div>
				</div>
			</div>
			</form>
		</div>

		<!-- BEST 액티비티 -->
		<!-- 카테로기 실내,야외 놀거리 찜 갯수 순으로 정렬 -->
		<div id="mabest2">
			<div id="b2-title">
				<div>
					<h3>요즘 뜨는 놀거리</h3>
				</div>
			</div>
			<div id="b2-content">
				<div class="bestcolumn">
					<div class="best-img">
						<img
							src="<%request.getContextPath(); %>/upload/${actlist[0]['pimg'].storedName}" />
						<div class="best-btn">
						
						<c:if test="${personallp[3] eq 1 }">
							<img class="maheart" style="cursor:pointer" src="../../../images/pl-heart-pick.png" />
        				</c:if>
        				<c:if test="${personallp[3] eq 0 }">
							<img class="maheart" style="cursor:pointer" src="../../../images/pl-heart-unpick.png" />
        				</c:if>
        				
						</div>
					</div>
					<div class="best-content">
						<div class="con1" style="cursor:pointer" OnClick="location.href='/place/detail?pno=${actlist[0]['place'].pno}';">${actlist[0]['place'].pname}</div>
						<div class="con2">${actlist[0]['place'].psubtitle }</div>
						<div class="con3">${actlist[0]['place'].ploc }</div>
						<div class="con4">
							<img src="../../../images/pl-heart-pick.png" />
							<div>${actlist[0]['place'].plike }</div>
						</div>
					</div>
				</div>
				<div class="bestcolumn">
					<div class="best-img">
						<img
							src="<%request.getContextPath(); %>/upload/${actlist[1]['pimg'].storedName}" />
						<div class="best-btn">
						
						<c:if test="${personallp[4] eq 1 }">
							<img class="maheart" style="cursor:pointer" src="../../../images/pl-heart-pick.png" />
        				</c:if>
        				<c:if test="${personallp[4] eq 0 }">
							<img class="maheart" style="cursor:pointer" src="../../../images/pl-heart-unpick.png" />
        				</c:if>
        				
						</div>
					</div>
					<div class="best-content">
						<div class="con1" style="cursor:pointer" OnClick="location.href='/place/detail?pno=${actlist[1]['place'].pno}';">${actlist[1]['place'].pname}</div>
						<div class="con2">${actlist[1]['place'].psubtitle }</div>
						<div class="con3">${actlist[1]['place'].ploc }</div>
						<div class="con4">
							<img src="../../../images/pl-heart-pick.png" />
							<div>${actlist[1]['place'].plike }</div>
						</div>
					</div>
				</div>
				<div class="bestcolumn">
					<div class="best-img">
						<img
							src="<%request.getContextPath(); %>/upload/${actlist[2]['pimg'].storedName}" />
						<div class="best-btn">
						
						<c:if test="${personallp[5] eq 1 }">
							<img class="maheart" style="cursor:pointer" src="../../../images/pl-heart-pick.png" />
        				</c:if>
        				<c:if test="${personallp[5] eq 0 }">
							<img class="maheart" style="cursor:pointer" src="../../../images/pl-heart-unpick.png" />
        				</c:if>
        				
						</div>
					</div>
					<div class="best-content">
						<div class="con1" style="cursor:pointer" OnClick="location.href='/place/detail?pno=${actlist[2]['place'].pno}';">${actlist[2]['place'].pname}</div>
						<div class="con2">${actlist[2]['place'].psubtitle }</div>
						<div class="con3">${actlist[2]['place'].ploc }</div>
						<div class="con4">
							<img src="../../../images/pl-heart-pick.png" />
							<div>${actlist[2]['place'].plike }</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 공통푸터 -->
	<c:import url="../layout/footer.jsp" />
</body>
</html>