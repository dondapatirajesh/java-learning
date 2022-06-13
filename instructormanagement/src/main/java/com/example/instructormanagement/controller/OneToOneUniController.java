package com.example.instructormanagement.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.instructormanagement.model.Instructor;
import com.example.instructormanagement.model.InstructorDetail;
import com.example.instructormanagement.repository.InstructorDetailRepository;
import com.example.instructormanagement.repository.InstructorRepository;

@RestController
@RequestMapping("/")
public class OneToOneUniController {
	
//	Instructor instructorInitial = new Instructor("rajesh", "dondapati", "rajesh@gmail.com");
//	
//	InstructorDetail instructorDetailInitial = new InstructorDetail("javacoders", "coding");
	
	
	@Autowired
	InstructorRepository instructorRepo;
	
	@Autowired
	InstructorDetailRepository instructorDetailRepo;
	
	
	
	@GetMapping("/instructors/{id}")	
	public Instructor findById(@PathVariable("id") Long id) {
		Optional<InstructorDetail> instructorDetail;
		instructorDetail = instructorDetailRepo.findById(id);
		InstructorDetail instructorDetailFinal = null;
		if(instructorDetail.isPresent()) {
			instructorDetailFinal = instructorDetail.get();
		}
		Optional<Instructor> instructor;
		instructor = instructorRepo.findById(id);
		Instructor instructorFinal = null;
		if (instructor.isPresent()) {
			instructorFinal = instructor.get();
		}
		instructorFinal.setInstructorDetail(instructorDetailFinal);
		return instructorFinal;
		
	}
	
	@PostMapping("/instructors")
	public Instructor save(@RequestBody Instructor instructor) {		
		return instructorRepo.save(instructor);
	}
}
