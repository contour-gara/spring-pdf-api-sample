package org.contourgara.infrastructure.order;

import org.contourgara.domain.model.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderEntityTest {
    @Test
    void ドメインモデルに変換できる() {
        // setup
        OrderEntity sut = new OrderEntity(1, "sample", 10000, "", "");

        // execute
        Order actual = sut.convertToModel();

        // assert
        Order expected = new Order("sample", 10000, "", "");
        assertThat(actual).isEqualTo(expected);
    }
}
