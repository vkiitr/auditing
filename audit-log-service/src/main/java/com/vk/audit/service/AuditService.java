package com.vk.audit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vk.audit.dao.AuditRepository;
import com.vk.audit.dto.AuditRequest;
import com.vk.audit.entities.AuditRecord;
import com.vk.audit.exceptions.DBException;

@Service
public class AuditService implements IAuditService {

	@Autowired
	private AuditRepository auditRepository;

	public Long getAuditCount() {
		return this.auditRepository.count();
	}

	@Override
	public AuditRecord saveRecord(AuditRequest auditData) throws IllegalArgumentException {
		try {
			AuditRecord savedRecord = this.auditRepository.save(this.convertToAuditRecordEntity(auditData));
			return savedRecord;
		} catch (Exception e) {
			throw new DBException(e.getMessage());
		}
		
	}
	
	private AuditRecord convertToAuditRecordEntity(AuditRequest auditData) {
		AuditRecord record = new AuditRecord();
		record.setUserName(auditData.getUserName());
		record.setCategory(auditData.getCategory());
		record.setOperation(auditData.getOperation());
		record.setServiceName(auditData.getServiceName());
		record.setMessage(auditData.getMessage());
		record.setAuditTime(auditData.getAuditTime());
		record.setAuditAttributes(auditData.getAuditAttributes());
		record.setCreateTime(System.currentTimeMillis());
        return record;
}

	@Override
	public List<AuditRecord> fetchAllRecords(Boolean isAdmin, String userName) {
		List<AuditRecord> result = new ArrayList<AuditRecord>();
		if (isAdmin) {
			Iterable<AuditRecord> records = this.auditRepository.findAll();
			records.forEach(result::add);
		} else {
			Iterable<AuditRecord> records = this.auditRepository.findByUserNameIgnoreCase(userName);
			records.forEach(result::add);
		}
		
		return result;
	}
}
