package com.vk.audit.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AuditCategory {
	GENERAL(0, "GENERAL"),
	LOGIN(1, "LOGIN"),
	CONFIGURATION(2, "CONFIGURATION"),
	UNKNOWN_CATEGORY (-99, "UNKNOWN");
	
	private final int id;
	private final String name;

	AuditCategory(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	@JsonValue
	public String getName() {
		return name;
	}
	
	public static AuditCategory fromId(int id) {
		for (AuditCategory cat: AuditCategory.values()) {
			if(cat.getId() == id) {
				return cat;
			}
		}
		throw new EnumConstantNotPresentException(AuditCategory.class, Integer.toString(id));
	}
	
	public static AuditCategory fromName(String name) {
		if (name != null) {
			for (AuditCategory cat: AuditCategory.values()) {
				if(cat.getName().equalsIgnoreCase(name)) {
					return cat;
				}
			}
		}
		throw new EnumConstantNotPresentException(AuditCategory.class, name);	
	}
	
	@JsonCreator
	public static AuditCategory getAuditCategoryFromCode(String value) {
		
			for (AuditCategory cat: AuditCategory.values()) {
				if(cat.getName().equalsIgnoreCase(value)) {
					return cat;
				}
			}
		
		return AuditCategory.UNKNOWN_CATEGORY;
	}
}
