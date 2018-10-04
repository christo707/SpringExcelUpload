package com.cognizant.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cognizant.entities.User;


public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(value = "SELECT * FROM users WHERE empid = ?1", nativeQuery = true)
	public User findByEmpId(int empid);

}
