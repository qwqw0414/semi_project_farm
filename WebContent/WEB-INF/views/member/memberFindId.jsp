<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
	Member m = (Member)request.getAttribute("member");
%>

<div class="container">
	<form action="<%=request.getContextPath()%>/member/memberFindIdEnd"
		method="POST" onsubmit="return FindIdValidate();" <%=(m!=null)?"hidden":""%>>
		<div class="form-group">
			<input type="text" class="form-control" id="memberName"
				name="memberName" placeholder="성명"> <small
				class="form-text text-muted">성명을 입력해주세요.</small>

		</div>
		<div class="form-group">
			<input type="text" class="form-control" id="birth" name="birth"
				placeholder="생년월일"> <small class="form-text text-muted">생년월일을
				입력해주세요.</small>
		</div>
		<div class="form-group">
            <input type="text" class="form-control" id="phone" name="phone" placeholder="연락처">
            <small class="form-text text-muted">연락처를 입력해주세요.</small>
        </div>
         <button type="submit" class="btn btn-primary">아이디 찾기</button>

	</form>
	
	<%if(m != null){ %>
	<div>
		찾은 아이디는 <%=m.getMemberId() %>입니다.
	</div>
	
	<%} %>

</div>
<script>
function FindIdValidate(){
	var $memberName = $("#memberName");
	var $birth = $("#birth");
	var $phone = $("#phone");
	
	if($memberName.val().trim().length==0){
		alert("성명을 입력하세요.");
		return false;
	}
	if($birth.val().trim().length==0){
		alert("생년월일을 입력하세요.");
		return false;
	}
	if($phone.val().trim().length==0){
		alert("연락처를 입력하세요.");
		return false;
	}
}

</script>







<%@ include file="/WEB-INF/views/common/footer.jsp"%>











