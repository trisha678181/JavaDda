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
</head>

<body>
<c:import url="../layout/header.jsp" />
<div class="container">
	<form method="post" action="/notice/update" enctype="multipart/form-data">
		<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
		<thead>
			<tr>
				<th colspan="3"><h4>게시물 수정 양식</h4></th>
			</tr>
		</thead>
		
		<tbody>
			<tr>
				<td style="width: 110px;"><h5>아이디</h5></td>
				<td>
					<h5>작성자-유예지(아이디)</h5>
					<input type="hidden" name="userID" value="작성자 아이디">
					<input type="hidden" name="boardID" value="게시물번호">
				</td>
			</tr>
			<tr>
				<td style="width: 110px;"><h5>글 제목</h5></td>
				<td>
					<input class="form-control" type="text" maxlength="50" name="boardTitle" placeholder="글 제목을 입력하세요." value ="게시물제목">
				</td>
			</tr>
			<tr>
				<td style="width: 110px;"><h5>글 내용</h5></td>
				<td>
					<textarea class="form-control" rows="10" name="boardContent" maxlength="2048" placeholder="글 내용을 입력하세요.">게시물내용</textarea>
				</td>
			</tr>		
			<tr>
				<td style="text-align: left;" colspan="3"><h5 style="color: red;"></h5>
						<input class="btn btn-primary pull-right" type="submit" value="수정">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<c:import url="../layout/footer.jsp" />
</body>
</html>