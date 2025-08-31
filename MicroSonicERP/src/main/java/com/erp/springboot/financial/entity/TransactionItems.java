package com.erp.springboot.financial.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TRANSACTION_ITEMS")
@Getter
@Setter
public class TransactionItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "transaction_item_idx")
	private int transactionItemIdx;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "transaction_idx")
	private Transactions transactionIdx;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "item_code")
	private Items itemCode;
	
	@Column(nullable = false, name = "amount")
	private int amount;
	
	@Column(nullable = false, name = "total_price")
	private int totalPrice;
}
