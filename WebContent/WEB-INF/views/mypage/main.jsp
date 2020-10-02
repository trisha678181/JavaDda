<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="../layout/favicon.jsp" />

<title>My Page</title>

<link href="../../../css/mypage/main.css" rel="stylesheet" type="text/css"
	media="all" />
	
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">

$(document).ready( function() {
	
	var modal = document.querySelector(".cmodal"); 
	var modal3 = document.querySelector(".cmodal3"); 
	
$("#edsubmit").click(function() { // 확인 누르면

	$.ajaxSettings.traditional = true;
	
	$.ajax({
	type : 'POST'
	, url : "/editcouple/idcheck" 
	, data : {oppoID : $("#edinput").val()}
	, dataType: "json"
	, success : function(result){
		
		console.log(result.oppo.uprofileimg)
		
		if(result.oppoID == false){ // 아이디가 없는 것
			$("#edp2").css("display", "inline-block");
			$("#edinput").val("");
			$("#edinput").focus();

		} else {
			modal.classList.toggle("show-modal");
			modal3.classList.toggle("show-modal");
			console.log("ㄷ르어옴")
			
			$(".oppoName").html( result.oppo.name );
			$("#cmodalpfimg").attr("src", "/upload/"+result.oppo.uprofileimg );
			$(".oppoBirth").html( result.oppo.birth );
			$(".oppoNick").html( result.oppo.nick );
		
		}
		
		
	}
	, error: function() {
		console.log('AJAX fail')
	}
	
})	
})

$("#edsubmit4").click(function() { // 이메일 보내기

	$.ajaxSettings.traditional = true;
	
	$.ajax({
	type : 'POST'
	, url : "/mypage/sendmail" 
	, data : {oppoID : $("#edinput").val()}
	, dataType: "json"
	, success : function(result){
		
		if(result.oppoID == false){ // 아이디가 없는 것
			$("#edp2").css("display", "inline-block");
			$("#edinput").val("");
			$("#edinput").focus();

		} else {
			modal.classList.toggle("show-modal");
			modal3.classList.toggle("show-modal");
			
			$(".oppoName").html( result.oppo.name );
			$("#cmodalpfimg").attr("src", "/upload/"+result.oppo.uprofileimg );
			$(".oppoBirth").html( result.oppo.birth );
			$(".oppoNick").html( result.oppo.nick );
		
		}
		
		
	}
	, error: function() {
		console.log('AJAX fail')
	}
	
})	
})

$("#edsubmit2").click(function() { // 연도 입력했을 때

	
	$.ajax({
	type : 'POST'
	, url : "/couple/setfirstday" 
	, data : {firstday : $("#edinput2").val()}
	, dataType: "json"
	, success : function(result){
		console.log('AJAX success')
	}
	, error: function() {
		console.log('AJAX fail')
	}
	
})	
})

})

</script>

<script type="text/javascript">
$(document).ready(function() {
	var modal = document.querySelector(".cmodal"); 
	var modal2 = document.querySelector(".cmodal2"); // 연도
	var modal3 = document.querySelector(".modal3");  // 다 되었어요
	var modal4 = document.querySelector(".cmodal3"); // 이 사람 맞아요?
	var mp1button = document.querySelector("#mp1button"); 
	var closeButton = document.querySelector(".close-button");
	
	$("#mp1cardchange2").click(function() {
		modal.classList.toggle("show-modal"); 
	});
	
	$(".close-button").click(function() {
		modal.classList.toggle("show-modal"); 
	});
	
	$(".close-button2").click(function() {
		modal2.classList.toggle("show-modal"); 
	});
	
	$(".close-button3").click(function() {
		modal3.classList.toggle("show-modal"); 
	});
	
	$(".close-button4").click(function() {
		modal4.classList.toggle("show-modal"); 
	});
	
	$("#edsubmit4").click(function() {
		modal4.classList.toggle("show-modal"); 
		modal2.classList.toggle("show-modal"); 
		
	});
	
	$("#edcc4").click(function() {
		modal.classList.toggle("show-modal"); 
		modal4.classList.toggle("show-modal"); 
	});
	
	$(window).click(function(e) {
		if(e.target === modal) {
			modal.classList.toggle("show-modal"); 
		} else if(e.target === modal3) {
			modal3.classList.toggle("show-modal"); 
		}
	})
	
	$("#edsubmit2").click(function() {
		var input2 = document.getElementById("edinput2").value;
		
		modal2.classList.toggle("show-modal");
		modal3.classList.toggle("show-modal");
		
	})
})

</script>

<script type="text/javascript">

$(document).ready(function() {
	
var modal = document.getElementById("myModal");

var img = document.getElementById("bgimg");
var img2 = document.getElementById("pfimg");
var modalImg = document.getElementById("img01");

img.onclick = function(){
  modal.style.display = "block";
  modalImg.src = this.src;
}

img2.onclick = function(){
	  modal.style.display = "block";
	  modalImg.src = this.src;
	}

var span = document.getElementsByClassName("close")[0];

span.onclick = function() { 
  modal.style.display = "none";
}

<c:if test= '${res eq 1}'> // 커플이면
$("#mp1cardchange").css("display", "block");
</c:if>

<c:if test= '${res eq 0}'>
$("#mp1cardchange2").css("display", "block");
</c:if>

});

</script>

<script type="text/javascript">

$(document).ready(function() {
    var nav = $( '#mp1nav' ).offset();
    
    $( window ).scroll( function() {
      if ( $( document ).scrollTop() > nav.top ) { //스크롤위치가 메뉴 넘으면
        $( '#mp1nav' ).addClass( 'menuscroll' ); //'scroll' 클래스 생성했다가
      }
      else {
        $( '#mp1nav'  ).removeClass( 'menuscroll' ); //'scroll' 클래스 제거
      }
 });


  //메뉴 이동 부드럽게 이동
//     $('#mp1nav a').click(function(e){
//         e.preventDefault(); //a태그이기때문에 버벅거림빼기
//         $('html,body').animate({scrollTop:$(this.hash).offset().top - 50}, 500);// 고정 header이기때문에 추가됨
//     });
    
	$("#mp1like").click(function() {
		location.href = "#mp1view2";
	})
	$("#mp1coupon").click(function() {
		location.href ="#mp1view";
	})
	$("#mp1write").click(function() {
		location.href = "#mp1view4";
	})
	$("#mp1review").click(function() {
		location.href = "#mp1view3";
	})
	
	
})

</script>

</head>

<body id="mp2body">
<c:import url="../layout/header.jsp" />

<div id="mp1wrapper">
			
<div id = profile_box>
	<div id="myModal">
  		<span class="close">&times;</span>
 		<img class="modal-content" id="img01">
	</div>
	
	<div id="imgs">
		
		<img id="bgimg" src="<%=request.getContextPath() %>/upload/${user.ubgimg }" alt="" style="cursor:pointer;"/>
	    
		<div id="pfimgform">
			<img id="pfimg" src="<%=request.getContextPath() %>/upload/${user.uprofileimg }" alt=""/>

			<a href="/mypage/editprofile"><button id="mp1button">회원정보 수정</button></a>
			
		</div>
			<div id="mp1card" style="cursor:pointer">
			   <p id="mp1cardchange"  style="display: none" OnClick="location.href ='../mypage/editcouple'">${names[0] } ♥ ${names[1] } 만난지 ${firstday }일째입니다</p>
			   <p id="mp1cardchange2" style="display: none">커플을 맺어보세요~!</p>
		    </div>	
		</div> <!-- imgs div END -->
</div>		

<!-- ************************************************************************************ -->
	
	<div class="cmodal"> 
         <div class="cmodal-content"> 
             <span class="close-button">&times;</span> 
             <h1 class="title">커플 맺기</h1> 
             <p id="edp">입력하신 ID의 등록된 이메일로 승낙 메일이 전송됩니다</p>
             
               <label id="edlabel">상대방 ID
               <input id="edinput" type="text" name="input1" required="required"></label>
               <p id="edp2" style="display: none">ID가 존재하지 않습니다. 다시 확인해보세요!</p>
               <button id="edsubmit">확인</button>
         </div> 
     </div>
     
     <div class="cmodal3"> 
         <div class="cmodal4-content"> 
             <span class="close-button4">&times;</span> 
             <h1 class="title">이 사람이 맞나요?</h1>
             <img id="cmodalpfimg" src="" alt=""/>
        	 <table id="cctable">
        	 	<tr>
        	 		<td class="mmtd1">이름</td>
        	 		<td class="oppoName"></td>
        	 	</tr>
        	 	<tr>
        	 		<td class="mmtd1">생년월일</td>
        	 		<td class="oppoBirth"></td>
        	 	</tr>
        	 	<tr>
        	 		<td class="mmtd1">닉네임</td>
        	 		<td class="oppoNick"></td>
        	 	</tr>
        	 </table>
             <button id="edsubmit4">확인</button>
             <button id="edcc4">취소</button>
         </div> 
     </div>
     
     <div class="cmodal2"> 
         <div class="cmodal2-content"> 
             <span class="close-button2">&times;</span> 
             <h1 class="title">커플 맺기</h1> 
             <p id="edp">처음 사귄 날짜를 입력해주세요!</p>
             
             <input id="edinput2" type="date" name="input2" required="required"><br>
             
             <button id="edsubmit2">맺기</button>
         </div> 
     </div>     
     
     <div class="modal3"> 
         <div class="modal3-content"> 
             <span class="close-button3">&times;</span> 
             <h1 class="title">커플 맺기 요청 완료</h1> 
             
             <div id="circle">
             <p>${user.name } ♥ <span class="oppoName"></span></p>
             </div>
             
             <hr id="edhr">
             <p id="ededp">커플만을 위한 다양한 기능을 즐겨보세요~!</p>
             
             <p id="edp"></p>
         </div> 
     </div>
     
     
	<!-- ************************************************************************************ -->


	<div id= hr></div>	
	<nav id="mp1nav">
	<div id = "menutext">
  		<a id="mp1like">찜</a>
    	<a id="mp1coupon">쿠폰</a>
  		<a id="mp1write">게시글</a>
  		<a id="mp1review">리뷰</a>
  	<div id="mp1nav-underline"></div>
  	</div>
	</nav>
	
	
	<div id="mp1view2" >
		<img alt="" src="../../../images/likeicon.svg">
		<p id="mp1viewtitle">찜한 장소가 <strong class="mainst"  style="color:#B43233">${likeplacecnt }</strong>개 있습니다.</p> <!-- 몇개가 있습니다. -->
		
		<div id="mp1wrapper2">
			<div id=pickback>
			
				<div id = "pickcoulumn">
					<div id="mp1likecolumn1">
						
						<c:if test="${likeplacecnt >= 1}">
						<a href="/place/detail?pno=${likeplace.get(0)['place'].pno }">
				   			<img id="mp1likeimg" src="<%=request.getContextPath() %>/upload/${likeplace.get(0)['pimg'].storedName }" alt="" />
				   			<c:if test="${likeplace.get(0)['likeplace'].uno == user.uno}">
				   			<img id="mp1heart" alt="" src="../../../images/hearticon.svg"/>
				   			</c:if>
				   			<c:if test="${likeplace.get(0)['likeplace'].uno == oppo.uno }">
				   			<img id="mp1heart" alt="" src="../../../images/bhearticon.svg"/>
				   			</c:if>
				   			<c:if test="${likeplace.get(0)['likeplace'].together == 'O'}">
				   			<img id="mp1heart" alt="" src="../../../images/phearticon.svg"/>
				   			</c:if>
				   			<div id="mp1likename">${likeplace.get(0)['place'].pname }</div></a>
				   		</c:if>
						</div>
						<div id="mp1likecolumn2">
						
						<c:if test="${likeplacecnt >= 2}">
						<a href="/place/detail?pno=${likeplace.get(1)['place'].pno }">
				   			<img id="mp1likeimg" src="<%=request.getContextPath() %>/upload/${likeplace.get(1)['pimg'].storedName }" alt="" />
			
				   			<c:if test="${likeplace.get(1)['likeplace'].uno == user.uno}">
				   			<img id="mp1heart" alt="" src="../../../images/hearticon.svg"/>
				   			</c:if>
				   			<c:if test="${likeplace.get(1)['likeplace'].uno == oppo.uno }">
				   			<img id="mp1heart" alt="" src="../../../images/bhearticon.svg"/>
				   			</c:if>
				   			<c:if test="${likeplace.get(1)['likeplace'].together == 'O'}">
				   			<img id="mp1heart" alt="" src="../../../images/phearticon.svg"/>
				   			</c:if>
				   			<div id="mp1likename">${likeplace.get(1)['place'].pname }</div></a>
				   		</c:if>
						</div>
						<div id="mp1likecolumn3">
						
						<c:if test="${likeplacecnt >= 3}">
						<a href="/place/detail?pno=${likeplace.get(2)['place'].pno }">
				   			<img id="mp1likeimg" src="<%=request.getContextPath() %>/upload/${likeplace.get(2)['pimg'].storedName }" alt="" />
		
				   			<c:if test="${likeplace.get(2)['likeplace'].uno == user.uno}">
				   			<img id="mp1heart" alt="" src="../../../images/hearticon.svg"/>
				   			</c:if>
				   			<c:if test="${likeplace.get(2)['likeplace'].uno == oppo.uno }">
				   			<img id="mp1heart" alt="" src="../../../images/bhearticon.svg"/>
				   			</c:if>
				   			<c:if test="${likeplace.get(2)['likeplace'].together == 'O'}">
				   			<img id="mp1heart" alt="" src="../../../images/phearticon.svg"/>
				   			</c:if>
				   			<div id="mp1likename">${likeplace.get(2)['place'].pname }</div></a>
				   		</c:if>
						</div>
						<div id="mp1likecolumn4">
						<c:if test="${likeplacecnt >= 4}">
						<a href="/place/detail?pno=${likeplace.get(3)['place'].pno }">
				   			<img id="mp1likeimg" src="<%=request.getContextPath() %>/upload/${likeplace.get(3)['pimg'].storedName }" alt="" />
				   			
				   			<c:if test="${likeplace.get(3)['likeplace'].uno == user.uno}">
				   			<img id="mp1heart" alt="" src="../../../images/hearticon.svg"/>
				   			</c:if>
				   			<c:if test="${likeplace.get(3)['likeplace'].uno == oppo.uno }">
				   			<img id="mp1heart" alt="" src="../../../images/bhearticon.svg"/>
				   			</c:if>
				   			<c:if test="${likeplace.get(3)['likeplace'].together == 'O'}">
				   			<img id="mp1heart" alt="" src="../../../images/phearticon.svg"/>
				   			</c:if>
				   			<div id="mp1likename">${likeplace.get(3)['place'].pname }</div>
				   		</a>
				   		</c:if>
				   		
						</div>
						<div id="mp1likecolumn5">
						
						<c:if test="${likeplacecnt >= 5}">
						<a href="/place/detail?pno=${likeplace.get(4)['place'].pno }">
				   			<img id="mp1likeimg" src="<%=request.getContextPath() %>/upload/${likeplace.get(4)['pimg'].storedName }" alt="" />
				   		
				   			<c:if test="${likeplace.get(4)['likeplace'].uno == user.uno}">
				   			<img id="mp1heart" alt="" src="../../../images/hearticon.svg"/>
				   			</c:if>
				   			<c:if test="${likeplace.get(4)['likeplace'].uno == oppo.uno }">
				   			<img id="mp1heart" alt="" src="../../../images/bhearticon.svg"/>
				   			</c:if>
				   			<c:if test="${likeplace.get(4)['likeplace'].together == 'O'}">
				   			<img id="mp1heart" alt="" src="../../../images/phearticon.svg"/>
				   			</c:if>
				   			<div id="mp1likename">${likeplace.get(4)['place'].pname }</div></a>
				   		</c:if>
				   		
					</div>
				</div>
				
			</div>
			
		</div>
		
		<div id = "button3_mp1">
			<a href="/mypage/likeplacelist" id="mp1button3">찜 관리</a>
		</div>
	
	</div> <!-- view END -->
	
	
	<div id="mp1view">
		<img alt="" src="../../../images/couponicon.svg">
		<p id="mp1viewtitle">쿠폰이 <strong class="mainst"  style="color:#B43233">${couponlistcnt }</strong>개 있습니다.</p> <!-- 몇개가 있습니다. -->
		<br>
		<div id="mp1table">
	
				<table id="mp1tb">
				<tr>
					<th style="width: 70px;">번호</th>
					<th>장소명</th>
					<th>쿠폰명</th>
					<th style="width: 150px;">유효기간</th>
				</tr>
				<c:if test="${couponlistcnt >= 1}">
					<tr>
						<td>1</td>
						<td><a id="couponname" href="/place/detail?pno=${couponlist.get(0)['coupon'].pno }">${couponlist.get(0)['place'].pname }</a></td>
						<c:if test="${couponlist.get(0)['coupon'].originname eq 'coupon_first.png'}">
							<td>첫 구매 쿠폰</td>
						</c:if>
						<c:if test="${couponlist.get(0)['coupon'].originname eq 'coupon_app.png'}">
							<td>앱 출시 기념 쿠폰</td>
						</c:if>
						<c:if test="${couponlist.get(0)['coupon'].originname eq 'coupon_over10.png'}">
							<td>10만원 이상 구매 시 할인 쿠폰</td>
						</c:if>
						<td>${couponlist.get(0)['coupon'].expdate }</td>
					</tr>
				</c:if>
				<c:if test="${couponlistcnt >= 2}">
					<tr>
						<td>2</td>
						<td><a id="couponname" href="/place/detail?pno=${couponlist.get(1)['coupon'].pno }">${couponlist.get(1)['place'].pname }</a></td>
						<c:if test="${couponlist.get(1)['coupon'].originname eq 'coupon_first.png'}">
							<td>첫 구매 쿠폰</td>
						</c:if>
						<c:if test="${couponlist.get(1)['coupon'].originname eq 'coupon_app.png'}">
							<td>앱 출시 기념 쿠폰</td>
						</c:if>
						<c:if test="${couponlist.get(1)['coupon'].originname eq 'coupon_over10.png'}">
							<td>10만원 이상 구매 시 할인 쿠폰</td>
						</c:if>
						<td>${couponlist.get(1)['coupon'].expdate }</td>
					</tr>
				</c:if>
				<c:if test="${couponlistcnt >= 3}">
					<tr>
						<td>3</td>
						<td><a id="couponname" href="/place/detail?pno=${couponlist.get(2)['coupon'].pno }">${couponlist.get(2)['place'].pname }</a></td>
						<c:if test="${couponlist.get(2)['coupon'].originname eq 'coupon_first.png'}">
							<td>첫 구매 쿠폰</td>
						</c:if>
						<c:if test="${couponlist.get(2)['coupon'].originname eq 'coupon_app.png'}">
							<td>앱 출시 기념 쿠폰</td>
						</c:if>
						<c:if test="${couponlist.get(2)['coupon'].originname eq 'coupon_over10.png'}">
							<td>10만원 이상 구매 시 할인 쿠폰</td>
						</c:if>
						<td>${couponlist.get(2)['coupon'].expdate }</td>
					</tr>
				</c:if>
				<c:if test="${couponlistcnt >= 4}">
					<tr>
						<td>4</td>
						<td><a id="couponname" href="/place/detail?pno=${couponlist.get(3)['coupon'].pno }">${couponlist.get(3)['place'].pname }</a></td>
						<c:if test="${couponlist.get(3)['coupon'].originname eq 'coupon_first.png'}">
							<td>첫 구매 쿠폰</td>
						</c:if>
						<c:if test="${couponlist.get(3)['coupon'].originname eq 'coupon_app.png'}">
							<td>앱 출시 기념 쿠폰</td>
						</c:if>
						<c:if test="${couponlist.get(3)['coupon'].originname eq 'coupon_over10.png'}">
							<td>10만원 이상 구매 시 할인 쿠폰</td>
						</c:if>
						<td>${couponlist.get(3)['coupon'].expdate }</td>
					</tr>
				</c:if>
				<c:if test="${couponlistcnt >= 5}">
					<tr>
						<td>5</td>
						<td><a id="couponname" href="/place/detail?pno=${couponlist.get(4)['coupon'].pno }">${couponlist.get(4)['place'].pname }</a></td>
						<c:if test="${couponlist.get(4)['coupon'].originname eq 'coupon_first.png'}">
							<td>첫 구매 쿠폰</td>
						</c:if>
						<c:if test="${couponlist.get(4)['coupon'].originname eq 'coupon_app.png'}">
							<td>앱 출시 기념 쿠폰</td>
						</c:if>
						<c:if test="${couponlist.get(4)['coupon'].originname eq 'coupon_over10.png'}">
							<td>10만원 이상 구매 시 할인 쿠폰</td>
						</c:if>
						<td>${couponlist.get(4)['coupon'].expdate }</td>
					</tr>
				</c:if>
				</table>
				
			
			</div>
		
			<div><a href="/mypage/couponlist" id="mp1button3">쿠폰 관리</a></div>
		</div> <!-- table + title END -->

	
	<div id="mp1view4">
		<img alt="" src="../../../images/writeicon.svg">
		<p id="mp1viewtitle">작성한 게시글이 <strong class="mainst" style="color:#B43233">${boardlistcnt }</strong>개 있습니다.</p> <!-- 몇개가 있습니다. -->
		<br>
		<div id="mp1table">
	
				<table id="mp1tb">
				<tr>
					<th style="width: 20%;">날짜</th>
					<th>제목</th>
					<th style="width: 10%;">댓글</th>
					<th style="width: 10%;">조회수</th>
				</tr>
				<c:if test="${boardlistcnt >= 1}">
					<tr>
						<td>${boardlist.get(0)['board'].brdate }</td>
						<td><a id="boardname" href="/board/show?brno=${boardlist.get(0)['board'].brno }">${boardlist.get(0)['board'].brtitle }</a></td>
						<td>${boardlist.get(0).cnt }</td>
						<td>${boardlist.get(0)['board'].brviewcnt }</td>
					</tr>
				</c:if>
				<c:if test="${boardlistcnt >= 2}">
					<tr>
						<td>${boardlist.get(1)['board'].brdate }</td>
						<td><a id="boardname" href="/board/show?brno=${boardlist.get(1)['board'].brno }">${boardlist.get(1)['board'].brtitle }</a></td>
						<td>${boardlist.get(1).cnt }</td>
						<td>${boardlist.get(1)['board'].brviewcnt }</td>
					</tr>
				</c:if>
				<c:if test="${boardlistcnt >= 3}">
					<tr>
						<td>${boardlist.get(2)['board'].brdate }</td>
						<td><a id="boardname" href="/board/show?brno=${boardlist.get(2)['board'].brno }">${boardlist.get(2)['board'].brtitle }</a></td>
						<td>${boardlist.get(2).cnt }</td>
						<td>${boardlist.get(2)['board'].brviewcnt }</td>
					</tr>
				</c:if>
				<c:if test="${boardlistcnt >= 4}">
					<tr>
						<td>${boardlist.get(3)['board'].brdate }</td>
						<td><a id="boardname" href="/board/show?brno=${boardlist.get(3)['board'].brno }">${boardlist.get(3)['board'].brtitle }</a></td>
						<td>${boardlist.get(3).cnt }</td>
						<td>${boardlist.get(3)['board'].brviewcnt }</td>
					</tr>
				</c:if>
				<c:if test="${boardlistcnt >= 5}">
					<tr>
						<td>${boardlist.get(4)['board'].brdate }</td>
						<td><a id="boardname" href="/board/show?brno=${boardlist.get(4)['board'].brno }">${boardlist.get(4)['board'].brtitle }</a></td>
						<td>${boardlist.get(4).cnt }</td>
						<td>${boardlist.get(4)['board'].brviewcnt }</td>
					</tr>
				</c:if>
				</table>
				
		
		</div> <!-- table + title END -->
			<div><a href="/mypage/boardlist" id="mp1button3">게시글 관리</a></div>
	</div> <!-- view END -->	
	

	<div id="mp1view3">
		<img alt="" src="../../../images/reviewicon.svg">
		<p id="mp1viewtitle">작성한 리뷰가 <strong class="mainst" >${reviewlistcnt }</strong>개 있습니다.</p> <!-- 몇개가 있습니다. -->
		<br>
		<div id="mp1table">
	
				<table id="mp1tb">
				<tr>
					<th style="width: 120px;">날짜</th>
					<th style="width: 200px;">장소명</th>
					<th>내용</th>
					<th style="width: 120px;">별점</th>
				</tr>
				<c:if test="${reviewlistcnt >= 1}">
					<tr>
						<td>${reviewlist.get(0)['review'].rvdate }</td>
						<td><a id="reviewname" href="/place/reviewplus?pno=${reviewlist.get(0)['review'].pno }">${reviewlist.get(0)['place'].pname }</a></td>
						<td>${reviewlist.get(0)['review'].rvcontent }</td>
						<c:if test="${reviewlist.get(0)['review'].rvstar eq 1}">
							<td>★☆☆☆☆</td>
						</c:if>
						<c:if test="${reviewlist.get(0)['review'].rvstar eq 2}">
							<td>★★☆☆☆</td>
						</c:if>
						<c:if test="${reviewlist.get(0)['review'].rvstar eq 3}">
							<td>★★★☆☆</td>
						</c:if>
						<c:if test="${reviewlist.get(0)['review'].rvstar eq 4}">
							<td>★★★★☆</td>
						</c:if>
						<c:if test="${reviewlist.get(0)['review'].rvstar eq 5}">
							<td>★★★★★</td>
						</c:if>
					</tr>
				</c:if>
				<c:if test="${reviewlistcnt >= 2}">
					<tr>
						<td>${reviewlist.get(1)['review'].rvdate }</td>
						<td><a id="reviewname" href="/place/reviewplus?pno=${reviewlist.get(1)['review'].pno }">${reviewlist.get(1)['place'].pname }</a></td>
						<td>${reviewlist.get(1)['review'].rvcontent }</td>
						<c:if test="${reviewlist.get(1)['review'].rvstar eq 1}">
							<td>★☆☆☆☆</td>
						</c:if>
						<c:if test="${reviewlist.get(1)['review'].rvstar eq 2}">
							<td>★★☆☆☆</td>
						</c:if>
						<c:if test="${reviewlist.get(1)['review'].rvstar eq 3}">
							<td>★★★☆☆</td>
						</c:if>
						<c:if test="${reviewlist.get(1)['review'].rvstar eq 4}">
							<td>★★★★☆</td>
						</c:if>
						<c:if test="${reviewlist.get(1)['review'].rvstar eq 5}">
							<td>★★★★★</td>
						</c:if>
					</tr>
				</c:if>
				<c:if test="${reviewlistcnt >= 3}">
					<tr>
						<td>${reviewlist.get(2)['review'].rvdate }</td>
						<td><a id="reviewname" href="/place/reviewplus?pno=${reviewlist.get(2)['review'].pno }">${reviewlist.get(2)['place'].pname }</a></td>
						<td>${reviewlist.get(2)['review'].rvcontent }</td>
						<c:if test="${reviewlist.get(2)['review'].rvstar eq 1}">
							<td>★☆☆☆☆</td>
						</c:if>
						<c:if test="${reviewlist.get(2)['review'].rvstar eq 2}">
							<td>★★☆☆☆</td>
						</c:if>
						<c:if test="${reviewlist.get(2)['review'].rvstar eq 3}">
							<td>★★★☆☆</td>
						</c:if>
						<c:if test="${reviewlist.get(2)['review'].rvstar eq 4}">
							<td>★★★★☆</td>
						</c:if>
						<c:if test="${reviewlist.get(2)['review'].rvstar eq 5}">
							<td>★★★★★</td>
						</c:if>
					</tr>
				</c:if>
				<c:if test="${reviewlistcnt >= 4}">
					<tr>
						<td>${reviewlist.get(3)['review'].rvdate }</td>
						<td><a id="reviewname" href="/place/reviewplus?pno=${reviewlist.get(3)['review'].pno }">${reviewlist.get(3)['place'].pname }</a></td>
						<td>${reviewlist.get(3)['review'].rvcontent }</td>
						<c:if test="${reviewlist.get(3)['review'].rvstar eq 1}">
							<td>★☆☆☆☆</td>
						</c:if>
						<c:if test="${reviewlist.get(3)['review'].rvstar eq 2}">
							<td>★★☆☆☆</td>
						</c:if>
						<c:if test="${reviewlist.get(3)['review'].rvstar eq 3}">
							<td>★★★☆☆</td>
						</c:if>
						<c:if test="${reviewlist.get(3)['review'].rvstar eq 4}">
							<td>★★★★☆</td>
						</c:if>
						<c:if test="${reviewlist.get(3)['review'].rvstar eq 5}">
							<td>★★★★★</td>
						</c:if>
					</tr>
				</c:if>
				<c:if test="${reviewlistcnt >= 5}">
					<tr>
						<td>${reviewlist.get(4)['review'].rvdate }</td>
						<td><a id="reviewname" href="/place/reviewplus?pno=${reviewlist.get(4)['review'].pno }">${reviewlist.get(4)['place'].pname }</a></td>
						<td>${reviewlist.get(4)['review'].rvcontent }</td>
						<c:if test="${reviewlist.get(4)['review'].rvstar eq 1}">
							<td>★☆☆☆☆</td>
						</c:if>
						<c:if test="${reviewlist.get(4)['review'].rvstar eq 2}">
							<td>★★☆☆☆</td>
						</c:if>
						<c:if test="${reviewlist.get(4)['review'].rvstar eq 3}">
							<td>★★★☆☆</td>
						</c:if>
						<c:if test="${reviewlist.get(4)['review'].rvstar eq 4}">
							<td>★★★★☆</td>
						</c:if>
						<c:if test="${reviewlist.get(4)['review'].rvstar eq 5}">
							<td>★★★★★</td>
						</c:if>
					</tr>
				</c:if>
				</table>
				
		
				
		
		</div> <!-- table + title END -->
			<div><a href="/mypage/reviewlist" id="mp1button3">리뷰 관리</a></div>
				
		</div> <!-- view END -->
	</div>	
</div> <!-- mp1container END -->
<c:import url="../layout/footer.jsp" />

</body>
</html>
