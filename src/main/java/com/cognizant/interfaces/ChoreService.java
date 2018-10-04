package com.cognizant.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.entities.Chore;

@Service
@Transactional
public interface ChoreService {
	
	public void createChore(Chore chore);
    public List<Chore> getChores();
    public Chore findById(int id);
    public List<Chore> findByTaskId(int taskid);
    public List<Chore> findByEmpId(int empid);
    public Chore update(Chore chore, int id);
    public Boolean deleteChoreById(int id);

}
