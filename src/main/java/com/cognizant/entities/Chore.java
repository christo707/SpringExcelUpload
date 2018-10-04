package com.cognizant.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="chores")
public class Chore {
	
	@Id
	@Column(name="chore_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="task_id")
	private int taskid;
	
	@Column(name="emp_id")
	private int empid;
	
	@Column(name="item")
	private String item;
	
	@Column(name="weight")
	private String weight;
	
	@Column(name="quantity")
	private int quantity;

	public Chore() {
		super();
	}

	public Chore(int taskid, int empid, String item, String weight, int quantity) {
		super();
		this.taskid = taskid;
		this.empid = empid;
		this.item = item;
		this.weight = weight;
		this.quantity = quantity;
	}

	public Chore(int id, int taskid, int empid, String item, String weight, int quantity) {
		super();
		this.id = id;
		this.taskid = taskid;
		this.empid = empid;
		this.item = item;
		this.weight = weight;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Chore [id=" + id + ", taskid=" + taskid + ", empid=" + empid + ", item=" + item + ", weight=" + weight
				+ ", quantity=" + quantity + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	


}
