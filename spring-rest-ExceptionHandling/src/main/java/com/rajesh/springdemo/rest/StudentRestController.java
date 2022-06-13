package com.rajesh.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private List<Student> students = null;

	{
		System.out.println("StudentRestController has been initialized.");
	}

	/**
	 * Will load this data only once after the bean is initialized
	 */
	@PostConstruct
	public void loadData() {
		System.out.println("Entered into postconstruct");
		students = new ArrayList<Student>();
		System.out.println("empty arraylist has been created.");
		students.add(new Student("dondapati", "rajesh"));
		students.add(new Student("steve", "paul"));
		students.add(new Student("micheal", "rossi"));
		System.out.println("Students added into arraylist");
	}

	/**
	 * Endpoint for students - @return list of students
	 */
	@GetMapping("/students")
	public List<Student> getStudents() {
		System.out.println(students);
		return students;
	}

	/**
	 * @param studentId will be mapped to URI path variable {studentId} in
	 *                  GetMapping
	 * @return student if present in list else throws StudentNotFoundException
	 */
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable("studentId") int studentId) {
		if (studentId >= students.size() || studentId < 0) {
			throw new StudentNotFoundException("Student Not Found: " + studentId);
		}
		return students.get(studentId);
	}

}