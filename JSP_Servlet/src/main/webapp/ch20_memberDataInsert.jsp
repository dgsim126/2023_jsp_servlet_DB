<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%!
    	Connection connection;
    	PreparedStatement preparedStatement;
    	ResultSet resultSet;
    	
    	String driver= "oracle.jdbc.driver.OracleDriver";
    	String url= "jdbc:oracle:thin:@localhost:1521:ORCL";
    	String uid= "c##scott";
    	String upw= "tiger";
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		try{
			Class.forName(driver);
			connection= DriverManager.getConnection(url, uid, upw);
			int n;
			String query= "insert into memberforpre (id, pw, name, phone) values (?, ?, ?, ?)";
			preparedStatement= connection.prepareStatement(query);
			
			preparedStatement.setString(1, "abc");
			preparedStatement.setString(2, "123");
			preparedStatement.setString(3, "홍길동");
			preparedStatement.setString(4, "010-1234-5678");
			n= preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "def");
			preparedStatement.setString(2, "456");
			preparedStatement.setString(3, "홍길자");
			preparedStatement.setString(4, "010-2222-5678");
			n= preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "ghi");
			preparedStatement.setString(2, "789");
			preparedStatement.setString(3, "홍길순");
			preparedStatement.setString(4, "010-3333-5678");
			n= preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "AAA");
			preparedStatement.setString(2, "111");
			preparedStatement.setString(3, "이길동");
			preparedStatement.setString(4, "010-4444-5678");
			n= preparedStatement.executeUpdate();
			
			if(n==1){
				out.println("insert success");
			}else{
				out.println("insert fail");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(resultSet!=null) resultSet.close();
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	%>
	
	<br>
	<a href="ch20_memberDataView.jsp">회원정보 보기</a>
</body>
</html>