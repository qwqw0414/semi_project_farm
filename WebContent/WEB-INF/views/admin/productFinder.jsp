<%@page import="product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	List<Product> list = (List<Product>) request.getAttribute("list");
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
	String pageBar = (String) request.getAttribute("pageBar");
%>

<style>
div#search-pName {
	display: <%="pName".equals(searchType) || searchType == null ? "inline-block" : "none"%>;
}

div#search-Category {
	display: <%="Category".equals(searchType) ? "inline-block" : "none"%>;
}
</style>
<script>
$(()=>{
	var $searchpName = $("#search-pName");
	var $searchCategory = $("#search-Category");
	
	$("#searchType").change(function(){
		$searchpName.hide();
		$searchCategory.hide();
		
		$("#search-"+$(this).val()).css("display", "inline-block");
	});
	
	
	
	$("tr").on("click",function(){		
		if( !$(this).parent().is($("thead")) ){
			var objectArr = $(this).children();
			console.log(objectArr);
			var pName=objectArr.eq(1).text();
			console.log(objectArr.eq(1).text());
			
			location.href='<%=request.getContextPath()%>/admin/productUpdate?pName='+pName;
			
		}
		
	});
	
	
	
});






</script>

<section id="productList-container">
	<h1 class="main-color text-center page-title">상품리스트</h1>
	<hr class="divide"/>
	<div id="search-container"  class="form-row align-items-center mx-auto" style="width:600px;">
			<div class="col-auto by-1">
			<select id="searchType" class="custom-select mr-sm-2">
			<option value="pName" <%="pName".equals(searchType) ? "selected" : ""%>>상품명</option>
			<option value="Category"
				<%="Category".equals(searchType) ? "selected" : ""%>>카테고리</option>
		</select>
	</div>




		<!-- 상품명 검색 -->
		<div id="search-pName">
			<form action="<%=request.getContextPath()%>/admin/productFinder">
				<input type="hidden" name="searchType" value="pName" />
				 <input type="search" name="searchKeyword" size="25"
					placeholder="검색할 삼품명을 입력하세요"
					value="<%="pName".equals(searchType) ? searchKeyword : ""%>" /> <input
					type="submit" value="검색" class="btn btn-success" />
					<button type="button" class="btn btn-primary enroll-btn btn-sub-bg" onclick="location.href='<%=request.getContextPath()%>/admin/productReg'">새상품 추가하기</button>
			</form>
		</div>





		<!-- 	카테고리별 검색 -->
		<div id="search-Category">
			<form action="<%=request.getContextPath()%>/admin/productFinder">
				<input type="hidden" name="searchType" value="Category" />
				 
				<input type="radio" id="vegetable" name="searchKeyword" value="V"
				  <%="Category".equals(searchType) && "V".equals(searchKeyword) ? "checked" : ""%> />
					<label for="vegetable">채소</label>
					
					<input type="radio" id="fruit" name="searchKeyword" value="F"
					<%="Category".equals(searchType) && "F".equals(searchKeyword) ? "checked" : ""%> />
					<label for="fruit">과일</label>
					
					<input type="radio" id="mushroom" name="searchKeyword" value="M"
					<%="Category".equals(searchType) && "M".equals(searchKeyword) ? "checked" : ""%>/>
					<label for="mushroom">버섯</label>
					
					<input type="radio" id="seafood" name="searchKeyword" value="S"
					<%="Category".equals(searchType) && "S".equals(searchKeyword) ? "checked" : ""%>/>
					<label for="seafood">해산물</label>
					
					<input type="radio" id="gogi" name="searchKeyword" value="G"
					<%="Category".equals(searchType) && "G".equals(searchKeyword) ? "checked" : ""%>/>
					<label for="gogi">육류</label>





					<input type="submit" value="검색" class="btn btn-success" />
					<button type="button" class="btn btn-primary enroll-btn btn-sub-bg" onclick="location.href='<%=request.getContextPath()%>/admin/productReg'">새상품 추가하기</button>
			</form>
		</div>



	</div>


	<table class="table table-hover">
		<thead>
			<tr>
				<th>카테고리</th>
				<th>상품명</th>
				<th>상품정보</th>
				<th>가격</th>
				<th>할인</th>
				<th>재고</th>
			</tr>

		</thead>

		<tbody>
			<%
				if (list == null || list.isEmpty()) {
			%>
			<tr>
				<td colspan="8" align="center">검색 결과가 없습니다.</td>
			</tr>
			<%
				} else {
					for (Product p : list) {
			%>
			<tr>
				<td><%=p.getCategory()%></td>
				<td><%=p.getpName()%></td>
				<td><%=p.getpInfo()%></td>
				<td><%=p.getPrice()%></td>
				<td><%=p.getDiscount()%></td>
				<td><%=p.getStock()%></td>
			</tr>

			<%
				}
				}
			%>
		</tbody>


	</table>

<ul class="pagination justify-content-center" style="margin:50px 0 150px 0">
	<%=pageBar%>
</ul>



</section>



<%@ include file="/WEB-INF/views/common/footer.jsp"%>