package com.erp.springboot.financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.springboot.financial.entity.Departments;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, String> {
	
}
