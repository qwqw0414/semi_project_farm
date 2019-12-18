<%@page import="common.util.Utils"%>
<%@page import="product.model.vo.WishListProduct"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
	List<WishListProduct> list = (List<WishListProduct>)request.getAttribute("list"); 
    int cnt = 0;
%>

<%if(memberLoggedIn != null){ %>
<h1><%=memberLoggedIn.getMemberName()%>님의 wishList</h1>

<table class="table">
	<thead class="thead-dark">
		<tr>
			<th scope="col">#</th>
			<th scope="col">상품명</th>
			<th scope="col">수량</th>
			<th scope="col">가격</th>
			<th scope="col">취소</th>
		</tr>
	</thead>
	<tbody>
	<%
		for(WishListProduct w : list){
	%>
		<tr>
			<th scope="row"><%=++cnt%></th>
			<td><%=w.getpName()%></td>
			<td><%=w.getAmount()%> 개</td>
			<td>￦ <%=new Utils().numberFormat(w.getAmount()*w.getPrice()) %></td>
			<td>
				<input type="hidden" value="<%=memberLoggedIn.getMemberId()%>" name="memberId">
				<input type="hidden" value="<%=w.getListId()%>" name="listId">
				<input type="button" class="btn btn-danger" value="x" onclick="deleteWishList(this);">
            </td>
		</tr>
	<%} %>
	</tbody>
</table>
<%}else{%>
<h1>로그인 필요</h1>
<%} %>

<script>
function deleteWishList(btn){
	var listId = $(btn).siblings("[name=listId]").val();
	var memberId = $(btn).siblings("[name=memberId]").val();
	var $tbody = $("tbody");
	

	$.ajax({
		url:"<%=request.getContextPath()%>/product/deleteWishList",
		data: { listId: listId,
				memberId: memberId},
		type: "post",
		success: data =>{
			var cnt = 0;
			let html = "";

			$(data).each((idx,wishList)=>{
				var price = numberFormat(wishList.price);

				html += "<tr><th scope='row'>" + ++cnt + "</th>";
				html += "<td>" + wishList.pName + "</td>";
				html += "<td>" + wishList.amount + " 개</td>";
				html += "<td>" + price + "</td>";
				html += "<td><input type='hidden' value='<%=memberLoggedIn.getMemberId()%>' name='memberId'>";
				html += "<input type='hidden' value='"+wishList.listId+"' name='listId'>";
				html += "<input type='button' class='btn btn-danger' value='x' onclick='deleteWishList(this);'></td></tr>";
			});

			$tbody.html(html);
		},
		error : (jqxhr, textStatus, errorThrown)=>{
			console.log(jqxhr, textStatus, errorThrown);
		}

	});

}
</script>















<%@ include file="/WEB-INF/views/common/footer.jsp"%>