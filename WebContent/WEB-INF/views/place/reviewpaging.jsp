 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<div class="text-center">
<ul class="pagination pagination-sm">

	<!-- 이전 페이지로 가기 -->
	<c:if test="${paging.curPage ne 1 }">
	<li><a href="/place/reviewplus?curPage=${paging.curPage - 1 }&pno=${param.pno }">&lt;</a></li>
	</c:if>
	


	<!-- 페이징 리스트 -->
	<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="i">
	
	<!-- 보고 있는 페이지번호 강조 (.active) -->
	<c:if test="${paging.curPage eq i }">
		<li class="active"><a href="place/reviewplus?curPage=${i }&pno=${param.pno }">${i }</a></li>
	</c:if>
	
	<!-- 보고 있는 페이지번호가 아니면 평소 모양으로 보여주기 -->
	<c:if test="${paging.curPage ne i }">
		<li><a href="/place/reviewplus?curPage=${i }&pno=${param.pno }">${i }</a></li>
	</c:if>
	
	</c:forEach>
	
	
	
	<!-- 다음 페이지로 가기 -->
	<c:if test="${paging.curPage ne paging.totalPage }">
	<li><a href="/place/reviewplus?curPage=${paging.curPage + 1 }&pno=${param.pno }">&gt;</a></li>
	</c:if>

</ul>
</div>