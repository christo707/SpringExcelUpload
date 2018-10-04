package com.cognizant.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.entities.User;
import com.cognizant.exception.ResourceNotFoundException;
import com.cognizant.interfaces.UserService;
import com.cognizant.repository.UserRepository;

@Service
@Transactional
public class UserServiceImp implements UserService{

	@Autowired
	UserRepository userRepository;
	
	public UserServiceImp(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	

	public UserServiceImp() {
		super();
		// TODO Auto-generated constructor stub
	}


//	@Override
//	public void createUser(User user) {
//		userRepository.save(user);
//		
//	}

	@Override
	public List<User> getUsers() {
		System.out.println("Users: " + userRepository.findAll());
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User findById(int id) {
		 Optional<User> user = userRepository.findById(id); // returns java8 optional
		    if (user.isPresent()) {
		        return user.get();
		    } else {
		       throw new ResourceNotFoundException("User", "Id", id);
		    }
	}
	
	@Override
	public User findByEmpId(int empid) {
		 User user = userRepository.findByEmpId(empid); // returns java8 optional
		    if (user != null) {
		        return user;
		    } else {
		       throw new ResourceNotFoundException("User", "EmpId", empid);
		    }
	}

}
