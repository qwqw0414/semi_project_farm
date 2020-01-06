<%@page import="common.util.Utils"%>
<%@page import="product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/views/common/header.jsp"%>
<%
	Product p = (Product)request.getAttribute("product");
%>
<div class="container">
<h1 class="text-center main-color">상품 상세보기</h1>
<hr class="divide" />
<input type="hidden" name="isAdmin" value="<%=memberLoggedIn!=null?memberLoggedIn.isAdmin():false %>"/>
<div class="card mb-3 border-light" style="max-width: 100%;">
    
    
    <div class="row">
		<div class="col-4">
			<img src='/farm/<%=(p.getPhoto() == null) ? "images/no.png" : "upload/product/" + p.getPhoto()%>' width="100%">
		</div>
		<div class="col-8">
			<div>
                <div class="goods-info">
                    <h3><%=p.getpName()%></h3><br>
                    <p class='goods-price-comment'>가격</p>
<%if(p.getDiscount() > 0){%>
                    <span class='price-info before-price'><%=p.getPrice()%>원</span>
                    <span class='price-info'>→</span>
                    <span class='price-info'><%=new Utils().numberFormat((int)(p.getPrice()-p.getPrice()*p.getDiscount()))%>원</span>
<%}else{%>
                    <span class='price-info'><%=new Utils().numberFormat(p.getPrice())%><small>원</small></span>
<%}%>
                    <hr class="divide-sm">
                    <dl>
                    <dt>상세정보</dt><span>
				    <dd><%=p.getpInfo() != null ? p.getpInfo() : ""%></dd>
                </span>
                </dl>
                    <hr class="divide-sm">
                    <br />
                    <!--  -->
                    
						<div class="input-group mb-3 float-right">
							<div class="input-group-prepend">
								<button class="btn btn-outline-secondary hover-main" type="button"
									id="minus">-</button>
							</div>
							<span id="buy-amount" class="input-group-text"
								style="width: 50px; display: inline;">0</span>
							<div class="input-group-append">
								<button class="btn btn-outline-secondary hover-main" type="button"
									id="plus">+</button>
							</div>
						</div>
					<!--  -->
						<input type="hidden" name="amount" id="amount" min="1" max="<%=p.getStock()%>" step="1" />
                    <span>재고:<%=p.getStock()%></span> <hr class="divide-sm"><br>
                </div>
                    
                    <div class="something-btn">
                        <button class="btn btn-outline-info btn-buy2" id="btn-WishList">장바구니</button>
                        <button class="btn btn-info btn-main-bg btn-buy1" id="btn-order">구매하기</button>
                    </div>
			</div>
        </div>
    </div>
   <div class="card-footer bg-transparent border-light">
	<hr />
		<div class="review-container">
			<h5 class="mt-0 text-left font-weight-bold">리뷰를 작성해 보세요</h5>
			<hr class="divide" />
			<form
				action="<%=request.getContextPath()%>/product/productCommentInsert"
				method="POST" name="productCommentFrm">
				<input type="hidden" name="pid" value="<%=p.getpId()%>" /> <input
					type="hidden" name="memberid"
					value="<%=memberLoggedIn != null ? memberLoggedIn.getMemberId() : ""%>" />
				<div class="input-group mb-3">
				<input type="text" class="form-control" name="comments"
					placeholder="리뷰를 입력하세요" aria-label="Recipient's username" aria-describedby="button-addon2">
					<div class="input-group-append">
						<button class="btn btn-success" type="submit"
							id="btn-insert">작성</button>
					</div>
				</div>
			</form>
			<!-- 리뷰 댓글 시작 -->
			<ul class="list-unstyled text-left" id="comment-wrapper">
			</ul>
			<!-- 리뷰 댓글 종료 -->
		</div>
	</div>
</div>
</div>


<script>
//수량 감소 버튼
$("#minus").click(()=>{
	var $buyAmount = $("#buy-amount");
	var $amount = $("#amount");
    if(Number($buyAmount.text()) > 0){
        $buyAmount.text(Number($buyAmount.text())-1);
    }
    	$amount.val($buyAmount.text());
});
//수량 증가 버튼
$("#plus").click(()=>{
	var $buyAmount = $("#buy-amount");
	var $amount = $("#amount");
    //재고 유효성
    if(<%=p.getStock()%> <= Number($buyAmount.text()))
        return;

    $buyAmount.text(Number($buyAmount.text())+1);
    $amount.val($buyAmount.text());
});

function getRecentDate(){
    var dt = new Date();
 
    var recentYear = dt.getFullYear();
    var recentMonth = dt.getMonth() + 1;
    var recentDay = dt.getDate();
 
    if(recentMonth < 10) recentMonth = "0" + recentMonth;
    if(recentDay < 10) recentDay = "0" + recentDay;
 
    return recentYear + "-" + recentMonth + "-" + recentDay;
}

$(()=>{
	showComments();
});

function showComments() {
	var $pId = $("[name=pid]").val();
	var $memberId = $("[name=memberid]").val();
	var $isAdmin = $("[name=isAdmin]").val();
	var recentDate = getRecentDate();
	console.log(recentDate);
	$.ajax({
		url: "<%=request.getContextPath()%>/product/productCommentView",
		data:{"pId":$pId},
		success: data=>{
			let $ul = $("#comment-wrapper");
			let html = "<br>";
			$(data).each((idx,data)=>{
				if($isAdmin=="true"&&data.memberId==$memberId){
					html += "<li class='media'><img src='<%=request.getContextPath()%>/images/icon.png' class='mr-3' width='55px'><div class='media-body'><h5 class='mt-0 mb-1'><span class='badge badge-light sub-color'>관리자</span><sub>"+data.commentDate+" </sub>";
				} else {
					html += "<li class='media'><img src='<%=request.getContextPath()%>/images/icon.png' class='mr-3' width='55px'><div class='media-body'><h5 class='mt-0 mb-1'>"+data.memberId+" <sub>"+data.commentDate+" </sub>";
				}
				if(data.commentDate==recentDate){
					html += "<sub class='badge badge-warning'>NEW</sub>";
				}
				if(data.memberId==$memberId||$isAdmin=="true"){
					html += "<span class='float-right'><a class='close' href='<%=request.getContextPath()%>/product/deleteComment?commentId="+data.commentId+"&pId="+data.pId+"'>x</a></span>";
				}
				html += "</h5>"+data.commentContent+"</div></li><br>";
			});//end of each
			$ul.html(html);
		},
		error: (jqxhr, textStatus, errorThrown)=>{
			console.log(jqxhr, textStatus, errorThrown);
		}
		
	});//end of ajax
}

$("[name=comments]").click(function(){
	if(<%=memberLoggedIn==null%>){
		loginAlert();
	}
});

$("[name=productCommentFrm]").submit(function(e){
	if(<%=memberLoggedIn==null%>){
		loginAlert();
		e.preventDefault();
		return;
	}
	var $content = $("[name=comments]");
	if($content.val().trim().length==0){
		alert("내용을 입력해 주세요");
		$content.focus();
		e.preventDefault();
		return;
	}
});

function loginAlert(){
	alert("로그인 후 이용할 수 있습니다.");
}

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
        },
        complete: ()=>{	
            wishListNum();
            $("#amount").val(0);
            $("#buy-amount").text("0");
        }
    });
});
<%}%>
</script>

<% if(memberLoggedIn != null){%>
<!-- 구입 모달 -->
<div class="modal-order" style="display: none;">
    <div class="modal-dialog" style="margin-top: 100px;">
        <div class="modal-content" style="height: 400px;">
            <div class="modal-header">
                구매하기
                <span class="close">x</span>
			</div>
<!-- 모달바디 -->
            <div class="modal-body">

				<div class="row">
					<div class="col">
						<h1><span id="product-name"><%=p.getpName()%></span></h1>
					</div>
					<div class="col-4 text-right">
						<h5>
							<span id="product-stock" class="badge badge-success"><%=p.getStock()%></span>
						</h5>
					</div>
				</div>

				<div class="row">
					<div class="col-8">
						<input type="hidden" id="product-price" value="<%=(int)(p.getPrice() - p.getPrice()*p.getDiscount())%>">
						<h2><span id="product-price-format"><%=new Utils().numberFormat((int)(p.getPrice() - p.getPrice()*p.getDiscount()))%><small>원</small></span></h2>
					</div>
					<div class="col-4 text-right" style="right:-17px;">
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<button class="btn btn-outline-secondary" type="button" id="amount-minus">-</button>
							</div>
							<span id="product-amount" class="input-group-text" style="width: 50px; display: inline;">0</span>
							<div class="input-group-append">
								<button class="btn btn-outline-secondary" type="button" id="amount-plus">+</button>
							</div>
						</div>
					</div>
				</div>
                <hr>

            <!-- 주소 -->
                <div class="row">
                    <div class="col">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend" style="width: 100px;">
                                <input type="text" readonly class="form-control" id="member-zipcode"></span>
                            </div>
                            <input type="text" class="form-control" readonly id="member-address">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" id="zipcodeSearchBtn">검색</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <input type="text" class="form-control" id="member-detail">
                    </div>
                </div>
                <hr>
                <!-- 금액 & 구입 -->
				<div class="row">
					<div class="col-3">
						<h4>합계 :</h4>
					</div>
					<div class="col text-right">
						<span id="product-price-sum">0<small>원</small></span>
					</div>
					<div class="col-4">
						<input type="hidden" id="product-id">
						<input type="button" class="btn btn-danger btn-block" value="구매" id="btn-order">
					</div>
				</div>
			</div>
        </div>
    </div>
</div>
<script>
$('.modal-order #zipcodeSearchBtn').click(function() {
    new daum.Postcode({
        oncomplete: function(data) {
            $(".modal-order #member-zipcode").val(data.zonecode);
            $(".modal-order #member-address").val(data.address);
            $(".modal-order #member-detail").focus();
        }
    }).open();
});

//모달 비활성화
$(".modal-order .close").click(() => {
    $(".modal-order").css("display", "none");
});

$(()=>{
    var $amount = $(".modal-order #product-amount");
    var $priceSum = $(".modal-order #product-price-sum");
    var $price = $(".modal-order #product-price");
    var $stock = $(".modal-order #product-stock");
    var $address = $(".modal-order #member-address");
    var $zipcode = $(".modal-order #member-zipcode");
    var $detail = $(".modal-order #member-detail");
    var memberId = "<%=memberLoggedIn.getMemberId()%>";

    if($stock.text() >= 100)
        $stock.attr('class','badge badge-success');
    else
        $stock.attr('class','badge badge-warning');


//모달 활성화
    $("#btn-order").click(()=>{
        
        if($stock.text() == 0){
            alert("재고가 없습니다.")
            return;
        }

        $.ajax({
            url:"<%=request.getContextPath()%>//member/memberinfo",
            type: "post",
            data: {memberId: memberId},
            dataType: "json",
            success: data =>{

                $address.val(data.address);
                $detail.val(data.detail);
                $zipcode.val(data.zipcode);
                $(".modal-order").css("display","block");
            },
            error: (jqxhr, textStatus, errorThrown)=>{
                console.log(jqxhr, textStatus, errorThrown);
            }
        });
    });

//수량 감소 버튼
    $(".modal-order #amount-minus").click(()=>{
        if(Number($amount.text()) > 0)
            $amount.text(Number($amount.text())-1);
        
        sumPirce();
    });
//수량 증가 버튼
    $(".modal-order #amount-plus").click(()=>{

        //재고 유효성
        if(Number($stock.text()) <= Number($amount.text()))
            return;

        $amount.text(Number($amount.text())+1);
        sumPirce();
    });

    function sumPirce(){
        $priceSum.html(numberFormat($price.val() * Number($amount.text()))+"<small>원</small>");
    
    }

    $(".modal-wish #btn-wishlist").click(()=>{
        
        var pId = $(".modal-wish #product-id").val();

        if(Number($amount.text()) < 1){
            alert("수량을 입력해주세요."); 
            return;
        }

        $.ajax({
            url:"<%=request.getContextPath()%>//product/directOrder",
            type: "post",
            data: { pNum: Number($amount.text()),
                    pId: pId,
                    memberId: memberId},
            dataType: "json",
            success: data =>{
                $(".modal-wish").css("display", "none");
            },
            error: (jqxhr, textStatus, errorThrown)=>{
                console.log(jqxhr, textStatus, errorThrown);
            }
        });
    });


    // 구입
    $(".modal-order #btn-order").click(()=>{
        var memberId = "<%=memberLoggedIn.getMemberId()%>";
        var pId = <%=p.getpId()%>;
        var amount = Number($amount.text());
        var price = $price.val() * amount;

        console.log($address.val());
        console.log($zipcode.val());

        if(Number($amount.text()) < 1){
            alert("수량을 입력해주세요."); 
            return;
        }

        if($detail.val().length < 1){
            alert("상세정보를 입력해주세요.");
            $detail.focus();
            return;
        }

        $.ajax({
            url:"<%=request.getContextPath()%>/product/directOrder",
            type: "post",
            data: { amount: Number($amount.text()),
                    pId: pId,
                    memberId: memberId,
                    price: price,
                    address: $address.val(),
                    zipcode: $zipcode.val(),
                    detail: $detail.val()
                  },
            dataType: "json",
            success: data =>{
            },
            error: (jqxhr, textStatus, errorThrown)=>{
                console.log(jqxhr, textStatus, errorThrown);
            },
            complete: ()=>{
                alert("구입 완료");
                $(".modal-order").css("display", "none");
                location.reload(true);
            }
        });
    });

});
</script>
<%}else{%>
<script>
$("#btn-order").click(() => {
    loginGo();
});
$("#btn-WishList").click(() => {
    loginGo();
});
</script>
<%}%>

<%@ include file ="/WEB-INF/views/common/footer.jsp"%>