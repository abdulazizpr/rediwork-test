package com.abdulazizpr.rediwork.integration.fruits;

import com.abdulazizpr.rediwork.application.RediworkApplication;
import com.abdulazizpr.rediwork.entity.Fruits;
import com.abdulazizpr.rediwork.helper.TestHelper;
import com.abdulazizpr.rediwork.model.base.request.fruit.SaveFruitRequest;
import com.abdulazizpr.rediwork.repository.FruitsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@SpringBootTest(
        classes = RediworkApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.config.name=application-test"}
)
@AutoConfigureWebTestClient
public class UpdateFruitTest {
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
    public void test_update_fruit_success_200() throws IOException {
        String expectedResponse = TestHelper
                .readJsonContent("api/spec/fruits/update-fruit/update-fruit-success-200.json");

        SaveFruitRequest request = new SaveFruitRequest();
        request.setName("Apple");
        request.setDescription("This is an apple");
        request.setStock(BigInteger.TEN);

        webTestClient.put()
                .uri("/api/v1/fruits/" + FRUIT_ID_FOUND)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), SaveFruitRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json(expectedResponse);
    }

    @Test
    public void test_update_fruit_success_400() throws IOException {
        String expectedResponse = TestHelper
                .readJsonContent("api/spec/fruits/update-fruit/update-fruit-error-400.json");

        SaveFruitRequest request = new SaveFruitRequest();
        request.setName("Apple");
        request.setDescription("This is an apple");
        request.setStock(BigInteger.TEN);

        webTestClient.put()
                .uri("/api/v1/fruits/" + FRUIT_NOT_FOUND)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), SaveFruitRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json(expectedResponse);
    }
}
