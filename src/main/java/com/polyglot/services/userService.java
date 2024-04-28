package com.polyglot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.polyglot.models.user;
import com.polyglot.repository.userRepository;

@Service
public class userService {
	private final userRepository UserRepository ;
	@Autowired
	public  userService (userRepository UserRepository) {
		this.UserRepository=UserRepository;
	}
	public List <user> getUserByIdPass(String username, String password) {
		List userByUsername_pass=UserRepository.findUserByUsernameAndPassword(username,password);
		return userByUsername_pass;
		 
		
		
		
	}
	
	public user getUserByEmail(String email) {
        return UserRepository.findByEmail(email);
    }
	public user getUserByUsername(String username) {
	    return UserRepository.findByUsername(username);
	}


    
	public void saveUser(user newUser) {
	    // Set the username as the ID for the user
	    
	    // Save the user entity
	    UserRepository.save(newUser);
	}
	public List<user> getAllUsers() {
		// TODO Auto-generated method stub
		return UserRepository.findAll();
	}
	public user getUserById(Long userId) {
		// TODO Auto-generated method stub
		return UserRepository.getReferenceById(userId);
	}
	public List<user>getAllUsersById(Long longValue) {
		// TODO Auto-generated method stub
		return UserRepository.findAllById(longValue) ;
	}
	public List<user> getAllUsersByName(String search) {
		// TODO Auto-generated method stub
		return UserRepository.findAllByName(search);
	}
	public List<user> getAllUsersByUsername(String search) {
		// TODO Auto-generated method stub
		return UserRepository.findAllByUsername(search);
	}
	

	
}
