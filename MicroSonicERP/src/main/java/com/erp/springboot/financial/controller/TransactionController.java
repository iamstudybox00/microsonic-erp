package com.erp.springboot.financial.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.springboot.financial.dto.TransactionDTO;
import com.erp.springboot.financial.service.TransactionsService;

// 전표 관련
@RestController
@RequestMapping("/transactions")
public class TransactionController {
	@Autowired
	TransactionsService transactionsService;

	// 작성
	@PostMapping()
	public int transactionWrite(@RequestBody TransactionDTO dto) {
		return transactionsService.insertTransaction(dto);
	}

	// 목록
	@GetMapping()
	public ResponseEntity<List<TransactionDTO>> transactionList() {
		return ResponseEntity.ok(transactionsService.getTransactionList());
	}

	// 페이징된 목록
	@GetMapping("/page/{page}/{size}")
	public ResponseEntity<List<TransactionDTO>> transactionListWithPaging(@PathVariable int page,
			@PathVariable int size) {
		System.out.println(page + "   " + size);
		return ResponseEntity.ok(transactionsService.getTransactionListWithPaging(page, size));
	}

	// 기간 내 목록
	@GetMapping("/{start}/{end}")
	public ResponseEntity<List<TransactionDTO>> transactionListWithDate(@PathVariable LocalDate start,
			@PathVariable LocalDate end) {
		return ResponseEntity.ok(transactionsService.getTransactionListWithDate(start, end));
	}

	// 기간 내 목록 페이징
	@GetMapping("/{start}/{end}/page/{page}/{size}")
	public ResponseEntity<List<TransactionDTO>> transactionListWithDateAndPaging(@PathVariable LocalDate start,
			@PathVariable LocalDate end, @PathVariable int page, @PathVariable int size) {
		return ResponseEntity.ok(transactionsService.getTransactionListWithDateAndPaging(start, end, page, size));
	}

	// 자세히 보기
	@GetMapping("/{transactionIdx}")
	public ResponseEntity<TransactionDTO> transactionView(@PathVariable int transactionIdx) {
		return ResponseEntity.ok(transactionsService.getOneTransaction(transactionIdx));
	}

	// 수정
	@PostMapping("/{transactionIdx}")
	public int transactionEdit(@PathVariable int transactionIdx, @RequestBody TransactionDTO dto) {
		return transactionsService.updateTransaction(transactionIdx, dto);
	}

	// 삭제
	@DeleteMapping("/{transactionIdx}")
	public String transactionDelete(@PathVariable int transactionIdx) {
		try {
			transactionsService.deleteTransaction(transactionIdx);
		} catch (Exception e) {
			return "삭제 실패";
		}
		return "삭제 성공";
	}

	// 총 개수
	@GetMapping("/count")
	public Long transactionCount() {
		return transactionsService.count();
	}

	// 기간내 포함된 개수
	@GetMapping("count/{start}/{end}")
	public Long transactionCountWithDate(@PathVariable LocalDate start,
			@PathVariable LocalDate end) {
		return transactionsService.countWithDate(start, end);
	}
}
