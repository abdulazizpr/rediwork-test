package com.abdulazizpr.rediwork.helper;

import com.abdulazizpr.rediwork.model.base.response.BaseListPaginationResponse;
import org.springframework.http.HttpStatus;

import java.util.List;

public class BaseListPaginationResponseHelper {
    public static <T> BaseListPaginationResponse<T> success(List<T> data, int currentPage, int pageSize, Long totalItems) {
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        return new BaseListPaginationResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                data,
                currentPage,
                totalPages,
                totalItems
        );
    }
}