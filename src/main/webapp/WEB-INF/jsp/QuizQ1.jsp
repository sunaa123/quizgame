<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String question = (String)session.getAttribute("question");//QuizQuestion1から質問を受け取るためセッションに接続
	String choices1 = (String)session.getAttribute("choices1");//QuizQuestion1から回答1を受け取るためセッションに接続
	String choices2 = (String)session.getAttribute("choices2");//QuizQuestion1から回答2を受け取るためセッションに接続
	String choices3 = (String)session.getAttribute("choices3");//QuizQuestion1から回答3を受け取るためセッションに接続
	String choices4 = (String)session.getAttribute("choices4");//QuizQuestion1から回答4を受け取るためセッションに接続
%>
        
    
<!DOCTYPE html>
<html>
	
	<head>
        <title>クイズ出題画面サンプル</title>
    </head>

	<meta charset="UTF-8">
	<meta http-equiv="refresh" content="11.5; url=${pageContext.request.contextPath}/timeover.jsp" />
		<input id="Question1" type="hidden" name="Q1" value="Q1"> 
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style3.css">
	
	<body>
	  
	<header class="header">
        <div class="ribbon20-wrapper delay-time04 box fadeUp">
  			<h3 class="ribbon20">Question!!!</h3>
  			<h3><%= question %></h3>
         </div>
    </header>

        
		<form action="${pageContext.request.contextPath}/QuizQuestion1" method="GET">
		<%--<input id="Question1" type="hidden" name="Q1" value="Q1">--%> 
	
			<!--    <audio id="sound" preload="auto">
    		<source src="${pageContext.request.contextPath}/css/nc97718.mp3" type="audio/mp3">
    		</audio>-->  
	
		<div class="container">
			<button class="box fadeUp btn btn-flatw" name="1" value="1" onclick="sound()"><%= choices1 %></button>
    		<button class="delay-time03 box fadeUp btn btn-flat" name="2" value="2" onclick="sound()"><%= choices2 %></button>
    			<audio id="sound" preload="auto">
    				<source src="${pageContext.request.contextPath}/css/nc2039.mp3" type="audio/mp3">
    			</audio>
        
			<div class=bomb></div>
		</div>
	
		<div class="container">
    		<button class="delay-time02 box fadeUp btn btn-flat" name="3" value="3" onclick="sound()"><%= choices3 %></button>
    		<button class="delay-time04 box fadeUp btn btn-flat" name="4" value="4" onclick="sound()"><%= choices4 %></button>
        		<audio id="sound" preload="auto">
    				<source src="${pageContext.request.contextPath}/css/nc2039.mp3" type="audio/mp3">
    			</audio>
    	</div>
	</form>

	<section>
    	<div class=timer style="text-align: center;">
    		<p class="timer">あと<span id="timer">10</span>秒</p>
		</div>
	</section>
<br/>
<br/>
			<a href="${pageContext.request.contextPath}/top">戻る</a><br>

			<%--魔理沙、霊夢 --%>
			<p class="change_img2"><%--画像を切り替えるための処理。切り替え画像を<P>の中に記述 --%>
  				<img id="img2" class="poyooon2" src="${pageContext.request.contextPath}/css/女おうえん.png" width="200" height="200">
  				<img id="img2" class="poyooon2" src="${pageContext.request.contextPath}/css/女おどろく.png" width="200" height="200">
  			</p>
  
		<div id="hideMe" class="timer balloon1"><%--吹き出し--%>
			<p id="hideMe" class="timer">がんばれーがんばれー</p>
  			<%--<p class="timer">しぬー。しぬー。</p>--%>
		</div>
  
  			<p class="change_img1"><%--画像を切り替えるための処理。切り替え画像を<P>の中に記述 --%>
				<img id="img2" class="poyooon1" src="${pageContext.request.contextPath}/css/男おうえん.png" width="200" height="200">
				<img id="img2" class="poyooon1" src="${pageContext.request.contextPath}/css/男おどろく.png" width="200" height="200">
			</p>
	
	<section>
		<%--吹き出し--%>
		<div id="hideMe" class="timer balloon2" >
 			 <p id="hideMe" class="timer"><span id="Timer"></span>秒しかないよ！急げー</p>
  			<%-- <p class="timer">爆発するー。</p>--%>
		</div>
	</section>

	</body>


	<footer>

  	<script>
    // ID値「mitarashi」に対してCSSアニメ―ション「poyooon」を600ミリ秒の間隔を空けてループ再生
    	looopAnimation("mitarashi", "poyooon", 600);
  	</script>


			<audio id="sound" preload="auto">
    			<source src="http://idotdesign.net/wp-content/themes/idotdesign/sound/sound.wav" type="audio/wav">
     		</audio>

		<div class="bomb black"><%--爆弾の設定 --%>
			<span class="ledge"></span>
  			<div class="fuse"></div>
  				<div class="dust">
    				<span></span>
    				<span></span>
    				<span></span>
    				<span></span>
    				<span></span>
    				<span></span>
    				<span></span>
    				<span></span>
    				<span></span>
    				<span></span>
    				<span></span>
    				<span></span>
				<p class="msg orange">Time Over!!!</p>
  				</div>
		</div>

 	<script>
 	'use strict';
 
 		<%--ページを後戻りさせない処理--%>
 		history.pushState(null, null, null);
 			window.addEventListener('popstate', function(e) {
   			history.pushState(null, null, null);
 			});


		<%--カウントダウンタイマー--%>
		let count = 11;<%--実際の表示に合わせて、スタート位置を決めている--%>
 			const countUp = () => {
   				console.log(count--);<%--カウントダウンなので、"--"を記入。カウントアップの時は"++"を記入--%>
   				document.querySelector('#timer').textContent=count;<%--結果を表示させるための記述--%>
   				document.querySelector('#Timer').textContent=count;<%--吹き出しに結果を表示させるための記述--%>
   				const timeoutId = setTimeout(countUp, 1000);<%--カウントを表示させるための記述。カウントダウン設定だが、カウントをUP(表示)する意味で"countUp"を記載--%>
   			if(count <= 0){　
     			clearTimeout(timeoutId);<%--timeoutIdをclearTimeoutで指定している--%>
   				}
 			}
	 	countUp();

	</script>

  
	</footer>

</html>