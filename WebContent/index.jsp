<%@page import="product.model.service.ProductService"%>
<%@page import="product.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
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
    <div class="row">
    <%for(Product p : list){ %>
        <div class="card" style="width: 16rem;">
            <img src="/farm/upload/product/<%=p.getPhoto()%>" class="card-img-top">
            <div class="card-body">
                <h5 class="card-title"><%=p.getpName()%></h5>
                <p class="card-text"><%=p.getPrice() %></p>
                <div class="btn-group mr-2" role="group" aria-label="First group">
                    <button type="button" class="btn btn-outline-danger">구매하기</button>
                    <button type="button" class="btn btn-outline-info">장바구니</button>
                </div>
            </div>
        </div>
	<%} %>
    </div>
</div>
<%}%>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>