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
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.util.List;

@SpringBootTest(
        classes = RediworkApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.config.name=application-test"}
)
@AutoConfigureWebTestClient
public class GetAllFruitsTest {
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
    public void test_get_all_fruits_with_page1_size5_success_200_with_data() throws IOException {
        String expectedResponse = TestHelper
                .readJsonContent("api/spec/fruits/get-all-fruits/fruits-page1-size5-response-200.json");

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/fruits")
                        .queryParam("page", 1)
                        .queryParam("size", 5)
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json(expectedResponse);
    }

    @Test
    public void test_get_all_fruits_with_page2_size5_success_200_with_data() throws IOException {
        String expectedResponse = TestHelper
                .readJsonContent("api/spec/fruits/get-all-fruits/fruits-page2-size5-response-200.json");

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/fruits")
                        .queryParam("page", 2)
                        .queryParam("size", 5)
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json(expectedResponse);
    }

    @Test
    public void test_get_all_fruits_with_page3_size5_success_200_with_data() throws IOException {
        String expectedResponse = TestHelper
                .readJsonContent("api/spec/fruits/get-all-fruits/fruits-page3-size5-response-200.json");

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/fruits")
                        .queryParam("page", 3)
                        .queryParam("size", 5)
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                 .json(expectedResponse);
    }

    @Test
    public void test_get_all_fruits_with_page1_size5_with_search_name_success_200_with_data() throws IOException {
        String expectedResponse = TestHelper
                .readJsonContent("api/spec/fruits/get-all-fruits/fruits-page1-size5-with-search-name-response-200.json");

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/fruits")
                        .queryParam("page", 1)
                        .queryParam("size", 5)
                        .queryParam("name", "kopi")
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json(expectedResponse);
    }

    @Test
    public void test_get_all_fruits_with_page1_size5_with_search_name_success_200_with_no_data() throws IOException {
        String expectedResponse = TestHelper
                .readJsonContent("api/spec/fruits/get-all-fruits/fruits-empty-data-response-200.json");

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/fruits")
                        .queryParam("page", 1)
                        .queryParam("size", 5)
                        .queryParam("name", "bajigur")
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json(expectedResponse);
    }

    @Test
    public void test_get_all_fruits_with_page4_size5_success_200_with_no_data() throws IOException {
        String expectedResponse = TestHelper
                .readJsonContent("api/spec/fruits/get-all-fruits/fruits-empty-data-response-200.json");

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/fruits")
                        .queryParam("page", 4)
                        .queryParam("size", 5)
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json(expectedResponse);
    }
}
