<%@page import="common.util.Utils"%>
<%@page import="product.model.vo.WishListProduct"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%if(memberLoggedIn != null){ %>
<h1 class="main-color text-center page-title"><%=memberLoggedIn.getMemberName()%>님의 장바구니</h1>
<hr class="divide"/>
<style>
img{
	width: 5rem;
}
</style>
<table class="table wishListView">
	<thead class="thead-dark">
		<tr>
			<th class="main-bg-border" scope="col"><input type="checkbox" id="checkAll" checked></th>
			<th class="main-bg-border" scope="col">이미지</th>
			<th class="main-bg-border" scope="col">상품명</th>
			<th class="main-bg-border" scope="col">수량</th>
			<th class="main-bg-border" scope="col">가격</th>
			<th class="main-bg-border" scope="col">취소</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
<hr>
<div class="float-right">
<h3>합계금액 : <span id="sumPrice"></span></h3>
<br>
<input type="button" value="구입하기" class="btn btn-danger btn-sub-bg" id="btnOrder">
</div>
<script>
$(()=>{
	loadWishList();

	$("#btnOrder").click(()=>{

		if(!confirm("구입?"))
			return;

		var $check = $(".check-WishList");
		var orderList = new Array();

		// 주문 내역 전처리
		for(var i = 0; i < $check.length; i++){
			if($check.eq(i).prop("checked")){
				var price = $check.eq(i).parent().siblings("[name=price]").val();
				var pId = $check.eq(i).parents("tr").find("[name=pId]").val();
				var listId = $check.eq(i).parent().siblings("[name=listId]").val();
				var amount = $check.eq(i).parents("tr").find(".amount").text();
				var memberId = "<%=memberLoggedIn.getMemberId()%>";

				orderList.push({
							pId: pId,
							listId: listId,
							amount: amount,
							price: price
						  });
			}
		}
		
		var jsonArr = JSON.stringify(orderList);
		
		$.ajax({
			url:"<%=request.getContextPath()%>/product/orderReg",
			data: {orderList: jsonArr,
				   memberId: memberId},
			type: "post",
			success: data =>{
				loadWishList();
			},
			error : (jqxhr, textStatus, errorThrown)=>{
				console.log(jqxhr, textStatus, errorThrown);
			}

		});

	});

});

// 전체 선택
$("#checkAll").click(function(){
	var $check = $(".check-WishList");
	$check.prop("checked",$(this).prop("checked"));
	sumPrice();
});

// 합계 금액
function sumPrice(){
	var price = 0;
	var $check = $(".check-WishList");
	var cntCheck = 0;

	for(var i = 0; i < $check.length; i++){

		console.log($check.eq(i));

		if($check.eq(i).prop("checked"))
			price += Number($check.eq(i).parent().siblings("[name=price]").val());

		if(!$check.eq(i).prop("checked"))
			cntCheck++;
	}
	
	if(cntCheck == 0)
		$("#checkAll").prop("checked", true);

	$("#sumPrice").text(numberFormat(price));
}

// 장바구니 제거
function deleteWishList(btn){
	var listId = $(btn).parents("tr").find("[name=listId]").val();
	var memberId = "<%=memberLoggedIn.getMemberId()%>";
	var $tbody = $(".wishListView tbody");

	$.ajax({
		url:"<%=request.getContextPath()%>/product/deleteWishList",
		data: { listId: listId,
				memberId: memberId},
		type: "post",
		success: data =>{
			loadWishList();
		},
		error : (jqxhr, textStatus, errorThrown)=>{
			console.log(jqxhr, textStatus, errorThrown);
		}

	});

}

// 장바구니리스트 로드
function loadWishList(){
	var $tbody = $(".wishListView tbody");
	$.ajax({
		url:"<%=request.getContextPath()%>/product/wishListView",
		data:{memberId: "<%=memberLoggedIn.getMemberId()%>"},
		success: data =>{
			let html = "";

			$(data).each((idx,wishList)=>{
				var price = Math.ceil(wishList.price*wishList.amount*(1-wishList.discount));

				if((wishList.stock - wishList.amount)<0)
					html += "<tr><th><input type='checkbox' disabled></th>";
				else
					html += "<tr><th><input type='checkbox' class='check-WishList' checked='checked'></th>";

				html += "<td><img src='/farm/upload/product/" + wishList.photo + "'></td>";
				html += "<td>" + wishList.pName + "</td>";
				
				if((wishList.stock - wishList.amount)<0)
					html += "<td><del><span class='amount'>" + wishList.amount + "</span> 개</del><br><span class='badge badge-danger'>재고 부족</span></td>";
				else
					html += "<td><span class='amount'>" + wishList.amount + "</span> 개</td>";

				html += "<td>" + numberFormat(price) + "</td>";
				html += "<td><input type='button' class='btn btn-danger' value='x' onclick='deleteWishList(this);'>";
				html += "<input type='hidden' value='"+wishList.pId+"' name='pId'></td>";
				html += "<input type='hidden' value='"+price+"' name='price'>";
				html += "<input type='hidden' value='"+wishList.listId+"' name='listId'>";
				html += "</tr>";
			});

			$tbody.html(html);
			sumPrice();

			$("tbody [type=checkbox]").click(function(){
				
				// 장바구니 전체,개별 선택 동기화
				if(!$(this).prop("checked"))
					$("#checkAll").prop("checked", false);

				sumPrice();
			});


		},
		error : (jqxhr, textStatus, errorThrown)=>{
			console.log(jqxhr, textStatus, errorThrown);
		}

	});
}

</script>
<%}else{%>
<h1>로그인 필요</h1>
<%} %>
<br>
<br>
<br>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>