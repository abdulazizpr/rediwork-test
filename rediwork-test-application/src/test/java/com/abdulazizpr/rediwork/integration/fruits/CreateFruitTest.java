package com.abdulazizpr.rediwork.integration.fruits;

import com.abdulazizpr.rediwork.application.RediworkApplication;
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

@SpringBootTest(
        classes = RediworkApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.config.name=application-test"}
)
@AutoConfigureWebTestClient
public class CreateFruitTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private FruitsRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll()
                .block();
    }

    @Test
    public void test_create_fruit_success_200() throws IOException {
        String expectedResponse = TestHelper
                .readJsonContent("api/spec/fruits/create-fruit/create-fruit-success-200.json");

        SaveFruitRequest request = new SaveFruitRequest();
        request.setName("Apple");
        request.setDescription("This is an apple");
        request.setStock(BigInteger.TEN);

        webTestClient.post()
                .uri("/api/v1/fruits")
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
