<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
	List<Member> list = new ArrayList(); 
	list = (ArrayList)request.getAttribute("list");

%>


<section id="memberList-container">
	<h2>회원정보</h2>
	<div class="form-group">
		<form action="<%=request.getContextPath()%>/admin/memberFinder">
			<input type="text" class="form-control" id="memberId" name="memberId" value="" placeholder="이름입력">
			<small class="form-text text-muted">검색할 성명을 입력해주세요.</small>
			<button type="submit" class="btn btn-primary" >검색</button>
		</form>
	</div>
	<table id="tbl-member">
		<thead>
			<tr>
				<th>아이디</th>
				<th>성명</th>
				<th>생년월일</th>
				<th>전화번호</th>
				<th>우편번호</th>
				<th>주소</th>
				<th>가입일자</th>
			</tr>
		</thead>
		<tbody>
		 <%for(Member m: list){ %>
			<tr>
				<td><%=m.getMemberId() %></td>
				<td><%=m.getMemberName()%></td>
				<td><%=m.getBirth() %></td>
				<td><%=m.getPhone()%></td>
				<td><%=m.getZipcode() %></td>
				<td><%=m.getAddress()%></td>
				<td><%=m.getEnrolldate()%></td>
			</tr>
			<%} %>
		</tbody>




</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>