<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />

<title>리뷰더보기</title>

<link href="../../../css/place/reviewplus.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	//답글보기 누르면 토글
	$(".list-reply").click(function(){
		$(this).next().children(".list-replywrite").toggle(300); //조건
		return false;
	})
	
	//댓글쓰기 버튼 누르면 유지
	
	//이미지 클릭하면 커짐
	
	$(".myImg").click(function(){
		
// 		console.log($(this).width());
		
		if($(this).width() == 120) {
			$(this).css('width', '500px');
			$(this).css('height', '500px');
			$(this).parent().css('height', '500px');
		}
		
		if($(this).width() == 500) {
			$(this).css('width', '120px');
			$(this).css('height', '90px');
			$(this).parent().css('height', '100px');
		}
		
	})
	
})
</script>

<script type="text/javascript">
$(document).ready(function() {
	
	$("#rvbtn").click(function() {
// 			console.log($("#rvbtn").val());
		
		$form = $("<form>").attr({
			action: "/review/insert",
			method: "post"
		}).append(
			$("<input>").attr({
				type:"hidden",
				name:"pno",
				value:"${param.pno }"
			})
		).append(
			$("<textarea>")
				.attr("name", "rvcontent")
				.css("display", "none")
				.text($("#rvcontent").val())
		).append(
			$("<input>")
				.attr({
					type:"hidden"
					, name:"rvstar"
					, value:$("#rvpselect").val()
				})
		);
		
		$(document.body).append($form);
// 		$form.submit();
		
	});
	
});
</script>

</head>
<body>
<!--공통헤더-->
<c:import url="../layout/header.jsp" />
<!-- 본문 -->
<div id="rewrapper">
	<!-- header -->
	<div id="reviewp-header" class="rvcontainer">
		<!-- 상단소개 -->
		<div id="rvptitle">
			<div id="rvptitle2">${place[0]['place'].pname }</div>
		</div>

		<!-- 리뷰 작성하기 -->
		<div id="rvpwrite">
			<div id="rvform">
				<form action="/review/insert" method="post" enctype="multipart/form-data">
				<input type="hidden" name="pno" value="${param.pno }" />
				<div id="rvp1">
					<div class="rvp1">리뷰 작성하기</div>
					<div class="rvp1">
					<select name="rvstar" id="rvpselect" >
						<option value="5" selected>★★★★★ 아주 좋아요</option>
						<option value="4">★★★★☆ 맘에 들어요</option>
						<option value="3">★★★☆☆ 보통이예요</option>
						<option value="2">★★☆☆☆ 그냥 그래요</option>
						<option value="1">★☆☆☆☆ 별로예요</option>
					</select>
					</div>
				</div>
				<div id="rvp2">
					<div id="texta"><textarea id="rvcontent" name="rvcontent" rows="50" cols="50" wrap="hard" placeholder="문의글 혹은 악의적인 비난은 삼가바랍니다."></textarea></div>
					<div id="texta2">
						<div id="rvbtn"><button type="submit" class="rvbtn">리뷰 등록</button></div>
					</div>
					<div id="upload"><input type="file" name="file" /></div>
				</div>
				</form>
			</div>
		</div>
	</div>
	<!-- content -->
	<div id="reviewp-content" class="rvcontainer">
		<!-- 리뷰 목록 -->
		<div id="rvplist">
			<div id="list-title">
				<div class="lt1">리뷰 더보기</div>
				<div class="lt2">( ${cnt }개 )</div>
<!-- 				<div id="list-title-menu"> -->
<!-- 					<div class="ltm2"><a href="#">최신순</a></div> -->
<!-- 					<div class="ltm3"><a href="#">평점순</a></div> -->
<!-- 				</div> -->
			</div>
			<c:forEach items="${reviewlist }" var="rev">
			<div class="list-star">
			<div class="lstar1">
			 	<c:choose>
                     <c:when test="${rev['review'].rvstar eq '1' }" >★☆☆☆☆ 별로예요</c:when>
                     <c:when test="${rev['review'].rvstar eq '2' }" >★★☆☆☆ 그냥 그래요</c:when>
                     <c:when test="${rev['review'].rvstar eq '3' }" >★★★☆☆ 보통이예요</c:when>
                     <c:when test="${rev['review'].rvstar eq '4' }" >★★★★☆ 맘에 들어요 </c:when>
                     <c:when test="${rev['review'].rvstar eq '5' }" >★★★★★ 아주 좋아요</c:when>
                  </c:choose>
				</div><!-- 아주좋아요!  -->
				<div class="lstar2">
				<div class="lstar2-1">${rev.review.rvdate}</div> 
				<div class="lstar2-2">${rev.user.id }</div>
				</div>
			</div>
			<div class="list-img">
				<div><img class="myImg" style="width:120px" src="<%=request.getContextPath() %>/upload/${rev.rvimg.storedname }" onerror="this.style.display='none'"/></div>
			</div>
			<div class="list-cont">
				<div>${rev.review.rvcontent }</div>
			</div>
			<div class="list-reply">
				<div class="lreply"><a href="#">답글 보기</a></div><!--누르면 답글박스 생성  -->
			</div>
			
			<!-- 답글 박스 -->
			<div class="list-border">
			<div class="list-replywrite">
			<c:forEach items="${rvcommentList }" var="rvcomment">
				<c:if test="${rev.review.rno eq rvcomment.rvcomment.rno }">
				<div class="list-replytitle">
					<div class="replytbox">${rvcomment.user.nick }</div>
					<div class="replytbox2">${rvcomment.rvcomment.rv_com_content }</div>
					<div class="replytbox3">${rvcomment.rvcomment.rv_com_date } <button type="button">&times;</button></div>
					
				</div>
				</c:if>
			</c:forEach>
				<form action="/review/comment" method="post">
				<input type="hidden" name="rno" value="${rev.review.rno }" />
				<input type="hidden" name="pno" value="${param.pno }" />
				<div class="replybox"><textarea name="rv_com_content"></textarea></div>	
				<div class="replybox2"><button class="replybtn">등록</button></div>
				</form>
			</div>
		</div>
		</c:forEach>

		</div>
	</div>
	
		<!-- 리뷰페이지 넘기기 -->
		<c:import url="/WEB-INF/views/place/reviewpaging.jsp"/>
</div>
	<!-- 공통푸터 -->
	<c:import url="../layout/footer.jsp" />
</body>
</html>