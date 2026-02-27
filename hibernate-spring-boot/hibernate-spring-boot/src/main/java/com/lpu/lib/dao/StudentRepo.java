package com.lpu.lib.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lpu.lib.model.Student;


// it will basically work as component so it will also create a bean of this but since it is not a class a bean would not be created
@Repository // tell spring that this class will extract the data from database so convert runtime exceptions to sql exceptions 
public interface StudentRepo extends JpaRepository<Student, Integer>{
	
	//NO queerry was written bcz it was laready accorsin to the accepted jpa format
	// jpa will automatically generatea  hql querry for this 
	List<Student> findBySname(String sname);
	
	//QUERY for HQL query 
	@Query("select s from Student s where s.branch = :branch")
	List<Student> findByBranch(String branch);
	
	
	//Native query for SQL QUERY
	@Query(value= "select * from student where deptid= :id ", nativeQuery=true)
	List<Student> deptStudent(int id);

}
