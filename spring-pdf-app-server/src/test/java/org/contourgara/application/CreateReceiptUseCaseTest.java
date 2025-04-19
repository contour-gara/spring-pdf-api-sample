package org.contourgara.application;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@DBUnit(allowEmptyFields = true)
@DBRider
class CreateReceiptUseCaseTest {
    @Autowired
    CreateReceiptUseCase sut;

    @Test
    @DataSet(value = "datasets/setup/order.yml")
    @ExpectedDataSet(value = "datasets/expected/order-updated.yml")
    void 受け取った宛名と但書から領収書を返す() {
        // execute
        byte[] actual = sut.execute("sample", "test name", "test product");

        // assert
        assertThat(actual).isNotNull();
    }
}
