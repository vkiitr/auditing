package com.vk.audit.dto;

import java.util.List;

import com.vk.audit.enums.AuditCategory;
import com.vk.audit.enums.AuditOperation;


/*
 * 
	{
	  "userName": "Vikas",
	  "serviceName": "security-service",
	  "category": "CONFIGURATION",
	  "operation": "MODIFY",
	  "auditTime": 1671354129,
	  "auditAttributes": [
	    {
	      "name": "interval",
	      "oldValue": "15",
	      "newValue": "10"
	  	},
	    {
	      "name": "retention",
	      "oldValue": "1 years",
	      "newValue": "2 years"
	  	},
	    {
	      "name": "hostName",
	      "oldValue": "abc.com",
	      "newValue": "xyz.com"
	  	}
	  ]
	}

 * 
 */
public class AuditRequest {

	private String userName;
	
	private String serviceName;

	private AuditCategory category;
	
	private AuditOperation operation;
	
	private List<AuditRecordDetail> auditAttributes;
	
	private Long auditTime;

	public AuditRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public AuditCategory getCategory() {
		return category;
	}

	public void setCategory(AuditCategory category) {
		this.category = category;
	}

	public AuditOperation getOperation() {
		return operation;
	}

	public void setOperation(AuditOperation operation) {
		this.operation = operation;
	}

	public List<AuditRecordDetail> getAuditAttributes() {
		return auditAttributes;
	}

	public void setAuditAttributes(List<AuditRecordDetail> auditAttributes) {
		this.auditAttributes = auditAttributes;
	}

	public Long getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Long auditTime) {
		this.auditTime = auditTime;
	}

	@Override
	public String toString() {
		return "AuditRequest [userName=" + userName + ", serviceName=" + serviceName + ", category=" + category
				+ ", operation=" + operation + ", auditAttributes=" + auditAttributes + "]";
	}

}
