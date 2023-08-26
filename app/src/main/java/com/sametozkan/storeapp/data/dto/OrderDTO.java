package com.sametozkan.storeapp.data.dto;

import java.util.List;

public class OrderDTO {

    private String orderId;

    private List<Integer> productIds;

    private double totalAmount;

    private long timestamp;

    public OrderDTO() {

    }

    public OrderDTO(String orderId, List<Integer> productIds, double totalAmount, long timestamp) {
        this.orderId = orderId;
        this.productIds = productIds;
        this.totalAmount = totalAmount;
        this.timestamp = timestamp;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
