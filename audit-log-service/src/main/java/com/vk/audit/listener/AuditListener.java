package com.vk.audit.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vk.audit.dto.AuditRequest;
import com.vk.audit.service.IAuditService;

@Component
public class AuditListener implements RabbitListenerConfigurer {

	// private static final Logger logger = LoggerFactory.getLogger(AuditListener.class);

	@Autowired
	public IAuditService auditService;
	
	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
	}

	@RabbitListener(queues = "${spring.rabbitmq.queue}")
	public void processAuditRecord(String auditDataJson) {
		// logger.info("\nRecieved inside AuditListener: " + auditDataJson);
		ObjectMapper mapper = new ObjectMapper();
		try {
			AuditRequest auditData = mapper.readValue(auditDataJson, AuditRequest.class);
			// logger.info("\nRecieved inside AuditListener: " + auditData);
			this.auditService.saveRecord(auditData);
		} catch (JsonMappingException e) {
			// add logger.debug
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// add logger.debug
			e.printStackTrace();
		}
		
	}

}