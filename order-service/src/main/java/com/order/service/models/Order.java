package com.order.service.models;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;


@Table(name = "Student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id ;

    @Column(name = "paymentId")
    private int paymentId ;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "amount")
    private Float amount;


    public String toJson (){
        JSONObject payload = new JSONObject();
        payload.put("amount",this.getAmount());
        payload.put("id",this.getId());
        return payload.toString();
    }

}
