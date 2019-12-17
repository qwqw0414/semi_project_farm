<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="container">
	<form action="<%=request.getContextPath()%>/member/UpdatePasswordEnd"
		method="POST" onsubmit="return password_validate();">

	</form>
	<form action="<%=request.getContextPath()%>/member/PasswordUpdateEnd"
		method="POST" onsubmit="return password2_validate();">
		<input type="hidden" value="" name="memberId" />
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

		<button type="submit" class="btn btn-primary">비밀번호 변경</button>
	</form>

</div>













<%@ include file="/WEB-INF/views/common/footer.jsp"%>