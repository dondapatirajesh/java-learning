package com.rajesh.jackson.json.demo;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

	public static void main(String[] args) {
		
		Student student = null;
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			student = mapper.readValue(new File("data/sample-full.json"), Student.class);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		System.out.println(student.getAddress());
		
		for(String language: student.getLanguages()) System.out.println(language);
	}

}
