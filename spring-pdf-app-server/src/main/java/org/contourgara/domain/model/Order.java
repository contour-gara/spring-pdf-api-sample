package org.contourgara.domain.model;

public record Order(String orderId, Integer amount, String recipientName, String remarks) {
}
