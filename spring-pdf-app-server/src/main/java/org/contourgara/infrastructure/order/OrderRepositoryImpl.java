package org.contourgara.infrastructure.order;

import lombok.RequiredArgsConstructor;
import org.contourgara.domain.model.Order;
import org.contourgara.domain.repository.OrderRepository;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final JdbcClient jdbcClient;

    @Override
    public Order findOrderByOrderId(String orderId) {
        return jdbcClient.sql("SELECT * FROM orders WHERE order_id = :orderId")
                .param("orderId", orderId)
                .query(new DataClassRowMapper<>(OrderEntity.class))
                .optional()
                .get()
                .convertToModel();
    }

    @Override
    public void updateOrderForReceipt(Order order) {
        jdbcClient.sql("UPDATE orders SET recipient_name = :recipientName, remarks = :remarks WHERE order_id = :orderId")
                .param("recipientName", order.recipientName())
                .param("remarks", order.remarks())
                .param("orderId", order.orderId())
                .update();
    }
}
