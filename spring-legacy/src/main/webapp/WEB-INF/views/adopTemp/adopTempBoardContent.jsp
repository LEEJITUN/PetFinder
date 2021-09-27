<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%-- include head.jsp --%>
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
					<li class="nav-item"><a
						class="nav-link active btn-lg text-white text-center Board-font"
						href="#"
						style="background-color: rgb(41, 128, 185); color: black;"><h3>입양
								| 임보</h3></a></li>
				</ul>
				<!-- end of Vertical Nav -->
			</div>
			<!-- end of Left Menu -->


			<!-- Right area -->
			<div class="col-sm-9">

				<!-- Contents area -->
				<div id="comment" class="border border-primary p-3">
					<br>
					<h3 class="text-center">제목 : ${ adopTempContent.boardTitle }</h3>

					<!-- 글 상세보기 영역 -->
					<table class="table">
						<thead>
							<tr>
								<th scope="row" class="text-center">작성자</th>
								<td>${ adopTempContent.memberNickName }</td>
								<th scope="row" class="text-center">작성일</th>
								<td><fmt:formatDate value="${ adopTempContent.boardRegDate }" pattern="yyyy.MM.dd" /></td>
								<th scope="row" class="text-center">조회수</th>
								<td>${ adopTempContent.boardReadCount }</td>
							</tr>
						</thead>
						
						<tbody>
						<tr style="height: 300px">
							<th scope="row" class="text-center">내용</th>
							<td colspan="5"><pre>${ adopTempContent.boardContent}</pre>

							</td>
						</tr>
						<tr>
							<th scope="row" class="text-center"></th>
							<td colspan="5"></td>
						</tr>
						</tbody>
					</table>

					<br>
					<div class="text-center">
						<%-- 로그인 사용자일때 --%>
						<c:if test="${not empty sessionScope.memberId }">
							<button type="button" id="goodBtn" class="btn btn-primary btn-lg"
								onclick="check('${ adopTempContent.boardId}', '${sessionScope.memberId }', 'Y')">
								<i class="material-icons align-middle" id="good">thumb_up_off_alt</i>
								<span class="align-middle">추천</span>
							</button>

							<button type="button" id="notGoodBtn"
								class="btn btn-secondary btn-lg ml-3"
								onclick="check('${ adopTempContent.boardId}', '${sessionScope.memberId }', 'N')">
								<i class="material-icons align-middle" id="notGood">thumb_down_off_alt</i>
								<span class="align-middle">비추천</span>
							</button>
							<button type="button" class="btn btn-danger btn-lg ml-3"
								onclick="waring('${ adopTempContent.boardId}', '${sessionScope.memberId }','Y')">
								<i class="material-icons align-middle" id="waring">error_outline</i> <span
									class="align-middle">신고</span>
							</button>
						</c:if>
					</div>
					<br> <br> <br>
				</div>
				<br>
				<div class="row">
					<div class="col-sm-2">
						<button type="button" class="btn btn-secondary btn-sm "
							onclick="location.href = '/adopTemp/adopTempBoardList?pageNum=${ pageNum }';">
							<i class="material-icons align-middle">list</i> <span
								class="align-middle">글목록</span>
						</button>
					</div>

					<div class="col-sm-10 text-right">
						<%-- 로그인 사용자일때 --%>
						<c:if test="${not empty sessionScope.memberId }">
							<%-- 로그인 사용자 닉네임과 글작성자 닉네임이  같을때 --%>
							<c:if
								test="${ sessionScope.memberId eq adopTempContent.memberId }">

								<button type="button" class="btn btn-primary text-white btn-sm"
									onclick="location.href = '/adopTemp/adopTempBoardModify?boardId=${ adopTempContent.boardId }&pageNum=${ pageNum }';">
									<i class="material-icons align-middle">edit</i> <span
										class="align-middle">글수정</span>
								</button>
								<button type="button" class="btn btn-danger btn-sm ml-3"
									onclick="remove(event);">
									<i class="material-icons align-middle">delete</i> <span
										class="align-middle">글삭제</span>
								</button>
							</c:if>
						</c:if>
					</div>
				</div>


				<!-- Comment -->
				<div id="comment" class="border border-secondary mt-2 p-3">
					<i class="material-icons">forum</i> 댓글

					<hr class="featurette-divider">

					<ul class="list-unstyled mt-4" id="mainUl">


						<c:choose>
							<c:when test="${fn:length(commentList) >  0}">
								<c:forEach var="comment" items="${ commentList }">
									<ul class="list-unstyled mt-4" id="${comment.boardNum}">

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
														<h6>${ comment.memberNickName }(${ comment.memberId }
															)</h6>
													</div>
													<div class="col-md-8">
														<div class="text-right text-secondary">
															<time class="comment-date">${comment.commentDateString}</time>
															<c:if test="${sessionScope.memberId eq comment.memberId}">
		                        | <a id="remove" onclick="removeComment('${comment.commentId}' , '${comment.boardId}')">삭제</a>
		                        | <a id="modify" onclick="modifyComment('${comment.memberNickName}' , '${comment.memberId}'
		                         , '${comment.commentDateString}' , '${comment.commentContent}' 
		                         , '${ comment.boardNum}', '${ comment.commentId}' , '${ comment.boardId}', '${ comment.memberProfileVO.uploadpath}')">수정</a>
															</c:if>
								| <a type="button" id="reply" onclick="replyComment('${comment.commentId}' , '${comment.boardId}', '${comment.boardNum}' , '${comment.commentRef}')">답글</a>
														</div>
													</div>
												</div>
												<p>${ comment.commentContent}</p>
											</div></li>
									</ul>
								</c:forEach>
							</c:when>
						</c:choose>

					</ul>


					<hr class="featurette-divider">


					<c:if test="${sessionScope.memberId != null}">
						<!-- write new comment -->
						<form id="frm">
							<input type="hidden" value="${sessionScope.memberId }" name="memberId" /> 
							<input type="hidden" value="${sessionScope.memberNic}" name="memberNickName" /> 
							<input type="hidden" value="${adopTempContent.boardId}" name="boardId" />

							<div class="row my-4">
								<div class="col-10">
									<div class="form-group">
										<label for="exampleFormControlTextarea1">새댓글 작성</label>
										<textarea class="form-control" id="commentContent"
											name="commentContent" rows="3"></textarea>
									</div>
								</div>
								<div class="col-2 align-self-center">
									<button type="submit" id="newCreatBtn"
										class="btn btn-info btn-sm">작성</button>
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
	<div class="container-fluid">
		<hr style="border: solid 2px lightgray">
		<div class="mx-5">
			<a href="#!" style="color: gray;">&ensp; 개식용 종식 &ensp;</a> | <a
				href="#!" style="color: gray;">&ensp; 케이지 프리 코리아 &ensp;</a> | <a
				href="#!" style="color: gray;">&ensp; 동물보호 관리시스템 &ensp;</a>
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
	<script src="/resources/js/jquery.serializeObject.min.js"></script>
	
	<script>
	
	// 화면이 시작할떼 돌아감 -> selectMemberGoodOrWarn 
	$(document).ready(function(){
		selectMemberGoodOrWarn('${adopTempContent.boardId }','${sessionScope.memberId }')
	});
	
	
	$('form#frm').on('submit', function () {
		event.preventDefault();
		
		let obj = $(this).serializeObject();
		let strJson = JSON.stringify(obj);
		document.getElementById("commentContent").value='';
		
// 		console.log('data ', strJson);
		// ajax 함수 호출
		$.ajax({
			url: '/api/adopCommCommentWrite.json',
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
	
	function allSelect(boardId) {		
		
		// ajax 함수 호출
		$.ajax({
			url: '/api/adopCommCommentList/' + boardId + '.json',
			method: 'GET',
			data: boardId,
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
		function removeComment(commentId,boardId) {
				var restAdopCommCommentVO  = {
								"commentId" : commentId,
								"boardId"  : boardId,
					};
				
				let isRemove = confirm('이 글을 정말 삭제하시겠습니까?');
				
				if (isRemove == true) {
					// 삭제 후 -> 다시 리로드 showData();

					 // ajax 함수 호출
					$.ajax({
						url: '/api/adopCommCommentDelete.json',
						method: 'DELETE',
						data: JSON.stringify(restAdopCommCommentVO),
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
			function modifyComment(nick,id,date,commentContent,index,commentId,boardId,profile) {
 				console.log('id', id);
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
						str += ' | <a id = "save" onclick="saveComment(\'' + commentId + '\'' + ',\'' +  boardId + '\'' + ',\'' + index + '\')">저장</a>';
						str += ' | <a  id = "cancle" onclick = "allSelect(\'' + boardId + '\')">취소</a>';
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
			function replyComment(commentId,boardId,index,commentRef) {
				console.log('boardId', boardId);
 				console.log('index', index);
				event.preventDefault();
				
				// 댓글 답글 폼 나오게
				
				var str = "";
					
				str += '<li class="media mb-2" style="margin-left: 80px;">';
				str += '<i class="material-icons">subdirectory_arrow_right</i>';
				str += '<div class="media-body">';
				str += '<form id="frm' + index + '">';
				str += '<input type = "hidden" value = "${sessionScope.memberId }" name = "memberId" />';
				
				str += '<input type = "hidden" value = "${sessionScope.memberNic}" name = "memberNickName" />';
				str += '<input type = "hidden" value = "${adopTempContent.boardId}" name = "boardId" />';										  
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
						url: '/api/adopCommCommentReply.json',
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
			
			function saveComment(commentId,boardId,index) {
				
				event.preventDefault();
				const commentContent = $('#textarea'+index).val();

				var restAdopCommCommentVO = {
						"commentContent" : commentContent,
						"commentId": commentId,
						"boardId" : boardId,
				};
				
				 // ajax 함수 호출
				$.ajax({
					url: '/api/adopCommCommentModify.json',
					method: 'PUT',
					data: JSON.stringify(restAdopCommCommentVO),
					contentType: 'application/json; charset=UTF-8',
					success: function (data) {
						showData(data);

					},
					error: function (request, status, error) {
						alert('code: ' + request.status + '\n message: ' + request.responseText + '\n error: ' + error);
					}
				});
			}
			
			
			function showData(array) {
		         
		         let str = '';
		         let memebrId = '${sessionScope.memberId}';
		         
		         if (array != null && array.length > 0) {
		            for (let i = 0; i< array.length; i++) {
		               
		               str += '<ul class="list-unstyled mt-4"id="' + array[i].boardNum + '">';
						
		               // 답글일 경우
						if(array[i].commentSeq != 0){
							str += '<li class="media mb-2" style="margin-left: 80px;">';
							str += '<i class="material-icons">subdirectory_arrow_right</i>'
						}else{
							str += '<li class="media mb-2">';
						}
						
						// 해당 세션id만 수정,삭제 가능
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
		               
		               if(array[i].memberId == memebrId){
		                  str += ' | <a  id = "remove" onclick = "removeComment(\'' +  array[i].commentId + '\'' + ',\'' +  array[i].boardId + '\')">삭제</a>';
		                  str += ' | <a id = "modify"'; 
		                  str += ' onclick="modifyComment(\'' + array[i].memberNickName +  '\'' + ',\''+ array[i].memberId +  '\'' + ',\'' + array[i].commentDateString +  '\'' + ',\'' 
		                		  + array[i].commentContent + '\'' + ',\'' + array[i].boardNum +  '\'' + ',\'' + array[i].commentId +  '\'' + ',\'' + array[i].boardId + '\'' + ',\''+ array[i].memberProfileVO.uploadpath + '\')">수정</a>';
		               }
		               
					   // 댓글이 시퀀스가 0 일때만 답글 가능
					   if(array[i].commentSeq == 0){
		              	 str += ' | <a type="button" id = "reply" onclick="replyComment(\'' +  array[i].commentId +  '\'' + ',\'' + array[i].boardId +  '\'' + ',\''+ array[i].boardNum + '\'' + ',\''+ array[i].commentRef + '\')">답글</a>';
					   }
		               
					   str += '</div>';
		               str += '</div>';
		               str += '</div>';
		               str += '<input type = "hidden" value = "' + array[i].boardNum + '" name = "boardNum" />';
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
		  	
		      

			// ======================== 글 삭제버튼 클릭시 호출되는 함수 ========================
				
				function remove(event) {
					// 이벤트 소스(이벤트가 발생한 오브젝트)의 기본동작을 못하게 만듬
					// 기본동작을 가진 대표적인 두 태그 : a 태그(클릭 못하게), form 태그(submit 못하게) 
					event.preventDefault();

					let isRemove = confirm('이 글을 정말 삭제하시겠습니까?');
					if (isRemove == true) {
						location.href = '/adopTemp/adopTempBoardRemove?boardId=${ adopTempContent.boardId }&pageNum=${ pageNum }';
					}
				}
		   
		  	// ======================== 추천 버튼 클릭 시 호출되는 함수 ========================
				// ajax 함수 호출	
				function check(boardId, memberId, goodOrNot) {

					var restAdopCommVO = {
						"boardId" : boardId,
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
									if (restAdopCommVO.goodOrNot == 'Y'
											&& data.goodOrNot == '1') {
										$("#notGoodBtn").attr("disabled", true);
										$('#good').replaceWith('<i class="material-icons align-middle" id = "good">thumb_up_alt</i>');

									} else if (restAdopCommVO.goodOrNot == 'Y'
											&& data.goodOrNot == '0') {
										$("#notGoodBtn").attr("disabled", false);
										$('#good')
												.replaceWith(
														'<i class="material-icons align-middle" id = "good">thumb_up_off_alt</i>');

									} else if (restAdopCommVO.goodOrNot == 'N'
											&& data.goodOrNot == '1') {
										$("#goodBtn").attr("disabled", true);
										$('#notGood').replaceWith('<i class="material-icons align-middle" id = "notGood">thumb_down_alt</i>');

									}  else if (restAdopCommVO.goodOrNot == 'N'
										&& data.goodOrNot == '0') {
										$("#goodBtn").attr("disabled", false);
										$('#notGood')
												.replaceWith(
														'<i class="material-icons align-middle" id = "notGood">thumb_down_off_alt</i>');

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

 				function waring(boardId, memberId,waring) {
 					var restAdopCommVO = {
 						"boardId" : boardId,
 						"memberId" : memberId,
 						"waring":waring,
 					};
 					$.ajax({
 						url : '/api/adopTempBoardWaring.json',
 						method : 'PUT',
						data : JSON.stringify(restAdopCommVO),
						contentType : 'application/json; charset=UTF-8',
 						success : function(data) {
 				
 							console.log('data',data);
 							if (data.waring == '1') {
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
					
 				function selectMemberGoodOrWarn(boardId, memberId) {
 					$.ajax({
 						url: '/api/boardWaringAndGood/' + boardId + '/'+ memberId + '.json',
 						method: 'GET',
						contentType : 'application/json; charset=UTF-8',
 						success : function(data) {
 							
 							console.log('data0', data[0]);
 							console.log('data1', data[1]);
//  						console.log('data',data);

 							if (data[0].waringCount == '1') {
								$('#waring').replaceWith('<i class="material-icons align-middle" id="waring">error</i>');
								
							}
 							
 							if(data[0].good == '1'){
								$('#good').replaceWith('<i class="material-icons align-middle" id = "good">thumb_up_alt</i>');
								
 							}
 							
 							if(data[0].notGood == '1'){
 								$('#notGood').replaceWith('<i class="material-icons align-middle" id = "notGood">thumb_down_alt</i>');
 								
 							}
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