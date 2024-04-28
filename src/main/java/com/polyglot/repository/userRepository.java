package com.polyglot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polyglot.models.user;
@Repository
public interface userRepository extends JpaRepository<user,Long>  {
	 List<user> findUserByUsernameAndPassword(String username, String password);
	 List<user> findAllById(long id);
	 List<user> findAllByUsername(String username);
	 List<user> findAllByName(String name);

	 

	user findByEmail(String email);

	user findByUsername(String username);
}
