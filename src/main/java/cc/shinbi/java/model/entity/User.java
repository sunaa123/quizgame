package cc.shinbi.java.model.entity;

//ユーザー情報に関するクラス
public class User {
	
	//ユーザーID
	private int id;
	//ユーザーのアカウント名
	private String account;
	//ユーザーの名前
	private String name;
	//ユーザーのパスワード
	private String password;
	//salt
	private String salt;
	//管理者かどうか
	private boolean admin;
	
	
	//getter&setter
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
