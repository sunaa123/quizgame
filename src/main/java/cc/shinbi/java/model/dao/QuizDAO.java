package cc.shinbi.java.model.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cc.shinbi.java.model.entity.Quiz;


//quizsテーブルを使った操作をするクラス
public class QuizDAO extends DAO<Quiz> {
	public QuizDAO(Connection connection) throws SQLException, NoSuchAlgorithmException {
		super("quizs", connection);
	}
	
	//データベースからquizオブジェクトを作成する処理
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
			quiz.setGenre(resultSet.getString("genre"));//setGener→setGenreに修正（小田）
			
			return quiz;
		}
		
		
		//quizを追加する処理
		public Quiz addQuiz(String question, String choices1,
				String choices2,String choices3, String choices4, String answer,
				String explanation, String genre) throws SQLException {
			
			String sql = "INSERT INTO quizs (question, choices1,"
					+ "choices2, choices3, choices4, answer,"
					+ "explanation, genre) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = this.connection.prepareStatement(sql);
			
			statement.setString(1, question);
			statement.setString(2, choices1);
			statement.setString(3, choices2);
			statement.setString(4, choices3);
			statement.setString(5, choices4);
			statement.setString(6, answer);
			statement.setString(7, explanation);
			statement.setString(8, genre);
			
			statement.execute();
			statement.close();
			
			Quiz quiz = this.findNew();
			return quiz;
		}
		
		//quizの変更をする処理
		public Quiz updateQuiz(int id, String question, String choices1,
				String choices2,String choices3, String choices4, String answer,
				String explanation, String genre) throws SQLException {
			
			String sql = "UPDATE quizs SET question = ?, choices1 = ?, choices2 = ?, "
					+ "choices3 = ?, choices4 = ?, answer = ?, explanation = ?,"
					+ "genre = ? WHERE id = ?";
			
			PreparedStatement statement = this.connection.prepareStatement(sql);
			statement.setString(1, question);
			statement.setString(2, choices1);
			statement.setString(3, choices2);
			statement.setString(4, choices3);
			statement.setString(5, choices4);
			statement.setString(6, answer);
			statement.setString(7, explanation);
			statement.setString(8, genre);
			statement.setInt(9, id);
			
			statement.execute();
			statement.close();
			
			Quiz quiz = this.findById(id);
			return quiz;
		}
}