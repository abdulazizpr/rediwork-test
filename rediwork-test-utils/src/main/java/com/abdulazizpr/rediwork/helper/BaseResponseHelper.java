package com.abdulazizpr.rediwork.helper;

import com.abdulazizpr.rediwork.model.base.response.BaseResponse;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class BaseResponseHelper {
    //HTTP Status 200 - Success
    public static <T> BaseResponse<T> success(T data, String message) {
        return new BaseResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    public static <T> BaseResponse<T> success(T data) {
        return success(data, HttpStatus.OK.getReasonPhrase());
    }

    public static <T> BaseResponse<T> success(String message) {
        return success(null, message);
    }

    public static <T> BaseResponse<T> success() {
        return success(null, null);
    }

    //HTTP Status 400 - Bad Request
    public static <T> BaseResponse<T> badRequest(String message, Map<String, String> errors) {
        return new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), message, errors);
    }

    public static <T> BaseResponse<T> badRequest(String error) {
        return badRequest(error, null);
    }

    public static <T> BaseResponse<T> badRequest(Map<String, String> errors) {
        return badRequest(HttpStatus.BAD_REQUEST.getReasonPhrase(), errors);
    }

    public static <T> BaseResponse<T> badRequest() {
        return badRequest(null, null);
    }

    //HTTP Status 500 - Internal Server Error
    public static <T> BaseResponse<T> internalServerError(Map<String, String> errors, String message) {
        return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, errors);
    }

    public static <T> BaseResponse<T> internalServerError(Map<String, String> errors) {
        return internalServerError(errors, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    public static <T> BaseResponse<T> internalServerError(String error) {
        return internalServerError(null, error);
    }

    public static <T> BaseResponse<T> internalServerError() {
        return internalServerError(null, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
}
