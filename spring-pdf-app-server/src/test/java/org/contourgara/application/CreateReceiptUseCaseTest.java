package org.contourgara.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CreateReceiptUseCaseTest {
    @Autowired
    CreateReceiptUseCase sut;

    @Test
    void 受け取った宛名と但書から領収書を返す() {
        // execute
        byte[] actual = sut.execute("sample", "test name", "test product");

        // assert
        assertThat(actual).isNotNull();
    }
}
