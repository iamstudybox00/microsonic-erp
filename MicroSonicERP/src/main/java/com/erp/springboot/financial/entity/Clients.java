package com.erp.springboot.financial.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "CLIENTS")
@Getter
public class Clients {
	@Id
	@Column(length = 100, nullable = false, name = "client_code")
	private String clientCode;
	
	@Column(length = 20, nullable = false, name = "client_code_category")
	private String clientCodeCategory;
	
	@Column(length = 255, nullable = false, name = "business_number")
	private String businessNumber;
	
	@Column(length = 100, nullable = false, name = "company_name")
	private String companyName;
	
	@Column(length = 100, nullable = false, name = "ceo_name")
	private String ceoName;
	
	@Column(length = 100, nullable = false, name = "industry")
	private String industry;
	
	@Column(length = 100, nullable = false, name = "sales_pic_name")
	private String salesPicName;
	
	@Column(length = 100, nullable = false, name = "client_pic_name")
	private String clientPicName;
	
	@Column(length = 20, nullable = false, name = "client_contact")
	private String clientContact;
	
	@Column(length = 100, nullable = false, name = "client_address")
	private String clientAddress;
	
	@Column(length = 30, nullable = false, name = "client_account_number")
	private String clientAccountNumber;
	
	@Column(length = 255, nullable = false, name = "client_email")
	private String clientEmail;
	
	@Column(nullable = false, name = "registration_date")
	private LocalDateTime registrationDate;
}
