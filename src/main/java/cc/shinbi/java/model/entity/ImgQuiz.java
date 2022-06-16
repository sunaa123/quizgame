package cc.shinbi.java.model.entity;

//画像クイズの情報に関するクラス
public class ImgQuiz {
	
    //クイズ番号
	private int id;
	//クイズ内容
    private String question;
	//選択肢1～4 画像のファイル名
	private String imgChoices1;
	private String imgChoices2;
	private String imgChoices3;
	private String imgChoices4;
	//答え 画像のファイル名
	private String answer;
	//答えの説明
	private String explanation;
	
	
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
	
	public String getImgChoices1() {
		return imgChoices1;
	}
	
	public void setImgChoices1(String imgChoices1) {
		this.imgChoices1 = imgChoices1;
	}
	
	public String getImgChoices2() {
		return imgChoices2;
	}
	
	public void setImgChoices2(String imgChoices2) {
		this.imgChoices2 = imgChoices2;
	}
	
	public String getImgChoices3() {
		return imgChoices3;
	}
	
	public void setImgChoices3(String imgChoices3) {
		this.imgChoices3 = imgChoices3;
	}
	
	public String getImgChoices4() {
		return imgChoices4;
	}
	
	public void setImgChoices4(String imgChoices4) {
		this.imgChoices4 = imgChoices4;
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
}
