package com.polyglot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polyglot.models.courses;
import com.polyglot.repository.courseRepository;
import com.polyglot.repository.userRepository;
@Service
public class courseService {
	private  final courseRepository CourseRepository ;
	@Autowired
	public  courseService (courseRepository CourseRepository) {
		this.CourseRepository=CourseRepository;
	}

	

	public List<courses> getAllCourses() {
		// TODO Auto-generated method stub
		return CourseRepository.findAll();
	}



	public courses getCourseById(Long courseId) {
		// TODO Auto-generated method stub
		
		return CourseRepository.getReferenceById( courseId) ;
	}



	public void save(courses course) {
		// TODO Auto-generated method stub
		CourseRepository.save(course);
		
	}
	public courses getCourseByName(String name) {
		return CourseRepository.findBycourseName(name);
		
		
	}



	public void deleteCourseById(Long id) {
		CourseRepository.deleteById(id);
		
		
	}



	

}
