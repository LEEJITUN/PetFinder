<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>
   <%--  include head.jsp --%>
   <jsp:include page="/WEB-INF/views/include/head.jsp" />


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
              style="background-color: rgb(46, 204, 113); color: black;">
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
        <div class="border border-success p-4 rounded">
          <div class="row">
            <div class="col-sm-6 ">
              <h3 style="font-family: 'Noto Sans KR', sans-serif;">유기동물 발견 신고 글쓰기</h3>
            </div>
          </div>
          <hr class="featurette-divider">
          <div class="clearfix"></div>

 		<form action="/petFindReport/findReportPetWrite" method="POST" enctype="multipart/form-data">
 		
 		    <input type = "hidden" value="${ sessionScope.memberId }" name = "memberId" />
 		    <input type = "hidden" value = "${sessionScope.memberNic}" name = "memberNickName" />
 		
            <table class="table table-bordered" style="text-align: center;">
              <div class="form-group">
                <input class="form-control form-control-lg" type="text" name = "boardTitle"  id = "boardTitle"placeholder="제목을 입력해주세요"/>
              </div>

              <thead class="thead-light">
                <tr>
                  <th scope="col" class="text-center pb-4">지역</th>
                  <td>
                    <div class="row">
                      <div class="col-md-6 ">
                        <div>
                        <input class="d-block w-100" type = "text" id ="address" name = "address" required/>
                        <input type = "hidden" name = "sido" id ="sido" />
                        <input type = "hidden" name = "sigungu" id ="sigungu"/>
                        </div>
                      </div>
                    <div class="col-md-6 ">
                        <div>
                        <button type = "button" onclick = "daumPostcode()" > 주소 찾기 </button>

                        </div>
                   </div>
                  </td>
                </tr>
                <tr>
                <th scope="col" class="text-center pb-4">날짜</th>
                  <td>
                    <div class="form-row form-control-sm mb-3">
                      <div class="col">
                     	<input class = "custom-select d-block w-100" type = "date" name = "findPetDate"  id = "findPetDate" required/>
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

                          <select class="custom-select d-block w-100" id="petKind" name="petKind" onclick="clickPetKind()" required>
         					<option value="" disabled selected>애완동물 종류을 선택하세요.</option>
                            <option value="D">강아지</option>
                            <option value="C">고양이</option>
                            <option value="O">기타</option>
                          </select>
                        </div>
                      </div>
                      <div class="col-md-6 ">
                        <div>
	                        <select class="form-control" id="petDetailKind" name="petDetailKind" >
	
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
                        <select class="custom-select" id="petGender" name = "petGender" required>
                          <option selected disabled value="">선택</option>
                          <option value="M">남자</option>
                          <option value="F">여자</option>
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
                        <select class="custom-select" id="petSize" name = "petSize">
                         <option selected disabled value="">선택</option>
                         <option value="S">소형</option>
	                     <option value="M">중형</option>
	                     <option value="L">대형</option>
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
                        <select class="custom-select" id="petColor" name = "petColor" >
                          <option selected disabled value="">선택</option>
    			          <option value="A">화이트</option>
	                      <option value="B">블랙</option>
	                      <option value="C">브라운</option>
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
                        <select class="custom-select" id="petCoatLength" namem = "petCoatLength">
                          <option selected disabled value="">선택</option>
                          <option value="S">단모</option>
	                      <option value="L">장모</option>
	                      <option value="C">곱슬</option>
                        </select>
                      </div>
                    </div>
                  </td>
                </tr>
                <tr>
                  <th scope="col" class="text-center pb-5">특징</th>
                  <td>
                    <textarea class="form-control" id="petCharacter" name = "petCharacter" rows="3"></textarea>
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
              <button type="submit" class="btn btn-success">
                <i class="material-icons align-middle">create</i>
                <span class="align-middle">발견 신고</span>
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

  <!-- FOOTER -->
  <footer>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-1 " style="margin-bottom: 1%; margin-left: 5%; margin-top: 1%; margin-right: 3%;">
          <!-- Just an image -->
          <a class="navbar-brand" href="/index.html">
            <img src="/resources/images/main_Title.png" width="100%" height="80%">
          </a>
        </div>


        <div style="margin-top: 1%;" class="text-center">
          <p style="font-size: 16px; color: black; font-family: sans-serif; ">(47291) 부산광역시 부산진구 중앙대로 708 | 전화번호
            051-xxxx-xxxx</p>
          <p style="font-size: 16px; color: black; font-family: sans-serif; margin-top: -2%;">copyright. 2021 by JI YUN
            LEE. all rights reserved.</p>
        </div>

      </div>
  </footer>
  <!-- end of FOOTER -->

	<jsp:include page="/WEB-INF/views/include/function.jsp" ></jsp:include>
	<jsp:include page="/WEB-INF/views/include/locationAPI.jsp" ></jsp:include>

  <!-- JavaScript -->
  <script src="/resources/js/jquery-3.6.0.js"></script>
  <script src="/resources/js/bootstrap.js"></script>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</body>

</html>