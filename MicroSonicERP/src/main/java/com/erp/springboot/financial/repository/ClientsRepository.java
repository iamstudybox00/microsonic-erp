package com.erp.springboot.financial.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.springboot.financial.entity.Clients;

public interface ClientsRepository extends JpaRepository<Clients, String> {
	@Query("SELECT c FROM Clients c WHERE companyName LIKE :companyName")
	List<Clients> findByCompanyNameLike(String companyName);
	
	@Query("SELECT c FROM Clients c WHERE clientPicName LIKE :clientPicName")
	List<Clients> findByClientPicNameLike(String clientPicName);

	Long countByCompanyNameLike(String companyName);

	Long countByClientPicNameLike(String clientPicName);

	Page<Clients> findByCompanyNameLike(String companyName, Pageable pageable);

	Page<Clients> findByClientPicNameLike(String clientPicName, Pageable pageable);
}
