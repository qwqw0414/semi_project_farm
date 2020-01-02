<%@page import="java.util.GregorianCalendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<div id="researchView">
    <h1>수입 통계</h1>
    <!-- 월간 총 수익 -->
    <div class="row">
        <div class="col">

            <select id="selectYear">
<%
	GregorianCalendar today = new GregorianCalendar ( );
   	int year = today.get(today.YEAR);
   	int month = today.get(today.MONTH);
   	int cnt = 0;
    while(cnt++ <= 100){
%>
                <option value="<%=year%>"><%=year--%>년</option>
<%
	}
%>
            </select>

            <select id="selectMonth">
                <option value="0" selected>전체</option>
<%
	for(int i = 1; i <= 12; i++){
		if(month == i){
%>
                <option value="<%=i%>"><%=i%>월</option>
<%		}else{
%>
				<option value="<%=i%>"><%=i%>월</option>
<%		}
	}
%>
            </select>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <span id="research-title"></span>
        </div>
        <div class="col">
            <h2 id="totalMonth"></h2>
        </div>
    </div>
</div>

<!-- 그래프 -->
<div id="chart_div" style="height: 400px;"></div>
<div id="donutchart" style="width: 900px; height: 500px;"></div>
<script>
$(()=>{
    var $totalMonth = $("#researchView #totalMonth");
    var $selectYear = $("#researchView #selectYear");
    var $selectMonth = $("#researchView #selectMonth");

    //이벤트
    $($selectYear).change(()=>{totalMonth()});
    $($selectYear).change(()=>{totalGraph()});
    $($selectMonth).change(()=>{totalMonth()});
    $($selectMonth).change(()=>{totalGraph()});

    function totalMonth(){

        if($selectMonth.val() == 0){
            // 년간 검색
            $.ajax({
                url:"<%=request.getContextPath()%>/research/totalPriceByYear",
                type: "post",
                data: {year: $selectYear.val()},
                dataType: "json",
                success: data =>{
                    var price = Number(data.price);
                    $totalMonth.html(numberFormat(price)+"<small>원</small>");
                },
                error: (jqxhr, textStatus, errorThrown)=>{
                    console.log(jqxhr, textStatus, errorThrown);
                }
            });
        }
        else{
            // 월간 검색
            $.ajax({
                url:"<%=request.getContextPath()%>/research/totalPriceByMonth",
                type: "post",
                data: {year: $selectYear.val(),
                       month: $selectMonth.val()},
                dataType: "json",
                success: data =>{
                    var price = Number(data.price);
                    $totalMonth.html(numberFormat(price)+"<small>원</small>");
                },
                error: (jqxhr, textStatus, errorThrown)=>{
                    console.log(jqxhr, textStatus, errorThrown);
                }
            });
        }
    }   

    function totalGraph(){
        if($selectMonth.val() == 0){
            //월별 총수입 그래프
            $.ajax({
                url:"<%=request.getContextPath()%>/research/dataListByAll",
                type: "post",
                data: {year: $selectYear.val()},
                dataType: "json",
                success: result =>{
                    //내역 존재여부 확인
                    if(result.length == 0){
                        $("#chart_div").html("거래내역이 존재하지 않습니다.")
                        return;
                    }

                    function drawBasic() {
                        var data = new google.visualization.DataTable();

                        data.addColumn('number', 'X');
                        data.addColumn('number', '총수익(원)');

                        $(result).each((idx,research)=>{
                            data.addRows([[Number(research.date), research.price]]);
                        });

                        var options = {
                        hAxis: {
                            title: '월'
                        },
                        vAxis: {
                            title: '수익(원)'
                        }
                        };

                        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

                        chart.draw(data, options);
                    }

                    google.charts.load('current', {packages: ['corechart', 'line']});
                    google.charts.setOnLoadCallback(drawBasic);
                },
                error: (jqxhr, textStatus, errorThrown)=>{
                    console.log(jqxhr, textStatus, errorThrown);
                }
            });
        }
        else{
            //일별 총수입 그래프
            $.ajax({
                url:"<%=request.getContextPath()%>/research/dataListByDay",
                type: "post",
                data: {year: $selectYear.val(),
                    month: $selectMonth.val()},
                dataType: "json",
                success: result =>{
                    //내역 존재여부 확인
                    if(result.length == 0){
                        $("#chart_div").html("거래내역이 존재하지 않습니다.")
                        return;
                    }

                    function drawBasic() {
                        var data = new google.visualization.DataTable();

                        data.addColumn('number', 'X');
                        data.addColumn('number', '총수익(원)');

                        $(result).each((idx,research)=>{
                            data.addRows([[Number(research.date), research.price]]);
                        });

                        var options = {
                        hAxis: {
                            title: '일'
                        },
                        vAxis: {
                            title: '수익(원)'
                        }
                        };

                        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

                        chart.draw(data, options);
                    }

                    google.charts.load('current', {packages: ['corechart', 'line']});
                    google.charts.setOnLoadCallback(drawBasic);
                },
                error: (jqxhr, textStatus, errorThrown)=>{
                    console.log(jqxhr, textStatus, errorThrown);
                }
            });
        }

    }

    function categoryGraph(){
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Task', 'Hours per Day'],
                ['Work', 11],
                ['Eat', 2],
                ['Commute', 2],
                ['Watch TV', 2],
                ['Sleep', 7]
            ]);

            var options = {
                title: '카테고리별 년간 수익',
                pieHole: 0.4,
            };

            var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
            chart.draw(data, options);
        }
        google.charts.load("current", { packages: ["corechart"] });
        google.charts.setOnLoadCallback(drawChart);
    }

    totalMonth();
    totalGraph();
    categoryGraph();
})

</script>



<script>
</script>



<%@ include file="/WEB-INF/views/common/footer.jsp"%>