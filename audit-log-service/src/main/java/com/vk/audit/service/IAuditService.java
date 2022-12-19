package com.vk.audit.service;

import com.vk.audit.dto.AuditRequest;
import com.vk.audit.dto.AuditResponse;
import com.vk.audit.entities.AuditRecord;

public interface IAuditService {

	public Long getAuditCount();

	public AuditRecord saveRecord(AuditRequest auditData);
	
	public AuditResponse fetchAllRecords(int pageNo, int pageSize, String sortBy, String sortDir, String userName);
}
