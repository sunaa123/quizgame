<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ユーザー新規登録ページ -->
    
<%@ page import="cc.shinbi.java.model.entity.User" %>
<%
   String error = (String)request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
   <head>
       <meta charset="UTF-8">
       <link rel="stylesheet" type="text/css" 
       href="${pageContext.request.contextPath}/css/style.css">
      <title>新規登録</title>
   </head>
   <body>
      <jsp:include page="header.jsp" />
      
      <main>
         <form id="edit_form" method="post" action="newuser" style="top: 60%;">
            <table id="edit_table">
               <tr>
                  <th>アカウント名</th>
                  <td><input type="text" name="account" placeholder="アカウント名を入力してください"></td>
               </tr>
               <tr>
                  <th>名前</th>
                  <td><input type="text" name="name" placeholder="名前を入力してください" ></td>
               </tr>
               <tr>
                  <th>パスワード</th>
                  <td><input type="password" name="password" placeholder="パスワードを入力してください"></td>
               </tr>
               <tr>
                  <th>パスワード(確認)</th>
                  <td><input type="password" name="confirmed" placeholder="パスワードをもう一度入力してください"></td>
               </tr>
               <tr>
                  <td>
                     <input id="submit" type="submit">
                  </td>
               </tr>
            </table>
            <input type="hidden" name="is_admin" value="false">
            <input type="hidden" name="operation" value="add">
         </form>
<%
   if(error != null) {
%>
         <div id="error"><%= error %></div>
<%
   }
%>
      </main>
      <%--<jsp:include page="footer.jsp" />--%>
   </body>
</html>