package com.cognizant.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@Column(name="user_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="empid")
	private int empid;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="pass_id")
	private int passId;

	public User() {
		super();
	}

	public User(int empid, String firstName, String lastName, int passId) {
		super();
		this.empid = empid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passId = passId;
	}

	public User(int id, int empid, String firstName, String lastName, int passId) {
		super();
		this.id = id;
		this.empid = empid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passId = passId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", empid=" + empid + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", passId=" + passId + "]";
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPassId() {
		return passId;
	}

	public void setPassId(int passId) {
		this.passId = passId;
	}

	

}
