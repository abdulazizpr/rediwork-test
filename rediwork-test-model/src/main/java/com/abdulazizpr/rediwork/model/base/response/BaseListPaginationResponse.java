package com.abdulazizpr.rediwork.model.base.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseListPaginationResponse<T> {
    private int code;
    private String message;
    private List<String> errors;
    private List<T> data;
    private int currentPage;
    private int currentSize;
    private int totalPages;
    private Long totalItems;

    // Constructor with Data and Pagination
    public BaseListPaginationResponse(int code, String message, List<T> data, int currentPage, int totalPages, long totalItems) {
        this(code, message, null, data, currentPage, data.size(), totalPages, totalItems);
    }
}
