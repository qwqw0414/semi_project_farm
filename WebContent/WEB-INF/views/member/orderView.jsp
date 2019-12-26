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
%>

<script>
	

</script>

<select name="year" id="year"></select>

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
			<td><%=o.getPhoto() %></td>
			<td><%=o.getpName() %></td>
			<td><%=o.getAmount() %></td>
			<td><%=o.getPrice() %></td>
			<td><%="N".equals(o.getStatus())?"준비중":"배송완료" %></td>
		</tr>
		<% } %>
	</tbody>
</table>

<div id="pageBar">
	<%=pageBar %>
</div>





<%@ include file="/WEB-INF/views/common/footer.jsp"%>
