package com.erp.springboot.financial.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "DEPARTMENTS")
@Getter
public class Departments {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 100, nullable = false, name = "dept_code")
	private String deptCode;
	
	@Column(length = 100, nullable = false, name = "dept_name")
	private String deptName;
}
