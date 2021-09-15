<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


    <!-- include libraries(jQuery, bootstrap) -->
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
    <link href="styles.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

    <!-- include summernote css/js -->
    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>


    <style>
        /* *{
            font-family: 'Noto Sans KR', sans-serif;
            font-size: 22px;
        } */

        .Board-font{
            font-family: 'Noto Sans KR', sans-serif;
            font-size: 22px;
        }
    </style>
</head>
<body>
       <!-- Navbar -->
       <nav class="navbar navbar-expand-lg navbar-light bg-light ">
        <div class="container Board-font">
            <div class="col-sm-2">
                <!-- Just an image -->
                <a class="navbar-brand" href="/index.html">
                    <img src="/resources/images/main_Title.png" width=100%" height="80%">
                </a>
             </div>
        
          <div class="collapse navbar-collapse " id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto mx-5">
                <div class="mx-3">
                        <li class="nav-item active">
                        <a class="nav-link" href="/findPets/">반려동물 찾기</a>
                        </li>
                </div>

                <div class="mx-3">
                <li class="nav-item dropdown active">
                    <a class="nav-link dropdown-toggle" href="/lostReportPets/" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    반려동물 신고
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/lostReportPets/">반려동물 분실 신고</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/findReportPets/">유기동물 발견 신고</a>
                    </div>
                </li>
                </div>
                <div class="mx-3">              
                    <li class="nav-item active">
                    <a class="nav-link" href="/adoptionPets/">입양 | 임보</a>
                </li>
                </div>
                <div class="mx-3"> 
                  <li class="nav-item dropdown active">
                    <a class="nav-link dropdown-toggle " href="/community/commuBoardList.html" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        커뮤니티
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/community">커뮤니티</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">갤러리</a>
                    </div>
                </li>
                </div>
            </ul>
  
        
            <div class=" my-2 my-lg-0" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown active">
                  <a class="nav-link dropdown-toggle " href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="material-icons">person</i>
                  </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                      <a class="dropdown-item" href="#">비밀번호 변경</a>
                      <a class="dropdown-item" href="#">내정보 수정</a>
                      <div class="dropdown-divider"></div>
                      <a class="dropdown-item" href="#">회원탈퇴</a>
                    </div>
                  </li>
                </ul>
            </div>
          </div>
        </div>
      </nav>
     <!-- end of Navbar -->

    <!-- middle container -->
    <div class="container mt-4">
      <div class="row">

        <!-- Left Menu -->
        <div class="col-sm-3 Board-font">

          <!-- Vertical Nav -->
          <ul class="nav flex-column nav-pills">
            <li class="nav-item">
              <a class="nav-link active btn-lg text-white text-center Board-font" href="#" style="background-color: rgb(189, 189, 189); color: black;"><h3>커뮤니티</h3></a>
            </li>

          </ul>
          <!-- end of Vertical Nav -->
        </div>
        <!-- end of Left Menu -->


        <!-- Right area -->
        <div class="col-sm-9" >
              <form  action="/write_action" method="POST">
                <!-- 제목 -->
                <div class="form-group">
                  <div class="input-group-prepend">
                    <select id="country" required>
                      <option value="">말머리</option>
                      <option>추천1</option>
                      <option>추천2</option>
                      <option>추천3</option>
                    </select>
                    <input type="text" class="form-control" id="title" name="title" value="제목을 입력하세요." onclick="inputSubject()">
                  </div>
                </div>
                <!-- 내용 -->
                <div id="summernote"  class="click2edit" ><p> 111 </p></div>
        
                <div class="my-4 text-center">
                  <button type="submit" class="btn btn-warning">
                    <i class="material-icons align-middle">create</i>
                    <span class="align-middle">글 등록하기</span>
                  </button>
                  <button type="reset" class="btn btn-success ml-3">
                    <i class="material-icons align-middle">clear</i>
                    <span class="align-middle">초기화</span>
                  </button>
                  <button type="button" class="btn btn-secondary ml-3" onclick="location.href = '/community/commuBoardList.html';">
                    <i class="material-icons align-middle">list</i>
                    <span class="align-middle">글목록</span>
                  </button>
                </div>
              </form>
        </div>
        <!-- end of Right area -->
      </div>
    </div>
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

     <!-- FOOTER -->
     <footer >
        <div class="container-fluid">
          <div class="row">
            <div class="col-sm-1 " style="margin-bottom: 1%; margin-left: 5%; margin-top: 1%; margin-right: 3%;">
              <!-- Just an image -->
              <a class="navbar-brand" href="/index.html">
                  <img src="/resources/images/main_Title.png" width="100%" height="80%">
              </a>
           </div>
            <div style="margin-top: 1%;" class ="text-center">
              <p style="font-size: 16px; color: black; font-family: sans-serif; ">(47291) 부산광역시 부산진구 중앙대로 708  |  전화번호 051-xxxx-xxxx</p>
              <p style="font-size: 16px; color: black; font-family: sans-serif; margin-top: -2%;">copyright. 2021 by JI YUN LEE. all rights reserved.</p>
            </div>
      
        </div>
      </footer>
      <!-- end of FOOTER -->



    <!-- JavaScript -->


   
    <script>
      $(document).ready(function() {
          $('#summernote').summernote({
              tabsize: 3,
              height: 800,
              lang: 'ko-KR' // 언어 세팅
          });
      });

      function inputSubject (){
        var strText = $(title).text();
      }
    </script>

    


</body>
</html>