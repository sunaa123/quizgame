<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- ログインページ -->

<%
    String error = (String)request.getAttribute("error");
%>
    
<!DOCTYPE html>
<html>

 <!-- 背景画像アニメ用 -->
      <div id="container">
        <div id="c-container">
            <canvas id="c">Sorry.</canvas>
        </div>
        <div id="c2-container">
            <canvas id="c2">Sorry.</canvas>
        </div>
    </div>
           
   <head>
      <jsp:include page="head.jsp" />
      <title>ログイン</title>
   </head>
   <body>
      <jsp:include page="header.jsp" />
      

      
      
      <main>
      <div class="login">
      
      
      
		<form id="login_form" name="login_form" method="post" action="login">
     		 <div class="login_form_top">
        		<h1>LOGIN</h1>
       			<p>アカウント名、パスワード<br/>を入力してください。</p>
      		</div>
      		<div class="login_form_btm">
        		<input type="text" name="account" placeholder="アカウント名">
       			<input type="password" name="password" placeholder="パスワード" class="pass">
        		<input type="submit" name="botton" value="LOGIN">
        		<input type="submit" name="botton" formaction="newuser"value="新規登録">
      		</div>
    
      
      
      
<%
    if(error != null) {
%>
            <div id="error"><%= error %></div>
<%
    }
%>

		</form>
	</div>




<!-- 背景画像アニメ用JS -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/1.19.0/TimelineMax.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/gsap/1.19.0/TweenMax.min.js'></script>
<script src='https://code.jquery.com/jquery-2.2.4.js'></script>
<script src='https://npmcdn.com/babel-core@5.8.38/browser.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery.imagesloaded/4.1.1/imagesloaded.pkgd.min.js'></script>
<script src="${pageContext.request.contextPath}/js/script-bg.js"></script>

      </main>
      <jsp:include page="footer.jsp" />
   </body>
</html>