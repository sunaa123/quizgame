package cc.shinbi.java.model.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import cc.shinbi.java.model.Const;
import cc.shinbi.java.model.entity.User;


//ユーザーのデータアクセスオブジェクトに関するクラス
public class UserDAO extends DAO<User> {
	public UserDAO(Connection connection) 
			throws SQLException, NoSuchAlgorithmException {
		super("users", connection);
		this.initialize();
	}
	
	
	//ユーザーオブジェクトを作成する処理
	@Override
	protected User createEntity(ResultSet resultSet) throws SQLException {
		User user = new User();
		
		user.setId(resultSet.getInt("id"));
		user.setAccount(resultSet.getString("account"));
		user.setName(resultSet.getString("name"));
		user.setAdmin(resultSet.getBoolean("is_admin"));
		user.setPassword(resultSet.getString("password"));
		user.setSalt(resultSet.getString("salt"));
	
		return user;
	}
	
	//Hash化する処理
	private static String createHash(String password, String salt, String pepper)
	            throws NoSuchAlgorithmException {
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
		String string = password + salt + pepper;
		
		byte[] bytes = sha256.digest(string.getBytes());
		String hash = String.format(
				"%040x",
				new BigInteger(1, bytes)
		);
		
		return hash;
	}
	
	//ユーザーの追加をする処理
	public User addUser(String account, String name, String password, boolean isAdmin)
	            throws SQLException, NoSuchAlgorithmException {
		
		String salt = UUID.randomUUID().toString();
		String hash = UserDAO.createHash(password, salt, Const.PEPPER);
		
		String sql = "INSERT INTO users (account, name, password,"
				  + "salt, is_admin) "
				  + "VALUES(?, ?, ?, ?, ?)";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, account);
		statement.setString(2, name);
		statement.setString(3, hash);
		statement.setString(4, salt);
		statement.setBoolean(5, isAdmin);
	
		statement.execute();
		statement.close();
		
		User user = this.findNew();
		return user;
	}
	
	//初期化 デフォルトユーザーの追加する処理
	private void initialize() throws SQLException, NoSuchAlgorithmException {
		if(this.count() == 0) {
			this.addUser(
					Const.DEFAULT_USER_ACCOUNT,
					Const.DEFAULT_USER_NAME,
					Const.DEFAULT_USER_PASSWORD,
					true
					);
		}
	}
	
	//アカウント名からユーザーオブジェクトの取得をする処理
	public User findByAccount(String account) throws SQLException {
		User user = null;
		
		String sql = "SELECT * FROM users WHERE account = ?";
		
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, account);
		
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			user = this.createEntity(resultSet);
		}
		
		resultSet.close();
		statement.close();
		
		return user;
	}
	
	//ログインをする処理
	public User login(String account, String password)
	              throws SQLException, NoSuchAlgorithmException {
		User user = this.findByAccount(account);
		
		if(user != null) {
			String salt = user.getSalt();
			String hash = UserDAO.createHash(password, salt, Const.PEPPER);
			if(!hash.equals(user.getPassword())) {
				user = null;
			}
		}
		return user;
	}
	
	//指定したユーザーの情報を変更する処理
	public User updateUser(int id, String account, String name, boolean isAdmin)
	               throws SQLException {
			
		String sql = "UPDATE users SET account = ?, name = ?, is_admin = ? WHERE id = ?";
			
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, account);
		statement.setString(2, name);
		statement.setBoolean(3, isAdmin);
		statement.setInt(4, id);
			
		statement.execute();
		statement.close();
			
		User user = this.findById(id);
		return user;
	}
		
		
	//指定したユーザーのパスワードを変更する処理
	public User updatePassword(int id, String password)
	         throws SQLException, NoSuchAlgorithmException {
			
		User user = this.findById(id);
		String salt = user.getSalt();
		String hash = UserDAO.createHash(password, salt, Const.PEPPER);
			
		String sql = "UPDATE users SET password = ? WHERE id = ?";
			
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setString(1, hash);
		statement.setInt(2, id);
			
		statement.execute();
		statement.close();
			
		user = this.findById(id);
		return user;
	}
}
	
