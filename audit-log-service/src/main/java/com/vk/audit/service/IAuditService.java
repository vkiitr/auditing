package com.vk.audit.service;

import java.util.List;

import com.vk.audit.dto.AuditRequest;
import com.vk.audit.entities.AuditRecord;

public interface IAuditService {

	public Long getAuditCount();

	public AuditRecord saveRecord(AuditRequest auditData);

	public List<AuditRecord> fetchAllRecords(Boolean isAdmin, String userName);
}
