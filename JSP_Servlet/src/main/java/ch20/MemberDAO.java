package ch20;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/*	DAO(Data Access Object):
 *  데이터베이스에 접속해 데이터 추가, 삭제, 수정 등의 작업을 수행
 *  일반적인 JSP 혹은 Servlet 페이지 내에서 해당 로직을 함께 기술할 수 있으나, 유지보수 및 코드의 모듈화를 위해 별도의 DAO클래스 생성
 * */

public class MemberDAO {
	private String url= "jdbc:oracle:thin:@localhost:1521:ORCL";
	private String uid= "c##scott";
	private String upw= "tiger";
	
	public MemberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<MemberDTO> memberSelect(){ // 함수
		ArrayList<MemberDTO> dtos= new ArrayList<MemberDTO>(); // 새로운 ArrayList객체를 만듦
		
		Connection connection= null;
		Statement statement= null;
		ResultSet resultSet= null;
		
		try {
			connection= DriverManager.getConnection(url, uid, upw); // 데이터베이스 연결 생성
			statement= connection.createStatement(); // 
			resultSet= statement.executeQuery("select * from member"); // member테이블에서 모든 열을 선택
			
			while(resultSet.next()) { // member테이블을 한 열씩 읽기
				String name= resultSet.getString("name");
				String id= resultSet.getString("id");
				String pw= resultSet.getString("pw");
				String phone1= resultSet.getString("phone1");
				String phone2= resultSet.getString("phone2");
				String phone3= resultSet.getString("phone3");
				String gender= resultSet.getString("gender");
				
				MemberDTO dto= new MemberDTO(name, id, pw, phone1, phone2, phone3, gender); // MemberDTO 객체에 넣은 후
				dtos.add(dto); // ArrayList에 넣어준다.
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet!=null) resultSet.close();
				if(statement!=null) statement.close();
				if(connection!=null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
}
