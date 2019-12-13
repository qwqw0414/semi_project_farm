
<%@page import="com.sun.org.apache.bcel.internal.generic.GETSTATIC"%>
<%@page import="product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
	List<Product> list = (List<Product>)request.getAttribute("list");
	/* String pageBar = (String)request.getAttribute("pageBar");	페이지바 */ 
%>
   


<section id="productList-container">
<h2>상품리스트</h2>

<!-- 검색div -->
<%-- <div id="search-container">
		<label for="searchType">검색타입 : </label>
		<select id="searchType">
			<option value="pId">아이디</option>
			<option value="PName">이름</option>
			<option value="Category">성별</option>
		</select>
		<div id="search-memberId">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" name="searchType" value="memberId" />
				<input type="search" name="searchKeyword" 
					   size="25" 
					   placeholder="검색할 아이디를 입력하세요"/>
				<input type="submit" value="검색" />
			</form>
		</div>
		<div id="search-memberName">
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" name="searchType" value="memberName" />
				<input type="search" name="searchKeyword" 
					   size="25" 
					   placeholder="검색할 회원명을 입력하세요"/>
				<input type="submit" value="검색" />
			</form>
		</div> --%>


<table id="tbl-product">
<thead>
<tr>
	<th>상품아이디</th>
	<th>카테고리</th>
	<th>상품명</th>
	<th>상품정보</th>
	<th>가격</th>
	<th>할인</th>
	<th>재고</th>
	<th>사진</th>
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
			<td><%=p.getpId() %></td>
        	<td><%=p.getCategory() %></td>
        	<td><%=p.getpName() %></td>
        	<td><%=p.getpInfo() %></td>
        	<td><%=p.getPrice() %></td>
        	<td><%=p.getDiscount() %></td>
        	<td><%=p.getStock() %></td>
        	<td><%=p.getPhoto() %></td>
        </tr>

 <%		} 
            }
        %>
     </tbody>   
		



</table>








</section>






<%@ include file="/WEB-INF/views/common/footer.jsp"%>