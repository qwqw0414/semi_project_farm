<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<% Member m = (Member)request.getAttribute("member"); %>
<div class="container">
    <form action="<%=request.getContextPath()%>/member/memberEnrollEnd" method="POST" onsubmit="return enrollValidate();">
        <div class="form-group">
            <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디" readonly value=<%=m.getMemberId() %>>
            <small class="form-text text-muted" >아이디를 입력해주세요.</small>
        </div>
       
       
        <div class="form-group">
            <input type="text"" class="form-control" id="memberName" name="memberName" placeholder="성명" value=<%=m.getMemberName() %> >
            <small class="form-text text-muted">성명을 입력해주세요.</small>
        </div>
        <div class="form-group">
            <input type="text"" class="form-control" id="birth" name="birth" placeholder="생년월일" value=<%=m.getBirth() %>>
            <small class="form-text text-muted">생년월일을 입력해주세요.</small>
        </div>
        <div class="form-group">
            <input type="text"" class="form-control" id="phone" name="phone" placeholder="연락처" value=<%=m.getPhone() %>>
            <small class="form-text text-muted">연락처를 입력해주세요.</small>
        </div>
        <div class="form-group">
            <input type="text"" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호" value=<%=m.getZipcode() %>>
            <small class="form-text text-muted">우편번호를 입력해주세요.</small>
        </div>
        <div class="form-group">
            <input type="text"" class="form-control" id="address" name="address" placeholder="나머지 주소" value=<%=m.getAddress() %>>
            <small class="form-text text-muted">나머지 주소를 입력해주세요.</small>
        </div>
        <button type="submit" class="btn btn-primary">수정 하기</button>
        <button type="submit" class="btn btn-primary">비밀번호 수정</button>
    </form>
</div>



<%@ include file="/WEB-INF/views/common/footer.jsp"%>