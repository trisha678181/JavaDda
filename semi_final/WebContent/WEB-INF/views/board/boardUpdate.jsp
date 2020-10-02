<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />

<title>자유게시판 수정</title>

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
   oEditors.getById["brcontent"].exec("UPDATE_CONTENTS_FIELD", []);
   
   try {
      
      
      elClikedObj.form.submit();
      
      
   } catch(e) {
      
   }
   
   
}
</script>   

<script type="text/javascript">
$(document).on("click", ".pic1", function() {
	  var file = $(this)
	    .parent()
	    .parent()
	    .parent()
	    .find("#file1");
	  file.trigger("click");
	})
$(document).on("change", "#file1", function() {
	  $(this)
	    .parent()
	    .find(".fc1")
	    .val(
	      $(this)
	        .val()
	        .replace(/C:\\fakepath\\/i, "")
	);
})

$(document).on("click", ".pic2", function() {
	  var file = $(this)
	    .parent()
	    .parent()
	    .parent()
	    .find("#file2");
	  file.trigger("click");
	})
$(document).on("change", "#file2", function() {
	  $(this)
	    .parent()
	    .find(".fc2")
	    .val(
	      $(this)
	        .val()
	        .replace(/C:\\fakepath\\/i, "")
	);
})

$(document).on("click", ".pic3", function() {
	  var file = $(this)
	    .parent()
	    .parent()
	    .parent()
	    .find("#file3");
	  file.trigger("click");
	})
$(document).on("change", "#file3", function() {
	  $(this)
	    .parent()
	    .find(".fc3")
	    .val(
	      $(this)
	        .val()
	        .replace(/C:\\fakepath\\/i, "")
	);
})
</script>


<script type="text/javascript">
$(document).ready(function() {
	
	$(document).on("click", ".btnDelete", function() {
		
		//prev() : 이전 요소 선택
		//prop() : 지정한 선택자를 가진 첫번째 요소의 속성값을 가져오거나 속성값을 추가
		//disabled : submit()을 할 경우 disabled지정한 부분의 값은 넘기지 않음
		//	treue : disabled 속성을 활성화
		//	false : disabled 속성을 비활성화
		console.log($(this));
		
		if( $(this).prev().prop("disabled") ) { 
			$(this).prev().attr("disabled", false); //값을 넘김
			$(this).html("취소")
// 			$("#deleteline").css("text-decoration", "line-through");
		} else {
			$(this).prev().attr("disabled", true); //값을 넘기지 않음
			$(this).html("삭제")
// 			$("#deleteline").css("text-decoration", "none");
		}
	})
	
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
		<form method="post" action="/board/update" enctype="multipart/form-data">
		<input type="hidden" name="brno" value="${param.brno }" />
			<table class="table table-bordered table-hover"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="8"><h4 class="board-Title">게시물 보기</h4></th>
					</tr>
					<tr>
						<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>글 제목</h5></td>
						<td colspan="8"><h5> <input class="form-control" type="text" maxlength="50" name="brtitle" placeholder="글 제목을 입력하세요." value="${showBoard.brtitle }"></h5></td>
					</tr>
					<tr>
						<td
							style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성자</h5></td>
						<td colspan="8"><h5>${nick }</h5></td>
					</tr>
					<tr>
						<td
							style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성날짜</h5></td>
						<td><h5>${showBoard.brdate }</h5></td>
						<td
							style="background-color: #fafafa; color: #000000; width: 80px;"><h5>조회수</h5></td>
						<td><h5>${showBoard.brviewcnt }</h5></td>
						<td
							style="background-color: #fafafa; color: #000000; width: 80px;"><h5>댓글</h5></td>
						<td><h5>0</h5></td>
						<td
							style="background-color: #fafafa; color: #000000; width: 80px;"><h5>좋아요</h5></td>
						<td><h5>${showBoard.brlike }</h5></td>
					</tr>
					<tr>
						<td style="vertical-align: middle; min-height: 150px; background-color: #fafafa; color: #000000; width: 80px;"><h5>글내용</h5></td>
						<td colspan="8" style="text-align: left;">
							<textarea class="form-control" id="brcontent" name="brcontent" placeholder="글 내용을 입력하세요.">${showBoard.brcontent }</textarea>
						</td>
					</tr>
					<tr>
						<td
							style="background-color: #fafafa; color: #000000; width: 80px;"><h5>첨부파일</h5></td>
						<td colspan="8">
						<c:forEach items="${brImgList }" var="brImg">
								<h5>
<!-- 								<span class="deleteline"> -->
									<a href="/upload/${brImg.storeName }"
										download="${brImg.originName }">${brImg.originName }</a>
										<input type="hidden" name="delitem" value='${brImg.brino }' disabled="disabled"/>
										<button type="button" class="btnDelete" class="btn btn-primary">삭제</button>
<!-- 								</span> -->
								</h5>
						</c:forEach>
						
						<input type="file" name="file1" class="file" id="file1">
							<div class="input-group col-xs-12">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-picture"></i></span> <input type="text"
									class="form-control input-lg fc1" disabled
									placeholder="파일을 업로드하세요"> <span class="input-group-btn">
									<button class="browse btn btn-primary input-lg pic1"
										type="button">
										<i class="glyphicon glyphicon-search"></i>파일 찾기
									</button>
								</span>
							</div>
						</td>
					</tr>
				</thead>
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
   elPlaceHolder: "brcontent", //에디터가 적용되는 <textarea>의 id를 입력
   sSkinURI: "/resources/se2/SmartEditor2Skin.html",
   fCreator: "createSEditor2"
})
</script>

<c:import url="../layout/footer.jsp" />
</body>
</html>