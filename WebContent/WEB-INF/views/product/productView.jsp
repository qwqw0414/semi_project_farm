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
        <div class="card" style="width: 16rem;">
        	<!-- 상품 이미지 -->
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
				<!-- 장바구니 & 구매 버튼 -->                
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


<script>
function wishListReg(pId){
    var pName = $("[id="+pId+"]").siblings(".card-title").text();
    var pNum = $("[id="+pId+"]").parent().find("#productNum").val();
    var $bar = $("#wishListBar");

<%
	if(memberLoggedIn != null){
%>	
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
<%
	}
%>
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>