package com.vuan.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "coupon")
public class Coupon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private long id;
	
	@Column(name = "code" ,unique = true)
	private String code;

	@Column(name = "present")
	private float present;

	public Coupon() {
	}

	public Coupon(@NonNull long id, String code, float present) {
		this.id = id;
		this.code = code;
		this.present = present;
	}
}
