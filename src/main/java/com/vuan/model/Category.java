package com.vuan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "category")
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	@NonNull
	private String name;

	public Category() {
	}

	public Category(long id, @NonNull String name) {
		this.id = id;
		this.name = name;
	}
}
