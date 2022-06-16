<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 共通のheader -->

<%@ page import="cc.shinbi.java.model.entity.User" %>
<%@ page import="cc.shinbi.java.model.Const" %>

<%
   User user = (User)session.getAttribute(Const.LOGIN_USER_KEY);
%>

<header>

	<!--タイトル粒子化-->
	<div id="wrapper">
		<canvas id="particle"></canvas>
		<!--/wrapper--></div>


<%
    if(user != null) {
%>
         <jsp:include page="menu.jsp" />
<%
    }
%>
    
    <%--<div id="title">
         <h1 id="systemName">Boost クイズゲーム</h1>
    </div>--%>
    
<%
    if(user != null) {
%>
        <div id="welcome">
           <br/><%= user.getName() %> さん。ようこそ。
        </div>
<%
    }
%>



<!--タイトル粒子化Js-->
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="  crossorigin="anonymous"></script>
<script src="js/particleText.js"></script>
<script>
$("#particle").particleText({
	text: "Boost QuizGame", // 表示させたいテキスト。改行の場合は<br>追加
	colors:["#fff","#fff", "#fff"], // パーティクルの色を複数指定可能
	speed: "middle", // slow, middle, high の3つから粒子が集まる速さを選択
	});
</script>

</header>


