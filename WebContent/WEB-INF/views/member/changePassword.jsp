<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="container">
	<form action="<%=request.getContextPath()%>/member/ChangePasswordEnd"
		method="POST" onsubmit="return password2_validate();">
		<div class="form-group">
		<input type="password" name="password" id="password" class="form-control" placeholder="현재 비밀번호" />
		<small class="form-text text-muted">현재 비밀번호</small>
		</div>
		<div class="form-group">
			<input type="password" class="form-control" id="pwd_new"
				name="pwd_new" placeholder="변경할 비밀번호">
				<small id="pwd1_Msg"> 변경할 비밀번호를 입력해주세요.</small>
		</div>
		<div class="form-group">
			<input type="password" class="form-control" id="pwd_check"
				name="pwd_check" placeholder="비밀번호 확인"><small
				id="pwd2_Msg">변경할 비밀번호를 한번 더 입력해주세요.</small><br />
		</div>
		<input type="hidden" name="memberId" value="<%=memberLoggedIn.getMemberId() %>" />
		<button type="submit" class="btn btn-primary">비밀번호 변경</button>
	</form>

</div>
<script>
 //비밀번호 유효성 검사
 $('#pwd_new').blur(function pwd1chk() {
        fn_pwd1chk();
    });

    function fn_pwd1chk() {
        var regExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g;
        var val = $('#pwd_new').val();


        if (regExp.test(val)) {
            $('#pwd_new').addClass('is-valid');
            $('#pwd1_Msg').text("  *사용가능합니다.");
            $('#pwd1_Msg').css("color", "green");
            if ($('#pwd_new').hasClass('is-invalid')) {
                $('#pwd_new').removeClass('is-invalid').addClass('is-valid');
            }
            return true;
        }
        else if (!regExp.test(val)) {
            $('#pwd_new').addClass('is-invalid');
            $('#pwd1_Msg').text("  *사용할 수 없습니다.");
            $('#pwd1_Msg').css("color", "red");
            if ($('#pwd_new').hasClass('is-valid')) {
                $('#pwd_new').removeClass('is-valid').addClass('is-invalid');
            }
            return false;
        }
    }




    //비밀번호 확인 유효성 검사
    $('#pwd_check').blur(function pwd2chk() {
        fn_pwd2chk();
    });
    function fn_pwd2chk() {

        var regExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g;
        var pwd1 = $('#pwd_new').val();
        var pwd2 = $('#pwd_check').val();

        if (pwd1 == pwd2) {
            $('#pwd_check').addClass('is-valid');
            $('#pwd2_Msg').text("  *일치합니다.");
            $('#pwd2_Msg').css("color", "green");
            if ($('#pwd_check').hasClass('is-invalid')) {
                $('#pwd_check').removeClass('is-invalid').addClass('is-valid');
            }

        }

        else if (!regExp.test(pwd1) || pwd1 != pwd2) {
            $('#pwd_check').addClass('is-invalid');
            $('#pwd2_Msg').text("  *비밀번호가 일치 하지 않습니다.");
            $('#pwd2_Msg').css("color", "red");
            if ($('#pwd_check').hasClass('is-valid')) {
                $('#pwd_check').removeClass('is-valid').addClass('is-invalid');
            }
            return false;
        }
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