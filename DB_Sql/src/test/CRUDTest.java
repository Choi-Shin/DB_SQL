package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUDTest {
	Connection conn = null;
	Statement stmt = null;
  
	
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버가 없습니다.");
		}
	}
	
	public void connect(){
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		try {
			conn = DriverManager.getConnection(url, "javalink", "javalink");
			System.out.println("Connection Success!!!");
		} catch (SQLException e) {
			System.out.println("주소,id,pw가 다릅니다.");
		}
	}

	public void insert() {

		// "'aaa','1111','홍길동','22','서울시','a@a.com'"
		
        try {
			//Class.forName("java.lang.String");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			
			conn = DriverManager.getConnection(url, "javalink", "javalink");
			stmt = conn.createStatement();
			
			String query = "insert into member1 values('aaa','1111','홍길동',22,'서울시','a@a.com')";
			int count = stmt.executeUpdate(query);
			if(count >0)
				System.out.println("데이터가 저장되었습니다.");
			else
				System.out.println("데이터가 저장되지 않았습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver가 없음:: 해당 클래스를 찾을수 없습니다.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
        System.out.println("정상종료");
        try {
			conn.close();
		} catch (SQLException e) {
			
		}
	}

	public void select() {
		
        try {
			//Class.forName("java.lang.String");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			
			conn = DriverManager.getConnection(url, "javalink", "javalink");
			stmt = conn.createStatement();
			String query = "select * from member1";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String id = rs.getString(1);
				String password = rs.getString(2);
				String name = rs.getString("name");
				String age = rs.getString(4);
				String addr = rs.getString("addr");
				String email = rs.getString("email");
				System.out.println(id+" : "+password+" : "+name+" : "+age+" : "+addr+" : "+email);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver가 없음:: 해당 클래스를 찾을수 없습니다.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
        System.out.println("정상종료");
        try {
			conn.close();
		} catch (SQLException e) {
			
		}
	}

	public void update() {
		// id=aaa 를 찾아서 addr=부산시
		
        try {
			//Class.forName("java.lang.String");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			
			conn = DriverManager.getConnection(url, "javalink", "javalink");
			stmt = conn.createStatement();
			
			String query = "update member1 set addr='부산시' where id='aaa'";
			int count = stmt.executeUpdate(query);
			if(count >0)
				System.out.println("업데이트가 되었습니다.");
			else
				System.out.println("업데이트가 되지 않았습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver가 없음:: 해당 클래스를 찾을수 없습니다.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
        System.out.println("정상종료");
        try {
			conn.close();
		} catch (SQLException e) {
			
		}
	}

	public void delete() {
        //id=aaa 삭제		
		
        try {
			//Class.forName("java.lang.String");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			
			conn = DriverManager.getConnection(url, "javalink", "javalink");
			stmt = conn.createStatement();
			
			String query = "delete member1 where id='aaa'";
			int count = stmt.executeUpdate(query);
			if(count >0)
				System.out.println("데이터가 삭제되었습니다.");
			else
				System.out.println("데이터가 저장되지 않았습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver가 없음:: 해당 클래스를 찾을수 없습니다.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
        System.out.println("정상종료");
        try {
			conn.close();
		} catch (SQLException e) {
			
		}
		
	}

	public static void main(String[] args) {
         
		CRUDTest st = new CRUDTest();
		
		st.connect();
		st.insert();
		System.out.println("insert 수행 후");
		st.connect();
		st.select();

		st.connect();
		st.update();
		System.out.println("update 수행 후");
		st.connect();
		st.select();

		
		st.connect();
		st.delete();
		System.out.println("delete 수행 후");
		st.connect();
		st.select();
	}

}