package com.erp.springboot.financial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.erp.springboot.financial.entity.AccountingRecords;

@Repository
public interface AccountingRecordsRepository extends JpaRepository<AccountingRecords, Integer> {

	@Query("SELECT ar FROM AccountingRecords ar "
			+ " WHERE ar.transactionIdx.transactionIdx = :transactionIdx")
	List<AccountingRecords> findByTransactionIdxOrderByAccountingRecordIdxAsc(int transactionIdx);

	@Query("DELETE FROM AccountingRecords ar "
			+ " WHERE ar.transactionIdx.transactionIdx = :transactionIdx")
	@Modifying
	void deleteByTransactionIdx(int transactionIdx);
}
