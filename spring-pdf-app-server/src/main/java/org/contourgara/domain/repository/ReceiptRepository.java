package org.contourgara.domain.repository;

public interface ReceiptRepository {
    byte[] create(String recipientName, String remarks);
}
