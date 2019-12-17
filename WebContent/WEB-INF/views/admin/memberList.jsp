<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
	List<Member> list = new ArrayList(); 
	list = (ArrayList)request.getAttribute("list");
	
	String pageBar = (String)request.getAttribute("pageBar");

%>
<style>
#search-container{
	margin: 0 0 10px 0;
	padding: 3px;
	background-color:lightgray;
}
#search-memberId {
	display: none;
}
#search-memberName {
	display: inline-block;
}
#search-phone {
	display: none;
}
div#pageBar span.cPage{
	margin-right: 10px;
}

</style>
<script>
$(()=>{
	var $searchMemberId = $("#search-memberId");
	var $searchMemberName = $("#search-memberName");
	var $searchPhone = $("#search-phone");

	
	$("#searchType").change(function(){
		$searchMemberName.hide();
		$searchMemberId.hide();
		$searchPhone.hide();
		
		$("#search-"+$(this).val()).css("display","inline-block");
	});
});

</script>

<section id="memberList-container">
<h2>회원정보</h2>
<div id="search-container">
		<label for="searchType">검색타입: </label>
		<select id="searchType">
			<option value="memberName">이름</option>
			<option value="memberId">아이디</option>
			<option value="phone">전화번호</option>
		</select>
		<div id="search-memberName">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" name="searchType" value="memberName"/>
				<input type="search" name="searchKeyword" size="25" placeholder="검색할 회원명을 입력하세요"/>
				<input type="submit" value="검색" />
			</form>
		</div>
		<div id="search-memberId">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" name="searchType" value="memberId"/>
				<input type="search" name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요"/>
				<input type="submit" value="검색" />
			</form>
		</div>
		<div id="search-phone">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" name="searchType" value="phone"/>
				<input type="search" name="searchKeyword" size="25" placeholder="검색할 전화번호 입력하세요"/>
				<input type="submit" value="검색" />
			</form>
		</div>
		<div id="search-memberId">
			<form action="<%=request.getContextPath()%>/admin/memberFinder" onsubmit="return searchValidate();">
				<input type="hidden" name="searchType" value="memberId"/>
				<input type="search" name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요"/>
				<input type="submit" value="검색" onclick="searchValidate();" />
			</form>
		</div>
		<button onclick="location.href='<%=request.getContextPath()%>/admin/memberList'">전체검색</button>
	</div>  

	<table class="table table-hover" id="member-table">
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
			<tr onclick="location.href='<%=request.getContextPath()%>/member/memberView?memberId=<%=m.getMemberId()%>'">
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
</table>
<div id="pageBar">
		<%=pageBar %>
</div>
</section>






<%@ include file="/WEB-INF/views/common/footer.jsp"%>

















