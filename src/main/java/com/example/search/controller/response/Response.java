package com.example.search.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {

    private String resultCode;
    private String resultMessage;
    private T result;

    public static <T> Response<T> success(T result) {
        return new Response<>("SUCCESS", null, result);
    }

    public static Response<Void> error(String resultCode) {
        return new Response<>("ERROR", resultCode, null);
    }
}
