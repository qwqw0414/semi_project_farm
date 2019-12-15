<%@page import="product.model.service.ProductService"%>
<%@page import="product.model.vo.WishListProduct"%>
<%@page import="common.util.Utils"%>
<%@page import="product.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<!-- 소개 슬라이드 -->
<div id="slideProduct" class="carousel slide" data-ride="carousel">
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
<%
	String keyWord = null;
%>

<!-- 상품 검색 바 -->
<nav class="navbar navbar-center bg-light">
    <form class="form-inline" method="get" action="<%=request.getContextPath()%>/product/productView">
        <input class="form-control mr-sm-2" type="search" placeholder="검색" name="keyWord">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
    </form>
</nav>

<!-- 상품 출력 -->
<%

	List<Product> list = (List<Product>)request.getAttribute("list");
	if(list == null){
%>
        <h1>등록된 상품이 없다.</h1>
<%
            /* <img src="/farm/images/no.png" class="card-img-top"> */
	}else{
		keyWord = (String)request.getAttribute("keyWord");
		String pageBar = (String)request.getAttribute("pageBar");		
%>
<div id="productList">
    <div class="row w-100" style="margin: auto;">
    <%for(Product p : list){ %>
        <div class="card" style="width: 16rem;">
            <img src='/farm/<%=(p.getPhoto()==null)?"images/no.png":"upload/product/"+p.getPhoto()%>' class="card-img-top">
            <div class="card-body">
                <input type="hidden" id="<%=p.getpId()%>">
                <h5 class="card-title"><%=p.getpName()%></h5>
                <p class="card-text">
                    <div class="input-group">
                        <span class="form-control">￦ <%=new Utils().numberFormat(p.getPrice()) %></span>
                        <div class="input-group-append">
                            <input type="number" min="1" max="10" class="input-group-text" value="1" id="productNum">
                        </div>
                    </div>
                </p>
                <div class="btn-group mr-2" role="group" aria-label="First group">
                
                    <button type="button" class="btn btn-outline-danger">구매하기</button>
                    <button type="button" class="btn btn-outline-info" onclick="wishListReg('<%=p.getpId()%>');">장바구니</button>
                </div>
            </div>
        </div>
	<%} %>
    </div>
</div>

<!-- 페이지바 -->
<ul class="pagination justify-content-center" style="margin:50px 0 150px 0">
	<%=pageBar%>
</ul>
<%}%>

<!-- 장바구니 -->
<%
	if(memberLoggedIn != null){ 
		
		List<WishListProduct> wishList = null;
		wishList = new ProductService().selectWishListByMemberId(memberLoggedIn.getMemberId());

		
  		if(wishList != null && wishList.size() > 0){
  			
%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-bottom" id="wishListNav">
    <div class="row" id="wishListBar">
        <div class="btn-group-vertical col">
            <button class="btn btn-info btn-sm">전체 보기</button>
            <button class="btn btn-danger btn-sm">구매 하기</button>
        </div>
<%
			int cnt = 0;
	
			for(WishListProduct w : wishList){ 
				if(cnt++ > 9) break;
%>
        <div class="wishList">
            <span><%=w.getpName()%>x<%=w.getAmount()%></span>
            <br>
            <form action="<%=request.getContextPath()%>/product/deleteWishListBar" method="POST">
            	<span>
            		<input type="hidden" name="keyWord" value='<%=(keyWord==null)?"":keyWord%>'>
                    <input type="hidden" name="memberId" value="<%=memberLoggedIn.getMemberId()%>">
                    <input type="hidden" name="listId" value="<%=w.getListId()%>">
                    <input type="submit" class="btn btn-sm btn-danger" value="x">
            	</span>
            </form>
        </div>
	<%} %>
    </div>
</nav>
<%}} %>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>