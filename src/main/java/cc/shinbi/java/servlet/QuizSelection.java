package cc.shinbi.java.servlet;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cc.shinbi.java.model.dao.DAO;
import cc.shinbi.java.model.entity.Quiz;


//データアクセスオブジェクトに関するクラス
public class QuizSelection extends DAO<Quiz> {
	public QuizSelection(Connection connection) throws SQLException, NoSuchAlgorithmException {
		super("quizs", connection);
		
	}
	
	
	//IDからレコードを取得をする処理
	public Quiz findById(int id) throws SQLException {//QuizQuestionで設定したidをサーバーに渡し、46行のオーバーライドで値を取得し、再度入手して、要求先に送る
		Quiz quiz = null;
		
		String sql = String.format(
		     "SELECT * FROM %s WHERE id = ?",//指定したIDのレコードを呼び出すためのSELECT文
		     this.tableName
		);
		
		PreparedStatement statement = this.connection.prepareStatement(sql);//呼び出し方法の指定。Preparedなので次に条件を記載
		statement.setInt(1, id);//呼び出し内容の定義。ランダムに設定した値がidに代入される。
		
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			quiz = this.createEntity(resultSet);
		}
		
		
		
		resultSet.close();
		statement.close();
		
		return quiz;
	}
			
	
	@Override//createEntityはDAOを呼び出してオブジェクト作成するが、呼び出せないので最後にメソッドを作っている
	protected Quiz createEntity(ResultSet resultSet) throws SQLException {

			Quiz quiz = new Quiz();//		上で取得したID情報をもとにオブジェクトを作成する。
			
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
			
			
		/////Genreからレコードを取得をする処理////////////////////////////////////////////////////////////////

			@SuppressWarnings({ "rawtypes", "unchecked" })//ArrayListを作るので、この行が追加される
			public Quiz findByGenre(String genre, HttpServletRequest request) throws SQLException {//QuizResultで設定したidをサーバーに渡し、52行のオーバーライドで値を取得し、再度入手して、要求先に送る。途中、AllayListを作成するので、”HttpServletRequest request”を追加
				Quiz quiz = null;
				ArrayList list3 = new ArrayList();//IDを収めるためのArrayListを作成

				
				String sql = String.format(
				     "SELECT * FROM %s WHERE genre = ?",//指定したGenreのレコードを呼び出すためのSELECT文
				     this.tableName
				);
				
				PreparedStatement statement = this.connection.prepareStatement(sql);//呼び出し方法の指定。Preparedなので次に条件を記載
				statement.setString(1, genre);//呼び出し内容の定義。ランダムに設定した値がidに代入される。
				
				ResultSet resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
				//for(int i=0; i <= 12; i++) {
					quiz = this.createEntity(resultSet);//まずはIDごとにレコードを取得
					int ida = quiz.getId();//その中からIDだけ取り出す。
					list3.add(ida);	//その結果をArrayListに収める
				}
				
				HttpSession session = request.getSession();//各結果を都度IDの数だけ行うArraylistに収めて、セッションに送る。
				session.setAttribute("list3", list3);
				
				resultSet.close();
				statement.close();

				return quiz;
				
			}
	}

    
    
    