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
        }*/

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
            <h5 class="Board-font">탈퇴하기</h5>

            <hr class="featurette-divider">

            <form action="/member/remove" method="POST" enctype="multipart/form-data" id="frm">
            
			<input type="hidden" id="memberId" name="memberId" value="${ sessionScope.memberId }" >
              <div class="form-group">
                <label for="memberPassword">
                  <i class="material-icons align-middle">lock</i>
                  <span class="align-middle">비밀번호</span>
                </label>
                <input type="password" class="form-control" id="memberPassword" name="memberPassword" aria-describedby="pwdHelp" required>                
              </div>

            <div class="my-3 text-center">
                <button type="submit" class="btn btn-danger" onclick="remove()">탈퇴하기</button>
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


	<!-- include -->
    <jsp:include page="/WEB-INF/views/include/footer.jsp" />
    <jsp:include page="/WEB-INF/views/include/function.jsp" />

    <script src="http://code.jquery.com/jquery-3.3.1.js"></script>
	<script type="text/javascript"/></script>
	
	
    <%-- JavaScript --%>
    <script src="/resources/js/jquery-3.6.0.js"></script>
    <script src="/resources/js/bootstrap.js"></script>

	<script>
	// 탈퇴하기 버튼을 클릭했을 때 호출되는 함수
	function remove() {
		
		let isRemove = confirm('정말 탈퇴 하시겠습니까?');
		if (isRemove == true) {
			document.frm.submit();
		}else {
			return;
		}
	}
	</script>

</body>
</html>