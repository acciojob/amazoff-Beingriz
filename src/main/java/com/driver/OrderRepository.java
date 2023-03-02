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
    HashMap<String, DeliveryPartner> del_part_db = new HashMap<>();

    // Creating Pari Database
    HashMap<String, ArrayList<String>> pair_db = new HashMap<>();

    // Adding New Order
    public void addOrder(Order order){
        order_db.put(order.getId(), order);
    }

    // Addning new Delivery Partner
    public void addPartner(String partnerId){
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        del_part_db.put(deliveryPartner.getId(),deliveryPartner);
    }

    //Adding pair of Order and Delivery Partner by their ID
    public void addOrderPartnerPair(String orderId, String partnerId){
        if(order_db.containsKey(orderId) && del_part_db.containsKey(partnerId)){
            ArrayList<String>  orders = new ArrayList();
            if(pair_db.containsKey(partnerId)){
                orders = pair_db.get(partnerId);
            }
            orders.add(orderId);
            pair_db.put(partnerId,orders);
            DeliveryPartner partner = getPartner(partnerId);
            partner.setNumberOfOrders(countOrdersbyPartner(partnerId));
        }
    }


    // Fetcing Order Details by Order Id
    public Order getOrder(String orderId){
        return order_db.get(orderId);
    }

    //Fetching Partner by Id
    public DeliveryPartner getPartner(String partnerId){
        return del_part_db.get(partnerId);
    }

    // Display Count of Orders by Partner Id
    public int countOrdersbyPartner(String partnerId){
        ArrayList<String> orders = new ArrayList<>();
        if(pair_db.containsKey(partnerId)){
            orders = new ArrayList<>(pair_db.get(partnerId));
        }
        return orders.size();
    }

    //Displaying List of Orders by Partner Id
    public ArrayList<String> ordersByPartnerID(String partnerId){
        ArrayList<String> orders = new ArrayList<>();
        if(pair_db.containsKey(partnerId)){
            orders = pair_db.get(partnerId);
        }
        return orders;
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
        int allOrders = order_db.size();
        int assigned = 0;
        for (ArrayList<String> order: pair_db.values()) {
            assigned+=order.size();
        }
        return allOrders-assigned;
    }

    // Deleting Partner by Id and Un-assigning all his orders
    public void deletePartner(String partnerId){
        if(del_part_db.containsKey(partnerId)){
            del_part_db.remove(partnerId);
        }
        if(pair_db.containsKey(partnerId)){
            pair_db.remove(partnerId);
        }
    }

    // Deleting Order by ID from ORders and From Pair DB
    public void deleteOrder(String orderId){
        if(order_db.containsKey(orderId)) {
            order_db.remove(orderId);
        }
        for(ArrayList<String> orders : pair_db.values()){
            for (int i = 0; i < orders.size(); i++) {
                if(orders.get(i).equals(orderId)){
                    orders.remove(i);
                    break;
                }
            }
        }

    }
}
