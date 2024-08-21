package com.project.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.project.demo.dto.TransactionRequestDto;

@Service
public class GenerateDataServiceImpl implements IGenerateDataService {

	Logger logger = LoggerFactory.getLogger(GenerateDataServiceImpl.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value(value = "${kafka.nameTopic}")
	private String nameTopic;

	private final Gson gson = new Gson();

	private static final String CRON = "* * * * * * ";

	@Override
	@Scheduled(cron = CRON)
	@Async
	public void generate() {

		TransactionRequestDto request = new TransactionRequestDto();
		request.setAmount((float) 500000);
		request.setDeviceNumber(123456);
		request.setGeoPosition("GeoPosition");
		request.setTransactionId("TransactionId");
		
		String requestJson = gson.toJson(request);
		
		logger.info("Se inicia envio al topico :: "+ nameTopic);
		
		kafkaTemplate.send(nameTopic, requestJson);
		
		logger.info("Envio exitoso :: "+ requestJson);

	}

}
