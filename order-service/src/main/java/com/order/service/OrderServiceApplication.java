package com.order.service;

import com.order.service.events.handelers.EventHandler;
import com.order.service.events.handelers.FailedOrder;
import com.order.service.events.handelers.SuccessOrder;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
		consumeEvents();
	}

	static void consumeEvents(){
		List<EventHandler> eventsHandlers = Arrays.asList(new SuccessOrder() , new FailedOrder());

		eventsHandlers.forEach(eventHandler -> {
			Consumer consumer = eventHandler.getConsumer();
			while (true) {
				final ConsumerRecords<Integer, String> consumerRecords = consumer.poll(1000);
				consumerRecords.forEach(record -> {
					System.out.println("Record Key " + record.key());
					System.out.println("Record value " + record.value());
					System.out.println("Record partition " + record.partition());
					System.out.println("Record offset " + record.offset());
					JSONObject json = new JSONObject(record.value());
					eventHandler.handle(record.key() , json);
				});
				consumer.commitAsync();
			}
		});
	}

}
