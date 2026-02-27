package com.lpu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpu.dao.StudentRepo;
import com.lpu.model.Student;

import jakarta.transaction.Transactional;


@Service
public class StudentServiceImplementation implements StudentService {

	@Autowired
	private StudentRepo studentRepo;
	
	@Override
	@Transactional
	public void saveStudent(Student student) {
		// TODO Auto-generated method stub
		studentRepo.save(student);
	}

	@Override
	public Student getStudentByEmail(String email) {
		// TODO Auto-generated method stub
		Student student = studentRepo.findById(email).orElse(null);
		return student;
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		List<Student> students = studentRepo.findAll();
		return students;
	}

	@Override
	@Transactional
	public void deleteStudentByEmail(String email) {
		// TODO Auto-generated method stub
		studentRepo.deleteById(email);
	}
}
