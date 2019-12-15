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
<!-- 스크립트 -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<title>semi-project</title>
<script>
function wishListReg(pId){
    var pName = $("[id="+pId+"]").siblings(".card-title").text();
    var pNum = $("[id="+pId+"]").parent().find("#productNum").val();
    var $bar = $("#wishListBar");

    /* $bar.append("<div class='col'><p class='wishList'>"+pName+"<br>x"+pNum+"</p></div>") */

    location.href = "<%=request.getContextPath()%>/product/wishListInsert?memberId=<%=(memberLoggedIn!=null)?memberLoggedIn.getMemberId():""%>&pId="+pId+"&pNum="+pNum;        
}
</script>
</head>
<body>
<header>
	<nav class="navbar navbar-expand-sm bg-light justify-content-center">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath()%>/">홈</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath()%>/member/memberEnroll">회원가입</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath()%>/member/memberFindId">아이디 찾기</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath()%>/member/updatePassword">비밀번호 찾기</a>
			</li>
			<li class="nav-item">
				<%if(memberLoggedIn==null){ %>
				<a class="nav-link" href="<%=request.getContextPath()%>/member/memberLogin">로그인</a>
				<%} else { %>
				<a class="nav-link" href="<%=request.getContextPath()%>/member/memberLogout">로그아웃</a>
				<%} %>
			</li>
			<%if(memberLoggedIn!=null){ %>
			<li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath()%>/member/memberView?memberId=<%=memberLoggedIn.getMemberId()%>">마이페이지</a>
			</li>
			<%} %>
			<li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath()%>/product/wishListView?memberId=<%=(memberLoggedIn!=null)?memberLoggedIn.getMemberId():null%>">장바구니</a>
			</li>
		</ul>
	</nav>
	<nav class="navbar navbar-expand-sm bg-light justify-content-center">
		<ul class="navbar-nav">
			<%if(memberLoggedIn!=null && "admin".equals(memberLoggedIn.getMemberId())){ %>
			<li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath()%>/admin/memberList">회원 정보 리스트</a>
			</li>
			<% } %>
			<li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath()%>/admin/productReg">상품 등록</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath()%>/admin/productIOList">상품 입출고</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath()%>/admin/productList">상품 리스트</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">주문 리스트</a>
			</li>
		</ul>
	</nav>
</header>
<div class="container text-center" id="contents">
