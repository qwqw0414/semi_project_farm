<%@page import="product.model.vo.WishListProduct"%>
<%@page import="common.util.Utils"%>
<%@page import="product.model.service.ProductService"%>
<%@page import="product.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
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
	ProductService ps = new ProductService();
	List<Product> list = ps.selectAll();
	if(list == null){
%>
        <h1>등록된 상품이 없다.</h1>
<%
            /* <img src="/farm/images/no.png" class="card-img-top"> */
	}else{
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
<%}%>
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
            	<button class="btn btn-danger btn-sm">전부 구매</button>
            </div>
		<%
			int cnt = 0;
	
			for(WishListProduct w : wishList){ 
				if(cnt++ > 9) break;
	%>
        <div class="wishList">
            <span><%=w.getpName()%>x<%=w.getAmount()%></span>
            <br>
            <span><input type="button" class="btn btn-sm btn-danger" value="x"
                         onclick="location.href='<%=request.getContextPath()%>/product/deleteWishList?memberId=<%=memberLoggedIn.getMemberId()%>&listId=<%=w.getListId()%>'"></span>
        </div>
	<%} %>
    </div>
</nav>
<%}} %>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>