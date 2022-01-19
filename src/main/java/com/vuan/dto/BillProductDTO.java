package com.vuan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
public class BillProductDTO {
	private int id;
	private long unitPrice;
	private int quantity;
	private long productId;
	private long billId;

	public BillProductDTO() {
	}

	public BillProductDTO(int id, long unitPrice, int quantity, long productId, long billId) {
		this.id = id;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.productId = productId;
		this.billId = billId;
	}
}
