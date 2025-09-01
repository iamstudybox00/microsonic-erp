package com.erp.springboot.financial.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.erp.springboot.financial.entity.Transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
	private int transactionIdx;
	private LocalDateTime transactionDate;
	private String clientCode;
	private String companyName;
	private String proofCategory;
	private String empId;
	private String empName;
	private int fileCodeIdx;
	private String transactionDivision;
	private String deptName;
	private int totalAmount;
	
	private List<TransactionItemDTO> itemList;
	private List<AccountingRecordDTO> recordList;
	private List<FileDTO> fileList;
	
	// 등록되어 있지만 수정후 삭제될 transactionItemIdx
	private List<Integer> deleteItemList;
	// 등록되어 있지만 수정후 삭제될 transactionRecordIdx
	private List<Integer> deleteRecordList;
	// 등록되어 있지만 수정후 삭제될 fileIdx
	private List<Integer> deleteFileList;
	
	
	public TransactionDTO(String type, Transactions entity) {
		switch (type) {
		case "List":
			InitForList(entity);
			break;
		case "View":
			InitForView(entity);
			break;
		}
	}
	
	// List에서 보여줄때 필요한 데이터만 초기화
	private void InitForList(Transactions entity) {
		transactionIdx = entity.getTransactionIdx();
		transactionDate = entity.getTransactionDate();
		proofCategory = entity.getProofCategory();
		empName = entity.getEmpId().getEmpName();
		transactionDivision = entity.getTransactionDivision();
		totalAmount = entity.getTotalAmount();
	}
	
	// View에서 보여줄때 필요한 데이터만 초기화
	private void InitForView(Transactions entity) {
		transactionIdx = entity.getTransactionIdx();
		transactionDate = entity.getTransactionDate();
		clientCode = entity.getClientCode().getClientCode();
		companyName = entity.getClientCode().getCompanyName();
		proofCategory = entity.getProofCategory();
		empId = entity.getEmpId().getEmpId();
		empName = entity.getEmpId().getEmpName();
		fileCodeIdx = entity.getFileCodeIdx();
		transactionDivision = entity.getTransactionDivision();
		deptName = entity.getEmpId().getDeptCode().getDeptName();
	}
}
