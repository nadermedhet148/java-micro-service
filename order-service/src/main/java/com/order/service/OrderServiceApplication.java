package com.order.service;

import com.order.service.events.EventsController;
import com.order.service.events.handelers.EventHandler;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class OrderServiceApplication {
	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(OrderServiceApplication.class, args);
		EventsController eventsController = (EventsController) context.getBean("eventsController");
		eventsController.consumeEvents();
	}



}
