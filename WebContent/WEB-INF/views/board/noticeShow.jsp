<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />

<title></title>

<link rel="stylesheet" href="../../../css/board3/bootstrap.css">
<link rel="stylesheet" href="../../../css/board3/board.css">

	<meta charset="UTF-8">
<!-- jQuery 2.2.4 -->
<script type="text/javascript" 
 src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
	
<script type="text/javascript">
$(document).ready(function() {
	// 댓글 입력
	$("#btnCommInsert").click(function() {
		// 게시글 번호.... ${viewBoard.boardno }
// 			console.log($("#commentWriter").val());
			console.log($("#nt_con_content").val());
		
		$form = $("<form>").attr({
			action: "/notice/replyInsert",
			method: "post"
		}).append(
			$("<input>").attr({
				type:"hidden",
				name:"noticeno",
				value:"${showNotice.noticeno }"
			})
		).append(
			$("<input>").attr({
				type:"hidden",
				name:"uno",
				value: "${uno }"
			})
		).append(
			$("<textarea>")
				.attr("name", "nt_con_content")
				.css("display", "none")
				.text($("#nt_con_content").val())
		);
		$(document.body).append($form);
		$form.submit();
		
	});
	
});

//댓글 삭제
function deleteComment(ntcmno) {
	console.log("delete");
	$.ajax({
		type: "post"
		, url: "/notice/replyDelete"
		, dataType: "json"
		, data: {
			ntcmno: ntcmno
		}
		, success: function(data){
			if(data.success) {
				
				alert("댓글 삭제하시겠습니까?");
				$("[data-ntcmno='"+ntcmno+"']").remove();
// 				location.href = "/notice/show?noticeno=${param.noticeno}"
	            location.reload("/notice/show?noticeno=${param.noticeno}");
						
			} else {
				alert("댓글 삭제 실패");
			}
		}
		, error: function() {
			console.log("error");
		}
	});
}
</script>
	
	
	
</head>
<body>
<c:import url="../layout/header.jsp" />
<div class="container">
		<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
		<thead>
			<tr>
				<th colspan="10" ><h4 class="board-Title">게시물 보기</h4></th>
			</tr>
			<tr>
				<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>제목</h5></td>
				<td colspan="10"><h5>${showNotice.title }</h5></td>
			</tr>
			<tr>
				<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성자</h5></td>
				<td colspan="3"><h5>관리자</h5></td>
				<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성날짜</h5></td>
				<td><h5>${showNotice.ntdate }</h5></td>
				<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>조회수</h5></td>
				<td><h5>${showNotice.hit }</h5></td>
				<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>댓글수</h5></td>
				<td><h5>${replyCount }</h5></td>
			</tr>
			<tr>
				<td style="vertical-align: middle; min-height: 150px; background-color: #fafafa; color: #000000; width: 80px;"><h5>글 내용</h5></td>
				<td colspan="10" style="text-align: left;"><h5>${showNotice.ntcontent }</h5></td>
			</tr>
		</thead>
		<tbody>
		<tr>
			<td colspan="10" style="text-align: right;">
				<a href="/notice/view" class="btn btn-primary">목록</a>
			</td>
		</tr>
		</tbody>
	</table>
	
	<!-- 댓글 입력 폼 -->
		<div>
			<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>아이디</h5> 
						<input type="hidden" name="id" value="id"> 
						<input type="hidden" name="ntcmno" value="ntcmno"></td>
						<td>
<!-- 							<span style="padding-left: 650px;" id="nt_con_date">날짜</span><br> -->
								<textarea class="form-control" rows="2" cols="60" id="nt_con_content" name="nt_con_content" maxlength="2048" placeholder="글 내용을 입력하세요." style="width: 780px; display: inline-block;"></textarea>
								<button id="btnCommInsert" type="submit" class="btn pull-right" style="margin-top: 20px;">등록</button>
						</td>
						</tr>
					</tbody>
				</table>
		</div><hr style="border-top: 1px solid #de6562;">
	
		<!-- 댓글 조회 -->
		<div style="padding-left: 20px;">
			<table style="text-align: center;">
				<tbody>
				<tr><td><h4>총 댓글의 수  : ${replyCount }</h4></td></tr>
				<c:forEach items="${commentList }" var="comment">
					<tr class="reply_detail" data-ntcmno="${comment.comment.ntcmno }">
						<td style="width: 110px;"><h5>${comment.user.id }</h5></td>
						<td>							
							<span style="padding-left: 650px;">${comment.comment.nt_con_date }</span><br>
							<h5 style="width: 780px; display: inline-block;">${comment.comment.nt_con_content }</h5>
								<c:if test="${id eq comment.user.id }">
								<button type="button" class="btn pull-right" style="margin-top: 20px;"
									onclick="deleteComment(${comment.comment.ntcmno});">&times</button>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
</div>
	
	<c:import url="../layout/footer.jsp" />
</body>
</html>