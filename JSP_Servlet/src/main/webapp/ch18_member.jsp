<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	
	String driver= "oracle.jdbc.driver.OracleDriver"; 
	String url= "jdbc:oracle:thin:@localhost:1521:ORCL"; 
	String uid= "c##scott";
	String upw= "tiger";
	String query= "select * from member";
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <%
 	try{ // DB를 사용할 때는 예외처리를 하는 것이 바람직함
 		// ~~~ 데이터 베이스 연결 순서(암기할 것) ~~~
 		// 1. JDBC 드라이버 로드
 		Class.forName(driver); // 메모리에 OracleDriver가 로드 (해당 드라이버가 메모리가 상주하게 됨)
 		
 		// 2. 데이터베이스 연결
 		connection = DriverManager.getConnection(url, uid, upw);  // Connection 객체 생성(JDBC URL, 계정ID, 비밀번호)
 		
 		// 3. SQL문 실행
 		statement= connection.createStatement(); // Statement객체를 통해 SQL문이 실행됨
 		
 		// 4. 데이터베이스 연결 해제
 		resultSet= statement.executeQuery(query); // SQL문의 결과값을 ResultSet객체로 받음(executeQuery()실행 후 반환 되는 레코드 셋)
 		
 		while(resultSet.next()){ // next() 다음 레코드로 이동 (종류: next, previous, first, next, get메서드(getString, getInt..))
 			String id= resultSet.getString("id");
 			String pw= resultSet.getString("pw");
 			String name= resultSet.getString("name");
 			String phone= resultSet.getString("phone");

 			
 			out.print("아이디: " + id + ", 비밀번호: " + pw + ", 이름: " + name + ", 전화번호: " + phone + "<br>");
 		}
 		
 	}catch(Exception e){
 		e.printStackTrace();
 	}finally{ // 자원 해제
 		try{
 			if(resultSet!=null){
 				resultSet.close();
 			}
 			if(statement!=null){
 				statement.close();
 			}
 			if(connection!=null){
 				connection.close();
 			}
 		}catch(Exception e){
 		}
 	}
 %>
</body>
</html>