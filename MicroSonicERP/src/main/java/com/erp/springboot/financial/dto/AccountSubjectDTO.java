package com.erp.springboot.financial.dto;

import com.erp.springboot.financial.entity.AccountSubjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountSubjectDTO {
	private String accountSubjectCode;
	private String accountSubjectName;
	
	public AccountSubjectDTO(AccountSubjects entity) {
		accountSubjectCode = entity.getAccountSubjectCode();
		accountSubjectName = entity.getAccountSubjectName();
	}
}
