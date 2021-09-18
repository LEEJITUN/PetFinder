
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<script>
	    

		function readImage(input) {
		    // 인풋 태그에 파일이 있는 경우
		    if(input.files && input.files[0]) {
		        // 이미지 파일인지 검사 (생략)
		        pathpoint = input.value.lastIndexOf('.');
				filepoint = input.value.substring(pathpoint+1,input.length);
				filetype = filepoint.toLowerCase();
				
		
			    // FileReader 인스턴스 생성
		        const reader = new FileReader();
		        // 이미지가 로드가 된 경우
		        reader.onload = e => {
		            const previewImage = document.getElementById("preview-image");
		            previewImage.src = e.target.result;
		        }
		        // reader가 이미지 읽도록 하기
		        reader.readAsDataURL(input.files[0]);
		    }
		}
		
		// input file에 change 이벤트 부여
		const inputImage = document.getElementById("input-image")
		inputImage.addEventListener("change", e => {
		    readImage(e.target)
		})


	    
	    function clickPetKind() {
	        $('#petKind').change(function() {
	
	            var dog = ["골든 리트리버", "닥스훈트", "래브라도 리트리버", "몰티즈", "슈나우저", "푸들", "미니어처 핀셔", "베들링턴 테리어", "보더 콜리", "보스턴 테리어",
	                      "비글", "비숑 프리제", "사모예드", "셰틀랜드 쉽독", "시바 이누","시베리안 허스키","시츄","코카스파니엘","요크셔 테리어","웰시 코기","이탈리안 그레이하운드",
	                      "스피츠","진돗개","치와와","파피용","퍼그","페키니즈","포메라니안","푸들","풍산개","프렌치 불독","믹스견"];
	            var cat = ["노르웨이숲", "랙돌", "러시안블루","먼치킨","뱅갈","브리티시쇼트헤어","샴","스코티시폴드","스핑크스","아메리카숏헤어","아바시니안","코리안숏헤어","터키시앙고라","페르시안"];
	            var changeItem;
	      
	            if (this.value == "D") {
	            	changeItem = dog;
	            } else if (this.value == "C") {
	                changeItem = cat;
	            }else{
	            	changeItem = "other";
	            }
	            
	            $('#petDetailKind').empty();
				/* 고칠 부분  */
	            if(changeItem != "other"){
	            	$('#petDetailKind').append("<option value = '' disabled selected>품종</option>");
		            for (var count = 0; count < changeItem.length; count++) {
		                var option = $("<option>" + changeItem[count] + "</option>");
		                $('#petDetailKind').append(option);
		            }
	            }else{
	            	 $('#petDetailKind').replaceWith("<input class = 'form-control' type = 'text' id='petDetailKind' name='petDetailKind' placeholder='직접입력'>");
	            }
	        });
	    }
	    
	    
	    function clickPetsYesOrNo(){
	    	
	    	// 라디오버튼 클릭시 이벤트 발생
        	var obj_value = $("input:radio[name='memberPetYN']:checked").val();
	    	
			if(obj_value == "Y"){
		   		$('#petRegisterNumber').attr("disabled",false);
		   		$('#petName').attr("disabled",false);
				$('#petKind').attr("disabled",false);
				$('#petDetailKind').attr("disabled",false);
				$('#petBirthday').attr("disabled",false);
				$('#petGender').attr("disabled",false);
				$('#petSize').attr("disabled",false);
				$('#petColor').attr("disabled",false);
				$('#petCoatLength').attr("disabled",false);
        	}else if(obj_value == "N" ){
				$('#petRegisterNumber').attr("disabled",true);
				$('#petName').attr("disabled",true);
				$('#petKind').attr("disabled",true);
				$('#petDetailKind').attr("disabled",true);
				$('#petBirthday').attr("disabled",true);
				$('#petGender').attr("disabled",true);
				$('#petSize').attr("disabled",true);
				$('#petColor').attr("disabled",true);
				$('#petCoatLength').attr("disabled",true);
        	}
	    }
	   
	    
</script>
	    