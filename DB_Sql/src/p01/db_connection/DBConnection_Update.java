package p01.db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection_Update {

	public static void main(String[] args) {
		
		//update goodsinfo set maker='samsung' where code='10001';
		Connection conn = null;
		Statement stmt = null;
        try {
			//Class.forName("java.lang.String");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			
			conn = DriverManager.getConnection(url, "javalink", "javalink");
			stmt = conn.createStatement();
			
			String query = "update goodsinfo set maker='엘지' where code='10002'";
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

}
