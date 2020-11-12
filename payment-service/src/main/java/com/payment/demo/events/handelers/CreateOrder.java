package com.payment.demo.events.handelers;

import com.payment.demo.events.EventsConstants;
import com.payment.demo.events.handelers.EventHandler;
import org.json.JSONObject;

public class CreateOrder extends EventHandler {
    public CreateOrder(){
        this.setEventName(EventsConstants.SUCCESS_ORDER);
    }
    @Override
    public void handle(int key , JSONObject payload) {
        System.out.println(payload.get("id"));
    }
}
