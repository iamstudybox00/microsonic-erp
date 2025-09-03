package com.erp.springboot.financial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.springboot.financial.dto.FileDTO;
import com.erp.springboot.financial.entity.Files;
import com.erp.springboot.financial.repository.FilesRepository;

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

	public FileDTO getOneFileResource(int fileidx) {
		Files entity = filesRepository.findById(fileidx).get();
		FileDTO dto = new FileDTO(entity);
		
		return dto;
	}
}
