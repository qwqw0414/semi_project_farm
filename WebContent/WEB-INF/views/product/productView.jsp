<%@page import="product.model.service.ProductService"%>
<%@page import="product.model.vo.WishListProduct"%>
<%@page import="common.util.Utils"%>
<%@page import="product.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>


<%
	String keyWord = null;
%>

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
<!-- 페이지바 -->
<ul class="pagination justify-content-center" style="margin:50px 0 150px 0">
	<%=pageBar%>
</ul>
<%}%>


<script>
$(".image").on("click",function(){
	var pId = $(this).parent().find("[type=hidden]").attr("id");
	location.href ='<%=request.getContextPath()%>/product/productInfo?pId='+pId;
});

$(".wishList").on("click",function(){
	var pId = $(this).parent().find("[type=hidden]").attr("id");
    var $modal = $(".modal-wish");
    var $con = $(".wishList .modal-body")

    $modal.css("display","block");

    $.ajax({
        url:"<%=request.getContextPath()%>/product/productModal",
        type: "post",
        data: {pId:pId},
        dataType: "json",
        success: data =>{
            
        },
        error: (jqxhr, textStatus, errorThrown)=>{
            console.log(jqxhr, textStatus, errorThrown);
        }
    });
});

$(".card").mouseenter(function(){
    $(this).find(".card-img-top").addClass("ani-photo-in");
    $(this).find(".wishList").addClass("ani-vibrate");
}).mouseleave(function(){
    $(this).find(".card-img-top").removeClass("ani-photo-in");
    $(this).find(".wishList").removeClass("ani-vibrate");
});

<%
	if(false){
%>	
function wishListReg(pId){
    var pName = $("[id="+pId+"]").siblings(".card-title").text();
    var pNum = $("[id="+pId+"]").parent().find("#productNum").val();
    var $bar = $("#wishListBar");

    var memberId = '<%=memberLoggedIn.getMemberId()%>';

    $.ajax({
        url:"<%=request.getContextPath()%>/product/wishListInsert",
        type: "post",
        data: { pNum: pNum,
                pId: pId,
                memberId: memberId},
        dataType: "json",
        success: data =>{
            alert(pName + " " + pNum + "개를 장바구니에 담았습니다.");
        },
        error: (jqxhr, textStatus, errorThrown)=>{
            console.log(jqxhr, textStatus, errorThrown);
        }
    });
}
<%
	}
%>
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>