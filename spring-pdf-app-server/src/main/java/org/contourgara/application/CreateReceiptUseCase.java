package org.contourgara.application;

import lombok.RequiredArgsConstructor;
import org.contourgara.domain.model.Order;
import org.contourgara.domain.repository.OrderRepository;
import org.contourgara.domain.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateReceiptUseCase {
    private final OrderRepository orderRepository;
    private final ReceiptRepository receiptRepository;

    public byte[] execute(String orderId, String recipientName, String remarks) {
        Order order = orderRepository.findOrderByOrderId(orderId);
        Order updatedOrder = new Order(order.orderId(), order.price(), recipientName, remarks);
        orderRepository.updateOrderForReceipt(updatedOrder);
        return receiptRepository.create(updatedOrder.recipientName(), updatedOrder.remarks());
    }
}
