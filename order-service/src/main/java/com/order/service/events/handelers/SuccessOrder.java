package com.order.service.events.handelers;

import com.order.service.events.EventsConstants;
import org.json.JSONObject;

public class SuccessOrder extends EventHandler {
    public SuccessOrder(){
        this.setEventName(EventsConstants.SUCCESS_ORDER);
    }
    @Override
    public void handle(int key , JSONObject payload) {
        System.out.println(payload.get("id"));
    }
}
