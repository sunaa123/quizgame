package cc.shinbi.java.model.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cc.shinbi.java.model.entity.Point;

public class PointDAO extends DAO<Point> {
	
	public PointDAO(Connection connection) throws SQLException, NoSuchAlgorithmException{
		//テーブル名、SQL接続
		super("points", connection);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	
    //scoreオブジェクト作成
	@Override
	protected Point createEntity(ResultSet resultSet) throws SQLException {
        Point point = new Point();
		
		point.setId(resultSet.getInt("id"));
		point.setUserId(resultSet.getInt("user_id"));
		point.setName(resultSet.getString("name"));
		point.setScore(resultSet.getInt("score"));
		point.setCreatedAt(resultSet.getTimestamp("created_at"));
	
		return point;
	}
	
	
	//scoreを追加
	public Point addPoint(int user_id, String name, int score) throws SQLException {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		String sql = "INSERT INTO points (user_id, name, score, created_at) "
		           + "VALUES(?, ?, ?, ?)";
		
	    PreparedStatement statement = this.connection.prepareStatement(sql);
	    //user_idとnameとscoreはセッションから入る
		statement.setInt(1, user_id);
		statement.setString(2, name);
		statement.setInt(3, score);
		statement.setTimestamp(4, now);
		
		statement.execute();
	    statement.close();
	    
		Point point = this.findNew();
	    return point;
	}
	
	
	//scoreランキングを取得する
	public List<Point> findRanking() throws SQLException {
		List<Point> list = new ArrayList<Point>();
				
	    //String sql = "SELECT * FROM points order BY score DESC LIMIT 10";
		String sql = "SELECT * FROM points order BY score DESC";
				
		Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
				
		while(resultSet.next()) {
			Point entity = this.createEntity(resultSet);
			list.add(entity);
			}
				
		resultSet.close();
		statement.close();
			
		return list;
	}
	
	//レコードのscoreが上から何番目か表示する
	public int myRank(int score) throws SQLException {
		int rank = 0;
		//自分のscoreより上にレコードがいくつあるか
	 	String sql = "SELECT COUNT(score) FROM points WHERE score > ?";
	    
		PreparedStatement statement = this.connection.prepareStatement(sql);
		
	    statement.setInt(1, score);
		
		ResultSet resultSet = statement.executeQuery();

		if(resultSet.next()) {
			rank =  resultSet.getInt(1);
		}
   
		statement.execute();
		statement.close();
		
		rank++;
		
		return rank;
	}	
}
