package org.contourgara.presentation;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.contourgara.application.CreateReceiptUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest
class GlobalExceptionHandlerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CreateReceiptUseCase createReceiptUseCase;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void バリデーション違反の場合400が返る() {
        // setup
        doThrow(new IllegalArgumentException("宛名がスペースであってはならない。")).when(createReceiptUseCase).execute("sample", " ", "product name");

        // execute & assert
        given()
                .header("Content-Type", "application/json")
                .body("""
                        {
                        "recipientName": " ",
                        "remarks": "product name"
                        }
                        """)
                .when()
                .post("/receipt/sample")
                .then()
                .statusCode(400)
                .header("Content-Type", "application/problem+json")
                .body("detail", equalTo("宛名がスペースであってはならない。"));
    }

    @Test
    void 予期しない例外が発生した場合500が返る() {
        // setup
        doThrow(new RuntimeException("予期しない例外。")).when(createReceiptUseCase).execute("sample", "test name", "product name");

        // execute & assert
        given()
                .header("Content-Type", "application/json")
                .body("""
                        {
                        "recipientName": "test name",
                        "remarks": "product name"
                        }
                        """)
                .when()
                .post("/receipt/sample")
                .then()
                .statusCode(500)
                .header("Content-Type", "application/problem+json")
                .body("detail", equalTo("予期しない例外。"));
    }
}
