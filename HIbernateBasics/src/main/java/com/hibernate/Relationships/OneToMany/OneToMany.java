package com.hibernate.Relationships.OneToMany;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OneToMany {

    private static SessionFactory sessionfactory;
    private static Scanner sc;

    static {
        sessionfactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n---- ONE TO MANY CRUD ----");
            System.out.println("1. Create Customer");
            System.out.println("2. Create Order");
            System.out.println("3. Read Customer by ID");
            System.out.println("4. Update Customer Orders");
            System.out.println("5. Delete Customer");
            System.out.println("6. Read All Customers");
            System.out.println("7. Read All Orders");
            System.out.println("8. Exit");
            System.out.print("Choose option: ");

            int n = sc.nextInt();
            sc.nextLine(); 

            switch (n) {
                case 1 : createCustomer(); break;
                case 2 : createOrder(); break;
                case 3 : readById(); break;
                case 4 : updateOrder(); break;
                case 5 : deleteCustomer(); break;
                case 6 : readAllCustomers(); break;
                case 7 : readAllOrders(); break;
                case 8 : 
                    sessionfactory.close();
                break;
                default : System.out.println("INVALID OPTION");
            }
            
            if(n==8) break;
        }
    }

    // ---------------- CREATE CUSTOMER ----------------
    private static void createCustomer() {
        Session session = sessionfactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.print("Enter customer name: ");
            String name = sc.nextLine();

            Customer cust = new Customer(name);
            session.persist(cust); 

            tx.commit();
            System.out.println("Customer created successfully "+cust);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // ---------------- CREATE ORDER ----------------
    private static void createOrder() {
        Session session = sessionfactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.print("Enter customer ID: ");
            int cid = sc.nextInt();
            sc.nextLine();

            Customer cust = session.get(Customer.class, cid);
            if (cust == null) {
                System.out.println("Customer not found");
                return;
            }

            System.out.print("Enter order name: ");
            String orderName = sc.nextLine();

            Orders order = new Orders(orderName, new Date(System.currentTimeMillis()));
            order.setCustomer(cust);

            cust.getOrders().add(order);
            tx.commit();
            System.out.println("Order added successfully");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // ---------------- READ CUSTOMER BY ID ----------------
    private static void readById() {
        Session session = sessionfactory.openSession();

        try {
            System.out.print("Enter customer ID: ");
            int id = sc.nextInt();

            Customer cust = session.get(Customer.class, id);
            if (cust == null) {
                System.out.println("Customer not found");
            } else {
                System.out.println(cust);
            }
        } finally {
            session.close();
        }
    }

    // ---------------- UPDATE ORDER ----------------
    private static void updateOrder() {
        while (true) {
            System.out.println("\n---- ORDER OPERATIONS ----");
            System.out.println("1. Update order name");
            System.out.println("2. Delete order");
            System.out.println("3. Back");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 : updateOrderName(); break;
                case 2 : deleteOrder(); break;
                case 3 :
                    return;
             
                default :System.out.println("Invalid option");
            }
        }
    }
    private static void updateOrderName() {
        Session session = sessionfactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.print("Enter Order ID: ");
            int orderId = sc.nextInt();
            sc.nextLine();

            Orders order = session.get(Orders.class, orderId);
            if (order == null) {
                System.out.println("Order not found");
                return;
            }

            System.out.print("Enter new order name: ");
            String newName = sc.nextLine();

            order.setName(newName);
            session.merge(order);

            tx.commit();
            System.out.println("Order name updated successfully");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    private static void deleteOrder() {
        Session session = sessionfactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.print("Enter Order ID: ");
            int orderId = sc.nextInt();

            Orders order = session.get(Orders.class, orderId);
            if (order == null) {
                System.out.println("Order not found");
                return;
            }

            session.remove(order);
            tx.commit();
            System.out.println("Order deleted successfully");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }



    // ---------------- DELETE CUSTOMER ----------------
    private static void deleteCustomer() {
        Session session = sessionfactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.print("Enter customer ID: ");
            int id = sc.nextInt();

            Customer cust = session.get(Customer.class, id);
            if (cust == null) {
                System.out.println("Customer not found");
                return;
            }

            session.remove(cust);
            tx.commit();

            System.out.println("Customer and orders deleted");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // ---------------- READ ALL ----------------
    private static void readAllCustomers() {
        Session session = sessionfactory.openSession();

        try {
            List<Customer> list = session.createQuery("from Customer", Customer.class).list();
            list.forEach(System.out::println);
        } finally {
            session.close();
        }
    }
    private static void readAllOrders() {
    	Session session = sessionfactory.openSession();
    	
    	try {
    		List<Orders> list = session.createQuery("from Orders", Orders.class).list();
    		list.forEach(System.out::println);
    	} finally {
    		session.close();
    	}
    }
}
