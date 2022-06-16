<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- トップページ -->    

<%@ page import="cc.shinbi.java.model.Const" %>
<%@ page import="cc.shinbi.java.model.entity.User" %>
<%@ page import="cc.shinbi.java.model.entity.Quiz" %>
<%@ page import="java.util.List" %>

<%
   User loginUser = (User)session.getAttribute(Const.LOGIN_USER_KEY);
   String error = (String)request.getAttribute("error");
   List<Quiz> quizs = (List<Quiz>)request.getAttribute("quizs");
%>

<!DOCTYPE html>

<html>


   <head>
      <jsp:include page="head.jsp" />
      <title>Boost クイズゲーム</title>
      
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/top.css">
      <!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style-bg.css">-->
      
   </head>
   
    <body>

      <!-- 背景画像アニメ用 -->
      <div id="container">
        <div id="c-container">
            <canvas id="c">Sorry.</canvas>
        </div>
        <!-- 重要：表示したいアイテムは、 c1-containerとc2-containerの間にはさむこと　--> 
         <jsp:include page="header.jsp" />
        
        <div id="c2-container">
            <canvas id="c2">Sorry.</canvas>
        </div>
    </div>
     <!-- 背景画像アニメ用ここまで -->
    
     
      <main>
      
     <!-- タイトル粒子化 -->
    	<div id="wrapper">
			<canvas id="particle"></canvas>
		<!--/wrapper--></div>
      
 
      	<%--<div style="text-align: center;">--%>
      	
      	<div class="container">
      	
	     	<div class="toppage">
    		    トップページ<br/><br/>
         		クイズカテゴリーを選択してください。<br/><br/>
				<form action="${pageContext.request.contextPath}/QuizResult" method="GET" >
					<select class="genre" name="genre" >
  		
<%
	for(Quiz quiz : quizs) {
%>
                    <!--<option hidden>選択してください</option>-->
                	<option value="<%= quiz.getGenre() %>"><%= quiz.getGenre() %></option>
<%
} 
%>
                
                          
                 <!--
                            <option value="ジャンル１" selected>ジャンル１</option>
                            <option value="ジャンル２">ジャンル２</option>
                            <option value="ジャンル３">ジャンル３</option>
                            <option value="ジャンル４">ジャンル４</option>
                            <option value="ガンダム">ガンダム</option>           
                    -->  		
	</select>
<br/>
<br/>
<br/>  		
  					<div>
			 			ボタンをクリックして<br/>💣💣🔥QuizGameスタート❕🔥💣💣<br/><br/>
  						<button  class="animoButtonOverlay" type="submit">ゲームスタート</button>
  							<input id="start" type="hidden" name="start" value="start">
  					</div>
				</form>
			</div>
	
     

  			<div class="box flipRight">
  				<h2 style="text-align: center;">このクイズゲームについて　</h2>
					<p>〇正解すると10点を獲得❕💪</p>
					<p>〇制限時間10秒以内に解答しないと…💣🔥</p>
					<p>〇残り時間が多いほど、ボーナス得点も加算❕👌</p>
					<p>〇出題数は５問、全て４択問題です❕👀</p>
					<p>〇お好みの【ジャンル】が選択できます❕🎯</p>
					<p>〇スタートボタンをクリックしてスタート❕🏇</p>
					<p>〇クイズ表示で、カウントダウンがスタート❕⌛</p>
					<p>〇４択画面より正解をクリックして解答❕🎊</p>
			</div>
		</div>
    
      </main>
      
      
 	<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="  crossorigin="anonymous"></script>
	 <script src="js/particleText.js"></script>


	<script>
	$("#particle").particleText({
		text: "Boost QuizGame", // 表示させたいテキスト。改行の場合は<br>追加
		colors:["#fff","#ccc", "#ddd"], // パーティクルの色を複数指定可能
		speed: "slow", // slow, middle, high の3つから粒子が集まる速さを選択
		});
	</script>


	<!-- 背景画像アニメ用JS -->
	<script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/1.19.0/TimelineMax.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/1.19.0/TweenMax.min.js'></script>
	<script src='https://code.jquery.com/jquery-2.2.4.js'></script>
	<script src='https://npmcdn.com/babel-core@5.8.38/browser.min.js'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery.imagesloaded/4.1.1/imagesloaded.pkgd.min.js'></script>
	<script src="${pageContext.request.contextPath}/js/script-bg.js"></script>
    
     
   </body>
      
   <footer>
    <jsp:include page="footer.jsp" />
   </footer>
   
</html>