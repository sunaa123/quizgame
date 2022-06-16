<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="style3.css">
	<title>クイズゲーム</title>
</head>
<body>

<h1>Boost Quiz Game</h1>
		<p>このクイズゲームについて</p>
		<p>〇クイズに正解すると10点を獲得! 不正解なら0点となります。</p>
		<p>〇問題には制限時間があります。10秒以内に解答してください。</p>
		<p>〇正解後の制限時間／残り時間により、ボーナス得点が加算されます。</p>
		<p>〇出題数は５問、四択問題です。</p>
		<p>〇正答と思うものを、四択画面からｸﾘｯｸして解答してください。</p>
		<p>〇お好きなクイズの【ジャンル】を、選択してください。</p>
		<p>〇スタートボタンをクリックすると、クイズがスタートします。</p>
		<p>〇スタート画面が表示された時点で、カウントダウンが始まります。</p>
<div>
<input type="button" value="Start" id="question" onClick="http://localhost:8080/BPquizgame/QuizQ1.jsp'">
</div>

</body>
</html>
   