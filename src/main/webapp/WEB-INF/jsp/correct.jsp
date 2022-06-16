<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  
 <%
	float answertime = (float)session.getAttribute("answertime");
  	int tokuten = (int)session.getAttribute("tokuten");
  	String explanation = (String)session.getAttribute("explanation");
%>
 
 <%
 	answertime = answertime / 1000;
 %>
  
<!DOCTYPE html>
<html>
	<head><title>クイズ回答結果</title></head>
		<meta charset="UTF-8">
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style3.css">
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style5.css">

	<body>
		<div class="word" style="text-align: center;">

			<p id="img" class="judge TextTyping">！！！  正解です。得点10点を獲得しました！！！</p>
				<p class="judge2 TextTyping">回答にかかった時間は<%= answertime %>secです。</p>
				<p class="judge2 TextTyping">残り時間ボーナス：<%= tokuten %>点が加算されます！</p>
				<p class="judge2 TextTyping">【解説】　<%= explanation %></p>
<br/>
<br/>
<br/>
			<a class="next">次に進む場合は、ボタンを押してください。</a><br/><br/>
				<form action="${pageContext.request.contextPath}/QuizResult" method="get">
					<button class="button" name="next" value="next">次に進む</button>
				</form>
			
		<div id="img" class="stamp1 poyon" ></div>
<br/>
<br/>
<br/>

			<a href="${pageContext.request.contextPath}/top">戻る</a><br>
		</div>

 	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
 	<script src="${pageContext.request.contextPath}/js/8-10.js"></script>
        
	</body>

	<footer>
 
		<%--魔理沙、霊夢 --%>
		<div class="change_img6"><%--画像を切り替えるための処理。切り替え画像を<P>の中に記述 --%>
  			<img id="img6" class="purupuru6" src="${pageContext.request.contextPath}/css/女キリッ.png" width="200" height="200">
  			<img id="img6" class="purupuru6" src="${pageContext.request.contextPath}/css/女キリッ.png" width="200" height="200">
  		</div>
  
  
  		<div class="change_img5"><%--画像を切り替えるための処理。切り替え画像を<P>の中に記述 --%>
  			<img id="img5" class="purupuru5" src="${pageContext.request.contextPath}/css/男キリッ.png" width="200" height="200">
  			<img id="img5" class="purupuru5" src="${pageContext.request.contextPath}/css/男キリッ.png" width="200" height="200">
		</div>

	
	<%-- <section>
		<!--吹き出し-->
		<div class="balloon5" >
  			<a>正解です！！</a>
  			<%-- <a class="timer">爆発するー。</a>--%>
  		<%-- </div>
	</section>--%>
 
 	<script>
 	'use strict';
 
 		<%--ID値「img」に対してCSSアニメ―ション「poyon」を300ミリ秒の間隔を空けてループ再生--%>
 		looopAnimation("img", "poyon", 300);

 
		<%--ページを後戻りさせない処理--%>
		history.pushState(null, null, null);
		window.addEventListener('popstate', function(e) {
  			history.pushState(null, null, null);
			});
	</script>
 
 	</footer>
 
</html>