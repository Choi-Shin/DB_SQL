package study07_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

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
	
	public static void main(String[] args) {
		
            ConnectionTest ct = new ConnectionTest();
            ct.connect();

	}


}
