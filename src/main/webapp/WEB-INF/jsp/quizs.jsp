<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- クイズ一覧ページ -->

<%@ page import="java.util.List" %>
<%@ page import="cc.shinbi.java.model.entity.Quiz" %>

<%
   List<Quiz> quizs = (List<Quiz>)request.getAttribute("quizs"); 
   String error = (String)request.getAttribute("error");
%>


<!DOCTYPE html>
<html>
   <head>
      <jsp:include page="head.jsp" />
      <title>クイズ一覧</title>
   </head>
   <body>
      <jsp:include page="header.jsp" />
      
      <h1>クイズ一覧</h1>
      
       <form id="quiz_form" action="postquiz" method="post">
            <input type="hidden" id="quiz_id_input" name="id" value="">
            <input type="hidden" id="operation_quiz" name="operation" value="">
       </form>
      
      <div>
            <a href="javascript:addQuiz()">
               <span id="addicon" class="icon far fa-plus-square"></span>
            </a>
       </div>
       

       <table id="tables">
            <tr>
               <th width="85%">質問内容</th>
               <th width="15%">操作</th>
            </tr>
<%
   for(Quiz quiz : quizs) {
%>
            <tr>
               <td><%= quiz.getQuestion() %></td>
               <td>
                  <a href="javascript:editQuiz(<%= quiz.getId() %>)">
                     <span class="icon fas fa-pencil-alt"></span>
                  </a>
                  <a href="javascript:deleteQuiz(<%= quiz.getId() %>)">
                     <span class="icon far fa-trash-alt"></span>
                  </a>
               </td>
            </tr>
<%
   }
%> 
         </table>
      

      <%--<jsp:include page="footer.jsp" />--%>
   </body>
</html>