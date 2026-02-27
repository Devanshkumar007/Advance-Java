package com.hibernate.Relationships.ManyToOne;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ManyToOne {

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
            System.out.println("\n---- MANY TO ONE CRUD ----");
            System.out.println("1. Create Department");
            System.out.println("2. Create Employee");
            System.out.println("3. Read Employee by ID");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Delete Department");
            System.out.println("7. Read All Departments");
            System.out.println("8. Read All Employees");
            System.out.println("9. Exit");
            System.out.print("Choose option: ");

            int n = sc.nextInt();
            sc.nextLine();

            switch (n) {
                case 1:
                    createDepartment();
                    break;

                case 2:
                    createEmployee();
                    break;

                case 3:
                    readEmployeeById();
                    break;

                case 4:
                    updateEmployeeMenu();
                    break;

                case 5:
                    deleteEmployee();
                    break;

                case 6:
                    deleteDepartment();
                    break;

                case 7:
                    readAllDepartments();
                    break;

                case 8:
                    readAllEmployees();
                    break;

                case 9:
                    sf.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("INVALID OPTION");
            }
        }
    }

    // ---------- CREATE DEPARTMENT ----------
    private static void createDepartment() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.print("Enter department name: ");
            String name = sc.nextLine();

            Department dept = new Department(name);
            session.persist(dept);

            tx.commit();
            System.out.println("Department created: " + dept);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // ---------- CREATE EMPLOYEE ----------
    private static void createEmployee() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.print("Enter employee name: ");
            String empName = sc.nextLine();

            System.out.print("Enter department ID: ");
            int deptId = sc.nextInt();
            sc.nextLine();

            Department dept = session.get(Department.class, deptId);
            if (dept == null) {
                System.out.println("Department not found");
                return;
            }

            Employee emp = new Employee(empName, dept);
            session.persist(emp);

            tx.commit();
            System.out.println("Employee created: " + emp);
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // ---------- READ EMPLOYEE ----------
    private static void readEmployeeById() {
        Session session = sf.openSession();

        try {
            System.out.print("Enter employee ID: ");
            int id = sc.nextInt();

            Employee emp = session.get(Employee.class, id);
            System.out.println(emp != null ? emp : "Employee not found");
        } finally {
            session.close();
        }
    }

    // ---------- UPDATE EMPLOYEE SUBMENU ----------
    private static void updateEmployeeMenu() {
        while (true) {
            System.out.println("\n---- UPDATE EMPLOYEE ----");
            System.out.println("1. Update employee name");
            System.out.println("2. Change employee department");
            System.out.println("3. Back");
            System.out.print("Choose option: ");

            int ch = sc.nextInt();
            sc.nextLine();

            if (ch == 1) {
                updateEmployeeName();
            } else if (ch == 2) {
                updateEmployeeDepartment();
            } else if (ch == 3) {
                return;
            } else {
                System.out.println("Invalid option");
            }
        }
    }

    private static void updateEmployeeName() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.print("Enter employee ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            Employee emp = session.get(Employee.class, id);
            if (emp == null) {
                System.out.println("Employee not found");
                return;
            }

            System.out.print("Enter new name: ");
            emp.setName(sc.nextLine());

            tx.commit();
            System.out.println("Employee name updated");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void updateEmployeeDepartment() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.print("Enter employee ID: ");
            int empId = sc.nextInt();

            Employee emp = session.get(Employee.class, empId);
            if (emp == null) {
                System.out.println("Employee not found");
                return;
            }

            System.out.print("Enter new department ID: ");
            int deptId = sc.nextInt();
            sc.nextLine();

            Department dept = session.get(Department.class, deptId);
            if (dept == null) {
                System.out.println("Department not found");
                return;
            }

            emp.setDept(dept);
            tx.commit();
            System.out.println("Employee department updated");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // ---------- DELETE EMPLOYEE ----------
    private static void deleteEmployee() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.print("Enter employee ID: ");
            int id = sc.nextInt();

            Employee emp = session.get(Employee.class, id);
            if (emp == null) {
                System.out.println("Employee not found");
                return;
            }

            session.remove(emp);
            tx.commit();
            System.out.println("Employee deleted");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // ---------- DELETE DEPARTMENT ----------
    private static void deleteDepartment() {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.print("Enter department ID: ");
            int id = sc.nextInt();

            Department dept = session.get(Department.class, id);
            if (dept == null) {
                System.out.println("Department not found");
                return;
            }

            session.remove(dept);
            tx.commit();
            System.out.println("Department deleted");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // ---------- READ ALL ----------
    private static void readAllDepartments() {
        Session session = sf.openSession();
        try {
            List<Department> list =
                    session.createQuery("from Department", Department.class).list();
            for (Department d : list) {
                System.out.println(d);
            }
        } finally {
            session.close();
        }
    }

    private static void readAllEmployees() {
        Session session = sf.openSession();
        try {
            List<Employee> list =
                    session.createQuery("from Employee", Employee.class).list();
            for (Employee e : list) {
                System.out.println(e);
            }
        } finally {
            session.close();
        }
    }
}