package com.erp.springboot.financial.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.erp.springboot.financial.dto.FileDTO;
import com.erp.springboot.financial.service.FilesService;
import com.erp.springboot.financial.utils.FileUtil;

@RestController
@RequestMapping("/files")
public class FileController {
	
	@Autowired
	FilesService filesService;
	
	@GetMapping("/{fileCodeIdx}")
	public ResponseEntity<List<FileDTO>> fileList(@PathVariable int fileCodeIdx) {
		return ResponseEntity.ok(filesService.getFileListWithFileCode(fileCodeIdx));
	}
	
	// 실제 파일 업로드 관련
	@PostMapping()
	public int filesWrite(@RequestParam List<MultipartFile> fileList, @RequestParam List<String> sfileList) {
		// 뭔가 오류가나서 저장할 이름과 실제 파일들의 개수가 맞지 않을 경우
		if (fileList.size() != sfileList.size()) {
			return -1;
		}
		
		try {
			FileUtil.uploadFiles(fileList, sfileList);
		} catch (Exception e) {
			return -1;
		}
		return 1;

	}

	@DeleteMapping()
	public int filesDelete(@RequestParam List<MultipartFile> fileList, @RequestParam List<String> sfileList) {
		try {
			FileUtil.deleteFiles(sfileList);
		} catch (FileNotFoundException e) {
			return -1;
		}
		
		return 1;
	}
}
