<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
	Member m = (Member)request.getAttribute("member");
%>
<div class="container find-id-container">
<h2 class="main-color text-center page-title">아이디 찾기</h2>
<hr class="divide"/>
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
         <button type="submit" class="btn btn-primary find-id-btn btn-main-bg">확인</button>

	</form>
	
	<%if(m != null){ %>
	<div class="container">
		<h3 class="text-center">아이디 찾기</h3>
		<img src="/farm/images/user.png" alt="" class="rounded mx-auto d-block user-img">
		<h5 class="text-center comment-pwd">고객님의 <br>
		아이디 찾기가 완료 되었습니다.</h5>
		<h6 class="text-center">
			아이디 : <%=m.getMemberId() %>
		</h6>	

		<button type="button" class="btn btn-primary enroll-btn btn-main-bg" onclick="location.href='<%=request.getContextPath()%>/member/memberLogin'">로그인 하러가기</button>
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
<br>
<br>
<br>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
