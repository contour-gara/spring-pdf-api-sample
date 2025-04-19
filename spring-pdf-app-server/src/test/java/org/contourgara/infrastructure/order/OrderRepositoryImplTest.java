package org.contourgara.infrastructure.order;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.contourgara.domain.model.Order;
import org.contourgara.domain.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@DBUnit(allowEmptyFields = true)
@DBRider
class OrderRepositoryImplTest {
    @Autowired
    OrderRepository sut;

    @Test
    @DataSet(value = "datasets/setup/order.yml")
    @ExpectedDataSet(value = "datasets/expected/order.yml")
    void 受注IDで検索した場合受注が返る() {
        // execute
        Order actual = sut.findOrderByOrderId("sample");

        // assert
        Order expected = new Order("sample", 10000, "", "");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DataSet(value = "datasets/setup/order.yml")
    @ExpectedDataSet(value = "datasets/expected/order-updated.yml")
    void 受注IDと宛名と但書で受注を更新した場合受注が返る() {
        // execute & assert
        assertThatCode(() -> sut.updateOrderForReceipt(new Order("sample", 10000, "test name", "test product")))
                .doesNotThrowAnyException();
    }
}
