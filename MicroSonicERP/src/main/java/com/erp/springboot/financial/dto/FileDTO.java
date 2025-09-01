package com.erp.springboot.financial.dto;

import com.erp.springboot.financial.entity.Files;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {
	private int fileIdx;
	private int fileCodeIdx;
	private String ofile;
	private String sfile;
	
	public FileDTO(Files entity) {
		fileIdx = entity.getFileIdx();
		fileCodeIdx = entity.getFileCodeIdx();
		ofile = entity.getOfile();
		sfile = entity.getSfile();
	}
}
