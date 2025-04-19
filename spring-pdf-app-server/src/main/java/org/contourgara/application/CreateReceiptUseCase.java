package org.contourgara.application;

import org.springframework.stereotype.Service;

@Service
public class CreateReceiptUseCase {
    public byte[] execute(String orderId, String recipientName, String remarks) {
        return new byte[0];
    }
}
