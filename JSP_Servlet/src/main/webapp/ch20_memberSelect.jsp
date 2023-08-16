<%@page import="ch20.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ch20.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		MemberDAO memberDAO= new MemberDAO();
		ArrayList<MemberDTO> dtos= memberDAO.memberSelect(); // 반환값이 ArrayList<MemberDTO>
		
		for(int i=0; i<dtos.size(); i++){
			MemberDTO dto= dtos.get(i); // 하나의 dto객체 ArrayList객체인 dtos의 i번째 값을 가져옴
			String name= dto.getName();
			String id= dto.getId();
			String pw= dto.getPw();
			String phone= dto.getPhone1()+"-"+dto.getPhone2()+"-"+dto.getPhone3();
			String gender= dto.getGender();
			
			out.println("이름: "+name+", 아이디: "+id+", 비밀번호: "+pw+", 연락처: "+phone+", 성별: "+gender+"<br>");
		}
	%>
</body>
</html>