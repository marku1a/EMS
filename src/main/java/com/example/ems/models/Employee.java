package com.example.ems.models;
import jakarta.persistence.Entity;


import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;

@Entity
@Table(name = "employees")
	public class Employee {
	@Id
	@GeneratedValue 
	private Integer id;
	private String name;
	private String surname;
	private String email;
	private String phone;
	
	public Employee(){};
	public Employee(String name, String surname, String email, String phone) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
