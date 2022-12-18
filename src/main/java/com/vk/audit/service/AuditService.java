package com.vk.audit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.audit.dao.AuditRepository;
import com.vk.audit.dto.AuditRequest;
import com.vk.audit.entities.AuditRecord;

@Service
public class AuditService implements IAuditService {

	@Autowired
	private AuditRepository auditRepository;

	public Long getAuditCount() {
		return this.auditRepository.count();
	}

	@Override
	public AuditRecord saveRecord(AuditRequest auditData) {
		// TODO Auto-generated method stub
		AuditRecord record = new AuditRecord(auditData);
		return this.auditRepository.save(record);
	}

	@Override
	public List<AuditRecord> fetchAllRecords() {
		// TODO Auto-generated method stub
		Iterable<AuditRecord> records = this.auditRepository.findAll();
		List<AuditRecord> result = new ArrayList<AuditRecord>();
		records.forEach(result::add);
		return result;
	}
}
