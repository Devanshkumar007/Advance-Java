package com.lpu;

import com.lpu.Entity.Employee;
import com.lpu.dao.EmployeeDao;

public class App {
	public static void main(String[] args) {
		EmployeeDao empDao = new EmployeeDao();
		
		//Insert emp 
//		empDao.saveEmployee(new Employee("Devansh", "IT", 1000.00));
		
		//Fetch and print details 
		empDao.getEmployee(1);
		
		//UpdateSalary
		empDao.updateSalary(1, 100000.00);
		
		//delete 
		empDao.deleteEmployee(1);
		
	}
}
