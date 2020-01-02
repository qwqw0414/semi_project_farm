
<%@page import="com.sun.org.apache.bcel.internal.generic.GETSTATIC"%>
<%@page import="product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
	List<Product> list = (List<Product>)request.getAttribute("list");
	 String pageBar = (String)request.getAttribute("pageBar"); 
%>


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
<h2 class="main-color text-center page-title">상품리스트</h2>
<hr class="divide"/>
<!-- <div class="form-inline mx-auto" > -->
<div id="search-container" class="form-row align-items-center">
		<div class="col-auto by-1">
			<select id="searchType" class="custom-select mr-sm-2">
			<option value="pName">상품명</option>
			<option value="Category">카테고리</option>
		</select>
	</div>
		
		
		<!-- 상품명 검색 -->
		<div id="search-pName">
			<form action="<%=request.getContextPath()%>/admin/productFinder">
				<input type="hidden" name="searchType" value="pName" />
				<input type="search" name="searchKeyword" 
					   size="25"
					   placeholder="검색할 삼품명을 입력하세요"/>
				<input type="submit" value="검색" class="btn btn-success" />
			</form>
		</div>
		
		
		<!-- 	카테고리별 검색 -->
		<div id="search-Category">
			<form action="<%=request.getContextPath()%>/admin/productFinder">
				<input type="hidden" name="searchType" value="Category" />
				<input type="radio" id="vegetable" name="searchKeyword" value="V" checked/>
				<label for="vegetable">채소</label>
				<input type="radio" id="fruit" name="searchKeyword" value="F"/>
				<label for="fruit">과일</label>
				<input type="radio" id="mushroom" name="searchKeyword" value="M"/>
				<label for="fruit">버섯</label>
				<input type="radio" id="seafood" name="searchKeyword" value="S"/>
				<label for="fruit">해산물</label>
				<input type="radio" id="gogi" name="searchKeyword" value="G"/>
				<label for="fruit">육류</label>
				<input type="submit" value="검색" class="btn btn-success" />
			</form>
		</div>
		
		
	
	</div>
<!-- </div> -->

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
		<% if(list==null || list.isEmpty()){ %>
            <tr>
                <td colspan="8" align="center"> 검색 결과가 없습니다. </td>
            </tr>
        <% 
            } 
            else {
                for(Product p : list){ 
        %>
        <tr>
        	<td><%=p.getCategory() %></td>
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

<ul class="pagination justify-content-center" style="margin:50px 0 150px 0">
	<%=pageBar%>
</ul>


</section>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>