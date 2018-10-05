package com.cognizant.tasks.ExcelTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.cognizant.entities.Chore;
import com.cognizant.interfaces.ChoreService;
import com.cognizant.utilities.ReadChoreFromExcel;

@RestController
@RequestMapping("/api/chores")
public class ChoreController {
	
	@Autowired
    private ChoreService choreServiceImp;
	
	@RequestMapping(value = "/get",  method = RequestMethod.GET, produces = "application/json")
	public List<Chore> getAllChore() {
        List<Chore> chores = choreServiceImp.getChores();
        return chores;
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Chore> getChoreById(@PathVariable("id") int id) {
        System.out.println("Fetching Chore with id " + id);
        Chore chore = choreServiceImp.findById(id);
        if (chore == null) {
            return new ResponseEntity<Chore>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Chore>(chore, HttpStatus.OK);
    }
	
	@GetMapping(value = "/empid/{id}", produces = "application/json")
    public List<Chore> getChoresByEmpId(@PathVariable("id") int id) {
        System.out.println("Fetching Chores with Emp id " + id);
        List<Chore> chores = choreServiceImp.findByEmpId(id);
        if (chores != null) {
        	 return chores;
        } else return null;
    }
	
	@GetMapping(value = "/taskid/{id}", produces = "application/json")
    public List<Chore> getChoresByTaskId(@PathVariable("id") int id) {
        System.out.println("Fetching Chores with Task id " + id);
        List<Chore> chores = choreServiceImp.findByTaskId(id);
        if (chores != null) {
        	 return chores;
        } else return null;
    }
	
//	@PostMapping(value="/create",headers="Accept=application/json")
//    public ResponseEntity<String> createTask(@RequestBody Chore chore, UriComponentsBuilder ucBuilder){
//        System.out.println("Creating Task "+ chore.getId());
//        choreServiceImp.createChore(chore);
//        return new ResponseEntity<String>("Chore Created Successfully", HttpStatus.CREATED);
//    }
	
	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
    public ResponseEntity<String> deleteTask(@PathVariable("id") int id){
        Chore chore = choreServiceImp.findById(id);
        if (chore == null) {
            return new ResponseEntity<String>("No such Chore", HttpStatus.NOT_FOUND);
        }
        choreServiceImp.deleteChoreById(id);
        return new ResponseEntity<String>("Chore Deleted", HttpStatus.NO_CONTENT);
    }

	@PutMapping(value="/update", headers="Accept=application/json")
    public ResponseEntity<Chore> updateTaskPartially(@RequestBody Chore chore){
        Chore choreFound = choreServiceImp.findById(chore.getId());
        if(choreFound == null){
            return new ResponseEntity<Chore>(HttpStatus.NOT_FOUND);
        }
        Chore r1 = choreServiceImp.update(chore, chore.getId());
        return new ResponseEntity<Chore>(r1, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile multiPartFile, @RequestParam String name) throws IOException {
		ReadChoreFromExcel test = new ReadChoreFromExcel();
		List<Chore> chores = test.read(multiPartFile);
		System.out.println("Name Received: " + name);
		if(chores != null){
      	System.out.println(chores);
      	for(Chore chore: chores){
      		choreServiceImp.createChore(chore);
      	}
      	return new ResponseEntity<String>("Read Excel Ok & Saved To Database", HttpStatus.OK);
      	
      } else 
      	return new ResponseEntity<String>("Fail to Read Excel", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(path = "/download", method = RequestMethod.GET, produces="application/octet-stream")
	public HttpEntity download(HttpServletResponse response) throws IOException, InvalidFormatException {

		List<Chore> chores = choreServiceImp.getChores();
		ReadChoreFromExcel test = new ReadChoreFromExcel();
		boolean b = test.write(chores);

		if(b){
			
			 File excel = new File("chores.xlsx");

		        InputStream is = new FileInputStream(excel);

		        response.setHeader("Content-Disposition", "attachment;filename=\"chores.xlsx\"");
		        response.setHeader("Content-Type", "application/octet-stream");
		        StreamUtils.copy(is ,response.getOutputStream());
		        is.close();
		        return new ResponseEntity(HttpStatus.OK);
			
//			java.io.File file = new java.io.File("chores.xlsx");
//		    Path path = Paths.get(file.getAbsolutePath());
//		    System.out.println(path);
//		    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//
//		    return ResponseEntity.ok()
//		            .contentLength(file.length())
//		            .contentType(MediaType.parseMediaType("application/octet-stream"))
//		            .body(resource);
		}
		 return new ResponseEntity(HttpStatus.NO_CONTENT);
		            
	}
	
}
