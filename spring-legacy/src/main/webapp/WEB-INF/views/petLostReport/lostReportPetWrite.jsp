<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

    .Board-font {
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
            <a class="nav-link active btn-lg text-white text-center Board-font" href="#"
              style="background-color: rgb(251, 215, 71); color: black;">
              <h3>반려동물 신고</h3>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" style="color: black;">반려동물 분실 신고</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" style="color: black;">유기동물 발견 신고</a>
          </li>
        </ul>
        <!-- end of Vertical Nav -->
      </div>
      <!-- end of Left Menu -->


      <!-- Right area -->
      <div class="col-sm-9">

        <!-- Contents area -->
        <div class="border border-warning p-4 rounded">
          <div class="row">
            <div class="col-sm-6 ">
              <h3 style="font-family: 'Noto Sans KR', sans-serif;">반려동물 분실 신고 글쓰기</h3>
            </div>
          </div>
          <hr class="featurette-divider">
          <div class="clearfix"></div>

          <form>
            <table class="table table-bordered" style="text-align: center;">
              <div class="form-group">
                <input class="form-control form-control-lg" type="text" placeholder="제목을 입력해주세요">
              </div>

              <thead class="thead-light">
                <tr>
                  <th scope="col" class="text-center pb-3">이름</th>
                  <td><input class="form-control" type="text" id="name" placeholder="이름을 입력해주세요." required></td>
                </tr>
                <tr>

                  <th scope="col" class="text-center pb-4">지역</th>
                  <td><div class="row">
                    <div class="col-md-6 ">
                    <div>
                        <select class="custom-select d-block w-100" id="country" required>
                            <option value="">(구) 선택</option>
                            <option>United States</option>
                        </select>
                    </div>
                    </div>
                    <div class="col-md-6 ">
                      <div>
                          <select class="custom-select d-block w-100" id="country" required>
                              <option value="">(동) 선택</option>
                              <option>United States</option>
                          </select>
                      </div>
                    </div>
                  </td>
                </tr>
                <tr>
                  <th scope="col" class="text-center pb-4">종류</th>
                  <td>
                    <div class="row">
                      <div class="col-md-6 ">
                        <div>
                          <select class="custom-select d-block w-100" id="kinds" required>
                            <option value="">선택</option>
                            <option>United States</option>
                          </select>
                        </div>
                      </div>
                      <div class="col-md-6 ">
                        <div>
                          <select class="custom-select d-block w-100" id="required" required>
                            <option value="">(품종) 선택</option>
                            <option>United States</option>
                          </select>
                        </div>
                      </div>
                  </td>
                </tr>
                <tr>
                  <th scope="col" class="text-center pb-4">나이</th>
                  <td>
                    <div class="form-row form-control-sm mb-3">
                      <div class="col">
                        <select class="custom-select" id="age" required>
                          <option selected disabled value="">선택</option>
                          <option>...</option>
                        </select>
                      </div>
                    </div>
                  </td>
                </tr>
                <tr>
                  <th scope="col" class="text-center pb-4">성별</th>
                  <td>
                    <div class="form-row form-control-sm mb-3">
                      <div class="col">
                        <select class="custom-select" id="gender" required>
                          <option selected disabled value="">선택</option>
                          <option>...</option>
                        </select>
                      </div>
                    </div>
                  </td>
                </tr>
                <tr>
                  <th scope="col" class="text-center pb-4">사이즈</th>
                  <td>
                    <div class="form-row form-control-sm mb-3">
                      <div class="col">
                        <select class="custom-select" id="size" >
                          <option selected disabled value="">선택</option>
                          <option>...</option>
                        </select>
                      </div>
                    </div>
                  </td>
                </tr>
                <tr>
                  <th scope="col" class="text-center pb-4">색상</th>
                  <td>
                    <div class="form-row form-control-sm mb-3">
                      <div class="col">
                        <select class="custom-select" id="color" >
                          <option selected disabled value="">선택</option>
                          <option>...</option>
                        </select>
                      </div>
                    </div>
                  </td>
                </tr>
                <tr>
                  <th scope="col" class="text-center pb-4">털 길이</th>
                  <td>
                    <div class="form-row form-control-sm mb-3">
                      <div class="col">
                        <select class="custom-select" id="length" required>
                          <option selected disabled value="">선택</option>
                          <option>...</option>
                        </select>
                      </div>
                    </div>
                  </td>
                </tr>
                <tr>
                  <th scope="col" class="text-center pb-5">특징</th>
                  <td>
                    <textarea class="form-control" id="feature" rows="3"></textarea>
                  </td>
                </tr>

            </table>


            <hr class="featurette-divider">

            <table class="table table-bordered">
              <thead class="thead-light">
                <tr>
                  <th scope="col" class="text-center pb-4" width="175px">첨부파일</th>
                  <td>
                    <div id="fileBox">
                      <div class="my-2">
                        <input type="file" name="files" multiple>
                        <button type="button" class="btn btn-secondary btn-sm delete-file">
                          <i class="material-icons align-middle">clear</i>
                          <span class="align-middle">삭제</span>
                        </button>
                      </div>
                    </div>
                  </td>
                </tr>
            </table>

            <div class="my-4 text-center">
              <button type="submit" class="btn btn-warning">
                <i class="material-icons align-middle">create</i>
                <span class="align-middle">답글등록</span>
              </button>
              <button type="reset" class="btn btn-secondary ml-3">
                <i class="material-icons align-middle">clear</i>
                <span class="align-middle">초기화</span>
              </button>
            </div>
          </form>



          <hr class="featurette-divider">

        </div>
        <!-- end of Contents area -->
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

</body>

</html>