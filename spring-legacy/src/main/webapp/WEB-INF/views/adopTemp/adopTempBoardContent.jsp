<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
              <a class="nav-link active btn-lg text-white text-center Board-font" href="#" style="background-color: rgb(41, 128, 185); color: black;"><h3>입양 | 임보</h3></a>
            </li>
          </ul>
          <!-- end of Vertical Nav -->
        </div>
        <!-- end of Left Menu -->


      <!-- Right area -->
      <div class="col-sm-9">
          
        <!-- Contents area -->
          <div id="comment" class="border border-primary p-3">
          <br>
          <h3 class="text-center"> 제목 </h3>

          <!-- 글 상세보기 영역 -->
          <table class="table">

              <th scope="row" class="text-center">작성자</th>
              <td>user1</td>
              <th scope="row" class="text-center">작성일</th>
              <td>2021.08.20</td>
              <th scope="row" class="text-center"></th>
              <td></td>
            </tr>
     
            <tr style="height: 300px" >
              <th scope="row" class="text-center" >내용</th>
              <td colspan="5">

                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit.<br>
                
              </td>
            </tr>
            <tr>
              <th scope="row" class="text-center">첨부파일</th>
              <td colspan="5">
                <ul>
                  <li>첨부파일1</li>
                  <li>첨부파일2</li>
                </ul>
              </td>
            </tr>
            <tr>
              <th scope="row" class="text-center"></th>
              <td colspan="5"></td>
            </tr>
          </table>

          <br>
          <div class="text-center">

            <button type="button" class="btn btn-primary btn-lg">
              <i class="material-icons align-middle">thumb_up_off_alt</i>
              <span class="align-middle">추천</span>
            </button>
            <button type="button" class="btn btn-secondary btn-lg ml-3">
              <i class="material-icons align-middle">thumb_down_off_alt</i>
              <span class="align-middle">비추천</span>
            </button>
            <button type="button" class="btn btn-danger btn-lg ml-3">
              <i class="material-icons align-middle">dangerous</i>
              <span class="align-middle">신고</span>
            </button>
            
          </div>
          <br><br><br>
        </div>
        <br>
        <div class="row">
          <div class = "col-sm-2">
            <button type="button" class="btn btn-secondary btn-sm " onclick="location.href = '/adopTemp/adopTempBoardList';">
              <i class="material-icons align-middle">list</i>
              <span class="align-middle">글목록</span>
            </button>
          </div>
          <div class = "col-sm-10 text-right">
            <button type="button" class="btn btn-primary text-white btn-sm">
              <i class="material-icons align-middle">edit</i>
              <span class="align-middle">글수정</span>
            </button>
            <button type="button" class="btn btn-danger btn-sm ml-3">
              <i class="material-icons align-middle">delete</i>
              <span class="align-middle">글삭제</span>
            </button>
          </div>
        </div>


          <!-- Comment -->
          <div id="comment" class="border border-secondary mt-2 p-3">
            <i class="material-icons">forum</i> 댓글

            <hr class="featurette-divider">

            <ul class="list-unstyled mt-4">
              <li class="media mb-2">
                <img src="/resources/images/kirby1.jpg" width="50" height="50" class="mr-3 rounded-circle">
                <div class="media-body">
                  <div class="row">
                    <div class="col-md-4">
                      <h6>홍길동 (user1)</h6>
                    </div>
                    <div class="col-md-8">
                      <div class="text-right text-secondary">
                        <time class="comment-date">2021-07-23 15:07:24</time>
                        | <a href="#!">삭제</a>
                        | <a href="#!">수정</a>
                        | <a href="#!">답글</a>
                      </div>
                    </div>
                  </div>
                  <p>All my girls vintage Chanel baby. So you can have your cake. Tonight, tonight, tonight, I'm walking on air. Slowly swallowing down my fear, yeah yeah. Growing fast into a bolt of lightning. So hot and heavy, 'Til dawn. That fairy tale ending with a knight in shining armor. Heavy is the head that wears the crown.</p>
                </div>
              </li>
              
              <li class="media mb-2">
                <img src="/resources/images/kirby2.jpg" width="50" height="50" class="mr-3 rounded-circle">
                <div class="media-body">
                  <div class="row">
                    <div class="col-md-4">
                      <h6>성춘향 (user2)</h6>
                    </div>
                    <div class="col-md-8">
                      <div class="text-right text-secondary">
                        <time class="comment-date">2021-07-23 15:07:24</time>
                        | <a href="#!">삭제</a>
                        | <a href="#!">수정</a>
                        | <a href="#!">답글</a>
                      </div>
                    </div>
                  </div>
                  <p>Maybe a reason why all the doors are closed. Cause once you’re mine, once you’re mine. Be your teenage dream tonight. Heavy is the head that wears the crown. It's not even a holiday, nothing to celebrate. A perfect storm, perfect storm.</p>
                </div>
              </li>

              <li class="media mb-2" style="margin-left: 40px;">
                <i class="material-icons">subdirectory_arrow_right</i>
                <img src="/resources/images/kirby4.jpg" width="50" height="50" class="mr-3 rounded-circle">
                <div class="media-body">
                  <div class="row">
                    <div class="col-md-4">
                      <h6>이몽룡 (user3)</h6>
                    </div>
                    <div class="col-md-8">
                      <div class="text-right text-secondary">
                        <time class="comment-date">2021-07-23 15:07:24</time>
                        | <a href="#!">삭제</a>
                        | <a href="#!">수정</a>
                        | <a href="#!">답글</a>
                      </div>
                    </div>
                  </div>
                  <p>Are you brave enough to let me see your peacock? There’s no going back. This is the last time you say, after the last line you break. At the eh-end of it all.</p>
                </div>
              </li>

              <!-- modify comment -->
              <li class="media mb-2" style="margin-left: 40px;">
                <i class="material-icons">subdirectory_arrow_right</i>
                <div class="media-body">
                  <form action="" method="post">
                    <div class="row">
                      <div class="col-10">
                        <div class="form-group">
                          <label>댓글 수정</label>
                          <textarea class="form-control" rows="3"></textarea>
                        </div>
                      </div>
                      <div class="col-2 align-self-center">
                        <button type="submit" class="btn btn-info btn-sm">수정</button>
                      </div>
                    </div>
                  </form>
                </div>
              </li>
              <!-- end of modify comment -->

              <!-- write reply comment -->
              <li class="media mb-2" style="margin-left: 80px;">
                <i class="material-icons">subdirectory_arrow_right</i>
                <div class="media-body">
                  <form action="" method="post">
                    <div class="row">
                      <div class="col-10">
                        <div class="form-group">
                          <label>답댓글 작성</label>
                          <textarea class="form-control" rows="3"></textarea>
                        </div>
                      </div>
                      <div class="col-2 align-self-center">
                        <button type="submit" class="btn btn-info btn-sm">작성</button>
                      </div>
                    </div>
                  </form>
                </div>
              </li>
              <!-- end of write reply comment -->
            </ul>


            <hr class="featurette-divider">


            <!-- write new comment -->
            <form action="" method="post">
              <div class="row my-4">
                <div class="col-10">
                  <div class="form-group">
                    <label for="exampleFormControlTextarea1">새댓글 작성</label>
                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                  </div>
                </div>
                <div class="col-2 align-self-center">
                  <button type="submit" class="btn btn-info btn-sm">작성</button>
                </div>
              </div>
            </form>
            <!-- end of write new comment -->
          </div>
          <!-- end of Comment -->
        </div>
        <!-- end of Contents area -->
      </div>
      <!-- end of Right area -->
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
    

   <%--  include footer.jsp --%>
   <jsp:include page="/WEB-INF/views/include/footer.jsp" />



    <!-- JavaScript -->
    <script src="/resources/js/jquery-3.6.0.js"></script>
    <script src="/resources/js/bootstrap.js"></script>


</body>
</html>