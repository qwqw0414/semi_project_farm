<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%
	 //http status error code로 에러페이지에 오게 되면,
	 //exception객체는 null이다
	 int statusCode = response.getStatus();
	   
	 //예외가 던져지거나, httpStatusCode값으로 넘어온 경우
	 //모두를 처리하는 메세지변수: exception객체의 null여부를 판단한다
	 String msg = (exception==null)?String.valueOf(statusCode):exception.getMessage();
%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/animation.css">

<style>
img{
	display: block;
	margin: 0px auto;
	margin-top: 8vh;
	width: 80%;
}
.error-title{
	position: absolute;
	top: 85px;
	z-index: 1;
	text-align: center;
	width: 100%;
}
.error-title h3{
	color: rgb(49, 169, 190);
	font-family: 'BMHANNAPro';
}
.error-title h6{
	color:rgb(80, 80, 80);
	/* font-family: 'NanumSquare_acB'; */
}
.error-title h4{
	color:darkslategray;
	font-family: 'NanumSquare_acB';
}

.error-title button{
	width: 150px;
}
</style>
<div class="row">
<div class="error-title">
	<h3>죄송합니다. 현재 찾을 수 없는 페이지를 요청 하셨습니다.</h3>
	<br>
	<h6>
		오류가 발생하였습니다 ｜ 오류 내용: <%=msg %>
	</h6>
	<h4>감사합니다.</h4>
	<br>
	<div class="row">
		<div class="col text-right">
			<button class="btn btn-outline-secondary" id="btn-main">메인으로</button>
		</div>
		<div class="col text-left">
			<button class="btn btn-outline-secondary" id="btn-back">이전 페이지</button>
		</div>
	</div>
</div>
	<div class="col">
		<img src="<%=request.getContextPath()%>/images/serverError.jpg">
	</div>
</div>
<script>

$("#btn-main").click(()=>{
	location.href = "<%=request.getContextPath()%>/product/productView";
})
$("#btn-back").click(()=>{
	// 임시
	location.href = "<%=request.getContextPath()%>/product/productView";
})
</script>