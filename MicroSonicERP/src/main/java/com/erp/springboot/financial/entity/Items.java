package com.erp.springboot.financial.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ITEMS")
@Getter
@Setter
public class Items {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, length = 100, name = "item_code")
	private String itemCode;
	
	@Column(nullable = false, length = 100, name = "item_pic")
	private String itemPic;
	
	@Column(nullable = false, length = 255, name = "item_name")
	private String itemName;
	
	@Column(nullable = false, length = 255, name = "item_category")
	private String itemCategory;
	
	@Column(nullable = false, name = "item_ea_price")
	private BigDecimal itemEaPrice;
	
	@Column(nullable = false, name = "item_stock_amount")
	private int itemStockAmount;
	
	@Column(length = 255, name = "ofile")
	private String ofile;
	
	@Column(length = 255, name = "sfile")
	private String sfile;
	
	@Column(name = "adjust_amount")
	private Integer adjustAmount;
	
}
