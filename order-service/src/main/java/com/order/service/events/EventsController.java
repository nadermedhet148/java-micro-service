package com.order.service.events;

import com.order.service.events.handelers.EventHandler;
import com.order.service.events.handelers.FailedOrderHandler;
import com.order.service.events.handelers.SuccessOrderHandler;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(
        @Inject))
public class EventsController {
    @Autowired
    FailedOrderHandler failedOrderHandler;

    @Autowired
    SuccessOrderHandler successOrderHandler;



    public void consumeEvents(){
        List<EventHandler> eventsHandlers = Arrays.asList(failedOrderHandler,successOrderHandler);

        eventsHandlers.forEach(eventHandler -> {
            Consumer consumer = eventHandler.getConsumer();
            while (true) {
                final ConsumerRecords<Integer, String> consumerRecords = consumer.poll(1000);
                consumerRecords.forEach(record -> {
                    System.out.println("Record Key " + record.key());
                    JSONObject json = new JSONObject(record.value());
                    eventHandler.handle(record.key() , json);
                });
                consumer.commitAsync();
            }
        });
    }
}
