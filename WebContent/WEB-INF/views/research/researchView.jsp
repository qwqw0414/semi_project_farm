<%@page import="java.util.GregorianCalendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<div id="researchView">
    <h1 class="main-color text-center page-title">수익 통계</h1>
	<hr class="divide"/>
    <!-- 월간 총 수익 -->

    <div class="form-row align-items-center mx-auto" style="width:20%;">
        <div class="col-xs-2">
            <select class="custom-select mr-sm-2" id="selectYear">
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
		</div>
		<div class="col-xs-2">
            <select class="custom-select mr-sm-2" id="selectMonth">
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
<br>
    <div class="row">
        <div class="col">
            <h2 class="main-color page-title text-center" id="research-title"></h2>
        </div>
        <h2 class="main-color page-title">|</h2>
        <div class="col">
            <h2 class="main-color page-title text-center" id="totalMonth"></h2>
        </div>
    </div>
</div>
 <hr class="divide-sm">
<!-- 그래프 -->
<!-- 선형 그래프 div -->
<div id="chart_div" style="height: 400px;"></div>
<hr class="divide-sub">
<!-- 원형 그래프 div -->
<div class="mx-auto" id="donutchart" style="width: 900px; height: 500px;"></div>

<script>
$(()=>{
    var $totalMonth = $("#researchView #totalMonth");
    var $selectYear = $("#researchView #selectYear");
    var $selectMonth = $("#researchView #selectMonth");
    var $researchTitle = $("#researchView #research-title");

    //이벤트
    $($selectYear).change(()=>{totalMonth()});
    $($selectYear).change(()=>{totalGraph()});
    $($selectYear).change(()=>{categoryGraph()});
    $($selectYear).change(()=>{researchTitle()});
    $($selectMonth).change(()=>{totalMonth()});
    $($selectMonth).change(()=>{totalGraph()});
    $($selectMonth).change(()=>{categoryGraph()});
    $($selectMonth).change(()=>{researchTitle()});

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
        if($selectMonth.val() == 0){
        //카테고리별 년간 수익
            $.ajax({
                url:"<%=request.getContextPath()%>/research/dataListByYearSetCate",
                type: "post",
                data: {year: $selectYear.val()},
                dataType: "json",
                success: result =>{
                    //내역 존재여부 확인
                    if(result.length == 0){
                        $("#donutchart").html("거래내역이 존재하지 않습니다.")
                        return;
                    }
                    //데이터 전처리
                    var vegetable = 0;
                    var Fruit = 0;
                    var Meat = 0;
                    var seafood = 0;
                    var mushroom = 0;

                    $(result).each((idx,research)=>{
                        switch (research.category) {
                            case '채소': vegetable = research.price; break;
                            case '과일': Fruit = research.price; break;
                            case '육류': Meat = research.price; break;
                            case '해산물': seafood = research.price; break;
                            case '버섯': mushroom = research.price; break;
                        }
                    });

                    function drawChart() {
                        var data = google.visualization.arrayToDataTable([
                            ['Task', 'Hours per Day'],
                            ['채소', vegetable],
                            ['과일', Fruit],
                            ['육류', Meat],
                            ['해산물', seafood],
                            ['버섯', mushroom]
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

                },
                error: (jqxhr, textStatus, errorThrown)=>{
                    console.log(jqxhr, textStatus, errorThrown);
                }
            });
        }
        else{
        //카테고리별 월간 수익
            $.ajax({
                url:"<%=request.getContextPath()%>/research/dataListByMonthSetCate",
                type: "post",
                data: {year: $selectYear.val(),
                       month: $selectMonth.val()
                       },
                dataType: "json",
                success: result =>{
                    //내역 존재여부 확인
                    if(result.length == 0){
                        $("#donutchart").html("거래내역이 존재하지 않습니다.")
                        return;
                    }
                    //데이터 전처리
                    var vegetable = 0;
                    var Fruit = 0;
                    var Meat = 0;
                    var seafood = 0;
                    var mushroom = 0;

                    $(result).each((idx,research)=>{
                        switch (research.category) {
                            case '채소': vegetable = research.price; break;
                            case '과일': Fruit = research.price; break;
                            case '육류': Meat = research.price; break;
                            case '해산물': seafood = research.price; break;
                            case '버섯': mushroom = research.price; break;
                        }
                    });

                    function drawChart() {
                        var data = google.visualization.arrayToDataTable([
                            ['Task', 'Hours per Day'],
                            ['채소', vegetable],
                            ['과일', Fruit],
                            ['육류', Meat],
                            ['해산물', seafood],
                            ['버섯', mushroom]
                        ]);

                        var options = {
                            title: '카테고리별 월간 수익',
                            pieHole: 0.4,
                        };

                        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
                        chart.draw(data, options);
                    }
                    google.charts.load("current", { packages: ["corechart"] });
                    google.charts.setOnLoadCallback(drawChart);

                },
                error: (jqxhr, textStatus, errorThrown)=>{
                    console.log(jqxhr, textStatus, errorThrown);
                }
            });
        }
    }

    function researchTitle(){
        var str = "";

        str += $selectYear.val() + '년 '

        if($selectMonth.val() != 0)
            str += $selectMonth.val() + '월 '

        str += '간 총 수입'
        $researchTitle.text(str);
    }

    totalMonth();
    totalGraph();
    categoryGraph();
    researchTitle();
})

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>