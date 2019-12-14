<%@page import="product.model.vo.ProductIO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	List<ProductIO> list = (List<ProductIO>)request.getAttribute("productIOList");
%>
<h1>입·출고 수행</h1>
<div class="container">
	<form action="productIO" method="POST"
		onsubmit="return productIOValidate();" id="selectIOFrm">
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<select class="custom-select" id="selectIO" name="selectIO">
					<option value="I" selected>입고</option>
					<option value="O">출고</option>
				</select>
			</div>
			<select class="custom-select" id="pName"" name="pName" required>
				<option value="" disabled selected hidden>상품 선택</option>
				<option value="">감자</option>
				<option value="">파</option>
			</select>
			<input type="number" id="amount" name="amount" placeholder="수량" min="0" required>
			<div class="input-group-append">
				<button class="btn btn-outline-secondary" type="submit">확인</button>
			</div>
		</div>
	</form>
	<h1>입·출고 내역</h1>
	<button class="btn btn-primary" onclick="viewProductIO();">조회</button>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>입·출고번호</th>
				<th>상품아이디</th>
				<th>memberId</th>
				<th>입·출고</th>
				<th>수량</th>
				<th>입·출고일</th>
			</tr>
		</thead>
		<tbody>
			<%if(list==null||list.isEmpty()){ %>
			<tr>
				<td>입·출고 내역이 없습니다</td>
			</tr>
		<%} else { 
			for(ProductIO pIO: list){%>
			<tr>
				<td><%=pIO.getIoId() %></td>
				<td><%=pIO.getpId() %></td>
				<td><%=pIO.getMemberId() %></td>
				<td><%=("I".equals(pIO.getStatus()))?"입고":"출고" %></td>
				<td><%=pIO.getAmount() %></td>
				<td><%=pIO.getIoDate() %></td>
			</tr>
		<%  }
		}%>
		</tbody>
	</table>
</div>

<script>
function productIOValidate(){
	var $pId = $("#pId");
	var $amount = $("#amount");
	var $selectIO = $("#selectIO").val();
	console.log($selectIO);
	if($pId.val().trim().length==0){
		alert("상품 아이디를 입력하세요");
		$pId.focus();
		return false;
	}
	if($amount.val().trim().length==0){
		alert("수랑을 입력하세요");
		$amount.focus();
		return false;
	}
	return true;
}
function viewProductIO(){
	location.href="<%=request.getContextPath()%>/admin/productIOList";
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>