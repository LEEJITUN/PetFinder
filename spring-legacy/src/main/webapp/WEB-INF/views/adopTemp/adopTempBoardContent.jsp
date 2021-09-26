<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
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
						style="background-color: rgb(41, 128, 185); color: black;"><h3>�Ծ�
								| �Ӻ�</h3></a></li>
				</ul>
				<!-- end of Vertical Nav -->
			</div>
			<!-- end of Left Menu -->


			<!-- Right area -->
			<div class="col-sm-9">

				<!-- Contents area -->
				<div id="comment" class="border border-primary p-3">
					<br>
					<h3 class="text-center">���� : ${ adopTempContent.boardTitle }</h3>

					<!-- �� �󼼺��� ���� -->
					<table class="table">
						<tr>
							<th scope="row" class="text-center">�ۼ���</th>
							<td>${ adopTempContent.memberNickName }</td>
							<th scope="row" class="text-center">�ۼ���</th>
							<td><fmt:formatDate
									value="${ adopTempContent.boardRegDate }" pattern="yyyy.MM.dd" /></td>
							<th scope="row" class="text-center"></th>
							<th scope="row" class="text-center">��ȸ��</th>
							<td>${ adopTempContent.boardReadCount }</td>
							<td></td>
						</tr>

						<tr style="height: 300px">
							<th scope="row" class="text-center">����</th>
							<td colspan="5"><pre>${ adopTempContent.boardContent}</pre>

							</td>
						</tr>
						<tr>
							<th scope="row" class="text-center">÷������</th>
							<td colspan="5">
								<ul>
									<li>÷������1</li>
									<li>÷������2</li>
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
						<%-- �α��� ������϶� --%>
						<c:if test="${not empty sessionScope.memberId }">
							<button type="button" id="goodBtn" class="btn btn-primary btn-lg"
								onclick="check('${ adopTempContent.boardId}', '${sessionScope.memberId }', 'Y', null)">
								<i class="material-icons align-middle" id="good">thumb_up_off_alt</i>
								<span class="align-middle">��õ</span>
							</button>

							<button type="button" id="notGoodBtn"
								class="btn btn-secondary btn-lg ml-3"
								onclick="check('${ adopTempContent.boardId}', '${sessionScope.memberId }', 'N', null)">
								<i class="material-icons align-middle" id="notGood">thumb_down_off_alt</i>
								<span class="align-middle">����õ</span>
							</button>
							<button type="button" class="btn btn-danger btn-lg ml-3"
								onclick="check('${ adopTempContent.boardId}', '${sessionScope.memberId }', null, 'Y')">
								<i class="material-icons align-middle">dangerous</i> <span
									class="align-middle">�Ű�</span>
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
								class="align-middle">�۸��</span>
						</button>
					</div>

					<div class="col-sm-10 text-right">
						<%-- �α��� ������϶� --%>
						<c:if test="${not empty sessionScope.memberId }">
							<%-- �α��� ����� �г��Ӱ� ���ۼ��� �г�����  ������ --%>
							<c:if
								test="${ sessionScope.memberNic eq adopTempContent.memberNickName }">

								<button type="button" class="btn btn-primary text-white btn-sm"
									onclick="location.href = '/adopTemp/adopTempBoardModify?boardId=${ adopTempContent.boardId }&pageNum=${ pageNum }';">
									<i class="material-icons align-middle">edit</i> <span
										class="align-middle">�ۼ���</span>
								</button>
								<button type="button" class="btn btn-danger btn-sm ml-3"
									onclick="remove(event);">
									<i class="material-icons align-middle">delete</i> <span
										class="align-middle">�ۻ���</span>
								</button>
							</c:if>
							<button type="button" class="btn btn-danger btn-sm ml-3">
								<i class="material-icons align-middle">reply</i> <span
									class="align-middle">��۾���</span>
							</button>
						</c:if>
					</div>
				</div>


				<!-- Comment -->
				<div id="comment" class="border border-secondary mt-2 p-3">
					<i class="material-icons">forum</i> ���

					<hr class="featurette-divider">

					<ul class="list-unstyled mt-4" id="mainUl">


						<c:choose>
							<c:when test="${fn:length(commentList) >  0}">
								<c:forEach var="comment" items="${ commentList }">
									<ul class="list-unstyled mt-4" id="${comment.boardNum}">
										<li class="media mb-2"><img
											src="/resources/images/kirby2.jpg" width="50" height="50"
											class="mr-3 rounded-circle">
											<div class="media-body">
												<div class="row">
													<div class="col-md-4">
														<h6>${ comment.memberNickName }(${ comment.memberId }
															)</h6>
													</div>
													<div class="col-md-8">
														<div class="text-right text-secondary">
															<time class="comment-date">${comment.commentRegDate}</time>
															<c:if test="${sessionScope.memberId eq comment.memberId}">
		                        | <a id="remove" onclick="removeComment('${comment.commentId}' , '${comment.boardId}')">����</a>
		                        | <a id="modify" onclick="modifyComment('${comment.memberNickName}' , '${comment.memberId}' , '${comment.commentRegDate}' , '${comment.commentContent}' , '${ comment.boardNum}', '${ comment.commentId}' , '${ comment.boardId}')">����</a>
															</c:if>
								| <a type="button" id="reply" onclick="replyComment('${comment.commentId}' , '${comment.boardId}', '${comment.boardNum}' , '${comment.commentRef}')">���</a>
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
										<label for="exampleFormControlTextarea1">����� �ۼ�</label>
										<textarea class="form-control" id="commentContent"
											name="commentContent" rows="3"></textarea>
									</div>
								</div>
								<div class="col-2 align-self-center">
									<button type="submit" id="newCreatBtn"
										class="btn btn-info btn-sm">�ۼ�</button>
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
			<a href="#!" style="color: gray;">&ensp; ���Ŀ� ���� &ensp;</a> | <a
				href="#!" style="color: gray;">&ensp; ������ ���� �ڸ��� &ensp;</a> | <a
				href="#!" style="color: gray;">&ensp; ������ȣ �����ý��� &ensp;</a>
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
	
	$('form#frm').on('submit', function () {
		event.preventDefault();
		
		let obj = $(this).serializeObject();
		let strJson = JSON.stringify(obj);
		
// 		console.log('data ', strJson);
		// ajax �Լ� ȣ��
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
		
		// ajax �Լ� ȣ��
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
		
		
		//  ��ۻ��� ��ư�� Ŭ������ �� ȣ��Ǵ� �Լ�
		function removeComment(commentId,boardId) {
				var restAdopCommCommentVO  = {
								"commentId" : commentId,
								"boardId"  : boardId,
					};
				
				let isRemove = confirm('�� ���� ���� �����Ͻðڽ��ϱ�?');
				
				if (isRemove == true) {
					// ���� �� -> �ٽ� ���ε� showData();

					 // ajax �Լ� ȣ��
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
		
			// ��ۼ��� ��ư�� Ŭ������ �� ȣ��Ǵ� �Լ�
			function modifyComment(nick,id,date,commentContent,index,commentId,boardId) {
 				console.log('id', id);
					var str = "";
					let memebrId = '${sessionScope.memberId}';
					

					str += '<li class="media mb-2">';
					str += '<img src="/resources/images/kirby1.jpg" width="50" height="50" class="mr-3 rounded-circle">';
					str += '<div class="media-body" >';
					str += '<div class="row">';
					str += '<div class="col-md-4">';
					str += '<h6>' + nick + '(' + id + ') </h6>';
					str += '</div>';
					str += '<div class="col-md-8">';
					str += '<div class="text-right text-secondary">';
					str += '<time class="comment-date">' + date +'</time>';
					
					if(id == memebrId){
						str += ' | <a id = "save" onclick="saveComment(\'' + commentId + '\'' + ',\'' +  boardId + '\'' + ',\'' + index + '\')">����</a>';
						str += ' | <a  id = "cancle" onclick = "allSelect(\'' + boardId + '\')">���</a>';
					}
					str += '</div>';
					str += '</div>';
					str += '</div>';
					str += ' <textarea class="form-control"  id="textarea' + index + '" name = "' + index + '" rows="3">' + commentContent + '</textarea>';
					str += '</div>';
					str += '</li>';

					$('ul#'+index).html(str); 
			}
			
			
			
			// ��۴�� ��ư�� Ŭ������ �� ȣ��Ǵ� �Լ�
			function replyComment(commentId,boardId,index,commentRef) {
				console.log('boardId', boardId);
 				console.log('index', index);
				event.preventDefault();
				
				// ��� ��� �� ������
				
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
				
				str += '<label>���� �ۼ�</label>';
				str += '<textarea class="form-control" id="commentContent" name = "commentContent" rows="3"></textarea>';
				str += '</div>';
				str += '</div>';
				str += '<div class="col-2 align-self-center">';
				str += '<button type="submit" onclick = "replySave('+ index + ')" class="btn btn-info btn-sm">�ۼ�</button>';
				str += '</div></div></form></div></li>';

					
				$('ul#'+index).append(str); 
					
			}

			function replySave(index){
				
			  $('#frm'+index).on('submit', function () {
					event.preventDefault();
					
					let obj = $(this).serializeObject();
					let strJson = JSON.stringify(obj);
					console.log('����',strJson);
					
					
					// ajax �Լ� ȣ��
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
				
				 // ajax �Լ� ȣ��
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
		               str += '<li class="media mb-2">';
		               str += '<img src="/resources/images/kirby1.jpg" width="50" height="50" class="mr-3 rounded-circle">';
		               str += '<div class="media-body" >';
		               str += '<div class="row">';
		               str += '<div class="col-md-4">';
		               str += '<h6>' + array[i].memberNickName + '(' + array[i].memberId + ') </h6>';
		               str += '</div>';
		               str += '<div class="col-md-8">';
		               str += '<div class="text-right text-secondary">';
		               str += '<time class="comment-date">' + array[i].commentRegDate +'</time>';
		               
		               if(array[i].memberId == memebrId){
		                  str += ' | <a  id = "remove" onclick = "removeComment(\'' +  array[i].commentId + '\'' + ',\'' +  array[i].boardId + '\')">����</a>';
		                  str += ' | <a id = "modify"'; 
		                  str += ' onclick="modifyComment(\'' + array[i].memberNickName +  '\'' + ',\''+ array[i].memberId +  '\'' + ',\'' + array[i].commentRegDate +  '\'' + ',\'' + array[i].commentContent + '\'' + ',\'' + array[i].boardNum +  '\'' + ',\'' + array[i].commentId +  '\'' + ',\'' + array[i].boardId + '\')">����</a>';
		               }
		               str += ' | <a type="button" id = "reply" onclick="replyComment(\'' +  array[i].commentId +  '\'' + ',\'' + array[i].boardId +  '\'' + ',\''+ array[i].boardNum + '\'' + ',\''+ array[i].commentRef + '\')">���</a>';
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
		      

			// ======================== �� ������ư Ŭ���� ȣ��Ǵ� �Լ� ========================
				
				function remove(event) {
					// �̺�Ʈ �ҽ�(�̺�Ʈ�� �߻��� ������Ʈ)�� �⺻������ ���ϰ� ����
					// �⺻������ ���� ��ǥ���� �� �±� : a �±�(Ŭ�� ���ϰ�), form �±�(submit ���ϰ�) 
					event.preventDefault();

					let isRemove = confirm('�� ���� ���� �����Ͻðڽ��ϱ�?');
					if (isRemove == true) {
						location.href = '/adopTemp/adopTempBoardRemove?boardId=${ adopTempContent.boardId }&pageNum=${ pageNum }';
					}
				}
		   
		  	// ======================== ��õ ��ư Ŭ�� �� ȣ��Ǵ� �Լ� ========================
				// ajax �Լ� ȣ��	
				function check(boardId, memberId, goodOrNot, waring) {

					var RestAdopCommVO = {
						"boardId" : boardId,
						"memberId" : memberId,
						"goodOrNot" : goodOrNot,
						"waring" : waring,
					};

					console.log('RestAdopCommVO', RestAdopCommVO);

					$.ajax({
								url : '/api/adopCommBoardCheck.json',
								method : 'PUT',
								data : JSON.stringify(RestAdopCommVO),
								contentType : 'application/json; charset=UTF-8',
								success : function(data) {
								console.log('data', data);
									// ���� �� : Y , �����Ϳ��� ���� �� : 1
									if (RestAdopCommVO.goodOrNot == 'Y'
											&& data.goodOrNot == '1') {
										$("#notGoodBtn").attr("disabled", true);
										$(good)
												.replaceWith(
														'<i class="material-icons align-middle" id = "good">thumb_up_alt</i>');

									} else if (RestAdopCommVO.goodOrNot == 'Y'
											&& data.goodOrNot == '0') {
										$("#notGoodBtn").attr("disabled", false);
										$(good)
												.replaceWith(
														'<i class="material-icons align-middle" id = "good">thumb_up_off_alt</i>');

									} else if (RestAdopCommVO.goodOrNot == 'N'
											&& data.goodOrNot == '1') {
										$("#goodBtn").attr("disabled", true);
										$(notGood)
												.replaceWith(
														'<i class="material-icons align-middle" id = "notGood">thumb_down_alt</i>');

									}  else if (RestAdopCommVO.goodOrNot == 'N'
										&& data.goodOrNot == '0') {
										$("#goodBtn").attr("disabled", false);
										$(notGood)
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
				
				// ======================== �Ű� ��ư�� Ŭ������ �� ȣ��Ǵ� �Լ� ========================

// 				function waring(boardId, memberId) {
// 					var RestAdopCommVO = {
// 						"boardId" : boardId,
// 						"memberId" : memberId
// 					};
// 					$.ajax({
// 						url : '/api/adopTempBoardWaring.json',
// 						method : 'POST',
// 						data : JSON.stringify(RestAdopCommVO),
// 						contentType : 'application/json; charset=UTF-8',
// 						success : function(data) {


// 						},
// 						error : function(request, status, error) {
// 							alert('code: ' + request.status + '\n message: '
// 									+ request.responseText + '\n error: ' + error);
// 						}
// 					});
// 				}
				
				

		
	</script>


</body>
</html>