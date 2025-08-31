package com.erp.springboot.financial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.springboot.financial.dto.ModalEmpDTO;
import com.erp.springboot.financial.entity.Employees;
import com.erp.springboot.financial.repository.EmployeesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeesService {
	@Autowired
	EmployeesRepository employeesRepository;

	// entity로 가져와 dto로 변환
	public List<ModalEmpDTO> getModalEmployeeList() {
		List<Employees> entityList = employeesRepository.findAll();
		List<ModalEmpDTO> list = new ArrayList<>();
		for (Employees employees : entityList) {
			list.add(new ModalEmpDTO(employees));
		}
		return list;
	}

	public List<ModalEmpDTO> getModalEmployeeList(String searchField, String searchWord) {
		List<Employees> entityList = null;
		switch (searchField) {
		case "empId":
			entityList = employeesRepository.findByEmpIdLike("%" + searchWord + "%");
			break;
		case "empName":
			entityList = employeesRepository.findByEmpNameLike("%" + searchWord + "%");
			break;
		}
		List<ModalEmpDTO> list = new ArrayList<>();
		for (Employees employees : entityList) {
			list.add(new ModalEmpDTO(employees));
		}

		return list;
	}

	public Long count() {
		return employeesRepository.count();
	}
	
	public Long count(String searchField, String searchWord) {
		switch (searchField) {
		case "empId":
			return employeesRepository.countByEmpIdLike("%" + searchWord + "%");
		case "empName":
			return employeesRepository.countByEmpNameLike("%" + searchWord + "%");
		}
		
		return (long)0;
	}

	public List<ModalEmpDTO> getEmployeeListWithPaging(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Employees> entityList = employeesRepository.findAll(pageable);
		List<ModalEmpDTO> list = new ArrayList<>();
		for (Employees entity : entityList) {
			list.add(new ModalEmpDTO(entity));
		}

		return list;
	}

	public List<ModalEmpDTO> getEmployeeListSearchWithPaging(String searchField, String searchWord, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Employees> entityList = Page.empty();
		
		switch (searchField) {
		case "empId":
			entityList = employeesRepository.findByEmpIdLike("%" + searchWord + "%", pageable);
			break;
		case "empName":
			entityList = employeesRepository.findByEmpNameLike("%" + searchWord + "%", pageable);
			break;
		}
		
		List<ModalEmpDTO> list = new ArrayList<>();
		for (Employees entity : entityList) {
			list.add(new ModalEmpDTO(entity));
		}

		return list;
	}
}
