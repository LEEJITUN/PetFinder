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
   <!--  include topNavbar.jsp  -->
   <jsp:include page="/WEB-INF/views/include/topNavbar.jsp" />
   
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
      <div class="col-sm-9">

        <!-- Contents area -->
        <div class="border border-secondary p-4 rounded">
          <div class="row">
            <div class="col-sm-6 ">
              <h3 style="font-family: 'Noto Sans KR', sans-serif;">커뮤니티 글쓰기 </h3>
            </div>
          </div>
          <hr class="featurette-divider">

          <div class="clearfix"></div>
          <form action="/community/commuBoardWrite" method="POST">
          
          <input type="hidden" value="${ sessionScope.memberId }" name="memberId"/>
          <input type="hidden" value="${ sessionScope.memberNic }" name="memberNickName">
          
            <div class="form-group">
              <input type="text" class="form-control" id="boardTitle" name="boardTitle" placeholder="제목을 입력해주세요." onclick="inputSubject()" required>
            </div>
            <!-- 내용-->
            <textarea id="summernote"  style="width:100%;height:500px;" name="boardContent" placeholder="내용을 입력해주세요." required></textarea>

            <div class="my-4 text-center">
              <button type="submit" class="btn btn-warning" onclick="location.href = '/community/commuBoardContent';">
                <i class="material-icons align-middle">create</i>
                <span class="align-middle">글 등록하기</span>
              </button>
              <button type="reset" class="btn btn-success ml-3">
                <i class="material-icons align-middle">clear</i>
                <span class="align-middle">초기화</span>
              </button>
              <button type="button" class="btn btn-secondary ml-3" onclick="location.href = '/community/commuBoardList';">
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
    <div class="container-fluid">
      <hr style="border: solid 2px lightgray">
      <div class="mx-5">
        <a href="#!" style="color: gray;">&ensp; 개식용 종식 &ensp;</a>
        | <a href="#!" style="color: gray;">&ensp; 케이지 프리 코리아 &ensp;</a>
        | <a href="#!" style="color: gray;">&ensp; 동물보호 관리시스템 &ensp;</a>
      </div>
      <hr style="border: solid 2px lightgray">
    </div>
    <!-- end of a link container -->
    <!-- a link container -->

   <%--  include footer.jsp --%>
   <jsp:include page="/WEB-INF/views/include/footer.jsp" />


    <!-- JavaScript -->
    <script src="/resources/js/jquery-3.6.0.js"></script>
    <script src="/resources/js/bootstrap.js"></script>


    <!-- JavaScript -->


    <script>
      $(document).ready(function () {
        $('#summernote').summernote({
          tabsize: 3,
          height: 800,
          lang: 'ko-KR' // 언어 세팅
        });
      });

      function inputSubject() {
        var strText = $(title).text();
      }
    </script>

</body>

</html>