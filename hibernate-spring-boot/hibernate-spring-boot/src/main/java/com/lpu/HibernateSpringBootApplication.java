package com.lpu;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.lpu.algo.MyNextPasswordHasher;
import com.lpu.algo.MyService;
import com.lpu.algo.PasswordAlgo;
import com.lpu.algo.PasswordHasher;
import com.lpu.lib.model.Student;
import com.lpu.lib.service.BookService;
import com.lpu.lib.service.StudentRepoService;
import com.lpu.lib.service.StudentService;
import com.lpu.model.Department;
import com.lpu.model.Employee;
import com.lpu.model.Faculty;
import com.lpu.model.Parking;
import com.lpu.model.Person;
import com.lpu.model.Student2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

@SpringBootApplication
public class HibernateSpringBootApplication {

    private final MyNextPasswordHasher myNextPasswordHasher;

    private final PasswordHasher nextPasswordHasher;

    HibernateSpringBootApplication(PasswordHasher nextPasswordHasher, MyNextPasswordHasher myNextPasswordHasher) {
        this.nextPasswordHasher = nextPasswordHasher;
        this.myNextPasswordHasher = myNextPasswordHasher;
    }

	public static void main(String[] args) {
		ConfigurableApplicationContext context =SpringApplication.run(HibernateSpringBootApplication.class, args);
		PasswordAlgo algo = context.getBean(PasswordAlgo.class);
		String rev = algo.encrypt("ThisPass@123");
		System.out.println(rev);
		
		PasswordHasher bean = context.getBean(PasswordHasher.class);
		System.out.println(bean);
		
		PasswordHasher bean2 = (PasswordHasher) context.getBean("myXmlBeanSimple");
		System.out.println(bean2);
		
		
		
		//this will throw error because there are two classes mysph and mynph for this to get into bean to 
		// remove this error we will add primary to any of the two classes which will resolve the error and choose the primary as default 
		
//		context.getBeanNamesForType(PasswordHasher.class);
		String[] beans = context.getBeanDefinitionNames();
		for(String s :beans) {
			Object bean3 = context.getBean(s);
			if(bean3 instanceof PasswordHasher || bean3 instanceof PasswordAlgo)
			System.out.println(s);
		}
		
		
		MyService beanMS = context.getBean(MyService.class);
		beanMS.show();
		
		//System.out.println(beans.length);
		//testStudent(context);
		//testEmployee(context);
		//testParking(context);
		//testSingleTable(context);
		//testCustomQuery(context);
		//testBookService(context);
		//testStudentService(context);
		
	}
	
	public static void testStudentRepoService(ConfigurableApplicationContext context) {
		StudentRepoService srepo= context.getBean(StudentRepoService.class);
		
	
	}
	
	
	public static void testStudentService(ConfigurableApplicationContext context){
		StudentService bean = context.getBean(StudentService.class);
		Student s1 = bean.findById(101);
		System.out.println("findById : "+s1);
		
		bean.listall().forEach(System.out::println);
		
	}
	
	public static void testBookService(ConfigurableApplicationContext context) {
		BookService bean = context.getBean(BookService.class);
		bean.findByAuthor("sher").forEach(System.out::println);
	}
	
	public static void testCustomQuery(ConfigurableApplicationContext context) {
		EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select e from Employee e where e.name= :name"); //this is hql querry Hibernate Querry Language 
		query.setParameter("name", "BHAIS");
		query.getResultList().forEach(System.out::println);
		
		Query nativeq = em.createNativeQuery("select * from person");
		List<Object[]> resultList = nativeq.getResultList();
		resultList.forEach(ar -> System.out.println(Arrays.toString(ar)));
		
		
		Query q2 = em.createQuery("select p, p.employee from Parking p where p.employee.name = :name");
		q2.setParameter("name", "D");
		List<Object[]> result = q2.getResultList();
		result.forEach(ar -> System.out.println(Arrays.toString(ar)));
		
		
	}
	
	
	public static void testSingleTable(ConfigurableApplicationContext context) {
		EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Person p1 = new Person(0,"Rajan M","7865438900");
		Student2 s1 = new Student2(0,"Pratyush","6578900098",1221,"CSE");
		Faculty f1 = new Faculty(0,"Dinesh","8789098767",11044,"Trainer");
		em.persist(p1);
		em.persist(s1);
		em.persist(f1);
		em.getTransaction().commit();
		
	}
	
	public static void testStudent(ConfigurableApplicationContext context){
		// context created entity manager factory and data source 
		EntityManagerFactory emf =context.getBean(EntityManagerFactory.class);
		EntityManager em = emf.createEntityManager();
		
		//get and print student with id 
		Student student =em.find(Student.class, 101);
		System.out.println(student);
		
		
		// add to data base 
//		em.getTransaction().begin();
//		Student stud = new Student(211,"Priyanshu","CSE","9084658795",Date.valueOf("2001-11-12"),"M",2);
//		//em.persist(stud);
//		
//		em.getTransaction().commit();
//		em.close();
		
		
		
//		//change the name of the student here
//		em.getTransaction().begin();
//		Student s = em.find(Student.class, 104);
//		s.setSname("Kohit Kholi");
//		em.getTransaction().commit();
//		em.close();
		
		
		//to remove student 
//		em.getTransaction().begin();
//		Student s = em.find(Student.class, 104);
//		em.remove(s);
//		em.getTransaction().commit();
//		em.close();
		
		
		//print all the student from DB
		em.getTransaction().begin();
		Query q =em.createQuery("select s from Student s");
		q.getResultList().forEach(System.out::println);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public static void testEmployee(ConfigurableApplicationContext context) {
		EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
		EntityManager em = emf.createEntityManager();
		
		Employee e1 = em.find(Employee.class, 101);
		System.out.println(e1);
		
		Department dept = em.find(Department.class, 4);
		System.out.println();
		System.out.println("Working in "+dept.getName());
		dept.getEmployees().forEach(System.out::println);
		
		
		em.getTransaction().begin();
		Employee emp = new Employee();
		emp.setName("BHAIS");
		emp.setMgrid(101);
		emp.setDepartment(dept);
		emp.setDesignation("SE");
		em.persist(emp);
		em.getTransaction().commit();
		
		
	}
	
	public static void testParking(ConfigurableApplicationContext context) {
		EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
		EntityManager em = emf.createEntityManager();
		
		Parking p1 = em.find(Parking.class , 1101);
		System.out.println(p1);
		
		
	}

}
