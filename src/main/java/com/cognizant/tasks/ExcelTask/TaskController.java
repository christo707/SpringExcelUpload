package com.cognizant.tasks.ExcelTask;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cognizant.entities.Task;
import com.cognizant.interfaces.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	@Autowired
    private TaskService taskServiceImp;
	
	@RequestMapping(value = "/get",  method = RequestMethod.GET, produces = "application/json")
	public List<Task> getAllTask() {
        List<Task> rooms = taskServiceImp.getTasks();
        return rooms;
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") int id) {
        System.out.println("Fetching Tasks with id " + id);
        Task task = taskServiceImp.findById(id);
        if (task == null) {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }
	
	@GetMapping(value = "/empid/{id}", produces = "application/json")
    public List<Task> getTaskByEmpId(@PathVariable("id") int id) {
        System.out.println("Fetching Tasks with Emp id " + id);
        List<Task> task = taskServiceImp.findByEmpId(id);
        if (task != null) {
        	 return task;
        } else return null;
    }
	
	@GetMapping(value = "/empid/todaydate/{id}", produces = "application/json")
    public List<Task> getTaskByTodayDate(@PathVariable("id") int id) {
		Date d = new Date();
        System.out.println("Fetching Tasks with Date " + d + id);
        List<Task> task = taskServiceImp.findByTodayDate(id);
        if (task != null) {
        	 return task;
        } else return null;
    }

	@PostMapping(value="/create",headers="Accept=application/json")
    public ResponseEntity<String> createTask(@RequestBody Task task, UriComponentsBuilder ucBuilder){
        System.out.println("Creating Task "+ task.getDescription());
        taskServiceImp.createTask(task);
        return new ResponseEntity<String>("Task Created Successfully", HttpStatus.CREATED);
    }
	
	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
    public ResponseEntity<String> deleteTask(@PathVariable("id") int id){
        Task task = taskServiceImp.findById(id);
        if (task == null) {
            return new ResponseEntity<String>("No such Task", HttpStatus.NOT_FOUND);
        }
        taskServiceImp.deleteTaskById(id);
        return new ResponseEntity<String>("Task Deleted", HttpStatus.NO_CONTENT);
    }

	@PutMapping(value="/update", headers="Accept=application/json")
    public ResponseEntity<Task> updateTaskPartially(@RequestBody Task task){
        Task taskFound = taskServiceImp.findById(task.getId());
        if(taskFound == null){
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
        Task r1 = taskServiceImp.update(task, task.getId());
        return new ResponseEntity<Task>(r1, HttpStatus.OK);
    }
	
}
