<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <!------ Include the above in your HEAD tag ---------->

    <!-- Google Font-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" >

   <%--  include head.jsp --%>
   <jsp:include page="/WEB-INF/views/include/head.jsp" />

   
</head>
<body>
    <div class="container login-container">
        <div class="row">
            <div class="col-md-6 login-form">
                <img src = "/resources/images/login.jpg">
            </div>
            <div class="col-md-6 login-form-2 ">
                <div style="padding-top: 20%;">
                    <p>로그인</p>
                    <form action="/member/login" method="POST">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="아아디를 입력하세요." id = "memberId" name = "memberId" required/>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" placeholder="비밀번호를 입력하세요." id="inputPassword" name = "memberPassword" required/>
                        </div>
                        <div class="form-group">
                            <div class = "row">
                                <input type="submit" class="btnSubmit btnLogin" value="Login" />
                                <input type="button" class="btnSubmit btnJoin" value="Join" onclick="location.href = '/member/join';"/>
                            </div>
                        </div>
                        <div class="custom-control custom-checkbox text-center mb-3">
						     <input type="checkbox" class="custom-control-input" id="customCheck1" name="rememberMe" value="remember-me">
						     <label class="custom-control-label" for="customCheck1">로그인 상태 유지</label>
						</div>
<!--                         <div class="form-group">
                            <a href="#" class="ForgetPwd">Forget Password?</a>
                        </div> -->
                    </form>
                </div>
            </div>
        </div>
    </div>

  <!-- JavaScript -->
  <script src="/resources/js/jquery-3.6.0.js"></script>
  <script src="/resources/js/bootstrap.js"></script>

  
  <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

	
	<script>

 	$(document).ready(function(){
		  	 sock.send('reply,cini1,cini2,zyfqn5x1,reply');
	});
 	

	</script>
	
</body>
</html>



