package cc.shinbi.java.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.shinbi.java.util.DbUtil;


//データベース接続に関するクラス
public abstract class DbServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
	       throws ServletException, IOException {
		try {
			Connection connection = DbUtil.connect();
			
			action(request, response, connection);
			
			connection.close();
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}
	
	//抽象メソッド データベースに接続して閉じるまでの間の処理
	protected abstract void action(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Connection connection
			) throws Exception;
}
