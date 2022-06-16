<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- メッセージ編集ページ -->
    
<%@ page import="cc.shinbi.java.model.entity.User" %>
<%@ page import="cc.shinbi.java.model.entity.Quiz" %>
<%@ page import="cc.shinbi.java.model.Const" %>

<%
   Quiz quiz = (Quiz)request.getAttribute("quiz");
   User loginUser = (User)session.getAttribute(Const.LOGIN_USER_KEY);
   String error = (String)request.getAttribute("error");
   //選択したquizオブジェクトの情報をgetで取得
   int id = quiz.getId();
   String question = quiz.getQuestion();
   String choices1 = quiz.getChoices1();
   String choices2 = quiz.getChoices2();
   String choices3 = quiz.getChoices3();
   String choices4 = quiz.getChoices4();
   String answer = quiz.getAnswer();
   String explanation = quiz.getExplanation();
   String genre = quiz.getGenre();
%>
    
<!DOCTYPE html>
<html>
   <head>
      <jsp:include page="head.jsp" />
      <title>クイズゲーム</title>
   </head>
   
    <body onLoad="selectAnswer(<%= answer %>)">
      <jsp:include page="header.jsp" />
      <main>
         <form id="edit_form" method="post" action="postquiz">
         <!-- 変数にはgetで取得した値が入る -->
         <input type="hidden" id="quiz_id_input" name="id" value="<%= id %>">
         <input type="hidden"  name="operation" value="update">
            <table id="edit_table">
                <tr>
                    <th>クイズ内容</th>
                    <td><input type="text" name="question" value="<%= question %>"  placeholder="クイズ内容を入力してください"></td>
                </tr>
                <tr>
                    <th>選択肢1</th>
                    <td><input type="text" name="choices1" value="<%= choices1 %>" placeholder="選択肢1を入力してください"></td>
                </tr>
                <tr>
                    <th>選択肢2</th>
                    <td><input type="text" name="choices2" value="<%= choices2 %>" placeholder="選択肢2を入力してください"></td>
                </tr>
                <tr>
                    <th>選択肢3</th>
                    <td><input type="text" name="choices3" value="<%= choices3 %>" placeholder="選択肢3を入力してください"></td>
                </tr>
                <tr>
                    <th>選択肢4</th>
                    <td><input type="text" name="choices4" value="<%= choices4 %>" placeholder="選択肢4を入力してください"></td>
                </tr>
                <tr>
                    <th>答え</th>
                    <td>
                    <div class="cp_ipselect cp_sl01">
                       <select id="answerselect" name="answer">
                          <option value="1" id="answer1">選択肢1</option>
                          <option value="2" id="answer2">選択肢2</option>
                          <option value="3" id="answer3">選択肢3</option>
                          <option value="4" id="answer4">選択肢4</option>

                       </select>
                    </div>
                </tr>
                <tr>
                    <th>説明</th>
                    <td><input type="text" name="explanation" value="<%= explanation %>" placeholder="クイズの説明を入力してください"></td>
                </tr>
                <tr>
                    <th>クイズジャンル</th>
                    <td><input type="text" name="genre" value="<%= genre %>" placeholder="クイズのジャンルを入力してください"></td>
                </tr>

                <tr>
                    <td>
                        <input id="submit" type="submit" value="クイズを更新">
                    </td>
                </tr>
            </table>
        </form>
      </main>
      <%--<jsp:include page="footer.jsp" />--%>
   </body>
</html>