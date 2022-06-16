package cc.shinbi.java.model.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cc.shinbi.java.model.entity.Quiz;

public class GenreDAO extends DAO<Quiz> {
	public GenreDAO(Connection connection) throws SQLException, NoSuchAlgorithmException {
		super("quizs", connection);
		
	}

	//クイズオブジェクトを作成する処理
	@Override
	protected Quiz createEntity(ResultSet resultSet) throws SQLException {
		Quiz quiz = new Quiz();
		
		quiz.setId(resultSet.getInt("id"));
		quiz.setQuestion(resultSet.getString("question"));
		quiz.setChoices1(resultSet.getString("choices1"));
		quiz.setChoices2(resultSet.getString("choices2"));
		quiz.setChoices3(resultSet.getString("choices3"));
		quiz.setChoices4(resultSet.getString("choices4"));
		quiz.setAnswer(resultSet.getString("answer"));
		quiz.setExplanation(resultSet.getString("explanation"));
		quiz.setGenre(resultSet.getString("genre"));
		
		return quiz;
	}

	
	//重複したジャンルのフィールドを取得	
	public List<Quiz> searchGenre () throws SQLException {
		List<Quiz> list = new ArrayList<Quiz>();
		
		//重複したgenreレコードを除いて取得する
	//	String sql = "SELECT * FROM quizs WHERE id IN(SELECT min(id) FROM quizs GROUP BY genre)";
		
		//5つ以上重複している場合取得する
		String sql = "SELECT * FROM quizs GROUP BY genre HAVING COUNT(genre) >= 5";
				
		Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		//取得したレコードでquizオブジェクトを作成し、quizオブジェクトを入れたArrayListを作成
		while(resultSet.next()) {
			Quiz entity = this.createEntity(resultSet);
			list.add(entity);
		}
				
		resultSet.close();
		statement.close();
	
		return list;
	}
	
	

}
