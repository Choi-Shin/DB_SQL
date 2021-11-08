package test4.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import test4.mvc.dto.Product;
import test4.mvc.util.DBConnect;

public class OracleDao implements Dao {

    //DBConnect dbconn2 = new DBConnection();
	private Connection  conn = null;
	private PreparedStatement pstmt = null;
	
	//기본생성자
	public OracleDao() {
		DBConnect dbconn =  DBConnect.getInstance(); //1.Singleton을 통한 객체생성
		conn = dbconn.getConnection(); //2.DB연결
	}
	
	public Connection getConn() {
		return conn;
	}
	
	@Override
	public void insert(Product p) {//insert
		String sql = "insert into product values(product_seq.nextval,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getName());
			pstmt.setInt(2, p.getPrice());
			int count = pstmt.executeUpdate();
			if(count > 0)
				System.out.println("데이터가 저장되었습니다.");
			else
				System.out.println("데이터가 저장되지 않았습니다.");
		} catch (SQLException e) {
			
		}
	}

	@Override
	public Product select(int num) {//select one
		 Product p = null;
		 ResultSet rs =null;
		    try {
				String query = "select * from product where num=?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, num);	
				rs = pstmt.executeQuery();
				if(rs.next()) {
						//int num2 = rs.getInt(1);
						String name = rs.getString("name");//2
						int price = rs.getInt("price");//3
						p = new Product(num, name, price);
						//System.out.println(num+" : "+name+" : "+price);
					}
			} catch (SQLException e) {
				
			}
		return p;
	}

	@Override
	public Vector<Product> selectAll() {//select all
		System.out.println("Oracle selectAll");
		Vector<Product> v = new Vector<Product>();
		
		String query = "select * from product order by num";
		try {
			pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int num = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				
				v.add(new Product(num, name, price));
			}
		} catch (SQLException e) {
			
		}
		
		
		return v;
	}

	@Override
	public void delete(int num) {//delete
		
		  try {
			  String query = "delete product where num=?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, num);	
				int count = pstmt.executeUpdate();
				if(count >0)
					System.out.println("데이터가 삭제되었습니다.");
				else
					System.out.println("데이터가 저장되지 않았습니다.");
			
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
	}

	@Override
	public void update(int num, Product p) {//update
		String query = "update product set name=?,price=? where num=?";
		 try {
			 pstmt = conn.prepareStatement(query);
			 pstmt.setString(1, p.getName());
			 pstmt.setInt(2, p.getPrice());
			 pstmt.setInt(3, p.getNum());
				
				int count = pstmt.executeUpdate();
				if(count >0)
					System.out.println("업데이트가 되었습니다.");
				else
					System.out.println("업데이트가 되지 않았습니다.");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
	       //Connection  되어 있으므로 다음 수행 작업인 delete 수행 불가
//	        try {
//				conn.close();
//			} catch (SQLException e) {
//				
//			}
	}

}
