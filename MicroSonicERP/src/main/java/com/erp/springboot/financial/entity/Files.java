package com.erp.springboot.financial.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FILES")
@Getter
@Setter
public class Files {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "file_idx")
	private int fileIdx;
	
	@Column(nullable = false, name = "file_code_idx")
	private int fileCodeIdx;
	
	@Column(length = 255, nullable = false, name = "ofile")
	private String ofile;
	
	@Column(length = 255, nullable = false, name = "sfile")
	private String sfile;
	
}
