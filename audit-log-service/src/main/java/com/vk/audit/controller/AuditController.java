package com.vk.audit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vk.audit.constant.AppConstants;
import com.vk.audit.dto.AuditResponse;
import com.vk.audit.dto.RecordCount;
import com.vk.audit.service.IAuditService;

@RestController
public class AuditController {

	@Autowired
	public IAuditService auditService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/audits/count")
	public RecordCount getTotalappCount() {
		Long count = this.auditService.getAuditCount();
		return new RecordCount(count);
	}

// closing this create end point

//	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/audit")
//	public String addAuditRecord(@RequestBody AuditRequest auditData) {
//		AuditRecord savedAudit = this.auditService.saveRecord(auditData);
//		System.out.println("Record saved: " + savedAudit);
//		return "SUCCESS";
//	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/audits")
	public AuditResponse getAllAuditRecords(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
			Authentication authentication) {

		Boolean isAdmin = authentication.getAuthorities().toString().contains("[ADMIN]");
		String userName = authentication.getName();
		AuditResponse records = new AuditResponse();
		if (isAdmin) {
			records = this.auditService.fetchAllRecords(pageNo, pageSize, sortBy, sortDir, null);
		} else {
			records = this.auditService.fetchAllRecords(pageNo, pageSize, sortBy, sortDir, userName);
		}

		return records;

	}
}
