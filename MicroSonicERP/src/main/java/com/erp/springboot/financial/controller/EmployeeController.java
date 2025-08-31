package com.erp.springboot.financial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.springboot.financial.dto.ModalEmpDTO;
import com.erp.springboot.financial.service.EmployeesService;

// 사원 관련
@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	EmployeesService employeesService;

	// 전체 리스트
	@GetMapping()
	public ResponseEntity<List<ModalEmpDTO>> employeeList() {
		return ResponseEntity.ok(employeesService.getModalEmployeeList());
	}

	// 검색 리스트
	@GetMapping("/{searchField}/{searchWord}")
	public ResponseEntity<List<ModalEmpDTO>> employeeListSearch(@PathVariable String searchField,
			@PathVariable String searchWord) {
		List<ModalEmpDTO> list = employeesService.getModalEmployeeList(searchField, searchWord);
		return ResponseEntity.ok(list);
	}

	// 페이징된 리스트
	@GetMapping("/page/{page}/{size}")
	public ResponseEntity<List<ModalEmpDTO>> employeeListWithPaging(@PathVariable int page, @PathVariable int size) {
		return ResponseEntity.ok(employeesService.getEmployeeListWithPaging(page, size));
	}

	// 검색 결과 개수
	@GetMapping("/count/{searchField}/{searchWord}")
	public Long empListSearchCount(@PathVariable String searchField, @PathVariable String searchWord) {
		return employeesService.count(searchField, searchWord);
	}

	// 검색 후 페이징된 리스트
	@GetMapping("/{searchField}/{searchWord}/page/{page}/{size}")
	public ResponseEntity<List<ModalEmpDTO>> employeeListSearchWithPaging(@PathVariable String searchField,
			@PathVariable String searchWord, @PathVariable int page, @PathVariable int size) {
		return ResponseEntity.ok(employeesService.getEmployeeListSearchWithPaging(searchField, searchWord, page, size));
	}

	@GetMapping("/count")
	public Long employeeCount() {
		return employeesService.count();
	}
}
