<%@page import="product.model.vo.OrderListProduct"%>
<%@page import="product.model.vo.OrderList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/views/common/header.jsp"%>
<%
	List<OrderListProduct> list = (List<OrderListProduct>)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");
	String status = (String)request.getAttribute("status");
	
%>
<h2>orderlist page</h2>
	<div class="custom-control custom-switch">
		<input type="checkbox" class="custom-control-input" id="orderByStatusN"
		<%=status!=null?"checked":"" %>>
		<label class="custom-control-label" for="orderByStatusN">미출고 주문보기</label>
	</div>
	<table class="table table-hover">
	<thead>
			<tr>
				<th>주문번호</th>
				<th>구매자 아이디</th>
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
				<td><%=o.getpName() %></td>
				<td><%=o.getPrice() %></td>
				<td><%=o.getZipcode() %></td>
				<td><%=o.getAddress() %></td>
				<td><%=o.getOrderDate() %></td>
				<td><%=o.getCheckDate()==null?"미처리":o.getCheckDate() %></td>
				<td><%="N".equals(o.getStatus())?"<button class='btn btn-success' onclick='changeStatus(this);'>출고하기</button>":"<button class='btn btn-danger' onclick='changeStatus(this);'>출고취소</button>" %></td>
			</tr>
		<%  }
		}%>
		</tbody>
	</table>
	<table  id="ajax">
	</table>
	<div id="pageBar">
		<%=pageBar %>
	</div>
<script>
$(()=>{
	$("#orderByStatusN").on('change', function(){
		var $orderByStatusN = $("#orderByStatusN");
		console.log($orderByStatusN);
		console.log($orderByStatusN.is(":checked"));
		if($orderByStatusN.is(":checked")){
			orderByStatusN();
		} else {
			location.href="<%=request.getContextPath()%>/admin/productOrderList";
		}
	});
	
});

function orderByStatusN(){
	console.log("function");
	location.href="<%=request.getContextPath()%>/admin/productOrderList?status=N";
}

function changeStatus(this_){
	var orderId = $(this_).parent("td").siblings("#orderId").text();
	console.log(orderId);
	$.ajax ({
		url: "<%=request.getContextPath()%>/admin/changeOrderStatus",
		data:{"orderId": orderId,
			  cPage:'<%=request.getParameter("cPage")%>'},
		success: data =>{
			let html ="";
			$(data).each((idx,data)=>{
			html += "<td id='orderId'>"+data.orderId+"</td>";
			html += "<td>"+data.memberId+"</td>";
			html += "<td>"+data.pName+"</td>";
			html += "<td>"+data.price+"</td>";
			html += "<td>"+data.zipcode+"</td>";
			html += "<td>"+data.address+"</td>";
			html += "<td>"+data.orderDate+"</td>";
			html += "<td>"+((data.checkDate==null)?'미처리':data.checkDate)+"</td>";
			if('N'==data.status){
			html += "<td><button class='btn btn-success' onclick='changeStatus(this);'>출고하기</button></td>";				
			} else {
				html += "<td><button class='btn btn-danger' onclick='changeStatus(this);'>출고취소</button></td>";
			}
			
			});//end of each
			
			let tr = $(this_).parent().parent("tr");

			tr.html(html);
		},
		error : (jqxhr, textStatus, errorThrown)=>{
			console.log(jqxhr, textStatus, errorThrown);
		}
		
	})
} 

</script>
<%@ include file ="/WEB-INF/views/common/footer.jsp"%>