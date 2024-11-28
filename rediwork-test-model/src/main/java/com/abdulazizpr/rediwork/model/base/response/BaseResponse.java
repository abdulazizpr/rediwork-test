package com.abdulazizpr.rediwork.model.base.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private int code;
    private String message;
    private T data;
    private Map<String, String> errors;

    //Constructor with Data
    public BaseResponse(int code, String message, T data) {
        this(code, message, data, null);
    }

    //Constructor with Errors
    public BaseResponse(int code, String message, Map<String, String> errors) {
        this(code, message, null, errors);
    }

    //Constructor only with code and message
    public BaseResponse(int code, String message) {
        this(code, message, null, null);
    }
}
