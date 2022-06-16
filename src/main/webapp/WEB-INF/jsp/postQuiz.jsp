<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="cc.shinbi.java.model.entity.User" %>
<%@ page import="cc.shinbi.java.model.entity.Quiz" %>
<%@ page import="cc.shinbi.java.model.Const" %>

<%
   User loginUser = (User)session.getAttribute(Const.LOGIN_USER_KEY);
// String error = (String)request.getAttribute("error");
%>
    
<!DOCTYPE html>
<html>
   <head>
      <jsp:include page="head.jsp" />
      <title>クイズゲーム</title>
   </head>
   
    <body>
      <jsp:include page="header.jsp" />
      <main>
         <form id="edit_form" method="post" action="postquiz">
         <input type="hidden"  name="operation" value="add">
            <table id="postquiz">
                <tr>
                    <th>クイズ内容</th>
                    <td><input type="text" name="question" placeholder="クイズ内容を入力してください"></td>
                </tr>
                <tr>
                    <th>選択肢1</th>
                    <td><input type="text" name="choices1" placeholder="選択肢1を入力してください"></td>
                </tr>
                <tr>
                    <th>選択肢2</th>
                    <td><input type="text" name="choices2" placeholder="選択肢2を入力してください"></td>
                </tr>
                <tr>
                    <th>選択肢3</th>
                    <td><input type="text" name="choices3" placeholder="選択肢3を入力してください"></td>
                </tr>
                <tr>
                    <th>選択肢4</th>
                    <td><input type="text" name="choices4" placeholder="選択肢4を入力してください"></td>
                </tr>
                <tr>
                    <th>答え</th>
                    <td>  
                    <div class="cp_ipselect cp_sl01">
                       <select name="answer" >
                          <option value="1">選択肢1</option>
                          <option value="2">選択肢2</option>
                          <option value="3">選択肢3</option>
                          <option value="4">選択肢4</option>
                       </select>
                    </div>
                    </td>
                </tr>
                <tr>
                    <th>説明</th>
                    <td><input type="text" name="explanation" placeholder="クイズの説明を入力してください"></td>
                </tr>
                <tr>
                    <th>クイズジャンル</th>
                    <td><input type="text" name="genre" placeholder="クイズのジャンルを入力してください"></td>
                </tr>
                <tr>
                    <td>
                    <div id="touroku">
                        <input id="submit" type="submit" value="クイズを登録">
                    </div>
                    </td>
                </tr>
            </table>
        </form>
      </main>
      <%--<jsp:include page="footer.jsp" />--%>
   </body>
</html>