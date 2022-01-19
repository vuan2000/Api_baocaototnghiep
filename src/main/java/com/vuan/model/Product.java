package com.vuan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private long id;

	@Column(name = "name" ,unique = true)
	private String name;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price")
	private Long price;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne()
	@JoinColumn(name = "id_category")
	private Category category ;

	public Product() {
	}

	public Product(@NonNull long id, String name, int quantity, Long price, String image, String description, Category category) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.image = image;
		this.description = description;
		this.category = category;
	}
}
