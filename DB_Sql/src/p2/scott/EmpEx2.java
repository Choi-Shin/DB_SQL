package p2.scott;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//SCOTT DB에 있는 EMP테이블 데이터를 자바에서 출력하기
//조건- 스캐너 입력을 받아서 empno=7902에 해당 레코드(tuple) 출력하기
public class EmpEx2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//1.Driver 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		//2.계정 연결
		Connection conn = DriverManager.getConnection(url, "scott", "scott");
		Statement stmt = conn.createStatement();
		//3.Query
		Scanner sc = new Scanner(System.in);
		System.out.println("[EMPNO 입력: ]");
		int no = sc.nextInt();//7902
		String query = "select * from emp where empno="+no;
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
		sc.close();
        rs.close();
        stmt.close();
        conn.close();
	}

}
