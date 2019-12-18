<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 폰트 -->
<link href="https://fonts.googleapis.com/css?family=Black+Han+Sans|Do+Hyeon|Dokdo|Gaegu|Gamja+Flower|Gugi|Jua|Kirang+Haerang|Nanum+Gothic+Coding|Noto+Sans+KR|Noto+Serif+KR|Stylish|Yeon+Sung&display=swap" rel="stylesheet">
<!-- 스타일시트 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/animation.css">
<!-- 스크립트 -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script src="<%=request.getContextPath()%>/js/js.js"></script>

<style>
.curtain{
    position: absolute;
    z-index: 9999;
    width: 100%;
    height: 100vh;
    background-color: rgb(48, 48, 48);
    overflow: hidden;
}
.curtain-call{
    /* animation: scale-out-ver-top 0.5s cubic-bezier(0.550, 0.085, 0.680, 0.530) both; */
    /* animation: slide-out-fwd-center 0.7s cubic-bezier(0.550, 0.085, 0.680, 0.530) both; */
    /* animation: slide-out-blurred-top 0.45s cubic-bezier(0.755, 0.050, 0.855, 0.060) both; */
    /* animation: flicker-out-1 2s linear both; */
    /* animation: scale-out-center 0.5s cubic-bezier(0.550, 0.085, 0.680, 0.530) both; */
    animation: slide-out-top 0.5s cubic-bezier(0.550, 0.085, 0.680, 0.530) both;
}
.curtain .title{
    color:white;
    text-shadow: lightskyblue 0 0 15px;
    font-size: 8ch;
    animation: vibrate-1 0.3s linear infinite both;
}
.curtain .info{
    color:white;
    text-shadow: lightskyblue 0 0 15px;
    font-size: 2ch;
}
.curtain .title:hover {
    animation: jello-horizontal 0.9s both;
}
.snow{
    position: absolute;
    color:rgb(206, 231, 245);
    text-shadow: lightskyblue 0 0 20px;
    opacity: 0.8;
    transition-duration: 0.5s;
    animation: slide-in-top 7s ease-in infinite both;
    opacity: 0;
    font-family: 'Jua', sans-serif;
}
.snow span{
    position: relative;
}
</style>

<title>semi-project</title>
</head>
<body>
    <div class="curtain">
        <div id="snowZip">
        </div>

        <div class="row h-100">
            <div class="col-sm-12 my-auto">
                <div class="text-center">
                    <p class="title" id="curtainCall">semi-project</p>
                </div>
            </div>
        </div>
    </div>
<script>
$("#curtainCall").click(function(){
    location.href = "<%=request.getContextPath()%>/product/productView";
});

function snowDrop(){
    var $inner = $("#snowZip");
    var line = 14;
    var drop = "*";
    var rndSize;
    var rndWidth;

    for(var i =0; i < line; i++){
        $inner.append("<div class='snow'></div>")
    }

    var $snow = $(".snow");

    for(var j = 0; j < line; j++){

        for(var i = 0; i < 10; i++){
            rndWidth = Math.random()*100;
            rndSize = Math.round(Math.random()*7)+1;
            $snow.eq(j).append("<span style='left: " + rndWidth + "vw;''><h" + rndSize + ">" + drop + "</h" + rndSize + "></span>");
            $snow.eq(j).css("animation-delay",j*0.3+"s");
            $snow.eq(j).css("animation-duration",2+(j/line)*j+"s");
        }
    }
}
snowDrop();

(()=>{
    var $drop = $(".snow");

    $("#curtainCall").hover(function(){
        $drop.css("color","lightgoldenrodyellow");
        $drop.children().children().text("★");
    },function(){
        $drop.css("color","rgb(206, 231, 245)");
        $drop.children().children().text("*");
    });
})();
</script>
</body>
</html>

