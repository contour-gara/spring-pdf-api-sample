package org.contourgara.domain.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class OrderTest {
    @Test
    void 宛名と但書が空文字でもインスタンスを作成できる() {
        assertThatCode(() -> new Order("sample", 10000, "", ""))
                .doesNotThrowAnyException();
    }

    @Nested
    class 受注IDが不適の場合例外が飛ぶ {
        @Test
        void 受注IDがnull() {
            // execute & assert
            assertThatThrownBy(() -> new Order(null, 10000, "test name", "product name"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("受注 ID が null であってはならない。");
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "a 1", "あ"})
        void 受注IDがアルファベットと数字以外(String orderId) {
            // execute & assert
            assertThatThrownBy(() -> new Order(orderId, 10000, "test name", "product name"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("受注 ID がアルファベットと数字以外であってはならない。[orderId = %s]".formatted(orderId));
        }
    }

    @Nested
    class 金額が不適の場合例外が飛ぶ {
        @Test
        void 金額がnull() {
            // execute & assert
            assertThatThrownBy(() -> new Order("sample", null, "test name", "product name"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("金額が null であってはならない。");
        }

        @Test
        void 金額が負の値() {
            // execute & assert
            assertThatThrownBy(() -> new Order("sample", -10000, "test name", "product name"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("金額が負の値であってはならない。[amount = -10000]");
        }
    }

    @Nested
    class 宛名が不適の場合例外が飛ぶ {
        @Test
        void 宛名がnull() {
            // execute & assert
            assertThatThrownBy(() -> new Order("sample", 10000, null, "product name"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("宛名が null であってはならない。");
        }

        @ParameterizedTest
        @ValueSource(strings = {" ", "  ", "　",  "　　"})
        void 宛名がスペースのみ(String recipientName) {
            // execute & assert
            assertThatThrownBy(() -> new Order("sample", 10000, recipientName, "product name"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("宛名がスペースであってはならない。");
        }

        @ParameterizedTest
        @ValueSource(strings = {"テスト", "bob/"})
        void 宛名がアルファベットと数字と半角スペース以外(String recipientName) {
            // execute & assert
            assertThatThrownBy(() -> new Order("sample", 10000, recipientName, "product name"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("宛名がアルファベットと半角スペース以外であってはならない。[recipientName = %s]".formatted(recipientName));
        }
    }

    @Nested
    class 但書が不適の場合例外が飛ぶ {
        @Test
        void 但書がnull() {
            // execute & assert
            assertThatThrownBy(() -> new Order("sample", 10000, "test name", null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("但書が null であってはならない。");
        }

        @ParameterizedTest
        @ValueSource(strings = {" ", "  ", "　",  "　　"})
        void 但書がスペースのみ(String remarks) {
            // execute & assert
            assertThatThrownBy(() -> new Order("sample", 10000, "test name", remarks))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("但書がスペースであってはならない。");
        }

        @Test
        void 但書がアルファベットと数字と半角スペース以外() {
            // execute & assert
            assertThatThrownBy(() -> new Order("sample", 10000, "test name", "但書"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("但書が半角文字以外であってはならない。[remarks = 但書]");
        }
    }
}
