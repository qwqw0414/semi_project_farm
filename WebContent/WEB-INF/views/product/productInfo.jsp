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
                    <input type="number" name="amount" id="amount" min="1" max="<%=p.getStock()%>" step="1"/>
                    <br>
                    <span>재고:<%=p.getStock()%></span>
                    <br>
	               	<button class="btn btn-danger">구매하기</button>
	               	<button class="btn btn-success" id="btn-WishList">장바구니</button>
               </div>
               <div class="col-12 order-4 float-left">
               	리뷰를 만든다면 여기에
               </div>
           </div>
       </div>
    </div>
</div>

<script>
<% if(memberLoggedIn != null){%>
$("#btn-WishList").click(()=>{
    var amount = $("#amount").val();
    var pId = "<%=p.getpId()%>";
    var memberId = "<%=memberLoggedIn.getMemberId()%>";
    var pName = "<%=p.getpName()%>";

    if(amount.length < 1){
        alert("수량을 입력해주세요.");
        $("#amount").focus();
    }

    $.ajax({
        url:"<%=request.getContextPath()%>/product/wishListInsert",
        type: "post",
        data: { pNum: amount,
                pId: pId,
                memberId: memberId},
        dataType: "json",
        success: data =>{
            alert(pName + " " + amount + "개를 장바구니에 담았습니다.");
        },
        error: (jqxhr, textStatus, errorThrown)=>{
            console.log(jqxhr, textStatus, errorThrown);
        }
    });
});
<%}%>
</script>

<%@ include file ="/WEB-INF/views/common/footer.jsp"%>