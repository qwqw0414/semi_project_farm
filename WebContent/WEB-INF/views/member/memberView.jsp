<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<% Member m = (Member)request.getAttribute("member"); %>
<div class="memberView-container mx-auto">
<h1 class="main-color text-center page-title">마이 페이지</h1>
<hr class="divide"/>
<div class="container">
    <form action="<%=request.getContextPath()%>/member/memberUpdate" method="POST" onsubmit="return enrollValidate();">
        <div class="form-group">
            <small class="form-text text-muted" >아이디</small>
            <input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디" readonly value=<%=m.getMemberId() %>>
        </div>
        <div class="form-group">
                <small id="name_Msg">성명</small>
                <input type="text" class="form-control" id="memberName" name="memberName" placeholder="성명" required value=<%=m.getMemberName() %> >
        </div>
        <div class="form-group">
            <small class="form-text text-muted">생년월일</small>
            <input type="text"" class="form-control" id="birth" name="birth" placeholder="생년월일"  readonly value=<%=m.getBirth() %>>
        </div>
        <div class="form-group">
            <small id="phone_Msg">연락처</small>
                <input type="text" class="form-control" id="phone" name="phone" placeholder="연락처" required  value=<%=m.getPhone() %>
                    maxlength="11"> 
            </div>
            <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <input type="text" class="form-control" id="zipCode" readonly name="zipcode" placeholder="우편번호"
                                required>
                        </div>
                        <input type="text" class="form-control" readonly name="addr" id="addr" value='<%=m.getZipcode() %>' required>
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="button" id="zipcodeSearchBtn"
                                id="button-addon2">우편번호 검색</button>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="address" name="address" value='<%=m.getAddress() %>'
                        required>
                </div>
         <div class="text-center memberView-button">
        <button type="submit" class="btn btn-primary btn-main-bg">수정 하기</button>
        <%if(memberLoggedIn.getMemberId().equals(m.getMemberId())){ %>
        <button type="button" class="btn btn-primary btn-main-bg" onclick="location.href='<%=request.getContextPath()%>/member/ChangePassword?memberId='">비밀번호 수정</button>
        <%} %>
        <button type="button" class="btn btn-primary btn-member-delete btn-sub-bg" onclick="return confirm_delete()">회원탈퇴</button>
    	</div>
    </form>
</div>
</div>

<script>
$('#zipcodeSearchBtn').click(function() {
    var url = "<%=request.getContextPath()%>/zipcode/search";
    var title = "우편번호 검색";
    var width = 600;
    var height = 400;
    var left = Math.ceil((window.screen.width - width) / 2);
    var top = Math.ceil((window.screen.height - height) / 2);

    open(url, title, "left=" + left + "px, top=" + top + "px, width=" + width + "px, height=" + height + "px");
});

function confirm_delete(){
	var bool = confirm("정말로 탈퇴하시겠습니까?")
	
	if(bool){
		location.href='<%=request.getContextPath()%>/member/memberDelete?memberId=<%=m.getMemberId()%>'
	}
}
//이름 유효성 검사
$('#memberName').blur(function namechk() {
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

        }
        else if (!regExp.test(val)) {
            $('#memberName').addClass('is-invalid');
            $('#name_Msg').text("  *다시 입력해주세요.");
            $('#name_Msg').css("color", "red");
            if ($('#memberName').hasClass('is-valid')) {
                $('#memberName').removeClass('is-valid').addClass('is-invalid');
            }
            return false;
        }
    }


//핸드폰번호 유효성검사
$('#phone').blur(function phonechk() {
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

        }
        else if (!regExp.test(val)) {
            $('#phone').addClass('is-invalid');
            $('#phone_Msg').text("  *다시 입력해주세요.");
            $('#phone_Msg').css("color", "red");
            if ($('#phone').hasClass('is-valid')) {
                $('#phone').removeClass('is-valid').addClass('is-invalid');
            }
            return false;
        }
    };

    function enrollValidate() {
    
    }



</script>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>