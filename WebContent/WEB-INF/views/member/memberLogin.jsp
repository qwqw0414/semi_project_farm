<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<div class="container">
	  <form action="<%=request.getContextPath()%>/member/memberLoginEnd" method="POST" onsubmit="return loginValidate();">
	        <div class="form-group">
	            <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디">
	            <small class="form-text text-muted">아이디를 입력해주세요.</small>
	            <input type="checkbox" name="saveId" id="saveId" />아이디 저장
	        </div>
	        <div class="form-group">
	            <input type="password" class="form-control" id="pwd" name="password" placeholder="비밀번호">
	            <small class="form-text text-muted">비밀번호를 입력해주세요.</small>
	        </div>
	        <button type="submit" class="btn btn-primary">로그인</button>
	        <button type="button" class="btn btn-primary">아이디 찾기</button>
        </form>
</div>
<script>
function loginValidate() {
	var $memberId = $("#memberId");
	var $password = $("#pwd");
	
	if($memberId.val().trim().length==0){
		alert("아이디를 입력하세요.");
		return false;
	}
	if($password.val().trim().length==0){
		alert("비밀번호를 입력하세요.");
		return false;
	}
	return true;
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>