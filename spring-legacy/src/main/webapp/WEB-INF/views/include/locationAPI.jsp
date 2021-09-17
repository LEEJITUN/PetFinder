<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<script>
    function daumPostcode() {
       new daum.Postcode({
            oncomplete: function (data) {
                console.log(data);
   
                $('#address').val(data.address);
                $('#sido').val(data.sido);
                $('#sigungu').val(data.sigungu);
            }
        }).open();
    }
</script>