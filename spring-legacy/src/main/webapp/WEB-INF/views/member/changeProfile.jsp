<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html lang="ko">
<head>
    <%--  include head.jsp --%>
	<jsp:include page="/WEB-INF/views/include/head.jsp" />

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
	    <div class="border border-light p-4 rounded">
		<form id="profileForm" method="post" enctype="multipart/form-data">
	        <input type="hidden" id="memberId" name="memberId" value="${ sessionScope.memberId }" >
	        <fieldset>
	            <table class="tbl_model">
	                <colgroup>
	                    <col style="width:22%"><col>
	                </colgroup>
	                <thead class="thead-light">
	                <tr>
	                    <th scope="col" class="text-center pb-4">
	                        <div class="thcell">프로필 사진</div>
	                    </th>
	                    <td>                  
	                    	<div class="form-group">              
                				<div class="col-md-5 col-lg-5 " align="center"> 
                					<c:set var="fileCallPath" value="${ profilePicVO.uploadpath }/${profilePicVO.mid}/s_${ profilePicVO.uuid }_${ profilePicVO.filename }" />
				                	<img src="/display?fileName=${fileCallPath}" id="preview-image"  class="img-thumbnail">      
                				</div>
              				</div>
	          				<div class="form-group col-sm-6">
            					<div id="fileBox">
			  	  					<input type="file" name="file" id="input-image" accept="image/*">
		  						</div>
	          				</div>
	                    </td>
	                	</tr>
               	
	                	<tr>
	                    <th scope="col" class="text-center pb-4">
	                        <div class="thcell"><label for="inpNickname">별명</label></div>
	                    </th>
	                    <td>
	                        <div class="col-sm-6">
	                            <p class="contxt_webctrl nickname">
	                            <input type="text" class="form-control" id="memberNickName" name="memberNickName" value="${ memberVO.memberNickName }" style="width:254px"/>
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