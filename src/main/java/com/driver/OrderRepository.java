package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepository {
    // Creating Order Database Using Order ID
    HashMap<String, Order> order_db = new HashMap<>();

    // Creating Delivery Partner Database with Partner Id
    HashMap<String, DeliveryPartner> deliveryPartner_db = new HashMap<>();


    // Partner and Order Assignment
    Map<String , String> orderPartnerDb = new HashMap<>();
    // Creating Pari Database
    HashMap<String, ArrayList<String>> partnerOrders_db = new HashMap<>();

    // Adding New Order
    public void addOrder(Order order){
        order_db.put(order.getId(), order);
    }

    // Addning new Delivery Partner
    public void addPartner(String partnerId){
        deliveryPartner_db.put(partnerId,new DeliveryPartner(partnerId));
    }

    //Adding pair of Order and Delivery Partner by their ID
    public void addOrderPartnerPair(String orderId, String partnerId){
        if(order_db.containsKey(orderId) && deliveryPartner_db.containsKey(partnerId)){
            orderPartnerDb.put(orderId,partnerId); // Assigning 1 order to 1 partner only.

            ArrayList<String>  orders = new ArrayList();
            if(partnerOrders_db.containsKey(partnerId)){
                orders = partnerOrders_db.get(partnerId);
            }
            orders.add(orderId);
            partnerOrders_db.put(partnerId,orders);

            // Increment the No of orders for that partner
            DeliveryPartner partner = deliveryPartner_db.get(partnerId);
            partner.setNumberOfOrders(orders.size());
        }
    }


    // Fetcing Order Details by Order Id
    public Order getOrder(String orderId){
        return order_db.get(orderId);
    }

    //Fetching Partner by Id
    public DeliveryPartner getPartner(String partnerId){
        return deliveryPartner_db.get(partnerId);
    }

    // Display Count of Orders by Partner Id
    public int countOrdersbyPartner(String partnerId){

        return partnerOrders_db.get(partnerId).size();
    }

    //Displaying List of Orders by Partner Id
    public ArrayList<String> ordersByPartnerID(String partnerId){
        return partnerOrders_db.get(partnerId);
    }

    // Displaying List of Orders
    public ArrayList<String> allOrders(){
        ArrayList<String> orders = new ArrayList<>();
        for (String order: order_db.keySet()) {
            orders.add(order);
        }
        return orders;
    }



    // Display count of Unassigned Orders
    public int unAssignedOrders(){
        return order_db.size()- orderPartnerDb.size();
    }

    // count of Orders left after Delivery tIme
    public  int getOrdersLeftAfterGivenTimeByPartnerId(int time, String partnerId){
        int count = 0;
        // Get the list of Orders of this Partner
        ArrayList<String> orders = partnerOrders_db.get(partnerId);
        for (String orderId: orders ) {
            int deliveryTime = order_db.get(orderId).getDeliveryTime();
            if(deliveryTime > time) count++;
        }
        return count;
    }

    // Last order time by Partner Id
    public int getLastDeliveryTimeByPartnerId(String partnerId){
        int maxTime = 0;
        ArrayList<String> orders = partnerOrders_db.get(partnerId);
        for(String orderId : orders){
            int currTime = order_db.get(orderId).getDeliveryTime();
            maxTime = Math.max(currTime, maxTime);
        }
        return maxTime;
    }

    // Deleting Partner by Id and Un-assigning all his orders
    public void deletePartner(String partnerId){
       deliveryPartner_db.remove(partnerId);
       ArrayList<String> orders = partnerOrders_db.get(partnerId);
       partnerOrders_db.remove(partnerId);
       for(String orderId : orders){
           orderPartnerDb.remove(orderId);
       }
    }

    // Deleting Order by ID from ORders and From Pair DB
    public void deleteOrder(String orderId){
      order_db.remove(orderId);
      String partnerId = orderPartnerDb.get(orderId);
      orderPartnerDb.remove(orderId);
      partnerOrders_db.get(partnerId).remove(orderId);
      deliveryPartner_db.get(partnerId).setNumberOfOrders(partnerOrders_db.get(partnerId).size());

    }
}
