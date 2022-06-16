package cc.shinbi.java.servlet;

import java.sql.Connection;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.shinbi.java.model.dao.GenreDAO;
import cc.shinbi.java.model.entity.Quiz;
import cc.shinbi.java.model.entity.User;



@WebServlet("/top")
public class TopServlet extends JspServlet {
	
	//ログインチェック ログインしてなかったらログイン画面に移動
	public TopServlet() {
		super(true);
	}

	//top.jspに移動する処理
	@Override
	protected String view(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Connection connection,
			User loginUser
			) throws Exception {
		
		//GenreDAOからジャンルを取得するメソッドを呼び出してListを作成しrequestにset
		GenreDAO dao = new GenreDAO(connection);
		List<Quiz> quizs = dao.searchGenre();
		request.setAttribute("quizs", quizs);
		
		String jsp = "/WEB-INF/jsp/top.jsp";
		return jsp;
	}
}