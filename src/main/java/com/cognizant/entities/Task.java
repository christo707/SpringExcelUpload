package com.cognizant.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tasks")
public class Task {
	
	@Id
	@Column(name="task_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="emp_id")
	private int empid;
	
	@Column(name="description")
	private String description;
	
	@Column(name="date")
	@Temporal(TemporalType.TIMESTAMP)
    private Date date;

	public Task() {
		super();
	}

	public Task(int empid, String description, Date date) {
		super();
		this.empid = empid;
		this.description = description;
		this.date = date;
	}

	public Task(int id, int empid, String description, Date date) {
		super();
		this.id = id;
		this.empid = empid;
		this.description = description;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", empid=" + empid + ", description=" + description + ", date=" + date + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	


}
