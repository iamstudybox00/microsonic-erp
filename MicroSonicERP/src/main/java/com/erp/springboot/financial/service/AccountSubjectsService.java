package com.erp.springboot.financial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.springboot.financial.dto.AccountSubjectDTO;
import com.erp.springboot.financial.entity.AccountSubjects;
import com.erp.springboot.financial.repository.AccountSubjectsRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountSubjectsService {
	@Autowired
	AccountSubjectsRepository accountSubjectsRepository;
	
	public List<AccountSubjectDTO> getModalAccountSubjectList(){
		List<AccountSubjects> entityList = accountSubjectsRepository.findAll();
		
		List<AccountSubjectDTO> list = new ArrayList<>();
		for (AccountSubjects entity : entityList) {
			list.add(new AccountSubjectDTO(entity));
		}
		return list;
	}
	
	public List<AccountSubjectDTO> getModalAccountSubjectList(String searchField, String searchWord){
		List<AccountSubjects> entityList = null;
		switch (searchField) {
		case "accountSubjectName": 
			entityList = accountSubjectsRepository.findByAccountSubjectNameLike("%" + searchWord + "%");
			break;
		}
		
		List<AccountSubjectDTO> list = new ArrayList<>();
		for (AccountSubjects entity : entityList) {
			list.add(new AccountSubjectDTO(entity));
		}
		
		return list;
	}

	public List<AccountSubjectDTO> getAccountSubjectListWithPaging(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<AccountSubjects> entityList = accountSubjectsRepository.findAll(pageable);
		List<AccountSubjectDTO> list = new ArrayList<>();
		for (AccountSubjects entity : entityList) {
			list.add(new AccountSubjectDTO(entity));
		}

		return list;
	}

	public Long count(String searchField, String searchWord) {
		switch (searchField) {
		case "accountSubjectName":
			return accountSubjectsRepository.countByAccountSubjectNameLike("%" + searchWord + "%");
		}
		
		return (long)0;
	}

	public List<AccountSubjectDTO> getAccountSubjectListSearchWithPaging(String searchField, String searchWord, int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<AccountSubjects> entityList = Page.empty();
		
		switch (searchField) {
		case "accountSubjectName":
			entityList = accountSubjectsRepository.findByAccountSubjectNameLike("%" + searchWord + "%", pageable);
			break;
		}
		
		List<AccountSubjectDTO> list = new ArrayList<>();
		for (AccountSubjects entity : entityList) {
			list.add(new AccountSubjectDTO(entity));
		}

		return list;
	}

	public Long count() {
		return accountSubjectsRepository.count();
	}
}
