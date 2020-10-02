<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<c:import url="../layout/favicon.jsp" />
<title>Insert title here</title>

<link href="../../../css/mypage/LikePlacePlus.css" rel="stylesheet"
	type="text/css" />


<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>


<script type="text/javascript">
	$(document).ready(function() {

		$("#edbtndelete").click(function() { // 삭제 누르면

			console.log("삭제")

			$(".mp1likex").css("display", "inline-block");
			
			
		})

		$(".mp1likex").click(function() { // X 누르면

 			$.ajax({
				type : 'POST',
				url : "/mypage/deletelikeplace",
				data : {
					placename : $(this).parent().children("span").eq(0).text()
				},
				dataType : "json",
				success : function(result) {

					location.reload();

				},
				error : function() {
					console.log('AJAX fail')
				}

			})

		})

	})
</script>

</head>

<body>
	<div id="mp2wrapper">
		<c:import url="../layout/header.jsp" />

		<p id="edp2">우리 찜목록♥</p>

		<span id="edbtndelete">삭제</span>
		<hr id="ed2hr">
		<div id="mp1wrapper2">
			<div id=pickback>
				<div id="pickcoulumn">

					<c:set var="i" value="0" />
					<c:forEach items="${likeplace }" var="likeplace">
						<c:if test="${i == 0}">
							<div id="mp1likecolumn1">
								<a href="#"> <img id="mp1likeimg"
									src="<%=request.getContextPath() %>/upload/${likeplace['pimg'].storedName }"
									alt="" /> <c:if
										test="${likeplace['likeplace'].uno == user.uno}">
										<img id="mp1heart" alt="" src="../../../images/hearticon.svg" />
									</c:if> <c:if test="${likeplace['likeplace'].uno == oppo.uno }">
										<img id="mp1heart" alt="" src="../../../images/bhearticon.svg" />
									</c:if> <c:if test="${likeplace['likeplace'].together == 'O'}">
										<img id="mp1heart" alt="" src="../../../images/phearticon.svg" />
									</c:if> <br> <span id="mp1likename">${likeplace['place'].pname }</span>
									<span class="mp1likex" style="display: none">X</span>
								</a>
							</div>
						</c:if>

						<c:if test="${i == 1}">
							<div id="mp1likecolumn2">
								<a href="#"> <img id="mp1likeimg"
									src="<%=request.getContextPath() %>/upload/${likeplace['pimg'].storedName }"
									alt="" /> <c:if
										test="${likeplace['likeplace'].uno == user.uno}">
										<img id="mp1heart" alt="" src="../../../images/hearticon.svg" />
									</c:if> <c:if test="${likeplace['likeplace'].uno == oppo.uno }">
										<img id="mp1heart" alt="" src="../../../images/bhearticon.svg" />
									</c:if> <c:if test="${likeplace['likeplace'].together == 'O'}">
										<img id="mp1heart" alt="" src="../../../images/phearticon.svg" />
									</c:if> <br> <span id="mp1likename">${likeplace['place'].pname }</span>
									<span class="mp1likex" style="display: none">X</span>
								</a>
							</div>
						</c:if>

						<c:if test="${i == 2}">
							<div id="mp1likecolumn3">
								<a href="#"> <img id="mp1likeimg"
									src="<%=request.getContextPath() %>/upload/${likeplace['pimg'].storedName }"
									alt="" /> <c:if
										test="${likeplace['likeplace'].uno == user.uno}">
										<img id="mp1heart" alt="" src="../../../images/hearticon.svg" />
									</c:if> <c:if test="${likeplace['likeplace'].uno == oppo.uno }">
										<img id="mp1heart" alt="" src="../../../images/bhearticon.svg" />
									</c:if> <c:if test="${likeplace['likeplace'].together == 'O'}">
										<img id="mp1heart" alt="" src="../../../images/phearticon.svg" />
									</c:if> <br> <span id="mp1likename">${likeplace['place'].pname }</span>
									<span class="mp1likex" style="display: none">X</span>
								</a>
							</div>
						</c:if>

						<c:if test="${i == 3}">
							<div id="mp1likecolumn4">
								<a href="#"> <img id="mp1likeimg"
									src="<%=request.getContextPath() %>/upload/${likeplace['pimg'].storedName }"
									alt="" /> <c:if
										test="${likeplace['likeplace'].uno == user.uno}">
										<img id="mp1heart" alt="" src="../../../images/hearticon.svg" />
									</c:if> <c:if test="${likeplace['likeplace'].uno == oppo.uno }">
										<img id="mp1heart" alt="" src="../../../images/bhearticon.svg" />
									</c:if> <c:if test="${likeplace['likeplace'].together == 'O'}">
										<img id="mp1heart" alt="" src="../../../images/phearticon.svg" />
									</c:if> <br> <span id="mp1likename">${likeplace['place'].pname }</span>
									<span class="mp1likex" style="display: none">X</span>
								</a>
							</div>
						</c:if>

						<c:if test="${i == 4}">
							<div id="mp1likecolumn5">
								<a href="#"> <img id="mp1likeimg"
									src="<%=request.getContextPath() %>/upload/${likeplace['pimg'].storedName }"
									alt="" /> <c:if
										test="${likeplace['likeplace'].uno == user.uno}">
										<img id="mp1heart" alt="" src="../../../images/hearticon.svg" />
									</c:if> <c:if test="${likeplace['likeplace'].uno == oppo.uno }">
										<img id="mp1heart" alt="" src="../../../images/bhearticon.svg" />
									</c:if> <c:if test="${likeplace['likeplace'].together == 'O'}">
										<img id="mp1heart" alt="" src="../../../images/phearticon.svg" />
									</c:if> <br> <span id="mp1likename">${likeplace['place'].pname }</span>
									<span class="mp1likex" style="display: none">X</span>
								</a>
							</div>
						</c:if>
						<c:set var="i" value="${i + 1}" />
						<c:if test="${i == 5}">
							<c:set var="i" value="0" />
						</c:if>
					</c:forEach>

				</div>

			</div>

		</div>
		<!-- table + title END -->

	</div>

	<c:import url="../layout/footer.jsp" />

</body>
</html>