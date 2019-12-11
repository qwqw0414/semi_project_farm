<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>


<div class="container">
	  <form action="<%=request.getContextPath()%>/member/UpdatePasswordEnd" method="POST" onsubmit="return loginValidate();">
	        <div class="form-group">
	            <input type="text" class="form-control" id="memberId" name="memberId" value="" placeholder="아이디">
	            <small class="form-text text-muted">아이디를 입력해주세요.</small>
	            
	        </div>
	        <div class="form-group">
	            <input type="password" class="form-control" id="pwd_new" name="password" placeholder="비밀번호">
	            <small class="form-text text-muted">변경할 비밀번호</small>
	        </div>
	         <div class="form-group">
	            <input type="password" class="form-control" id="pwd_check" name="password" placeholder="비밀번호">
	            <small class="form-text text-muted">비밀번호 확인</small>
	        </div>
	        
	    </form>
	    
</div>




<%@ include file="/WEB-INF/views/common/footer.jsp"%>