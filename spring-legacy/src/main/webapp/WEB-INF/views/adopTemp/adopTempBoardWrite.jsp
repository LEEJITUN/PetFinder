<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%--  include head.jsp --%>
<jsp:include page="/WEB-INF/views/include/head.jsp" />

<!-- include libraries(jQuery, bootstrap) -->
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="styles.css" rel="stylesheet">

<!-- include summernote css/js -->
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>


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
				<div class="border border-primary p-4 rounded">
					<div class="row">
						<div class="col-sm-6 ">
							<h3 style="font-family: 'Noto Sans KR', sans-serif;">입양 | 임보
								글쓰기</h3>
						</div>
					</div>
					<hr class="featurette-divider">

					<div class="clearfix"></div>
					<form action="/adopTemp/adopTempBoardWrite" method="POST">

						<input type="hidden" value="${ sessionScope.memberId }"
							name="memberId" /> <input type="hidden"
							value="${ sessionScope.memberNic }" name="memberNickName">

						<div class="form-group">
							<input type="text" class="form-control" id="boardTitle"
								name="boardTitle" placeholder="제목을 입력해주세요."
								onclick="inputSubject()" required="required">
						</div>
						<!-- 내용-->
						          <textarea id="summernote" class="summernote" name="boardContent"><h1 style="text-align: center; " helvetica="" neue",="" helvetica,="" arial,="" sans-serif;="" color:="" rgb(0,="" 0,="" 0);="" text-align:="" center;"=""><span style="color: rgb(255, 0, 0); background-color: rgb(255, 255, 0); font-weight: bold;">간단하게 적어주세요.</span></h1><h4 style="font-family: " helvetica="" neue",="" helvetica,="" arial,="" sans-serif;="" color:="" rgb(0,="" 0,="" 0);"=""><br></h4><p style="font-family: " helvetica="" neue",="" helvetica,="" arial,="" sans-serif;="" color:="" rgb(0,="" 0,="" 0);"=""><b>1-1. 입양보내는 분 거주지역 :&nbsp;</b></p><p style="font-family: " helvetica="" neue",="" helvetica,="" arial,="" sans-serif;="" color:="" rgb(0,="" 0,="" 0);"=""><b>1-2. 입양 보내는 사람 :&nbsp;</b></p><p style="font-family: " helvetica="" neue",="" helvetica,="" arial,="" sans-serif;="" color:="" rgb(0,="" 0,="" 0);"=""><b>1-3. 입양 보내는 사람 전화번호 :&nbsp;</b></p><p style="font-family: " helvetica="" neue",="" helvetica,="" arial,="" sans-serif;="" color:="" rgb(0,="" 0,="" 0);"=""><b>2-1. 아이 성별 :&nbsp;</b></p><p style="font-family: " helvetica="" neue",="" helvetica,="" arial,="" sans-serif;="" color:="" rgb(0,="" 0,="" 0);"=""><b>2-2 : 아이 나이 :&nbsp;</b></p><p style="font-family: " helvetica="" neue",="" helvetica,="" arial,="" sans-serif;="" color:="" rgb(0,="" 0,="" 0);"=""><b>2-3. 건강사항 :&nbsp;</b></p><p style="font-family: " helvetica="" neue",="" helvetica,="" arial,="" sans-serif;="" color:="" rgb(0,="" 0,="" 0);"=""><b>3. 특이사항 :&nbsp;</b></p><h6 style="font-family: " helvetica="" neue",="" helvetica,="" arial,="" sans-serif;="" color:="" rgb(0,="" 0,="" 0);"=""><br></h6>	  
								  </textarea>

						<div class="my-4 text-center">
							<button type="submit" class="btn btn-warning">
								<i class="material-icons align-middle">create</i> <span
									class="align-middle">글 등록하기</span>
							</button>
							<button type="reset" class="btn btn-success ml-3">
								<i class="material-icons align-middle">clear</i> <span
									class="align-middle">초기화</span>s
							</button>
							<button type="button" class="btn btn-secondary ml-3"
								onclick="location.href = '/adopTemp/adopTempBoardList?pageNum=${ pageNum }';">
								<i class="material-icons align-middle">list</i> <span
									class="align-middle">글목록</span>
							</button>
						</div>
					</form>
				</div>
			</div>
			<!-- end of Right area -->
		</div>
	</div>
	<!-- end of middle container -->


	<!-- Footer -->
    <jsp:include page="/WEB-INF/views/include/footer.jsp" />


	<!-- JavaScript -->
	<script>
		$(document).ready(function() {
			$('#summernote').summernote({
				tabsize : 3,
				height : 800,
				lang : 'ko-KR' // 언어 세팅
			});
		});

		function inputSubject() {
			var strText = $(title).text();
		}
	</script>

</body>

</html>