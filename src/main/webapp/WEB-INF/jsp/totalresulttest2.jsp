<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  
<%
	int tokutentotal = (int) session.getAttribute("tokutentotal"); 
	int count = (int) session.getAttribute("count"); 
%>
  
<%
	int rate = count/5*100;
	int totalresult = count*10 + tokutentotal;
%>
    
    <!DOCTYPE html>
<html>
<head><title>ご褒美画面</title></head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style3.css">
<body style="background-color:orchid;"><div style="text-align: center;">
<h2>まあまあです。</h2><br/>
<h3>あなたの結果は5問中、<%= count %>問正解でした。</h3>
<h4>●●総合得点は、<%= totalresult %> 点でした！●●</h4><br/><br/><br/>
ホーム画面へ戻る場合は、以下のボタンをクリックしてください。<br/><br/>
	<button type="button" onclick=location.href="${pageContext.request.contextPath}/top">トップページへ戻る</button>
<br/>
<hr>
<a href="${pageContext.request.contextPath}/starttest.jsp">戻る</a><br>
</div></body>
</html>