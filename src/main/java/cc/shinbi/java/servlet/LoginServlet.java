package cc.shinbi.java.servlet;

import java.sql.Connection;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cc.shinbi.java.model.Const;
import cc.shinbi.java.model.dao.GenreDAO;
import cc.shinbi.java.model.dao.UserDAO;
import cc.shinbi.java.model.entity.Quiz;
import cc.shinbi.java.model.entity.User;


//ログイン処理に関するクラス
@WebServlet("/login")
public class LoginServlet extends JspServlet {
	
	//ログインチェック無し
	public LoginServlet() {
		super(false);
	}
	
	@Override
	protected String view(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Connection connection,
			User loginUser
	) throws Exception {
		String jsp = null;
		String error = "";
		User user = null;
		
		UserDAO userDao = new UserDAO(connection);
		
		//ユーザーが入力した値を取得
		String account = request.getParameter("account");
		
		//入力されてなかった場合
		if(account == null || account.isEmpty()) {
			error = "アカウント名を入力してください。";
		}
	
		String password = request.getParameter("password");
		if(password == null || password.isEmpty()) {
			error = "パスワードを入力してください。";
		}
		
		//入力されていた場合
		if(error.isEmpty()) {
			user = userDao.login(account, password);
		}
		
		//入力されていたが間違えていた場合 ログイン画面に移動
		if(user == null) {
			if(error.isEmpty()) {
				error = "ユーザー名もしくはパスワードが違います。";
			}
			request.setAttribute("error", error);
			jsp = "/WEB-INF/jsp/login.jsp";
		}
		
		//ログイン成功するとセッションに保存してトップページに移動
		else {
			HttpSession session = request.getSession();
			session.setAttribute(Const.LOGIN_USER_KEY, user);
			
			//GenreDAOからジャンルを取得するメソッドを呼び出してListを作成しrequestにset
			GenreDAO dao = new GenreDAO(connection);
			List<Quiz> quizs = dao.searchGenre();
			request.setAttribute("quizs", quizs);
			
			jsp = "/WEB-INF/jsp/top.jsp";
		}
		
		return jsp;
	}

}