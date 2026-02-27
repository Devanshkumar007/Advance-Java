package com.lovely.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lovely.Model.Student;
import com.lovely.Service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService ss;
	
	@PostMapping("/register")
	public String registerStudent(@ModelAttribute Student student, Model model) {
		ss.saveStudent(student);
		model.addAttribute("name",student.getName());
		model.addAttribute("email",student.getEmail());
		return "success";
	}
	
	@GetMapping("/register")
	public String showForm() {
		return "register" ;
	}
}
