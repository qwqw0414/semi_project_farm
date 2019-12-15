<%@page import="product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	List<Product> list = (List<Product>)request.getAttribute("list");
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
	String pageBar = (String)request.getAttribute("pageBar");
%>

<style>
#search-container {
	margin: 0 0 10px 0;
	padding: 3px;
	background-color: rgba(0, 188, 212, .3);
}

div#search-pName {
	display: <%="pName" .equals(searchType)||searchType==null?"inline-block":"none"%>;
}

div#search-Category {
	display: <%="Category" .equals(searchType)?"inline-block":"none"%>;
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
	
});

</script>

<section id="productList-container">
	<h2>상품리스트</h2>
	<div id="search-container">
		<label for="searchType">검색타입 : </label> <select id="searchType">
			<option value="pName" <%="pName".equals(searchType)?"selected":"" %>>상품명</option>
			<option value="Category"
				<%="Category".equals(searchType)?"selected":""%>>카테고리</option>
		</select>





		<!-- 상품명 검색 -->
		<div id="search-pName">
			<form action="<%=request.getContextPath()%>/admin/productFinder">
				<input type="hidden" name="searchType" value="pName" /> <input
					type="search" name="searchKeyword" size="25"
					placeholder="검색할 삼품명을 입력하세요"
					value="<%="pName".equals(searchType)?searchKeyword:""%>" /> <input
					type="submit" value="검색" />
			</form>
		</div>





		<!-- 	카테고리별 검색 -->
		<div id="search-Category">
			<form action="<%=request.getContextPath()%>/admin/productFinder">
				<input type="hidden" name="searchType" value="Category" /> <input
					type="radio" name="searchKeyword" value="V"
					<%="Category".equals(searchType) && "V".equals(searchKeyword)?"checked":""%> />
				채소 <input type="radio" name="searchKeyword" value="F"
					<%="Category".equals(searchType) && "F".equals(searchKeyword)?"checked":""%> />
				과일 <input type="submit" value="검색" />
			</form>
		</div>



	</div>


	<table id="tbl-product">
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
			<% if(list==null || list.isEmpty()){ %>
			<tr>
				<td colspan="8" align="center">검색 결과가 없습니다.</td>
			</tr>
			<% 
            } 
            else {
                for(Product p : list){ 
        %>
			<tr>
				<td><%="V".equals(p.getCategory())?"채소":"과일"%></td>
				<td><%=p.getpName() %></td>
				<td><%=p.getpInfo() %></td>
				<td><%=p.getPrice() %></td>
				<td><%=p.getDiscount() %></td>
				<td><%=p.getStock() %></td>
			</tr>

			<%		} 
            }
        %>
		</tbody>




	</table>

	<div id="pageBar">
		<%=pageBar %>
	</div>






</section>



























<%@ include file="/WEB-INF/views/common/footer.jsp"%>