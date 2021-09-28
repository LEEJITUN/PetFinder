<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>


   <%--  include head.jsp --%>
   <jsp:include page="/WEB-INF/views/include/head.jsp" />

    <style>
    *{
        font-family: 'Noto Sans KR', sans-serif;
        font-size: 22px;
    }
    .carousel-indicators li {
      box-sizing: content-box;
      -ms-flex: 0 1 auto;
      flex: 0 1 auto;
      width: 20px;
      height: 20px;
      line-height: 30px;
      border-radius: 50%;
      text-align: center;
      margin-right: 3%;
      margin-left: 3%;
      text-indent: unset;
      cursor: pointer;
      background-color: lightskyblue;
      background-clip: unset;
      border-top: 0;
      border-bottom: 0;
      opacity: .5;
      transition: opacity .6s ease;
  }

  .carousel-size{
    height: 350px;
  }
  .carousel-control-prev-icon { background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='black' viewBox='0 0 8 8'%3E%3Cpath d='M5.25 0l-4 4 4 4 1.5-1.5-2.5-2.5 2.5-2.5-1.5-1.5z'/%3E%3C/svg%3E"); }
  .carousel-control-next-icon { background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='black' viewBox='0 0 8 8'%3E%3Cpath d='M2.75 0l-1.5 1.5 2.5 2.5-2.5 2.5 1.5 1.5 4-4-4-4z'/%3E%3C/svg%3E"); }



    </style>

</head>
<body>

	<!--  include topNavbar.jsp  -->
	<jsp:include page="/WEB-INF/views/include/topNavbar.jsp" />
	
	
    <!-- Carousel -->
    <div class="container-fluid justify-content-center" style="position:relative;      
    left: 50%;transform: translate(-50%, 0%);">
    <!-- <div id="carouselExampleControls" class="carousel slide container" data-ride="carousel"> -->
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img src="/resources/images/main_Thum.png" class="d-block w-100">
          </div>
        </div>
    </div>
    <!-- end of Carousel -->

	<!-- search Div -->
    <div class = "container-fluid " style="position: relative; background-color:  rgb(255, 239, 107); width: 70%; margin-top:-70px;">
        
    <!-- Select Box -->
		<form action="/petFindReport/findReportPetList" method="GET">
	        <div class="album py-4">
	              
	              <div class="row">
	              
	              	<label for="inputLocation"  class="col-form-label mx-4" style="font-size: 25px;">지역</label>  
	                <div class="col-md-3 mb-3">
	                	<div>
		                    <select class="custom-select d-block w-100" id="SIDO" name = "sido" >
	                        </select>
	                    </div>
	                </div>
	                <label class="col-form-label mx-2" >시군구</label>
	                <div class="col-md-3 mb-3">
	                    <div>
	                        <select class="custom-select d-block w-100" id="SIGUN" name = "sigungu">
	                        </select>
	                    </div>
	                </div>
	                <label class="col-form-label mx-2" >읍면동</label> 
	                <div class="col-md-3 mb-3">
	                    <div>
	                        <select class="custom-select d-block w-100" id="BNAME" name = "bname">
	                        </select>
	                    </div>
	                </div>    
	              </div>
	              
	              <div class= "row">    
	                   
	                <label for="inputLocation"  class="col-form-label mx-4" style="font-size: 25px;">날짜</label>    
	                <div class="col-md-3 mb-2">
	                    <div>
	                        <input  class="form-control" type="date" id="inputDate" name = "inputDate" placeholder=".input-sm">
	                    </div>
	                </div>    
	                       
	                <label for="inputLocation"  class="col-form-label mx-2 " style="font-size: 25px;">&nbsp;종류&nbsp;</label>                                           
	                <div class="col-md-3 mb-2">
	                    <div>
	                        <select class="custom-select d-block w-100" id="petKind" name="petKind" onclick="clickPetKind()" >
	       					  <option value="" disabled selected>종류 선택</option>
	                        </select>
	                    </div>
	                </div>
	                
	                <label for="inputLocation"  class="col-form-label mx-2" style="font-size: 25px;">&nbsp;품종&nbsp;</label>            
	                <div class="col-md-3 mb-2">
	                    <div>
	                        <select class="form-control" id="petDetailKind" name="petDetailKind" >
		
	                        </select>
	                    </div>
	                </div>
	                
	              </div>
	                           
	         </div>	  
                
             <div class="text-center">
       		<button type="submit" class="btn btn-info">찾기</button>
             </div>    
		</form>
 	</div>
    <!-- end of Select Box -->

        
    <br><br><br>

        
 <!--      접기버튼 
         <div id="wrap"  style="display:none; position:relative; width: 10%; height: 30%; ">
            <img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
        </div> -->
    </div>
    <!-- end of search -->

    <!-- middle1 container -->
    <div class="container-fluid"  style="background-color: rgb(239, 239, 239); ">
        <div class="row" >
            <!-- left Menu-->
            <div class = "col-sm-6">
                <!-- carousel_1 -->
                <div id="carouselExampleIndicators" class="carousel slide text-center" data-ride="carousel" style="width: 60%; margin-left: 25%; margin-top: 7%; margin-bottom: 5%; ">
                    <div class="carousel-inner" style="padding-bottom: 12%;  ">
		            <c:choose>
	              	<c:when test="${fn:length(bannerList) >  0}" >
	              		<c:forEach var="attach" items="${ bannerList }"  varStatus="status">
	              		    <c:set var="fileCallPath" value="${ attach.uploadpath }/s_${ attach.uuid }_${ attach.filename }" />
						    
						    <div class="carousel-item <c:if test = "${status.index eq 0}" >active </c:if> text-center">
		           					<a href="">
		           						<img class="d-block w-100" src="/display?fileName=${ fileCallPath }" class="img-thumbnail" style="height: 500px;" >
		           					</a>
						    </div>
						</c:forEach>
					</c:when>
					<c:otherwise>
					 <div class="carousel-item active "> 
                        <img src="/resources/images/main1_slide.PNG" class="d-block w-100 carousel-size" >
                      </div>
                      
                      <div class="carousel-item">
                        <img src="/resources/images/main2_slide.PNG" class="d-block w-100 carousel-size" >
                      </div>
                      
                      <div class="carousel-item">
                        <img src="/resources/images/main1_slide.PNG" class="d-block w-100 carousel-size">
                      </div>
                      
					</c:otherwise>
				</c:choose>
                 
                      
                      <!-- move button -->
                      <a class="carousel-control-prev justify-content-center" href="#carouselExampleIndicators" role="button" data-slide="prev" style="padding-top: 57%;">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                      </a>

                      <ol class="carousel-indicators justify-content-center " >
                        <li data-target="#carouselExampleIndicators" data-slide-to="0"  ></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="1" ></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="2" ></li>
                      </ol>

                      <a class="carousel-control-next justify-content-center" href="#carouselExampleIndicators" role="button" data-slide="next" style="padding-top: 57%; ">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                      </a>
                      <!-- end of next button -->
                    </div>
                </div>
             </div>
            <!-- end of left Menu-->

            <!-- right Menu-->
            <div class = "col-sm-6">
              <div class="text-center" style="margin-top: 10%; margin-right: 10%;">
                <h1 >우리 아이 찾아주세요.</h1>
                <div style="margin-top: 2%;"></div>
                <h3>도움이 필요한 사람들을 위해 발견 신고도 해주세요.</h3>
              </div>
              <br>
              <c:choose>
              <%-- 로그인 사용자일때 --%>
              <c:when test="${ not empty sessionScope.memberId }"> 
	              <div class="text-center">
	                <button type="button" class="btn btn-warning btn-lg text-white" onclick="location.href='/petLostReport/lostReportPetWrite'"><h4>반려동물 분실 신고</h4></button>
	                <button type="button" class="btn btn-success btn-lg text-white" onclick="location.href='/petFindReport/findReportPetWrite'"><h4>유기동물 발견 신고</h4></button>
	              </div>   
      		  </c:when>
          		<c:otherwise>
	              <div class="text-center" style="margin-top: 5%; margin-right: 15%;">
	                <button type="button" class="btn btn-warning btn-lg text-white" onclick="location.href='/member/login'"><h4>반려동물 분실 신고</h4></button>
	                <button type="button" class="btn btn-success btn-lg text-white" onclick="location.href='/member/login'"><h4>유기동물 발견 신고</h4></button>
	              </div>
	              </c:otherwise>  
			</c:choose>
            </div>
            <!-- end of right Menu-->

        </div>
    </div>
  <!-- end of middle1 container -->

  <!-- middle2 container -->
    <div class="container-fluid" >
      <div class="row" >
            <!-- left Menu-->
            <div class = "col-sm-6">
              <!-- carousel_1 -->
              <div id="carouselExampleIndicators2" class="carousel slide text-center" data-ride="carousel" style="width: 60%; margin-left: 25%; margin-top: 7%; margin-bottom: 5%; ">
                  <div class="carousel-inner" style="padding-bottom: 12%; ">
                    <div class="carousel-item active">
                      <img src="/resources/images/main2_slide.PNG" class="d-block w-100 carousel-size" alt="...">
                    </div>
                    <div class="carousel-item">
                      <img src="/resources/images/main2_slide.PNG" class="d-block w-100 carousel-size" alt="...">
                    </div>
                    <div class="carousel-item">
                      <img src="/resources/images/main2_slide.PNG" class="d-block w-100 carousel-size" alt="...">
                    </div>
                    
                    <!-- move button -->
                    <a class="carousel-control-prev justify-content-center" href="#carouselExampleIndicators2" role="button" data-slide="prev" style="padding-top: 57%;">
                      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                      <span class="sr-only">Previous</span>
                    </a>

                    <ol class="carousel-indicators justify-content-center " >
                      <li data-target="#carouselExampleIndicators2" data-slide-to="0"  ></li>
                      <li data-target="#carouselExampleIndicators2" data-slide-to="1" ></li>
                      <li data-target="#carouselExampleIndicators2" data-slide-to="2" ></li>
                    </ol>

                    <a class="carousel-control-next justify-content-center" href="#carouselExampleIndicators2" role="button" data-slide="next" style="padding-top: 57%; ">
                      <span class="carousel-control-next-icon" aria-hidden="true"></span>
                      <span class="sr-only">Next</span>
                    </a>
                    <!-- end of next button -->
                  </div>
              </div>
           </div>
            <!-- right Menu-->
          <div class = "col-sm-6">
            <div class="text-center" style="margin-top: 10%; margin-right: 10%;">
              <h1>입양하기</h1>
              <div style="margin-top: 2%;"></div>
              <h3>도움이 필요한 동물에게 보금자리를 내어주세요.</h3>
            </div>
            <div class="text-center" style="margin-top: 5%; margin-right: 10%;">
              <button type="button" class="btn btn-primary btn-lg text-white " style="height: 70px; padding-left: 100px;  padding-right: 100px;  background-color: rgb(41, 128, 185); border-color: rgb(41, 128, 185);" 
              			onclick="location.href='/adopTemp/adopTempBoardList'"><h4>임보 | 입양</h4></button>
            </div>
          </div>
            <!-- end fo right Menu-->
      </div>
    </div>
  <!-- end of middle2 container -->


   <!-- include -->
   <jsp:include page="/WEB-INF/views/include/footer.jsp" />
   <jsp:include page="/WEB-INF/views/include/function.jsp" />
   

  <!-- JavaScript -->
  <script src="/resources/js/jquery-3.6.0.js"></script>
  <script src="/resources/js/bootstrap.js"></script>

  
  <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script src="/resources/js/locationAPI.js"></script>

  <script>
    $(document).ready(function(){
    	selectLocationBox('SIDO',null,null); // 시도코드 	
    	selectBox('KIND',null);
	});
    
	$("#SIDO").on('change', function () {
	    selectLocationBox('SIGUN',$("#SIDO").val(),null); 
	});
	
	$("#SIGUN").on('change', function () {
		selectLocationBox('BNAME',$("#SIDO").val(),$("#SIGUN").val());
	});
    
    $('.carouselExampleIndicators2').carousel({
    	interval: 1500
    });

    
  </script>
</body>
</html>