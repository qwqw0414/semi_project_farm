<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>



<div class="container">
	  <form action="<%=request.getContextPath()%>/member/UpdatePasswordEnd" method="POST"
	  					onsubmit="return password_validate();">
	        <div class="form-group">
	            <input type="text" class="form-control" id="memberId" name="memberId" value="" placeholder="아이디">
	            <small class="form-text text-muted">아이디를 입력해주세요.</small>
	            
	        </div>
	        <div class="form-group">
	            <input type="text" class="form-control" id="birth" name="birth" value="" placeholder="생년월일">
	            <small class="form-text text-muted">생년월일을 입력하세요.</small>
	            
	        </div>
	        <div class="form-group">
	            <input type="password" class="form-control" id="pwd_new" name="pwd_new" placeholder="비밀번호">
	            <small class="form-text text-muted">변경할 비밀번호</small>
	        </div>
	         <div class="form-group">
	            <input type="password" class="form-control" id="pwd_check" name="pwd_check" placeholder="비밀번호">
	            <small class="form-text text-muted">비밀번호 확인</small><br />
	       		<button type="submit" class="btn btn-primary" >비밀번호 변경</button>
	        </div>
	        
	    </form>
	    
</div>
<script>
function password_validate(){
	var memberId = $("#memberId").val().trim();
	var birth = $("#birth").val().trim();
	var pwd_new = $("#pwd_new").val().trim();
	var pwd_chk = $("#pwd_check").val().trim();
	
	if($memberId){
		alert("성명을 입력하세요.");
		return false;
	}
	if($birth.length==0){
		alert("생년월일을 입력하세요.");
		return false;
	}
	if(pwd_new!=pwd_chk){
	    alert("입력한 비밀번호가 일치하지 않습니다.");
	    $("#pwd_new").select();
	    return false;
	}
	
	return true;
}

</script>





<%@ include file="/WEB-INF/views/common/footer.jsp"%>









