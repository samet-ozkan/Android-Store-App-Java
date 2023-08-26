package com.sametozkan.storeapp.data.mapper;

import com.sametozkan.storeapp.data.dto.OrderDTO;
import com.sametozkan.storeapp.domain.model.Order;
import com.sametozkan.storeapp.util.Utils;

public class OrderMapper {

    public static Order toOrder(OrderDTO orderDTO) {
        return new Order(
                orderDTO.getOrderId(),
                orderDTO.getProductIds(),
                orderDTO.getTotalAmount(),
                Utils.convertTimestampToDate(orderDTO.getTimestamp())
        );
    }

    public static OrderDTO toOrderDTO(Order order) {
        return new OrderDTO(
                order.getOrderId(),
                order.getProductIds(),
                order.getTotalAmount(),
                Utils.convertDateStringToTimestamp(order.getDate())
        );
    }
}
