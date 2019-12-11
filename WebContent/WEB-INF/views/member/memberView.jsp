<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<% Member m = (Member)request.getAttribute("member"); %>
<div class="container">
    <form action="<%=request.getContextPath()%>/member/memberEnrollEnd" method="POST" onsubmit="return enrollValidate();">
        <div class="form-group">
            <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디">
            <small class="form-text text-muted">아이디를 입력해주세요.</small>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" id="pwd" name="password" placeholder="비밀번호">
            <small class="form-text text-muted">비밀번호를 입력해주세요.</small>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" id="pwdck" placeholder="비밀번호 확인">
            <small class="form-text text-muted">비밀번호를 한번 더 입력해주세요.</small>
        </div>
        <div class="form-group">
            <input type="text"" class="form-control" id="memberName" name="memberName" placeholder="성명">
            <small class="form-text text-muted">성명을 입력해주세요.</small>
        </div>
        <div class="form-group">
            <input type="text"" class="form-control" id="birth" name="birth" placeholder="생년월일">
            <small class="form-text text-muted">생년월일을 입력해주세요.</small>
        </div>
        <div class="form-group">
            <input type="text"" class="form-control" id="phone" name="phone" placeholder="연락처">
            <small class="form-text text-muted">연락처를 입력해주세요.</small>
        </div>
        <div class="form-group">
            <input type="text"" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호">
            <small class="form-text text-muted">우편번호를 입력해주세요.</small>
        </div>
        <div class="form-group">
            <input type="text"" class="form-control" id="address" name="address" placeholder="나머지 주소">
            <small class="form-text text-muted">나머지 주소를 입력해주세요.</small>
        </div>
        <button type="submit" class="btn btn-primary">회원 가입</button>
    </form>
</div>



<%@ include file="/WEB-INF/views/common/footer.jsp"%>