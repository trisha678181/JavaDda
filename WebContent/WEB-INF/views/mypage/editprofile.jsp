<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<c:import url="../layout/favicon.jsp" />
<title></title>

<link href="../../../css/mypage/editprofile.css" rel="stylesheet"
	type="text/css" />

<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">
$(document).ready( function() {

	
	   //작성버튼 동작
	   $("#mp2ok").click(function() {
		   
		   if( $('#mp2i1').val() == '0' || $('#mp2i1').val() == '' || $('#mp2i1').val() == 'undefined' || $('#mp2i1').val() == null ) {
			   $("#mp2pwcheck").css("display", "inline-block");
			   $("#mp2i1").val("");
			   $("#mp2i1").focus();
		   } else {
			 //<form>태그 submit
			 $("form").submit();
		   }
	      
	      
	   });
	   
	   //취소버튼 동작
	   $("#mp2cc").click(function() {
	      history.go(-1);
	   });
	
	/* 사용자 gender 따라서 미리 checked 하기 */
	<c:if test='${user.gender eq "m"}'> // 남자일 때
		$('#mp2male').attr('checked', 'checked');
	</c:if>

	<c:if test='${user.gender eq "f"}'>
		$('#mp2female').attr('checked', 'checked');
	</c:if>

	/* 사용자 선호도 따라서 미리 checked 하기 */
	<c:choose> 
	<c:when test='${user.hot eq 1}'> $('input:radio[name="hot"][value=1]').attr('checked', 'checked'); </c:when>
	<c:when test='${user.hot eq 2}'> $('input:radio[name="hot"][value=2]').attr('checked', 'checked'); </c:when>
	<c:when test='${user.hot eq 3}'> $('input:radio[name="hot"][value=3]').attr('checked', 'checked'); </c:when>
	<c:when test='${user.hot eq 4}'> $('input:radio[name="hot"][value=4]').attr('checked', 'checked'); </c:when>
	<c:when test='${user.hot eq 5}'> $('input:radio[name="hot"][value=5]').attr('checked', 'checked'); </c:when>
	</c:choose>

	<c:choose> 
	<c:when test='${user.cold eq 1}'> $('input:radio[name="cold"][value=1]').attr('checked', 'checked'); </c:when>
	<c:when test='${user.cold eq 2}'> $('input:radio[name="cold"][value=2]').attr('checked', 'checked'); </c:when>
	<c:when test='${user.cold eq 3}'> $('input:radio[name="cold"][value=3]').attr('checked', 'checked'); </c:when>
	<c:when test='${user.cold eq 4}'> $('input:radio[name="cold"][value=4]').attr('checked', 'checked'); </c:when>
	<c:when test='${user.cold eq 5}'> $('input:radio[name="cold"][value=5]').attr('checked', 'checked'); </c:when>
	</c:choose>

	<c:choose>
	<c:when test='${user.rain eq 1}'> $('input:radio[name="rain"][value=1]').attr('checked', 'checked'); </c:when>
	<c:when test='${user.rain eq 2}'> $('input:radio[name="rain"][value=2]').attr('checked', 'checked'); </c:when>
	<c:when test='${user.rain eq 3}'> $('input:radio[name="rain"][value=3]').attr('checked', 'checked'); </c:when>
	<c:when test='${user.rain eq 4}'> $('input:radio[name="rain"][value=4]').attr('checked', 'checked'); </c:when>
	<c:when test='${user.rain eq 5}'> $('input:radio[name="rain"][value=5]').attr('checked', 'checked'); </c:when>
	</c:choose>

	<c:choose>
	<c:when test='${user.snow eq 1}'> $('input:radio[name="snow"][value=1]').attr('checked', 'checked'); </c:when>
	<c:when test='${user.snow eq 2}'> $('input:radio[name="snow"][value=2]').attr('checked', 'checked'); </c:when>
	<c:when test='${user.snow eq 3}'> $('input:radio[name="snow"][value=3]').attr('checked', 'checked'); </c:when>
	<c:when test='${user.snow eq 4}'> $('input:radio[name="snow"][value=4]').attr('checked', 'checked'); </c:when>
	<c:when test='${user.snow eq 5}'> $('input:radio[name="snow"][value=5]').attr('checked', 'checked'); </c:when>
	</c:choose>

	})
</script>

<script type="text/javascript">

$(document).ready( function() {
	
$("#mp2i1").blur(function() { // 입력한 비번 일치하는지
$.ajax({
	type : 'POST'
	, url : "/editprofile/pwcheck" 
	, data : {userPW : $(this).val()}
	, dataType: "json"
	, success : function(result){
		
		if(result.checkPW==false){
			$("#mp2pwcheckfalse").css("display", "inline-block");
			$("#mp2i1").val("");
			$("#mp2i1").focus();
		} else {
			$("#mp2pwcheckfalse").css("display", "none");
		}
		
	}
	
})	
})	

	$("#mp2i6").blur(function() { // 새 비번 
		var userpw = /^[A-Za-z0-9_@#$&*?!][A-Za-z_@#$&*?!][A-Za-z0-9_@#$&*?!]{5,}/
		if (!userpw.test($(this).val())) {
			$("#mp2pwchange2").css("display", "inline-block");
			$("#mp2pwchange").css("display", "none");
			$("#mp2i6").val("");
			$("#mp2i6").focus();
			
		} else {
			$("#mp2pwchange2").css("display", "none");
		}
	})

	$("#mp2i7").blur(function() { // 새 비번 확인
		if ($("#mp2i6").val() != $("#mp2i7").val()) {
			$("#mp2pwcheck").css("display", "inline-block")
			$("#mp2i7").val("");
			$("#mp2i6").val("");
			$("#mp2i6").focus();
			
		} else {
			$("#mp2pwcheck").css("display", "none");
		}
	})
	
})

</script>

</head>

<body>
	<c:import url="../layout/header.jsp" />

	<div id="mp2wrapper">
		<div id="mp2title">프로필 편집</div>
		<hr id="mp2hr">
		
		<div id = profileForm>

		<form id="form1" action="/mypage/editprofile" method="post"
			enctype="multipart/form-data">
			<table id="mp2table">
				<tr>
					<td><div>이름</div></td>
					<td>
						<div id="mp2td2">
							<span class="mp2card card1">${user.name }</span>
						</div>
					</td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						<div id="mp2td2">
							<input type="radio" id="mp2male" name="male" disabled="disabled">&nbsp;&nbsp;남&emsp;&emsp;&emsp;&emsp;&emsp;
							<input type="radio" id="mp2female" name="female"
								disabled="disabled">&nbsp;&nbsp;여
						</div>
					</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td>
						<div id="mp2td2">
							<span class="mp2card card2">${user.birth.substring(0,4)}</span>년&ensp;
							<span class="mp2card card3">${user.birth.substring(5,7)}</span>월&ensp;
							<span class="mp2card card3">${user.birth.substring(8,10)}</span>일
						</div>
					</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td>
						<div id="mp2td2">
							<span class="mp2card card1">${user.id }</span>
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="mp2i1">비밀번호</label></td>
					<td>
						<div id="mp2td2">
							<input id="mp2i1" type="password" name="pw"
								class="mp2card card1 in" required="required"> <img
								id="mp2eye" src="../../../images/eyex.svg" onclick="pweye(this)">
						</div>
						<div id="mp2pwcheckfalse" style="display: none">비밀번호가 일치하지
							않습니다!</div>
							<div id="mp2pwcheck" style="display: none">필수 입력값입니다.</div>
					</td>
				</tr>
				<tr>
					<td style="width:105.33"><label for="mp2i6">새 비밀번호</label></td>
					<td><div id="mp2td2">
							<input id="mp2i6" type="password" name="newpw"
								class="mp2card card1 in"> <img id="mp2eye2"
								src="../../../images/eyex.svg" onclick="pweye2(this)">
						</div>
						<div id="mp2pwchange">비밀번호 변경하고 싶으시면 기입해주세요.</div>
						<div id="mp2pwchange2" style="display: none">8자 이상의 대·소문자,
							숫자, 특수문자(_@#$&*?!)의 조합을 사용하세요.</div></td>
				</tr>
				<tr>
					<td style="width:105.33"><label for="mp2i7">새 비밀번호 확인</label></td>
					<td><div id="mp2td2">
							<input id="mp2i7" type="password" name="newpwcheck"
								class="mp2card card1 in"> <img id="mp2eye3"
								src="../../../images/eyex.svg" onclick="pweye3(this)">
						</div>
						<div id="mp2pwcheck" style="display: none">비밀번호와 동일하게 입력하세요</div>
					</td>
				</tr>
				</div>
				<tr>
					<td><label for="mp2i3">닉네임</label></td>
					<td><div id="mp2td2">
							<input id="mp2i3" type="text" name="nick"
								class="mp2card card1 in" value="${user.nick }">
						</div></td>
				</tr>
				<tr>
					<td><label for="mp2i4">휴대전화</label></td>
					<td><div id="mp2td2">
							<input id="mp2i4" type="text" name="phone"
								class="mp2card card1 in" value="${user.phone }">
						</div></td>
				</tr>
				<tr>
					<td><label for="mp2i5">이메일</label></td>
					<td><div id="mp2td2">
							<input id="mp2i5" type="email" name="mail"
								class="mp2card card1 in" value="${user.mail }">
						</div></td>
				</tr>
				<tr style="height: 20px;"></tr>
				<tr>
					<td>더위</td>
					<td><div id="mp2td2" class="mp2like">
							<label><input class="mp2radio" type="radio" name="hot"
								value=1> 매우 싫음&nbsp;</label> <label><input
								class="mp2radio" type="radio" name="hot" value=2>
								싫음&nbsp;</label> <label><input class="mp2radio" type="radio"
								name="hot" value=3> 보통&nbsp;</label> <label><input
								class="mp2radio" type="radio" name="hot" value=4>
								좋음&nbsp;</label> <label><input class="mp2radio" type="radio"
								name="hot" value=5> 매우 좋음&nbsp;</label>
						</div></td>
				</tr>
				<tr>
					<td>추위</td>
					<td><div id="mp2td2" class="mp2like">
							<label><input class="mp2radio" type="radio" name="cold"
								value=1> 매우 싫음&nbsp;</label> <label><input
								class="mp2radio" type="radio" name="cold" value=2>
								싫음&nbsp;</label> <label><input class="mp2radio" type="radio"
								name="cold" value=3> 보통&nbsp;</label> <label><input
								class="mp2radio" type="radio" name="cold" value=4>
								좋음&nbsp;</label> <label><input class="mp2radio" type="radio"
								name="cold" value=5> 매우 좋음&nbsp;</label>
						</div></td>
				</tr>
				<tr>
					<td>비</td>
					<td><div id="mp2td2" class="mp2like">
							<label><input class="mp2radio" type="radio" name="rain"
								value=1> 매우 싫음&nbsp;</label> <label><input
								class="mp2radio" type="radio" name="rain" value=2>
								싫음&nbsp;</label> <label><input class="mp2radio" type="radio"
								name="rain" value=3> 보통&nbsp;</label> <label><input
								class="mp2radio" type="radio" name="rain" value=4>
								좋음&nbsp;</label> <label><input class="mp2radio" type="radio"
								name="rain" value=5> 매우 좋음&nbsp;</label>
						</div></td>
				</tr>
				<tr>
					<td>눈</td>
					<td><div id="mp2td2" class="mp2like">
							<label><input class="mp2radio" type="radio" name="snow"
								value=1> 매우 싫음&nbsp;</label> <label><input
								class="mp2radio" type="radio" name="snow" value=2>
								싫음&nbsp;</label> <label><input class="mp2radio" type="radio"
								name="snow" value=3> 보통&nbsp;</label> <label><input
								class="mp2radio" type="radio" name="snow" value=4>
								좋음&nbsp;</label> <label><input class="mp2radio" type="radio"
								name="snow" value=5> 매우 좋음&nbsp;</label>
						</div></td>
				</tr>
				<tr style="height: 20px;"></tr>
				<tr>
					<td>프로필사진</td>
					<td><div id="mp2td2">
							<label><input type="image" id="mp2pfimg"
								name="profileimg"
								src="<%=request.getContextPath() %>/upload/${user.uprofileimg }">
								<br> <input type="file" id="mpfile" name="profileimgupload"></label>
						</div></td>
				</tr>
				<tr>
					<td>배경사진</td>
					<td><div id="mp2td2">
							<label><input type="image" id="mp2bgimg" name="bgimg"
								src="<%=request.getContextPath() %>/upload/${user.ubgimg }">
								<input type="file" name="bgimgupload"></label>
						</div></td>
				</tr>
			</table>
					<div id="mp2tt">
			회원정보는 개인정보처리방침에 따라 안전하게 보호되며, 회원님의 명백한 동의 없이 공개 또는 제 3자에게 제공되지 않습니다.
			<a>개인정보처리방침</a>
		</div>
		</form>
		<div id="mp2btn2">
			<input id="mp2ok" type="submit" value="확인"> <span id="mp2cc"><a
				href="javascript:history.back();">취소</a></span>
		</div>

	</div>
	</div>

	<c:import url="../layout/footer.jsp" />

	<script src="../../../js/mypage/editprofile.js"></script>

</body>
</html>