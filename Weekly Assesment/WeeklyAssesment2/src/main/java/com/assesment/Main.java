package com.assesment;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.assesment.Entity.Course;
import com.assesment.Entity.Department;
import com.assesment.Entity.IDCard;
import com.assesment.Entity.Student;

public class Main {
	private static final SessionFactory sf;
	private static final Scanner sc;

	static {
		sf = new Configuration()
				.configure("hibernate.cfg.xml")
				.buildSessionFactory();
		sc = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		while(true) {
			System.out.println("1. Create Department");
			System.out.println("2. Create Student");
			System.out.println("3. Create Course");
			System.out.println("4. Enroll Student in Course");
			System.out.println("5. Add Student in Department");
			System.out.println("6. Show all students");
			System.out.println("7. Show all courses");
			System.out.println("8. Show all departments");
			System.out.println("9. Exit");
			System.out.println("ENTER CHOICE : ");
			int n = sc.nextInt();
			sc.nextLine();
			switch(n) {
			case 1 :
				createDepartment();
				break;
			case 2 :
				createStudent();
				break;
			case 3:
				createCourse(); break;
				
			case 4:
				enrollStudentsInCourse(); break;
			
			case 5: 
				enrollStudentInDepartment(); break;
			
			case 6 : 
				showStudents(); break;
				
			case 7 : 
				showCourses(); break;
			
			case 8 : 
				showDepartments(); break;
			
			case 9 : 
				break;
			
			default: 
				System.out.println("INVALID");
			}
			
			if(n==9) break;
		}
	}

	private static void showDepartments() {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
		
		try {
			Query<Department> q = session.createQuery("from Department", Department.class);
			List<Department> ls = q.list();
			if(ls.isEmpty()) {
				System.out.println("No items in the list");
			}
			else {
				System.out.println();
				ls.forEach(System.out::println);
				System.out.println();
			}
		}catch(Exception e) {
			System.out.println("ERROR!!!!!!!!!");
			e.printStackTrace();
		}
	}

	private static void showCourses() {
		// TODO Auto-generated method stub
Session session = sf.openSession();
		
		try {
			List<Course> ls = session.createQuery("from Course", Course.class).list();
			if(ls.isEmpty()) {
				System.out.println("No items in the list");
			}
			else {
				System.out.println();
				ls.forEach(System.out::println);
				System.out.println();
			}
		}catch(Exception e) {
			System.out.println("ERROR!!!!!!!!!");
			e.printStackTrace();
		}
	}

	private static void showStudents() {
	    Session session = sf.openSession();
	    try {
	        List<Student> list = session
	                .createQuery("from Student", Student.class)
	                .list();

	        if (list.isEmpty()) {
	            System.out.println("No students found");
	        } else {
	            list.forEach(System.out::println);
	        }
	    } finally {
	        session.close();
	    }
	}

	private static void enrollStudentInDepartment() {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		System.out.print("Enter student id: ");
		int sid = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter dept id: ");
		int did = sc.nextInt();
		sc.nextLine();
		try {
			Student s = session.get(Student.class, sid);
			Department d = session.get(Department.class, did);
			s.setDepartment(d);
			d.getStudents().add(s);
			session.merge(s);
			session.merge(d);
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	private static void enrollStudentsInCourse() {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		System.out.print("Enter student id: ");
		int sid = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter course id: ");
		int cid = sc.nextInt();
		sc.nextLine();
		try {
			Student s = session.get(Student.class, sid);
			Course c = session.get(Course.class, cid);
			s.getCourses().add(c);
			c.getStudents().add(s);
			session.merge(s);
			session.merge(c);
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	private static void createCourse() {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		System.out.print("Enter name for course: ");
		String name = sc.nextLine();
		try {
			Course course = new Course(name);
			session.persist(course);
			tx.commit();
			System.out.println(course);
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
	}


	private static void createStudent() {
	    Session session = sf.openSession();
	    Transaction tx = session.beginTransaction();

	    System.out.print("Enter name for Student: ");
	    String name = sc.nextLine();

	    try {
	        Student s = new Student(name);

	        System.out.print("Enter card number: ");
	        String cardNo = sc.nextLine();

	        IDCard id = new IDCard(cardNo);

	        s.setIdCard(id);
	        id.setStudent(s);

	        session.persist(s);
	        tx.commit();

	        System.out.println(s);
	    } catch (Exception e) {
	        tx.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}

	private static void createDepartment() {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		System.out.print("Enter name for department: ");
		String name = sc.nextLine();
		try {
			Department dept = new Department(name);
			session.persist(dept);
			tx.commit();
			System.out.println(dept);
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
	}
}
