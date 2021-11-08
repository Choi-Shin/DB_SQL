package plsql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class CallInoutProc {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		String p1value = "a";
		String p2value = "b";
		String p3value ;
		
		Connection conn = DriverManager.getConnection(url, "javalink", "javalink");
		CallableStatement cs = conn.prepareCall("{call javatest(?,?,?)}");
		cs.setString(1, p1value);
		
		cs.setString(2, p2value);
		cs.registerOutParameter(2, Types.VARCHAR);
		
		cs.registerOutParameter(3, Types.VARCHAR);
		
		cs.execute();
		
		p2value = cs.getString(2);
		p3value = cs.getString(3);
		
		System.out.println("p2: "+p2value);
		System.out.println("p3: "+p3value);
	}
}
