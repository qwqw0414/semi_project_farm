<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
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
<%if(memberLoggedIn==null){ %>
<div class="container">
	  <form action="<%=request.getContextPath()%>/member/memberLoginEnd" method="POST" onsubmit="return loginValidate();">
	        <div class="form-group">
	            <input type="text" class="form-control" id="memberId" name="memberId" value="<%=saveId?memberId:""%>" placeholder="아이디">
	            <small class="form-text text-muted">아이디를 입력해주세요.</small>
	            <input type="checkbox" name="saveId" id="saveId" <%=saveId?"checked":"" %> />아이디 저장
	        </div>
	        <div class="form-group">
	            <input type="password" class="form-control" id="pwd" name="password" placeholder="비밀번호">
	            <small class="form-text text-muted">비밀번호를 입력해주세요.</small>
	        </div>
	        <button type="submit" class="btn btn-primary">로그인</button>
	        <button type="button" class="btn btn-primary">아이디 찾기</button>
	        <button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/member/updatePassword'">비밀번호 찾기</button>
        </form>
</div>
<%} else { %>
<div class="container">
	<table id="logged-in">
		<tr>
			<td><%=memberLoggedIn.getMemberName() %>님, 안녕하세요.</td>
		</tr>
		<tr>
			<td>
				<input type="button" value="내 정보보기" onclick="location.href='#" />
				<input type="button" value="로그아웃" onclick="location.href='<%=request.getContextPath()%>/member/memberLogout'"/>
			</td>
		</tr>
	</table>
</div>
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