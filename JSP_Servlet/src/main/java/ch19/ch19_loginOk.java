package ch19;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class ch19_loginOk
 */
public class ch19_loginOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	private String name, id, pw, phone1, phone2, phone3, gender;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ch19_loginOk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		id= request.getParameter("id"); 
		pw= request.getParameter("pw");
		
		String query= "select * from member where id='"+id+"'and pw='"+pw+"'";
		
		try {			
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버 로드
			connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "c##scott", "tiger");
			statement= connection.createStatement();
			resultSet= statement.executeQuery(query);
			
			while(resultSet.next()) { // 데이터가 있다면(아이디와 비밀번호에 해당하는 값을 이미 가져왔기에 while문을 한 번만 돌게 됨)
				name= resultSet.getString("name");
				id= resultSet.getString("id");
				pw= resultSet.getString("pw");
				phone1= resultSet.getString("phone1");
				phone2= resultSet.getString("phone2");
				phone3= resultSet.getString("phone3");
				gender= resultSet.getString("gender");
			}
			
			// 브라우저당 하나의 세션이 존재
			HttpSession httpSession= request.getSession(); // 세션을 구하는 코드
			httpSession.setAttribute("name", name);
			httpSession.setAttribute("id", id);
			httpSession.setAttribute("pw", pw);
			
			response.sendRedirect("ch19_loginResult.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				if(resultSet!=null) resultSet.close();
				if(statement!=null) statement.close();
				if(connection!=null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	

}
