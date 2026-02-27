package com.hibernate.Relationships.OneToOne;


import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;



public class OneToOne {
	private static SessionFactory sessionfactory;
	static {
		sessionfactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
	}

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		int choice = 0;
		while (choice !=6) {
            System.out.println("\n---- ONE TO ONE CRUD ----");
            System.out.println("1. Create");
            System.out.println("2. Read");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Read All");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1 : createPerson(sc); break;
                case 2 : readPerson(sc); break;
                case 3 : updatePerson(sc); break;
                case 4 : deletePerson(sc); break;
                case 5 : realAll(); break;
                case 6 : {
                    sessionfactory.close();
                    break;
                }
                default : System.out.println("Invalid choice");
            }
        }
    }

	private static void deletePerson(Scanner sc) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
        Transaction tx = session.beginTransaction();
        System.out.println("Enter the id of person: ");
        int id = sc.nextInt();
        
        try {
        Person p = session.get(Person.class, id);
        if(p==null) throw new RuntimeException("Person not found with id: "+id);
        else {
        	session.remove(p);
        	tx.commit();
        	System.out.println("Person Delted");
        }
        }
        catch(RuntimeException r) {
        	System.out.println(r.getMessage());
        }
        catch(Exception e) {
        	tx.rollback();
        }
        finally {
        	session.close();
        }
        
	}

	private static void updatePerson(Scanner sc) {

	    Session session = sessionfactory.openSession();
	    Transaction tx = session.beginTransaction();

	    try {
	        System.out.println("Enter the id of person: ");
	        int id = sc.nextInt();
	        sc.nextLine(); // consume newline

	        Person p = session.get(Person.class, id);

	        if (p == null) {
	            throw new RuntimeException("Person not found with id: " + id);
	        }

	        System.out.println("Enter new name: ");
	        String name = sc.nextLine();

	        System.out.println("Enter new country: ");
	        String country = sc.nextLine();

	        p.setName(name);
	        p.getPassport().setCountry(country);

	        session.merge(p);
	        tx.commit();

	        System.out.println("Person updated successfully");

	    } catch (RuntimeException r) {
	        System.out.println(r.getMessage());
	        tx.rollback();
	    } catch (Exception e) {
	        tx.rollback();
	    } finally {
	        session.close();
	    }
	}


		private static void readPerson(Scanner sc) {

		    Session session = sessionfactory.openSession();

		    try {
		        System.out.println("Enter the id of person: ");
		        int id = sc.nextInt();

		        Person p = session.get(Person.class, id);

		        if (p == null) {
		            throw new RuntimeException("Person not found with id: " + id);
		        }

		        System.out.println(p);

		    } catch (RuntimeException r) {
		        System.out.println(r.getMessage());
		    } finally {
		        session.close();
		    }
		}

		
		
	
	
	

	private static void createPerson(Scanner sc) {
		// TODO Auto-generated method stub
		Session session = sessionfactory.openSession();
		Transaction tx = session.beginTransaction();
		sc.nextLine();
		System.out.println("Enter name passportno country");
		String name = sc.nextLine();
		String passportNo = sc.nextLine();
		String country = sc.nextLine();
		
		try {
			
			Person p = new Person(name, new Passport());
			p.getPassport().setPassportNumber(passportNo);
			p.getPassport().setCountry(country);
			p.getPassport().setPerson(p);
			session.persist(p);
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
		}
		finally {
			session.close();
		}
		
	}
	
	private static void realAll() {
		Session session = sessionfactory.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
            Query<Person> q = session.createQuery("select p from Person p", Person.class);
            List<Person> list = q.list();

            if (list.isEmpty()) {
                System.out.println("No records found");
            } else {
                list.forEach(System.out::println);
            }
        } finally {
            session.close();
        }
	}
	
	}
