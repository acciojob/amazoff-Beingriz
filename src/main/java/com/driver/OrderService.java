package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;

@Service
public class OrderService {
    @Autowired OrderRepository OR;


    // Adding New Order
    public void addOrder(Order order){
        OR.addOrder(order);
    }

    // Adding New Delivery Partner
    public void addPartner(String partnerId){
        OR.addPartner(partnerId);
    }

    // Creating Pair or Assigning Orders
    public void pairOrders(String orderId, String partnerId){
        OR.addOrderPartnerPair(orderId,partnerId);
    }

    // Getting order by ID
    public Order getOrder(String orderId){
       return  OR.getOrder(orderId);
    }

    // Getting partner by Id
    public DeliveryPartner getPartner(String partnerId){
        return OR.getPartner(partnerId);
    }

    // Count of order by Partner Id
    public int countOrdersByPartner(String partnerId){
        return OR.countOrdersbyPartner(partnerId);
    }


    // List of Orders by Partner Id
    public ArrayList<String> listOfOrderByPartnerId(String partnerId){
        return OR.ordersByPartnerID(partnerId);
    }

    // Displaying All Orders
    public ArrayList<String> allOrders(){
        return OR.allOrders();
    }


    // count of orders left after delivery Time
    public int getOrdersLeftAfterGivenTimeByPartnerId(String deliveryTime, String partnerId){
        String[] time = deliveryTime.split(":");
        int newTime =  Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);
        return OR.getOrdersLeftAfterGivenTimeByPartnerId(newTime, partnerId);
    }


    // Time of Last Order by Partner Id
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        int time = OR.getLastDeliveryTimeByPartnerId(partnerId);
        String HH = String.valueOf(time/60);
        String MM = String.valueOf(time%60);

        // Appending Zero for Single No.
        if(HH.length()<2) HH = '0'+HH;
        if(MM.length()<2) MM = '0'+MM;
        return HH+':'+MM;
    }
    // Count of Unassigned Orders
    public int unAssignedOrders(){
        return OR.unAssignedOrders();
    }

    // Deleting Partner by ID and Unassining his Orders
    public void deletePartner(String partnerId){
        OR.deletePartner(partnerId);
    }

    // Deleting Order by Id and Removing from Partner
    public void deleteOrder(String orderId){
        OR.deleteOrder(orderId);
    }
}
