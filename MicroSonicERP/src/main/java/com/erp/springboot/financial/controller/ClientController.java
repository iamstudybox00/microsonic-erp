package com.erp.springboot.financial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.springboot.financial.dto.ModalClientDTO;
import com.erp.springboot.financial.service.ClientsService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	@Autowired
	ClientsService clientsService;

	@GetMapping()
	public ResponseEntity<List<ModalClientDTO>> clientList() {
		return ResponseEntity.ok(clientsService.getModalClientList());
	}

	@GetMapping("/{searchField}/{searchWord}")
	public ResponseEntity<List<ModalClientDTO>> clientListSearch(@PathVariable String searchField,
			@PathVariable String searchWord) {
		List<ModalClientDTO> list = clientsService.getModalClientList(searchField, searchWord);
		return ResponseEntity.ok(list);
	}

	// 페이징된 리스트
	@GetMapping("/page/{page}/{size}")
	public ResponseEntity<List<ModalClientDTO>> clientListWithPaging(@PathVariable int page, @PathVariable int size) {
		return ResponseEntity.ok(clientsService.getClientListWithPaging(page, size));
	}

	// 검색 결과 개수
	@GetMapping("/count/{searchField}/{searchWord}")
	public Long clientListSearchCount(@PathVariable String searchField, @PathVariable String searchWord) {
		return clientsService.count(searchField, searchWord);
	}

	// 검색 후 페이징된 리스트
	@GetMapping("/{searchField}/{searchWord}/page/{page}/{size}")
	public ResponseEntity<List<ModalClientDTO>> clientListSearchWithPaging(@PathVariable String searchField,
			@PathVariable String searchWord, @PathVariable int page, @PathVariable int size) {
		return ResponseEntity.ok(clientsService.getClientListSearchWithPaging(searchField, searchWord, page, size));
	}

	@GetMapping("/count")
	public Long clientCount() {
		return clientsService.count();
	}
}
