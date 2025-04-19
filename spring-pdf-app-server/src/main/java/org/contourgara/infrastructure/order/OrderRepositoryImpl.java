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
        return jdbcClient.sql("SELECT * FROM orders WHERE order_id = ?")
                .params(orderId)
                .query(new DataClassRowMapper<>(OrderEntity.class))
                .optional()
                .get()
                .convertToModel();
    }

    @Override
    public void updateOrderForReceipt(Order order) {
        jdbcClient.sql("UPDATE orders SET recipient_name = ?, remarks = ? WHERE order_id = ?")
                .params(order.recipientName(), order.remarks(), order.orderId())
                .update();
    }
}
