package com.erp.springboot.financial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.springboot.financial.dto.ModalItemDTO;
import com.erp.springboot.financial.service.ItemsService;

@RestController
@RequestMapping("/items")
public class ItemController {
	@Autowired
	ItemsService itemsService;

	@GetMapping("/items")
	public ResponseEntity<List<ModalItemDTO>> itemList() {
		return ResponseEntity.ok(itemsService.getModalItemList());
	}

	// 페이징된 리스트
	@GetMapping("/page/{page}/{size}")
	public ResponseEntity<List<ModalItemDTO>> itemListWithPaging(@PathVariable int page, @PathVariable int size) {
		return ResponseEntity.ok(itemsService.getItemListWithPaging(page, size));
	}

	@GetMapping("/items/{searchField}/{searchWord}")
	public ResponseEntity<List<ModalItemDTO>> itemListSearch(@PathVariable String searchField,
			@PathVariable String searchWord) {
		List<ModalItemDTO> list = itemsService.getModalItemList(searchField, searchWord);
		return ResponseEntity.ok(list);
	}

	// 검색 결과 개수
	@GetMapping("/count/{searchField}/{searchWord}")
	public Long itemListSearchCount(@PathVariable String searchField, @PathVariable String searchWord) {
		return itemsService.count(searchField, searchWord);
	}

	// 검색 후 페이징된 리스트
	@GetMapping("/{searchField}/{searchWord}/page/{page}/{size}")
	public ResponseEntity<List<ModalItemDTO>> itemListSearchWithPaging(@PathVariable String searchField,
			@PathVariable String searchWord, @PathVariable int page, @PathVariable int size) {
		return ResponseEntity.ok(itemsService.getItemListSearchWithPaging(searchField, searchWord, page, size));
	}

	@GetMapping("/count")
	public Long itemCount() {
		return itemsService.count();
	}
}
