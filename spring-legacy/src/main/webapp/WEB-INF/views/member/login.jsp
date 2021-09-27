<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>

    
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <!-- Google Font-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">


    
<style>
    .login-container{
        margin-top: 10%;
        margin-bottom: 5%;
    }
    
    .login-form-2{
        padding: 8%;
        box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0 rgba(0, 0, 0, 0.19);
    }
    .login-form-2 p{
        font-family: 'Noto Sans KR', sans-serif;
        font-size: 50px;
        text-align: center;
        color: #333;
    }
    .login-container form{
        padding: 5%;
    }
    .btnSubmit
    {
        width: 40%;
        border-radius: 1rem;
        padding: 1.5%;
        border: none;
        cursor: pointer;
        margin-right: 10px;
    }
    
    .login-form-2 .btnLogin{
        font-weight: 600;
        color: white;
        background-color:rgb(46, 204, 113);
        margin-right: 40px;
        margin-left: 20px;
    }

    .login-form-2 .btnJoin{
        font-weight: 600;
        color: white;
        background-color:rgb(251, 215, 71);
    }
    .login-form-2 .ForgetPwd{
        color: rgb(46, 204, 113);
        font-weight: 600;
        text-decoration: none;
    }
    </style>
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
                        <div class="form-group">
                            <a href="#" class="ForgetPwd">Forget Password?</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>



