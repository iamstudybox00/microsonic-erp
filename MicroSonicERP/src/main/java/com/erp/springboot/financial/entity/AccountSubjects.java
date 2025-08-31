package com.erp.springboot.financial.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "ACCOUNT_SUBJECTS")
@Getter
public class AccountSubjects {
	@Id
	@Column(length = 100, nullable = false, name = "account_subject_code")
	private String accountSubjectCode;
	
	@Column(length = 255, nullable = false, name = "account_subject_name")
	private String accountSubjectName;
}
