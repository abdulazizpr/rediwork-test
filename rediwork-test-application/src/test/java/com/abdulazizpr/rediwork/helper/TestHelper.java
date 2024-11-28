package com.abdulazizpr.rediwork.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TestHelper {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public static String readJsonContent(String filePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(filePath);
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

    public static <T> T readJsonObject(String filePath, Class<T> clazz) throws IOException {
        String jsonContent = readJsonContent(filePath);
        return objectMapper.readValue(jsonContent, clazz);
    }

    public static <T> List<T> readJsonArray(String filePath, Class<T> clazz) throws IOException {
        String jsonContent = readJsonContent(filePath);
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return objectMapper.readValue(jsonContent, listType);
    }
}