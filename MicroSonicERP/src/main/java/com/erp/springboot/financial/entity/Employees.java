package com.erp.springboot.financial.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "EMPLOYEES")
@Getter
public class Employees {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 50, nullable = false, name = "emp_id")
	private String empId;
	
	@Column(length = 100, nullable = false, name = "emp_pw")
	private String empPw;
	
	@Column(length = 50, nullable = false, name = "emp_name")
	private String empName;
	
	@Column(length = 50, nullable = false, name = "emp_phone")
	private String empPhone;
	
	@ManyToOne(fetch = FetchType.LAZY) // 여기 안에 있는 컬럼을 참조할때 select문 실행
	@JoinColumn(name = "dept_code")
	private Departments deptCode;
	
	@Column(length = 100, nullable = false, name = "emp_email")
	private String empEmail;
	
	@Column(length = 50, nullable = false, name = "emp_role")
	private String empRole;
	
	@Column(length = 50, nullable = false, name = "emp_position")
	private String empPosition;
	
	@Column(nullable = false, name = "emp_joindate")
	private Date empJoindate;
	
	@Column(length = 100, nullable = false, name = "emp_addr")
	private String empAddr;
	
	@Column(nullable = false, name = "enable")
	private String enable;
	
	
}
