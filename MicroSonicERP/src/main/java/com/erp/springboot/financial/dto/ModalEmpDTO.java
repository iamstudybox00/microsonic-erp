package com.erp.springboot.financial.dto;

import com.erp.springboot.financial.entity.Employees;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModalEmpDTO {
	private String empId;
	private String empName;
	private String deptName;
	private String deptCode;

	public ModalEmpDTO(Employees entity) {
		empId = entity.getEmpId();
		empName = entity.getEmpName();
		deptName = entity.getDeptCode().getDeptName();
		deptCode = entity.getDeptCode().getDeptCode();
	}
}
