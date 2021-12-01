package p2.scott;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//SCOTT DB에 있는 EMP테이블 데이터를 자바에서 출력하기
public class EmpEx1 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//1.Driver 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		//2.계정 연결
		Connection conn = DriverManager.getConnection(url, "scott", "scott");
		Statement stmt = conn.createStatement();
		//3.Query
		String query = "select * from emp";
		//4.실행과 리턴
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()) {
			int empno = rs.getInt(1);
			String ename = rs.getString(2);
			String job = rs.getString(3);
			int mgr = rs.getInt(4);
			Date hiredate = rs.getDate(5);
			int sal = rs.getInt(6);
			int comm = rs.getInt(7);
			int deptno = rs.getInt(8);
			System.out.println(empno+" : "+ename+" : "+job+" : "+mgr+" : "+hiredate+" : "+sal+" : "+comm+" : "+deptno);
		}
		//5.DB종료
        rs.close();
        stmt.close();
        conn.close();
	}

}
