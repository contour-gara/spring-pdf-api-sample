package org.contourgara.infrastructure.receipt;

import lombok.RequiredArgsConstructor;
import org.contourgara.domain.repository.ReceiptRepository;
import org.springframework.stereotype.Repository;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ReceiptRepositoryImpl implements ReceiptRepository {
    private final TemplateEngine templateEngine;

    @Override
    public byte[] create(String recipientName, String remarks) {
        Context context = new Context();
        context.setVariables(
                Map.ofEntries(
                        Map.entry("recipientName", recipientName),
                        Map.entry("remarks", remarks),
                        Map.entry("amount", 10000)
                )
        );
        String html = templateEngine.process("receipt", context);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ITextRenderer iTextRenderer = new ITextRenderer();
        iTextRenderer.setDocumentFromString(html);
        iTextRenderer.layout();
        iTextRenderer.createPDF(byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }
}
