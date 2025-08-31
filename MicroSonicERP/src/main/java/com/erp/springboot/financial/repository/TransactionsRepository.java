package com.erp.springboot.financial.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.springboot.financial.entity.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {
	List<Transactions> findAllByOrderByTransactionDateDesc();
	
	List<Transactions> findAllBytransactionDateBetween(LocalDateTime start, LocalDateTime end);

	Page<Transactions> findAllByOrderByTransactionDateDesc(Pageable pageable);

	Page<Transactions> findAllBytransactionDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

	Long countBytransactionDateBetween(LocalDateTime start, LocalDateTime end);
}
