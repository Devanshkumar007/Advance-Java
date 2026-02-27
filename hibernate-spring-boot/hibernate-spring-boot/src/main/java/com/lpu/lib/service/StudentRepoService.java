package com.lpu.lib.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.lpu.lib.dao.StudentRepo;
import com.lpu.lib.model.Student;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

public class StudentRepoService implements StudentService {

	@Autowired
	StudentRepo sRepo;
	@Override
	public List<Student> listall() {
		// TODO Auto-generated method stub
		return sRepo.findAll();
	}

	@Override
	public Student findById(int roll) {
		// TODO Auto-generated method stub
		Optional<Student> opt = sRepo.findById(roll);
		return opt.orElse(null);
	}

	@Override
	@Transactional // if we add transaactional the jpa willl automatically beging commit and end the transaction // it can also handle all the exceptions if the transaction fails i.e it willl rollback if req 
	public boolean removeById(int roll) {
		// TODO Auto-generated method stub
		try{ 	
			sRepo.deleteById(roll);
		return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	@Transactional
	public Student update(Student student) {
		// TODO Auto-generated method stub
		Student s = sRepo.findById(student.getRoll()).orElseThrow(() -> new EntityNotFoundException("Student not found"));
		s.setSname(student.getSname());
		s.setBranch(student.getBranch());
		s.setDepid(student.getDepid());
		s.setDob(student.getDob());
		s.setGender(student.getGender());
		s.setPhone(student.getPhone());
		return s;
	}

	@Override
	@Transactional
	public Student add(Student student) {
		// TODO Auto-generated method stub
		sRepo.save(student);
		return student;
	}

	
}
