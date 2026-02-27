package com.lpu.lib.dao;

import java.util.List;

import com.lpu.lib.model.Student;

public interface StudentDao {
	public List<Student> listall();
	Student findById(int roll);
	boolean removeById(int roll);
	Student update(Student student);
	Student add(Student student);
}
