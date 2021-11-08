package test4.mvc.dao;

import java.util.Vector;

import test4.mvc.dto.Product;
//인터페이스 : 강제성,일관성
public interface Dao {
	//추상 메소드
	void insert(Product p);
	Product select(int num);
	Vector<Product> selectAll();
	void delete(int num);
	void update(int num,Product p);
}
