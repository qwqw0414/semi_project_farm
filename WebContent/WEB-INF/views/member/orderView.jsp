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
	System.out.println("pageBar= "+pageBar);
	
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
	
	System.out.println(tMonth);
	System.out.println(year);
	System.out.println(month);
%>

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
		if(month != tMonth && month==i){%>
			<option value="<%=i%>" selected="selected"><%=i %>월</option>
		<% continue; } else if(month == tMonth){ %>
			<option value="<%=i%>" selected="selected"><%=i %>월</option>
		<% continue; }%>
		<option value="<%=i%>"><%=i %>월</option>
	
	<%} %>
</select>
	<input type="submit" value="검색" class="btn btn-success"/>
</form>


<table class="table">
	<thead class="thead-dark">
		<tr>
			<th scope="col">이미지</th>
			<th scope="col">상품명</th>
			<th scope="col">수량</th>
			<th scope="col">가격</th>
			<th scope="col">배송조회</th>
		</tr>
	</thead>
	<tbody>
		<%for(OrderListProduct o: list) { %>
		<tr>
			<td><img src="<%=request.getContextPath() %>/upload/product/<%=o.getPhoto() %>" 
				width="130px" /></td>
			<td><%=o.getpName() %></td>
			<td><%=o.getAmount() %></td>
			<td><%=o.getPrice() %></td>
			<td><%="N".equals(o.getStatus())?"준비중":"배송완료" %></td>
		</tr>
		<% } %>
	</tbody>
</table>

<ul class="pagination justify-content-center" style="margin:50px 0 150px 0">
	<%=pageBar%>
</ul>



<%@ include file="/WEB-INF/views/common/footer.jsp"%>
