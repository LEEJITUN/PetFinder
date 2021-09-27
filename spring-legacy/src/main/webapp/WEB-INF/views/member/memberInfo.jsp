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
        }*/

        .Board-font{
          font-family: 'Noto Sans KR', sans-serif;
          font-size: 22px;
        }
        .box {
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
   
	<br><br><br>
	
    <!-- middle container -->
     
    
    <div class="container mt-4">
        <div class="row mb-2">
            <!-- 프로필 설정 -->
            <div class="col-md-6">
              <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div class="col p-4 d-flex flex-column position-static">
                  <h3 class="mb-0"><strong class="d-inline-block mb-2 text-primary">MY 프로필</strong></h3>
                  <br>
                  <table>
                    <tbody>  
                        <tr>                                            
                            <th>
                            	<div class="box" style="background: #BDBDBD;">
                            	      <c:if test = "${ memberProfileVO.uploadpath != null}">
                            	    	<c:set var="fileCallPath" value="${ memberProfileVO.uploadpath }/s_${ memberProfileVO.uuid }_${ memberProfileVO.filename }" />
			                			<img class="profile" src="/display?fileName=${fileCallPath}" id="preview-image"  class="img-thumbnail">
					              	  </c:if>
					              	  
					              	  <c:if test = "${  profileVO.uploadpath == null}">
										<img src="/resources/images/default.png" class="profile">
					                  </c:if>
                                </div> 
                            </th>
                            <th>별명</th>
                            <td>${ memberVo.memberNickName }</td>
                        </tr>
                    </tbody>
                  </table>
                  <br>
                  <button type="button" class="btn btn-light"><a href="/member/changeProfile?memberId=${ sessionScope.memberId }" class="stretched-link">수정</a></button>
                </div>
              </div>
            </div>

            <!-- 내정보 설정 -->
            <div class="col-md-6">
              <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div class="col p-4 d-flex flex-column position-static">
                  <h3 class="mb-0"><strong class="d-inline-block mb-2 text-warning">내정보 수정</strong></h3>
                  <br>
                  <table>
                  <input type="hidden" id="memberId" name="memberId" value="${ sessionScope.memberId }" />
                    <tbody>   
                        <tr>
                            <th>폰번호</th>
                            <td>${ memberVo.memberPhoneNumber }</td>
                        </tr>                    
                        <tr>                
                            <th>이메일주소</th>
                            <td>${ memberVo.memberEmail }</td>
                        </tr>
                        <tr>
                            <th>알림수신</th>
                            <td>${ memberVo.memberNotice }</td>
                        </tr>    
                        <tr>
                            <th>등록번호</th>
                            <td>${ memberVo.petVO.petRegisterNumber }</td>
                        </tr>                                                                    
                    </tbody>
                  </table>
                  <br>
                  <button type="button" class="btn btn-light"><a href="/member/changeUserInfo?memberId=${ sessionScope.memberId }" class="stretched-link">수정</a></button>
                </div>
              </div>
            </div>
            
			<!-- 패스워드 변경 -->
            <div class="col-md-6">
              <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div class="col p-4 d-flex flex-column position-static">
                  <h3 class="mb-0"><strong class="d-inline-block mb-2 text-success">비밀번호</strong></h3>
                  <br>
                  <p>주기적인 비밀번호 변경을 통해 개인정보를 안전하게 보호하세요.</p>
                  <br><br><br>
                  <button type="button" class="btn btn-light"><a href="/member/changePasswd/" class="stretched-link">비밀번호 변경</a></button>
                </div>
              </div>
            </div>
            
			<!-- 탈퇴하기 -->
            <div class="col-md-6">
              <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div class="col p-4 d-flex flex-column position-static">
                  <h3 class="mb-0"><strong class="d-inline-block mb-2 text-danger">탈퇴하기</strong></h3>
                  <br>
                  <p>회원탈퇴를 신청하기 전에 안내 사항을 꼭 확인해주세요.</p>
                  <br><br><br>
                  <button type="button" class="btn btn-light"><a href="/member/remove/" class="stretched-link">탈퇴하기</a></button>
                </div>
              </div>
            </div>                        

        </div>    
    </div>
    
    </form>
    <!-- end of middle container -->
    
    <br><br><br>
    
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