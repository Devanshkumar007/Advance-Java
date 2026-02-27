package com.lpu.lib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lpu.lib.dao.StudentDao;
import com.lpu.lib.model.Student;

@Component
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentDao sdao;
	
	@Override
	public List<Student> listall() {
		// TODO Auto-generated method stub
		
		return sdao.listall();
	}

	@Override
	public Student findById(int roll) {
		// TODO Auto-generated method stub
		return sdao.findById(roll);
	}

	@Override
	public boolean removeById(int roll) {
		// TODO Auto-generated method stub
		return sdao.removeById(roll);
	}

	@Override
	public Student update(Student student) {
		// TODO Auto-generated method stub
		return sdao.update(student);
	}

	@Override
	public Student add(Student student) {
		// TODO Auto-generated method stub
		return sdao.add(student);
	}

}
