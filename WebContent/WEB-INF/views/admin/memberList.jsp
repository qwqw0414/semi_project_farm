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
<h1 class="main-color text-center page-title">회원정보</h1>
<hr class="divide"/>

<section id="memberList-container">
<div class="form-inline mx-auto" style="width:600px;">

<div id="search-container" class="form-row align-items-center">
	<div class="col-auto my-1">
		<select id="searchType" class="custom-select mr-sm-2" >
			<option value="memberName" selected>이름</option>
			<option value="memberId">아이디</option>
			<option value="phone">전화번호</option>
		</select>
	</div>
		<div id="search-memberName" class="input-group">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" name="searchType" value="memberName" />
				<input type="search" name="searchKeyword" size="25" class="form-control mr-sm-2" 
					   placeholder="검색할 회원명을 입력하세요" />
				<input type="submit" value="검색" class="btn btn-success"/>
			</form>
		</div>
		<div id="search-memberId">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" name="searchType" value="memberId"/>
				<input type="search" name="searchKeyword" size="25" class="form-control mr-sm-2" 
					   placeholder="검색할 아이디를 입력하세요"/>
				<input type="submit" value="검색" class="btn btn-success" />
			</form>
		</div>
		<div id="search-phone">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" name="searchType" value="phone"/>
				<input type="search" name="searchKeyword" size="25" class="form-control mr-sm-2" 
					   placeholder="검색할 전화번호 입력하세요"/>
				<input type="submit" value="검색" class="btn btn-success" />
			</form>
		</div>
	</div> 
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
<ul class="pagination justify-content-center" style="margin:50px 0 150px 0">
	<%=pageBar%>
</ul>
</section>






<%@ include file="/WEB-INF/views/common/footer.jsp"%>

















