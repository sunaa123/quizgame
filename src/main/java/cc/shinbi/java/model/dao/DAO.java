package cc.shinbi.java.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


//データアクセスオブジェクトに関するクラス
public abstract class DAO<T> {
	protected String tableName;
	protected Connection connection;
	
	public DAO(String tableName, Connection connection) {
		this.tableName = tableName;
		this.connection = connection;
	}
	
  //レコードの数を取得する処理
	public int count() throws SQLException {
		String sql = String.format(
			"SELECT COUNT(*) AS count FROM %s",
			this.tableName
			);
		
		Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		int count = 0;
		if(resultSet.next()) {
			count = resultSet.getInt("count");
		}
		
		resultSet.close();
		statement.close();
		
		return count;
	}
	
	//サブクラスで中身を書く抽象メソッド
	protected abstract T createEntity(ResultSet resultSet) throws SQLException;
	

	//一番新しいレコードを取得する処理
	public T findNew() throws SQLException {
		T entity = null;
		
		String sql = String.format(
			"SELECT * FROM %s ORDER BY id DESC LIMIT 1",
			this.tableName
			);
		
		Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		if(resultSet.next()) {
			entity = this.createEntity(resultSet);
		}
		
		resultSet.close();
		statement.close();
		
		return entity;
	}
		
	//レコード一覧を取得する処理
	public List<T> findAll() throws SQLException {
		List<T> list = new ArrayList<T>();
			
		String sql = String.format(
	        "SELECT * FROM %s",
	        this.tableName
			);
			
		Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
			
		while(resultSet.next()) {
			T entity = this.createEntity(resultSet);
			list.add(entity);
	}
			
		resultSet.close();
		statement.close();
			
		return list;
	}
		
		
	//IDからレコードを取得をする処理
	public T findById(int id) throws SQLException {
		T entity = null;
			
		String sql = String.format(
	        "SELECT * FROM %s WHERE id = ?",
	        this.tableName
			);
			
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setInt(1, id);
			
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			entity = this.createEntity(resultSet);
	}
			
		resultSet.close();
		statement.close();
			
		return entity;
}
		
		
	//レコードの削除をする処理
	public void delete(int id) throws Exception {
		String sql = String.format(
			"DELETE FROM %s WHERE id = ?",
			this.tableName
			);
			
		PreparedStatement statement = this.connection.prepareStatement(sql);
		statement.setInt(1, id);
		statement.execute();
			
		statement.close();
	}
}