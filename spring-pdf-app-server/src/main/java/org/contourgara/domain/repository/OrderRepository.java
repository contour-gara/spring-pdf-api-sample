package org.contourgara.domain.repository;

import org.contourgara.domain.model.Order;

public interface OrderRepository {
    Order findOrderByOrderId(String orderId);
    void updateOrderForReceipt(Order order);
}
