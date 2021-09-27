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
            width: 200px;
            height: 200px; 
            border-radius: 50%;
            overflow: hidden;
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
	    <br>
	    <div class="border border-light p-4 rounded">
		<form id="/member/changeProfile" method="POST" enctype="multipart/form-data">
	        <fieldset>
	            <table class="tbl_model">
	                <colgroup>
	                    <col style="width:22%"><col>
	                </colgroup>
	                <thead class="thead-light">
	                <tr>
	                    <th scope="col" class="text-center pb-4">
	                       <!--  <div class="thcell">프로필 사진</div> -->
	                    </th>
	                    <td>          
	                    	<div class="form-group">              
			                   <div class=profile_photo style="background: #BDBDBD;" id = "profileBox"> 
		                          <c:if test = "${ memberProfileVO.uploadpath != null}">
                           	    	<c:set var="fileCallPath" value="${ memberProfileVO.uploadpath }/s_${ memberProfileVO.uuid }_${ memberProfileVO.filename }" />
		                			<img class="profile"  src="/display?fileName=${fileCallPath}" id="preview-image" >
				              	  </c:if>
				              	  
				              	  <c:if test = "${  memberProfileVO.uploadpath == null}">
									<img src="/resources/images/default.png" class="profile" id="preview-image">
				                  </c:if>
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
	                <button type="reset" id ="resetBtn" class="btn btn-secondary" onclick="resetAll('${ sessionScope.memberId}')">취소</button>
	            </div>
	        </fieldset>
	    </form>
		</div>
	  </div>
	</div>
	<br><br><br>
	
	<!-- end of middle container -->


	<!-- Footer -->
    <jsp:include page="/WEB-INF/views/include/footer.jsp" />
    
    
    <jsp:include page="/WEB-INF/views/include/function.jsp" />

	<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
	<script type="text/javascript"/></script>
	

    <%-- JavaScript --%>
    <script src="/resources/js/jquery-3.6.0.js"></script>
    <script src="/resources/js/bootstrap.js"></script>

	<script>
		//  프로필 리셋
		function resetAll(memberId) {
				console.log('memberId',memberId);
				 // ajax 함수 호출
				$.ajax({
					url: '/api/memberPropicDelete/'+memberId+'.json',
					method: 'DELETE',
					contentType: 'application/json; charset=UTF-8',
					success: function (data) {
						console.log('date',data);
						resetData();
					},
					error: function (request, status, error) {
						alert('code: ' + request.status + '\n message: ' + request.responseText + '\n error: ' + error);
					}
				});
				
		}
		
		function resetData(){
			$('#preview-image').replaceWith('<img src="/resources/images/default.png" class="profile" id="preview-image">');
		}
	
	</script>
</body>
</html>