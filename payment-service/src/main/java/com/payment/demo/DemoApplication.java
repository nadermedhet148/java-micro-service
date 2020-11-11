package com.payment.demo;

import com.payment.demo.kafka.constants.IKafkaConstants;
import com.payment.demo.kafka.consumer.ConsumerCreator;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
		runConsumer();
	}

	static void runConsumer() {
		Consumer<Long, String> consumer = ConsumerCreator.createConsumer();
		consumer.subscribe(Arrays.asList("channel"));


		while (true) {
			final ConsumerRecords<Long, String> consumerRecords = consumer.poll(100);
			for (ConsumerRecord<Long, String> record : consumerRecords)
				System.out.printf("HHHHHHHHHHHHHHH = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
		}
	}
}
