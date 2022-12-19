package com.vk.audit.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vk.audit.entities.AuditRecord;

public interface AuditRepository extends JpaRepository<AuditRecord, Integer> {

	public Page<AuditRecord> findAllByUserNameIgnoreCase(String userName, Pageable pageable);
}