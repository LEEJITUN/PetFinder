<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
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
        <div class="col-sm-2 Board-font"></div>

        <!-- center area -->
        <div class="col-sm-8">

        <!-- Contents area -->
        <div class="border p-4 rounded">
            <h5 class="Board-font">내정보 수정</h5>

            <hr class="featurette-divider">

            <form action="/member/changeUserInfo" method="POST" enctype="multipart/form-data">
            
              <div class="form-group">
                <label for="memberId">
                  <i class="material-icons align-middle">account_box</i>
                  <span class="align-middle">아이디</span>
                </label>
                <input type="hidden" id="memberId" name="memberId" value="${ sessionScope.memberId }" >
                <input type="text" class="form-control" aria-describedby="idHelp" value="${ sessionScope.memberId }" disabled="disabled">
              </div>


              <div class="form-group">

		                <label for="memberName">
		                  <i class="material-icons align-middle">person</i>
		                  <span class="align-middle">이름</span>
		                </label>
		                <input type="text" class="form-control" id="memberName" name="memberName" value="${ memberVO.memberName }" required/>

              </div>

              <div class="form-group">
                <div class = "row">
                    <div class="col-sm-6">
                        <label for="memberBirthday">
                            <i class="material-icons align-middle">event</i>
                            <span class="align-middle">생년월일</span>
                          </label>
                          <input type="date" class="form-control" id="memberBirthday" name="memberBirthday" 
                          			value="${ memberVO.memberBirthday }" required>
                    </div>
                    <div class="col-sm-6">
                        <label for="memberGender">
                            <i class="material-icons align-middle">wc</i>
                            <span class="align-middle">성별 선택</span>
                        </label>
                        <select class="form-control" id="memberGender" name="memberGender">
                            <option value="" disabled selected>성별을 선택하세요.</option>
                            <option value="M" <c:if test="${ memberVO.memberGender == 'M'}">selected</c:if> >남자</option>
                            <option value="F" <c:if test="${ memberVO.memberGender == 'F'}">selected</c:if> >여자</option>
                            <option value="U" <c:if test="${ memberVO.memberGender == 'U'}">selected</c:if> >선택 안함</option>
                        </select>
                    </div>
                </div>
              </div>
              
              <div class="form-group">
                <label for="memberEmail">
                  <i class="material-icons align-middle">phone</i>
                  <span class="align-middle">핸드폰 번호</span>
                </label>
                <input type="number" class="form-control" id="memberPhoneNumber" name="memberPhoneNumber" value="${ memberVO.memberPhoneNumber }">
              </div>
              
              <div class="form-group">
                <label for="memberEmail">
                  <i class="material-icons align-middle">mail</i>
                  <span class="align-middle">이메일 주소</span>
                </label>
                <input type="email" class="form-control" id="memberEmail" name="memberEmail" value="${ memberVO.memberEmail }">
              </div>

              <div class="form-group">

	              <i class="material-icons align-middle">pets</i>
	              <span class="align-middle">반려동물 유무</span>
                    <div class="text-center">
                        <div style="padding-top: 15px;"></div>
                        <div class="custom-control custom-radio custom-control-inline">
                         <input type="radio" id="petsYes" name="memberPetYN" class="custom-control-input" value="Y" onclick="clickPetsYesOrNo()" 
                        	 <c:if test="${ memberVO.memberPetYN =='Y'}"> checked </c:if>>
                         <label class="custom-control-label" for="petsYes">반려동물 있음</label>
                        </div>
                       <div class="custom-control custom-radio custom-control-inline">
                         <input type="radio" id="petsNo" name="memberPetYN" class="custom-control-input" value="N" onclick="clickPetsYesOrNo()"
                        	 <c:if test="${ memberVO.memberPetYN =='N'}"> checked </c:if>>
                         <label class="custom-control-label" for="petsNo">반려동물 없음</label>
                       </div>
                    </div>
              </div>
   			  <div class="form-group">
			  	<div class = "row">
			  		<div class="col-sm-6" >
                        <label for="petName">
                            <i class="material-icons align-middle">pets</i>
                            <span class="align-middle">반려동물 이름</span>
                        </label>
                        <input type="text" class="form-control" id="petName" name="petName" value="${ petVO.petName }">
                    </div>
			  	    <div class="col-sm-6" >
                        <label for="petRegisterNumber">
                            <i class="material-icons align-middle">pets</i>
                            <span class="align-middle">반려동물 등록번호</span>
                        </label>
                        <input type="text" class="form-control" id="petRegisterNumber" name="petRegisterNumber" value="${ petVO.petRegisterNumber }">
                    </div>
        		</div>
        	</div>


              <div class="form-group">
				<div class = "row">
        			<div class="col-sm-6">
                        <label for="petKind">
                            <i class="material-icons align-middle">pets</i>
                            <span class="align-middle">반려동물 종류</span>
                        </label>
                        <select class="form-control" id="petKind" name="petKind" onclick="clickPetKind()" required>
                            <option value="" disabled selected>반려동물 종류을 선택하세요.</option>
                            <option value="D" <c:if test="${ petVO.petKind == 'D'}">selected</c:if> >강아지</option>
                            <option value="C" <c:if test="${ petVO.petKind == 'C'}">selected</c:if> >고양이</option>
                            <option value="O" <c:if test="${ petVO.petKind == 'O'}">selected</c:if> >기타</option>
                        </select>
                    </div>
                   	<div class="col-sm-6">
                   	    <label for="petDetailKind">
                            <i class="material-icons align-middle">pets</i>
                            <span class="align-middle">반려동물 품종</span>
                        </label>
                        <select class="form-control" id="petDetailKind" name="petDetailKind" required>

                        </select>
                   	</div>
               </div>
			</div>
			<div class="form-group">
				<div class = "row">
        			<div class="col-sm-6">
                        <label for="petBirthday">
                            <i class="material-icons align-middle">pets</i>
                            <span class="align-middle">반려동물 생일</span>
                        </label>
                        <input type="date" class="form-control" id="petBirthday" name="petBirthday"
                        value = "${petVO.petBirthday}" required>
                    </div>
                   	<div class="col-sm-6">
                   	    <label for="petGender">
                            <i class="material-icons align-middle">pets</i>
                            <span class="align-middle">반려동물 성별</span>
                        </label>
                     	<select class="form-control" id="petGender" name="petGender" required>
                            <option value="" disabled selected>반려동물의 성별을 선택하세요.</option>
                            <option value="M" <c:if test="${ petVO.petGender == 'M'}">selected</c:if> >남자</option>
                            <option value="F" <c:if test="${ petVO.petGender == 'F'}">selected</c:if> >여자</option>
                        </select>
                   	</div>
               </div>
			</div>
			
			 <div class="form-group">
				<div class = "row">
                   	<div class="col-sm-6">
                   	    <label for="petColor">
                            <i class="material-icons align-middle">pets</i>
                            <span class="align-middle">반려동물 털색</span>
                        </label>
                        <select class="form-control" id="petColor" name="petColor" required>
	                        <option value="" disabled selected>반려동물 털색을 선택하세요.</option>
			             	<option value="A" <c:if test="${ petVO.petColor == 'A'}">selected</c:if> >화이트</option>
	                      	<option value="B" <c:if test="${ petVO.petColor == 'B'}">selected</c:if> >블랙</option>
	                      	<option value="C" <c:if test="${ petVO.petColor == 'C'}">selected</c:if> >브라운</option>
                        </select>
                   	</div>
                   	  <div class="col-sm-6">
                   	    <label for="petCoatLength">
                            <i class="material-icons align-middle">pets</i>
                            <span class="align-middle">반려동물 털길이</span>
                        </label>
                        <select class="form-control" id="petCoatLength" name="petCoatLength" required>
                        <option value="" disabled selected>반려동물 털길이를 선택하세요.</option>
	  						<option value="S" <c:if test="${ petVO.petCoatLength == 'S'}">selected</c:if> >단모</option>
	                      	<option value="L" <c:if test="${ petVO.petCoatLength == 'L'}">selected</c:if> >장모</option>
	                      	<option value="C" <c:if test="${ petVO.petCoatLength == 'C'}">selected</c:if> >곱슬</option>
                        </select>
                   	</div>
               </div>
			</div>
			
			<div class="form-group">
				<div class = "row">
        			<div class="col-sm-12">
                        <label for="petSize">
                            <i class="material-icons align-middle">pets</i>
                            <span class="align-middle">반려동물 사이즈</span>
                        </label>
                        <select class="form-control" id="petSize" name="petSize" required>
                            <option value="" disabled selected>반려동물 사이즈를 선택하세요.</option>
	  						<option value="S" <c:if test="${ petVO.petSize == 'S'}">selected</c:if> >소형</option>
	                      	<option value="M" <c:if test="${ petVO.petSize == 'M'}">selected</c:if> >중형</option>
	                      	<option value="L" <c:if test="${ petVO.petSize == 'L'}">selected</c:if> >대형</option>
                        </select>
                    </div>
               </div>
			</div>
			
             <div class="text-center">
                <label class="mr-3">이벤트 등 알림 메일 수신동의 : </label>
                <div class="custom-control custom-radio custom-control-inline">
                  <input type="radio" id="customRadioInline1" name="memberNotice" class="custom-control-input" value="Y" 
                  			<c:if test="${ memberVO.memberNotice =='Y'}"> checked </c:if> >
                  <label class="custom-control-label" for="customRadioInline1">동의함</label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                  <input type="radio" id="customRadioInline2" name="memberNotice" class="custom-control-input" value="N"
                  			<c:if test="${ memberVO.memberNotice =='N'}"> checked </c:if> >
                  <label class="custom-control-label" for="customRadioInline2">동의 안함</label>
                </div>
              </div>
           
            <div class="my-3 text-center">
                
                <c:choose>
                	<%-- 내정보 보기 일 경우 --%>
                	<c:when test= "${ not empty sessionScope.memberId}"> 
                		<button type="submit" class="btn text-white ml-3" style="background-color: rgb(251, 215, 71);">수정</button>                		
                	</c:when>
                 	<%--  회원가입 일 경우 --%>
			        <c:otherwise>			        
                		<button type="submit" class="btn text-white ml-3" style="background-color:rgb(46, 204, 113);">회원가입</button>
                		<button type="reset" class="btn text-white ml-3" style="background-color: rgb(251, 215, 71);">초기화</button>
                	</c:otherwise>
                </c:choose>
            </div>
          </form>

          </div>
          <!-- end of Contents area -->
        </div>
        <!-- end of center area -->
        <!-- right area -->
        <div class="col-sm-2 Board-font"></div>
      </div>
    </div>
    <!-- end of middle container -->


	<!-- Footer -->
    <jsp:include page="/WEB-INF/views/include/footer.jsp" />
    
    
    <jsp:include page="/WEB-INF/views/include/function.jsp" />

	
    <!-- JavaScript -->
    <script src="http://code.jquery.com/jquery-3.3.1.js"></script>
	<script type="text/javascript"/></script>
	
    <script src="/resources/js/jquery-3.6.0.js"></script>
    <script src="/resources/js/bootstrap.js"></script>

	<script>
		$(document).ready(function(){
			selectBox('${ petVO.petKind }','${ petVO.petDetailKind }');
			clickPetsYesOrNo();
		});

	</script>
</body>
</html>