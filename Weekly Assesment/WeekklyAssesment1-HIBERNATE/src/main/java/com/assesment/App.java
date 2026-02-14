package com.assesment;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.assesment.model.MenuItems;



public class App {
	private static SessionFactory sf ;
	private static Scanner sc ;
	static {
		sf = new Configuration()
				.configure("hibernate.cfg.xml")
				.buildSessionFactory();
		sc = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		while(true) {
			System.out.println("Restaurant Management System");
			System.out.println("1. Add Menu Item");
			System.out.println("2. View All Items");
			System.out.println("3. Update Price");
			System.out.println("4. Delete Item");
			System.out.println("5. Exit");
			System.out.print("Enter Choice: ");
			int n = sc.nextInt();
			sc.nextLine();
			
			switch (n) {
			case 1:
				addItem();
				break;
			case 2:
				viewAll();
				break;
			case 3:
				updatePrice();
				break;
			case 4:
				deleteItem();
			case 5:
				break;
			default:
				System.out.println("Invalid try again");
				break;
			}
			
			
			if(n==5) break;
			


		}
	}

	private static void addItem() {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			System.out.println("Enter name: ");
			String name =sc.nextLine();
			System.out.println("Enter price: ");
			double price =sc.nextDouble();
			sc.nextLine();
			System.out.println("Enter category: ");
			String category =sc.nextLine();
			MenuItems item = new MenuItems(name,price,category,true);
			session.persist(item);
			tx.commit();
			System.out.println("ITEM ADDED SUCCESSFULLY !!");
		}catch(Exception e){
			tx.rollback();
		}
		finally {
			session.close();
		}
		
		
	}
	
	private static void deleteItem() {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		System.out.println("Enter id :");
		int id = sc.nextInt();
		try {
		MenuItems item = session.get(MenuItems.class, id);
			if(item==null) throw new RuntimeException("ITEM NOT FOUND");
			else {
				session.remove(item);
				tx.commit();
				System.out.println("ITEM DELETED SUCCESSFULLY !!");
			}
		}catch(RuntimeException r) {
			System.out.println(r.getMessage());
			tx.rollback();
		}catch(Exception e) {
			System.out.println("Error Occured Unable to remove item");
			System.out.println(e.getMessage());
			tx.rollback();
		}finally {
			session.close();
		}
		
	}

	private static void updatePrice() {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		System.out.println("Enter id : ");
		int id = sc.nextInt();
		sc.nextLine();
		
		try {
			MenuItems item = session.get(MenuItems.class, id);
			if(item==null) throw new RuntimeException("ITEM NOT FOUND");
			
			System.out.println("Enter the new price: ");
			double price = sc.nextDouble();
			item.setPrice(price);
			session.merge(item);
			tx.commit();
			System.out.println("ITEM PRICE UPDATED SUCCESSFULLY !!");
			
		}catch(RuntimeException r) {
			System.out.println(r.getMessage());
			tx.rollback();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally{
			session.close();
		}
	}

	private static void viewAll() {
		Session session = sf.openSession();
		
		try {
			Query<MenuItems> q = session.createQuery("Select m from MenuItems m", MenuItems.class);
			List<MenuItems> ls = q.list();
			if(ls.isEmpty()) {
				System.out.println("No items in the list");
			}
			else {
				System.out.println();
				ls.forEach(System.out::println);
				System.out.println();
			}
//			System.out.println("!!");
		}catch(Exception e) {
			System.out.println("ERROR!!!!!!!!!");
			e.printStackTrace();
		}
	}
}
