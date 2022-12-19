package com.vk.audit.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vk.audit.dto.AuditRequest;
import com.vk.audit.producer.service.IMQSenderService;

@RestController
@RequestMapping(value = "/audit/")
public class ProducerController {

	private IMQSenderService mqSender;

	@Autowired
	public ProducerController(IMQSenderService mqSender) {
		this.mqSender = mqSender;
	}

	@Value("${app.message}")
	private String message;

	@PostMapping(value = "publish")
	public String publishUserDetails(@RequestBody AuditRequest record) {
		mqSender.send(record);
		return message;
	}

}