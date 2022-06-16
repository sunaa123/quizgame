package cc.shinbi.java.model.entity;

//文字のクイズの情報に関するクラス
public class Quiz {
	
	//クイズ番号
	private int id;
    //クイズ内容
	private String question;
	//選択肢1～4
	private String choices1;
	private String choices2;
	private String choices3;
	private String choices4;
	//答え
	private String answer;
	//答えの説明
	private String explanation;
	//クイズのジャンル
	private String genre;//Gener→Genreに修正（小田）
	
	
	//getter&setter
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getChoices1() {
		return choices1;
	}
	
	public void setChoices1(String choices1) {
		this.choices1 = choices1;
	}
	
	public String getChoices2() {
		return choices2;
	}
	
	public void setChoices2(String choices2) {
		this.choices2 = choices2;
	}
	
	public String getChoices3() {
		return choices3;
	}
	
	public void setChoices3(String choices3) {
		this.choices3 = choices3;
	}
	
	public String getChoices4() {
		return choices4;
	}
	
	public void setChoices4(String choices4) {
		this.choices4 = choices4;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getExplanation() {
		return explanation;
	}
	
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	public String getGenre() {//Gener→Genreに修正（小田）
		return genre;//Gener→Genreに修正 （小田）
	}
	
	public void setGenre(String genre) {//Gener→Genreに修正（小田）
		this.genre = genre;//Gener→Genreに修正（小田）
	}
}
