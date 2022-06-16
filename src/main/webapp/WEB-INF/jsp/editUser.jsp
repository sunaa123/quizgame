<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- ユーザー編集ページ -->
    
<%@ page import="cc.shinbi.java.model.entity.User" %>

<%
   String error = (String)request.getAttribute("error");
   User user = (User)request.getAttribute("user") ;
   int id = 0;
   String account = "";
   String name = "";
   String adminOption = "";
   String userOption = "checked";
   String submitName = "登録";
   String operationName = "add";
   if(user != null) {
	   id = user.getId();
	   account = user.getAccount();
	   name = user.getName();
	   if(user.isAdmin()) {
		   adminOption = "checked";
		   userOption = "";
	   }
	   submitName = "更新";
	   operationName = "update";
   }
%>

<!DOCTYPE html>
<html>
   <head>
      <jsp:include page="head.jsp" />
      <title>ユーザー編集</title>
   </head>
   <body>
      <jsp:include page="header.jsp" />
      
      <main>
         <form id="edit_form" method="post" action="user">
            <table id="edit_table">
               <tr>
                  <th>アカウント名</th>
                  <td><input type="text" name="account" value="<%= account %>" placeholder="アカウント名を入力してください"></td>
               </tr>
               <tr>
                  <th>名前</th>
                  <td><input type="text" name="name" value="<%= name %>" placeholder="名前を入力してください"></td>
               </tr>
               <tr>
                  <th>権限</th>
                  <td>
                     <input type="radio" name="is_admin" value="true" <%= adminOption %>> 
                        <label id="radio">管理者</label>
                     <input type="radio" name="is_admin" value="false"<%= userOption %>> 
                        <label id="radio">一般</label>
                  </td>
               </tr>
               <tr>
                  <th>パスワード</th>
                  <td><input type="password" name="password" placeholder="パスワードを入力してください"></td>
               </tr>
               <tr>
                  <th>パスワード(確認)</th>
                  <td><input type="password" name="confirmed" placeholder="もう一度パスワードを入力してください"></td>
               </tr>
               <tr>
                  <td>
                     <input id="submit" type="submit" value="<%= submitName %>">
                  </td>
               </tr>
            </table>
            <input type="hidden" name="id" value="<%= id %>">
            <input type="hidden" name="operation" value="<%= operationName %>">
         </form>
<%
   if(error != null) {
%>
         <div id="error"><%= error %></div>
<%
   }
%>
      </main>
     <%-- <jsp:include page="footer.jsp" /> --%>
   </body>
</html>