<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<form action="/petFindReport/findReportPetList" method="GET">
		<div class="album py-5">
			<div class="container"
				style="background-color: rgb(255, 239, 107); padding-top: 30px; padding-bottom: 30px;">
				<div class="text-center">
					<div class="custom-control custom-radio custom-control-inline">
					  <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="L" style="width:20px;height:20px;border:1px;">
					  <label class="form-check-label" for="inlineRadio1">분실</label>
					</div>
					<div class="custom-control custom-radio custom-control-inline">
					  <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="F" style="width:20px;height:20px;border:1px;">
					  <label class="form-check-label" for="inlineRadio2">신고</label>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 mb-3">
						<label for="size">지역선택 (시/구)</label>
						<div>
							<select class="custom-select d-block w-100" id="SIDO" name="sido">
							</select>
						</div>
					</div>
					<div class="col-md-4 mb-3">
						<label for="size">(시/군/구)</label>
						<div>
							<select class="custom-select d-block w-100" id="SIGUN"
								name="sigungu">
							</select>
						</div>
					</div>
					<div class="col-md-4 mb-3">
						<label for="size">(읍/면/동)</label>
						<div>
							<select class="custom-select d-block w-100" id="BNAME"
								name="bname">
							</select>
						</div>
					</div>

					<div class="col-md-4 mb-3">
						<label for="size">시작 날짜</label>
						<div>
							<input class="form-control" type="date" id="startDate"
								name="startDate" placeholder=".input-sm">
						</div>
					</div>

					<div class="col-md-4 mb-3">
						<label for="size">끝 날짜</label>
						<div>
							<input class="form-control" type="date" id="endDate"
								name="endDate" placeholder=".input-sm">
						</div>
					</div>


					<div class="col-md-4 mb-3">
						<label for="size"></label>
						<div></div>
					</div>

					<div class="col-md-4 mb-3">
						<label for="size">종류</label>
						<div>
							<select class="custom-select d-block w-100" id="petKind"
								name="petKind" onclick="clickPetKind()">
								<option value="" disabled selected>종류 선택</option>

							</select>
						</div>
					</div>
					<div class="col-md-4 mb-3">
						<label for="size">품종</label>
						<div>
							<select class="form-control" id="petDetailKind"
								name="petDetailKind">

							</select>
						</div>
					</div>
					<div class="col-md-4 mb-3">
						<label for="size">성별</label>
						<div>
							<select class="custom-select" id="petGender" name="petGender">

							</select>
						</div>
					</div>


					<div class="col-md-4 mb-3">
						<label for="size">사이즈</label>
						<div>
							<select class="custom-select" id="petSize" name="petSize">
							</select>
						</div>
					</div>
					<div class="col-md-4 mb-3">
						<label for="size">색</label>
						<div>
							<select class="custom-select" id="petColor" name="petColor">
							</select>
						</div>
					</div>
					<div class="col-md-4 mb-3">
						<label for="size">털길이</label>
						<div>
							<select class="custom-select" id="petCoatLength"
								name="petCoatLength">
							</select>
						</div>
					</div>
				</div>
				<div class="text-center">
					<button type="submit" class="btn btn-info">상세정보 검색</button>
				</div>
			</div>
		</div>
	</form>

	<!-- end of Select Box -->

	<!-- Thumbnail -->
	<div class="album py-5 bg-white">
		<div class="container">
			<div class="row">

				<c:choose>
					<c:when test="${fn:length(reportBoardList) >  0}">
						<c:forEach var="reportBoard" items="${ reportBoardList }">

							<div class="col-md-3">
								<div class="card mb-4 shadow-sm">
									<c:choose>
										<c:when test="${reportBoard.petVO.attachList[0].uuid != null}">
											<c:set var="fileCallPath"
												value="${ reportBoard.petVO.attachList[0].uploadpath }/s_${ reportBoard.petVO.attachList[0].uuid }_${ reportBoard.petVO.attachList[0].filename }" />
											<c:set var="originPath"
												value="${ reportBoard.petVO.attachList[0].uploadpath }/${ reportBoard.petVO.attachList[0].uuid }_${ reportBoard.petVO.attachList[0].filename }" />
											<a href="/display?fileName=${ originPath }"> <img
												class="bd-placeholder-img card-img-top W-100"
												src="/display?fileName=${ fileCallPath }"
												class="img-thumbnail" style="height: 225px;">
											</a>
										</c:when>
										<c:otherwise>
											<svg class="bd-placeholder-img card-img-top" width="100%"
												height=225px xmlns="http://www.w3.org/2000/svg" role="img"
												aria-label="Placeholder: Thumbnail"
												preserveAspectRatio="xMidYMid slice" focusable="false">    
	                 <rect width="100%" height="100%" fill="#55595c" />
	                 <text x="50%" y="50%" fill="#eceeef" dy=".3em"></text></svg>
										</c:otherwise>
									</c:choose>
									<div class="card-body">
										<div align="right">
											<c:if test="${reportBoard.boardReportType == 'L'}">
												<span class="badge badge-pill badge-warning">분실</span>
											</c:if>
											<c:if test="${reportBoard.boardReportType == 'F'}">
												<span class="badge badge-pill badge-success">발견</span>
											</c:if>
										</div>
										<h5 class="card-title">${reportBoard.boardTitle}</h5>
										<p class="card-text">
											<c:choose>
												<c:when test="${reportBoard.petVO.sido != ''}">
													<c:if test="${reportBoard.boardReportType == 'L'}">
	                   		${reportBoard.petVO.sido}_${reportBoard.petVO.sigungu}_${reportBoard.petVO.lostPetDate}
	                   	</c:if>
													<c:if test="${reportBoard.boardReportType == 'F'}">
	                  		${reportBoard.petVO.sido}_${reportBoard.petVO.sigungu}_${reportBoard.petVO.findPetDate}
	                  	</c:if>
												</c:when>
												<c:otherwise>
	                 	 ${reportBoard.petVO.address}_${reportBoard.petVO.findPetDate}
	                   </c:otherwise>
											</c:choose>
										</p>
										<p class="card-text">
											품종 : ${reportBoard.petVO.petKind}
											<c:if test="${not empty reportBoard.petVO.petDetailKind}">_${reportBoard.petVO.petDetailKind}</c:if>
										</p>
										<div class="d-flex justify-content-between align-items-center">
											<div class="btn-group">
												<button type="button"
													class="btn btn-sm btn-outline-secondary"
													onclick=<c:if test = "${reportBoard.boardReportType == 'F'}">
	                       "location.href = '/petFindReport/findReportPetContent?reportId=${ reportBoard.reportId }'"
	                      </c:if>
													<c:if test = "${reportBoard.boardReportType == 'L'}">
	                      "location.href = '/petLostReport/lostReportPetContent?reportId=${ reportBoard.reportId }'"
	                      </c:if>>자세히
													보기</button>
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

			<!-- pagination area -->
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<!-- 이전 -->
					<li class="page-item ${(pageMaker.prev) ? '': 'disabled'}"><a
						class="page-link"
						href="${(pageMaker.prev) ? 'petFindReport/findReportPetList?pageNum=' += (pageMaker.startPage - 1) += '&type=' += pageMaker.cri.type += '&keyword=' += pageMaker.cri.keyword : '' }#board"
						tabindex="-1" aria-disabled="true">Previous</a></li>

					<!-- 시작페이지 번호 ~ 끝 페이지 번호 -->
					<c:forEach var="i" begin="${ pageMaker.startPage }"
						end="${ pageMaker.endPage }" step="1">
						<li
							class="page-item ${ (pageMaker.cri.pageNum eq i) ? 'active': '' }">
							<a class="page-link"
							href="/petFindReport/findReportPetList?pageNum=${ i }#board">${ i }</a>
						</li>
					</c:forEach>

					<!-- 다음 -->
					<li class="page-item ${(pageMaker.next) ? '': 'disabled'}"><a
						class="page-link"
						href="${(pageMaker.next) ? 'petFindReport/findReportPetList?pageNum=' += (pageMaker.endPage + 1) += '&type=' += pageMaker.cri.type += '&keyword=' += pageMaker.cri.keyword : '' }#board"
						tabindex="-1" aria-disabled="true">Next</a></li>
				</ul>
			</nav>
			<!-- end of pagination area -->
		</div>
		<!-- end of Thumbnail -->


		<%--  include footer.jsp --%>
		<jsp:include page="/WEB-INF/views/include/function.jsp" />
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />


		<!-- JavaScript -->
		<script src="/resources/js/jquery-3.6.0.js"></script>
		<script src="/resources/js/bootstrap.js"></script>
		<script>
	
		$(document).ready(function(){
			selectLocationBox('SIDO',null,null); // 시도코드 
			selectBox('KIND',null);
			selectBox('GENDER',null);
			selectBox('SIZE',null);
			selectBox('COLOR',null);
			selectBox('COATlENGTH',null);
			connectWs();
		});
		
	
		$("#SIDO").on('change', function () {
		    selectLocationBox('SIGUN',$("#SIDO").val(),null); // 시군구코드
		});
		$("#SIGUN").on('change', function () {
			selectLocationBox('BNAME',$("#SIDO").val(),$("#SIGUN").val()); // 동
		});
		
	 	

	</script>
</body>
</html>