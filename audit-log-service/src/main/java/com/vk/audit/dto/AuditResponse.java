package com.vk.audit.dto;

import java.util.List;

import com.vk.audit.entities.AuditRecord;

public class AuditResponse {

	List<AuditRecord> data;

	public AuditResponse() {
		super();
	}

	public AuditResponse(List<AuditRecord> data) {
		super();
		this.data = data;
	}

	public List<AuditRecord> getData() {
		return data;
	}

	public void setData(List<AuditRecord> data) {
		this.data = data;
	}

}
