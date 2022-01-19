package com.vuan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDTO {
	private long id;
	private String name;
	private String age;
	private String role;
	private Boolean enabled;
	private String username;
	private String password;
	private String address;
	private String gender;
	private String phone;
	private String email;
	private String avatar;

	public UserDTO() {
	}

	public UserDTO(long id, String name, String age, String role, Boolean enabled, String username, String password, String address, String gender, String phone, String email, String avatar) {
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
