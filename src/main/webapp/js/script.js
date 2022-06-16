//ユーザーの新規作成に関する処理
function addUser() {
	$('#operation_input').val('new');
	$('#user_form').submit();
}

//ユーザーの編集に関する処理
function editUser(id) {
	$('#operation_input').val('edit');
	$('#user_id_input').val(id);
	$('#user_form').submit();
}

//ユーザーの削除に関する処理
function deleteUser(id) {
	var ret = confirm('ユーザーを削除してもよろしいですか？');
	if(ret) {
		$('#operation_input').val('delete');
		$('#user_id_input').val(id);
		$('#user_form').submit();
	}
}

//クイズの新規作成に関する処理
function addQuiz() {
	$('#operation_quiz').val('new');
	$('#quiz_form').submit();
}

//クイズの削除に関する処理
function deleteQuiz(id) {
	var ret = confirm('クイズを削除してもよろしいですか？');
	if(ret) {
		$('#operation_quiz').val('delete');
		$('#quiz_id_input').val(id);
		$('#quiz_form').submit();
	}
}

//ユーザーの編集に関する処理
function editQuiz(id) {
	$('#operation_quiz').val('edit');
	$('#quiz_id_input').val(id);
	$('#quiz_form').submit();
}

//editUser.jspで答えになる選択肢を取得して<select>の初期値として選ぶ処理
function selectAnswer (answer) {
	if(answer == 1) {
	   	$('#answerselect').val('1');
  	}else if(answer == 2){
 		$('#answerselect').val('2');
  	}else if(answer == 3){
  		$('#answerselect').val('3');
  	}else if(answer == 4){
  		$('#answerselect').val('4');
  	}
}
