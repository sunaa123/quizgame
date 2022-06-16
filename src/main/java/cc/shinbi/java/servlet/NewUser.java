package cc.shinbi.java.servlet;

import java.sql.Connection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cc.shinbi.java.model.Const;
import cc.shinbi.java.model.dao.UserDAO;
import cc.shinbi.java.model.entity.User;

//ログインページの新規登録ボタンを押したときに使うサーブレット
@WebServlet("/newuser")
public class NewUser extends JspServlet {

	public NewUser() {
		super(false);
	}

	@Override
	protected String view(HttpServletRequest request, HttpServletResponse response, Connection connection,
			User loginUser) throws Exception {	
        String jsp = null;
        String operation = request.getParameter("operation");
		UserDAO dao = new UserDAO(connection);
	
		if(operation == null) {
			jsp = newUser(request, dao, loginUser);
		}
		else if(operation.equals("add")){
			jsp = addUser(request, dao, loginUser);
		}
		
		return jsp;
	}
	
	//ユーザーを新規登録する処理
	private String addUser(HttpServletRequest request, UserDAO dao, User loginUser)
	         throws Exception {
		String jsp = null;
		
			String error = "";
			String account = request.getParameter("account");
			if(account == null || account.isEmpty()) {
				error = "アカウント名を入力してください。";
			}
			else {
				User user = dao.findByAccount(account);
				if(user != null) {
					error ="そのアカウントは既に使われています。";
				}
			
			String name = request.getParameter("name");
			if(name == null || name.isEmpty()) {
				error += "名前を入力してください。";
			}
			
			String isAdmin = request.getParameter("is_admin");
			
			String password = request.getParameter("password");
			if(password == null || password.isEmpty()) {
				error += "パスワードを入力してください。";
			}
			
			String confirmed = request.getParameter("confirmed");
			if(!password.equals(confirmed)) {
				error += "パスワードが一致しません。";
			}
			
		    //エラーがなかったらユーザーを追加するメソッドを呼び、sessionにログイン情報を
			//保存して、トップページに移動する
			if(error.isEmpty()) {
				dao.addUser(account, name, password, Boolean.parseBoolean(isAdmin));
				HttpSession session = request.getSession();
				user = dao.login(account, password);
				session.setAttribute(Const.LOGIN_USER_KEY, user);
				
				jsp = "/WEB-INF/jsp/top.jsp";
			}
			else {
				request.setAttribute("error", error);
				jsp = "/WEB-INF/jsp/newUser.jsp";
			}
		}
		
		return jsp;
	}
	
	//新規ユーザーを追加するページに移動する処理 
		private String newUser(HttpServletRequest request, UserDAO dao, User loginUser)
		         throws Exception {
			
			String jsp = "/WEB-INF/jsp/newUser.jsp";
		
			return jsp;
		}
	


}
