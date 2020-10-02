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
		<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
		<thead>
			<tr>
				<th colspan="7" ><h4 class="board-Title">게시물 보기</h4></th>
			</tr>
			<tr>
				<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>제목</h5></td>
				<td colspan="7"><h5>${showQna.title }</h5></td>
			</tr>
			<tr>
				<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성자</h5></td>
				<td colspan="3"><h5><input type="text" value="${name }" name="${name }" readonly></h5></td>
				<td style="background-color: #fafafa; color: #000000; width: 80px;"><h5>작성날짜</h5></td>
				<td><h5>${showQna.qdate }</h5></td>
			</tr>
			<tr>
				<td style="vertical-align: middle; min-height: 150px; background-color: #fafafa; color: #000000; width: 80px;"><h5>글 내용</h5></td>
				<td colspan="7" style="text-align: left;"><h5>${showQna.qcontent }</h5></td>
			</tr>
		</thead>
		<tbody>
		<tr>
			<td colspan="7" style="text-align: right;">
				<a href="/qa/view" class="btn btn-primary">목록</a>
				<c:if test="${bno eq showQna.bno }">
				<a href="/qa/update?qnano=${showQna.qnano }" class="btn btn-primary">수정</a>
				<a href="/qa/delete?qnano=${showQna.qnano }" class="btn btn-primary" onclick="return confirm('정말로 삭제하시겠습니까?');">삭제</a>
				</c:if>
			</td>
		</tr>
			</tbody>
		</table>
	</div>
	<c:import url="../layout/footer.jsp" />
</body>
</html>