package com.polyglot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.polyglot.models.*;
import com.polyglot.services.userService;

@RestController
@RequestMapping(path="api/user")
public class userController {
	private final userService UserService;
	
	@Autowired
	public userController(userService userService) {
		
		this.UserService = userService;
	}
	@PostMapping//post method use d for user registration
	public void userRegisration(@RequestBody user User) {
		//insert the logic to save the post request to database we will do that in the next video using service layer and repository layer
		
	}
	@GetMapping(path="{username}")
	public ResponseEntity<List<user>> userLogin(@PathVariable("username") String username, @RequestParam String password) {
	    List<user> user = UserService.getUserByIdPass(username, password);
	    if (user != null && !user.isEmpty()) {
	        return ResponseEntity.ok(user);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
	}

		
	}
	
	


