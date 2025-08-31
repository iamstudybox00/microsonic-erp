package com.erp.springboot.financial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.erp.springboot.financial.entity.TransactionItems;

@Repository
public interface TransactionItemsRepository extends JpaRepository<TransactionItems, Integer> {
	
	@Query("SELECT ti FROM TransactionItems ti JOIN FETCH ti.itemCode i "
			+ " WHERE ti.transactionIdx.transactionIdx = :transactionIdx")
	List<TransactionItems> findByTransactionIdxOrderByTransactionItemIdxAsc(int transactionIdx);
	
	@Query("DELETE FROM TransactionItems ti "
			+ " WHERE ti.transactionIdx.transactionIdx = :transactionIdx")
	@Modifying
	void deleteByTransactionIdx(int transactionIdx);
}