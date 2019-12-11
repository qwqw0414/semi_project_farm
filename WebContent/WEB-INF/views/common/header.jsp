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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<title>semi-project</title>
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
			<li class="nav-item">
				<a class="nav-link" href="<%=request.getContextPath()%>/member/memberView?memberId=<%=memberLoggedIn.getMemberId()%>">마이페이지</a>
			</li>
		</ul>
	</nav>
</header>
<div class="container text-center">
