package com.erp.springboot.financial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.springboot.financial.entity.Files;

@Repository
public interface FilesRepository extends JpaRepository<Files, Integer> {
	
	List<Files> findByFileCodeIdx(int fileCodeIdx);
}
