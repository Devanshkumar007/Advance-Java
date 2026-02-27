package com.lpu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lpu.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, String> {

}
