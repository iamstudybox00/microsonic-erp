package com.erp.springboot.financial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.springboot.financial.dto.AccountSubjectDTO;
import com.erp.springboot.financial.service.AccountSubjectsService;

@RestController
@RequestMapping("/accountSubject")
public class AccountSubjectController {
	@Autowired
	AccountSubjectsService accountSubjectsService;

	@GetMapping()
	public ResponseEntity<List<AccountSubjectDTO>> accountSubjectList() {
		return ResponseEntity.ok(accountSubjectsService.getModalAccountSubjectList());
	}

	@GetMapping("/{searchField}/{searchWord}")
	public ResponseEntity<List<AccountSubjectDTO>> accountSubjectListSearch(@PathVariable String searchField,
			@PathVariable String searchWord) {
		List<AccountSubjectDTO> list = accountSubjectsService.getModalAccountSubjectList(searchField, searchWord);
		return ResponseEntity.ok(list);
	}

	// 페이징된 리스트
	@GetMapping("/page/{page}/{size}")
	public ResponseEntity<List<AccountSubjectDTO>> accountSubjectListWithPaging(@PathVariable int page, @PathVariable int size) {
		return ResponseEntity.ok(accountSubjectsService.getAccountSubjectListWithPaging(page, size));
	}

	// 검색 결과 개수
	@GetMapping("/count/{searchField}/{searchWord}")
	public Long accountSubjectListSearchCount(@PathVariable String searchField, @PathVariable String searchWord) {
		return accountSubjectsService.count(searchField, searchWord);
	}

	// 검색 후 페이징된 리스트
	@GetMapping("/{searchField}/{searchWord}/page/{page}/{size}")
	public ResponseEntity<List<AccountSubjectDTO>> accountSubjectListSearchWithPaging(@PathVariable String searchField,
			@PathVariable String searchWord, @PathVariable int page, @PathVariable int size) {
		return ResponseEntity.ok(accountSubjectsService.getAccountSubjectListSearchWithPaging(searchField, searchWord, page, size));
	}

	@GetMapping("/count")
	public Long accountSubjectCount() {
		return accountSubjectsService.count();
	}
}
