<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
	Member m = (Member)request.getAttribute("m");
	String str= (String)request.getAttribute("str");
	System.out.println("str"+"m");
	
%>
<%=(str!=null)?str:"" %>

<div class="container pwd-update-container">
	<%if(m==null ){ %>
	<form action="<%=request.getContextPath()%>/member/UpdatePasswordEnd"
		method="POST" onsubmit="return password_validate();">

		<div class="form-group">
			<input type="text" class="form-control" id="memberId" name="memberId"
				value="" placeholder="아이디"> <small
				class="form-text text-muted">아이디를 입력해주세요.</small>
		</div>
		<div class="form-group">
			<input type="text" class="form-control" id="birth" name="birth"
				value="" placeholder="생년월일"> <small
				class="form-text text-muted">생년월일을 입력하세요.</small>
		</div>
		<div class="form-group">
			<input type="text" class="form-control" id="phone" name="phone"
				value="" placeholder="전화번호"> <small
				class="form-text text-muted">전화번호를 입력하세요.</small>
		</div>
		<button type="submit" class="btn btn-primary pwd-find-btn">비밀번호 변경</button>
	</form>
	<% }else {%>
	<form action="<%=request.getContextPath()%>/member/PasswordUpdateEnd"
		method="POST" onsubmit="return password2_validate();">
		<input type="hidden" value="<%=m.getMemberId() %>" name="memberId" />
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

		<button type="submit" class="btn btn-primary pwd-find-btn">비밀번호 변경</button>
	</form>
	<%} %>

</div>
<script>
function password_validate(){
	var memberId = $("#memberId").val().trim();
	var birth = $("#birth").val().trim();
	var phone = $("#phone").val().trim();
	
	if(memberId.length == 0){
		alert("아이디를 입력하세요.");
		return false;
	}
	if(birth.length==0){
		alert("생년월일을 입력하세요.");
		return false;
	}
	if(phone.length == 0){
		alert("전화번호를 입력하세요.");
		return false;
	}
		return true;
}

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









