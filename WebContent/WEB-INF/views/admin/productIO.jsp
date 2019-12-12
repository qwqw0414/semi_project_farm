<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<br>
<div class="container">
	<form action="productIO" method="POST"
		onsubmit="return productIOValidate();" id="selectIOFrm">
		<select name="selectIO" id="selectIO">
			<option value="I" selected>입고</option>
			<option value="O">출고</option>
		</select>
		<label for="pId">상품아이디</label><input type="text" id="pId" name="pId" />
		<label for="amount">수량</label><input type="number" id="amount" name="amount" />
		<input type="hidden" name="memberId" value="<%=memberLoggedIn.getMemberId() %>" />
		<input type="submit" value="확인" >
	</form>
	<table>
	
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

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>