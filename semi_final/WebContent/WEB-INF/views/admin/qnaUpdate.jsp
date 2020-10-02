<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />

<script type="text/javascript" 
 src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<!-- <form> 태그의 submit을 수행하면 editor에 작성한 내용을 <textarea>에 반영 -->
<script type="text/javascript">
function submitContents(clClickedObj) {
	oEditors.getById["qcontent"].exec("UPDATE_CONTENTS_FIELD",[]);
	
	try{
		//<form>태그의 submit 수행
		elClickedObj.form.submit();
	}catch(e){	}
}
</script>
<script type="text/javascript">
$(document).ready(function() {
	
	console.log("a");
	
	//작성버튼 동작
	$("#btnWrite").click(function() {
		
		//스마트에디터의 내용을 <textared>에 적용하는 함수 호출
		submitContents($("#btnWrite"));
		//<form>태그 submit
		$("#qna").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
});
</script>


<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="../../../css/board3/bootstrap.css">
<link rel="stylesheet" href="../../../css/board3/board.css">

	<meta charset="UTF-8">
</head>
<body>

<body>
<c:import url="../layout/header.jsp" />
	<div class="container">
		<form method="post" action="/admin/qnamodify" id="qna">
		<input type="hidden" name="qnano" value="${list['qna'].qnano }" />
			<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
			<thead>
				<tr>
					<th colspan="3"><h4 class="board-Title">QaA 양식</h4></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td style="width: 110px;"><h5>작성자</h5></td>
					<td><h5>${list['business'].name}</h5><input type="hidden" name="name" value="1">
					</td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>글 제목</h5></td>
					<td>
						<input class="form-control" type="text" maxlength="50" name="title" placeholder="글 제목을 입력하세요." value="${list['qna'].title }">
					</td>
				</tr>
				<tr>
					<td style="width: 110px;"><h5>글 내용</h5></td>
					<td>
						<textarea class="form-control" rows="10" id="qcontent" name="qcontent" maxlength="2048">${list['qna'].qcontent }</textarea>
					</td>
				</tr>			
			
			</tbody>
		</table>
	</form>
	
					<div style="text-align: right;">
						<button class="btn btn-primary" id="btnWrite" type="button" >등록</button>
						<button class="btn btn-primary" id="btnCancel"  type="button">취소</button>
					</div>
				
</div>
<!-- 스마트에디터 적용하는 코드 -->
<!-- <textarea>에 스마트 에디터의 스킨을 입히는 코드 -->
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
   oAppRef : oEditors,
   elPlaceHolder : "qcontent", //에디터가 적용되는 <textarea>의 id 를 입력
   sSkinURI : "/resources/se2/SmartEditor2Skin.html",
   fCreator : "createSEditor2"
   })
</script>
<c:import url="../layout/footer.jsp" />
</body>
</html>