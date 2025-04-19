package org.contourgara.application;

import lombok.RequiredArgsConstructor;
import org.contourgara.domain.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateReceiptUseCase {
    private final ReceiptRepository receiptRepository;

    public byte[] execute(String orderId, String recipientName, String remarks) {
        return receiptRepository.create(recipientName, remarks);
    }
}
