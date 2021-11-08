package test4.mvc.controller;

import java.util.Scanner;
import java.util.Vector;
import test4.mvc.dto.Product;
import test4.mvc.service.Service;
import test4.mvc.service.ServiceImpl;

public class ProductMain {

	public static void main(String[] args) {
		
		Service service = new ServiceImpl();
		boolean flag = true;
		Scanner sc =  new Scanner(System.in);
		int menu;
		Product p = null;
		Vector<Product> v =null;
		
		while(flag) {
			System.out.println("1.제품등록 2.제품검색 3.전체검색 4.수정 5.삭제 6.종료");
			 menu = sc.nextInt();
			switch(menu) {
			case 1:
				service.addProduct();//1.insert
				break;
			case 2:
				p = service.getProduct();//2.select one
					System.out.println(p);
				break;
			case 3:
				v = service.getProducts();//3.select all
				for(int i=0; i<v.size(); i++)
				System.out.println(v.get(i));
				break;
			case 4:
				service.editProduct();//4.update
				break;
			case 5:
				service.delProduct();//5.삭제
				break;
			case 6:
				flag = false;
				break;	
			default:
				System.out.println("다시 입력하기");
			}
			
		}
		sc.close();
		
	}

}
