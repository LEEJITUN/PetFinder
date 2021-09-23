<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <style>
        .font{
            font-family: 'Noto Sans KR', sans-serif;
            font-size: 22px;
        }
    </style>
 	<!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light font">
        <div class="container">
            <div class="col-sm-2">
                <!-- Just an image -->
                <a class="navbar-brand" href="/home.html">
                    <img src="/resources/images/main_Title.png" width="100%" height="80%">
                </a>
             </div>
        
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto mx-5">
               
                        <li class="nav-item active">
                        	<a class="nav-link" href="/petFindReport/findReportPetList">반려동물 찾기</a>
                        </li>
                
                     <%-- 로그인 사용자일때 --%>
       				<c:if test="${ not empty sessionScope.memberId }">
		                <li class="nav-item dropdown active">
		                    <a class="nav-link dropdown-toggle" href="/petFindReport/findReportPetList" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		                    반려동물 신고
		                    </a>
		                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
		                        <a class="dropdown-item" href="/petLostReport/lostReportPetWrite">반려동물 분실 신고</a>
		                        <div class="dropdown-divider"></div>
		                        <a class="dropdown-item" href="/petFindReport/findReportPetWrite">반려동물 발견 신고</a>
		                    </div>
		                </li>
               		</c:if>
                          
                    <li class="nav-item active">
                   		<a class="nav-link" href="/adopTemp/adopTempBoardList">입양 | 임보</a>
                	</li>
               
               
	                <li class="nav-item dropdown active">
	                    <a class="nav-link dropdown-toggle " href="/community/commuBoardList" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                        커뮤니티
	                    </a>
	                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
	                        <a class="dropdown-item" href="/community/commuBoardList">갤러리</a>
	                    </div>
	                </li>
            	</ul>
	            <div class=" my-2 my-lg-0" id="navbarSupportedContent">
	              <ul class="navbar-nav mr-auto">
	              <li class="nav-item dropdown active">
	                
	              <%--로그인했을 때  내정보 --%> 
		          <c:choose>
			       	  <%-- 로그인 했을 때 --%>
			          <c:when test= "${ not empty sessionScope.memberId}"> 
			            <span>${ sessionScope.memberId }</span>
    					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                    <i class="material-icons">person</i>
		                    <div class="box" style="background: #BDBDBD; margin-left: 75%;">
	                    		<img class="profile" src="/display?fileName=${fileCallPath}"  class="img-thumbnail">
	                   		</div>
	                  	</a>
	                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
	                      <a class="dropdown-item" href="#">비밀번호 변경</a>
	                      <a class="dropdown-item" onclick="location.href = '/member/memberInfo?memberId=${ sessionScope.memberId }';">내정보 수정</a>
	                      <div class="dropdown-divider"></div>
	                	  <a class="dropdown-item" onclick="location.href = '/member/logout';">로그아웃</a>
	                    </div> 
			          </c:when>  
			          <%--  로그인 안했을 때 --%>
			          <c:otherwise>
			             <a href="/member/login" class="mx-2">로그인</a> | 
			             <a href="/member/join" class="mx-2">회원가입</a>
			          </c:otherwise>
		       	  </c:choose>
	              </li>
	             </ul>
	          </div>
          </div>
        </div>
      </nav>
      <!-- end of Navbar -->