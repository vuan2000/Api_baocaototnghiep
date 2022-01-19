package com.vuan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "billproduct")
public class BillProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "unit_price")
	private Long unitPrice;

	@Column(name = "quantity")
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "id_product")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "id_bill")
	private Bill bill;

	public BillProduct() {
	}

	public BillProduct(long id, Long unitPrice, Integer quantity, Product product, Bill bill) {
		this.id = id;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.product = product;
		this.bill = bill;
	}
}
