package com.vk.audit.dto;

import java.io.Serializable;

public class AuditRecordDetail {
	public String name;
	public String oldValue;
	public String newValue;
	
	public AuditRecordDetail() {
		super();
	}

	public AuditRecordDetail(String name, String oldValue, String newValue) {
		super();
		this.name = name;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	@Override
	public String toString() {
		return "AuditRecordDetail [name=" + name + ", oldValue=" + oldValue + ", newValue=" + newValue + "]";
	}
	
}
