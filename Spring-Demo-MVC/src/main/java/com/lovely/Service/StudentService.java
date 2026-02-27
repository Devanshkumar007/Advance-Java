package com.lovely.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovely.Model.Student;
import com.lovely.Repository.StudentDao;

@Service
public class StudentService {

	@Autowired
	private StudentDao dao;
	
	public void saveStudent(Student student) {
		dao.save(student);
	}
}
