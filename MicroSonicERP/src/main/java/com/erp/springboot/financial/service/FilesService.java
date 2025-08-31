package com.erp.springboot.financial.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.erp.springboot.financial.dto.FileDTO;
import com.erp.springboot.financial.dto.ModalClientDTO;
import com.erp.springboot.financial.entity.Clients;
import com.erp.springboot.financial.entity.Files;
import com.erp.springboot.financial.repository.FilesRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class FilesService {
	@Autowired
	FilesRepository filesRepository;

	// 전체 select
	public List<FileDTO> getFileList() {
		List<Files> entityList = filesRepository.findAll();
		List<FileDTO> list = new ArrayList<>();
		for (Files entity : entityList) {
			list.add(new FileDTO(entity));
		}

		return list;
	}

	// 특정 전표의 파일 select
	public List<FileDTO> getFileListWithFileCode(int fileCodeIdx) {
		List<Files> entityList = filesRepository.findByFileCodeIdx(fileCodeIdx);
		List<FileDTO> list = new ArrayList<>();
		for (Files entity : entityList) {
			list.add(new FileDTO(entity));
		}

		return list;
	}

//	public List<ModalClientDTO> getModalClientList(String searchField, String searchWord) {
//		List<Clients> entityList = new ArrayList<>();
//		switch (searchField) {
//		case "companyName":
//			entityList = clientRepository.findByCompanyNameLike("%" + searchWord + "%");
//			break;
//		case "clientPicName":
//			entityList = clientRepository.findByClientPicNameLike("%" + searchWord + "%");
//			break;
//		}
//		List<ModalClientDTO> list = new ArrayList<>();
//		for (Clients entity : entityList) {
//			list.add(new ModalClientDTO(entity));
//		}
//
//		return list;
//	}
//
//	public List<ModalClientDTO> getClientListWithPaging(int page, int size) {
//		Pageable pageable = PageRequest.of(page - 1, size);
//		Page<Clients> entityList = clientRepository.findAll(pageable);
//		List<ModalClientDTO> list = new ArrayList<>();
//		for (Clients entity : entityList) {
//			list.add(new ModalClientDTO(entity));
//		}
//
//		return list;
//	}
//
//	public Long count() {
//		return clientRepository.count();
//	}
//
//	public Long count(String searchField, String searchWord) {
//		switch (searchField) {
//		case "companyName":
//			return clientRepository.countByCompanyNameLike("%" + searchWord + "%");
//		case "clientPicName":
//			return clientRepository.countByClientPicNameLike("%" + searchWord + "%");
//		}
//
//		return (long) 0;
//	}
//
//	public List<ModalClientDTO> getClientListSearchWithPaging(String searchField, String searchWord, int page,
//			int size) {
//		Pageable pageable = PageRequest.of(page - 1, size);
//		Page<Clients> entityList = Page.empty();
//
//		switch (searchField) {
//		case "companyName":
//			entityList = clientRepository.findByCompanyNameLike("%" + searchWord + "%", pageable);
//			break;
//		case "clientPicName":
//			entityList = clientRepository.findByClientPicNameLike("%" + searchWord + "%", pageable);
//			break;
//		}
//
//		List<ModalClientDTO> list = new ArrayList<>();
//		for (Clients entity : entityList) {
//			list.add(new ModalClientDTO(entity));
//		}
//
//		return list;
//	}
}
