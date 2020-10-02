<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
   src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<c:import url="../layout/favicon.jsp" />
<title>상세보기</title>

<link href="../../../css/place/detail.css" rel="stylesheet" type="text/css"
   media="all" />

<script type="text/javascript">
$(document).ready(function() {
	
	$("#detailheart").click(function() {	
		
		var i = $(this)
		
		if($(this).attr('src') == '../../../images/pl-heart-unpick.png') {

			$.ajax({
				type : 'POST'
				, url : "/addlikeplace" 
				, data : {placename : '${place.pname}'}
				, dataType: "json"
				, success : function(result){
					
					$(i).attr('src', "../../../images/pl-heart-pick.png");
					
				}
				, error: function() {
					console.log('AJAX fail')
				}
				
			})	
			} else if($(this).attr('src') == '../../../images/pl-heart-pick.png') {

				$.ajax({
					type : 'POST'
					, url : "/mypage/deletelikeplace" 
					, data : {placename : '${place.pname}'}
					, dataType: "json"
					, success : function(result){
						
						console.log('AJAX succ')
						$(i).attr('src', "../../../images/pl-heart-unpick.png");
					}
					, error: function() {
						console.log('AJAX fail')
					}
					
				})	
			} 
		
	});
});
</script>   


<script type="text/javascript">
$(document).ready( function() {
   
<c:if test='${weather["SKY"] eq 1 && weather["PTY"] eq 0}'> // 맑음
   <c:set var="sky" value="맑음" />
   $('#weathericon').prop('src', '<%=request.getContextPath()%>/images/weather/sun.svg');

</c:if>

<c:if test='${weather["SKY"] eq 3 && weather["PTY"] eq 0}'> // 구름 많음
   <c:set var="sky" value="구름 많음" />
   $('#weathericon').attr('src', '<%=request.getContextPath()%>/images/weather/cas.svg');

</c:if>

<c:if test='${weather["SKY"] eq 4 && weather["PTY"] eq 0}'> // 흐림
   <c:set var="sky" value="흐림" />
   $('#weathericon').attr('src', '<%=request.getContextPath()%>/images/weather/cloud.svg');

</c:if>

<c:if test='${weather["PTY"] eq 1 || weather["PTY"] eq 4}'> // 비, 소나기
   <c:set var="sky" value="비" />
$('#weathericon').attr('src', '<%=request.getContextPath()%>/images/weather/rain.svg');

</c:if>

<c:if test='${weather["PTY"] eq 2}'> // 비눈
   <c:set var="sky" value="진눈깨비" />
$('#weathericon').attr('src', '<%=request.getContextPath()%>/images/weather/sleet.svg');

</c:if>

<c:if test='${weather["PTY"] eq 3}'> // 눈
   <c:set var="sky" value="눈" />
$('#weathericon').attr('src', '<%=request.getContextPath()%>/images/weather/snow.svg');

</c:if>

<c:if test='${air eq 1}'> // 미세먼지 좋음
<c:set var="airinfo" value="좋음" />
$('#airicon').attr('src', '<%=request.getContextPath()%>/images/weather/agood.svg');

</c:if>

<c:if test='${air eq 2}'> // 미세먼지 보통
<c:set var="airinfo" value="보통" />
$('#airicon').attr('src', '<%=request.getContextPath()%>/images/weather/asoso.svg');

</c:if>

<c:if test='${air eq 3}'> // 미세먼지 나쁨
<c:set var="airinfo" value="나쁨" />
$('#airicon').attr('src', '<%=request.getContextPath()%>/images/weather/abad.svg');

</c:if>

<c:if test='${air eq 4}'> // 미세먼지 매우나쁨
<c:set var="airinfo" value="매우나쁨" />
$('#airicon').attr('src', '<%=request.getContextPath()%>
   /images/weather/aworst.svg');
</c:if>

})
</script>


<script type="text/javascript">
   // 왼쪽 플로팅배너
   var startTop = 110;
   $(window)
         .scroll(
               function() {
                  var cTop = document.body.scrollTop == 0 ? document.documentElement.scrollTop
                        : document.body.scrollTop;

                  if (cTop <= startTop) {
                     $(".main_left #left").animate({
                        'top' : 110 + 'px'
                     }, {
                        queue : false,
                        duration : 450
                     });
                  } else {
                     var endTop = $('#plwrapper').outerHeight();
                     if (cTop < endTop) {
                        $(".main_left #left").animate({
                           'top' : cTop + 80 + 'px'
                        }, {
                           queue : false,
                           duration : 450
                        });
                     } else {
                        $(".main_left #left").animate({
                           'top' : endTop + 80 + 'px'
                        }, {
                           queue : false,
                           duration : 450
                        });
                     }
                     startTop = 110;
                  }
               });
   
   // 오른쪽 플로팅배너
   var startTop = 110;
   $(window)
         .scroll(
               function() {
                  var cTop = document.body.scrollTop == 0 ? document.documentElement.scrollTop
                        : document.body.scrollTop;

                  if (cTop <= startTop) {
                     $(".main_right #right").animate({
                        'top' : 110 + 'px'
                     }, {
                        queue : false,
                        duration : 450
                     });
                     
                  } else {
                     var endTop = $('#plwrapper').outerHeight();
                     if (cTop < endTop) {
                        $(".main_right #right").animate({
                           'top' : cTop + 80 + 'px'
                        }, {
                           queue : false,
                           duration : 450
                        });
                     } else {
                        $(".main_right #right").animate({
                           'top' : endTop + 80 + 'px'
                        }, {
                           queue : false,
                           duration : 450
                        });
                     }
                     startTop = 110;
                  }
               });
</script>

<script type="text/javascript">
   $(document).ready(function() {
      $("#weathericon").click(function() {
         $("#weatherinfo").toggle();
      });

      $("#airicon").click(function() {
         $("#airinfo").toggle();
      });

      $("#ddday").click(function() {
         $("#ddaylist").toggle();
      });
   })
</script>

<script type="text/javascript">

	
	//모달 처리
	$(document).ready(function() {
		var modal = document.getElementById("myModal");

		var img = document.getElementById("menuimg");
		var modalImg = document.getElementById("img01");

		img.onclick = function(){
		  modal.style.display = "block";
		  modalImg.src = this.src;
		}

		var span = document.getElementsByClassName("close")[0];

		span.onclick = function() { 
		  modal.style.display = "none";
		}
	});

		
	$(document).ready(function() {

	      $("#detailclose1").click(function() {
	            $modal2.classList.toggle("show-modal"); 
	       });
	        $("#detailclose2").click(function() {
	           $modal3.classList.toggle("show-modal"); 
	         });
	      
	      //전화 팝업
	      var $modal2 = document.querySelector("#phoneModal"); 
	      $("#qbtn1").click(function() {
	         $modal2.classList.toggle("show-modal"); 
	        });

	      var $modal3 = document.querySelector("#urlModal"); 
	      $("#qbtn2").click(function() {
	         $modal3.classList.toggle("show-modal"); 
	         var obShareUrl = document.getElementById("ShareUrl");
	          obShareUrl.value = window.document.location.href;  // 현재 URL세팅
	        });      
	      
	     $(window).click(function(e) {
	        if(e.target === $modal2) {
	         $modal2.classList.toggle("show-modal"); 
	      } else if(e.target === $modal3) {
	         $modal3.classList.toggle("show-modal"); 
	      }
	   })
	   
	});
	
// 	var modal = document.getElementById("myModal");

// 	var img = document.getElementById("menuimg");
// 	var modalImg = document.getElementById("img01");

// 	img.onclick = function(){
// 	  modal.style.display = "block";
// 	  modalImg.src = this.src;
// 	}

// 	var span = document.getElementsByClassName("close")[0];

// 	span.onclick = function() { 
// 	  modal.style.display = "none";
// 	}
// 	});

// $(document).ready(function() {

//       $("#detailclose1").click(function() {
//             $modal2.classList.toggle("show-modal"); 
//        });
//         $("#detailclose2").click(function() {
//            $modal3.classList.toggle("show-modal"); 
//          });
        
//         //이미지 팝업
//         var $modal = document.querySelector("#photoModal"); 
//         var $menuimg = document.querySelector("#menuimg"); 
        
//         $("#menuimg").click(function(e) {
           
//            console.log(e.target.src);
           
//            $("#myModal2").css('width', e.target.width);
//            $("#myModal2").css('height', e.target.height);
           
//              $("#myModal2").css('background-image', 'url('+'"'+e.target.src+'"'+')');
//            $("#myModal2").css('background-repeat', 'no-repeat');
//            $("#myModal2").css('background-size', 'cover');
//            $("#myModal2").css('margin', '10px auto');

//            $modal.classList.toggle("show-modal"); 
           
//               $("#dclose").click(function() {
//                   $modal.classList.toggle("show-modal"); 
//               });
              
              
//        });
      
//       전화 팝업
//       var $modal2 = document.querySelector("#phoneModal"); 
//       $("#qbtn1").click(function() {
//          $modal2.classList.toggle("show-modal"); 
//         });

//       var $modal3 = document.querySelector("#urlModal"); 
//       $("#qbtn2").click(function() {
//          $modal3.classList.toggle("show-modal"); 
//          var obShareUrl = document.getElementById("ShareUrl");
//           obShareUrl.value = window.document.location.href;  // 현재 URL세팅
//         });      
      
//      $(window).click(function(e) {
//         if(e.target === $modal) {
//          $modal.classList.toggle("show-modal"); 
//       } else if(e.target === $modal2) {
//          $modal2.classList.toggle("show-modal"); 
//       } else if(e.target === $modal3) {
//          $modal3.classList.toggle("show-modal"); 
//       }
//    })


function CopyUrlToClipboard() //url복사 함수
{
   var obShareUrl = document.getElementById("ShareUrl");
   obShareUrl.value = window.document.location.href;  // 현재 URL 세팅

   obShareUrl.select();  // 해당 값이 선택되도록 select() 합니다
   document.execCommand("copy"); // 클립보드에 복사합니다.

   obShareUrl.blur(); // 선택된 것을 다시 선택안된것으로 바꿈니다.
   alert("URL이 클립보드에 복사되었습니다"); 

}

function CopyPnumToClipboard() //번호 복사 함수
{
   var pnum = document.getElementById("SharePnum");
   
   pnum.select(); 
   document.execCommand("copy");
   
   pnum.blur();
   alert("클립보드에 복사되었습니다.")
      
}

function NotReady(){ //신고
   alert("서비스 준비중입니다.")
}

</script>

<script type="text/javascript">
$(document).ready(function(){
      
      //스크롤시 헤더메뉴 상단 고정
       var nav = $( '#place-menu' ).offset();
      
           $( window ).scroll( function() {
             if ( $( document ).scrollTop() > nav.top ) { //스크롤위치가 메뉴 넘으면
               $( '#place-menu' ).addClass( 'menuscroll' ); //'scroll' 클래스 생성했다가
             }
             else {
               $( '#place-menu'  ).removeClass( 'menuscroll' ); //'scroll' 클래스 제거
             }
           });
      
      
      //메뉴 이동 부드럽게 이동
       $('#place-menu a').click(function(e){
          e.preventDefault(); //a태그이기때문에 버벅거림빼기
          $('html,body').animate({scrollTop:$(this.hash).offset().top - 50}, 500);// 고정 header이기때문에 추가됨
      });
      
       $('#side-top').click(function(e){
             e.preventDefault(); 
             $('html,body').animate({scrollTop:$(this.hash).offset().top - 50}, 500);
         });
       
       $('#side-bottom').click(function(e){
             e.preventDefault(); 
             $('html,body').animate({scrollTop:$(this.hash).offset().top - 50}, 700);
         });
   });
   
</script>


<script type="text/javascript">
$(document).ready(function() {

   $("#cpimg1").click(function() {
      console.log("클릭성공")
      $.ajax({
         type : "POST"
         , url : "/coupon/insert"
         , data : {"cpno" : '${cimglist[0].cpno }' }
         , dataType : "json"
         , success : function(data) {
            console.log("data.checklog : " + data.checklog);
            console.log("data.msg : " + data.res);
            console.log("data.res : " + data.msg);
            
            if(data.checklog){
               if(data.res == 2){
                  alert(data.msg)
               }else if(data.res == 3){
                  alert(data.msg)
               }
            }else {
               alert(data.msg)
               location.href = "/login"                  
            }
         }
         , error : function() {
            console.log("실패")
         }
         
         
      }) 
      
   })
      
      $("#cpimg2").click(function() {
      console.log("클릭성공")
      $.ajax({
         type : "POST"
         , url : "/coupon/insert"
         , data : {"cpno" : '${cimglist[1].cpno }' }
         , dataType : "json"
         , success : function(data) {
            console.log("data.checklog : " + data.checklog);
            console.log("data.msg : " + data.res);
            console.log("data.res : " + data.msg);
            
            if(data.checklog){
               if(data.res == 2){
                  alert(data.msg)
               }else if(data.res == 3){
                  alert(data.msg)
               }
            }else {
               alert(data.msg)
               location.href = "/login"                  
            }
         }
         , error : function() {
            console.log("실패")
         }
         
         
      }) 
      
      
      
   })
   
   

})
</script>
 <script type="text/javascript">
   	function loclick(){ //로그인체크
   		alert("로그인이 필요합니다!");
   		location.href="/login";
   	}
 </script>
      
</head>
<body>


<!-- 모달부분 --> <!-- CSS디자인 추가수정 -->

<div id="myModal" class="modal">
     <span class="close">&times;</span>
    <img class="modal-content" id="img01">
</div>

   
<!-- 전화팝업 -->
<div id="phoneModal" class="dmodal">
<div class="phmodal-content">
   <span id="detailclose1" class="close">&times;</span>
   <div class="pmodaltitle">연락하기</div>
   <div class="pmodalinput">
      <input type="text" id="SharePnum" value="${place.pphone }"/>
      <button class="btn-type2" OnClick="javascript:CopyPnumToClipboard()">복사</button>
   </div>
</div>
</div>

<!-- url팝업 -->
<div id="urlModal" class="dmodal">
<div class="urmodal-content">
<span id="detailclose2" class="close">&times;</span>
   <div class="pmodaltitle">공유하기</div>
   <div class="pmodalinput2">
      <input type="text" id = "ShareUrl"/>
      <button class="btn-type1" OnClick="javascript:CopyUrlToClipboard()">복사</button>
      <div class="pmodaltext">나랑 같이 여기 가자♥</div>
   </div>
   
</div>
</div>


<!--공통헤더-->
<c:import url="../layout/header.jsp" />
   
<div id="plwrapper">
    <div id="plheader-side">
   <!-- 따라다니는 메뉴 -->
      <div class="main_right">
         <div id="right">
            <table id="righttb">
               <tr>
                  <td><a id="side-top" href="#plwrapper"><img class="rightt" src="../../../images/pageup.png"/></a></td>
               </tr>
               <tr>
                  <td><img id="side-share" OnClick="javascript:CopyUrlToClipboard()" class="rightt" src="../../../images/share.png" /></td>
               </tr>
<!--                <tr> -->
<!--                   <td><img id="side-report" OnClick="javascript:NotReady()" class="rightt" src="../../../images/report.png" /></td> -->
<!--                </tr> -->
               <tr>
                  <td><a id="side-bottom" href="#sideplace-second"><img class="rightt" src="../../../images/pagedown.png"/></a></td>
               </tr>
            </table>
         </div>
      </div>
   
      <!-- 날씨바 -->
      <div class="main_left">
         <div id="left">
            <table id="weathertb">
               <tr>
                  <td><img id="weathericon" src="" /></td>
                  <td id="weatherinfo" style="display: none;">${weather["T3H"]}℃
                     ${sky } <br>
                  </td>
               </tr>
               <tr>
                  <td><img id="airicon" src="" /></td>
                  <td id="airinfo" style="display: none;">${airinfo }<br>
                  </td>
               </tr>
            </table>
         </div>
      </div>
   </div>

<!--상단이미지슬라이드--> <!-- 좀 더 고쳐서 꽉 채운 이미지로 하고 싶다 -->
   <div id="pslider">
      <div class="sliderp">
       <div><img src="<%=request.getContextPath() %>/upload/${pimglist[0].storedName }" alt="" /></div>
       <div><img src="<%=request.getContextPath() %>/upload/${pimglist[1].storedName }" alt="" /></div>
       <div><img src="<%=request.getContextPath() %>/upload/${pimglist[2].storedName }" alt="" /></div>
       <div><img src="<%=request.getContextPath() %>/upload/${pimglist[3].storedName }" alt="" /></div>
       <div><img src="<%=request.getContextPath() %>/upload/${pimglist[4].storedName }" alt="" /></div>
       </div>
    </div>
    
      <!-- 상단 -->
   <div id="place-header" class="plcontainer">
      <!-- 이름 -->
      <div id="header-name" >
         <div id="hname1">${place.pname }</div>
         <div id="hname2">${place.psubtitle }</div>
      </div>
      <!-- 빠른이동 -->
      <div id="header-quick">
         <div id="quick1" >
            <img id="qbtn1" class="qimg" src="../../../images/pl-chat.png" alt="" /><br>
            <span class="qname" id="">연락하기</span>
         </div>
         <div id="quick2" class="content">
         <c:if test="${likeplacebl eq false}">
            <img id="detailheart" class="qimg" src="../../../images/pl-heart-unpick.png" alt="" /><br>
         </c:if>
         <c:if test="${likeplacebl eq true}">
            <img id="detailheart" class="qimg" src="../../../images/pl-heart-pick.png" alt="" /><br>
         </c:if>
            <span class="qname">찜하기</span>
         </div>
         <div id="quick3" class="content">
            <img id="qbtn2" class="qimg" src="../../../images/pl-share.png" alt="" /><br>
            <span class="qname">공유하기</span>
         </div>
      </div>
      <!-- 기본정보 -->
      <div id="header-main">
         <div class="hm-content">
            <img src="../../../images/pl-contact.png" alt="" />
            <div class="hm-content2">${place.pphone } </div>
         </div>
         <div class="hm-content">
            <img src="../../../images/pl-map.png" alt="" />
            <div class="hm-content2">${place.ploc }</div>
            <div class="hm-content3"><a href="https://map.kakao.com/link/to/${place.pname },${place.px },${place.py }">길 찾기</a></div>
         </div> 
         <div class="hm-content">
            <img src="../../../images/pl-clock.png" alt="" />
            <div class="hm-content2">${place.poperate }</div>
         </div>
         <div class="hm-content">
            <img src="../../../images/pl-park.png" alt="" />
            <div class="hm-content2">${place.pparking }</div>
         </div>
      </div>
   </div>
   
   <!-- 메뉴 -->
   <div id="place-menu" class="plcontainer">
      <ul>
         <li class="current_page_item"><a href="#page" accesskey="1" title="">상세정보</a></li>
         <li class="current_page_item"><a href="#review-scroll" accesskey="2" title="">후기</a></li>
         <li class="current_page_item"><a href="#sideplace-wrapper" accesskey="3" title="">주변정보</a></li>
      </ul>
   </div>
      
   <!--상세정보-->
   <div id="page" class="plcontainer">
      <div id="page-coupon">
      <ul id="couponlist">
         <li id="cpimg1"><img src="<%=request.getContextPath() %>/upload/${cimglist[0].originname }" alt=""/></li>
         <li id="cpimg2"><img src="<%=request.getContextPath() %>/upload/${cimglist[1].originname }" alt=""/></li>
      </ul>
      </div>
      <div id="page-content">
         <div>
            <p>${place.pcontent }</p>
         </div>
      </div>
      <div id="page-menu">
         <div id="pmenutitle" class="page-title">메뉴</div>
         <div id="pmenu">
         <ul id = "pmenucol">
            <li><div class="pmenutext">${fn:split(place.pmenu_txt,'/')[0]}</div><div class="pmenuprice2">${fn:split(place.pmenu_txt,'/')[1]}</div></li>
            <li><div class="pmenutext">${fn:split(place.pmenu_txt,'/')[2]}</div><div class="pmenuprice">${fn:split(place.pmenu_txt,'/')[3]}</div></li>
            <li><div class="pmenutext">${fn:split(place.pmenu_txt,'/')[4]}</div><div class="pmenuprice2">${fn:split(place.pmenu_txt,'/')[5]}</div></li>
            <li><div class="pmenutext">${fn:split(place.pmenu_txt,'/')[6]}</div><div class="pmenuprice">${fn:split(place.pmenu_txt,'/')[7]}</div></li>
            <li><div class="pmenutext">${fn:split(place.pmenu_txt,'/')[8]}</div><div class="pmenuprice2">${fn:split(place.pmenu_txt,'/')[9]}</div></li>
            <li><div class="pmenutext">${fn:split(place.pmenu_txt,'/')[10]}</div><div class="pmenuprice">${fn:split(place.pmenu_txt,'/')[11]}</div></li>
         </ul>
         </div>
         <div id="pmenuimg"><div></div>
            <img id="menuimg" src="<%=request.getContextPath() %>/upload/${pimg.storedName }" />
         </div>
      </div>


      <div id="page-map">
         <div id="pmaptitle" class="page-title">찾아오는 길</div>
         <div id="pmap"></div>
      </div>
   </div>
   
   <!--후기-->
   <div id="review-scroll"></div>
   <div id="review-wrapper">
      <div id="review" >
         <div id="reviewtop">
            <div id="rvtitle">후기</div>
            <div id="rvcount">${cnt }개</div>
            <div id="rvstar">
            <c:choose>
                     <c:when test="${review[0]['review'].rvstar eq '1' }" >★☆☆☆☆ 별로예요</c:when>
                     <c:when test="${review[0]['review'].rvstar eq '2' }" >★★☆☆☆ 그냥 그래요</c:when>
                     <c:when test="${review[0]['review'].rvstar eq '3' }" >★★★☆☆ 보통이예요</c:when>
                     <c:when test="${review[0]['review'].rvstar eq '4' }" >★★★★☆ 맘에 들어요 </c:when>
                     <c:when test="${review[0]['review'].rvstar eq '5' }" >★★★★★ 아주 좋아요</c:when>
            </c:choose>
            </div>
            
         </div>
         
         <c:if test="${empty login}">
         <div id="rvwritebtn">
         	<a onclick="loclick();"><img src="../../../images/pl-docs.png" alt=""/></a>
         </div>
         </c:if>
         
         <c:if test="${not empty login}">
         <div id="rvwritebtn">
         	<a href="/place/reviewplus?pno=${place.pno }"><img src="../../../images/pl-docs.png" alt=""/></a>
         </div>
         </c:if>
         <div id="reviewcont">
            <div id="rvcont1">
               <p>${review[0]['review'].rvcontent }</p>
            </div>
            <div id="rvcontimg">
              <img src="<%request.getContextPath(); %>/upload/${review[0]['rvimg'].storedname}" alt="" />
            </div>
            <div class="clearfix"></div>
         </div>
        
          <c:if test="${empty login}">
          <div id="reviewbtn"><a onclick="loclick();">리뷰더보기</a></div>
          </c:if>
          <c:if test="${not empty login}">
          <div id="reviewbtn"><a href="/place/reviewplus?pno=${place.pno }">리뷰더보기</a></div>
          </c:if>
      </div>
   </div>
   
   <!-- 주변 정보 -->
   <div id="sideplace-wrapper" class="plcontainer">
      <div id="sideplace-title" class="page-title">주변 정보</div>
            <div id="sideplace-first">
               <div class="column1 spstyle">
                  <div class="sideplace-img"><a href="/place/detail?pno=${placelist[0]['place'].pno }"><img src="<%=request.getContextPath() %>/upload/${placelist[0]['img'].storedName }" /></a></div>
                  <div class="sideplace-content">${placelist[0]['place'].pname }</div>
         
               </div>
               <div class="column2 spstyle">
                  <div class="sideplace-img"><a href="/place/detail?pno=${placelist[1]['place'].pno }"><img src="<%=request.getContextPath() %>/upload/${placelist[1]['img'].storedName }"alt="" /></a></div>
                  <div class="sideplace-content">${placelist[1]['place'].pname }</div>
               </div>
               <div class="column3 spstyle">
                  <div class="sideplace-img"><a href="/place/detail?pno=${placelist[2]['place'].pno }"><img src="<%request.getContextPath(); %>/upload/${placelist[2]['img'].storedName}" alt="" /></a></div>
                  <div class="sideplace-content">${placelist[2]['place'].pname }</div>
               </div>
               <div class="column4 spstyle">
                  <div class="sideplace-img"><a href="/place/detail?pno=${placelist[3]['place'].pno }"><img src="<%request.getContextPath(); %>/upload/${placelist[3]['img'].storedName}" alt="" /></a></div>
                  <div class="sideplace-content">${placelist[3]['place'].pname }</div>
               </div>
            </div>
            <div class="clearfix"></div>
       
         
            <div id="sideplace-second">
               <div class="column1 spstyle">
                  <div class="sideplace-img"><a href="/place/detail?pno=${placelist[4]['place'].pno }"><img src="<%request.getContextPath(); %>/upload/${placelist[4]['img'].storedName}" alt="" /></a></div>
                  <div class="sideplace-content">${placelist[4]['place'].pname }</div>
               </div>
               <div class="column2 spstyle">
                  <div class="sideplace-img"><a href="/place/detail?pno=${placelist[5]['place'].pno }"><img src="<%request.getContextPath(); %>/upload/${placelist[5]['img'].storedName}" alt="" /></a></div>
                  <div class="sideplace-content">${placelist[5]['place'].pname }</div>
               </div>
               <div class="column3 spstyle">
                  <div class="sideplace-img"><a href="/place/detail?pno=${placelist[6]['place'].pno }"><img src="<%request.getContextPath(); %>/upload/${placelist[6]['img'].storedName}" alt="" /></a></div>
                  <div class="sideplace-content">${placelist[6]['place'].pname }</div>
               </div>
               <div class="column4 spstyle">
                  <div class="sideplace-img"><a href="/place/detail?pno=${placelist[7]['place'].pno }"><img src="<%request.getContextPath(); %>/upload/${placelist[7]['img'].storedName}" alt="" /></a></div>
                  <div class="sideplace-content">${placelist[7]['place'].pname }</div>
               </div>
            </div>
            <div class="clearfix"></div>
       </div>
   <div class="clearfix"></div>
</div>


<!-- 카카오맵 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8c85088b5e186aef655dd9fc104c9815&libraries=services"></script>
 <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=LIBRARY"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script>
<script>
      var container = document.getElementById('pmap');
      var options = {
            
         center: new kakao.maps.LatLng(33.450701, 126.570667),
         level: 3
      };

      var map = new kakao.maps.Map(container, options);

//       // 마커가 표시될 위치입니다 
//       var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667); 

//       // 마커를 생성합니다
//       var marker = new kakao.maps.Marker({
//           position: markerPosition
//       });

//       // 마커가 지도 위에 표시되도록 설정합니다
//       marker.setMap(map);
      
      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();

      // 주소로 좌표를 검색합니다
      geocoder.addressSearch('${place.ploc }', function(result, status) {

          // 정상적으로 검색이 완료됐으면 
           if (status === kakao.maps.services.Status.OK) {

              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
              var placey = result[0].y;
              var placex = result[0].x;

              // 결과값으로 받은 위치를 마커로 표시합니다
              var marker = new kakao.maps.Marker({
                  map: map,
                  position: coords
              });

              // 인포윈도우로 장소에 대한 설명을 표시합니다
              var infowindow = new kakao.maps.InfoWindow({
                  content: '<div style="width:150px;text-align:center;padding:6px 0;">${place.pname}</div>'
              });
              infowindow.open(map, marker);

              // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
              map.setCenter(coords);
          } 
      });
      
</script>

<!-- bxslider -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

<script>
  $(document).ready(function(){
    $('.sliderp').bxSlider();
  });
</script>

<c:import url="../layout/footer.jsp" />

  
</body>
</html>