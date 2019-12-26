<%@page import="product.model.vo.OrderListProduct"%>
<%@page import="product.model.vo.OrderList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/views/common/header.jsp"%>
<%
	List<OrderListProduct> list = (List<OrderListProduct>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
	System.out.println(request.getParameter("cPage"));
%>
<h2>orderlist page</h2>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>주문번호</th>
				<th>구매자 아이디</th>
				<th>상품 아이디</th>
				<th>상품명</th>
				<th>가격</th>
				<th>우편번호</th>
				<th>주소</th>
				<th>주문일자</th>
				<th>출고처리일</th>
				<th>상태</th>
			</tr>
		</thead>
		<tbody>
			<%if(list==null||list.isEmpty()){ %>
			<tr>
				<td>주문내역이 없습니다</td>
			</tr>
		<%} else { 
			for(OrderListProduct o:list){%>
			<tr>
				<td id="orderId"><%=o.getOrderId() %></td>
				<td><%=o.getMemberId() %></td>
				<td><%=o.getpId() %></td>
				<td><%=o.getpName() %></td>
				<td><%=o.getPrice() %></td>
				<td><%=o.getZipcode() %></td>
				<td><%=o.getAddress() %></td>
				<td><%=o.getOrderDate() %></td>
				<td><%=o.getCheckDate()==null?"미처리":o.getCheckDate() %></td>
				<td><button onclick="changeStatus(this);"><%="N".equals(o.getStatus())?"미출고":"출고됨" %></button></td>
			</tr>
		<%  }
		}%>
		</tbody>
	</table>
	<div id="pageBar">
		<%=pageBar %>
	</div>
<script>
function changeStatus(this_){
	var orderId = $(this_).parents("td").siblings("#orderId").text();
	$.ajax ({
		url: "<%=request.getContextPath()%>/admin/changeOrderStatus",
		data:{"orderId": orderId,
			  cPage:'<%=request.getParameter("cPage")%>'},
		success: data =>{
		 console.log(data);	
		},
		error : (jqxhr, textStatus, errorThrown)=>{
			console.log(jqxhr, textStatus, errorThrown);
		}
		
	})
} 
</script>
<%@ include file ="/WEB-INF/views/common/footer.jsp"%>