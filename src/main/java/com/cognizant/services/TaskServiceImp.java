package com.cognizant.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.entities.Task;
import com.cognizant.exception.ResourceNotFoundException;
import com.cognizant.interfaces.TaskService;
import com.cognizant.repository.TaskRepository;

@Service
@Transactional
public class TaskServiceImp  implements TaskService{

	@Autowired
	TaskRepository taskRepository;
	
	public TaskServiceImp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskServiceImp(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}

	@Override
	public void createTask(Task task) {
		taskRepository.save(task);
		
	}
	
	@Override
	public List<Task> getTasks() {
		System.out.println("Tasks: " + taskRepository.findAll());
		return (List<Task>) taskRepository.findAll();
	}

	@Override
	public Task findById(int id) {
		 Optional<Task> optTask = taskRepository.findById(id); // returns java8 optional
		    if (optTask.isPresent()) {
		        return optTask.get();
		    } else {
		       throw new ResourceNotFoundException("Task", "Id", id);
		    }
	}
	
	@Override
	public List<Task> findByEmpId(int empid) {
		return (List<Task>) taskRepository.findByEmpId(empid);
	}
	
	@Override
	public List<Task> findByTodayDate(int empid) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date today = cal.getTime();
		System.out.println("Tasks Today: " + today + empid);
		return (List<Task>) taskRepository.findByTodayDate(today, empid);
	}

	@Override
	public Boolean deleteTaskById(int id) {
		 Optional<Task> optTask = taskRepository.findById(id); // returns java8 optional
		 if (optTask.isPresent()) {
			 taskRepository.delete(optTask.get());
			 return true;
		    } else {
		       throw new ResourceNotFoundException("Task", "Id", id);
		    }
	}

	@Override
	public Task update(Task task, int id) {
		Optional<Task> optTask = taskRepository.findById(id); // returns java8 optional
	    if (optTask.isPresent()) {
	    	Task newTask = optTask.get();
	    	newTask.setEmpid(task.getEmpid());
	    	newTask.setDescription(task.getDescription());
	    	newTask.setDate(task.getDate());

	        Task updatedtask = taskRepository.save(newTask);
	        return updatedtask;
	    } else {
	    	throw new ResourceNotFoundException("Task", "Id",id);
	    }
	}
	
	
}
