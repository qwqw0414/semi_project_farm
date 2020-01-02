<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<form action="<%=request.getContextPath()%>/admin/productRegEnd" 
	  method="post"
	  enctype="multipart/form-data">
	  
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="tag">분류</span>
        </div>
        <select class="custom-select" name="category" required>
            <option selected disabled hidden>카테고리 선택</option>
            <option value="과일">과일</option>
            <option value="채소">채소</option>
            <option value="육류">육류</option>
            <option value="생선">생선</option>
        </select>
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="tag">상품명</span>
        </div>
        <input type="text" class="form-control" placeholder="상품명을 입력해주세요." name="pName" required>
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="tag">가격</span>
        </div>
        <input type="number" class="form-control" min="0" step="100" placeholder="상품 가격을 입력해주세요." name="price">
    </div>
    <div class="input-group mb-3">
        <div class="custom-file">
            <input type="file" class="custom-file-input" id="photo" name="photo">
            <label class="custom-file-label" for="photo">상품 이미지 등록</label>
        </div>
    </div>
    <div class="input-group">
        <div class="input-group-prepend">
            <span class="input-group-text" id="tag">상품 정보</span>
        </div>
        <textarea class="form-control" name="pInfo"></textarea>
    </div>
    <br>
    <button type="submit" class="btn btn-danger btn-block btn-main-bg">상품 등록</button>
</form>



<%@ include file="/WEB-INF/views/common/footer.jsp"%>


