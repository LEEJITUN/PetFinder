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
	
	<br>
    <!-- middle container -->
    <div class="container mt-4">
      <div class="row">

        <!-- Left Menu -->
        <div class="col-sm-2 Board-font"></div>

        <!-- center area -->
        <div class="col-sm-8">

        <!-- Contents area -->
        <div class="border p-4 rounded"><br>
            <h5 class="Board-font">탈퇴하기</h5>
            <p>회원탈퇴를 신청하기 전에 안내 사항을 꼭 확인해주세요.
            <br><br>
            <p><strong>* 탈퇴 후에도 게시판형 서비스에 등록한 게시물은 그대로 남아 있습니다.</strong>
			<p>	- 모든 게시글 및 댓글은 탈퇴 시 자동 삭제되지 않고 그대로 남아 있습니다.<br>
				- 삭제를 원하는 게시글이 있다면 <strong style="color:red">반드시 탈퇴 전 삭제하시기 바랍니다.</strong><br>
				- 탈퇴 후에는 회원정보가 삭제되어 본인 여부를 확인할 수 있는 방법이 없어, <br>  
				&ensp;게시글을 임의로 삭제해드릴 수 없습니다.<br><br><br>
            <hr class="featurette-divider">
			<br><br>
            <form action="/member/remove" method="POST" enctype="multipart/form-data" id="frm">
            
			<input type="hidden" id="memberId" name="memberId" value="${ sessionScope.memberId }" >
              <div class="form-group">
                <label for="memberPassword">
                  <i class="material-icons align-middle">lock</i>
                  <span class="align-middle">비밀번호</span>
                </label>
                <input type="password" class="form-control" id="memberPassword" name="memberPassword" aria-describedby="pwdHelp" required>                
              </div>
				<br>
            <div class="my-3 text-center">
                <button type="submit" class="btn btn-danger" onclick="remove()">탈퇴하기</button>
            </div>
            <br><br>
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
	<br>

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