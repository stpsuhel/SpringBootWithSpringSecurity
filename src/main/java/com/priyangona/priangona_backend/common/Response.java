package com.priyangona.priangona_backend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private int statusCode;
    private String message;
    private boolean isSuccess;
    private T data;
    private Object errors;
}
