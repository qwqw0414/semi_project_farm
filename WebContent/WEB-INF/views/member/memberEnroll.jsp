<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="container">
    <form action="<%=request.getContextPath()%>/member/memberEnrollEnd" name="frmMemberEnroll" method="POST" onsubmit="return enrollValidate();">
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
            <input type="text" class="form-control" id="memberName" name="memberName" placeholder="성명">
            <small class="form-text text-muted">성명을 입력해주세요.</small>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="birth" name="birth" placeholder="생년월일">
            <small class="form-text text-muted">생년월일을 입력해주세요.</small>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="phone" name="phone" placeholder="연락처">
            <small class="form-text text-muted">연락처를 입력해주세요.</small>
        </div>
		<div class="form-group">
			<div class="input-group">
                <div class="input-group-prepend">
                    <input type="text" class="form-control" disabled="disabled" name="zipcode" placeholder="우편번호">
                </div>
				<input type="text" class="form-control" disabled="disabled" name="addr">
				<div class="input-group-append">
					<button class="btn btn-outline-secondary" type="button" id="zipcodeSearchBtn"
						id="button-addon2">우편번호 검색</button>
				</div>
			</div>
		</div>
		<div class="form-group">
            <input type="text" class="form-control" id="address" name="address" placeholder="나머지 주소를 입력해주세요.">
        </div>
        <button type="submit" class="btn btn-primary">회원 가입</button>
    </form>
</div>
<script>
zipcodeSearchBtn.onclick = function(){
	var url = "<%=request.getContextPath()%>/zipcode/search";
	var title = "우편번호 검색";
    var width = 600;
    var height = 400;
    var left = Math.ceil((window.screen.width - width)/2);
    var top = Math.ceil((window.screen.height - height)/2);
    
    open(url,title,"left="+left+"px, top="+top+"px, width="+width+"px, height="+height+"px");
}

function enrollValidate(){

	var $memberId = $("#memberId");
	if($memberId.val().trim().length < 4){
		alert("아이디는 4글자 이상 가능합니다.");
		return false;
	}
	
	var $pwd = $("#pwd");
	if($pwd.val().trim().length < 4){
		alert("비밀번호는 4글자 이상 가능합니다.");
		return false;
	}

       var $pwdck = $("#pwdck");
	if($pwdck.val() != $pwd.val()){
		alert("비밀번호가 비일치");
		return false;
	}

       var $memberName = $("#memberName");
	if($memberName.val().trim().length < 2){
		alert("성명을 입력해주세요.");
		return false;
	}

	return true;
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>