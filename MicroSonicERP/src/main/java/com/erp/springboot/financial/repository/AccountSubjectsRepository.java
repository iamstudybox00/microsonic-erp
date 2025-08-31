package com.erp.springboot.financial.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.erp.springboot.financial.entity.AccountSubjects;

@Repository
public interface AccountSubjectsRepository extends JpaRepository<AccountSubjects, String> {
	@Query("SELECT as FROM AccountSubjects as WHERE accountSubjectName LIKE :accountSubjectName")
	List<AccountSubjects> findByAccountSubjectNameLike(String accountSubjectName);

	Long countByAccountSubjectNameLike(String accountSubjectName);

	Page<AccountSubjects> findByAccountSubjectNameLike(String accountSubjectName, Pageable pageable);
}
