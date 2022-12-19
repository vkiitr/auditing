package com.vk.audit.producer.service;

import com.vk.audit.dto.AuditRequest;

public interface IMQSenderService {

	public void send(AuditRequest record);
}
