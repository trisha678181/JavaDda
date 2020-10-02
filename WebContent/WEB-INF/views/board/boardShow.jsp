<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />

<title>자유게시판 상세보기</title>

<link rel="stylesheet" href="../../../css/board3/bootstrap.css">
<link rel="stylesheet" href="../../../css/board3/board.css">

<!-- jQuery 2.2.4 -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<meta charset="UTF-8">

<script type="text/javascript">
$(document).ready(function() {
	
	$("#like_update").click(function() {
// 		console.log("like_update");
		$.ajax({
			url: "/board/like",
			type: "POST",
			data: {
				brno: ${showBoard.brno} 
			},
			success: function(result) {
				$("#like_count").html(result);
			}
			
		});
	
	});
	
});
</script>

<script type="text/javascript">
$(document).ready(function() {
	// 댓글 입력
	$("#btnCommInsert").click(function() {
		// 게시글 번호.... ${viewBoard.boardno }
// 			console.log($("#commentWriter").val());
			console.log($("#brcontent").val());
		
		$form = $("<form>").attr({
			action: "/board/replyInsert",
			method: "post"
		}).append(
			$("<input>").attr({
				type:"hidden",
				name:"brno",
				value:"${showBoard.brno }"
			})
		).append(
			$("<input>").attr({
				type:"hidden",
				name:"uno",
				value:"${uno }"
			})
		).append(
			$("<textarea>")
				.attr("name", "brcontent")
				.css("display", "none")
				.text($("#brcontent").val())
		);
		$(document.body).append($form);
		$form.submit();
		
	});
	
});

//댓글 삭제
function deleteComment(brcmno) {
	console.log("delete");
	$.ajax({
		type: "post"
		, url: "/board/replyDelete"
		, dataType: "json"
		, data: {
			brcmno: brcmno
		}
		, success: function(data){
			if(data.success) {
				
				alert("댓글 삭제하시겠습니까?");
				$("[data-brcmno='"+brcmno+"']").remove();
// 				location.href = "/board/show?brno=${param.brno}";
				location.reload("/board/show?brno=${param.brno}");
				
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
		<table class="table table-bordered table-hover"
			style="text-align: center; border: 1px solid #dddddd">
			<thead>
				<tr>
					<th colspan="8"><h4 class="board-Title">게시물 보기</h4></th>
				</tr>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>제목</h5></td>
					<td colspan="8"><h5>${showBoard.brtitle }</h5></td>
				</tr>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성자</h5></td>
					<td colspan="8"><h5>${nick }</h5></td>
				</tr>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성날짜</h5></td>
					<td><h5>${showBoard.brdate }</h5></td>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>조회수</h5></td>
					<td><h5>${showBoard.brviewcnt }</h5></td>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>댓글</h5></td>
					<td><h5>${replyCount }</h5></td>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>좋아요</h5></td>
					<td><h5>
							<span id="like_count">${showBoard.brlike }</span>
						</h5></td>
				</tr>
				<tr>
					<td
						style="vertical-align: middle; min-height: 150px; background-color: #fafafa; color: #000000; width: 80px;"><h5>글
							내용</h5></td>
					<td colspan="8" style="text-align: left;"><h5>${showBoard.brcontent }</h5></td>
				</tr>
				<tr>
					<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>첨부파일</h5></td>
					<td colspan="8"><c:forEach items="${brImgList }" var="brImg">
							<h5>
								<a href="/upload/${brImg.storeName }"
									download="${brImg.originName }">${brImg.originName }</a>
							</h5>
						</c:forEach></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="8" style="text-align: right;"><a
						href="/board/view" class="btn btn-primary">목록</a>
						<button class="btn btn-primary" id="like_update"
							name="like_update">좋아요</button> <c:if
							test="${uno eq showBoard.uno }">
							<a href="/board/update?brno=${showBoard.brno }"
								class="btn btn-primary">수정</a>
							<a href="/board/delete?brno=${showBoard.brno }"
								class="btn btn-primary"
								onclick="return confirm('정말로 삭제하시겠습니까?');">삭제</a>
						</c:if></td>
				</tr>
			</tbody>
		</table>

		<!-- 댓글 입력 폼 -->
		<div>
			<table class="table table-bordered table-hover"
				style="text-align: center; border: 1px solid #dddddd">
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>아이디</h5> <input type="hidden"
							name="uno" value="uno"> <input type="hidden"
							name="brcmno" value="brcmno"></td>
						<td>
							<!-- 							<span style="padding-left: 650px;" id="brdate">날짜</span><br> -->
							<textarea class="form-control" rows="2" cols="60" id="brcontent"
								name="brcontent" maxlength="2048" placeholder="글 내용을 입력하세요."
								style="width: 780px; display: inline-block;"></textarea>
							<button id="btnCommInsert" type="submit" class="btn pull-right"
								style="margin-top: 20px;">등록</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<hr style="border-top: 1px solid #de6562;">

		<!-- 댓글 조회 -->
		<div style="padding-left: 20px;">
			<table style="text-align: center;">
				<tbody>
					<tr>
						<td><h4>총 댓글의 수 : ${replyCount }</h4></td>
					</tr>
					<c:forEach items="${commentList }" var="comment">
						<tr class="reply_detail" data-brcmno="${comment.comment.brcmno }">
							<td style="width: 110px;"><h5>${comment.user.id }</h5></td>
							<td><span style="padding-left: 650px;">${comment.comment.brdate }</span><br>
								<h5 style="width: 780px; display: inline-block;">${comment.comment.brcontent }</h5>
								<c:if test="${id eq comment.user.id }">
									<button type="button" class="btn pull-right"
										style="margin-top: 20px;"
										onclick="deleteComment(${comment.comment.brcmno});">&times</button>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>



	<c:import url="../layout/footer.jsp" />



</body>
</html>