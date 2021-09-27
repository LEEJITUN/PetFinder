
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
		const inputImage = document.getElementById("input-image");
		
		inputImage.addEventListener("change", e => {
		    readImage(e.target)
		});

		
	    function clickPetKind() {
	        $('#petKind').change(function() {
	
	        	var codeM = this.value;
	        	
	        	selectBox(codeM,null);
	        	
      		});
	            
	    }
		
		function selectBox(codeM, codeD){
        	let changeItemArray = [];
        	
        	console.log(';codeM : ',codeM);
        	
        	$.ajax({
				url: '/api/coedStrList/' + codeM + '.json',
				method : 'GET',
				contentType : 'application/json; charset=UTF-8',
				success : function(data) {
					console.log('data',data);
					for(let item of data){
						changeItemArray.push(item);
					}
					
					if(codeM == 'D' || codeM == 'C'|| codeM == 'O'){
						console.log('품종');
						setDataPetKind(changeItemArray,codeM,codeD);
					}else{
						setSelectBox(changeItemArray,codeM,codeD);
					}
				},
				error : function(request, status, error) {
					alert('code: ' + request.status + '\n message: '
							+ request.responseText + '\n error: ' + error);
				}
			});
		}
		
		// selectBox 나누기
		function setSelectBox(changeItemArray,codeM,codeD){
	
			if(codeM == 'KIND' ){
				setSelectBoxData(changeItemArray,codeD,'petKind');
			}else if(codeM == 'SIZE' ){
				setSelectBoxData(changeItemArray,codeD,'petSize');
				 
			}else if(codeM == 'COLOR' ){
				setSelectBoxData(changeItemArray,codeD,'petColor');
				 
			}else if(codeM == 'COATlENGTH' ){
				setSelectBoxData(changeItemArray,codeD,'petCoatLength');
				 
			}else if(codeM == 'GENDER' ){
				setSelectBoxData(changeItemArray,codeD,'petGender');
			}
		}
		
		// selectBox 값 담기
		function setSelectBoxData(changeItemArray,codeD,selectBoxName){
			 $('#'+selectBoxName).empty();
			 
			 $('#'+selectBoxName).append("<option value = '' disabled selected>선택</option>");
			 
			  for (var i = 0; i < changeItemArray.length; i++) {
				  
			  		 if(codeD == changeItemArray[i].codeD){
						 var option = $("<option value = "+ changeItemArray[i].codeD + " selected >" + changeItemArray[i].str + "</option>");
						 $('#'+selectBoxName).append(option);
			  		 }else{
						 var option = $("<option value = "+ changeItemArray[i].codeD + ">" + changeItemArray[i].str + "</option>");
						 $('#'+selectBoxName).append(option);
			  		 }
			  }
		}
		
		function setDataPetKind(changeItemArray,codeM,codeD){
			
	          $('#petDetailKind').empty();
	          
	         if(codeM != 'O') {
	          $('#petDetailKind').append("<option value = '' disabled selected>품종</option>");
	          	 
	        	  for (var i = 0; i < changeItemArray.length; i++) {
	        		  if(codeD == changeItemArray[i].codeD){
	        			  var option = $("<option value = "+ changeItemArray[i].codeD + " selected >" + changeItemArray[i].str + "</option>");
	        			  $('#petDetailKind').append(option);
	        		  }else{
	        			  var option = $("<option value = "+ changeItemArray[i].codeD + ">" + changeItemArray[i].str + "</option>");
	        			  $('#petDetailKind').append(option);
	        		  }
				  }

	          }else{
	          	 $('#petDetailKind').replaceWith("<input class = 'form-control' type = 'text' id='petDetailKind' name='petDetailKindInput' placeholder='직접입력'>");
	          }
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
	    