<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//로그인한 경우
	Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 폰트 -->
<link href="https://fonts.googleapis.com/css?family=Black+Han+Sans|Do+Hyeon|Dokdo|Gaegu|Gamja+Flower|Gugi|Jua|Kirang+Haerang|Nanum+Gothic+Coding|Noto+Sans+KR|Noto+Serif+KR|Stylish|Yeon+Sung&display=swap" rel="stylesheet">
<!-- 스타일시트 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/animation.css">
<!-- 스크립트 -->
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script src="<%=request.getContextPath()%>/js/js.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<title>semi-project</title>

<!-- 범용 스크립트 -->
<script>
function loginGo() {
	if (confirm("로그인이 필요합니다. \n로그인 화면으로 이동?")) {
		location.href = "<%=request.getContextPath()%>/member/memberLogin";
	}
}
</script>
</head>
<body>

<modal>
<% if(memberLoggedIn != null){%>
<!-- 모달창 -->
<div class="modal-wish" style="display: none;">
    <div class="modal-dialog" style="margin-top: 100px;">
        <div class="modal-content" style="height: 300px;">
            <div class="modal-header">
                장바구니 담기
                <span class="close">x</span>
			</div>
<!-- 모달바디 -->
            <div class="modal-body">

				<div class="row">
					<div class="col">
						<h1><span id="product-name">name</span></h1>
					</div>
					<div class="col-4 text-right">
						<h5>
							<span id="product-stock" class="badge badge-success"></span>
						</h5>
					</div>
				</div>

				<div class="row">
					<div class="col-8">
						<input type="hidden" id="product-price">
						<h2><span id="product-price-format">price</span></h2>
					</div>
					<div class="col-4 text-right" style="right:-17px;">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<button class="btn btn-outline-secondary" type="button" id="amount-minus">-</button>
							</div>
							<span id="product-amount" class="input-group-text" style="width: 50px; display: inline;">0</span>
							<div class="input-group-append">
								<button class="btn btn-outline-secondary" type="button" id="amount-plus">+</button>
							</div>
						</div>
					</div>
				</div>

			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-3">
						<h4>합계 :</h4>
					</div>
					<div class="col text-right">
						<span id="product-price-sum"></span>
					</div>
					<div class="col-4">
						<input type="hidden" id="product-id">
						<input type="button" class="btn btn-info btn-block btn-main-bg" value="장바구니" id="btn-wishlist">
					</div>
				</div>
			</div>
        </div>
    </div>
</div>

<script>
//모달 비활성화
$(".modal-wish .close").click(() => {
	$(".modal-wish").css("display", "none");
});

//수량 가감 버튼
$(()=>{
	var $amount = $(".modal-wish #product-amount");
	var $priceSum = $(".modal-wish #product-price-sum");
	var $price = $(".modal-wish #product-price");

	$priceSum.text("0");

	$(".modal-wish #amount-minus").click(()=>{
		if(Number($amount.text()) > 0)
			$amount.text(Number($amount.text())-1);
		
		sumPirce();
	});

	$(".modal-wish #amount-plus").click(()=>{
		$amount.text(Number($amount.text())+1);
		sumPirce();
	});

	function sumPirce(){
		$priceSum.html(numberFormat($price.val() * Number($amount.text()))+"<small>원</small>");
	
	}

	$(".modal-wish #btn-wishlist").click(()=>{
		var memberId = "<%=memberLoggedIn.getMemberId()%>";
		var pId = $(".modal-wish #product-id").val();

		if(Number($amount.text()) < 1){
			alert("수량을 입력해주세요."); 
			return;
		}

		$.ajax({
			url:"<%=request.getContextPath()%>/product/wishListInsert",
			type: "post",
			data: { pNum: Number($amount.text()),
					pId: pId,
					memberId: memberId},
			dataType: "json",
			success: data =>{
				$(".modal-wish").css("display", "none");
			},
			error: (jqxhr, textStatus, errorThrown)=>{
				console.log(jqxhr, textStatus, errorThrown);
			},
			complete: ()=>{	
				wishListNum();
			}
		});
	});

});
</script>
<%}%>
</modal>


<header>
	<nav class="navbar navbar-expand-sm main-bg navbar-dark fixed-top justify-content-center">
		<ul class="navbar-nav">
			<a class="navbar-brand" href="<%=request.getContextPath()%>/product/productView">Farm</a>
			<!-- 검색 분류 -->
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
					카테고리
				</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=request.getContextPath()%>/product/productCategory?category=채소">채소</a>
					<a class="dropdown-item" href="<%=request.getContextPath()%>/product/productCategory?category=과일">과일</a>
					<a class="dropdown-item" href="<%=request.getContextPath()%>/product/productCategory?category=해산물">해산물</a>
					<a class="dropdown-item" href="<%=request.getContextPath()%>/product/productCategory?category=육류">육류</a>
					<a class="dropdown-item" href="<%=request.getContextPath()%>/product/productCategory?category=버섯">버섯</a>
				</div>
			</li>
				<a class="nav-link" href="<%=request.getContextPath()%>/product/productBest">베스트 12</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath()%>/product/productSmart">알뜰쇼핑</a>
			</li>
			<!-- 검색창 -->
			<form class="form-inline" action="<%=request.getContextPath()%>/product/productView">
				<input class="form-control mr-sm-2" type="text" placeholder="Search" name="keyWord">
				<button class="btn btn-success btn-sub-bg" type="submit" style="margin-right: 100px;">Search</button>
			</form>
			<!-- 회원 관련 -->
<%
			if(memberLoggedIn == null){
%>
			<li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath()%>/member/memberLogin">로그인</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath()%>/member/memberEnroll">회원가입</a>
			</li>
<%
			}else if(memberLoggedIn.isAdmin()){
%>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
						<%=memberLoggedIn.getMemberName()%>
				</a>
				<div class="dropdown-menu">
					<!-- <a class="dropdown-item" href="<%=request.getContextPath()%>/admin/productReg">상품 등록</a> -->
					<a class="dropdown-item" href="<%=request.getContextPath()%>/admin/productList">상품 관리</a>
					<a class="dropdown-item" href="<%=request.getContextPath()%>/admin/productIOList">재고 관리</a>
					<a class="dropdown-item" href="<%=request.getContextPath()%>/admin/productOrderList">주문 관리</a>
					<a class="dropdown-item" href="<%=request.getContextPath()%>/research/researchView">수입 통계</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="<%=request.getContextPath()%>/admin/memberList">회원 관리 리스트</a>
					<a class="dropdown-item" href="<%=request.getContextPath()%>/member/memberView?memberId=<%=memberLoggedIn.getMemberId()%>">내 정보</a>
					<a class="dropdown-item" href="<%=request.getContextPath()%>/member/memberLogout">로그아웃</a>
				</div>
			</li>
<%
			}else{
%>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
						<%=memberLoggedIn.getMemberName()%>
				</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="<%=request.getContextPath()%>/member/memberView?memberId=<%=memberLoggedIn.getMemberId()%>">내 정보</a>
					<a class="dropdown-item" href="<%=request.getContextPath()%>/member/orderView?memberId=<%=memberLoggedIn.getMemberId()%>">구매한 상품</a>
					<a class="dropdown-item" href="<%=request.getContextPath()%>/member/memberLogout">로그아웃</a>
				</div>
			</li>
<%			
			}
%>
<%if(memberLoggedIn != null && !memberLoggedIn.isAdmin()){%>
			<li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath()%>/product/wishList">장바구니 <span class="badge badge-light" id="wishListNum"></span></a>
			</li>
<script>

function wishListNum(){
	var $wishNum = $("a.nav-link #wishListNum");

	$.ajax({
		url: "<%=request.getContextPath()%>/product/wishListCount",
		data: {
			memberId:"<%=memberLoggedIn.getMemberId()%>"
		},
		success: data=>{
			$wishNum.text(data.result);
		},
		error: (jqxhr, textStatus, errorThrown)=>{
			console.log(jqxhr, textStatus, errorThrown);
		}

	});
}
wishListNum();
</script>
<%}%>

		</ul>
	</nav>
</header>
<!-- 소개 슬라이드 -->
<div id="slideProduct" class="carousel slide" data-ride="carousel" style="margin-top: 55px;">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="/farm/images/logo1.png" class="d-block w-100">
        </div>
        <div class="carousel-item">
            <img src="/farm/images/logo2.png" class="d-block w-100">
        </div>
        <div class="carousel-item">
            <img src="/farm/images/logo3.png" class="d-block w-100">
        </div>
    </div>
    <a class="carousel-control-prev" href="#slideProduct" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#slideProduct" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<div class="container" id="contents">
