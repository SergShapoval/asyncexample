package com.example.async.asyncexample.dto;

import java.io.Serializable;
import java.util.Map;

public class RESTResponse<T> implements Serializable {
    private T result;
    private Map<String, String> errors;

    public RESTResponse() {
    }

    public RESTResponse(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
