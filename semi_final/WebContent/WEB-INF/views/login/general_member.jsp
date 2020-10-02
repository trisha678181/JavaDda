<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
 response.setHeader("Cache-Control","no-store"); 
 response.setHeader("Pragma","no-cache"); 
%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />
<link rel="stylesheet" type="text/css" href="../../../css/login/general_member.css">
<link rel="stylesheet" type="text/css" href="../../../css/login/account_footer.css">
<meta charset="UTF-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src = "../../../js/httpRequest.js"></script>
<style type="text/css">
input{
	letter-spacing: 2px;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	

	// 	아이디
	$("#inputInfoID").blur(function() {

	
		var userid = /[a-zA-Z0-9][a-zA-Z][a-zA-Z0-9]{4,12}$/;
		if (!userid.test($(this).val())) {
			$("#alertId").css("display", "inline-block");
			$("#inputInfoID").val("")
// 			$("#inputInfoID").focus()
		} else {
			$("#alertId").css("display", "none");
			
			
		}
		$.ajax({
			type : 'POST'
			, url : "/id/check" 
			, data : {userId : $(this).val()}
			, success : function(result){
				console.log(result.checkId)

				if(result.checkId){
					$("#inputInfoID").val("")
// 					$("#inputInfoID").focus()
					$("#overlapId").css("display", "inline-block");
				}else{
					$("#overlapId").css("display", "none");
				}
				
			}
						
		})


		
	})
	
	//닉네임
	$("#inputNick").blur(function() {
		
		var usernick = /[A-Za-z0-9_@#$&*?!가-힣]{4,12}$/;
		if (!usernick.test($(this).val())) {
			$("#alertNick").css("display", "inline-block");
// 			$("#inputNick").val("")
// 			$("#inputNick").focus()
		} else {
			$("#alertNick").css("display", "none");
			
			
		}
// 		console.log('---nick---')
// 		console.log($(this).val())
		$.ajax({
			type : 'POST'
			, url : "/nick/check" 
			, data : {userNick : $(this).val()}
			, success : function(result){
				
// 					console.log("result.checkNick"+result.checkNick)
				
									
				if(result.checkNick==false){
					$("#alertNick").css("display", "inline-block");
					$("#inputNick").val("")
// 					$("#inputNick").focus()
				}else{
					$("#alertNick").css("display", "none");
				}
				
			}
			
			
		})	
		
	})

	//비밀번호
	$("#inputInfoPassword").blur(function() {
		checkp = 0;
		var userpw = /^[A-Za-z0-9_@#$&*?!][A-Za-z_@#$&*?!][A-Za-z0-9_@#$&*?!]{5,}/
		if (!userpw.test($(this).val())) {
			$("#alertpw").css("display", "inline-block");
			$("#inputInfoPassword").val("")
// 			$("#inputInfoPassword").focus()
			
		} else {
			$("#alertpw").css("display", "none");
			checkp++;
		}
	})
	//비밀번호 확인
	$("#inputInfoPasswordRe").blur(function() {
		checkp = 0;
		if ($("#inputInfoPassword").val() != $(
				"#inputInfoPasswordRe").val()) {
			$("#checkpw").css("display", "inline-block")
			$("#inputInfoPasswordRe").val("")
			$("#inputInfoPassword").val("")
			$("#inputInfoPassword").focus()
			
		} else {
			$("#checkpw").html("&nbsp;")
			checkp++;
		}
	})
	//이름
	$("#inputInfoName").blur(function() {
		checkp = 0;
		var username = /^[가-힣]{2,12}|[a-zA-Z]{2,30}/
		if (!username.test($(this).val())) {
			$("#alertname").css("display", "inline-block");
			$("#inputInfoName").val("")
// 			$("#inputInfoName").focus()
			
		} else {
			$("#alertname").html("&nbsp;")
			checkp++;
		}
	})
	
	//이메일
	$("#inputInfoEmail").blur(function() {
		checkp = 0;
		var userEmail = /^[0-9a-zA-Z]([-_\.!]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i
		if (!userEmail.test($(this).val())) {
			$("#alerEmail").css("display", "inline-block");
			$("#inputInfoEmail").val("")
// 			$("#inputInfoEmail").focus()
			
		} else {
			$("#alertEmail").html("&nbsp;")
			checkp++;
		}
		$.ajax({
			type : 'POST'
			, url : "/email/check" 
			, data : {email : $(this).val()}
			, success : function(result){
				
				console.log("result.checkEmail : "+result.checkEmail)
				
									
				if(result.checkEmail== true){
					$("#overlapemail").css("display","inline-block")
					$("#inputInfoEmail").val("")
// 					$("#inputInfoEmail").focus()
				}else{
					$("#overlapemail").css("display", "none");
					
					
				}
				
			}
			
			
		})
	})
	
	$("#inputInfoPhone").blur(function() {
		checkp = 0;
		var userphone = /^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$/
		if (!userphone.test($(this).val())) {
			$("#alerPhone").css("display", "inline-block");
			$("#inputInfoPhone").val("")
// 			$("#inputInfoPhone").focus()
			
		} else {
			$("#alertPhone").html("&nbsp;")
			checkp++;
		}
	})
	
	$("#inputInfoAnsw").blur(function() {
		checkp = 0;
		var userAnsw =/^[가-힣a-zA-Z\.\d]{1,20}/
		if (!userAnsw.test($(this).val())) {
			$("#alertAnsw").css("display", "inline-block");
			$("#inputInfoAnsw").val("")
// 			$("#inputInfoAnsw").focus()
			
		} else {
			$("#alertAnsw").html("&nbsp;")
			checkp++;
		}
								
	})
	
		
	$("#btnJoin").click(function() {
	
		console.log("a");
		
		if($("#inputInfoID").val() == null || $("#inputInfoID").val() == ''){
			alert("아이디는 필수 입력 값 입니다")
		}
		else if($("#inputInfoPassword").val() == null || $("#inputInfoPassword").val() ==''){
			alert("비밀번호는 필수 입력 값 입니다")
		}
		else if($("#inputInfoPasswordRe").val() == null || $("#inputInfoPasswordRe").val() ==''){
			alert("비밀번호 확인이 필요 합니다")
		}
		else if($("#inputInfoName").val() == null || $("#inputInfoName").val() == ''){
			alert("이름은 필수 입력 값 입니다")
		}
		else if($("#inputInfoEmail").val() == null || $("#inputInfoEmail").val() ==''){
			alert("이메일은 필수 입력 값 입니다")
		}
		else if($("#inputInfoPhone").val() == null || $("#inputInfoPhone").val() ==''){
			alert("핸드폰번호는 필수 입력 값 입니다")
		}
		else if($("#inputInfoAnsw").val() == null ||$("#inputInfoA").val() == ''){
			alert("답변은 필수 입력 값 입니다")
		}
		else{
		$(this).parents("form").submit();	
		}
		
})
			

})
</script>


<title>일반회원가입</title>

</head>

<body id="general_accout">

	<div class="gen_warp" id="gen_warp">
		<div id="header">
			<a href="../../../views/place/placemain.jsp" class="logo"> <img
				src="../../../images/whiteLogoCool2.png" width="160" height="65" />
			</a>
		</div>

		<div id="gen_container">
			<form id="gen_account_form" action="/account/general" method="post">
				<input type="hidden" id="sendEmailCheck" class="m_check"
					value="true" checked="checked">
				<div class="join_step_2">
					<div class="step_header">
						<h2>가입 정보 입력하기</h2>
					</div>
					<div class="box_info_write">
						<dl class="write_input">
							<dt class="info_id">
								<label class="title" for="inputInfoID">아이디</label> <span
									class="red_bullet">필수 항목</span>
							</dt>
							<dd>
								<span class="text_box"> <!-- on : 활성화 --> 
								<input type="text" id="inputInfoID" class="input_text valid mustValid mustwt"
									maxlength="12" name="userId" placeholder="6-12자의 영문 소문자와 숫자">

								</span>

								<div id=alertId class="text_alert" style="display: none;">
									<p>6~12자의 영문 대소문자와 숫자를 사용해주세요</p>
								</div>
								<div id=overlapId class="text_alert" style="display: none;">
									<p>사용불가능한 아이디 입니다.</p>
								</div>
							</dd>

							<dt class="info_id">
								<label class="title" for="inputNick">닉네임</label>
							</dt>
							<dd>
								<span class="text_box"> <!-- on : 활성화 --> 
								<input type="text" id="inputNick"
									class="input_text valid mustValid" maxlength="12"
									name="userNick">
								</span>
								<div id=alertNick class="text_alert" style="display: none;">
									<p>사용이 불가능한 닉네임입니다.(12글자 이하)</p>
								</div>

							</dd>


							<dt class="info_pw">
								<label class="title" for="inputInfoPassword">비밀번호</label> <span
									class="red_bullet">필수 항목</span>
							</dt>
							<dd>
								<span class="text_box"> <input type="password"
									id="inputInfoPassword" class="input_text valid mustValid mustwt"
									name="userPass" placeholder="8자 이상,대/소문자, 숫자, 특수문자(_@#$&*?!)">

								</span>
								<div id="alertpw" class="text_alert" style="display: none;">
									<p>8자 이상의 대·소문자, 숫자, 특수문자(_@#$&*?!)의 조합을 사용하세요</p>
								</div>
							</dd>
							<dt class="info_pw_r">
								<label class="title" for="inputInfoPasswordRe">비밀번호 재확인</label>
								<span class="red_bullet">필수 항목</span>
							</dt>
							<dd>
								<span class="text_box"> <!-- warning : 입력 사항에 문제 있음 --> <input
									type="password" id="inputInfoPasswordRe"
									class="input_text valid mustValid mustwt" name="userPassConfirm"
									placeholder="비밀번호를 한번 더 입력해주세요." />
								</span>

								<div id=checkpw class="text_alert" style="display: none;">
									<p>비밀번호와 동일하게 입력하세요</p>
								</div>
							</dd>
						</dl>
					</div>
					<div class="box_info_write">
						<dl class="write_input">
							<dt class="info_name">
								<label class="title" for="inputInfoName">이름</label> <span
									class="red_bullet">필수 항목</span>
							</dt>
							<dd>
								<span class="text_box"> <input type="text"
									id="inputInfoName" class="input_text valid mustValid mustwt"
									name="inputInfoName" placeholder="이름을 적어주세요.">
								</span>
								<div id=alertname class="text_alert" style="display: none;">
									<p>한글 2-16자</p>
								</div>
							</dd>
							<dt class="info_birth">
								<label class="title" for="inputInfoBirthYear">생년월일</label>
							</dt>
							<dd>
								<div class="select_birthdate">
									<span class="select_year"> <!-- on : 활성화 --> <select
										class="valid" id="inputInfoBirthYear" name="birthYear">
											<option selected=" ">년</option>




											<option>2020</option>

											<option>2019</option>

											<option>2018</option>

											<option>2017</option>

											<option>2016</option>

											<option>2015</option>

											<option>2014</option>

											<option>2013</option>

											<option>2012</option>

											<option>2011</option>

											<option>2010</option>

											<option>2009</option>

											<option>2008</option>

											<option>2007</option>

											<option>2006</option>

											<option>2005</option>

											<option>2004</option>

											<option>2003</option>

											<option>2002</option>

											<option>2001</option>

											<option>2000</option>

											<option>1999</option>

											<option>1998</option>

											<option>1997</option>

											<option>1996</option>

											<option>1995</option>

											<option>1994</option>

											<option>1993</option>

											<option>1992</option>

											<option>1991</option>

											<option>1990</option>

											<option>1989</option>

											<option>1988</option>

											<option>1987</option>

											<option>1986</option>

											<option>1985</option>

											<option>1984</option>

											<option>1983</option>

											<option>1982</option>

											<option>1981</option>

											<option>1980</option>

											<option>1979</option>

											<option>1978</option>

											<option>1977</option>

											<option>1976</option>

											<option>1975</option>

											<option>1974</option>

											<option>1973</option>

											<option>1972</option>

											<option>1971</option>

											<option>1970</option>

											<option>1969</option>

											<option>1968</option>

											<option>1967</option>

											<option>1966</option>

											<option>1965</option>

											<option>1964</option>

											<option>1963</option>

											<option>1962</option>

											<option>1961</option>

											<option>1960</option>

											<option>1959</option>

											<option>1958</option>

											<option>1957</option>

											<option>1956</option>

											<option>1955</option>

											<option>1954</option>

											<option>1953</option>

											<option>1952</option>

											<option>1951</option>

											<option>1950</option>

											<option>1949</option>

											<option>1948</option>

											<option>1947</option>

											<option>1946</option>

											<option>1945</option>

											<option>1944</option>

											<option>1943</option>

											<option>1942</option>

											<option>1941</option>

											<option>1940</option>

											<option>1939</option>

											<option>1938</option>

											<option>1937</option>

											<option>1936</option>

											<option>1935</option>

											<option>1934</option>

											<option>1933</option>

											<option>1932</option>

											<option>1931</option>

											<option>1930</option>

											<option>1929</option>

											<option>1928</option>

											<option>1927</option>

											<option>1926</option>

											<option>1925</option>

											<option>1924</option>

											<option>1923</option>

											<option>1922</option>

											<option>1921</option>

											<option>1920</option>

											<option>1919</option>

											<option>1918</option>

											<option>1917</option>

											<option>1916</option>

											<option>1915</option>

											<option>1914</option>

											<option>1913</option>

											<option>1912</option>

											<option>1911</option>

											<option>1910</option>

											<option>1909</option>

											<option>1908</option>

											<option>1907</option>

											<option>1906</option>

											<option>1905</option>

											<option>1904</option>

											<option>1903</option>

											<option>1902</option>

											<option>1901</option>

											<option>1900</option>

									</select>
									</span> <span class="select_month"> <select class="valid"
										id="inputInfoBirthMonth" name="birthMonth">
											<option selected="">월</option>
											<option>01</option>
											<option>02</option>
											<option>03</option>
											<option>04</option>
											<option>05</option>
											<option>06</option>
											<option>07</option>
											<option>08</option>
											<option>09</option>
											<option>10</option>
											<option>11</option>
											<option>12</option>
									</select>
									</span> <span class="select_day"> <input
										class="valid mustValid" id="inputInfoBirthDay"
										name="birthDate" placeholder="일" />
									</span>
								</div>

							</dd>

							<div>
								<dl>
									<dt class="info_gender">
										<span class="title">성별</span> <span class="red_bullet">필수항목</span>
									</dt>

									<dd>
										<div class="check_gender" id="gender">
											<span> <!-- selected : 체크됨 --> 
											<input type="radio" name="inputInfoGender" id="inputInfoGenderMen" value="m" checked="checked">
												<label for="inputInfoGenderMen"> <span class="icon"><b></b></span>남자
											</label>
											</span> <span> <input type="radio" name="inputInfoGender"
												id="inputInfoGenderWomen" value="f"> <label
												for="inputInfoGenderWomen"> <span class="icon"><b></b></span>여자
											</label>
											</span> <input type="hidden" id="inputGender"
												class="valid mustValid" name="gender" value="">
										</div>
										<div id="alertgender" class="text_alert"
											style="display: none;">
											<p>성별을 선택해 주시기 바랍니다.</p>
										</div>
									</dd>
								</dl>
							</div>


							<div>
								<dl>
									<dt class="info_hot">
										<span class="title">더위</span> <span class="red_bullet">필수항목</span>
									</dt>

									<dd>
										<div id="mp2td2" class="mp2like">
											<label><input class="mp2radio" type="radio"
												name="hot" value=1 checked="checked"> 매우 싫음&nbsp;</label> 
											<label><input class="mp2radio" type="radio" 
												name="hot" value=2>싫음&nbsp;</label> 
											<label><input class="mp2radio" type="radio"
												name="hot" value=3> 보통&nbsp;</label> 
											<label><input class="mp2radio" type="radio" 
												name="hot" value=4>좋음&nbsp;</label> 
											<label><input class="mp2radio" type="radio"
												name="hot" value=5> 매우 좋음&nbsp;</label>
										</div>
										<div class="text_alert" style="display: none;">
											<p>선호도를 선택해 주세요</p>
										</div>
									</dd>
								</dl>


								<dl>
									<dt class="info_hot">
										<span class="title">추위</span> <span class="red_bullet">필수항목</span>
									</dt>

									<dd>
										<div id="mp2td2" class="mp2like">
											<label><input class="mp2radio" type="radio"
												name="cold" value=1 checked="checked"> 매우 싫음&nbsp;</label> <label><input
												class="mp2radio" type="radio" name="cold" value=2>
												싫음&nbsp;</label> <label><input class="mp2radio" type="radio"
												name="cold" value=3> 보통&nbsp;</label> <label><input
												class="mp2radio" type="radio" name="cold" value=4>
												좋음&nbsp;</label> <label><input class="mp2radio" type="radio"
												name="cold" value=5> 매우 좋음&nbsp;</label>
										</div>
									</dd>
								</dl>


								<dl>
									<dt class="info_hot">
										<span class="title">비</span> <span class="red_bullet">필수항목</span>
									</dt>

									<dd>
										<div id="mp2td2" class="mp2like">
											<label><input class="mp2radio" type="radio"
												name="rain" value=1 checked="checked"> 매우 싫음&nbsp;</label> <label><input
												class="mp2radio" type="radio" name="rain" value=2>
												싫음&nbsp;</label> <label><input class="mp2radio" type="radio"
												name="rain" value=3> 보통&nbsp;</label> <label><input
												class="mp2radio" type="radio" name="rain" value=4>
												좋음&nbsp;</label> <label><input class="mp2radio" type="radio"
												name="rain" value=5> 매우 좋음&nbsp;</label>
										</div>
									</dd>
								</dl>


								<dl>
									<dt class="info_hot">
										<span class="title">눈</span> <span class="red_bullet">필수항목</span>
									</dt>

									<dd>
										<div id="mp2td2" class="mp2like">
											<label><input class="mp2radio" type="radio"
												name="snow" value=1 checked="checked"> 매우 싫음&nbsp;</label> <label><input
												class="mp2radio" type="radio" name="snow" value=2>
												싫음&nbsp;</label> <label><input class="mp2radio" type="radio"
												name="snow" value=3> 보통&nbsp;</label> <label><input
												class="mp2radio" type="radio" name="snow" value=4>
												좋음&nbsp;</label> <label><input class="mp2radio" type="radio"
												name="snow" value=5> 매우 좋음&nbsp;</label>
										</div>
										<div class="text_alert" style="display: none;">
											<p>선호도를 선택해 주세요</p>
										</div>
									</dd>
								</dl>



							</div>


							<dt class="info_email">
								<label class="title" for="inputInfoEmail">이메일</label> 
								<span class="red_bullet">필수 항목</span>
							</dt>
							<dd>
								<span class="text_box"> <input type="email"
									id="inputInfoEmail" class="input_text valid mustValid mustwt"
									name="email" placeholder="이메일 주소를입력하세요" />
								</span>
								<div id ="alertEmail" class="text_alert" style="display: none;">
									<p>이메일 형식이 잘못되었습니다</p>
								</div>
								<div id=overlapemail class="text_alert" style="display: none;">
									<p>사용불가능한 이메일 입니다.</p>
								</div>
							</dd>

							<dt class="info_Phone">
								<label class="title" for="inputInfoPhone">핸드폰번호</label> <span
									class="red_bullet">필수 항목</span>
							</dt>
							<dd>
								<span class="text_box"> 
								<input type="text" id="inputInfoPhone" class="input_text valid mustValid mustwt"
									name="Phone" placeholder="전화 번호를 입력해 주세요" />
								</span>
								<div id="alertPhone" class="text_alert" style="display: none;">
									<p>전화 번호 형식이 잘못 되었습니다.</p>
								</div>
							</dd>

							<dt class="info_Q">
								<label class="title" for="inputInfoQ">본인인증 질문</label> <span
									class="red_bullet">필수 항목</span>
							</dt>
							<dd>
								<span class="text_box"> 
								<select class="select_Q" id="select_Q" name="select_Q">
										<option selected="selected">질문을 선택해주세요.</option>
										<option>내가 태어난 곳은?</option>
										<option>나의 가장 친한친구는?</option>
										<option>나의 소중한 보물 1호는?</option>
										<option>내가 가장 좋아하는 음식은?</option>
										<option>나의 소원은?</option>
										<option>나의 꿈은?</option>

								</select>
								</span>

							</dd>

							<dt class="info_QnA">
								<label class="title" for="inputInfoAnsw">답변</label> <span
									class="red_bullet">필수 항목</span>
							</dt>
							<dd>
								<span class="text_box"> 
								<input type="text" id="inputInfoAnsw" class="input_text valid mustValid mustwt"
									name="select_A" placeholder="답변을 입력해 주세요.(20자이하)" />
								</span>
								<div id="alertAnsw" class="text_alert" style="display: none;">
									<p>답변형식이 잘못되었습니다.</p>
								</div>

							</dd>

						</dl>
					</div>

				</div>
				<div class="btn_bottom">
					<button type="button" class="btn_submit" id="btnJoin">가입하기</button>
				</div>
			</form>


			<div id="footer">

				<ul class="footer_menu">
					<li><a href="http://policym.com/terms/">이용약관</a></li>
					<li><a href="http://policym.com/info"><strong>개인정보처리방침</strong></a></li>
					<li><a href="http://wwwernet.com/" target="_blank"><strong>©ZUMinternet</strong></a></li>
				</ul>


			</div>
		</div>

	</div>


</body>
</html>