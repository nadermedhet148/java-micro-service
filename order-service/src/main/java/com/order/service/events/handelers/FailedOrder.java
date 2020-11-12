package com.order.service.events.handelers;

import com.order.service.events.EventsConstants;
import org.json.JSONObject;

public class FailedOrder extends EventHandler {
    public FailedOrder(){
        this.setEventName(EventsConstants.FAILED_ORDER);
    }
    @Override
    public void handle(int key , JSONObject payload) {
        System.out.println(payload.get("id"));
    }
}
