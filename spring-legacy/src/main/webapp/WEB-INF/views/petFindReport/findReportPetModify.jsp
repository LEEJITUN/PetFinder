<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

 		<form action="/petFindReport/findReportPetModify" method="POST" enctype="multipart/form-data" id = "frm">
 		
 		    <input type = "hidden" value ="${ reportBoardVO.memberId }" 			name = "memberId" />
 		    <input type = "hidden" value = "${reportBoardVO.memberNickName}" 	name = "memberNickName" />
 		    <input type = "hidden" value ="${ reportBoardVO.reportId }" 			name="reportId" >                   
            <table class="table table-bordered" style="text-align: center;">
              <div class="form-group">
                <input class="form-control form-control-lg" type="text" name = "boardTitle"  id = "boardTitle"placeholder="제목을 입력해주세요"
				value = "${reportBoardVO.boardTitle}" required />
              </div>

              <thead class="thead-light">
                <tr>
                  <th scope="col" class="text-center pb-4">지역</th>
                  <td>
                    <div class="row">
                      <div class="col-md-6 ">
                        <div>
                        <input class="d-block w-100" type = "text" id ="address" name = "address"  
                        value = "${reportBoardVO.petVO.address}" required/>
                        <input type = "hidden" name = "sido" id ="sido" value = "${reportBoardVO.petVO.sido}" />
                        <input type = "hidden" name = "sigungu" id ="sigungu" value = "${reportBoardVO.petVO.sigungu}"/>
                        <input type = "hidden" name = "bname" id ="bname" value = "${reportBoardVO.petVO.bname}"/>
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
                     	<input class = "custom-select d-block w-100" type = "date" name = "findPetDate"  id = "findPetDate" value = "${reportBoardVO.petVO.findPetDate}" required/>
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
                            <option value="D" <c:if test = "${reportBoardVO.petVO.petKind eq 'D'}"> selected</c:if>>강아지</option>
                            <option value="C" <c:if test = "${reportBoardVO.petVO.petKind eq 'C'}"> selected</c:if>>고양이</option>
                            <option value="O" <c:if test = "${reportBoardVO.petVO.petKind eq 'O'}"> selected</c:if>>기타</option>
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
                          <option value="M" <c:if test = "${reportBoardVO.petVO.petGender eq 'M'}"> selected</c:if>>남자</option>
                          <option value="F" <c:if test = "${reportBoardVO.petVO.petGender eq 'F'}"> selected</c:if>>여자</option>
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
                         <option <c:if test = "${reportBoardVO.petVO.petSize eq null}"> selected</c:if>  disabled value="">선택</option>
                         <option value="S" <c:if test = "${reportBoardVO.petVO.petSize eq 'S'}"> selected</c:if>>소형</option>
	                     <option value="M" <c:if test = "${reportBoardVO.petVO.petSize eq 'M'}"> selected</c:if>>중형</option>
	                     <option value="L" <c:if test = "${reportBoardVO.petVO.petSize eq 'L'}"> selected</c:if>>대형</option>
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
                          <option <c:if test = "${reportBoardVO.petVO.petColor eq null}"> selected</c:if> disabled value="">선택</option>
    			          <option value="A" <c:if test = "${reportBoardVO.petVO.petColor eq 'A'}"> selected</c:if>>화이트</option>
	                      <option value="B" <c:if test = "${reportBoardVO.petVO.petColor eq 'B'}"> selected</c:if>>블랙</option>
	                      <option value="C" <c:if test = "${reportBoardVO.petVO.petColor eq 'C'}"> selected</c:if>>브라운</option>
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
                        <select class="custom-select" id="petCoatLength" name = "petCoatLength">
                          <option <c:if test = "${reportBoardVO.petVO.petCoatLength eq null}"> selected</c:if> disabled value="">선택</option>
                          <option value="S" <c:if test = "${reportBoardVO.petVO.petCoatLength eq 'S'}"> selected</c:if>>단모</option>
	                      <option value="L" <c:if test = "${reportBoardVO.petVO.petCoatLength eq 'L'}"> selected</c:if>>장모</option>
	                      <option value="C" <c:if test = "${reportBoardVO.petVO.petCoatLength eq 'C'}"> selected</c:if>>곱슬</option>
                        </select>
                      </div>
                    </div>
                  </td>
                </tr>
                <tr>
                  <th scope="col" class="text-center pb-5">특징</th>
                  <td>
                    <textarea class="form-control" id="petCharacter" name = "petCharacter" rows="3">
                    	${reportBoardVO.petVO.petCharacter}
                    </textarea>
                  </td>
                </tr>

            </table>


            <hr class="featurette-divider">

            <table class="table table-bordered">
              <thead class="thead-light">
                <tr>
                  <th scope="col" class="text-center pb-4" width="175px">첨부파일</th>
                  <td>
                  <button type="button" class="btn btn-primary my-3" id="btnAddFile">파일 추가</button>
				  <div><span>첨부 파일</span></div>
					  <!-- 기존 첨부파일 영역. 삭제할 파일정보 표현 용도 -->
					  <div id="oldFileBox">
					  
					  <!-- .delete-oldfile 삭제버튼 클릭 시 hidden input 요소의 name 속성을 oldfile → delfile 로 수정함 -->
					  <!-- 서버에서는 oldfile은 찾지않고 delfile만 찾아서 파일 삭제처리 -->
					  <c:forEach var="attach" items="${ attachList }">
					  	<input type="hidden" name="oldfile" value="${ attach.uuid }">
					  	<div>
					  		<span>${ attach.filename }</span>
					  		<button type="button" class="btn btn-primary btn-sm delete-oldfile">
			                	<i class="material-icons align-middle">clear</i>
			                	<span class="align-middle">삭제</span>
			                </button>
					  	</div>
					  </c:forEach>
					  
					  </div>
					  <!-- 신규 첨부파일 영역. 새로 첨부될 파일 업로드 용도 -->
					  <div id="newFileBox"></div>
                  </td>
                </tr>
            </table>

            <div class="my-4 text-center">
              <button type="submit" class="btn btn-success">
                <i class="material-icons align-middle">create</i>
                <span class="align-middle">수정</span>
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
	<script>
		const MAX_FILE_COUNT = 5;
		let fileCount = ${ fn:length(attachList) };  // 화면에 보이는 file 입력상자 개수
		
		
		// 기존 첨부파일 영역에 있는 삭제버튼 클릭했을 때
		$('button.delete-oldfile').on('click', function (event) {
			//let btn = event.target  // 이벤트 소스: 이벤트가 발생한 객체
			
			// 현재 클릭이 발생한 버튼(this)을 기준으로
			// 직계부모 div 요소의 이전(prev)요소인 hidden input 요소를 참조하여
			// name 속성값을 기존 oldfile에서 delfile로 수정하기
			$(this).parent().prev().prop('name', 'delfile');
			
			$(this).parent().remove(); // 현재 클릭한 버튼의 직계부모 div 요소를 삭제하기
			fileCount--;
		});
		
		
		// jQuuery 방식 이벤트 처리
		// 정적 이벤트 연결
		$('#btnAddFile').on('click', function (event) {
			if (fileCount >= MAX_FILE_COUNT) {
				alert('첨부파일은 최대 5개 까지만 첨부할 수 있습니다.')
				return;
			}
			
			var str = `
				<div class="my-2">
	                <input type="file" name="files" multiple>
	                <button type="button" class="btn btn-primary btn-sm delete-addfile">
	                	<i class="material-icons align-middle">clear</i>
	                	<span class="align-middle">삭제</span>
	                </button>
	            </div>
			`;
			$('div#newFileBox').append(str);
			
			fileCount++;
		});
		
		
		// 동적 이벤트 연결 - 이벤트 등록을 이미 존재하는 요소에게 위임하는 방식
		$('div#newFileBox').on('click', 'button.delete-addfile', function (event) {
			//event.target; // 실제 이벤트가 발생한 오브젝트
			
			$(this).closest('div').remove();  // empty()와 구분 주의!
			//$(this).parent().remove();
			
			fileCount--;
		});
	</script>
</body>

</html>