package com.erp.springboot.financial.dto;

import com.erp.springboot.financial.entity.AccountingRecords;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountingRecordDTO {
	private int accountingRecordIdx;
	private int transactionIdx;
	private String accountingRecordCategory;
	private String accountSubjectCode;
	private String accountSubject;
	private int accountingRecordAmount;
	private String summary;
	
	public AccountingRecordDTO(AccountingRecords entity) {
		accountingRecordIdx = entity.getAccountingRecordIdx();
		transactionIdx = entity.getTransactionIdx().getTransactionIdx();
		accountingRecordCategory = entity.getAccountingRecordCategory();
		accountSubjectCode = entity.getAccountSubjectCode().getAccountSubjectCode();
		accountSubject = entity.getAccountSubjectCode().getAccountSubjectName();
		accountingRecordAmount = entity.getAccountingRecordAmount();
		summary = entity.getSummary();
	}
}
