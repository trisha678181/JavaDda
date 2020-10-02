<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" 
 src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<c:import url="../layout/favicon.jsp" />

<title></title>
	
<script type="text/javascript">
$(document).ready(function() {
	// 댓글 입력
	$("#btnCommInsert").click(function() {
		// 게시글 번호.... ${viewBoard.boardno }
// 			console.log($("#commentWriter").val());
			console.log($("#qna_con_content").val());
		
		$form = $("<form>").attr({
			action: "/admin/replyInsert",
			method: "post"
		}).append(
			$("<input>").attr({
				type:"hidden",
				name:"qnano",
				value:"${list['qna'].qnano }"
			})
		).append(
			$("<input>").attr({
				type:"hidden",
				name:"ano",
				value: 1
			})
		).append(
			$("<textarea>")
				.attr("name", "qna_con_content")
				.css("display", "none")
				.text($("#qna_con_content").val())
		);
		$(document.body).append($form);
		$form.submit();
		
	});
	
});

//댓글 삭제
function deleteComment(qnacmno) {
	console.log("delete");

	var delcheck = confirm("댓글 삭제하시겠습니까?");

	if(delcheck) {
	$.ajax({
		type: "post"
		, url: "/admin/replyDelete"
		, dataType: "json"
		, data: {
			qnacmno: qnacmno
		}
		, success: function(data){
			if(data.success) {
				
				$("[data-qnacmno='"+qnacmno+"']").remove();
				
			} else {
				alert("댓글 삭제 실패");
			}
		}
		, error: function() {
			console.log("error");
		}
	});
	}
}
</script>
	
<link rel="stylesheet" href="../../../css/board3/bootstrap.css">
<link rel="stylesheet" href="../../../css/board3/board.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<title></title>

<link href="../../../css/admin/main.css" rel="stylesheet"
	type="text/css" media="all" />
<script type="text/javascript" src="../../../js/admin/admin_main.js"></script>
<meta charset="UTF-8">
</head>


<body>
	<c:import url="../layout/header.jsp" />
	
	<div id="adminBox">
		<!-- 헤더와 푸터 사이에 공간 잡아주는 div -->

		<div id="admin_menu">
			<!-- 메뉴바 div -->
			<ul>
				<li>
					<!-- class="current_page_item"> --> <a href="/admin/main"><img
						src="../../../images/profile.png" alt="profile" /> 회원관리 </a>
				</li>
				<li><a href="#"><img src="../../../images/pencil.png"
						alt="profile" /> 글관리</a>
					<ul>
						<li><a href="/admin/noticelist">공지사항</a></li>
						<li><a href="/admin/reviewlist">REVIEW</a></li>
						<li><a href="/admin/qnalist">1:1문의</a></li>
					</ul></li>
				<li><a href="#"><img src="../../../images/navigation.png"
						alt="profile" /> 장소관리</a>
					<ul>
						<li><a href="/admin/placelist">장소리스트</a></li>
						<li><a href="/admin/businesslist">사업자리스트</a></li>
						
					</ul></li>
			</ul>
		</div>
		<!-- 메뉴바 div END -->
	
<div class="container" >
		<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd; width:735px; float: right; ">
		<thead>
			<tr>
				<th colspan="8"><h4 class="board-Title">QNA 상세보기</h4></th>
			</tr>
			<tr>
				<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>제목</h5></td>
				<td colspan="8"><h5>${list['qna'].title }</h5></td>
			</tr>
			<tr>
			<td style="background-color: #fafafa; color: #000000; width: 100px;"><h5>작성자</h5></td>
				<td><h5>${list['business'].name }</h5></td>
				<td style="background-color: #fafafa; color: #000000; width: 70px;"><h5>작성날짜</h5></td>
				<td><h5>${list['qna'].qdate }</h5></td>
			</tr>
			<tr>
				<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>글 내용</h5></td>
				<td colspan="8"><h5>${list['qna'].qcontent }</h5></td>
			</tr>
		</thead>
		<tbody>
		<tr>
			<td colspan="8" style="text-align: right;">
				<a href="/admin/qnalist" class="btn btn-primary">목록</a>
				<a href="/admin/qnadelete?qnano=${list['qna'].qnano }" class="btn btn-primary" onclick="return confirm('정말로 삭제하시겠습니까?');">삭제</a>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
		<!-- 댓글 입력 폼 -->
		<div>
			<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>아이디</h5> 
						<input type="hidden" name="id" value="id"> 
						<input type="hidden" name="qnacmno" value="qnacmno"></td>
						<td>
<!-- 							<span style="padding-left: 650px;" id="qna_con_date">날짜</span><br> -->
								<textarea class="form-control" rows="2" cols="60" id="qna_con_content" name="qna_con_content" maxlength="2048" placeholder="글 내용을 입력하세요." style="width: 780px; display: inline-block;"></textarea>
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
				<c:forEach items="${commentList }" var="comment">
					<tr class="reply_detail" data-qnacmno="${comment.comment.qnacmno }">
						<td style="width: 110px;"><h5>관리자</h5></td>
						<td>							
							<span style="padding-left: 650px;">${comment.comment.qna_con_date }</span><br>
							<h5 style="width: 780px; display: inline-block;">${comment.comment.qna_con_content }</h5>
							<button type="button" class="btn pull-right" style="margin-top: 20px;"
								onclick="deleteComment(${comment.comment.qnacmno });">&times</button>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		</div>
	<!-- 회원 리스트 div END -->
	<c:import url="../layout/footer.jsp" />
	
	
	
</body>
</html>