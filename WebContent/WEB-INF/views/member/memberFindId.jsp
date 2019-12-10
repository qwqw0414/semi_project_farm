<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="container">
	<form action="<%=request.getContextPath()%>/member/memberFindIdEnd" method="POST">
		<label for="Name">이름</label>
		<input type="text" name="memberName" id="memberName" /><br />
		<label for="phone">전화번호</label>
		<input type="text" name="phone" id="phone" /><br />
		<label for="birth">생년월일</label>
		<input type="text" name="birth" id="birth" />
	
	
	</form>

</div>




<%@ include file="/WEB-INF/views/common/footer.jsp"%>
