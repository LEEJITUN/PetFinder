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
	
      <!-- Select Box -->
      <form>
        <div class="album py-5">
            <div class="container" style="background-color:  rgb(255, 239, 107); padding-top: 30px; padding-bottom: 30px;">
              
              <div class="row">
                <div class="col-md-4 mb-3">
                    <label for="size">지역(구)전체</label>
                    <div>
                        <select class="custom-select d-block w-100" id="area" required>
                            <option value="">Choose...</option>
                            <option>United States</option>
                        </select>
                    </div>
                    </div>
                    <div class="col-md-4 mb-3">
                    <label for="size">지역(동)전체</label>

                    <div>
                        <select class="custom-select d-block w-100" id="village" required>
                            <option value="">Choose...</option>
                            <option>United States</option>
                        </select>
                    </div>
                    </div>
                    <div class="col-md-4 mb-3">
                    <label for="size">날짜</label>
                    <div>
                        <input  class="form-control" type="date" id="inputDate" placeholder=".input-sm">
                    </div>
                    </div>

                    <div class="col-md-4 mb-3">
                    <label for="size">종류</label>
                    <div>
                        <select class="custom-select d-block w-100" id="searchType" required>
                            <option value="">Choose...</option>
                            <option>United States</option>
                        </select>
                    </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="size">품종</label>
                        <div>
                            <select class="custom-select d-block w-100" id="kind" required>
                                <option value="">Choose...</option>
                                <option>United States</option>
                            </select>
                        </div>
                        </div>
                        <div class="col-md-4 mb-3">
                            <label for="size">성별</label>
                            <div>
                                <select class="custom-select d-block w-100" id="gender" required>
                                    <option value="">Choose...</option>
                                    <option>United States</option>
                                </select>
                            </div>
                            </div>

             
                    <div class="col-md-4 mb-3">
                    <label for="size">사이즈</label>
                    <div>
                        <select class="custom-select d-block w-100" id="size" required>
                            <option value="">Choose...</option>
                            <option>United States</option>
                        </select>
                    </div>
                    </div>
                    <div class="col-md-4 mb-3">
                    <label for="size">색</label>
                    <div>
                        <select class="custom-select d-block w-100" id="color" required>
                            <option value="">Choose...</option>
                            <option>United States</option>
                        </select>
                    </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="size">털길이</label>
                        <div>
                            <select class="custom-select d-block w-100" id="coatLength" required>
                                <option value="">Choose...</option>
                                <option>United States</option>
                            </select>
                        </div>
                        </div>
                    </div>
                    <div class="text-center">
                      <button type="button" class="btn btn-info">상세정보 검색</button>
                      </div>
                    </div>
                    </div>
                </form>
       
      
      

      <!-- end of Select Box -->

      <!-- Thumbnail -->
      <div class="album py-5 bg-white">
        <div class="container">
          <div class="row">
          
<script type="text/javascript">

    			console.log(${ reportBoardList });
</script>             
    	<c:choose>
    		<c:when test="${fn:length(reportBoardList) >  0}">
    			<c:forEach var="reportBoard" items="${ reportBoardList }">
    			
	    		 <div class="col-md-3">
	              <div class="card mb-4 shadow-sm">
	                <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
	                <title>Placeholder</title>
	                <rect width="100%" height="100%" fill="#55595c"/>
	                <text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
	                <div class="card-body">
	                  <p class="card-text">${reportBoard.boardTitle}</p>
	                  <p class="card-text">
	                  ${reportBoard.petVO.sido}_${reportBoard.petVO.sigungu}_${reportBoard.petVO.lostPetDate}
	                  </p>
	                  <p class="card-text">품종 : ${reportBoard.petVO.petDetailKind}</p>
	                  <div class="d-flex justify-content-between align-items-center">
	                    <div class="btn-group">
	                      <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
	                    </div>
	                  </div>
	                </div>
	              </div>
	              </div>
    			</c:forEach>
    		
    		</c:when>
    		
    		<c:otherwise>
    			게시판 글이 없습니다.
    		</c:otherwise>
    	</c:choose>

              
              
            </div>

    

 

          </div>
        </div>

        <!-- pagination area -->
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                <a class="page-link" href="#">Next</a>
                </li>
            </ul>
        </nav>
        <!-- end of pagination area -->
      </div>
      <!-- end of Thumbnail -->

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


   <%--  include footer.jsp --%>
   <jsp:include page="/WEB-INF/views/include/footer.jsp" />



    <!-- JavaScript -->
    <script src="/resources/js/jquery-3.6.0.js"></script>
    <script src="/resources/js/bootstrap.js"></script>

</body>
</html>