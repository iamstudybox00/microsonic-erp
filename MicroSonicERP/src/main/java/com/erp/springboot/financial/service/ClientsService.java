package com.erp.springboot.financial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.springboot.financial.dto.ModalClientDTO;
import com.erp.springboot.financial.entity.Clients;
import com.erp.springboot.financial.repository.ClientsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClientsService {
	
	@Autowired
	ClientsRepository clientRepository;

	public List<ModalClientDTO> getModalClientList(){
		List<Clients> entityList = clientRepository.findAll();
		List<ModalClientDTO> list = new ArrayList<>();
		for (Clients entity : entityList) {
			list.add(new ModalClientDTO(entity));
		}
		
		return list;
	}

	public List<ModalClientDTO> getModalClientList(String searchField, String searchWord) {
		List<Clients> entityList = new ArrayList<>();
		switch (searchField) {
		case "companyName": 
			entityList = clientRepository.findByCompanyNameLike("%" + searchWord + "%");
			break;
		case "clientPicName":
			entityList = clientRepository.findByClientPicNameLike("%" + searchWord + "%");
			break;
		}
		List<ModalClientDTO> list = new ArrayList<>();
		for (Clients entity : entityList) {
			list.add(new ModalClientDTO(entity));
		}
		
		return list;
	}

	public List<ModalClientDTO> getClientListWithPaging(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Clients> entityList = clientRepository.findAll(pageable);
		List<ModalClientDTO> list = new ArrayList<>();
		for (Clients entity : entityList) {
			list.add(new ModalClientDTO(entity));
		}

		return list;
	}
	
	public Long count() {
		return clientRepository.count();
	}
	
	public Long count(String searchField, String searchWord) {
		switch (searchField) {
		case "companyName":
			return clientRepository.countByCompanyNameLike("%" + searchWord + "%");
		case "clientPicName":
			return clientRepository.countByClientPicNameLike("%" + searchWord + "%");
		}
		
		return (long)0;
	}

	public List<ModalClientDTO> getClientListSearchWithPaging(String searchField, String searchWord, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Clients> entityList = Page.empty();
		
		switch (searchField) {
		case "companyName":
			entityList = clientRepository.findByCompanyNameLike("%" + searchWord + "%", pageable);
			break;
		case "clientPicName":
			entityList = clientRepository.findByClientPicNameLike("%" + searchWord + "%", pageable);
			break;
		}
		
		List<ModalClientDTO> list = new ArrayList<>();
		for (Clients entity : entityList) {
			list.add(new ModalClientDTO(entity));
		}

		return list;
	}
}
