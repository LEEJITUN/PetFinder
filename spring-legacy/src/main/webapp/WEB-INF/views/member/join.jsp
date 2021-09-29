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
        <div class="col-sm-2 Board-font"></div>

        <!-- center area -->
        <div class="col-sm-8">

        <!-- Contents area -->
        <div class="border p-4 rounded">
            <h5 class="Board-font">회원 가입</h5>

            <hr class="featurette-divider">

            <form action="/member/join" method="POST" enctype="multipart/form-data">
            
           	  <div class="form-group">              
                <div class="col-md-5 col-lg-5 " align="center"> 
                <c:set var="fileCallPath" value="${ profilePicVO.uploadpath }/${profilePicVO.mid}/s_${ profilePicVO.uuid }_${ profilePicVO.filename }" />
                <img src="/display?fileName=${fileCallPath}" id="preview-image"  class="img-thumbnail">
                </div>
              </div>
	          <div class="form-group">
            	<div id="fileBox">
			  	  <input type="file" name="file" id="input-image" accept="image/*">
		  		</div>
	          </div>
            
              <div class="form-group">
                <label for="memberId">
                  <i class="material-icons align-middle">account_box</i>
                  <span class="align-middle">아이디</span>
                </label>
                <input type="text" class="form-control" id="memberId" name="memberId"  aria-describedby="idHelp" required autofocus>
                <small id="idHelp" class="form-text text-muted">아이디는 필수 입력 요소입니다.</small>
              </div>

              <div class="form-group">
                <label for="memberPassword">
                  <i class="material-icons align-middle">lock</i>
                  <span class="align-middle">비밀번호</span>
                </label>
                <input type="password" class="form-control" id="memberPassword" name="memberPassword" aria-describedby="pwdHelp" required>
                <small id="pwdHelp" class="form-text text-muted">비밀번호는 필수 입력 요소입니다.</small>
              </div>
              <div class="form-group">
                <label for="password2">
                  <i class="material-icons align-middle">check</i>
                  <span class="align-middle">비밀번호 재확인</span>
                </label>
                <input type="password" class="form-control" id="password2" required>
                <small id="pwd2Help" class="form-text text-muted">비밀번호 확인은 필수 입력 요소입니다.</small>
              </div>

              <div class="form-group">
               	<div class = "row">
               	     <div class="col-sm-6">
		                <label for="memberName">
		                  <i class="material-icons align-middle">person</i>
		                  <span class="align-middle">이름</span>
		                </label>
		                <input type="text" class="form-control" id="memberName" name="memberName" required/>
	                </div>
               	    <div class="col-sm-6">
		                <label for="memberNickName">
		                  <i class="material-icons align-middle">person</i>
		                  <span class="align-middle">닉네임</span>
		                </label>
		                <input type="text" class="form-control" id="memberNickName" name="memberNickName" required/>
	                </div>
                </div>
              </div>

              <div class="form-group">
                <div class = "row">
                    <div class="col-sm-6">
                        <label for="memberBirthday">
                            <i class="material-icons align-middle">event</i>
                            <span class="align-middle">생년월일</span>
                          </label>
                          <input type="date" class="form-control" id="memberBirthday" name="memberBirthday" required>
                    </div>
                    <div class="col-sm-6">
                        <label for="memberGender">
                            <i class="material-icons align-middle">wc</i>
                            <span class="align-middle">성별 선택</span>
                        </label>
                        <select class="form-control" id="memberGender" name="memberGender" required>
                            <option value="" disabled selected>성별을 선택하세요.</option>
                            <option value="M">남자</option>
                            <option value="F">여자</option>
                            <option value="U">선택 안함</option>
                        </select>
                    </div>
                </div>
              </div>
              
              <div class="form-group">
                <label for="memberEmail">
                  <i class="material-icons align-middle">phone</i>
                  <span class="align-middle">핸드폰 번호</span>
                </label>
                <input type="number" class="form-control" id="memberPhoneNumber" name="memberPhoneNumber" required>
              </div>
              
              <div class="form-group">
                <label for="memberEmail">
                  <i class="material-icons align-middle">mail</i>
                  <span class="align-middle">이메일 주소</span>
                </label>
                <input type="email" class="form-control" id="memberEmail" name="memberEmail" required>
              </div>

              <div class="form-group">

	              <i class="material-icons align-middle">pets</i>
	              <span class="align-middle">반려동물 유무</span>
                    <div class="text-center">
                        <div style="padding-top: 15px;"></div>
                        <div class="custom-control custom-radio custom-control-inline">
                         <input type="radio" id="petsYes" name="memberPetYN" class="custom-control-input" value="Y" onclick="clickPetsYesOrNo()" checked >
                         <label class="custom-control-label" for="petsYes">반려동물 있음</label>
                        </div>
                       <div class="custom-control custom-radio custom-control-inline">
                         <input type="radio" id="petsNo" name="memberPetYN" class="custom-control-input" value="N" onclick="clickPetsYesOrNo()">
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
                        <input type="text" class="form-control" id="petName" name="petName" placeholder="반려동물 이름을 입력해주세요." required>
                    </div>
			  	    <div class="col-sm-6" >
                        <label for="petRegisterNumber">
                            <i class="material-icons align-middle">pets</i>
                            <span class="align-middle">반려동물 등록번호</span>
                        </label>
                        <input type="number" class="form-control" id="petRegisterNumber" name="petRegisterNumber" placeholder="'-' 를 빼고 입력해주세요." required>
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
                            <option value="D">강아지</option>
                            <option value="C">고양이</option>
                            <option value="O">기타</option>
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
                        <input type="date" class="form-control" id="petBirthday" name="petBirthday" required>
                    </div>
                   	<div class="col-sm-6">
                   	    <label for="petGender">
                            <i class="material-icons align-middle">pets</i>
                            <span class="align-middle">반려동물 성별</span>
                        </label>
                     	<select class="form-control" id="petGender" name="petGender" required>
                            <option value="" disabled selected>반려동물의 성별을 선택하세요.</option>
                            <option value="M">남자</option>
                            <option value="F">여자</option>
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
			             	<option value="A">화이트</option>
	                      	<option value="B">블랙</option>
	                      	<option value="C">브라운</option>
                        </select>
                   	</div>
                   	  <div class="col-sm-6">
                   	    <label for="petCoatLength">
                            <i class="material-icons align-middle">pets</i>
                            <span class="align-middle">반려동물 털길이</span>
                        </label>
                        <select class="form-control" id="petCoatLength" name="petCoatLength" required>
                        <option value="" disabled selected>반려동물 털길이를 선택하세요.</option>
	  						<option value="S">단모</option>
	                      	<option value="L">장모</option>
	                      	<option value="C">곱슬</option>
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
	  						<option value="S">소형</option>
	                      	<option value="M">중형</option>
	                      	<option value="L">대형</option>
                        </select>
                    </div>
               </div>
			</div>
			
             <div class="text-center">
                <label class="mr-3">이벤트 등 알림 메일 수신동의 : </label>
                <div class="custom-control custom-radio custom-control-inline">
                  <input type="radio" id="customRadioInline1" name="memberNotice" class="custom-control-input" value="Y" checked>
                  <label class="custom-control-label" for="customRadioInline1">동의함</label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                  <input type="radio" id="customRadioInline2" name="memberNotice" class="custom-control-input" value="N">
                  <label class="custom-control-label" for="customRadioInline2">동의 안함</label>
                </div>
              </div>
            
            <div class="my-3 text-center">
                
                <c:choose>
                	<%-- 내정보 보기 일 경우 --%>
                	<c:when test= "${ not empty sessionScope.memberId}"> 
                		<button type="submit" class="btn text-white ml-3" style="background-color: rgb(251, 215, 71);">저장</button>                		
                	</c:when>
                 	<%--  회원가입 일 경우 --%>
			        <c:otherwise>			        
                		<button type="submit" id="joinBtn" class="btn text-white ml-3" style="background-color:rgb(46, 204, 113);">회원가입</button>
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

	<script src="http://code.jquery.com/jquery-3.3.1.js"></script>
	<script type="text/javascript"/></script>
	

    <%-- JavaScript --%>
    <script src="/resources/js/jquery-3.6.0.js"></script>
    <script src="/resources/js/bootstrap.js"></script>


<script>
	
	let idFlag = false;
	let pwFlag = false;
	
	$('input#memberId').on('focusout', function () {
		
		let id = $(this).val();
		
		console.log('id',id);
		if (id.length == 0) {
			return;
		}
		
		// ajax 함수 호출
		$.ajax({
			url: '/api/selectMemberId/' + id + '.json',
			method: 'GET',
			success: function (data) {
				
				if (data.memberId == null) {
					$('small#idHelp').html('사용가능한 아이디 입니다.')
						.removeClass('text-muted').removeClass('text-danger')
						.addClass('text-success');
					
					idFlag = true;
				} else { // data.count == 1
					$('small#idHelp').html('이미 사용중인 아이디 입니다.')
						.removeClass('text-muted').removeClass('text-success')
						.addClass('text-danger');
				
					idFlag = false;
				}
			},
			error: function (request, status, error) {
				alert('code: ' + request.status + '\n message: ' + request.responseText + '\n error: ' + error);
			}
		});
		
	});
	
	
	
	$('input#password2').on('focusout', function () {
		
		let pw = $('input#memberPassword').val();
		let pw2 = $(this).val();

		console.log('pw',pw);  // object
		console.log('pw2',pw2);  // {}
		
		
		if (pw != pw2) {
			$('small#pwd2Help').html('비밀번호가 일치하지 않습니다.')
			.removeClass('text-muted').removeClass('text-success')
			.addClass('text-danger');
			
			pwFlag = false;
		}else{
			$('small#pwd2Help').html('')
			.removeClass('text-muted').removeClass('text-success')
			.addClass('text-danger');
			
			pwFlag = true;
		}
		
	});
	
	
	$('button#joinBtn').on('click', function () {
		if(!(pwFlag && idFlag)){			
			event.preventDefault();
			alert('다시 한번 확인해주세요.');
		}
		
	}); 
	
	

		
</script>

</body>
</html>