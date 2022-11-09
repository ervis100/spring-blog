package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIexception extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public BlogAPIexception(String message1, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public BlogAPIexception(String message, Throwable cause, HttpStatus httpStatus, String message1) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
