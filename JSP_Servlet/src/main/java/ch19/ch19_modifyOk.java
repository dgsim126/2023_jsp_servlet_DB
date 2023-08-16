package ch19;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Servlet implementation class ch19_modifyOk
 */
public class ch19_modifyOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	private Statement statement;
	
	private String name, id, pw, phone1, phone2, phone3, gender;
	
	HttpSession httpSession;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ch19_modifyOk() {
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
		response.setContentType("text/html;charset=UTF-8"); // 인코딩 및 컨텐츠 타입 설정
	    PrintWriter out = response.getWriter();
		
		out.write("hi there!");
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		httpSession= request.getSession();
		
		name= request.getParameter("name");
		id= request.getParameter("id");
		pw= request.getParameter("pw");
		phone1= request.getParameter("phone1");
		phone2= request.getParameter("phone2");
		phone3= request.getParameter("phone3");
		gender= request.getParameter("gender");
		
		response.setContentType("text/html;charset=UTF-8"); // 인코딩 및 컨텐츠 타입 설정
	    PrintWriter out = response.getWriter();
		
		if(pwConfirm()) {
			System.out.println("OK");
			
			// String query = "update member set name ='"+name+"', phone1 ='"+phone1+"', phone2 ='"+phone2+"', phone3 ='"+phone3+"', gender ='"+gender+"'";
			String query = "update member set name ='"+name+"', phone1 ='"+phone1+"', phone2 ='"+phone2+"', phone3 ='"+phone3+"', gender ='"+gender+"' where id = '"+id+"'";
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버 로드
				connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "c##scott", "tiger");
				statement= connection.createStatement();
				
				
				
				
				
				int i= statement.executeUpdate(query); // 현재 업데이트가 계속해서 실패하고 있음
				System.out.println("i:" +i);
				if(i==1) {
					System.out.println("update success");
					httpSession.setAttribute("name", name);
					out.write("check point 4");
					response.sendRedirect("ch19_modifyResult.jsp");
				}else {
					System.out.println("update failed");
					response.sendRedirect("ch19_modify.jsp");
				}
			}catch(Exception e) {
				out.write("exception");
				e.printStackTrace();
			}finally {
				try {
					out.write("finally");
					if(statement!=null) statement.close();
					if(connection!=null) connection.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		}else {
			// 추가기능 구현할 것
			System.out.println("NG");
		}
	}
	
	private boolean pwConfirm() {
		boolean rs= false;
		
		String sessionPw= (String)httpSession.getAttribute("pw");
		
		// error check
		System.out.println(sessionPw);
		System.out.println(pw);
		
		if(sessionPw.equals(pw)) {
			System.out.println("TRUE");
			rs= true;
		}else {
			System.out.println("FALSE");
			rs= false;
		}
		System.out.println(rs);
		return rs;
	}

}
