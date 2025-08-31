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
@Table(name = "ACCOUNTING_RECORDS")
@Getter
@Setter
public class AccountingRecords {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "accounting_record_idx")
	private int accountingRecordIdx;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "transaction_idx")
	private Transactions transactionIdx;

	@Column(length = 10, nullable = false, name = "accounting_record_category")
	private String accountingRecordCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "account_subject_code")
	private AccountSubjects accountSubjectCode;

	@Column(nullable = false, name = "accounting_record_amount")
	private int accountingRecordAmount;

	@Column(length = 4000, nullable = false, name = "summary")
	private String summary;
}
