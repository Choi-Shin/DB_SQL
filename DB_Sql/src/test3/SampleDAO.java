package test3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SampleDAO {
	
	//DB내용을 찾아서 SampleDTO에 넣은후 ArrayList
	public ArrayList<SampleDTO> findAll() throws ClassNotFoundException, SQLException {
		
		ArrayList<SampleDTO> sampleDTOs = new ArrayList<SampleDTO>();
		// DTO를 저장하는 리스트
		//1.Driver 연결
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
				//2.계정 연결
				Connection conn = DriverManager.getConnection(url, "javalink", "javalink");
				Statement stmt = conn.createStatement();
				//3.Query
				String query = "select * from book";
				//4.실행과 리턴
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()) {
					
					SampleDTO dto = new SampleDTO();
					
					dto.setId(rs.getString(1));
					dto.setName(rs.getString(2));
				    dto.setPrice(rs.getInt("price"));
					
				    sampleDTOs.add(dto);
					
				}
				
		return sampleDTOs;
	}

}
