<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
   <jsp:include page="/WEB-INF/views/include/head.jsp" />
</head>
<body>
	

	<jsp:include page="/WEB-INF/views/include/topNavbar.jsp" />

    <!-- middle container -->
    <div class="container mt-4">
      <div class="row">

        <!-- Left Menu -->
        <div class="col-sm-3 Board-font">

          <!-- Vertical Nav -->
          <ul class="nav flex-column nav-pills">
            <li class="nav-item">
              <a class="nav-link active btn-lg text-white text-center Board-font" href="#" style="background-color: rgb(251, 215, 71); color: black;"><h3>반려동물 신고</h3></a>
            </li>
            <li class="nav-item" >
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
          
       		<input type = "hidden" value = "${ sessionScope.memberId }" name = "memberId" />
 		    <input type = "hidden" value = "${sessionScope.memberNic}" name = "memberNickName" />
 		    <input type = "hidden" value = "${reportBoardVO.boardReportType}" name = "boardReportType" />
 		
              <input class="form-control form-control-lg" type="text" name = "boardTitle"  id = "boardTitle"
				value="${reportBoardVO.boardTitle}" disabled />
              <div class="form-group"></div>
              
              <div class = "row">              
              	<h5>작성자&nbsp;${reportBoardVO.memberId}</h5>
              </div>
              <!-- carouselExampleFade  -->

			<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
	  			<div class="carousel-inner" style="background: gray;">
				  <c:choose>
	              	<c:when test="${fn:length(attachList) >  0}" >
	              		<c:forEach var="attach" items="${ attachList }"  varStatus="status">
	              		    <c:set var="fileCallPath" value="${ attach.uploadpath }/s_${ attach.uuid }_${ attach.filename }" />
	               			<c:set var="originPath" value="${ attach.uploadpath }/${ attach.uuid }_${ attach.filename }" />
						    
						    <div class="carousel-item <c:if test = "${status.index eq 0}" >active </c:if> text-center">
	           					<a href="/display?fileName=${ originPath }">
	           						<img class="d-block w-100" src="/display?fileName=${ fileCallPath }" class="img-thumbnail" style="height: 500px;" >
	           					</a>
						    </div>
						</c:forEach>
					</c:when>
					<c:otherwise>
					
					</c:otherwise>
				</c:choose>
			 </div>
			  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="sr-only">Previous</span>
			  </a>
			  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="sr-only">Next</span>
			  </a>
			</div>
            <!-- end of carouselExampleFade  -->
            <table class="table table-bordered Board-font" style="text-align: center;">
              <thead class="thead-light">
                <tr>
                  <th scope="col" class="text-center pb-4">지역</th>
                  <td>
					${reportBoardVO.petVO.address}
                  </td>
                </tr>
                <tr>
                <th scope="col" class="text-center pb-4">날짜</th>
                  <td>
                    <div class="form-row form-control-sm mb-3">
                      <div class="col">
                      ${reportBoardVO.petVO.findPetDate}
                      </div>
                    </div>
                  </td>
                </tr>
                <tr>
                  <th scope="col" class="text-center pb-4">종류</th>
                  <td>
					 ${reportBoardVO.petVO.petKind}
                  </td>
                </tr>
                <tr>
                  <th scope="col" class="text-center pb-4">품종</th>
                  <td>
					 ${reportBoardVO.petVO.petDetailKind}
                  </td>
                </tr>
                <tr>
                  <th scope="col" class="text-center pb-4">성별</th>
                  <td>
						${reportBoardVO.petVO.petGender}
                  </td>
                </tr>
                <tr>
                  <th scope="col" class="text-center pb-4">사이즈</th>
                  <td>
                    	${reportBoardVO.petVO.petSize}
                  </td>
                </tr>
                <tr>
                  <th scope="col" class="text-center pb-4">색상</th>
                  <td>
                    ${reportBoardVO.petVO.petColor}
                  </td>
                </tr>
                <tr>
                  <th scope="col" class="text-center pb-4">털 길이</th>
                  <td>
                     ${reportBoardVO.petVO.petCoatLength}
                  </td>
                </tr>
                <tr>
                  <th scope="col" class="text-center pb-5">특징</th>
                  <td>
                    	${reportBoardVO.petVO.petCharacter}
                  </td>
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

        <br>
        <div class="row">
          <div class = "col-sm-2">
            <button type="button" class="btn btn-secondary btn-sm " onclick="location.href = '/petFindReport/findReportPetList';">
              <i class="material-icons align-middle">list</i>
              <span class="align-middle">글목록</span>
            </button>
          </div>
       	<%-- 로그인 사용자일때 --%>
       	<c:if test="${ not empty sessionScope.memberId }">
         	<%-- 로그인 아이디와 글작성자 아이디가 같을때 --%>
         	<c:if test="${ sessionScope.memberId eq reportBoardVO.memberId }">
          <div class = "col-sm-10 text-right">
            <button type="button" class="btn btn-primary text-white btn-sm" onclick="location.href = '/petFindReport/findReportPetModify?reportId=${reportBoardVO.reportId}';">
              <i class="material-icons align-middle">edit</i>
              <span class="align-middle">글수정</span>
            </button>
            <button type="button" class="btn btn-danger btn-sm ml-3"  onclick="remove(event);">
              <i class="material-icons align-middle">delete</i>
              <span class="align-middle">글삭제</span>
            </button>
          </div>
          </c:if>
        </c:if>
      </div>


          <!-- Comment -->
          <div id="comment" class="border border-secondary mt-2 p-3">
            <i class="material-icons">forum</i> 댓글

            <hr class="featurette-divider">

            <ul class="list-unstyled mt-4" id ="mainUl">


           <c:choose>
	             <c:when test="${fn:length(commentList) >  0}">
	             <c:forEach var="comment" items="${ commentList }">
		             <ul class="list-unstyled mt-4" id="${comment.commentNum}">
		              <li class="media mb-2">
		                <img src="/resources/images/kirby2.jpg" width="50" height="50" class="mr-3 rounded-circle">
		                <div class="media-body">
		                  <div class="row">
		                    <div class="col-md-4">
		                      <h6>${ comment.memberNickName } (${ comment.memberId } )</h6>
		                    </div>
		                    <div class="col-md-8">
		                      <div class="text-right text-secondary">
		                        <time class="comment-date">${comment.commentRegDate}</time>
		                   <c:if test = "${sessionScope.memberId eq comment.memberId}">
		                        | <a  id = "remove" onclick = "removeComment('${comment.commentId}' , '${comment.reportId}')">삭제</a>
		                        | <a id = "modify"
		                        onclick="modifyComment('${comment.memberNickName}' , '${comment.memberId}' , '${comment.commentRegDate}' , '${comment.commentContent}' , '${ comment.commentNum}', '${ comment.commentId}' , '${ comment.reportId}')">수정</a>
		                        | <a type="button" id = "reply" 
		                        onclick="replyComment('${comment.commentId}' , '${comment.reportId}', '${comment.commentNum}')">답글</a>
		                  </c:if>
		                      </div>
		                    </div>
		                  </div>
		                  <p> ${ comment.commentContent}</p>
		                </div>
		              </li>
		            </ul>
		              

		              
	              </c:forEach>
	             </c:when>
             </c:choose>

            </ul>


            <hr class="featurette-divider">


			<c:if test = "${sessionScope.memberId != null}">
	            <!-- write new comment -->
	            <form id="frm">
	            	<input type = "hidden" value = "${sessionScope.memberId }" name = "memberId" />
	 		    	<input type = "hidden" value = "${sessionScope.memberNic}" name = "memberNickName" />
	 		    	<input type = "hidden" value = "${reportBoardVO.reportId}" name = "reportId" />
	 				<input type = "hidden" value = "${reportBoardVO.boardReportType}" name = "boardReportType" />
	              <div class="row my-4">
	                <div class="col-10">
	                  <div class="form-group">
	                    <label for="exampleFormControlTextarea1">새댓글 작성</label>
	                    <textarea class="form-control" id="commentContent" name = "commentContent" rows="3"></textarea>
	                  </div>
	                </div>
	                <div class="col-2 align-self-center">
	                  <button type="submit" id="newCreatBtn" class="btn btn-info btn-sm">작성</button>
	                </div>
	              </div>
	            </form>
            </c:if>
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


   <jsp:include page="/WEB-INF/views/include/footer.jsp" />

    <!-- JavaScript -->
    <script src="/resources/js/jquery-3.6.0.js"></script>
    <script src="/resources/js/bootstrap.js"></script>
    <script src="/resources/js/jquery.serializeObject.min.js"></script>

   <script>

	$('form#frm').on('submit', function () {
		event.preventDefault();
		
		let obj = $(this).serializeObject();
		console.log(obj);
		console.log(typeof obj); // object
	
		
		let strJson = JSON.stringify(obj);
		console.log(typeof strJson); // string
		console.log(strJson);
		
		
		// ajax 함수 호출
		$.ajax({
			url: '/api/findReportCommentWrite.json',
			method: 'POST',
			data: strJson,
			contentType: 'application/json; charset=UTF-8',
			success: function (data) {
				console.log(typeof data);  // object
				console.log(data);  // {}
				showData(data);

			},
			error: function (request, status, error) {
				alert('code: ' + request.status + '\n message: ' + request.responseText + '\n error: ' + error);
			}
		});
	});
	
	//  댓글삭제 버튼을 클릭했을 때 호출되는 함수
	function removeComment(commentId,reportId) {
			// 이벤트 소스(이벤트가 발생한 오브젝트)의 기본동작을 못하게 만듬
			// 기본동작을 가진 대표적인 두 태그 : a 태그(클릭 못하게), form 태그(submit 못하게) 

			let isRemove = confirm('이 글을 정말 삭제하시겠습니까?');
			if (isRemove == true) {
				// 삭제 후 -> 다시 리로드 showData();

				 // ajax 함수 호출
				$.ajax({
					url: '/api/findReportCommentDelete.json',
					method: 'POST',
					data: JSON.stringify(reportBoardCommentVO  = {
							"commentId" : commentId,
							"reportId"  : reportId,
					}),
					contentType: 'application/json; charset=UTF-8',
					success: function (data) {
						console.log(typeof data);  // object
						console.log('data',data);  // {}
						showData(data);

					},
					error: function (request, status, error) {
						alert('code: ' + request.status + '\n message: ' + request.responseText + '\n error: ' + error);
					}
				});
			}
		}

		
		// 댓글수정 버튼을 클릭했을 때 호출되는 함수
		function modifyComment(nick,id,date,commentContent,index,commentId,reportId) {
			// 이벤트 소스(이벤트가 발생한 오브젝트)의 기본동작을 못하게 만듬
			// 기본동작을 가진 대표적인 두 태그 : a 태그(클릭 못하게), form 태그(submit 못하게) 

				var str = "";
					
				str += `
							<li class="media mb-2">
			                <img src="/resources/images/kirby1.jpg" width="50" height="50" class="mr-3 rounded-circle">
			                <div class="media-body" >
			                  <div class="row">
			                    <div class="col-md-4">
			                      <h6>\${nick} (\${id})</h6>
			                    </div>
			                    <div class="col-md-8">
			                      <div class="text-right text-secondary">
			                        <time class="comment-date">\${date}</time>
			                        | <a  id = "remove" onclick = "removeComment('\${ commentId}' , '\${reportId}'  )">삭제</a>
			                        | <a id = "save"
			                        	 onclick="saveComment('\${commentId}' , '\${reportId}' , '\${index}')">저장</a>
			                        | <a type="button" id = "reply">답글</a>
			                      </div>
			                    </div>
			                  </div>
			             		 <textarea class="form-control"  id="textarea\${index}" name = "\${index}" rows="3">\${commentContent}</textarea>
			                </div>
			              </li>
					`;

					
					$('ul#'+index).html(str); 
		}
		
		// 댓글답글 버튼을 클릭했을 때 호출되는 함수
		function replyComment(commentId,reportId,index) {
			// 이벤트 소스(이벤트가 발생한 오브젝트)의 기본동작을 못하게 만듬
			// 기본동작을 가진 대표적인 두 태그 : a 태그(클릭 못하게), form 태그(submit 못하게) 
			
			console.log('11111111');
			event.preventDefault();
			
			// 댓글 답글 폼 나오게
			console.log(commentId);
			
			var str = "";
				
			str += `
	             <!-- write reply comment -->
	              <li class="media mb-2" style="margin-left: 80px;">
	                <i class="material-icons">subdirectory_arrow_right</i>
	                <div class="media-body">
	                  <form id="frm'${index}'">
	                  	<input type = "hidden" value = "${sessionScope.memberId }" name = "memberId" />
	  	 		    	<input type = "hidden" value = "${sessionScope.memberNic}" name = "memberNickName" />
	  	 		    	<input type = "hidden" value = "${reportBoardVO.reportId}" name = "reportId" />
	  	 				<input type = "hidden" value = "${reportBoardVO.boardReportType}" name = "boardReportType" />
	                    <div class="row">
	                      <div class="col-10">
	                        <div class="form-group">
	                          <label>답댓글 작성</label>
	  	                    <textarea class="form-control" id="commentContent" name = "commentContent" rows="3"></textarea>
	                        </div>
	                      </div>
	                      <div class="col-2 align-self-center">
	                        <button type="submit"   class="btn btn-info btn-sm">작성</button>
	                      </div>
	                    </div>
	                  </form>
	                </div>
	              </li>
				`;


				
				$('ul#'+index).append(str); 
				
		}
		
		function saveComment(commentId,reportId,index) {
			event.preventDefault();
			console.log('index : ',index);
			const commentContent = $('#textarea'+index).val();

			
			var reportBoardCommentVO = {
					"commentContent" : commentContent,
					"commentId": commentId,
					"reportId" : reportId,
			};
			
			console.log('reportBoardCommentVO',reportBoardCommentVO);
			 // ajax 함수 호출
			$.ajax({
				url: '/api/findReportCommentModify.json',
				method: 'POST',
				data: JSON.stringify(reportBoardCommentVO),
				contentType: 'application/json; charset=UTF-8',
				success: function (data) {
					console.log(typeof data);  // object
					console.log('data',data);  // {}
					showData(data);

				},
				error: function (request, status, error) {
					alert('code: ' + request.status + '\n message: ' + request.responseText + '\n error: ' + error);
				}
			});
		}

	
		// 글삭제 버튼을 클릭했을 때 호출되는 함수
		function remove() {
			// 이벤트 소스(이벤트가 발생한 오브젝트)의 기본동작을 못하게 만듬
			// 기본동작을 가진 대표적인 두 태그 : a 태그(클릭 못하게), form 태그(submit 못하게) 
			event.preventDefault();
	
			
			let isRemove = confirm('이 글을 정말 삭제하시겠습니까?');
			if (isRemove == true) {
				location.href = '/petFindReport/findReportPetDelete?reportId=${reportBoardVO.reportId}&boardReportType=${reportBoardVO.boardReportType}';
			}
		}
		


		function showData(array) {
			
			let str = '';

			
			if (array != null && array.length > 0) {
				for (let i = 0; i< array.length; i++) {
			
					console.log(array[i].memberId);
					
					str += `
						<ul class="list-unstyled mt-4" id="\${array[i].commentNum}">
						<li class="media mb-2">
		                <img src="/resources/images/kirby1.jpg" width="50" height="50" class="mr-3 rounded-circle">
		                <div class="media-body" >
		                  <div class="row">
		                    <div class="col-md-4">
		                      <h6>\${array[i].memberNickName} (\${array[i].memberId})</h6>
		                    </div>
		                    <div class="col-md-8">
		                      <div class="text-right text-secondary">
		                      mem:  ${array[i].memberId}
		                      se : ${sessionScope.memberId}
		                        <time class="comment-date">\${array[i].commentRegDate}</time>
		                        <c:if test = "${sessionScope.memberId eq memberId}">
		                        | <a  id = "remove" onclick = "removeComment('\${ array[i].commentId}' , '\${ array[i].reportId}' )">삭제</a>
		                        | <a id = "modify"
		                        onclick="modifyComment('\${array[i].memberNickName}' , '\${array[i].memberId}' , '\${array[i].commentRegDate}' , '\${array[i].commentContent}' , '\${ array[i].commentNum}', '\${ array[i].commentId}' , '\${ array[i].reportId}')">수정</a>
		                        | <a type="button" id = "reply" onclick="replyComment('\${ array[i].commentId}' , '\${ array[i].reportId}', '\${ array[i].commentNum}')">답글</a>
		                       </c:if>
		                        </div>
		                    </div>
		                  </div>
		             	  <input type = "hidden" value = "${ array[i].commentNum }" name = "commentNum" />
		                  <p>\${array[i].commentContent}</p>
		                </div>
		              </li>
		              </ul>
					`;

				} // for
				


				
			} else { // array == null || array.length == 0
				str = `

				`;
			}

			$('div#comment > ul').html(str);
			

		} // showData
	
  </script>
  

    
</body>
</html>