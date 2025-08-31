package com.erp.springboot.financial.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.erp.springboot.financial.entity.Items;

@Repository
public interface ItemsRepository extends JpaRepository<Items, String>{
	@Query("SELECT i FROM Items i WHERE i.itemCode LIKE :itemCode ")
	List<Items> findByItemCodeLike(String itemCode);
	
	@Query("SELECT i FROM Items i WHERE i.itemName LIKE :itemName ")
	List<Items> findByItemNameLike(String itemName);

	Long countByItemNameLike(String itemName);

	Long countByItemCodeLike(String itemCode);

	Page<Items> findByItemNameLike(String itemName, Pageable pageable);

	Page<Items> findByItemCodeLike(String itemCode, Pageable pageable);
}
