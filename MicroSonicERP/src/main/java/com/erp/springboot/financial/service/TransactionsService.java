package com.erp.springboot.financial.service;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.springboot.financial.dto.AccountingRecordDTO;
import com.erp.springboot.financial.dto.FileDTO;
import com.erp.springboot.financial.dto.TransactionDTO;
import com.erp.springboot.financial.dto.TransactionItemDTO;
import com.erp.springboot.financial.entity.AccountingRecords;
import com.erp.springboot.financial.entity.Files;
import com.erp.springboot.financial.entity.TransactionItems;
import com.erp.springboot.financial.entity.Transactions;
import com.erp.springboot.financial.repository.AccountSubjectsRepository;
import com.erp.springboot.financial.repository.AccountingRecordsRepository;
import com.erp.springboot.financial.repository.ClientsRepository;
import com.erp.springboot.financial.repository.EmployeesRepository;
import com.erp.springboot.financial.repository.FilesRepository;
import com.erp.springboot.financial.repository.ItemsRepository;
import com.erp.springboot.financial.repository.TransactionItemsRepository;
import com.erp.springboot.financial.repository.TransactionsRepository;
import com.erp.springboot.financial.utils.FileUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionsService {

	@Autowired
	TransactionsRepository transactionsRepository;

	@Autowired
	ClientsRepository clientsRepository;

	@Autowired
	EmployeesRepository employeesRepository;

	@Autowired
	ItemsRepository itemsRepository;

	@Autowired
	TransactionItemsRepository transactionItemsRepository;

	@Autowired
	AccountingRecordsRepository accoutAccountingRecordsRepository;

	@Autowired
	AccountSubjectsRepository accountSubjectsRepository;

	@Autowired
	FilesRepository filesRepository;

	public int insertTransaction(TransactionDTO dto) {
		Transactions transaction = new Transactions();
		transaction.setTransactionDate(dto.getTransactionDate());
		// 실제 값이 사용되지 않으니 getReferenceById로 가져오기 findById는 사용시 sql문이 실행되는것 같고
		// getReferenceById는 실제로 get...을 사용할때 sql문이 실행되는 듯하다
		transaction.setClientCode(clientsRepository.getReferenceById(dto.getClientCode()));
		transaction.setProofCategory(dto.getProofCategory());
		transaction.setEmpId(employeesRepository.getReferenceById(dto.getEmpId()));
		transaction.setTransactionDivision(dto.getTransactionDivision());
		transaction.setTotalAmount(dto.getTotalAmount());

		transactionsRepository.save(transaction); // insert 성공
		transaction.setFileCodeIdx(transaction.getTransactionIdx());
		transactionsRepository.save(transaction);
		// 전표에 포함된 제품 등록
		for (TransactionItemDTO itemDTO : dto.getItemList()) {
			// dto로 받아온 값들을 entity형태로
			TransactionItems item = new TransactionItems();
			item.setTransactionIdx(transaction);
			item.setItemCode(itemsRepository.getReferenceById(itemDTO.getItemCode()));
			item.setAmount(itemDTO.getAmount());
			item.setTotalPrice(itemDTO.getTotalPrice());
			transactionItemsRepository.save(item);
		}

		// 차/대변 등록
		for (AccountingRecordDTO recordDto : dto.getRecordList()) {
			// dto로 받아온 값들을 entity형태로
			AccountingRecords record = new AccountingRecords();
			record.setTransactionIdx(transaction);
			record.setAccountingRecordCategory(recordDto.getAccountingRecordCategory());
			record.setAccountSubjectCode(accountSubjectsRepository.getReferenceById(recordDto.getAccountSubjectCode()));
			record.setAccountingRecordAmount(recordDto.getAccountingRecordAmount());
			record.setSummary(recordDto.getSummary());
			accoutAccountingRecordsRepository.save(record);
		}

		// 파일 등록
		for (FileDTO fileDto : dto.getFileList()) {
			Files file = new Files();
			file.setFileCodeIdx(transaction.getFileCodeIdx());
			file.setOfile(fileDto.getOfile());
			file.setSfile(fileDto.getSfile());
			filesRepository.save(file);
		}
		return 1;
	}

	public int updateTransaction(int transactionIdx, TransactionDTO dto) {
		Transactions transaction = transactionsRepository.findById(transactionIdx).get();
		transaction.setTransactionDate(dto.getTransactionDate());
		transaction.setClientCode(clientsRepository.getReferenceById(dto.getClientCode()));
		transaction.setProofCategory(dto.getProofCategory());
		transaction.setEmpId(employeesRepository.getReferenceById(dto.getEmpId()));
		transaction.setTransactionDivision(dto.getTransactionDivision());
		transaction.setTotalAmount(dto.getTotalAmount());

		transactionsRepository.save(transaction);
		// 수정후 삭제된 제품 삭제
		for (int deleteItemIdx : dto.getDeleteItemList()) {
			transactionItemsRepository.deleteById(deleteItemIdx);
		}
		// 전표에 포함된 제품 등록
		for (TransactionItemDTO itemDTO : dto.getItemList()) {
			// dto로 받아온 값들을 entity형태로
			TransactionItems item = new TransactionItems();
			// 기존에 있었던 항목이라면 insert가 아닌 수정형태로
			if (itemDTO.getTransactionItemIdx() != -1) {
				item.setTransactionItemIdx(itemDTO.getTransactionItemIdx());
			}
			item.setTransactionIdx(transaction);
			item.setItemCode(itemsRepository.getReferenceById(itemDTO.getItemCode()));
			item.setAmount(itemDTO.getAmount());
			item.setTotalPrice(itemDTO.getTotalPrice());
			transactionItemsRepository.save(item);
		}

		// 수정후 삭제된 차/대변 삭제
		for (int deleteRecordIdx : dto.getDeleteRecordList()) {
			accoutAccountingRecordsRepository.deleteById(deleteRecordIdx);
		}
		// 차/대변 등록
		for (AccountingRecordDTO recordDto : dto.getRecordList()) {
			// dto로 받아온 값들을 entity형태로
			AccountingRecords record = new AccountingRecords();
			// 기존에 있었던 항목이라면 insert가 아닌 수정형태로
			if (recordDto.getAccountingRecordIdx() != -1) {
				record.setAccountingRecordIdx(recordDto.getAccountingRecordIdx());
			}
			record.setTransactionIdx(transaction);
			record.setAccountingRecordCategory(recordDto.getAccountingRecordCategory());
			record.setAccountSubjectCode(accountSubjectsRepository.getReferenceById(recordDto.getAccountSubjectCode()));
			record.setAccountingRecordAmount(recordDto.getAccountingRecordAmount());
			record.setSummary(recordDto.getSummary());
			accoutAccountingRecordsRepository.save(record);
		}

		// 수정후 삭제된 첨부파일

		List<String> deleteFile = filesRepository.findByFileIdxIn(dto.getDeleteFileList());
		// 실제 저장된 파일 삭제
		try {
			FileUtil.deleteFiles(deleteFile);
		} catch (FileNotFoundException e) {
			return -1;
		}
		// 첨부파일 등록
		for (FileDTO fileDto : dto.getFileList()) {
			// dto로 받아온 값들을 entity형태로
			Files file = new Files();
			file.setFileCodeIdx(transaction.getFileCodeIdx());
			file.setOfile(fileDto.getOfile());
			file.setSfile(fileDto.getSfile());
			filesRepository.save(file);
		}

		// db상 파일 삭제
		for (int deleteFileIdx : dto.getDeleteFileList()) {
			filesRepository.deleteById(deleteFileIdx);
		}
		return 1;
	}

	public List<TransactionDTO> getTransactionList() {
		List<Transactions> entityList = transactionsRepository.findAllByOrderByTransactionDateDesc();

		List<TransactionDTO> list = new ArrayList<>();
		for (Transactions entity : entityList) {
			list.add(new TransactionDTO("List", entity));
		}

		return list;
	}

	public List<TransactionDTO> getTransactionListWithPaging(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		Page<Transactions> entityList = transactionsRepository.findAllByOrderByTransactionDateDesc(pageable);
		List<TransactionDTO> list = new ArrayList<>();
		for (Transactions entity : entityList) {
			list.add(new TransactionDTO("List", entity));
		}

		return list;
	}

	public List<TransactionDTO> getTransactionListWithDate(LocalDate start, LocalDate end) {
		// localDate를 localDatetime으로 변환
		LocalDateTime startT = start.atStartOfDay();
		LocalDateTime endT = end.atTime(LocalTime.MAX);
		List<Transactions> entityList = transactionsRepository.findAllBytransactionDateBetween(startT, endT);

		List<TransactionDTO> list = new ArrayList<>();
		for (Transactions entity : entityList) {
			list.add(new TransactionDTO("List", entity));
		}

		return list;
	}

	public List<TransactionDTO> getTransactionListWithPagingAndDate(LocalDate start, LocalDate end, int page,
			int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		// localDate를 localDatetime으로 변환
		LocalDateTime startT = start.atStartOfDay();
		LocalDateTime endT = end.atTime(LocalTime.MAX);
		Page<Transactions> entityList = transactionsRepository.findAllBytransactionDateBetween(startT, endT, pageable);

		List<TransactionDTO> list = new ArrayList<>();
		for (Transactions entity : entityList) {
			list.add(new TransactionDTO("List", entity));
		}

		return list;
	}

	public TransactionDTO getOneTransaction(int transactionIdx) {
		Transactions entity = transactionsRepository.findById(transactionIdx).get();
		TransactionDTO dto = new TransactionDTO("View", entity);

		// 제품
		List<TransactionItems> itemEntity = transactionItemsRepository
				.findByTransactionIdxOrderByTransactionItemIdxAsc(transactionIdx);
		List<TransactionItemDTO> itemList = new ArrayList<>();
		for (TransactionItems item : itemEntity) {
			itemList.add(new TransactionItemDTO(item));
		}
		dto.setItemList(itemList);

		// 차/대변
		List<AccountingRecords> recordEntity = accoutAccountingRecordsRepository
				.findByTransactionIdxOrderByAccountingRecordIdxAsc(transactionIdx);
		List<AccountingRecordDTO> recordList = new ArrayList<>();
		for (AccountingRecords record : recordEntity) {
			recordList.add(new AccountingRecordDTO(record));
		}
		dto.setRecordList(recordList);

		// 첨부파일
		List<Files> fileEntity = filesRepository.findByFileCodeIdx(transactionIdx);
		List<FileDTO> fileList = new ArrayList<>();
		for (Files file : fileEntity) {
			fileList.add(new FileDTO(file));
		}
		dto.setFileList(fileList);

		return dto;
	}

	public void deleteTransaction(int transactionIdx) throws FileNotFoundException {
		transactionsRepository.deleteById(transactionIdx);
		transactionItemsRepository.deleteByTransactionIdx(transactionIdx);
		accoutAccountingRecordsRepository.deleteByTransactionIdx(transactionIdx);
		List<String> deleteFileName = filesRepository.getFileNameByTransactionIdx(transactionIdx);
		System.out.println(deleteFileName);
		filesRepository.deleteByFileCodeIdx(transactionIdx);
		FileUtil.deleteFiles(deleteFileName);
	}

	public Long count() {
		return transactionsRepository.count();
	}

	public Long countWithDate(LocalDate start, LocalDate end) {
		LocalDateTime startT = start.atStartOfDay();
		LocalDateTime endT = end.atTime(LocalTime.MAX);
		return transactionsRepository.countBytransactionDateBetween(startT, endT);
	}

	public List<TransactionDTO> getTransactionListWithDateAndPaging(LocalDate start, LocalDate end, int page,
			int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		LocalDateTime startT = start.atStartOfDay();
		LocalDateTime endT = end.atTime(LocalTime.MAX);
		Page<Transactions> entityList = transactionsRepository.findAllBytransactionDateBetween(startT, endT, pageable);
		List<TransactionDTO> list = new ArrayList<>();
		for (Transactions entity : entityList) {
			list.add(new TransactionDTO("List", entity));
		}

		return list;
	}
}
