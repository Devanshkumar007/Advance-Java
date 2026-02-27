package com.lpu;


import com.lpu.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CRUD {

    public static void createStudent(SessionFactory sf, String name, int age) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Student s = new Student(name, age);
            session.persist(s);
            tx.commit();
            System.out.println("Student inserted successfully");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void getStudent(SessionFactory sf, int id) {
        Session session = sf.openSession();

        try {
            Student s = session.get(Student.class, id);
            if (s != null)
                System.out.println(s);
            else
                System.out.println("Student not found");
        } finally {
            session.close();
        }
    }

    public static void getAllStudents(SessionFactory sf) {
        Session session = sf.openSession();

        try {
            Query<Student> q = session.createQuery("seelct s from Student s", Student.class);
            List<Student> list = q.list();

            if (list.isEmpty()) {
                System.out.println("No records found");
            } else {
                list.forEach(System.out::println);
            }
        } finally {
            session.close();
        }
    }

    public static void updateStudent(SessionFactory sf, int id, String name, int age) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Student s = session.get(Student.class, id);
            if (s != null) {
                s.setName(name);
                s.setAge(age);
                tx.commit();
                System.out.println("Student updated");
            } else {
                System.out.println("Student not found");
            }
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public static void deleteStudent(SessionFactory sf, int id) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Student s = session.get(Student.class, id);
            if (s != null) {
                session.remove(s);
                tx.commit();
                System.out.println("Student deleted");
            } else {
                System.out.println("Student not found");
            }
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
    }
}
