package com.caliope.data.error;

import org.springframework.http.HttpStatus;

public class InfoEntity {

    private HttpStatus status;
    private String message;

    public InfoEntity(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
