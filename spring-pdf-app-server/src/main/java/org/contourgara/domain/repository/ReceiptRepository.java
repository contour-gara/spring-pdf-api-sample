package org.contourgara.domain.repository;

import org.contourgara.domain.model.Order;

public interface ReceiptRepository {
    byte[] create(Order order);
}
