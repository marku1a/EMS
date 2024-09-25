package com.example.ems.models;


import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String surname;
	private String email;
	private String password;
	private Boolean approved;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
	)
	Collection<Role> roles;

	public User() {
	}

	public User(String name, String surname, String email, String password, Collection<Role> roles) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public static UserBuilder builder() {
		return new UserBuilder();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}


	public static class UserBuilder {
		private String name;
		private String surname;
		private String email;
		private String password;
		private Collection<Role> roles;

		UserBuilder() {
		}


		public UserBuilder name(String name) {
			this.name = name;
			return this;
		}

		public UserBuilder surname(String surname) {
			this.surname = surname;
			return this;
		}

		public UserBuilder email(String email) {
			this.email = email;
			return this;
		}

		public UserBuilder password(String password) {
			this.password = password;
			return this;
		}



		public UserBuilder roles(Collection<Role> roles) {
			this.roles = roles;
			return this;
		}

		public User build() {
			return new User(this.name, this.surname, this.email, this.password, this.roles);
		}

	}
}
