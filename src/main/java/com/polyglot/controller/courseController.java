package com.polyglot.controller;




import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.polyglot.models.courses;

@RestController
@RequestMapping(path="api/courses")


public class courseController {
	@PostMapping
	public void courseRegistration(courses Course) {
		// define  the logic in next video
	}
	@GetMapping
	public List<courses> getCourse(){
		return null;
		
	}

}
