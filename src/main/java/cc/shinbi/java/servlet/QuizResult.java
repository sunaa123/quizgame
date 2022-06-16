package cc.shinbi.java.servlet;
	
	import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cc.shinbi.java.model.entity.Quiz;
import cc.shinbi.java.model.entity.User;

@WebServlet("/QuizResult")
public class QuizResult extends JspServlet{
	
	//ログイン認証
	public QuizResult() {
		super(true);
	}
	
	@Override
	protected String view(
			HttpServletRequest request,
			HttpServletResponse response,
			Connection connection,
			User loginUser
		) throws Exception {

		
		
	
			int i =0;
		    long startTime = 0;
		    long endTime = 0;
		    int count = 0;
		    int tokuten = 0;
		    int tokutentotal = 0;
		    int totalresult = 0;
			String jsp = null;
		   
		    String start = request.getParameter("start");
		    String next = request.getParameter("next");
		    String over = request.getParameter("over");
		    
		    
		    HttpSession session = request.getSession();//iの情報をいったんセッションに保管し、下の方の次のページを開くときの条件として渡してやる
		    
		    
			//クイズ出題前に押されるボタンの処理
		     if(start != null) {
		    	 count = 0;
		    	 session.setAttribute("count", count);//正解数（count）をリセットしてやる＞＞前の情報が残ると正確に計算できない
		    	 tokutentotal = 0;
		    	 totalresult = 0;
		    	 session.setAttribute("tokutentotal", tokutentotal);//ボーナス得点合計（count）をリセットしてやる＞＞前の情報が残ると正確に計算できない
		    	 i = 0;
		    	 session.setAttribute("i", i);//試験問題変数のiをリセットしてやる。
		     }else if(over != null){//タイムオーバーの時の処理。回答をスキップするのでiを追加する必要がある。
				 i = (int) session.getAttribute("i");//iの情報をセッションから呼び出す。
		    	 i++;//回答をスキップした分、iを追加してやる。
		    	 session.setAttribute("i", i);//そのiをセッションに保存する。
		     }else if(next != null){
		    	 i = (int) session.getAttribute("i");//iの情報をセッションから呼び出す。
		     }

				
				
			//サーバーからのクイズ情報取得と出題内容の決定
				
		    int id =0;
		 	 
		 	 QuizSelection quizselection = new QuizSelection(connection);	
		 	
		 	int qcount = quizselection.count(); 
		 	
		 	
		 			//※下はramdomを使った場合のid算出方法。重複してしまうので却下 
		 	 			//id = (int) (Math.floor( Math.random() * qcount ) + 1);//ランダムでクイズIDを設定するために、Math.randomで数値を求める。また最大数はレコード数=qcountにしておく
		 	
		 	
		 	
		 	//トップページで選択したジャンルに該当するIDを取得するための処理。ジャンル情報をQuizSelectionに送り、該当するクイズIDのAllayListを返えしてもらう////////////////////////////////////////
		 
		 	String genre = request.getParameter("genre");//スタート画面から選択されたジャンル情報を取得。
		 	
		 	Quiz quiz = (Quiz) quizselection.findByGenre(genre, request);//指定したジャンル情報をQuizServletに送り、対象となるIDをAllayListで入手する。なので、最後のかっこの中に”request”を追加して処理できるようにする
		 	
		 	ArrayList list3 = (ArrayList) session.getAttribute("list3");//QuizServletで処理した結果はArrayListになっているので、受け取るときは型をArrayListにする。この結果を以下のシャッフルするコードへ渡す。

		 		System.out.println("ジャンルquiz：" + quiz);
		 		System.out.println("ジャンルボタン：" + genre);
		 		System.out.println("start：" + start);
		 		System.out.println("ジャンルlist3：" + list3);

		 	//////////////////////////////////////////	

		 		
		 
		 	//質問をリスト中からランダムで5問抽出する処理。重複した出題にならないように、取得したクイズIDをシャフルさせる。		 	
		 	if(i == 0) {
		 				 	
		 		Collections.shuffle(list3);//shuffleメソッドで上で作ったリストをシャッフル
		 		
		 		System.out.println("シャフルした値：" + list3);
            
            	id = (int) list3.get(0);
            	int id1 = (int) list3.get(1);
            	session.setAttribute("id1", id1);//ArrayListは毎回更新されてしまうので、初回のリストを2回目以降に使えるようにセッションに保存(2問目用)
            	int id2 = (int) list3.get(2);
            	session.setAttribute("id2", id2);//ArrayListは毎回更新されてしまうので、初回のリストを2回目以降に使えるようにセッションに保存(3問目用)
            	int id3 = (int) list3.get(3);
            	session.setAttribute("id3", id3);//ArrayListは毎回更新されてしまうので、初回のリストを2回目以降に使えるようにセッションに保存(4問目用)
            	int id4 = (int) list3.get(4);
            	session.setAttribute("id4", id4);//ArrayListは毎回更新されてしまうので、初回のリストを2回目以降に使えるようにセッションに保存(5問目用)
            }else if(i == 1) {
            	int id1 = (int)session.getAttribute("id1");//セッションに保存したArrayListを取得。
            	id = id1;//2番目の数値をidに代入
            	System.out.println("2問目（i=1）のid：" + id1);
            }else if(i == 2) {
            	int id2 = (int)session.getAttribute("id2");//セッションに保存したArrayListを取得。
            	id = id2;//3番目の数値をidに代入
            	System.out.println("3問目（i=1）のid：" + id2);
            }else if(i == 3) {
            	int id3 = (int)session.getAttribute("id3");//セッションに保存したArrayListを取得。
            	id = id3;//4番目の数値をidに代入
            	System.out.println("4問目（i=1）のid：" + id3);
            }else if(i == 4) {
            	int id4 = (int)session.getAttribute("id4");//セッションに保存したArrayListを取得。
            	id = id4;//5番目の数値をidに代入
            	System.out.println("5問目（i=1）のid：" + id4);
            }
		 	 
            
		 	if(i < 5) {//以下のコードは各idに入っているレコードの情報を取り出しセッションに保存する処理であるが、iの範囲を指定しないと処理エラーになるためそれを指示する（上記でi==4までの指定しかないので、i==5の時、quizが設定できず、エラーになる）
		 	/*Quiz*/ quiz = quizselection.findById(id);//上でランダムに選んだ数を問題を抽出する条件に設定するためｉｄに代入し、その数値の問題レコードを抽出するために、quizselectionへ投げる。→ｉｄに相当する情報が戻される。
		 	 
		 	String question = quiz.getQuestion();//サーバーから入手したデータを各変数に代入する
			String choices1 = quiz.getChoices1();
			String choices2 = quiz.getChoices2();
			String choices3 = quiz.getChoices3();
			String choices4 = quiz.getChoices4();
			String answer = quiz.getAnswer();
			String explanation = quiz.getExplanation();
			genre=  quiz.getGenre();
			
			
		       //結果画面に正解を表示するために正解No,を選択肢に変換、セッションに保存する。
		       String result = null;
		       if(answer.equals("1")) {
		    	   result = choices1;
		       }else if(answer.equals("2")) {
		    	   result = choices2;
		       }else if(answer.equals("3")) {
		    	   result = choices3;
		       }else if(answer.equals("4")) {
		    	   result = choices4;
		       }   
		      
		    session.setAttribute("result", result);
			session.setAttribute("question", question);//各項目を表示画面（QusetionQ1）へ渡すためにセッションへ保存
			session.setAttribute("choices1", choices1);
			session.setAttribute("choices2", choices2);
			session.setAttribute("choices3", choices3);
			session.setAttribute("choices4", choices4);
			session.setAttribute("answer", answer);
			session.setAttribute("explanation", explanation);
			session.setAttribute("genre", genre);
		 	}
			
			
	
		 	//タイマー起動設定
			startTime = System.currentTimeMillis();//// 次の質問に移るときに"nextが送信されるので、それを時間カウントスタートに使う。
		          
			session.setAttribute("startTime", startTime);//スタートタイムをセッションに保存し、"QuizQuestion1"に渡す。
			
		     

			
			//結果発表後に次の問題または最終結果（ご褒美画面）への移動処理
				
				tokutentotal = (int) session.getAttribute("tokutentotal"); 
				count = (int) session.getAttribute("count");
				totalresult = (count*10) + tokutentotal; 
				session.setAttribute("totalresult", totalresult);
				
				//サーブレットコンテキストの取得
				ServletContext sc = getServletContext();  
				
				//Top画面、結果画面、タイムオーバー画面でボタンクリックされた時の処理
				if(start != null) {
					sc.getRequestDispatcher("/WEB-INF/jsp/QuizQ1.jsp").forward(request,  response);//最初なのでQ1ページへダイレクトに表示
				}else if((over != null || next != null) && i < 5 ) {//結果画面の次へを押した時の処理を指示してやる。最後の質問(i==4)が終わったらご褒美画面に飛ばすので、その条件を指示
			    	sc.getRequestDispatcher("/WEB-INF/jsp/QuizQ1.jsp").forward(request,  response);
				}else if((over != null || next != null) && i == 5 && totalresult >= 85) {
					String operation = "operation";
					session.setAttribute("operation", operation);
					operation = (String) session.getAttribute("operation");
					System.out.println("operation有無：" + operation);

			    //	sc.getRequestDispatcher("/WEB-INF/jsp/last1.jsp").forward(request,  response);//pount.servletに以降の処理を移すため、この処理は不要。
			    	
					String disp = "/point";//この３行の処理を行うことで、以降の処理は"/point"=PointServletに渡される。
				    RequestDispatcher dispatch = request.getRequestDispatcher(disp);
				    dispatch.forward(request, response);
					
			    	
			    }else if((over != null || next != null) && i == 5 && totalresult >= 55) {
			    	String operation = "operation";
					session.setAttribute("operation", operation);
					operation = (String) session.getAttribute("operation");
					System.out.println("operation有無：" + operation);

			   // 	sc.getRequestDispatcher("/WEB-INF/jsp/last2.jsp").forward(request,  response);//pount.servletに以降の処理を移すため、この処理は不要。
			    	
			    	String disp = "/point";//この３行の処理を行うことで、以降の処理は"/point"=PointServletに渡される。
				    RequestDispatcher dispatch = request.getRequestDispatcher(disp);
				    dispatch.forward(request, response);
				
				     
				     
			    }else if((over != null || next != null) && i == 5 && totalresult < 55) {
			    	String operation = "operation";
					session.setAttribute("operation", operation);
					operation = (String) session.getAttribute("operation");
					System.out.println("operation有無：" + operation);

			   // 	sc.getRequestDispatcher("/WEB-INF/jsp/last3.jsp").forward(request,  response);//pount.servletに以降の処理を移すため、この処理は不要。
			    	
					String disp = "/point";//この３行の処理を行うことで、以降の処理は"/point"=PointServletに渡される。
				    RequestDispatcher dispatch = request.getRequestDispatcher(disp);
				    dispatch.forward(request, response);
				}
				
				return null;
		}
    }


