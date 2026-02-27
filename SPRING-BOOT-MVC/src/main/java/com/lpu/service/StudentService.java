package com.lpu.service;

import java.util.List;

import com.lpu.model.Student;

public interface StudentService {
	public void saveStudent(Student student);
	
	public Student getStudentByEmail(String email);
	
	public List<Student> getAllStudents();
	
	public void deleteStudentByEmail(String email);
	
}