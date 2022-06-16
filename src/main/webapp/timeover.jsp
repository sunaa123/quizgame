<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String answer = (String)session.getAttribute("answer");
	String explanation = (String)session.getAttribute("explanation");
  	String result = (String)session.getAttribute("result");
  	String operation = null;
%>

    
<!DOCTYPE html>
<html>
	<head><title>タイムオーバー</title></head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style4.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style5.css">
	<body>

		<p class="bounce-top"></p>
<br/>
		<div class="next">
			<a class="TextTyping">正解は ”<%= result %>" でした。</a>
<br/>
			<a class="TextTyping">【解説】　<%= explanation %></a>
<br/>
<br/>
			<a>次に進む場合は、ボタンを押してください。</a>
<br/>
			
			<form action="${pageContext.request.contextPath}/QuizResult" method="get">
				<button  class="button" class="bt" name="over" value="over" style="font-size:20px;">次に進む</button>
			</form>
		</div>
<br/>
<br/>
<br/>
<br/>
<br/>
			<a href="${pageContext.request.contextPath}/top">戻る</a><br>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/8-10.js"></script>
        

	</body>

	<footer>

<%--魔理沙、霊夢 --%>
<div class="change_img2"><%--画像を切り替えるための処理。切り替え画像を<P>の中に記述 --%>
  <img id="img2" class="puyon2" src="${pageContext.request.contextPath}/css/女ぬける.png" width="200" height="200">
  <img id="img2" class="puyon2" src="${pageContext.request.contextPath}/css/女ぬける.png" width="200" height="200">
  </div>
  
  <%-- <div class="balloon4"><!--吹き出し-->--%>
  <%-- <a>タイムオーバーです。</a> --%>
  <%--<a class="timer">しぬー。しぬー。</a>--%>
<%-- </div> --%>
  
  <div class="change_img1"><%--画像を切り替えるための処理。切り替え画像を<P>の中に記述 --%>
  <img id="img2" class="puyon1" src="${pageContext.request.contextPath}/css/男ぬける.png" width="200" height="200">
  <img id="img2" class="puyon1" src="${pageContext.request.contextPath}/css/男ぬける.png" width="200" height="200">
	</div>


<!--吹き出し-->
<%-- <div class="balloon3" >
  <a>たいむおーばーです。</a>  --%>
  <%-- <a class="timer">爆発するー。</a>--%>
  <%-- </div>--%>



 	 <script>
 		'use strict';
 
		<%--ページを後戻りさせない処理--%>
		history.pushState(null, null, null);
		window.addEventListener('popstate', function(e) {
  			history.pushState(null, null, null);
		});
 	</script>
 	
 	</footer>
 	
</html>