package com.payment.demo;


import com.payment.demo.events.handelers.CreateOrder;
import com.payment.demo.events.handelers.EventHandler;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		consumeEvents();
	}

	static void consumeEvents(){
		List<EventHandler> eventsHandlers = Arrays.asList(new CreateOrder());

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
//				consumer.commitAsync();
			}
		});
	}

//	static void runConsumer() {
//		Consumer<Long, String> consumer = ConsumerCreator.createConsumer();
//		consumer.subscribe(Arrays.asList("channel"));
//
//
//		while (true) {
//			final ConsumerRecords<Long, String> consumerRecords = consumer.poll(100);
//			for (ConsumerRecord<Long, String> record : consumerRecords)
//				System.out.printf("HHHHHHHHHHHHHHH = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
//		}
//	}
}
