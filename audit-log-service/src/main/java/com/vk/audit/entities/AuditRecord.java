package com.vk.audit.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vk.audit.dto.AuditRecordDetail;
import com.vk.audit.dto.AuditRecordDetailConverter;
import com.vk.audit.enums.AuditCategory;
import com.vk.audit.enums.AuditOperation;

@Entity
@Table(name = "Audit_Record")
@DynamicUpdate
public class AuditRecord {

	@Id
	@Column(name = "RecordId", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long recordId;

	@Column(name = "UserName")
	private String userName;

	@Column(name = "ServiceName")
	private String serviceName;

	@Column(name = "Message")
	private String message;

	@Column(name = "Category")
	@Convert(converter = AuditCategory.AuditCategoryConverter.class)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private AuditCategory category;

	@Column(name = "Operation")
	@Convert(converter = AuditOperation.AuditOperationConverter.class)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private AuditOperation operation;

	@Column(name = "Attributes")
	@Convert(converter = AuditRecordDetailConverter.class)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<AuditRecordDetail> auditAttributes;

	@Column(name = "AuditTimestamp", insertable = true, updatable = false)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long auditTime;

	@Column(name = "CreatedDateTime", insertable = true, updatable = false)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long createTime;

	public AuditRecord() {
		// TODO Auto-generated constructor stub
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "AuditRecord [recordId=" + recordId + ", userName=" + userName + ", serviceName=" + serviceName
				+ ", message=" + message + ", category=" + category + ", operation=" + operation + ", auditAttributes="
				+ auditAttributes + ", auditTime=" + auditTime + ", createTime=" + createTime + "]";
	}

}
