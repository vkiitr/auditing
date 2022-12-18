package com.vk.audit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vk.audit.dto.AuditResponse;
import com.vk.audit.dto.RecordCount;
import com.vk.audit.service.IAuditService;

@RestController
public class AuditController {

	@Autowired
	public IAuditService auditService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/audit/count")
	public RecordCount getTotalappCount() {
		Long count = this.auditService.getAuditCount();
		return new RecordCount(count);
	}

// closing this interface

//	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/audit")
//	public String addAuditRecord(@RequestBody AuditRequest auditData) {
//		AuditRecord savedAudit = this.auditService.saveRecord(auditData);
//		System.out.println("Record saved: " + savedAudit);
//		return "SUCCESS";
//	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/audit")
	public AuditResponse getAllRecords() {
		
		return new AuditResponse(this.auditService.fetchAllRecords());
	}
}
