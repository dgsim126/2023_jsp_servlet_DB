<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
		Connection connection;
		Statement statement;
		ResultSet resultSet;
		
		String name, id, pw, phone1, phone2, phone3, gender;
	%>
	<%
		id= (String)session.getAttribute("id");
		
		String query= "select * from member where id='"+id+"'";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "c##scott", "tiger");
		statement= connection.createStatement();
		resultSet= statement.executeQuery(query);
		
		while(resultSet.next()){
			name= resultSet.getString("name");
			pw= resultSet.getString("pw");
			phone1= resultSet.getString("phone1");
			phone2= resultSet.getString("phone2");
			phone3= resultSet.getString("phone3");
			gender= resultSet.getString("gender");
		}
	%>
	
	<form action="ModifyOk" method="post">
		아이디는 변경할 수 없습니다. 당신의 아이디: <%=id %><br>
		비밀번호 확인:<input type="password" name="pw" size="10" placeholder="비밀번호"><br>
		<input type="text" name="name" size="10" placeholder="이름" value=<%=name %>><br>
		
		
		전화번호: <select name="phone1">
			<option value="010">010</option>
			<option value="010">016</option>
			<option value="010">017</option>
			<option value="010">018</option>
			<option value="010">019</option>
			<option value="010">011</option>
		</select>-
		<input type="text" name="phone2" size="3" value=<%=phone2 %>>-
		<input type="text" name="phone3" size="3" value=<%=phone3 %>><br>
		
		<%if(gender.equals("man")){ %>
			성별구분: <input type="radio" name="gender" value="man" checked="checked">남 
	    	&nbsp; <input type="radio" name="gender" value="female">여<br>
		<%}else{ %>
			성별구분: <input type="radio" name="gender" value="man">남 
	    	&nbsp; <input type="radio" name="gender" value="female" checked="checked">여<br>
		<%} %>
		
		<input type="submit" value="정보수정">
		<input type="reset" value="취소">
	</form>
</body>
</html>