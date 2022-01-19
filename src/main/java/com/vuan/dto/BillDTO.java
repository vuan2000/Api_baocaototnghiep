package com.vuan.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class BillDTO {
	private int id;
	private Date buyDate;
	private Long priceTotal;
	private String coupon;
	private int couponPresent;
	private long userId;
	private String status;
	private List<BillProductDTO> billProducts;
	private String pay;

	public BillDTO() {
	}

	public BillDTO(int id, Date buyDate, Long priceTotal, String coupon, int couponPresent, long userId, String status, List<BillProductDTO> billProducts, String pay) {
		this.id = id;
		this.buyDate = buyDate;
		this.priceTotal = priceTotal;
		this.coupon = coupon;
		this.couponPresent = couponPresent;
		this.userId = userId;
		this.status = status;
		this.billProducts = billProducts;
		this.pay = pay;
	}
}
