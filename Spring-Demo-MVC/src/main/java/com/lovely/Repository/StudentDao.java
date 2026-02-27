package com.lovely.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lovely.Model.Student;

@Repository
public class StudentDao {
	public void save(Student student) {
		System.out.println("Saved to DB: "+student.getName());
	}
}
