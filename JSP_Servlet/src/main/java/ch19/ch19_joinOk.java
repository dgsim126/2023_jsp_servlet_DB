package ch19;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Servlet implementation class ch19_joinOk
 */
public class ch19_joinOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	private Statement statement;
	
	private String name, id, pw, phone1, phone2, phone3, gender;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ch19_joinOk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("doGet");
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		System.out.println("doPost");
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		name= request.getParameter("name");
		id= request.getParameter("id");
		pw= request.getParameter("pw");
		phone1= request.getParameter("phone1");
		phone2= request.getParameter("phone2");
		phone3= request.getParameter("phone3");
		gender= request.getParameter("gender");
		
		String query= "insert into member values('"+name+"','"+id+"','"+pw+"','"+phone1+"','"+phone2+"','"+phone3+"','"+gender+"')";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버 로드
			connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "c##scott", "tiger");
			statement= connection.createStatement();
			int i= statement.executeUpdate(query);  // 현재 상태 update 후, 몇 개가 업데이트 되었는지 반환
			
			if(i==1) {
				System.out.println("Insert Success");
				response.sendRedirect("ch19_joinResult.jsp");
			}else{
				System.out.println("Insert Fail");
				response.sendRedirect("ch19_join.html");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally { // 자원 해제
			try {
				if(statement!=null) {
					statement.close();
				}
				if(connection!=null) {
					connection.close();
				}
			}catch(Exception e) {}
		}
	}

}
