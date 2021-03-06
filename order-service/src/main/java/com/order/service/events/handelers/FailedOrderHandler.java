package com.order.service.events.handelers;

import com.order.service.events.EventsConstants;
import com.order.service.models.Order;
import com.order.service.repository.IOrderRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FailedOrderHandler extends EventHandler {
    @Autowired
    IOrderRepository orderRepository;

    public FailedOrderHandler(){
        this.setEventName(EventsConstants.FAILED_ORDER);
    }

    @Override
    public void handle(int key , JSONObject payload) {
        Optional<Order> order = this.orderRepository.findById((Integer) payload.get("orderId"));
        if(order.isPresent()){
            Order presentedOrder = order.get();
            presentedOrder.setStatus("FAILED");
            presentedOrder.setPaymentId((Integer) payload.get("paymentId"));
        }
    }
}
