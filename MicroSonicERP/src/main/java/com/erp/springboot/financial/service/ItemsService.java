package com.erp.springboot.financial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.springboot.financial.dto.ModalItemDTO;
import com.erp.springboot.financial.entity.Items;
import com.erp.springboot.financial.repository.ItemsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemsService {
	@Autowired
	ItemsRepository itemsRepository;
	
	public List<ModalItemDTO> getModalItemList(){
		List<Items> entityList = itemsRepository.findAll();
		
		List<ModalItemDTO> list = new ArrayList<>();
		for (Items entity : entityList) {
			list.add(new ModalItemDTO(entity));
		}
		return list;
	}
	
	public List<ModalItemDTO> getModalItemList(String searchField, String searchWord){
		List<Items> entityList = null;
		switch (searchField) {
		case "itemName": 
			entityList = itemsRepository.findByItemNameLike("%" + searchWord + "%");
			break;
		case "itemCode":
			entityList = itemsRepository.findByItemCodeLike("%" + searchWord + "%");
			break;
		}
		
		List<ModalItemDTO> list = new ArrayList<>();
		for (Items entity : entityList) {
			list.add(new ModalItemDTO(entity));
		}
		
		return list;
	}

	public List<ModalItemDTO> getItemListWithPaging(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Items> entityList = itemsRepository.findAll(pageable);
		List<ModalItemDTO> list = new ArrayList<>();
		for (Items entity : entityList) {
			list.add(new ModalItemDTO(entity));
		}

		return list;
	}

	public Long count(String searchField, String searchWord) {
		switch (searchField) {
		case "itemName":
			return itemsRepository.countByItemNameLike("%" + searchWord + "%");
		case "itemCode":
			return itemsRepository.countByItemCodeLike("%" + searchWord + "%");
		}
		
		return (long)0;
	}

	public List<ModalItemDTO> getItemListSearchWithPaging(String searchField, String searchWord, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Items> entityList = Page.empty();
		
		switch (searchField) {
		case "itemName":
			entityList = itemsRepository.findByItemNameLike("%" + searchWord + "%", pageable);
			break;
		case "itemCode":
			entityList = itemsRepository.findByItemCodeLike("%" + searchWord + "%", pageable);
			break;
		}
		
		List<ModalItemDTO> list = new ArrayList<>();
		for (Items entity : entityList) {
			list.add(new ModalItemDTO(entity));
		}

		return list;
	}

	public Long count() {
		return itemsRepository.count();
	}
}