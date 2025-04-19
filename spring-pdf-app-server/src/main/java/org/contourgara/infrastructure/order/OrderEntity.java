package org.contourgara.infrastructure.order;

import org.contourgara.domain.model.Order;

public record OrderEntity(Integer id, String orderId, Integer price, String recipientName, String remarks) {
    public Order convertToModel() {
        return new Order(orderId, price, recipientName, remarks);
    }
}
