<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<h2 class="main-color text-center page-title">비밀번호 변경</h2>
<hr class="divide"/>
<div class="changePassword-container">
<div class="container">
	<form action="<%=request.getContextPath()%>/member/ChangePasswordEnd"
		method="POST" onsubmit="return password2_validate();">
		<div class="form-group">
		<input type="password" name="password" id="password" class="form-control" placeholder="현재 비밀번호" />
		<small class="form-text text-muted">현재 비밀번호</small>
		</div>
		<div class="form-group">
			<input type="password" class="form-control" id="pwd_new"
				name="pwd_new" placeholder="비밀번호"> <small
				class="form-text text-muted">변경할 비밀번호</small>
		</div>
		<div class="form-group">
			<input type="password" class="form-control" id="pwd_check"
				name="pwd_check" placeholder="비밀번호 확인"> <small
				class="form-text text-muted">비밀번호 확인</small><br />
		</div>
		<input type="hidden" name="memberId" value="<%=memberLoggedIn.getMemberId() %>" />
		<button type="submit" class="btn btn-primary btn-main-bg">비밀번호 변경</button>
	</form>
</div>
</div>
<script>








function password2_validate(){
	
	var pwd_new = $("#pwd_new").val().trim();
	var pwd_chk = $("#pwd_check").val().trim();
	
	if(pwd_new.length == 0){
		alert("변경할 비밀번호를 입력하세요.");
		return false;
	}
	if(pwd_chk.length == 0){
		alert("비밀번호 확인을 입력하세요.");
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