package com.cognizant.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.entities.Chore;
import com.cognizant.exception.ResourceNotFoundException;
import com.cognizant.interfaces.ChoreService;
import com.cognizant.repository.ChoreRepository;

@Service
@Transactional
public class ChoreServiceImp implements ChoreService{

	@Autowired
	ChoreRepository choreRepository;
	
	public ChoreServiceImp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChoreServiceImp(ChoreRepository choreRepository) {
		super();
		this.choreRepository = choreRepository;
	}

	@Override
	public void createChore(Chore chore) {
		choreRepository.save(chore);
		
	}
	
	@Override
	public List<Chore> getChores() {
		return (List<Chore>) choreRepository.findAll();
	}

	@Override
	public Chore findById(int id) {
		 Optional<Chore> optChore = choreRepository.findById(id); // returns java8 optional
		    if (optChore.isPresent()) {
		        return optChore.get();
		    } else {
		       throw new ResourceNotFoundException("Chore", "Id", id);
		    }
	}
	
	@Override
	public List<Chore> findByEmpId(int empid) {
		return (List<Chore>) choreRepository.findByEmpId(empid);
	}

	@Override
	public Boolean deleteChoreById(int id) {
		 Optional<Chore> optChore = choreRepository.findById(id); // returns java8 optional
		 if (optChore.isPresent()) {
			 choreRepository.delete(optChore.get());
			 return true;
		    } else {
		       throw new ResourceNotFoundException("Chore", "Id", id);
		    }
	}

	@Override
	public Chore update(Chore Chore, int id) {
		Optional<Chore> optChore = choreRepository.findById(id); // returns java8 optional
	    if (optChore.isPresent()) {
	    	Chore newChore = optChore.get();
	    	newChore.setTaskid(Chore.getTaskid());
	    	newChore.setEmpid(Chore.getEmpid());
	    	newChore.setItem(Chore.getItem());
	    	newChore.setWeight(Chore.getWeight());
	    	newChore.setQuantity(Chore.getQuantity());

	        Chore updatedChore = choreRepository.save(newChore);
	        return updatedChore;
	    } else {
	    	throw new ResourceNotFoundException("Chore", "Id",id);
	    }
	}

	@Override
	public List<Chore> findByTaskId(int taskid) {
		return (List<Chore>) choreRepository.findByTaskId(taskid);
	}
	
	
}
