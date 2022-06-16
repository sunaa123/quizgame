<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ランキングページ-->

<%@ page import="java.util.List" %>
<%@ page import="cc.shinbi.java.model.entity.Point" %>
<%@ page import="cc.shinbi.java.model.entity.User" %>

<%
   List<Point> points = (List<Point>)request.getAttribute("points");
   String error = (String)request.getAttribute("error");
   User user = (User)request.getAttribute("user") ;
   int i = 1;
%>


<!DOCTYPE html>
<html>
   <head>
      <jsp:include page="head.jsp" />
      <title>ランキング</title>
   </head>
   <body>
      <jsp:include page="header.jsp" />
      
      <h1>ランキング</h1>
       
       <table id="tables">
            <tr>
               <th width="25%">順位</th>
               <th width="25%">名前</th>
               <th width="25%">得点</th>
               <th width="25%">日付</th>
            </tr>
<%
   for(Point point : points) {
%>
            <tr>
               <td><%= i %>位</td>
               <td><%= point.getName() %>
               <td><%= point.getScore() %></td>
               <td><%= point.getCreatedAt() %>
            </tr>
<%
   i++;
   }
%> 
         </table>
      

      <%--<jsp:include page="footer.jsp" />--%>
   </body>
</html>