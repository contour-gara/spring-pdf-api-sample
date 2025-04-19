package org.contourgara.infrastructure.order;

import org.contourgara.domain.model.Order;

public record OrderEntity(Integer id, String orderId, Integer amount, String recipientName, String remarks) {
    public Order convertToModel() {
        return new Order(orderId, amount, recipientName, remarks);
    }
}
