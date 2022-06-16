<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int tokutentotal = (int) session.getAttribute("tokutentotal"); 
	int count = (int) session.getAttribute("count");
%>

  
<%
	int rate = count/5*100;
	int totalresult = count*10 + tokutentotal;
	int rank = (int) request.getAttribute("rank"); 
%>
    
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<title>結果画面</title>

<link rel="stylesheet" href="last body.css">

<meta name="viewport" content="width=device-width,initial-scale=1.0">
<!--==============レイアウトを制御する独自のCSSを読み込み===============-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/last 1.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/last slowly.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/last 3.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/last 5-8.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/last reset.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/last delya.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style5.css">

</head>

<body>
  

  <h1 style="text-align:center"></h1>
  
    <div id="particles-js"></div>
    <div id="wrapper">
  

  <p><span class="blurTrigger"><img src="${pageContext.request.contextPath}/css/がんばりましょう.png" width="350" height="350"> <br/> 
 
  
  </span>
  </p>

<div class="text-align:center">
  <div class="box fadeUp">正解得点合計<%= count*10 %>点</div>
  <div class="delay-time01 box fadeUp">残り時間ボーナス<%= tokutentotal %>点</div>
  <div class="delay-time02 box fadeUp">総合得点<%= totalresult %>点</div>
  <div class="delay-time03 box fadeUp">あなたのランキングは<%= rank %>位</div>
  <button type="button" class="button" onclick=location.href="${pageContext.request.contextPath}/top">トップページへ戻る</button>
 
</div>

<!--/wrapper--></div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="  crossorigin="anonymous"></script>
<script src="http://coco-factory.jp/ugokuweb/wp-content/themes/ugokuweb/data/move02/8-9/js/8-9.js"></script>
<script src=""></script>
  <script src=""></script>
  
</body>

<footer>

<%--魔理沙、霊夢 --%>
<div class="change_img8"><%--画像を切り替えるための処理。切り替え画像を<P>の中に記述 --%>
  <img id="imglast3" class="korokoro8" src="${pageContext.request.contextPath}/css/女ぴえん.png" width="200" height="200">
  <img id="imglast3" class="korokoro8" src="${pageContext.request.contextPath}/css/女ぴえん.png" width="200" height="200">
  </div>
  
  <%-- <div class="balloon4"><!--吹き出し-->--%>
  <%-- <a>タイムオーバーです。</a> --%>
  <%--<a class="timer">しぬー。しぬー。</a>--%>
<%-- </div> --%>
  
  <div class="change_img7"><%--画像を切り替えるための処理。切り替え画像を<P>の中に記述 --%>
  <img id="imglast3" class="korokoro7" src="${pageContext.request.contextPath}/css/男ぴえん.png" width="200" height="200">
  <img id="imglast3" class="korokoro7" src="${pageContext.request.contextPath}/css/男ぴえん.png" width="200" height="200">
	</div>
	

<!--吹き出し-->
<%-- <div class="balloon3" >
  <a>たいむおーばーです。</a>  --%>
  <%-- <a class="timer">爆発するー。</a>--%>
  <%-- </div>--%>

</footer>

</html>