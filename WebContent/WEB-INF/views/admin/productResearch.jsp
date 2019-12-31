<%@page import="product.model.vo.OrderListProduct"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	List<OrderListProduct> list = (List<OrderListProduct>)request.getAttribute("list");
%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<h1>수입 통계</h1>

<div id="chart_div" style="height: 400px;"></div>

<script>
google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(drawBasic);

function drawBasic() {

    var data = new google.visualization.DataTable();
    data.addColumn('number', 'X');
    data.addColumn('number', '수입');

    // data.addRows([
    // [0, 0],   [1, 10],  [2, 23],  [3, 17],  [4, 18],  [5, 9],
    // [6, 11],  [7, 27],  [8, 33],  [9, 40],  [10, 32], [11, 35],
    // [12, 30], [13, 40], [14, 42], [15, 47], [16, 44], [17, 48],
    // [18, 52], [19, 54], [20, 42], [21, 55], [22, 56], [23, 57],
    // [24, 60], [25, 50], [26, 52], [27, 51], [28, 49], [29, 53],
    // [30, 55], [31, 60], [32, 61], [33, 59], [34, 62], [35, 65],
    // [36, 62], [37, 58], [38, 55], [39, 61], [40, 64], [41, 65],
    // [42, 63], [43, 66], [44, 67], [45, 69], [46, 69], [47, 70],
    // [48, 72], [49, 68], [50, 66], [51, 65], [52, 67], [53, 70],
    // [54, 71], [55, 72], [56, 73], [57, 75]
    // ]);

    data.addRows([
<%for(OrderListProduct o : list){%>

<%}%>
    ]);

    var options = {
    hAxis: {
        title: '일'
    },
    vAxis: {
        title: '수입'
    }
    };

    var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

    chart.draw(data, options);
}
</script>



<%@ include file="/WEB-INF/views/common/footer.jsp"%>