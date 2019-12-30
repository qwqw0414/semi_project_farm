<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>



<div class="memberEnroll container">
    <div class="container">
        <form action="<%=request.getContextPath()%>/member/memberEnrollEnd" name="frmMemberEnroll" method="POST"
            onsubmit="return enrollValidate();">
            <div class="form-group">
                <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디" required>
                <small id="id_Msg">아이디를 입력해주세요.</small>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="pwd" name="password" placeholder="비밀번호" required> <small
                    id="pwd1_Msg">비밀번호를 입력해주세요.</small>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="pwdck" placeholder="비밀번호 확인" required> <small
                    id="pwd2_Msg">비밀번호를 한번 더 입력해주세요.</small>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="memberName" name="memberName" placeholder="성명" required>
                <small id="name_Msg">성명을 입력해주세요.</small>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="birth" name="birth" placeholder="생년월일" required
                    maxlength="6"> <small id="birth_Msg">생년월일을 입력해주세요.</small>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="phone" name="phone" placeholder="연락처" required
                    maxlength="11"> <small id="phone_Msg">연락처를 입력해주세요.</small>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <input type="text" class="form-control" id="zipCode" readonly name="zipcode" placeholder="우편번호"
                            required>
                    </div>
                    <input type="text" class="form-control" readonly name="addr" required>
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button" id="zipcodeSearchBtn"
                            id="button-addon2">우편번호 검색</button>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="address" name="address" placeholder="나머지 주소를 입력해주세요."
                    required>
            </div>
            <input type="hidden" id="hide" value="0">



            <button type="submit" class="btn btn-primary">회원 가입</button>
        </form>
    </div>

</div>




</body>

<script>
  









  //아이디 중복 검사 및 유효성 검사
  $('#memberId').blur(function() {
	  fn_idchk()
});

function fn_idchk(){
    var memberId = $('#memberId').val();
    console.log(memberId);

    // 유효성 검사
    var regExp = /^[a-z][a-z\d]{3,14}$/;
    if(!regExp.test(memberId)){
        $('.memberEnroll #memberId').focus();
        $('#memberId').addClass('is-invalid');
        $('#id_Msg').text("  *사용 할 수 없는 아이디 입니다.");
        $('#id_Msg').css("color", "red");
        if ($('#memberId').hasClass('is-valid')) {
            $('#memberId').removeClass('is-valid').addClass('is-invalid');
        }
        return;
    }


    $.ajax({
        url: "<%=request.getContextPath()%>/member/MemberIdCheckServlet",
        data: { memberId: memberId },
        type: "post",
        success: function (result) {
            var $memberId = $('#memberId');

            if ($('#memberId').val() == "") {
                alert("아이디를 입력해주세요.");
                return;
            }



            if (result <= 0) {
                $memberId.addClass('is-valid');
                $('#id_Msg').text("  *인증되었습니다.");
                $('#id_Msg').css("color", "green");
                if ($('#memberId').hasClass('is-invalid')) {
                    $('#memberId').removeClass('is-invalid').addClass('is-valid');
                }
                    $('#hide').val("1");
                    return true;
            }
            else {
                $('.memberEnroll #memberId').focus();
                $('#memberId').addClass('is-invalid');
                $('#id_Msg').text("  *중복된 아이디 입니다.");
                $('#id_Msg').css("color", "red");
                if ($('#memberId').hasClass('is-valid')) {
                    $('#memberId').removeClass('is-valid').addClass('is-invalid');
                }
                
                    if( $('#hide').val("1")){
                        $('#hide').val("0")
                    }

                return false;
            }
        },
        error: (jqxhr, textStatus, errorThrown) => {
            console.log(jqxhr, textStatus, errorThrown);
        }
    });
};





    //비밀번호 유효성 검사
    $('#pwd').blur(function() {
        fn_pwd1chk();
    });

    function fn_pwd1chk() {
        var regExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g;
        var val = $('#pwd').val();


        if (regExp.test(val)) {
            $('#pwd').addClass('is-valid');
            $('#pwd1_Msg').text("  *사용가능합니다.");
            $('#pwd1_Msg').css("color", "green");
            if ($('#pwd').hasClass('is-invalid')) {
                $('#pwd').removeClass('is-invalid').addClass('is-valid');
            }
            return true;
        }
        else if (!regExp.test(val)) {
            $('#pwd').focus();
            $('#pwd').addClass('is-invalid');
            $('#pwd1_Msg').text("  *사용할 수 없습니다.");
            $('#pwd1_Msg').css("color", "red");
            if ($('#pwd').hasClass('is-valid')) {
                $('#pwd').removeClass('is-valid').addClass('is-invalid');
            }
            return false;
        }
    }




    //비밀번호 확인 유효성 검사
    $('#pwdck').blur(function() {
        fn_pwd2chk();
    });
    function fn_pwd2chk() {

        var regExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g;
        var pwd1 = $('#pwd').val();
        var pwd2 = $('#pwdck').val();

        if (pwd1 == pwd2) {
            $('#pwdck').addClass('is-valid');
            $('#pwd2_Msg').text("  *일치합니다.");
            $('#pwd2_Msg').css("color", "green");
            if ($('#pwdck').hasClass('is-invalid')) {
                $('#pwdck').removeClass('is-invalid').addClass('is-valid');
            }
                return true;

        }

        else if (!regExp.test(pwd1) || pwd1 != pwd2) {
            $('#pwdck').focus();
            $('#pwdck').addClass('is-invalid');
            $('#pwd2_Msg').text("  *비밀번호가 일치 하지 않습니다.");
            $('#pwd2_Msg').css("color", "red");
            if ($('#pwdck').hasClass('is-valid')) {
                $('#pwdck').removeClass('is-valid').addClass('is-invalid');
            }
            return false;
        }
    }




    //이름 유효성 검사
    $('#memberName').blur(function() {
        fn_namechk();
    });

    function fn_namechk() {

        var regExp = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,5}$/;
        var val = $('#memberName').val();



        if (regExp.test(val)) {
            $('#memberName').addClass('is-valid');
            $('#name_Msg').text("  *확인 되었습니다.");
            $('#name_Msg').css("color", "green");
            if ($('#memberName').hasClass('is-invalid')) {
                $('#memberName').removeClass('is-invalid').addClass('is-valid');
            }
            return true;

        }
        else if (!regExp.test(val)) {
            $('#memberName').focus();
            $('#memberName').addClass('is-invalid');
            $('#name_Msg').text("  *다시 입력해주세요.");
            $('#name_Msg').css("color", "red");
            if ($('#memberName').hasClass('is-valid')) {
                $('#memberName').removeClass('is-valid').addClass('is-invalid');
            }
            return false;
        }
    }




    //생년월일 유효성검사
    $('#birth').blur(function() {
        fn_birthchk();
    });


    function fn_birthchk() {
        var regExp = /([0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1]))/;
        var val = $('#birth').val();

        if (regExp.test(val)) {
            $('#birth').addClass('is-valid');
            $('#birth_Msg').text("  *확인 되었습니다.");
            $('#birth_Msg').css("color", "green");


            if ($('#birth').hasClass('is-invalid')) {
                $('#birth').removeClass('is-invalid').addClass('is-valid');
            }
            return true;

        }
        else if (!regExp.test(val)) {
            $('#birth').focus();
            $('#birth').addClass('is-invalid');
            $('#birth_Msg').text("  *다시 입력해주세요.");
            $('#birth_Msg').css("color", "red");
            if ($('#birth').hasClass('is-valid')) {
                $('#birth').removeClass('is-valid').addClass('is-invalid');
            }
            return false;
        }
    }




    //핸드폰번호 유효성검사
    $('#phone').blur(function() {
        fn_phonechk();
    });

    function fn_phonechk() {
        var regExp = /01{1}[016789]{1}[0-9]{7,8}/;
        var val = $('#phone').val();

        if (regExp.test(val)) {
            $('#phone').addClass('is-valid');
            $('#phone_Msg').text("  *확인 되었습니다.");
            $('#phone_Msg').css("color", "green");
            if ($('#phone').hasClass('is-invalid')) {
                $('#phone').removeClass('is-invalid').addClass('is-valid');
            }
            return true;

        }
        else if (!regExp.test(val)) {
            $('#phone').focus();
            $('#phone').addClass('is-invalid');
            $('#phone_Msg').text("  *다시 입력해주세요.");
            $('#phone_Msg').css("color", "red");
            if ($('#phone').hasClass('is-valid')) {
                $('#phone').removeClass('is-valid').addClass('is-invalid');
            }
            return false;
        }
    };


   
   
   
   
    //우편번호 유효성 검사
$('#zipCode').blur(function(){
    fn_zipcodechk();
});

function fn_zipcodechk(){
    var val = $('#zipCode').val();

if(val.trim().length==0){
    // $('#address').focus();
    alert("우편번호를 입력 해주세요");
    openzipcode();
    return false;
}
else return true;
} 






    //상세주소 유효성 검사
    $('#address').blur(function() {
        fn_addresschk();
    });


    function fn_addresschk(){
        var val = $('#address').val();

        if(val.trim().length==0){
            // $('#address').focus();
            alert("상세주소를 입력 해주세요")
            return false;
        }
        else return true;
    }


  




function openzipcode(){
    var url = "<%=request.getContextPath()%>/zipcode/search";
        var title = "우편번호 검색";
        var width = 600;
        var height = 400;
        var left = Math.ceil((window.screen.width - width) / 2);
        var top = Math.ceil((window.screen.height - height) / 2);

        open(url, title, "left=" + left + "px, top=" + top + "px, width=" + width + "px, height=" + height + "px");
}





    $('#zipcodeSearchBtn').click(function() {
       openzipcode();
    });








    function enrollValidate() {
        if ($('#hide').val()==0)
            return false;
        if (!fn_pwd1chk())
            return false;
        if (!fn_pwd2chk())
            return false;
        if (!fn_namechk())
            return false;
        if (!fn_birthchk())
            return false;
        if (!fn_phonechk())
            return false;

        fn_idchk();
        fn_pwd2chk();
        fn_namechk();
        fn_birthchk();
        fn_phonechk();
        fn_addresschk();
        fn_zipcodechk();
        fn_addresschk();

        return true;




        var $memberId = $("#memberId");
        if ($memberId.val().trim().length < 4) {
            alert("아이디는 4글자 이상 가능합니다.");
            return false;
        }

        var $pwd = $("#pwd");
        if ($pwd.val().trim().length < 4) {
            alert("비밀번호는 4글자 이상 가능합니다.");
            return false;
        }

        var $pwdck = $("#pwdck");
        if ($pwdck.val() != $pwd.val()) {
            alert("비밀번호가 비일치");
            return false;
        }

        var $memberName = $("#memberName");
        if ($memberName.val().trim().length < 2) {
            alert("성명을 입력해주세요.");
            return false;
        }

        return true;
    }
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>