package com.vuan.model;

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

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;



@Getter
@Setter
@Entity
@Transactional
@Table(name = "bill")
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "buy_date")
	private Date buyDate;

	@Column(name = "price_total")
	private Long priceTotal;

	@Column(name = "coupon")
	private String coupon;
	
	@Column(name = "coupon_present")
	private int couponPresent;
 
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User buyer;

	@Column(name = "status")
	private String status;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bill")
	private List<BillProduct> billProducts;

	@Column(name = "pay")
	private String pay;

	public Bill() {
	}

	public Bill(long id, Date buyDate, Long priceTotal, String coupon, int couponPresent, User buyer, String status, List<BillProduct> billProducts, String pay) {
		this.id = id;
		this.buyDate = buyDate;
		this.priceTotal = priceTotal;
		this.coupon = coupon;
		this.couponPresent = couponPresent;
		this.buyer = buyer;
		this.status = status;
		this.billProducts = billProducts;
		this.pay = pay;
	}
}
