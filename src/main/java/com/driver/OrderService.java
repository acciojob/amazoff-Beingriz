package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;


    // Adding New Order
    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }

    // Adding New Delivery Partner
    public void addPartner(String partnerId){
        orderRepository.addPartner(partnerId);
    }

    // Creating Pair or Assigning Orders
    public void pairOrders(String orderId, String partnerId){
        orderRepository.addOrderPartnerPair(orderId,partnerId);
    }

    // Getting order by ID
    public Order getOrder(String orderId){
       return  orderRepository.getOrder(orderId);
    }

    // Getting partner by Id
    public DeliveryPartner getPartner(String partnerId){
        return orderRepository.getPartner(partnerId);
    }

    // Count of order by Partner Id
    public int countOrdersByPartner(String partnerId){
        return orderRepository.countOrdersbyPartner(partnerId);
    }


    // List of Orders by Partner Id
    public ArrayList<String> listOfOrderByPartnerId(String partnerId){
        return orderRepository.ordersByPartnerID(partnerId);
    }

    // Displaying All Orders
    public ArrayList<String> allOrders(){
        return orderRepository.allOrders();
    }


    // count of orders left after delivery Time
    public int getOrdersLeftAfterGivenTimeByPartnerId(String deliveryTime, String partnerId){
        String[] time = deliveryTime.split(":");
        int newTime =  Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(newTime, partnerId);
    }


    // Time of Last Order by Partner Id
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        int time = orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
        String HH = String.valueOf(time/60);
        String MM = String.valueOf(time%60);

        // Appending Zero for Single No.
        if(HH.length()<2) HH = '0'+HH;
        if(MM.length()<2) MM = '0'+MM;
        return HH+':'+MM;
    }
    // Count of Unassigned Orders
    public int unAssignedOrders(){
        return orderRepository.unAssignedOrders();
    }

    // Deleting Partner by ID and Unassining his Orders
    public void deletePartner(String partnerId){
        orderRepository.deletePartner(partnerId);
    }

    // Deleting Order by Id and Removing from Partner
    public void deleteOrder(String orderId){
        orderRepository.deleteOrder(orderId);
    }
}
