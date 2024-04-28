package com.polyglot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polyglot.models.courses;
import com.polyglot.models.user;
@Repository
public interface courseRepository extends JpaRepository<courses,Long>{
	
	courses findBycourseName(String name);
}
