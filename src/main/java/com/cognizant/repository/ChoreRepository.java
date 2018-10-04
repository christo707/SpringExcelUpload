package com.cognizant.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cognizant.entities.Chore;
import com.cognizant.entities.Task;


public interface ChoreRepository extends JpaRepository<Chore, Integer>{
	
	@Query(value = "SELECT * FROM chores WHERE emp_id = ?1", nativeQuery = true)
	public List<Chore> findByEmpId(int empid);
	
	@Query(value = "SELECT * FROM chores WHERE task_id = ?1", nativeQuery = true)
	public List<Chore> findByTaskId(int taskid);

}
