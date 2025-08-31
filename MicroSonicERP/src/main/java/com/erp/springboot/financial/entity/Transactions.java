package com.erp.springboot.financial.entity;

import java.time.LocalDateTime;

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
@Table(name = "TRANSACTIONS")
@Getter
@Setter
public class Transactions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "transaction_idx")
	private int transactionIdx;
	
	@Column(nullable = false, name = "transaction_date")
	private LocalDateTime transactionDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "client_code")
	private Clients clientCode;
	
	@Column(length = 100, nullable = false, name = "proof_category")
	private String proofCategory;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "emp_id")
	private Employees empId;
	
	@Column(nullable = false, name = "file_code_idx", unique = true)
	private int fileCodeIdx;
	
	@Column(length = 20, nullable = false, name = "transaction_division")
	private String transactionDivision;
	
	@Column(nullable = false, name = "total_amount")
	private int totalAmount;
}
