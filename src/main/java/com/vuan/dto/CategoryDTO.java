package com.vuan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
	private long id;
	private String name;

	public CategoryDTO() {
	}

	public CategoryDTO(long id, String name) {
		this.id = id;
		this.name = name;
	}
}
