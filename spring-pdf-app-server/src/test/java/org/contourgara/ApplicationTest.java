package org.contourgara;

import org.contourgara.presentation.ReceiptController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ApplicationTest {
    @Autowired
    ReceiptController receiptController;

    @Test
    void contextLoads() {
        // assert
        assertThat(receiptController).isNotNull();
    }
}
