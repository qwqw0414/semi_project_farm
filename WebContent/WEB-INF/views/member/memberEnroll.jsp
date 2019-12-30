<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>



<div class="container memberEnroll">
    <form action="<%=request.getContextPath()%>/member/memberEnrollEnd" name="frmMemberEnroll" method="POST"
        onsubmit="return enrollValidate();">
        <div class="form-group">
            <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디" required>
            <small id="id-msg"></small>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" id="pwd" name="password" placeholder="비밀번호" required> <small
                id="pwd-msg"></small>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" id="pwdck" placeholder="비밀번호 확인" required> <small
                id="pwdchk-msg"></small>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="memberName" name="memberName" placeholder="성명" required>
            <small id="name-msg"></small>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="birth" name="birth" placeholder="생년월일" required
                maxlength="6"> <small id="msg-birth"></small>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" id="phone" name="phone" placeholder="연락처" required
                maxlength="11"> <small id="msg-phone"></small>
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
        <input type="hidden" id="id-duple-chk" value="0">
        <button type="submit" class="btn btn-block btn-primary">회원 가입</button>
    </form>
</div>

</body>

<script>
$(()=>{
var $memberId = $("#memberId");
var $memberId_msg = $("#memberId-msg");

$("#memberId").change(()=>{
    var memberId = $memberId.val();
    var regExp = /^[a-z][a-z\d]{3,14}$/;

    console.log(memberId);

    if(!regExp.test(memberId)){
        $memberId_msg.text("잘못된 아이디");
    }

    $.ajax({
        url: "<%=request.getContextPath()%>/member/MemberIdCheckServlet",
        data: { memberId: memberId },
        type: "post",
        success: function (result) {

        },
        error: (jqxhr, textStatus, errorThrown) => {
            console.log(jqxhr, textStatus, errorThrown);
        }
    });

});


});
// var regExp = /^[a-z][a-z\d]{3,14}$/; 아이디

// var regExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g; 비밀번호 regExp.test(val)

// var regExp = /^[가-힣]{2,5}$/; 성명

// var regExp = /([0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1]))/; 생년월일

// var regExp = /01{1}[016789]{1}[0-9]{7,8}/; 연락처


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