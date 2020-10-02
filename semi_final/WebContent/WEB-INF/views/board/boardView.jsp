<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />

<title>자유게시판 목록</title>

    <link rel="stylesheet" href="../../../css/board3/bootstrap.css">
    <link rel="stylesheet" href="../../../css/board3/board.css">

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
    
   <meta charset="UTF-8">
   
<script type="text/javascript">
$(document).ready(function() {
   console.log("search");
   //검색 버틀 클릭
   $("#btnSearch").click(function() {
      location.href="/board/view?search="+$("#search").val();
   });
});
</script>   
   
</head>

<body>
<c:import url="../layout/header.jsp" />
<div class="container" style="margin-bottom:15px;">
<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
   <thead>
      <tr>
         <th colspan="8"><h4 class="board-Title">자유 게시판</h4></th>
      </tr>
      <tr>
         <th style="background-color: #fafafa; color: #000000; width: 70px;"><h5>번호</h5></th>
         <th style="background-color: #fafafa; color: #000000;"><h5>제목</h5></th>
         <th style="background-color: #fafafa; color: #000000;"><h5>작성자</h5></th>
         <th style="background-color: #fafafa; color: #000000; width: 100px;"><h5>작성 날짜</h5></th>
         <th style="background-color: #fafafa; color: #000000; width: 70px;"><h5>조회수</h5></th>
         <th style="background-color: #fafafa; color: #000000; width: 70px;"><h5>댓글</h5></th>
         <th style="background-color: #fafafa; color: #000000; width: 70px;"><h5>좋아요</h5></th>
      </tr>
   </thead>
   <tbody>
   <c:forEach items="${boardList }" var="board">
      <tr>
         <td>${board.board.brno }</td>
         <td style="text-align: left;">
            <a href="/board/show?brno=${board.board.brno }"><span aria-hidden="true"></span>
               ${board.board.brtitle }
            </a>
         </td>
         <td>${board.user.id}</td>
         <td>${board.board.brdate }</td>
         <td>${board.board.brviewcnt }</td>
         <td>${board.cnt_comment }</td>
         <td>${board.board.brlike }</td>
      </tr>
   </c:forEach>
      <tr>
         <td colspan="8">
            <div class="btn-toolbar form-inline" role="toolbar" aria-label="Toolbar with button groups">
                <div class="input-group">
                     <input type="text" id="search" name="search" class="form-control" placeholder="검색어를 입력">
                     <span class="input-group-btn">
                       <button id="btnSearch" class="btn btn-default" type="button">검색</button>
                     </span>
                 </div>
               <a href="/board/write" class="btn btn-primary pull-right" style="back" type="sumbit">글쓰기</a>
               <ul class="pagination" style="margin: 0 auto;"></ul>
            </div>
         </td>
      </tr>
   </tbody>
</table>
<c:import url="/WEB-INF/views/layout/paging.jsp" />
</div>
<c:import url="../layout/footer.jsp" />
</body>
</html>