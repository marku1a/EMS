package com.example.ems.models;

import jakarta.persistence.*;


@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private String department;
	private Double salary;

	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
	private Performance performance;

	public Employee() {
	}

	public Employee(String name, String surname, String email, String phone, String department, Double salary) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.department = department;
		this.salary = salary;
	}

	public static EmployeeBuilder builder() {
		return new EmployeeBuilder();
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

	@Transient
	public String getFullName() {
		return name + " " + surname;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public static class EmployeeBuilder {
		private Integer id;
		private String name;
		private String surname;
		private String email;
		private String phone;
		private String department;
		private Double salary;

		EmployeeBuilder() {
		}

		public EmployeeBuilder id(Integer id) {
			this.id = id;
			return this;
		}

		public EmployeeBuilder name(String name) {
			this.name = name;
			return this;
		}

		public EmployeeBuilder surname(String surname) {
			this.surname = surname;
			return this;
		}

		public EmployeeBuilder email(String email) {
			this.email = email;
			return this;
		}

		public EmployeeBuilder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public EmployeeBuilder department(String department) {
			this.department = department;
			return this;
		}

		public EmployeeBuilder salary(Double salary) {
			this.salary = salary;
			return this;
		}


		public Employee build() {
			return new Employee(this.name, this.surname, this.email, this.phone, this.department, this.salary);
		}

		public String toString() {
			return "Employee.EmployeeBuilder(name=" + this.name + ", surname=" + this.surname + ", email=" + this.email + ", phone=" + this.phone + ", department=" + this.department + ", salary=" + this.salary + ")";
		}
	}
}
