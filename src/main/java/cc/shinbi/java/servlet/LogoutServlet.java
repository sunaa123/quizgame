package cc.shinbi.java.servlet;

import java.sql.Connection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cc.shinbi.java.model.Const;
import cc.shinbi.java.model.entity.User;


//ログアウト処理に関するクラス
@WebServlet("/logout")
public class LogoutServlet extends JspServlet {
	
	//ログインチェック
	public LogoutServlet() {
		super(true);
	}
	
	@Override
	protected String view(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Connection connection,
			User loginUser
	) throws Exception {
		//セッション情報を削除してログインページに移動する
		HttpSession session = request.getSession();
		session.removeAttribute(Const.LOGIN_USER_KEY);
		
		String jsp = "WEB-INF/jsp/login.jsp";
		return jsp;
	}
}
