package com.JPADemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JPADemo.entities.Students;
import com.JPADemo.repository.StudentRepo;

@RestController   // @Controller + @ResponseBody
public class StudentController {


	@Autowired
	StudentRepo student;

	
//	http://localhost:8080/test
	
	@GetMapping("/test")
	public String helloStudents() {
		return "hello controller";
	}
	
	
//	http://localhost:8080/students
	
	@GetMapping("/students")
	public List<Students> getStudents() {
		return student.findAll();
	}
	
	
//	http://localhost:8080/addstudent
	
	@PostMapping("/addstudent")
	public String AddStudent(@RequestBody Students s) {
		student.save(s);
		return "Student Added Successfully : "+s;
	}
	
	
//	http://localhost:8080/studentbyid/38
	
	@GetMapping("/studentbyid/{rollNo}")
	public Optional<Students> getStudentById(@PathVariable long rollNo) {
		Optional<Students> byId = student.findById(rollNo);
		return byId;
	}
	
	
//	http://localhost:8080/studentcontains/name?name=aman
	
	@GetMapping("/studentcontains/name")
	public List<Students> findByNameContaining(@RequestParam String name){
		return student.findByNameContaining(name);
	}
	
	
//	http://localhost:8080/updatestudent/38
	
	@PutMapping("/updatestudent/{rollNo}")
	public Optional<Students> updateStudent(@PathVariable long rollNo, @RequestBody Students s) {
		
		Optional<Students> ss = student.findById(rollNo);
		
		if(ss.isPresent()) {
			Students s1 = ss.get();
			if(s.getName() != null) {
				s1.setName(s.getName());
			}
			if(s.getMarks() != 0) {
				s1.setMarks(s.getMarks());
			}
			student.save(s1);
		}
		return ss;
	}
	
	
}
