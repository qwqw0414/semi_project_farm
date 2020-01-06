<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>


<div class="productReg-container mx-auto">
<h1 class="text-center main-color page-title">상품 등록</h1>
<hr class="divide" />
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
            <option value="해산물">해산물</option>
            <option value="버섯">버섯</option>
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
            <input type="file" class="custom-file-input" id="photo" name="photo" onchange="LoadImg(this);" required>
            <label class="custom-file-label" for="photo">상품 이미지 등록</label>
        </div>
    </div>
    <div class="input-group mb-3 mx-auto">
        <img src="" id="img-viewer" style="width: 300px;">
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
</div>

<script>
function LoadImg(f) {
    console.dir(f);
    console.dir(f.files[0]);

    if (f.files && f.files[0]) {
        let reader = new FileReader();
        //파일읽기, 읽기완료시 load event 발생
        reader.readAsDataURL(f.files[0]);

        reader.onload = e => {
            // 파일컨텐츠는 e.target.resut속성에 담겨있음.
            $("#img-viewer").attr("src", e.target.result);
        }
    }
    else {
        $("#img-viewer").attr("src", "");
    }
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>


