<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    </style>
</head>
<body>
    <!--  include topNavbar.jsp  -->
	<jsp:include page="/WEB-INF/views/include/topNavbar.jsp" />
	
	<br><br>
    <!-- middle container -->
    <div class="container mt-4">
      <div class="row">

        <!-- Left Menu -->
        <div class="col-sm-2 Board-font"></div>

        <!-- center area -->
        <div class="col-sm-8">

        <!-- Contents area -->
        <div class="border p-4 rounded">
            <h5 class="Board-font">비밀번호 변경</h5>
            <p><strong>* 안전한 비밀번호로 내정보를 보호하세요.</strong>
			<p>	- <strong style="color:red">다른 아이디/사이트에서 사용한 적 없는 비밀번호</strong><br>
				- <strong style="color:red">이전에 사용한 적 없는 비밀번호</strong>가 안전합니다.<br>
            <br>			
            <hr class="featurette-divider">
			<br>
            <form action="/member/changePasswd" method="POST" enctype="multipart/form-data">
                        
             <div class="form-group">
                <label for="memberId">
                  <i class="material-icons align-middle">account_box</i>
                  <span class="align-middle">아이디</span>
                </label>
                <input type="hidden" id="memberId" name="memberId" value="${ sessionScope.memberId }" >
                <input type="text" class="form-control" aria-describedby="idHelp" value="${ sessionScope.memberId }" disabled="disabled">
              </div>
				<br>
              <div class="form-group">
                <label for="memberPassword">
                  <i class="material-icons align-middle">lock</i>
                  <span class="align-middle">현재 비밀번호</span>
                </label>
                <input type="password" class="form-control" id="memberPassword" name="memberPassword" aria-describedby="pwdHelp" required>
                <small id="pwdHelp" class="form-text text-muted">비밀번호는 필수 입력 요소입니다.</small>
              </div>
              <div class="form-group">
                <label for="password2">
                  <i class="material-icons align-middle">check</i>
                  <span class="align-middle">새 비밀번호</span>
                </label>
                <input type="password" class="form-control" id="memberPassword2" name="memberPassword2" required>
              </div>
          <!-- 
              <div class="form-group">
                <label for="password2">
                  <i class="material-icons align-middle">check</i>
                  <span class="align-middle">새 비밀번호 확인</span>
                </label>
                <input type="password" class="form-control" id="memberPassword2" required>
              </div> 
           -->
              
            <br><br>
            <div class="my-3 text-center">
                <button type="submit" class="btn btn-success">변경하기</button>
            </div>
          </form>

          </div>
          <!-- end of Contents area -->
        </div>
        <!-- end of center area -->
        <!-- right area -->
        <div class="col-sm-2 Board-font"></div>
      </div>
    </div>
    <!-- end of middle container -->
	<br>
    
	<!-- Footer -->
    <jsp:include page="/WEB-INF/views/include/footer.jsp" />
    
    <jsp:include page="/WEB-INF/views/include/function.jsp" />

	<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
	<script type="text/javascript"/></script>

	
    <%-- JavaScript --%>
    <script src="/resources/js/jquery-3.6.0.js"></script>
    <script src="/resources/js/bootstrap.js"></script>

</body>
</html>