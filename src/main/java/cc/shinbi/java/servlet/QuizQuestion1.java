package cc.shinbi.java.servlet;

import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cc.shinbi.java.model.entity.User;



@WebServlet("/QuizQuestion1")
public class QuizQuestion1 extends JspServlet{
	
	//ログイン認証
	public QuizQuestion1() {
		super(true);
	}
	
	@Override
	protected String view(
			HttpServletRequest request,
			HttpServletResponse response,
			Connection connection,
			User loginUser
		) throws Exception {
		
		//↑↑↑↑↑↑↑↑↑↑このシステムのsarvletではDbservletをベースに動かす必要があるので上記の記載は共通にすること（以下に条件文を記載）↑↑↑↑↑↑↑↑↑↑
		
			
			 int tokuten = 0;
		     long startTime = 0;
		     long endTime = 0;
		     
		     HttpSession session = request.getSession();
		     
		     String next = request.getParameter("next");
		     

		     int i = (int)session.getAttribute("i");//前の問題でiを保存しているのでそれを呼び出す。
		     int tokutentotal = (int) session.getAttribute("tokutentotal"); //前の問題までの合計得点を取得する。
		 	 int count = (int) session.getAttribute("count"); //各質問の正解得点をセッションに保存してあるのでそれを呼び出す。
		     
		 	 
		 	String question = (String)session.getAttribute("question");//QuizResultから質問を受け取るためセッションに接続
			String choices1 = (String)session.getAttribute("choices1");//QuizResultから回答1を受け取るためセッションに接続
			String choices2 = (String)session.getAttribute("choices2");//QuizResultから回答2を受け取るためセッションに接続
			String choices3 = (String)session.getAttribute("choices3");//QuizResultから回答3を受け取るためセッションに接続
			String choices4 = (String)session.getAttribute("choices4");//QuizResultから回答4を受け取るためセッションに接続
			String answer = (String)session.getAttribute("answer");//QuizResultから答えを受け取るためセッションに接続 
			
			String str2 = answer;//answerはString型で登録されているので、intへ変換する
		    int in1 = Integer.parseInt(str2);//入力値を整数に変換し、in1に代入。  
		 	
					
					System.out.println("質問：" + question);
			        System.out.println("回答１：" + choices1);
			        System.out.println("回答２：" + choices2);
			        System.out.println("回答３：" + choices3);
			        System.out.println("回答４：" + choices4);
					
			           
           
           //回答の取得
			String str1;
			int in =0;
			
				if(request.getParameter("1") != null) {
					str1 =request.getParameter("1");
					in = Integer.parseInt(str1);
				}else if(request.getParameter("2") != null) {
					str1 =request.getParameter("2");
					in = Integer.parseInt(str1);
				}else if(request.getParameter("3") != null) {
					str1 =request.getParameter("3");
					in = Integer.parseInt(str1);
				}else if(request.getParameter("4") != null) {
					str1 =request.getParameter("4");
					in = Integer.parseInt(str1);
				}
				
					System.out.println("回答：" + in);
					System.out.println("答え：" + in1);
	
		       
       
		      // 処理後の時刻を取得
			     endTime = System.currentTimeMillis(); //この処理で、回答時間を確定
			     startTime =  (long) session.getAttribute("startTime");//QuizResultで計測したstartTimeを入手する。
			      
			      float answertime =  (endTime - startTime);//endTime と startTimeの差からかかった時間を計算してやる。
			      
	    			//サーブレットコンテキストの取得
	    			ServletContext sc = getServletContext();   
			      

			      int no = i+1;//問題No,を生じさせるための処理+1を加えることで、問題文と一致させる。
		         
		      if(answertime >= 11500) {//10秒以上は無効にしたいのでこの処理を追加。
		    	  
		    	  	sc.getRequestDispatcher("/timeover.jsp").forward(request,  response);
		    	  
		    	  	System.out.println("時間切れです");
		      }
		      else if(in == in1 && answertime < 10000) {//正解だった時の残り時間のボーナス得点を算出する。
		    	  
		    	  	tokuten = (10-((int)answertime/1000));//制限時間10secなので、10secからかかった時間を差し引き、残り時間を求める。
		    	  	
		    	  	session.setAttribute("answertime", answertime);//時間情報をcorrect.jspに渡す。
		    	  	session.setAttribute("tokuten", tokuten);//得点情報をcorrect.jspに渡す。
		    	  	
		    	  	count++;//各質問の獲得得点を足していくための処理。
		    	  	session.setAttribute("count", count);
		    	  	tokutentotal += tokuten; //最終的にトータルのボーナス得点を求める必要があるので、この式を入れて、加算させる。
		    	  	session.setAttribute("tokutentotal", tokutentotal);
		    	  	
		    	  		System.out.println("！！！  大正解です。得点10点を獲得しました！！！");
		    	  		System.out.println("問題" + no + "の回答までの時間：" + answertime/1000 + "sec");
		    	  		System.out.println("残り時間ボーナス：" + tokuten  + "点が加算されます！"); 
				    
				    sc.getRequestDispatcher("/WEB-INF/jsp/correct.jsp").forward(request,  response);//上の行の処理が終わってからページを開くようにする必要があるので、この行は最後に持ってくる（次のページが情報を取れなくてエラーになる）
		    	  		
		      }
		      else if(in == in1){//正解だった場合の処理。ページが切り替わるまでのタイムラグがあるので、その処理を追加。１０秒以上でも正解得点は追加するがボーナスはなしの設定。
		    	  answertime = 10000;
		    	  tokuten = 0;
		    	  
	      		  session.setAttribute("answertime", answertime);//時間情報をcorrect.jspに渡す。
	      		  session.setAttribute("tokuten", tokuten);//得点情報をcorrect.jspに渡す。
	      		
	      		  count++;//各質問の獲得得点を足していくための処理。
	      		  session.setAttribute("count", count);
	      		
	      		  System.out.println("！！！  正解です。得点10点を獲得しました！！！");
	      		  System.out.println("問題" + no + "の回答までの時間：" + answertime/1000 + "sec");
	      		
	      		  sc.getRequestDispatcher("/WEB-INF/jsp/correct.jsp").forward(request,  response);//上の行の処理が終わってからページを開くようにする必要があるので、この行は最後に持ってくる（次のページが情報を取れなくてエラーになる）
		      }
		      else if(in != in1 && next == null) {//不正解だった場合の処理。
		    	  
		    	  	session.setAttribute("answertime", answertime);//時間情報をcorrect.jspに渡す。
		    	   
		    	  		System.out.println("・・・   残念！はずれです   ・・・");
		    	   		System.out.println("問題" + no + "の回答までの時間：" + answertime/1000 + "sec");

		    	   	sc.getRequestDispatcher("/WEB-INF/jsp/incorrect.jsp").forward(request,  response);//上の行の処理が終わってからページを開くようにする必要があるので、この行は最後に持ってくる（次のページが情報を取れなくてエラーになる）

		      }   
		      
		      
		      i++;//次の問題に進むためにiに1を追加する。
		      session.setAttribute("i", i);//追加したiをセッションに保存する。
		      
		      
		      count = (int)session.getAttribute("count");//動作確認のためにcount情報を入手
		      tokutentotal = (int)session.getAttribute("tokutentotal");//動作確認のためにcount情報を入手
		      i = (int)session.getAttribute("i");
		      
		      int  totalresult = (count*10) + tokutentotal;
		      
		      		System.out.println("合計得点" + count*10);
		      		System.out.println("総合得点" + totalresult);
		      		System.out.println("更新したi：" + i);

			
		//↓↓↓↓↓↓↓↓↓↓このシステムのsarvletではDbservletをベースに動かす必要があるので上記の記載は共通にすること（以下に条件文を記載）↓↓↓↓↓↓↓↓↓↓     
   
		return null;
           
	
	}
	
}
