<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	String str= (String)request.getAttribute("str");
	System.out.println("str");
%>
<%
	//로그인한 경우
	/* Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn"); */
	//아이디 저장 쿠키
	boolean saveId = false;
	String memberId = "";
	Cookie[] cookies = request.getCookies();
	if(cookies!=null){
		for(Cookie c:cookies){
			String k = c.getName();
			String v = c.getValue();
			
			if("saveId".equals(k)){
				saveId = true;
				memberId = v;
			}
		}
	}
%>
<%=(str!=null)?str:"" %>
<%if(memberLoggedIn==null){ %>
<div class="container LoginContainer">
	  <form action="<%=request.getContextPath()%>/member/memberLoginEnd" method="POST" onsubmit="return loginValidate();">
	        <div class="form-group">
	            <input type="text" class="form-control" id="memberId" name="memberId" value="<%=saveId?memberId:""%>" placeholder="아이디">
	        </div>
	        <div class="form-group">
				<input type="password" class="form-control" id="pwd" name="password" placeholder="비밀번호">
	        </div>
			
			<div class="row">
					<div class="col-4"><input type="checkbox" name="saveId" id="saveId" <%=saveId?"checked":"" %> />아이디 저장</div>
					<div class="col-8"><a  class="find" href='<%=request.getContextPath()%>/member/memberFindId'">아이디 찾기</a> <span id="span1"> | </span>  
	        <a class="find" href='<%=request.getContextPath()%>/member/updatePassword'">비밀번호 찾기</a></div>
			</div>
			<button type="submit" class="btn btn-primary btn1">로그인</button><br>
	        <button type="button" class="btn btn-primary btn1" onclick="location.href='<%=request.getContextPath()%>/member/memberEnroll'">회원가입</button>
        </form>
</div>


<style>
	#span1{
		font-size: 10px;
		color:blue;
	}
.LoginContainer{
	width:380px;
	height:296px;
}

.find{
	font-size: 10px;
}
.btn1{
	width:348px;
	padding: 0;
	height: 40px;
	margin-bottom: 10px;
	margin-top: 10px;
	
}
.col-4{
	font-size: 12px;
}
.col-8{
	padding-left: 112px;
}

</style>






<%} %>
<script>
function loginValidate() {
	var $memberId = $("#memberId");
	var $password = $("#pwd");
	
	if($memberId.val().trim().length==0){
		alert("아이디를 입력하세요.");
		return false;
	}
	if($password.val().trim().length==0){
		alert("비밀번호를 입력하세요.");
		return false;
	}
	return true;
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>