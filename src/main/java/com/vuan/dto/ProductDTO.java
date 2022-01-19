package com.vuan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductDTO {
	private long id;
	private String name;
	private int quantity;
	private Long price;
	private String image;
	private String description;
	private long categoryId	;

	public ProductDTO() {
	}

	public ProductDTO(long id, String name, int quantity, Long price, String image, String description, long categoryId) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.image = image;
		this.description = description;
		this.categoryId = categoryId;
	}
}
