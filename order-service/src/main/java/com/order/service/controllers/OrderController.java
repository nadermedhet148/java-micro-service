package com.order.service.controllers;

import com.order.service.controllers.requests.CreateOrderRequest;
import com.order.service.events.publishers.CreatePayment;
import com.order.service.models.Order;
import com.order.service.repository.IOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@AllArgsConstructor(onConstructor = @__(
        @Inject))
public class OrderController {

    @Autowired
    IOrderRepository OrderRepository;

    @Autowired
    CreatePayment createPayment;

    @PostMapping(value = "")
    public Order createOrder(@RequestBody CreateOrderRequest body){
        Order order = new Order();
        order.setAmount(body.getAmount());
        order.setName(body.getName());
        order.setStatus("PENDING");
        this.OrderRepository.save(order);
        createPayment.publishCreateOrderPayment(order);
        return order;
    }

}
