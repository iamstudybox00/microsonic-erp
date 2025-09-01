package com.erp.springboot.financial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.erp.springboot.financial.entity.Files;

@Repository
public interface FilesRepository extends JpaRepository<Files, Integer> {

	List<Files> findByFileCodeIdx(int fileCodeIdx);

	@Query("DELETE FROM Files f " + " WHERE f.fileCodeIdx = :fileCodeIdx")
	@Modifying
	void deleteByFileCodeIdx(int fileCodeIdx);

	@Query("SELECT f.sfile FROM Files f WHERE f.fileCodeIdx = :fileCodeIdx")
	List<String> getFileNameByTransactionIdx(int fileCodeIdx);

	@Query("SELECT f.sfile FROM Files f WHERE f.fileIdx IN :deleteFileList")
	List<String> findByFileIdxIn(List<Integer> deleteFileList);
}
