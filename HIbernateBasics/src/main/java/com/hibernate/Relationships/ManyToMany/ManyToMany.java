package com.hibernate.Relationships.ManyToMany;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManyToMany {

    private static SessionFactory sf;
    private static Scanner sc;

    static {
        sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n---- MANY TO MANY CRUD ----");
            System.out.println("1. Create Student");
            System.out.println("2. Create Course");
            System.out.println("3. Enroll Student to Course");
            System.out.println("4. Read All Students");
            System.out.println("5. Read All Courses");
            System.out.println("6. Read Courses by Student ID");
            System.out.println("7. Read Students by Course ID");
            System.out.println("8. Update Student");
            System.out.println("9. Update Course");
            System.out.println("10. Delete Student");
            System.out.println("11. Delete Course");
            System.out.println("12. Exit");
            System.out.print("Choose option: ");

            int n = sc.nextInt();
            sc.nextLine();

            switch (n) {

                case 1:
                    createStudent();
                    break;

                case 2:
                    createCourse();
                    break;

                case 3:
                    enrollStudentToCourse();
                    break;

                case 4:
                    readAllStudents();
                    break;

                case 5:
                    readAllCourses();
                    break;

                case 6:
                    readCoursesByStudentId();
                    break;

                case 7:
                    readStudentsByCourseId();
                    break;

                case 8:
                    updateStudent();
                    break;

                case 9:
                    updateCourse();
                    break;

                case 10:
                    deleteStudent();
                    break;

                case 11:
                    deleteCourse();
                    break;

                case 12:
                    sf.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("INVALID OPTION");
            }
        }
    }

    private static void createStudent() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            System.out.print("Enter student name: ");
            Student s = new Student(sc.nextLine());
            session.persist(s);
            tx.commit();
            System.out.println("Student created: " + s);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void createCourse() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            System.out.print("Enter course name: ");
            String name = sc.nextLine();
            System.out.print("Enter domain: ");
            String domain = sc.nextLine();

            Courses c = new Courses(name, domain);
            session.persist(c);
            tx.commit();
            System.out.println("Course created: " + c);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void enrollStudentToCourse() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            System.out.print("Enter student ID: ");
            int sid = sc.nextInt();
            System.out.print("Enter course ID: ");
            int cid = sc.nextInt();

            Student s = session.get(Student.class, sid);
            Courses c = session.get(Courses.class, cid);

            if (s == null || c == null) {
                System.out.println("Student or Course not found");
                return;
            }

            s.getCourses().add(c);
            c.getStudents().add(s);

            tx.commit();
            System.out.println("Student enrolled to course");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void readAllStudents() {
        Session session = sf.openSession();
        try {
            List<Student> list =
                    session.createQuery("from Student", Student.class).list();
            for (Student s : list) {
                System.out.println(s);
            }
        } finally {
            session.close();
        }
    }

    private static void readAllCourses() {
        Session session = sf.openSession();
        try {
            List<Courses> list =
                    session.createQuery("from Courses", Courses.class).list();
            for (Courses c : list) {
                System.out.println(c);
            }
        } finally {
            session.close();
        }
    }

    private static void readCoursesByStudentId() {
        Session session = sf.openSession();
        try {
            System.out.print("Enter student ID: ");
            int id = sc.nextInt();

            Student s = session.get(Student.class, id);
            if (s == null) {
                System.out.println("Student not found");
                return;
            }

            for (Courses c : s.getCourses()) {
                System.out.println(c);
            }
        } finally {
            session.close();
        }
    }

    private static void readStudentsByCourseId() {
        Session session = sf.openSession();
        try {
            System.out.print("Enter course ID: ");
            int id = sc.nextInt();

            Courses c = session.get(Courses.class, id);
            if (c == null) {
                System.out.println("Course not found");
                return;
            }

            for (Student s : c.getStudents()) {
                System.out.println(s);
            }
        } finally {
            session.close();
        }
    }

    private static void updateStudent() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            System.out.print("Enter student ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            Student s = session.get(Student.class, id);
            if (s == null) {
                System.out.println("Student not found");
                return;
            }

            System.out.print("Enter new name: ");
            s.setName(sc.nextLine());

            tx.commit();
            System.out.println("Student updated");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void updateCourse() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            System.out.print("Enter course ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            Courses c = session.get(Courses.class, id);
            if (c == null) {
                System.out.println("Course not found");
                return;
            }

            System.out.print("Enter new name: ");
            c.setName(sc.nextLine());
            System.out.print("Enter new domain: ");
            c.setDomain(sc.nextLine());

            tx.commit();
            System.out.println("Course updated");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void deleteStudent() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            System.out.print("Enter student ID: ");
            int id = sc.nextInt();

            Student s = session.get(Student.class, id);
            if (s == null) {
                System.out.println("Student not found");
                return;
            }

            s.getCourses().forEach(c -> c.getStudents().remove(s));
            s.getCourses().clear();
            session.remove(s);

            tx.commit();
            System.out.println("Student deleted");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void deleteCourse() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        try {
            System.out.print("Enter course ID: ");
            int id = sc.nextInt();

            Courses c = session.get(Courses.class, id);
            if (c == null) {
                System.out.println("Course not found");
                return;
            }

            c.getStudents().forEach(s -> s.getCourses().remove(c));
            c.getStudents().clear();
            session.remove(c);

            tx.commit();
            System.out.println("Course deleted");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
