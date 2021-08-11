package com.priyangona.priangona_backend.common;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.util.List;

public class ResponseObject {

    public static <T> Response<T> bindingResultErrorResponse(Response<T> response, List<ObjectError> errors){
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setSuccess(false);
        response.setMessage("Data is Corrupted!");
        response.setErrors(errors);
        return response;
    }

    public static <T> Response<T> createSuccessResponse(Response<T> response, T data){
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setSuccess(true);
        response.setMessage("Successfully Added!");
        response.setData(data);
        return response;
    }

    public static <T> Response<T> updateSuccessResponse(Response<T> response, T data){
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setSuccess(true);
        response.setMessage("Successfully Updated!");
        response.setData(data);
        return response;
    }

    public static <T> Response<T> noIdFoundResponse(Response<T> response){
        response.setSuccess(false);
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Id cannot be null or less then zero!");
        return response;
    }

    public static <T> Response<T> noDataFoundResponse(Response<T> response){
        response.setSuccess(true);
        response.setStatusCode(HttpStatus.NO_CONTENT.value());
        response.setMessage("No Data Found!");
        return response;
    }

    public static <T> Response<T> dataFoundSuccessResponse(Response<T> response, T data){
        response.setSuccess(true);
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Data Found!");
        response.setData(data);
        return response;
    }

    public static Response<Boolean> dataDeletedSuccessResponse(Response<Boolean> response){
        response.setSuccess(true);
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Data deleted successfully!");
        response.setData(true);
        return response;
    }
}
