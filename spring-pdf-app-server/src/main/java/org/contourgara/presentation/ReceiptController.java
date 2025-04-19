package org.contourgara.presentation;

import lombok.RequiredArgsConstructor;
import org.contourgara.application.CreateReceiptUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReceiptController {
    private final CreateReceiptUseCase createReceiptUseCase;

    @PostMapping(value = "/receipt/{orderId}", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] createReceipt(
            @PathVariable(value = "orderId") String orderId,
            @RequestBody CreateReceiptRequest createReceiptRequest
    ) {
        return createReceiptUseCase.execute(orderId, createReceiptRequest.recipientName(), createReceiptRequest.remarks());
    }
}
