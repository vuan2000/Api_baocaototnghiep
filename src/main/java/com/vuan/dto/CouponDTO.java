package com.vuan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
public class CouponDTO {
	private long id;
	private String code;
	private float present;

	public CouponDTO() {
	}

	public CouponDTO(long id, String code, float present) {
		this.id = id;
		this.code = code;
		this.present = present;
	}
}
