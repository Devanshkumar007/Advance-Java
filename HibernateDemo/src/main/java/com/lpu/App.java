package com.lpu;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n========= STUDENT MANAGEMENT SYSTEM =========");
            System.out.println("1. Insert Student");
            System.out.println("2. View Student By ID");
            System.out.println("3. View All Students");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter name: ");
                    sc.nextLine(); // consume newline
                    String name = sc.nextLine();

                    System.out.print("Enter age: ");
                    int age = sc.nextInt();

                    CRUD.createStudent(factory, name, age);
                    break;

                case 2:
                    System.out.print("Enter student ID: ");
                    int id = sc.nextInt();
                    CRUD.getStudent(factory, id);
                    break;

                case 3:
                    CRUD.getAllStudents(factory);
                    break;

                case 4:
                    System.out.print("Enter student ID: ");
                    int uid = sc.nextInt();

                    System.out.print("Enter new name: ");
                    sc.nextLine();
                    String newName = sc.nextLine();

                    System.out.print("Enter new age: ");
                    int newAge = sc.nextInt();

                    CRUD.updateStudent(factory, uid, newName, newAge);
                    break;

                case 5:
                    System.out.print("Enter student ID to delete: ");
                    int did = sc.nextInt();
                    CRUD.deleteStudent(factory, did);
                    break;

                case 0:
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        factory.close();
        sc.close();
    }
}
