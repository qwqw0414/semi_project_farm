<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="memberEnroll" style="margin: auto;">

    <div><h2 class="text-center">회원가입</h2><br /></div>    
    <form action="<%=request.getContextPath()%>/member/memberEnrollEnd" name="frmMemberEnroll" method="POST"
        onsubmit="return enrollValidate()">
        <div class="form-group">
            <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디">
            <div id="id-msg" class="invalid-feedback"></div>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" id="pwd" name="password" placeholder="비밀번호"> 
            <div id="pwd-msg"></div>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" id="pwdck" placeholder="비밀번호 확인"> 
            <div id="pwdck-msg"></div>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="memberName" name="memberName" placeholder="성명">
            <div id="name-msg"></div>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="birth" name="birth" placeholder="생년월일"
                maxlength="6"> 
            <div id="birth-msg"></div>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="phone" name="phone" placeholder="연락처"
                maxlength="11"> 
            <div id="phone-msg"></div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-prepend">
                    <input type="text" class="form-control" id="zipCode" readonly name="zipcode" placeholder="우편번호">
                </div>
                <input type="text" class="form-control" readonly name="addr" required>
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" id="zipcodeSearchBtn">우편번호 검색</button>
                </div>
            </div>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="address" name="address" placeholder="나머지 주소를 입력해주세요.">
            <div id="address-msg"></div>
        </div>
        <input type="hidden" id="id-duple-chk" value="0">
        <button type="submit" class="btn btn-block btn-primary btn-main-bg">회원 가입</button>
    </form>
</div>

</body>

<script>

{
var valid = "form-control is-valid";
var invalid = "form-control is-invalid";
var validMsg = "valid-feedback";
var invalidMsg = "invalid-feedback";
var $memberId = $(".memberEnroll #memberId");
var $memberId_msg = $(".memberEnroll #id-msg");
var $idDupleChk = $(".memberEnroll #id-duple-chk");
var $pwd = $(".memberEnroll #pwd");
var $pwd_msg = $(".memberEnroll #pwd-msg");
var $pwdck = $(".memberEnroll #pwdck");
var $pwdck_msg = $(".memberEnroll #pwdck-msg");
var $name = $(".memberEnroll #memberName");
var $name_msg = $(".memberEnroll #name-msg");
var $birth = $(".memberEnroll #birth");
var $birth_msg = $(".memberEnroll #birth-msg");
var $phone = $(".memberEnroll #phone");
var $phone_msg = $(".memberEnroll #phone-msg");
var $zipcode = $(".memberEnroll #zipCode");
var $address = $(".memberEnroll #address");
var $address_msg = $(".memberEnroll #address-msg");

//이벤트 처리
$($pwd).change(()=>{valid_pwd();});
$($pwdck).change(()=>{valid_pwdck();});
$($name).change(()=>{valid_name();});
$($birth).change(()=>{valid_birth();});
$($phone).change(()=>{valid_phone();});
$($address).change(()=>{valid_address();});

//회원가입 유효성 검사
function enrollValidate(){

    //아이디 검사
    if($idDupleChk.val() == 0){
        $memberId.attr("class",invalid);
        $memberId_msg.attr("class",invalidMsg);
        $memberId_msg.text("아이디를 입력해주세요.");
        $memberId.focus();
        return false;
    }

    //비밀번호 검사
    if(!valid_pwd())
        return false;

    //비밀번호 확인
    if($pwdck.val().length == 0 || !valid_pwdck())
        return false;

    //성명 확인
    if($name.val().length == 0 || !valid_name())
        return false;

    //생년월일 확인
    if($birth.val().length == 0 || !valid_birth())
        return false;

    //연락처 확인
    if($phone.val().length == 0 || !valid_phone())
        return false;

    if($zipcode.val().length == 0 || $address.val().length == 0 || !valid_address()){
        return false;
    }

    return true;
}

function valid_pwd(){
    var regExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g;
    if(!regExp.test($pwd.val())){
        $pwd.attr("class",invalid);
        $pwd_msg.attr("class",invalidMsg);
        $pwd_msg.text("비밀번호를 영문자 + 숫자 + 특수문자 조합");
        $pwd.focus();
        return false;
    }else{
        $pwd.attr("class",valid);
        $pwd_msg.attr("class",validMsg);
        $pwd_msg.text("사용가능한 비밀번호");
        $pwdck.focus();
        return true;
    }
}
function valid_pwdck(){
    if($pwd.val() !== $pwdck.val()){
        $pwdck.attr("class",invalid);
        $pwdck_msg.attr("class",invalidMsg);
        $pwdck_msg.text("비밀번호를 다시 확인해주세요.");
        $pwdck.focus();
        return false;
    }else{
        $pwdck.attr("class",valid);
        $pwdck_msg.attr("class",validMsg);
        $pwdck_msg.text("비밀번호가 동일합니다.");
        $name.focus();
        return true;
    }
}
function valid_name(){
    var regExp = /^[가-힣]{2,5}$/;
    if(!regExp.test($name.val())){
        $name.attr("class",invalid);
        $name_msg.attr("class",invalidMsg);
        $name_msg.text("다시 확인해주세요.");
        $name.focus();
        return false;
    }else{
        $name.attr("class",valid);
        $birth.focus();
        return true;
    }
}
function valid_birth(){
    var regExp = /([0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1]))/;
    if(!regExp.test($birth.val())){
        $birth.attr("class",invalid);
        $birth_msg.attr("class",invalidMsg);
        $birth_msg.text("생년월일 6자리를 입력해주세요.");
        $birth.focus();
        return false;
    }else{
        var birth = $birth.val();
        $birth.attr("class",valid);
        $birth_msg.attr("class",validMsg);
        $birth_msg.text(birth.substr(0,2)+"년 "+birth.substr(2,2)+"월 "+birth.substr(4,2)+"일");
        $phone.focus();
        return true;
    }
}
function valid_phone(){
    var regExp = /01{1}[016789]{1}[0-9]{7,8}/;
    if(!regExp.test($phone.val())){
        $phone.attr("class",invalid);
        $phone_msg.attr("class",invalidMsg);
        $phone_msg.text("잘못된 전화번호");
        $phone.focus();
        return false;
    }else{
        $phone.attr("class",valid);
        // $phone.focus();
        return true;
    }
}

function valid_address(){
    if($zipcode.val().length == 0 || $address.val().length == 0){
        $address.attr("class",invalid);
        $address_msg.attr("class",invalidMsg);
        $address_msg.text("우편번호와 나머지주소를 정확하게 적어주세요.");
        $address.focus();
        return false;
    }else{
        $address.attr("class",valid);
        return true;
    }
}

//아이디 중복 검사
$("#memberId").change(()=>{
    var regExp = /^[a-z][a-z\d]{3,14}$/;

    if(!regExp.test($memberId.val())){
        $memberId.attr("class",invalid);
        $memberId_msg.attr("class",invalidMsg);
        $memberId_msg.text("잘못된 아이디");
        return;
    }

    $.ajax({
        url: "<%=request.getContextPath()%>/member/MemberIdCheckServlet",
        data: { memberId: $memberId.val() },
        type: "post",
        success: function (result) {
            if(result == 0){
                $memberId.attr("class",valid);
                $memberId_msg.attr("class",validMsg);
                $memberId_msg.text("사용가능한 아이디");
                $idDupleChk.val(1);
                $pwd.focus();
            }
            else{
                $memberId.attr("class",invalid);
                $memberId_msg.attr("class",invalidMsg);
                $memberId_msg.text("중복된 아이디");
                $idDupleChk.val(0);
            }
        },
        error: (jqxhr, textStatus, errorThrown) => {
            console.log(jqxhr, textStatus, errorThrown);
        }
    });
});
}

$('#zipcodeSearchBtn').click(function() {
    var url = "<%=request.getContextPath()%>/zipcode/search";
    var title = "우편번호 검색";
    var width = 600;
    var height = 400;
    var left = Math.ceil((window.screen.width - width) / 2);
    var top = Math.ceil((window.screen.height - height) / 2);

    open(url, title, "left=" + left + "px, top=" + top + "px, width=" + width + "px, height=" + height + "px");
});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>