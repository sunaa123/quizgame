package cc.shinbi.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//データベース接続に関するクラス
public class DbUtil {
    //データベース名はquizgameで作成しました
	private static final String DB_DRIVER = "jdbc:mysql://localhost:8889/quizgame";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	
	public static Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				DB_DRIVER,
				DB_USER,
				DB_PASSWORD
		);
		return connection;
	}
}
