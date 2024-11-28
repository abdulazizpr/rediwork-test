package com.abdulazizpr.rediwork.integration.fruits;

import com.abdulazizpr.rediwork.application.RediworkApplication;
import com.abdulazizpr.rediwork.entity.Fruits;
import com.abdulazizpr.rediwork.helper.TestHelper;
import com.abdulazizpr.rediwork.repository.FruitsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.util.List;

@SpringBootTest(
        classes = RediworkApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.config.name=application-test"}
)
@AutoConfigureWebTestClient
public class  DeleteFruitTest {
    private static final String FRUIT_ID_FOUND = "c641e63f-fd16-48a2-9db9-7b0699f9cdff";

    private static final String FRUIT_NOT_FOUND = "a672e63f-fd16-48a2-9db9-7b2399f9cdfa";

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private FruitsRepository repository;

    @BeforeEach
    public void setUp() throws IOException {
        repository.deleteAll()
                .block();

        List<Fruits> fruits = TestHelper.readJsonArray(
                "api/database/fruits/fruits.json",
                Fruits.class
        );

        repository.saveAll(fruits)
                .blockLast();
    }

    @Test
    public void test_delete_fruit_success_200() throws IOException {
        String expectedResponse = TestHelper
                .readJsonContent("api/spec/fruits/delete-fruit/delete-fruit-success-200.json");

        webTestClient.delete()
                .uri("/api/v1/fruits/" + FRUIT_ID_FOUND)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json(expectedResponse);
    }

    @Test
    public void test_delete_fruit_error_400() throws IOException {
        String expectedResponse = TestHelper
                .readJsonContent("api/spec/fruits/delete-fruit/delete-fruit-error-400.json");

        webTestClient.delete()
                .uri("/api/v1/fruits/" + FRUIT_NOT_FOUND)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json(expectedResponse);
    }
}
