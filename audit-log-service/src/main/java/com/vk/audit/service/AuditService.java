package com.vk.audit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vk.audit.dao.AuditRepository;
import com.vk.audit.dto.AuditRequest;
import com.vk.audit.dto.AuditResponse;
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
			AuditRecord savedRecord = this.auditRepository.save(this.mapToEntity(auditData));
			return savedRecord;
		} catch (Exception e) {
			throw new DBException(e.getMessage());
		}
	}

	@Override
	public AuditResponse fetchAllRecords(int pageNo, int pageSize, String sortBy, String sortDir, String userName) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		// create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		List<AuditRequest> result = new ArrayList<AuditRequest>();
		AuditResponse auditResponse = new AuditResponse();
		if (userName == null) {
			Page<AuditRecord> records = this.auditRepository.findAll(pageable);
			result = records.stream().map(record -> mapToResponseDto(record)).collect(Collectors.toList());
			
	        auditResponse.setData(result);
	        auditResponse.setPageNo(records.getNumber());
	        auditResponse.setPageSize(records.getSize());
	        auditResponse.setTotalElements(records.getTotalElements());
	        auditResponse.setTotalPages(records.getTotalPages());
	        auditResponse.setLast(records.isLast());
		} else {
			Page<AuditRecord> records = this.auditRepository.findAllByUserNameIgnoreCase(userName, pageable);
			result = records.stream().map(record -> mapToResponseDto(record)).collect(Collectors.toList());
			
	        auditResponse.setData(result);
	        auditResponse.setPageNo(records.getNumber());
	        auditResponse.setPageSize(records.getSize());
	        auditResponse.setTotalElements(records.getTotalElements());
	        auditResponse.setTotalPages(records.getTotalPages());
	        auditResponse.setLast(records.isLast());
		}
		
		return auditResponse;
	}
	
	private AuditRecord mapToEntity(AuditRequest auditData) {
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
	
	private AuditRequest mapToResponseDto(AuditRecord auditData) {
		AuditRequest record = new AuditRequest();
		record.setUserName(auditData.getUserName());
		record.setCategory(auditData.getCategory());
		record.setOperation(auditData.getOperation());
		record.setServiceName(auditData.getServiceName());
		record.setMessage(auditData.getMessage());
		record.setAuditTime(auditData.getAuditTime());
		record.setAuditAttributes(auditData.getAuditAttributes());
		record.setCreateTime(auditData.getCreateTime());
		return record;
	}

}
