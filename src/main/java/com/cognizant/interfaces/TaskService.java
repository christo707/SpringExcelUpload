package com.cognizant.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.entities.Task;

@Service
@Transactional
public interface TaskService {
	
	public void createTask(Task task);
    public List<Task> getTasks();
    public Task findById(int id);
    public List<Task> findByEmpId(int empid);
    public List<Task> findByTodayDate(int id);
    public Task update(Task task, int id);
    public Boolean deleteTaskById(int id);

}
