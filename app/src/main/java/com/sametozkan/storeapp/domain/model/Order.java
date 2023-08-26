package com.sametozkan.storeapp.domain.model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {

    private String orderId;

    private List<Integer> productIds;

    private double totalAmount;

    private String date;

    public Order(String orderId, List<Integer> productIds, double totalAmount, String date) {
        this.orderId = orderId;
        this.productIds = productIds;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
