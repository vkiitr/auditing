package com.vk.audit.enums;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AuditOperation {

	GENERAL(0, "GENERAL"),
	CREATE(1, "CREATE"),
	MODIFY(2, "MODIFY"),
	DELETE(3, "DELETE"),
	START(4, "START"),
	STOP(5, "STOP"),
	CANCEL(6, "CANCEL"),
	RESUME(7, "RESUME"),
	ACCESS(8, "ACCESS"),
	UNKNOWN_OPERATION(-99, "UNKNOWN_OPERATION");
	
	private final int id;
	private final String name;

	AuditOperation(int id, String name) {
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
	
	public static AuditOperation fromId(int id) {
		for (AuditOperation cat: AuditOperation.values()) {
			if(cat.getId() == id) {
				return cat;
			}
		}
		throw new EnumConstantNotPresentException(AuditOperation.class, Integer.toString(id));
	}
	
	public static AuditOperation fromName(String name) {
		if (name != null) {
			for (AuditOperation cat: AuditOperation.values()) {
				if(cat.getName().equalsIgnoreCase(name)) {
					return cat;
				}
			}
		}
		throw new EnumConstantNotPresentException(AuditOperation.class, name);	
	}
	
	@JsonCreator
	public static AuditOperation getAuditOperationFromCode(String value) {
		
			for (AuditOperation cat: AuditOperation.values()) {
				if(cat.getName().equalsIgnoreCase(value)) {
					return cat;
				}
			}
		
		return AuditOperation.UNKNOWN_OPERATION;
	}
	
	// @Converter(autoApply = true)
	public static class AuditOperationConverter implements AttributeConverter <AuditOperation, Integer> {

		@Override
		public Integer convertToDatabaseColumn(AuditOperation attribute) {
			if (attribute.equals(AuditOperation.UNKNOWN_OPERATION)) {
				throw new EnumConstantNotPresentException(AuditCategory.class, attribute.getName());
			}
			return attribute.id;
		}

		@Override
		public AuditOperation convertToEntityAttribute(Integer dbData) {
			try {
				return AuditOperation.fromId(dbData);
			} catch (EnumConstantNotPresentException e) {
				return UNKNOWN_OPERATION;
			}
		}
	}
}
