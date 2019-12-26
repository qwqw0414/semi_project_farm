<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="container">
        <form action="<%=request.getContextPath()%>/member/memberEnrollEnd" name="frmMemberEnroll" method="POST"
            onsubmit="return enrollValidate();">
            <div class="form-group">
                <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디" required>
                <small class="form-text text-muted">아이디를 입력해주세요.</small>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="pwd" name="password" placeholder="비밀번호" required>
                <small class="form-text text-muted">비밀번호를 입력해주세요.</small>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="pwdck" placeholder="비밀번호 확인" required>
                <small class="form-text text-muted">비밀번호를 한번 더 입력해주세요.</small>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="memberName" name="memberName" placeholder="성명" required>
                <small class="form-text text-muted">성명을 입력해주세요.</small>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="birth" name="birth" placeholder="생년월일" required maxlength="6">
                <small class="form-text text-muted">생년월일을 입력해주세요.</small>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="phone" name="phone" placeholder="연락처" required maxlength="11">
                <small class="form-text text-muted">연락처를 입력해주세요.</small>
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
            <button type="submit" class="btn btn-primary">회원 가입</button>
        </form>
    </div>






</body>

<script>
    //유효성 검사
    // function enrollValidate(){
    //     var memberId = document.getElementById("memberId");
    //     var memberPwd1 = document.getElementById("pwd");
    //     var memberPwd2 = document.getElementById("pwdck")
    //     var memberName = document.getElementById("memberName");
    //     var birth = document.getElementById("birth");
    //     var phone = document.getElementById("phone");
    //     var zipCode = document.getElementById("zipCode");
    //     var address = document.getElementById("address");


    //     //아이디 유효성 검사
    //     //알파벳 소문자로 시작하고 숫자가 하나이상 조합된 4~12자
    //     var regExp1 = /^[a-z][a-z\d]{3,11}$/;
    //     var regExp2 = /[0-9]/;
    //     var idVal = memberId.val;

    //     if(regExp1.test(idVal)){


    //     }

    // }




    //아이디 유효성 검사 일다 재껴
    // $('#memberId').blur(function(){
    //     var regExp = /^[a-z][a-z\d]{3,14}$/;
    //     var val = $('#memberId').val();
    //     console.log(val);

    //      //기본 class -> 한번에 성공시    
    //      if(regExp.test(val)){
    //          $('#memberId').removeClass('form-control').addClass('form-control is-valid');
    //      }






    // });



    //비밀번호 유효성 검사
    $('#pwd').blur(function () {
        var regExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g;
        var val = $('#pwd').val();


        if (regExp.test(val)) {
            $('#pwd').addClass('is-valid');
            if ($('#pwd').hasClass('is-invalid')) {
                $('#pwd').removeClass('is-invalid').addClass('is-valid');
            }

        }
        else if (!regExp.test(val)) {
            $('#pwd').addClass('is-invalid');
            if ($('#pwd').hasClass('is-valid')) {
                $('#pwd').removeClass('is-valid').addClass('is-invalid');
            }
        }
    });





    //비밀번호 확인 유효성 검사
    $('#pwdck').blur(function () {
        var regExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g;
        var pwd1 = $('#pwd').val();
        var pwd2 = $('#pwdck').val();





        if (pwd1 == pwd2) {
            $('#pwdck').addClass('is-valid');
            if ($('#pwdck').hasClass('is-invalid')) {
                $('#pwdck').removeClass('is-invalid').addClass('is-valid');
            }
        }

        else if (!regExp.test(pwd1) || pwd1 != pwd2) {
            $('#pwdck').addClass('is-invalid');
            if ($('#pwdck').hasClass('is-valid')) {
                $('#pwdck').removeClass('is-valid').addClass('is-invalid');
            }
        }
    });




    //이름 유효성 검사
    $('#memberName').blur(function () {
        var regExp = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,5}$/;
        var val = $('#memberName').val();



        if (regExp.test(val)) {
            $('#memberName').addClass('is-valid');
            if ($('#memberName').hasClass('is-invalid')) {
                $('#memberName').removeClass('is-invalid').addClass('is-valid');
            }

        }
        else if (!regExp.test(val)) {
            $('#memberName').addClass('is-invalid');
            if ($('#memberName').hasClass('is-valid')) {
                $('#memberName').removeClass('is-valid').addClass('is-invalid');
            }
        }
    });



    //생년월일 유효성검사
    $('#birth').blur(function () {
    var regExp=/([0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1]))/;
    var val =$('#birth').val(); 

    if (regExp.test(val)) {
            $('#birth').addClass('is-valid');
            if ($('#birth').hasClass('is-invalid')) {
                $('#birth').removeClass('is-invalid').addClass('is-valid');
            }

        }
        else if (!regExp.test(val)) {
            $('#birth').addClass('is-invalid');
            if ($('#birth').hasClass('is-valid')) {
                $('#birth').removeClass('is-valid').addClass('is-invalid');
            }
        }

    });



    //핸드폰번호 유효성검사
    $('#phone').blur(function () {
        var regExp=/01{1}[016789]{1}[0-9]{7,8}/;
        var val =$('#phone').val(); 

        if (regExp.test(val)) {
            $('#phone').addClass('is-valid');
            if ($('#phone').hasClass('is-invalid')) {
                $('#phone').removeClass('is-invalid').addClass('is-valid');
            }

        }
        else if (!regExp.test(val)) {
            $('#phone').addClass('is-invalid');
            if ($('#phone').hasClass('is-valid')) {
                $('#phone').removeClass('is-valid').addClass('is-invalid');
            }
        }    





    });




























    zipcodeSearchBtn.onclick = function () {
        var url = "<%=request.getContextPath()%>/zipcode/search";
        var title = "우편번호 검색";
        var width = 600;
        var height = 400;
        var left = Math.ceil((window.screen.width - width) / 2);
        var top = Math.ceil((window.screen.height - height) / 2);

        open(url, title, "left=" + left + "px, top=" + top + "px, width=" + width + "px, height=" + height + "px");
    }

    function enrollValidate() {

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