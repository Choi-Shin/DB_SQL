package plsql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class ProcedureTest {

	Connection conn;
	PreparedStatement pstmt;

	public void connect() {
		try {
			// Class.forName("java.lang.String");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "scott");
			System.out.println("접속완료");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver가 없음:: 해당 클래스를 찾을수 없습니다.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// 저장프로시저 호출전 데이터 입력
	public void insert() {
		connect();
		String sql = "insert into member2 values(?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "Alpha");
			pstmt.setString(2, "1234");
			pstmt.setString(3, "AI");
			pstmt.setInt(4, 20);
			pstmt.setString(5, "LA");
			pstmt.setString(6, "Alpha@aa.com");
			int count = pstmt.executeUpdate();
			if (count > 0) {
				System.out.println("insert success!!!");
			} else {
				System.out.println("insert fail!!!");
			}
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
		}
	}

	public void select() {
		pstmt = null;
		String sql = "select * from member2";
		ResultSet rs = null;
		connect();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("아이디: " + rs.getString(1) + ", 비밀번호: " + rs.getString(2) + ", 이름: " + rs.getString(3)
						+ ", 나이: " + rs.getString(4) + ", 주소: " + rs.getString(5) + ", 이메일: " + rs.getString(6));
			}
		} catch (SQLException e) {

		}

	}
	public void insertMember(){
		connect();
		Scanner sc = new Scanner(System.in);
		System.out.print("user_id: ");
		String id = sc.next();
		System.out.print("user_pw: ");
		String pw = sc.next();
		System.out.print("user_name: ");
		String name = sc.next();
		System.out.print("user_age: ");
		int age = sc.nextInt();
		System.out.print("user_addr: ");
		String addr = sc.next();
		System.out.print("user_email: ");
		String email = sc.next();
		try {
			CallableStatement cs = conn.prepareCall("{call user_insert(?,?,?,?,?,?)}");
			
			cs.setString(1, id);
			cs.setString(2, pw);
			cs.setString(3, name);
			cs.setInt(4, age);
			cs.setString(5, addr);
			cs.setString(6, email);
			cs.execute();
		} catch (SQLException e) {
		}
		
	}


	public static void main(String[] args) {
		ProcedureTest pt = new ProcedureTest();

		System.out.println("***저장 프로시저 호출 전 데이터 ***");
		pt.insert();
		pt.select();
		
		pt.insertMember();
		pt.select();
	}

}
