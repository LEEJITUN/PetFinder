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
              <a class="nav-link active btn-lg text-white text-center Board-font" href="#" style="background-color: rgb(189, 189, 189); border-color: rgb(189, 189, 189); color: black;"><h3>커뮤니티</h3></a>
            </li>
          </ul>
          <!-- end of Vertical Nav -->
        </div>
        <!-- end of Left Menu -->


        <!-- Right area -->
        <div class="col-sm-9">

          <!-- Contents area -->
          <div class="border border-secondary p-4 rounded">
            <div class = "row">
                <div class = "col-sm-6 ">
                    <h3 style="font-family: 'Noto Sans KR', sans-serif;">커뮤니티</h3>
                </div>
                <div class = "col-sm-6">
                    <!-- 새글쓰기 버튼 -->
                    <button type="button" class="btn btn-secondary btn-sm float-right my-3 " onclick="location.href = '/community/commuBoardWrite.html';">
                    <i class="material-icons align-middle">create</i>
                    <span class="align-middle Board-font">글쓰기</span>
                    </button>
                </div>
            </div>

            <div class="clearfix"></div>

            <!-- 글목록 테이블 -->
            <table class="table table-hover" id="board">
              <thead>
                <tr>
                  <th scope="col" class="text-center">번호</th>
                  <th scope="col" class="text-center">제목</th>
                  <th scope="col" class="text-center">작성자</th>
                  <th scope="col" class="text-center">작성일</th>
                  <th scope="col" class="text-center">조회수</th>
                </tr>
              </thead>
              <tbody>
                
                    
                      <tr>
                        <td class="text-center">13</td>
                        <td>Vivamus viverra porttitor commodo.</td>
                        <td class="text-center">user1</td>
                        <td class="text-center">2021.08.20</td>
                        <td class="text-center">15</td>
                      </tr>
                      <tr>
                        <td class="text-center">12</td>
                        <td>In pulvinar fermentum erat a tincidunt. Nulla id magna sit ...</td>
                        <td class="text-center">user1</td>
                        <td class="text-center">2021.08.20</td>
                        <td class="text-center">15</td>
                      </tr>
                      <tr>
                        <td class="text-center">11</td>
                        <td>Nullam ac dignissim diam. Mauris vitae magna ipsum,</td>
                        <td class="text-center">user1</td>
                        <td class="text-center">2021.08.20</td>
                        <td class="text-center">150</td>
                      </tr>
                  <td class="text-center">10</td>
                  <td>
                    <a href="/board/boardContent.html">부산[동래구] - 강아지[포메라니안] 찾습니다.</a>
                  </td>
                  <td class="text-center">user1</td>
                  <td class="text-center">2021.08.20</td>
                  <td class="text-center">15000</td>
                </tr>
                <tr>
                  <td class="text-center">9</td>
                  <td>Vivamus viverra porttitor commodo.</td>
                  <td class="text-center">user1</td>
                  <td class="text-center">2021.08.20</td>
                  <td class="text-center">150</td>
                </tr>
                <tr>
                  <td class="text-center">8</td>
                  <td>In pulvinar fermentum erat a tincidunt. Nulla id magna sit ...</td>
                  <td class="text-center">user1</td>
                  <td class="text-center">2021.08.20</td>
                  <td class="text-center">15000</td>
                </tr>
                <tr>
                  <td class="text-center">7</td>
                  <td>Sed diam velit, dictum a iaculis sed, tempor sed mi.</td>
                  <td class="text-center">user1</td>
                  <td class="text-center">2021.08.20</td>
                  <td class="text-center">150</td>
                </tr>
                <tr>
                  <td class="text-center">6</td>
                  <td>Nullam ac dignissim diam. Mauris vitae magna ipsum,</td>
                  <td class="text-center">user1</td>
                  <td class="text-center">2021.08.20</td>
                  <td class="text-center">15</td>
                </tr>
                <tr>
                  <td class="text-center">5</td>
                  <td>eget vehicula metus. In euismod sollicitudin lorem eu.</td>
                  <td class="text-center">user1</td>
                  <td class="text-center">2021.08.20</td>
                  <td class="text-center">15000</td>
                </tr>
                <tr>
                  <td class="text-center">4</td>
                  <td>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</td>
                  <td class="text-center">user1</td>
                  <td class="text-center">2021.08.20</td>
                  <td class="text-center">1500</td>
                </tr>
                <tr>
                  <td class="text-center">3</td>
                  <td>Vivamus viverra porttitor commodo.</td>
                  <td class="text-center">user1</td>
                  <td class="text-center">2021.08.20</td>
                  <td class="text-center">15</td>
                </tr>
                <tr>
                  <td class="text-center">2</td>
                  <td>In pulvinar fermentum erat a tincidunt. Nulla id magna sit ...</td>
                  <td class="text-center">user1</td>
                  <td class="text-center">2021.08.20</td>
                  <td class="text-center">15</td>
                </tr>
                <tr>
                  <td class="text-center">1</td>
                  <td>Nullam ac dignissim diam. Mauris vitae magna ipsum,</td>
                  <td class="text-center">user1</td>
                  <td class="text-center">2021.08.20</td>
                  <td class="text-center">150</td>
                </tr>
              </tbody>
            </table>


            <!-- pagination area -->
            <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                <a class="page-link" href="#">Next</a>
                </li>
            </ul>
            </nav>
            <!-- end of pagination area -->

            <hr class="featurette-divider">

              
            <!-- Search area -->
            <form class="form-inline justify-content-center my-4">
          
              <div class="form-group mx-3">
                <label for="searchType">검색 조건</label>
                <select class="form-control mx-2" id="searchType" name="type">
                  <option value="" disabled selected>--</option>
                  <option value="T">제목</option>
                  <option value="C">내용</option>
                  <option value="W">작성자</option>
                </select>
              </div>

              <label for="searchKeyword">검색어</label>
              <input type="text" class="form-control mb-2 mr-sm-2 mx-2" id="searchKeyword" placeholder="검색어" name="keyword">

              <button type="submit" class="btn btn-secondary mb-2 text-white">
                <i class="material-icons align-middle">search</i>
                <span class="align-middle">검색</span>
              </button>
            </form>
            <!-- end of Search area -->
          </div>
          <!-- end of Contents area -->
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
    <script src="/resources/js/jquery-3.6.0.js"></script>
    <script src="/resources/js/bootstrap.js"></script>

</body>
</html>