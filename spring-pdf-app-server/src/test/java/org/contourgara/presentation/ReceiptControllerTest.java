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

@WebMvcTest(controllers = ReceiptController.class)
class ReceiptControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CreateReceiptUseCase createReceiptUseCase;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void 領収書発行エンドポイントにPOSTした場合200と領収書が返る() {
        // setup
        doReturn(new byte[0]).when(createReceiptUseCase).execute("sample", "test name", "product name");

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
                .statusCode(200)
                .header("Content-Type", "application/pdf")
                .body(notNullValue());
    }
}
