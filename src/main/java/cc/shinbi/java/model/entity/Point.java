package cc.shinbi.java.model.entity;

import java.sql.Timestamp;

//スコア情報に関するクラス
public class Point {
	//スコアID
	private int id;
	//ユーザーのID
	private int userId;
	//ユーザーの名前
	private String name;
	//スコア
	private int score;
	//記録した時間
	private Timestamp createdAt;
	
	
	//getter&setter
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
