package com.example.instructormanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.instructormanagement.model.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

}
