<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
   <jsp:include page="/WEB-INF/views/include/head.jsp" />
   <style>
     	input[type="text"]:disabled { background: white; }
   </style>
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
              <a class="nav-link active btn-lg text-white text-center Board-font" href="#" style="background-color: rgb(46, 204, 113); color: black;"> <h3>반려동물 신고</h3></a>
            </li>
            <li class="nav-item" >
              <a class="nav-link" href="/petLostReport/lostReportPetWrite" style="color: black;">반려동물 분실 신고</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/petFindReport/findReportPetWrite" style="color: black;">유기동물 발견 신고</a>
            </li>
          </ul>
          <!-- end of Vertical Nav -->
        </div>
        <!-- end of Left Menu -->


      <!-- Right area -->
      <div class="col-sm-9">
          
       	<input type = "hidden" value = "${sessionScope.memberId}" name = "memberId" />
 		<input type = "hidden" value = "${sessionScope.memberNic}" name = "memberNickName" />
 		<input type = "hidden" value = "${reportBoardVO.boardReportType}" name = "boardReportType" />
 		
 		<!-- Contents area -->
        <div class="border border-success p-4 rounded">
        
	      <div class="form-group">
               	<div class = "row">
               	     <div class="col-sm-9 ">
		                <input type="text" class="form-control form-control-lg"  name = "boardTitle"  id = "boardTitle" value="${reportBoardVO.boardTitle}" disabled />
	                </div>
               	    <div class="col-sm-3 ">
		                <input type="text" class="form-control form-control-lg " style = "text-align:center;" id="memberId" name="memberId" value="${reportBoardVO.memberId}" disabled/>
	                </div>
                </div>
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
                      ${reportBoardVO.petVO.findPetDate}
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
		  <%-- 로그인 사용자일때 --%>
			<c:if test="${not empty sessionScope.memberId }">
				<button type="button" id="goodBtn" class="btn btn-primary btn-lg" onclick="check('${ reportBoardVO.reportId }', '${sessionScope.memberId }', 'Y')">
					<i class="material-icons align-middle" id="good">thumb_up_off_alt</i>
					<span class="align-middle" id="good" >추천</span>
				</button>
				<button type="button" id="notGoodBtn"  class="btn btn-secondary btn-lg ml-3" onclick="check('${  reportBoardVO.reportId }', '${sessionScope.memberId }', 'N')">
						<i class="material-icons align-middle" id="notGood">thumb_down_off_alt</i>
						<span class="align-middle" id="notGood" >비추천</span>
				</button>
				<button type="button" class="btn btn-danger btn-lg ml-3" onclick="waring('${ reportBoardVO.reportId }', '${sessionScope.memberId }','Y')">
						<i class="material-icons align-middle" id="waring">error_outline</i> 
						<span class="align-middle" id="waring">신고</span>
				</button>
            </c:if>
          </div>
          <br><br><br>
			</div>
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

						<c:if test = "${comment.commentSeq != 0 }">
							<li class="media mb-2" style="margin-left: 80px;">
							<i class="material-icons">subdirectory_arrow_right</i>
						</c:if>
						<c:if test = "${comment.commentSeq == 0 }">
			              <li class="media mb-2">
						</c:if>
							
		              <c:if test = "${ comment.memberProfileVO.uploadpath != null}">
		                 <c:set var="fileCallPath" value="${ comment.memberProfileVO.uploadpath }/s_${ comment.memberProfileVO.uuid }_${ comment.memberProfileVO.filename }" />
                         <img  src="/display?fileName=${ fileCallPath }" width="50" height="50" class="mr-3 rounded-circle">
		              </c:if>
		              <c:if test = "${ comment.memberProfileVO.uploadpath == null}">
						<img src="/resources/images/default.png" width="50" height="50" class="mr-3 rounded-circle">
		              </c:if>
		              
		                <div class="media-body">
		                  <div class="row">
		                    <div class="col-md-4">
		                      <h6>${ comment.memberNickName } (${ comment.memberId } )</h6>
		                    </div>
		                    <div class="col-md-8">
		                      <div class="text-right text-secondary">
		                        <time class="comment-date">${comment.commentDateString}</time>
		                   <c:if test = "${sessionScope.memberId eq comment.memberId}">
		                        | <a  id = "remove" onclick = "removeComment('${comment.commentId}' , '${comment.reportId}')">삭제</a>
		                        | <a id = "modify"
		                        onclick="modifyComment('${comment.memberNickName}' , '${comment.memberId}'
		                         , '${comment.commentDateString}' , '${comment.commentContent}' , '${ comment.commentNum}'
		                         , '${ comment.commentId}' , '${ comment.reportId}'  , '${ comment.memberProfileVO.uploadpath}')">수정</a>
		                  </c:if>
		                  
		                  <c:if test = "${comment.commentSeq == 0 }">
		                        | <a type="button" id = "reply" 
		                        onclick="replyComment('${comment.commentId}' , '${comment.reportId}', '${comment.commentNum}' , '${comment.commentRef}')">답글</a>
		                  </c:if>    
		                      </div>
		                    </div>
		                  </div>
		                  <p> ${ comment.commentContent}</p>
		                </div>
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


	<!-- include -->
    <jsp:include page="/WEB-INF/views/include/footer.jsp" />
    

    <!-- JavaScript -->
    <script src="/resources/js/jquery-3.6.0.js"></script>
    <script src="/resources/js/bootstrap.js"></script>
    <script src="/resources/js/jquery.serializeObject.min.js"></script>

   <script>
   
	// 화면이 시작할떼 돌아감 -> selectMemberGoodOrWarn 
	$(document).ready(function(){
		console.log('1111');
		selectMemberGoodOrWarn('${ reportBoardVO.reportId }','${sessionScope.memberId }')
		connectWs();
	});
   
	function connectWs(){
 		
 		sock = new SockJS("<c:url value="/echo"/>");
 		
 		
	  	sock.onopen = function() {
	          console.log('info: connection opened.');
	
	          
	      	$('form#frm').on('submit', function () {
		  	  	 
		 		event.preventDefault();

		  	  	let memberId = '${reportBoardVO.memberId}';
		  	  	
				let obj = $(this).serializeObject();
				let strJson = JSON.stringify(obj);
				document.getElementById("commentContent").value='';

				console.log('obj',obj);
		  	  	sock.send('reply,' + obj.memberId + ',' + memberId + ',' + memberId + ',reply');
				// ajax 함수 호출
				$.ajax({
					url: '/api/findReportCommentWrite.json',
					method: 'POST',
					data: strJson,
					contentType: 'application/json; charset=UTF-8',
					success: function (data) {
						showData(data);
			
					},
					error: function (request, status, error) {
						alert('code: ' + request.status + '\n message: ' + request.responseText + '\n error: ' + error);
					}
				});
	      	});
	          
		   sock.onmessage = function(event) {
			   	console.log("ReceivMessage : " + event.data + "\n");
			 	var data = event.data;
		
		   };
		
		   sock.onclose = function() {
		     	console.log('connect close');
		     	/* setTimeout(function(){conntectWs();} , 1000); */
		   };
		
		   sock.onerror = function (err) {
			   console.log('Errors : ' , err);
		   };
	  	}
	}
	
/*  	$('form#frm').on('submit', function () {
		event.preventDefault();
		
		let obj = $(this).serializeObject();
		let strJson = JSON.stringify(obj);
		document.getElementById("commentContent").value='';
	
		// ajax 함수 호출
		$.ajax({
			url: '/api/findReportCommentWrite.json',
			method: 'POST',
			data: strJson,
			contentType: 'application/json; charset=UTF-8',
			success: function (data) {
				showData(data);
	
			},
			error: function (request, status, error) {
				alert('code: ' + request.status + '\n message: ' + request.responseText + '\n error: ' + error);
			}
		});
	}); 
 */
	
	
	function allSelect(reportId) {		
		
		// ajax 함수 호출
		$.ajax({
			url: '/api/findReportCommentList/' + reportId + '.json',
			method: 'GET',
			data: reportId,
			contentType: 'application/json; charset=UTF-8',
			success: function (data) {
				showData(data);

			},
			error: function (request, status, error) {
				alert('code: ' + request.status + '\n message: ' + request.responseText + '\n error: ' + error);
			}
		});
	}
	
	//  댓글삭제 버튼을 클릭했을 때 호출되는 함수
	function removeComment(commentId,reportId) {
			var reportBoardCommentVO  = {
							"commentId" : commentId,
							"reportId"  : reportId,
				};
			
			let isRemove = confirm('이 글을 정말 삭제하시겠습니까?');
			
			if (isRemove == true) {
				// 삭제 후 -> 다시 리로드 showData();

				 // ajax 함수 호출
				$.ajax({
					url: '/api/findReportCommentDelete.json',
					method: 'DELETE',
					data: JSON.stringify(reportBoardCommentVO),
					contentType: 'application/json; charset=UTF-8',
					success: function (data) {
						showData(data);

					},
					error: function (request, status, error) {
						alert('code: ' + request.status + '\n message: ' + request.responseText + '\n error: ' + error);
					}
				});
			}
	}
	
	

		
		// 댓글수정 버튼을 클릭했을 때 호출되는 함수
		function modifyComment(nick,id,date,commentContent,index,commentId,reportId,profile) {
			
				var str = "";
				let memebrId = '${sessionScope.memberId}';
				
				
				str += '<li class="media mb-2">';
	            
				if(profile != null){					
					<c:set var="fileCallPath" value="${ profileVO.uploadpath }/s_${ profileVO.uuid }_${ profileVO.filename }" />
		          	str += '<img  src="/display?fileName=${ fileCallPath }" width="50" height="50" class="mr-3 rounded-circle">';
				}else{
					 str += '<img src="/resources/images/default.png" width="50" height="50" class="mr-3 rounded-circle">';
				}
				
				str += '<div class="media-body" >';
				str += '<div class="row">';
				str += '<div class="col-md-4">';
				str += '<h6>' + nick + '(' + id + ') </h6>';
				str += '</div>';
				str += '<div class="col-md-8">';
				str += '<div class="text-right text-secondary">';
				str += '<time class="comment-date">' + date +'</time>';
				
				if(id == memebrId){
					str += ' | <a id = "save" onclick="saveComment(\'' + commentId + '\'' + ',\'' +  reportId + '\'' + ',\'' + index + '\')">저장</a>';
					str += ' | <a  id = "cancle" onclick = "allSelect(\'' + reportId + '\')">취소</a>';
				}
				str += '</div>';
				str += '</div>';
				str += '</div>';
				str += ' <textarea class="form-control"  id="textarea' + index + '" name = "' + index + '" rows="3">' + commentContent + '</textarea>';
				str += '</div>';
				str += '</li>';

				$('ul#'+index).html(str); 
		}
		
		
		
		// 댓글답글 버튼을 클릭했을 때 호출되는 함수
		function replyComment(commentId,reportId,index,commentRef) {
		
			event.preventDefault();
			
			// 댓글 답글 폼 나오게
			
			var str = "";
				
			str += '<li class="media mb-2" style="margin-left: 80px;">';
			str += '<i class="material-icons">subdirectory_arrow_right</i>';
			str += '<div class="media-body">';
			str += '<form id="frm' + index + '">';
			str += '<input type = "hidden" value = "${sessionScope.memberId }" name = "memberId" />';
			
			str += '<input type = "hidden" value = "${sessionScope.memberNic}" name = "memberNickName" />';
			str += '<input type = "hidden" value = "${reportBoardVO.reportId}" name = "reportId" />';
			str += '<input type = "hidden" value = "${reportBoardVO.boardReportType}" name = "boardReportType" />';
			str += '<input type = "hidden" value = "' + commentRef + '" name = "commentRef" />';
			str += '<div class="row">';
			str += '<div class="col-10">';
			str += '<div class="form-group">';
			
			str += '<label>답댓글 작성</label>';
			str += '<textarea class="form-control" id="commentContent" name = "commentContent" rows="3"></textarea>';
			str += '</div>';
			str += '</div>';
			str += '<div class="col-2 align-self-center">';
			str += '<button type="submit" onclick = "replySave('+ index + ')" class="btn btn-info btn-sm">작성</button>';
			str += '</div></div></form></div></li>';

				
			$('ul#'+index).append(str); 
				
		}

		function replySave(index){
			
		  $('#frm'+index).on('submit', function () {
				event.preventDefault();
				
				let obj = $(this).serializeObject();
				let strJson = JSON.stringify(obj);
				console.log('여기',strJson);
				
				
				// ajax 함수 호출
				$.ajax({
					url: '/api/findReportCommentReply.json',
					method: 'POST',
					data: strJson,
					contentType: 'application/json; charset=UTF-8',
					success: function (data) {
						showData(data);

					},
					error: function (request, status, error) {
						alert('code: ' + request.status + '\n message: ' + request.responseText + '\n error: ' + error);
					}
				});
			});
		}
		
		function saveComment(commentId,reportId,index) {
			event.preventDefault();
			const commentContent = $('#textarea'+index).val();

			var reportBoardCommentVO = {
					"commentContent" : commentContent,
					"commentId": commentId,
					"reportId" : reportId,
			};
			
			 // ajax 함수 호출
			$.ajax({
				url: '/api/findReportCommentModify.json',
				method: 'PUT',
				data: JSON.stringify(reportBoardCommentVO),
				contentType: 'application/json; charset=UTF-8',
				success: function (data) {
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
			let memebrId = '${sessionScope.memberId}';
			
			if (array != null && array.length > 0) {
				for (let i = 0; i< array.length; i++) {
			        
					str += '<ul class="list-unstyled mt-4"id="' + array[i].commentNum + '">';
					
					// 답글일 경우
					if(array[i].commentSeq != 0){
						str += '<li class="media mb-2" style="margin-left: 80px;">';
						str += '<i class="material-icons">subdirectory_arrow_right</i>'
					}else{
						str += '<li class="media mb-2">';
					}

					if(array[i].memberProfileVO.uploadpath != null){						
						str += '<c:set var="fileCallPath" value="' + array[i].memberProfileVO.uploadpath + '/s_' +array[i].memberProfileVO.uuid + '_' + array[i].memberProfileVO.filename + '" />';
					    str += '<img  src="/display?fileName=${ fileCallPath }" width="50" height="50" class="mr-3 rounded-circle">';
					}else{
						 str += '<img src="/resources/images/default.png" width="50" height="50" class="mr-3 rounded-circle">';
					}

					str += '<div class="media-body" >';
					str += '<div class="row">';
					str += '<div class="col-md-4">';
					str += '<h6>' + array[i].memberNickName + '(' + array[i].memberId + ') </h6>';
					str += '</div>';
					str += '<div class="col-md-8">';
					str += '<div class="text-right text-secondary">';
					str += '<time class="comment-date">' + array[i].commentDateString +'</time>';
					
		           	// 해당 세션id만 수정,삭제 가능
					if(array[i].memberId == memebrId){
						str += ' | <a  id = "remove" onclick = "removeComment(\'' +  array[i].commentId + '\'' + ',\'' +  array[i].reportId + '\')">삭제</a>';
						str += ' | <a id = "modify"'; 
						str += ' onclick="modifyComment(\'' + array[i].memberNickName +  '\'' + ',\''+ array[i].memberId +  '\'' + ',\'' + array[i].commentDateString +  '\'' + ',\'' + array[i].commentContent + '\'' + ',\'' 
								+ array[i].commentNum +  '\'' + ',\'' + array[i].commentId +  '\'' + ',\'' + array[i].reportId + '\'' + ',\''+ array[i].memberProfileVO.uploadpath + '\')">수정</a>';
					}
		            
					// 댓글이 시퀀스가 0 일때만 답글 가능
					if(array[i].commentSeq == 0){
						str += ' | <a type="button" id = "reply" onclick="replyComment(\'' +  array[i].commentId +  '\'' + ',\'' + array[i].reportId +  '\'' + ',\''+ array[i].commentNum + '\'' + ',\''+ array[i].commentRef + '\')">답글</a>';
					}
					str += '</div>';
					str += '</div>';
					str += '</div>';
					str += '<input type = "hidden" value = "' + array[i].commentNum + '" name = "commentNum" />';
					str += '<p> ' + array[i].commentContent + '</p>';
					str += '</div>';
					str += '</li>';
					str += '</ul>';
					
				} // for

			} else { // array == null || array.length == 0
				str = `

				`;
			}

			$('div#comment > ul').html(str);
			

		} // showData
		
		
	  	// ======================== 추천 버튼 클릭 시 호출되는 함수 ========================
		// ajax 함수 호출	
		function check(reportId, memberId, goodOrNot) {

			var restAdopCommVO = {
				"boardId" : reportId,
				"memberId" : memberId,
				"goodOrNot" : goodOrNot,
			};

			console.log('restAdopCommVO', restAdopCommVO);

			$.ajax({
						url : '/api/adopCommBoardCheck.json',
						method : 'PUT',
						data : JSON.stringify(restAdopCommVO),
						contentType : 'application/json; charset=UTF-8',
						success : function(data) {
						console.log('data', data);
							// 누른 값 : Y , 데이터에서 나온 값 : 1
							if (restAdopCommVO.goodOrNot == 'Y'&& data.goodOrNot == '1') {
								$("#notGoodBtn").attr("disabled", true);
								$('#good').replaceWith('<i class="material-icons align-middle" id = "good">thumb_up_alt</i>');

							} else if (restAdopCommVO.goodOrNot == 'Y'&& data.goodOrNot == '0') {
								$("#notGoodBtn").attr("disabled", false);
								$('#good').replaceWith('<i class="material-icons align-middle" id = "good">thumb_up_off_alt</i>');

							} else if (restAdopCommVO.goodOrNot == 'N'&& data.goodOrNot == '1') {
								$("#goodBtn").attr("disabled", true);
								$('#notGood').replaceWith('<i class="material-icons align-middle" id = "notGood">thumb_down_alt</i>');

							}  else if (restAdopCommVO.goodOrNot == 'N'
								&& data.goodOrNot == '0') {
								$("#goodBtn").attr("disabled", false);
								$('#notGood').replaceWith('<i class="material-icons align-middle" id = "notGood">thumb_down_off_alt</i>');

							}

						},
						error : function(request, status, error) {
							alert('code: ' + request.status + '\n message: '
									+ request.responseText + '\n error: '
									+ error);
						}
					});
		}
		
		// ======================== 신고 버튼을 클릭했을 때 호출되는 함수 ========================

			function waring(reportId, memberId,waring) {
				var restAdopCommVO = {
					"boardId" : reportId,
					"memberId" : memberId,
					"waring" : waring,
					"boardType"	: 'F',
				};
				$.ajax({
					url : '/api/adopTempBoardWaring.json',
					method : 'PUT',
				data : JSON.stringify(restAdopCommVO),
				contentType : 'application/json; charset=UTF-8',
					success : function(data) {
			

						
						console.log('dta',data.str);
						
						if(data.str != null){
							  alert(data.str);
							 location.href  ='/petFindReport/findReportPetList';
						}
						
						if (data.waringCount == '1') {
							$('#waring').replaceWith('<i class="material-icons align-middle" id="waring">error</i>');
						}else{
							$('#waring').replaceWith('<i class="material-icons align-middle" id="waring">error_outline</i>');
						}
					},

					error : function(request, status, error) {
						alert('code: ' + request.status + '\n message: '
								+ request.responseText + '\n error: ' + error);
					}
				});
			}
		
		
		// ======================== 해당 유저의 추천,비추천,신고 체크 ========================

			function selectMemberGoodOrWarn(reportId, memberId) {
				var restAdopCommVO = {
						"boardId" : reportId,
						"memberId" : memberId,
					};
				$.ajax({
					url: '/api/boardWaringAndGood/' + reportId + '/'+ memberId + '.json',
					method: 'GET',
				contentType : 'application/json; charset=UTF-8',
					success : function(data) {
						
						console.log('data',data);
						if (data[0].waringCount == '1') {
						$('i#waring').replaceWith('<i class="material-icons align-middle" id="waring">error</i>');
					}
						
						if(data[0].good == '1'){
							$("#notGoodBtn").attr("disabled", true);
							$('i#good').replaceWith('<i class="material-icons align-middle" id = "good">thumb_up_alt</i>');
						}
						
						if(data[0].notGood == '1'){
							$("#goodBtn").attr("disabled", true);
							$('i#notGood').replaceWith('<i class="material-icons align-middle" id = "notGood">thumb_down_alt</i>');
						}
						$('span#good').replaceWith('<span class="align-middle" id="good">추천(' + data[1].good + ')</span>');
						$('span#notGood').replaceWith('<span class="align-middle" id="notGood">비추천(' + data[1].notGood  + ')</span>');
					},

					error : function(request, status, error) {
						alert('code: ' + request.status + '\n message: '
								+ request.responseText + '\n error: ' + error);
					}
				});
			}
		
	
  </script>
  

    
</body>
</html>