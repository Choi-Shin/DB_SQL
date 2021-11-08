package test2.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginSVC {
	
Connection con;
	
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
			con = DriverManager.getConnection(url, "javalink", "javalink");
			System.out.println("Connection Success!!!");
		} catch (SQLException e) {
			System.out.println("주소,id,pw가 다릅니다.");
		}
	}
	
	public User login(String id, String passwd) {
		
		connect();	
	    User user = null;
	    Statement stmt = null;
		try{

		    stmt = con.createStatement();
			String sql = "SELECT * FROM member1 WHERE id = '" + id + "' AND " +
					"password = '" + passwd + "'";
			ResultSet rs = stmt.executeQuery(sql);//'aaa','aaa','홍길동',22,'서울시','a@a.com'
			if(rs.next()) {
				id = rs.getString(1);
				passwd = rs.getString(2);
				String name = rs.getString(3);
				int age = rs.getInt("age");
				String addr = rs.getString("addr");
				String email = rs.getString("email");
				user = new User(id, passwd, name, age, addr, email);	
				
			}
			
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		finally{
			try{
				stmt.close();
				con.close();

			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return user;
	}
}

