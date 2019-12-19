<%@page import="product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/views/common/header.jsp"%>
<%
	Product p = (Product)request.getAttribute("product");
%>
<h1>상품 상세보기</h1>
    <div class="container-fluid">
    <div class="row">
        <div class="col-xl-1 d-none d-md-block"></div>
        <div class="col-xl-10 col-12">
           <div class="row d-xl-block d-flex h-100">
               <div class="col-xl-6 order-xl-1 col-12 order-2 order-xl-1 d-flex tall float-left">
                   <img src='/farm/<%=(p.getPhoto()==null)?"images/no.png":"upload/product/"+p.getPhoto()%>' class="card-img-top" >
               </div>
               <div class="col-xl-6 order-xl-2 col-12 order-1 float-left">
                   <%=p.getpName()%>
	              할인율: <%=p.getDiscount()%><br>
	              상품 상세정보: <%=p.getpInfo()!=null?p.getpInfo():"" %>
               </div>
               <div class="col-xl-6 order-xl-3 col-12 order-3 float-left">
               		<label for="amount">수량 :</label>
               		<input type="number" name="amount" id="amount" />
	               	<button>구매하기</button>
	               	<button>장바구니</button>
               </div>
               <div class="col-12 order-4 float-left">
               	리뷰를 만든다면 여기에
               </div>
           </div>
       </div>
    </div>
</div> 
<%@ include file ="/WEB-INF/views/common/footer.jsp"%>