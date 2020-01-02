<%@page import="product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	Product p = (Product)request.getAttribute("product");
%>
<form action="<%=request.getContextPath()%>/admin/productUpdateEnd" 
	  method="post"
	  enctype="multipart/form-data">
	  
  <!--상품 수정을 위해 게시글 고유번호 전송 필수-->
	<input type="hidden" name="pId" value="<%=p.getpId() %>" />
	<h2>상품 상세정보</h2>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="tag">분류</span>
        </div>
        <select class="custom-select" name="category" required>
            <option selected disabled hidden>카테고리 선택</option>
            <option value="과일" <%="과일".equals(p.getCategory())? "selected='selected'":""%> >과일</option>
            <option value="채소" <%="채소".equals(p.getCategory())? "selected='selected'":""%> >채소</option>
            <option value="육류" <%="육류".equals(p.getCategory())? "selected='selected'":""%> >육류</option>
            <option value="해산물" <%="해산물".equals(p.getCategory())? "selected='selected'":""%> >생선</option>
            <option value="버섯" <%="버섯".equals(p.getCategory())? "selected='selected'":""%> >버섯</option>
        </select>
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="tag">상품명</span>
        </div>
        <input type="text" class="form-control" placeholder="상품명을 입력해주세요." name="pName" value="<%=p.getpName()%>" required>
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="tag">가격</span>
        </div>
        <input type="number" class="form-control" min="0" step="100" placeholder="상품 가격을 입력해주세요." name="price" value="<%=p.getPrice()%>">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="tag">할인율</span>
        </div>
        <input type="number" class="form-control" min="0.00" max="1" step="0.01" placeholder="할인율을 입력해주세요." name="discount" value="<%=p.getDiscount()%>">
    </div>
    <div class="input-group mb-3">
        <div class="custom-file">
            <input type="file" class="custom-file-input" id="photo" name="photo">
            <label class="custom-file-label" for="photo">상품 이미지 등록</label>
            <span id="oldFileName"><%= p.getPhoto() %></span>
            <input type="hidden" value="<%= p.getPhoto() %>" name="oldPhotoName">
        </div>
    </div>
    <div class="input-group">
        <div class="input-group-prepend">
            <span class="input-group-text" id="tag">상품 정보</span>
        </div>
        <textarea class="form-control" name="pInfo"><%=p.getpInfo()!=null?p.getpInfo():"" %></textarea>
    </div>
    <br>
    <button type="submit" class="btn btn-danger btn-block btn-main-bg">상품정보 수정</button>
    <button type="button" class="btn btn-danger btn-block btn-sub-bg" onClick="deleteValidate()">상품 삭제</button>
</form>
<script>
function deleteValidate(){	
	if(confirm("정말로 상품을 삭제하시겠습니까?")){
		location.href='<%=request.getContextPath()%>/admin/productDelete?pId=<%=p.getpId()%>&oldPhotoName=<%=p.getPhoto()%>';
	}
	
}
$("#photo").change(function(){

    if($(this).val() != ""){
        $("#oldFileName").hide();
    }
    else{
        $("#oldFileName").show();
    }

})





</script>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>


