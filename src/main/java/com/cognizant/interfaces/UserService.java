package com.cognizant.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.entities.User;

@Service
@Transactional
public interface UserService {
	
//	public void createUser(User meetingroom);
    public List<User> getUsers();
    public User findById(int id);
    public User findByEmpId(int empid);
//    public User update(User user, int id);
//    public Boolean deleteUserById(int id);

}
