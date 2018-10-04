package com.cognizant.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cognizant.entities.Task;
import com.cognizant.entities.User;

public interface TaskRepository extends JpaRepository<Task, Integer>{
	
	@Query(value = "SELECT * FROM tasks WHERE emp_id = ?1", nativeQuery = true)
	public List<Task> findByEmpId(int empid);
	
	@Query(value = "SELECT * FROM tasks WHERE (date = ?1 and emp_id = ?2)", nativeQuery = true)
	public List<Task> findByTodayDate(Date date, int id);

}
