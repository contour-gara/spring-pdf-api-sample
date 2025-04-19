package org.contourgara.infrastructure;

import org.contourgara.domain.repository.ReceiptRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ReceiptRepositoryImplTest {
    @Autowired
    ReceiptRepository sut;

    @Autowired
    ResourceLoader resourceLoader;

    @Test
    void 宛名と但書を受け取り領収書を返す() {
        // execute
        byte[] actual = sut.create("test name", "product name");

        // assert
        assertThat(actual).isNotNull();
    }

    @Disabled
    @Test
    void テストのため領収書を出力する() throws Exception {
        // execute
        byte[] actual = sut.create("test name", "product name");

        // save
        File file = Paths.get(resourceLoader.getResource("classpath:").getURI().getPath(), "receipt.pdf").toFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(actual);
        }
        System.out.println(file.getAbsolutePath());
    }
}
