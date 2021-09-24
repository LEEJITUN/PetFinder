<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <!-- Google Font-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">

    <!-- Google Fonts and Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/resources/css/bootstrap.css">


    <style>
        /* *{
            font-family: 'Noto Sans KR', sans-serif;
            font-size: 22px;
        } */

        .Board-font{
            font-family: 'Noto Sans KR', sans-serif;
            font-size: 22px;
        }
        .profile_photo {
            width: 100px;
            height: 100px; 
            border-radius: 50%;
            overflow: hidden;
        }
        .profile {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }        
    </style>
</head>
<body>
    <!--  include topNavbar.jsp  -->
	<jsp:include page="/WEB-INF/views/include/topNavbar.jsp" />
	
	<!-- middle container -->
	<br><br><br>
	<div class="container">
	  <div class="jumbotron">
	    <div class="p_header">
		    <h2>프로필 수정</h2>
		    <p> PET FINDER 대표 프로필과 별명을 수정 하실 수 있습니다.</p>
		</div>
	    <br>
		<form id="profileForm" method="post" enctype="multipart/form-data">
	        <input type="hidden" id="memberId" name="memberId" value="${ sessionScope.memberId }" >
	        <fieldset>
	            <table border="0" class="tbl_model">
	                <colgroup>
	                    <col style="width:22%"><col>
	                </colgroup>
	                <thead class="thead-light">
	                <tr>
	                    <th scope="col" class="text-center pb-4">
	                        <div class="thcell">프로필 사진</div>
	                    </th>
	                    <td>
	                    
	                        <div class="tdcell">
	                        	<div class="profile_photo">
                                	<img class="profile" src="/resources/images/default.png" class="img-thumbnail">                   
	                            </div>                                
	                        </div>
	                        <br>
	                        <div class="btn_area_btm">
	                                <span class="btn_file">
	                                	
	                                    <label for="inputImage" class="btn_model"><b id="btnChangeProfile" class="btn2" onclick="clickcr(this,'prf.upimg','','',event);">사진변경</b></label>
	                                    <input type="file" id="inputImage" name="profileImage"  accept="image/*" />
	                                    <button type="button" class="btn btn-outline-dark btn-sm">삭제</button>
	                                </span>
	                        </div>
	                    </td>
	                	</tr>
	                	
	                	<tr>
	                    <th scope="col" class="text-center pb-4">
	                        <div class="thcell"><label for="inpNickname">별명</label></div>
	                    </th>
	                    <td>
	                        <div class="tdcell">
	                            <p class="contxt_webctrl nickname">
	                                <input type="text" name="memberNickName" id="memberNickName" value="${ memberVO.memberId }" style="width:254px">
	                                <!-- Enter 입력으로 submit이 되는걸 방지하기 위한 Input -->
	                                <input type="text" style="display: none;" >
	                            </p>
	                        </div>
	                    </td>
	                </tr>
	                </thead>
	            </table>
	            
	            <br>
	            <div class="btn_wrap">
	                <button type="submit" class="btn btn-success">적용</button>
	                <button type="reset" class="btn btn-secondary">취소</button>
	            </div>
	        </fieldset>
	    </form>

	  </div>
	</div>
	<br><br><br>
	
	<!-- end of middle container -->

    <!-- a link container -->
    <div class="container-fluid" >
        <hr style="border: solid 2px lightgray">
        <div class="mx-5" >
            <a href="#!"  style="color: gray;">&ensp; 개식용 종식 &ensp;</a>
        | <a href="#!" style="color: gray;">&ensp; 케이지 프리 코리아 &ensp;</a>
        | <a href="#!" style="color: gray;">&ensp; 동물보호 관리시스템 &ensp;</a>
        </div>
        <hr style="border: solid 2px lightgray">
    </div>
    <!-- end of a link container -->
    <!-- a link container -->

    
   <%--  include footer.jsp --%>
   <jsp:include page="/WEB-INF/views/include/footer.jsp" />

	<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
	<script type="text/javascript"/></script>
	
	<jsp:include page="/WEB-INF/views/include/function.jsp" ></jsp:include>

	
	
     <%-- JavaScript --%>
    <script src="/resources/js/jquery-3.6.0.js"></script>
    <script src="/resources/js/bootstrap.js"></script>

</body>
</html>