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
   
<!-- 네이버 스마트 에디터2 라이브러리  -->
<script type="text/javascript"
   src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<!-- <form>태그의 submit을 수행하면 editor에 작성한 내용을 <textarea>에 반영 -->
<script type="text/javascript">
function submitContents(elClikedObj) {
   
   //에디터의 내용을 #content에 반영
   oEditors.getById["qcontent"].exec("UPDATE_CONTENTS_FIELD", []);
   
   try {
      
      
      elClikedObj.form.submit();
      
      
   } catch(e) {
      
   }
   
   
}
</script> 

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnUpdate").click(function() {
		
		//스마트에디터의 내용으로 <textarea>에 적용시키기
		submitContents($("#btnUpdate"));
		
		//form submit 수행
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
});
</script>


</head>

<body>
<c:import url="../layout/header.jsp" />
<div class="container">
	<form method="post" action="/qa/update">
	<input type="hidden" name="qnano" value="${param.qnano }" />
		<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
		<thead>
			<tr>
				<th colspan="3"><h4>게시물 수정 양식</h4></th>
			</tr>
		</thead>
		
		<tbody>
			<tr>
				<td style="width: 110px;"><h5>작성자</h5></td>
				<td>
					<h5>${name }</h5>
					<input type="hidden" name="bno" value="${param.bno }">
				</td>
			</tr>
			<tr>
				<td style="width: 110px;"><h5>글 제목</h5></td>
				<td>
					<input class="form-control" type="text" maxlength="50" name="title" placeholder="글 제목을 입력하세요." value ="${showQna.title }">
				</td>
			</tr>
			<tr>
				<td style="width: 110px;"><h5>글 내용</h5></td>
				<td>
					<textarea class="form-control" rows="10" name="qcontent" maxlength="2048" placeholder="글 내용을 입력하세요." id="qcontent">${showQna.qcontent }</textarea>
				</td>
			</tr>			
			</tbody>
		</table>
	</form>
	<div style="text-align: right;">	
		<button type="button" id="btnUpdate" class="btn btn-primary">수정 적용</button>
		<button type="button" id="btnCancel" class="btn btn-primary">취소</button>
	</div>
</div>

<!-- 스마트 에디터 적용하는 코드 -->
<!-- <textarea>에 스마트 에디터의 스킨을 입히는 코드  -->
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
   oAppRef: oEditors,
   elPlaceHolder: "qcontent", //에디터가 적용되는 <textarea>의 id를 입력
   sSkinURI: "/resources/se2/SmartEditor2Skin.html",
   fCreator: "createSEditor2"
})
</script>

<c:import url="../layout/footer.jsp" />
</body>
</html>