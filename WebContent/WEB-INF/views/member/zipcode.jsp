<%@page import="zipcode.model.vo.Zipcode"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Zipcode> list = (List)request.getAttribute("list");
	int searchType = 0;

	if(request.getParameter("searchType") != null){
    	searchType = Integer.parseInt(request.getParameter("searchType"));
	}
	
    String keyWord = request.getParameter("keyWord");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>우편번호검색</title>
</head>
<body>
    <div class="container text-center">
        <h2>우편번호 검색</h2>
        <form action="<%=request.getContextPath()%>/zipcode/search" method="get">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <select class="custom-select" name="searchType">
                        <!-- <option value="1">시/도</option> -->
                        <option value="2" <%=(searchType == 2)?"selected":""%>>시/군/구</option>
                        <option value="3" <%=(searchType == 3)?"selected":""%>>읍/면</option>
                        <option value="4" <%=(searchType == 4)?"selected":""%>>길</option>
                    </select>
                </div>
                <input type="text" class="form-control" name="keyWord" value="<%=(keyWord!=null)?keyWord:""%>" required>
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="submit">검색</button>
                </div>
            </div>
        </form>
		<%if(list != null && list.size() > 0) {%>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="zipcodeSel">주소 선택</label>
            </div>
            <select class="custom-select" id="zipcodeSel" size="8">
            <%for(Zipcode z : list){ 
            	String str = "";
            	str += (z.getSido()==null?"":z.getSido()+" ");
            	str += (z.getSigungu()==null?"":z.getSigungu()+" ");
            	str += (z.getMyun()==null?"":z.getMyun()+" ");
            	str += (z.getRo()==null?"":z.getRo()+" ");
            	str += (z.getName()==null?"":z.getName());
            %><option value="<%=z.getCode()%>|<%=str%>"><%=str%></option><%} %>
            
            </select>
        </div>
        <div class="btn-group mr-2" role="group">
            <button type="button" class="btn btn-secondary" onclick="self.close()">취소</button>
            <button type="button" class="btn btn-secondary" id="btnSelect">확인</button>
        </div>
       	<%}else if(list != null){ %>
       	<h2>해당 주소와 일치하는 우편번호가 없습니다.<br> 다시 입력해주세요.</h2>
       	<button type="button" class="btn btn-secondary" onclick="self.close()">취소</button>
<%		  }%>
    </div>
<script>
btnSelect.onclick = function(){
    var code = $("#zipcodeSel").val();
    var frm = opener.frmMemberEnroll;
    var codeArr = code.split("|");
    // opener.document.getElementById("zipcode").value = codeArr[0];
    $("#zipCode", parent.opener.document).val(codeArr[0]);
    $("#addr", parent.opener.document).val(codeArr[1]);
    // frm.addr.value = codeArr[1];
    self.close();
}
</script>
</body>
</html>