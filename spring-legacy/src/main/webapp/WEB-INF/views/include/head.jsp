<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PET FINDER</title>
    
    <!-- Google Font-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">

    <!-- Google Fonts and Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="js" href="/resources/js/index.js">
	<link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>

     <style>
      .Board-font{
          font-family: 'Noto Sans KR', sans-serif;
          font-size: 22px;
      }
      .box {
	      width: 50px;
	      height: 50px; 
	      border-radius: 50%;
	      overflow: hidden;
 		}
 	 .profile {
	      width: 100%;
	      height: 100%;
	      object-fit: cover;
  	}
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
  
 