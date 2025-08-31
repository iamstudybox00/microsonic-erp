package com.erp.springboot.financial.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.erp.springboot.financial.entity.Employees;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, String>{
	@Query("SELECT e FROM Employees e JOIN FETCH e.deptCode")
	List<Employees> findAll();
	
	@Query("SELECT e FROM Employees e JOIN FETCH e.deptCode WHERE empId LIKE :empId")
	List<Employees> findByEmpIdLike(String empId);
	
	@Query("SELECT e FROM Employees e JOIN FETCH e.deptCode WHERE empName LIKE :empName")
	List<Employees> findByEmpNameLike(String empName);
	
	@Query(value = "SELECT e FROM Employees e JOIN FETCH e.deptCode WHERE empId LIKE :empId",
			countQuery = "SELECT COUNT(e) FROM Employees e WHERE e.empId LIKE :empId")
	Page<Employees> findByEmpIdLike(String empId, Pageable pageable);
	
	@Query(value = "SELECT e FROM Employees e JOIN FETCH e.deptCode WHERE empName LIKE :empName",
			countQuery = "SELECT COUNT(e) FROM Employees e WHERE e.empName LIKE :empName")
	Page<Employees> findByEmpNameLike(String empName, Pageable pageable);

	Long countByEmpIdLike(String empId);

	Long countByEmpNameLike(String empName);
}