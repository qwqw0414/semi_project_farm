<%@page import="product.model.service.ProductService"%>
<%@page import="product.model.vo.WishListProduct"%>
<%@page import="common.util.Utils"%>
<%@page import="product.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<!-- 상품 출력 -->
<%
	List<Product> list = (List<Product>)request.getAttribute("list");
	if(list == null || (list != null && list.size() == 0)){
%>
        <h1>등록된 상품이 없다.</h1>
<%
            /* <img src="/farm/images/no.png" class="card-img-top"> */
	}else{
%>
<div id="productList">
    <div class="row w-100" style="margin: auto;">
<%for(Product p : list){ %>
        <div class="card" style="width: 16rem; height: 440px;">
            <!-- 장바구니 아이콘 -->
            <img src="/farm/images/wishlist.png" class="wishList">
            <!-- 상품 이미지 -->
            <div class="image">
                <img src='/farm/<%=(p.getPhoto()==null)?"images/no.png":"upload/product/"+p.getPhoto()%>' class="card-img-top" >
            </div>
            <div class="card-body">
                <input type="hidden" id="<%=p.getpId()%>">
                <h5 class="card-title"><%=p.getpName()%></h5>

<%if(p.getDiscount() == 0){%>
                <p class="card-text price">
                    <%=new Utils().numberFormat(p.getPrice())%>원
<%}else{%>
                <p class="card-text">
                    <span class="discount">
                        <%=new Utils().numberFormat(p.getPrice())%>원 
                    </span>
                    <span class="price"> 
                        -> <%=new Utils().numberFormat((int)(p.getPrice()-p.getPrice()*p.getDiscount()))%>원
                    </span>
<%} %>
                </p>
            </div>
        </div>
<%} %>
    </div>
</div>
<%}%>


<script>
$(".image").on("click",function(){
	var pId = $(this).parent().find("[type=hidden]").attr("id");
	location.href ='<%=request.getContextPath()%>/product/productInfo?pId='+pId;
});


// 장바구니 모달 활성화
$(".wishList").on("click",function(){
<% if(memberLoggedIn != null){%>  
	var pId = $(this).parent().find("[type=hidden]").attr("id");
    var $modal = $(".modal-wish");
    var $con = $(".wishList .modal-body");
	var $amount = $("#product-amount");
	var $priceSum = $("#product-price-sum");

    //공통 자원 값 초기화
    $amount.text("0");
    $priceSum.html("0<small>원</small>");
    $(".modal-wish #product-id").val(pId);

    //모달 화면에 표시
    $modal.css("display","block");

    $.ajax({
        url:"<%=request.getContextPath()%>/product/productModal",
        type: "post",
        data: {pId:pId},
        dataType: "json",
        success: data =>{
            var price = data.price - data.price*data.discount;
            var $stock = $("modal #product-stock");

            $("modal #product-name").text(data.pName);

            if(data.stock == 0)
                $stock.text('sold out');
            else
                $stock.text(data.stock);

            //재고별 색상 변경
            if(data.stock >= 100)
                $stock.attr('class','badge badge-success');
            else if(data.stock > 0)
                $stock.attr('class','badge badge-warning');
            else
                $stock.attr('class','badge badge-danger');

            $("modal #product-price").val(price);
            $("modal #product-price-format").html(numberFormat(price)+"<small>원</small>");
        },
        error: (jqxhr, textStatus, errorThrown)=>{
            console.log(jqxhr, textStatus, errorThrown);
        }
    });
<%}else{%>
	location.href = "<%=request.getContextPath()%>/member/memberLogin";
<%}%>
});

$(".card").mouseenter(function(){
    $(this).find(".card-img-top").addClass("ani-photo-in");
    $(this).find(".wishList").addClass("ani-wishlist");
}).mouseleave(function(){
    $(this).find(".card-img-top").removeClass("ani-photo-in");
    $(this).find(".wishList").removeClass("ani-wishlist");
});

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>