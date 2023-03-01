package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id = id;
        int H1 = deliveryTime.charAt(0)-'0';
        int H2 = deliveryTime.charAt(1)-'0';
        int HH = (H1+H2)*60;
        int M1 = deliveryTime.charAt(3)-'0';
        int M2 = deliveryTime.charAt(4)-'0';
        this.deliveryTime =  HH+(M1+M2);
    }



    public String getId() {
        return id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
