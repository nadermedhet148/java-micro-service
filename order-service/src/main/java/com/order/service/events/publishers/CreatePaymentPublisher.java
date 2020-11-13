package com.order.service.events.publishers;

import com.order.service.events.EventsConstants;
import com.order.service.events.kafka.producer.ProducerCreator;
import com.order.service.models.Order;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class CreatePaymentPublisher {

    public void publishCreateOrderPayment(Order order){

		Producer<Long, String> producer = ProducerCreator.createProducer();


		ProducerRecord<Long, String> producerRecord = new ProducerRecord(EventsConstants.CREATE_PAYMENT, order.getId() , order.toJson());
		try {
			RecordMetadata metadata = producer.send(producerRecord).get();
			System.out.println("Record sent with key " + " to partition " + metadata.partition()
					+ " with offset " + metadata.offset());
		} catch (ExecutionException e) {
			System.out.println("Error in sending record");
			System.out.println(e);
		} catch (InterruptedException e) {
			System.out.println("Error in sending record");
			System.out.println(e);
		}
    }

}
