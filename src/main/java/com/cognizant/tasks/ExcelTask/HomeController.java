package com.cognizant.tasks.ExcelTask;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.entities.Chore;
import com.cognizant.entities.Test;
import com.cognizant.interfaces.ChoreService;
import com.cognizant.interfaces.TaskService;
import com.cognizant.utilities.ReadChoreFromExcel;

@RestController
@RequestMapping("/api")
public class HomeController {
	
	@Autowired
    private ChoreService choreServiceImp;
	
	@RequestMapping(value = "/test",  method = RequestMethod.GET, produces = "application/json")
	public Test test(){
		Test t = new Test(1, "API IS UP", new Date());
		return t;
	}
	
	@RequestMapping(value = "/testdate",  method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Test> testDate(@RequestBody Test test){
		System.out.println(test);
		System.out.println(test.getD());
		return new ResponseEntity<Test>(test, HttpStatus.OK);
	}
	
//	@RequestMapping(value = "/excel",  method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<String> Excel() throws IOException{
//		ReadChoreFromExcel test = new ReadChoreFromExcel();
//        test.setInputFile("C:\\Users\\727853\\Documents\\Tasks.xlsx");
//      //  List<Chore> chores = test.read();
//        if(chores != null){
//        	System.out.println(chores);
//        	for(Chore chore: chores){
//        		choreServiceImp.createChore(chore);
//        	}
//        	return new ResponseEntity<String>("Read Excel Ok & Saved To Database", HttpStatus.OK);
//        	
//        } else 
//        	return new ResponseEntity<String>("Fail to Read Excel", HttpStatus.BAD_REQUEST);
//	}

}
