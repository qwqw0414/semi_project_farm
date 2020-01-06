<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="product.model.vo.OrderListProduct"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="product.model.vo.OrderList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
 
<%
	List<OrderListProduct> list = (List<OrderListProduct>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
	
	Date date = new Date();	
	SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM");
	String today = simple.format(date);
	Calendar cal = Calendar.getInstance();
	
	int tYear = cal.get(Calendar.YEAR);
	int tMonth = cal.get(Calendar.MONTH)+1;
	int year;
	int month;
	try{
		year = Integer.parseInt(request.getParameter("year"));
		month = Integer.parseInt(request.getParameter("month"));
	}catch(NumberFormatException e){
		year = tYear;
		month = tMonth;
	}

%>
<h1 class="main-color text-center page-title">구매 내역 보기</h1>
<hr class="divide"/>
<form  class="form-inline" action="<%=request.getContextPath()%>/member/orderViewEnd?memberId=<%=memberLoggedIn.getMemberId()%>"
	  method="post">
<select name="year" id="year" class="custom-select my-1 mr-sm-2">
	<%  
		for(int i=tYear;i>=tYear-5;i-- ){
			if(year!=tYear&&year==i){%>
				<option value="<%=i%>" selected="selected"><%=i %>년</option>
			<% continue; }%>
			<option value="<%=i%>"><%=i %>년</option>
	<% } %>

</select>
<select name="month" id="month" class="custom-select my-1 mr-sm-2">
	<% for(int i=1;i<=12;i++) { 
		if(month == tMonth&&month==i){%>
			<option value="<%=i%>" selected="selected"><%=i %>월</option>
		<% 
		} else {
			if((month != tMonth) && (month==i)){%>
			<option value="<%=i%>" selected="selected"><%=i %>월</option>
			<%}%>
			<option value="<%=i%>"><%=i %>월</option>
		<%}%>

	<%} %>
</select>
	<input type="submit" value="검색" class="btn btn-success btn-main-bg"/>
</form>


<table class="table">
	<thead class="thead-dark">
		<tr>
			<th class="main-bg-border" scope="col">이미지</th>
			<th class="main-bg-border" scope="col">상품명</th>
			<th class="main-bg-border" scope="col">수량</th>
			<th class="main-bg-border" scope="col">가격</th>
			<th class="main-bg-border" scope="col">배송조회</th>
			<th class="main-bg-border" scope="col">주문취소</th>
		</tr>
	</thead>
	<tbody>
		<%for(OrderListProduct o: list) { %>
		<tr>
			<td><img src="<%=request.getContextPath() %>/upload/product/<%=o.getPhoto() %>" 
				width="130px" /></td>
			<td><a href="<%=request.getContextPath()%>/product/productInfo?pId=<%=o.getpId() %>">
				<%=o.getpName() %></a>
			</td>
			<td><%=o.getAmount() %></td>
			<td><%=o.getPrice() %></td>
			<td><%="N".equals(o.getStatus())?"준비중":"배송완료" %></td>
			<%if(o.getStatus()!=null&&"N".equals(o.getStatus())){ %>
			<td><button onclick="deleteOrder(<%=o.getOrderId()%>);">x</button></td>
			<%} else { %>
			<td></td>
			<%} %>
			
		</tr>
		<% } %>
	</tbody>
</table>

<ul class="pagination justify-content-center" style="margin:50px 0 150px 0">
	<%=pageBar%>
</ul>

<script>
function deleteOrder(orderId){
	var memberId = "<%=memberLoggedIn.getMemberId()%>";
	location.href = "<%=request.getContextPath()%>/member/deleteOrder?orderId="+orderId;
}

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
