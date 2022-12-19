package com.vk.audit.dto;

import java.io.IOException;
import java.util.List;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuditRecordDetailConverter implements AttributeConverter<List<AuditRecordDetail>, String> {

    @Override
    public String convertToDatabaseColumn(List<AuditRecordDetail> auditAtrributes) {

        String auditAttributesJSON = null;
        ObjectMapper objectMapper = new ObjectMapper(); 
        try {
            auditAttributesJSON = objectMapper.writeValueAsString(auditAtrributes);
        } catch (final JsonProcessingException e) {
        	System.out.println("JSON writing error" + e);
        }

        return auditAttributesJSON; 
    }

    @Override
    public List<AuditRecordDetail> convertToEntityAttribute(String auditAttributesJSON) {

    	List<AuditRecordDetail> auditAttributes = null;
        ObjectMapper objectMapper = new ObjectMapper(); 
        try {
            auditAttributes = objectMapper.readValue(auditAttributesJSON, 
            	new TypeReference<List<AuditRecordDetail>>() {});
        } catch (final IOException e) {
            System.out.println("JSON reading error" + e);
        }

        return auditAttributes;
    }
}