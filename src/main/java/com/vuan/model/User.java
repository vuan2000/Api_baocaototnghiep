package com.vuan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name" ,columnDefinition = "")
	private String name;
	
	@Column(name = "age")
	private String age;
	
	@Column(name = "role")
	private String role;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "address")
	private String address;
	
	@Column(name = "gender")
	private String gender;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;
	
	@Column(name = "avatar")
	private String avatar;

	public User() {
	}

	public User(long id, String name, String age, String role, Boolean enabled, String username, String password,
			String address, String gender, String phone, String email, String avatar) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.role = role;
		this.enabled = enabled;
		this.username = username;
		this.password = password;
		this.address = address;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.avatar = avatar;
	}
}
