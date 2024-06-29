package com.JPADemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.JPADemo.entities.Students;

@Repository
public interface StudentRepo extends JpaRepository<Students, Long> {
	
	List<Students> findByNameContaining(String name);
	
}
