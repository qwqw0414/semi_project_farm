<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<% Member m = (Member)request.getAttribute("member"); %>
<div class="container">
    <form action="<%=request.getContextPath()%>/member/memberEnrollEnd" method="POST" onsubmit="return enrollValidate();">
        <div class="form-group">
            <small class="form-text text-muted" >아이디</small>
            <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디" readonly value=<%=m.getMemberId() %>>
        </div>
        <div class="form-group">
            <small class="form-text text-muted">성명</small>
            <input type="text"" class="form-control" id="memberName" name="memberName" placeholder="성명" value=<%=m.getMemberName() %> >
        </div>
        <div class="form-group">
            <small class="form-text text-muted">생년월일</small>
            <input type="text"" class="form-control" id="birth" name="birth" placeholder="생년월일" value=<%=m.getBirth() %>>
        </div>
        <div class="form-group">
            <small class="form-text text-muted">연락처</small>
            <input type="text"" class="form-control" id="phone" name="phone" placeholder="연락처" value=<%=m.getPhone() %>>
        </div>
        <div class="form-group">
            <small class="form-text text-muted">우편번호</small>
            <input type="text"" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호" value=<%=m.getZipcode() %>>
        </div>
        <div class="form-group">
            <small class="form-text text-muted">상세주소</small>
            <input type="text"" class="form-control" id="address" name="address" placeholder="나머지 주소" value=<%=m.getAddress() %>>
        </div>
        <button type="submit" class="btn btn-primary">수정 하기</button>
        <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/member/ChangePassword?memberId=<%=m.getMemberId()%>'">비밀번호 수정</button>
        <button type="button" class="btn btn-primary btn-member-delete" onclick="return confirm_delete()">회원탈퇴</button>
    </form>
</div>


<script>
function confirm_delete(){
	var bool = confirm("정말로 탈퇴하시겠습니까?")
	
	if(bool){
		location.href='<%=request.getContextPath()%>/member/memberDelete?memberId=<%=m.getMemberId()%>'
	}
}



</script>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>