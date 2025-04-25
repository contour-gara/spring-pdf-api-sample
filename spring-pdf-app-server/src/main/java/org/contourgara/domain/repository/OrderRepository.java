package org.contourgara.domain.repository;

import org.contourgara.domain.model.Order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findOrderByOrderId(String orderId);
    void updateOrderForReceipt(Order order);
}
