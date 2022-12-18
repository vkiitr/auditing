package com.vk.audit.dao;

import org.springframework.data.repository.CrudRepository;

import com.vk.audit.entities.AuditRecord;

public interface AuditRepository extends CrudRepository<AuditRecord, Integer> {

}