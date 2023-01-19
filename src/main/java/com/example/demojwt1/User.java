package com.example.demojwt1;




import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String password;
	
	private String Role;

	
	
	public String getRole() {
		return Role;
	}

	public void setRole(String roles) {
		Role = roles;
	}


	
	public long getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	
	
	public User() {
		
		super();
	}
	public User(String name, String password, String role) {
		super();
		this.name = name;
		this.password = password;
		this.Role = role;
		
	}
	
	
}