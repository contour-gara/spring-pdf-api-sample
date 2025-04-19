package org.contourgara.domain.model;

public record Order(String orderId, Integer price, String recipientName, String remarks) {
}
