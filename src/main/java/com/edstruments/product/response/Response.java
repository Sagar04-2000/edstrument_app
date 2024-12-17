package com.edstruments.product.response;

import org.springframework.http.HttpStatus;

/**
 * author : Sagar Wagh
 */

/**
 * Base class of All response classes
 */
public class Response {

    String Message;

    HttpStatus httpStatusCode;

    HttpStatus httpStatusMessage;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(HttpStatus httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatus getHttpStatusMessage() {
        return httpStatusMessage;
    }

    public void setHttpStatusMessage(HttpStatus httpStatusMessage) {
        this.httpStatusMessage = httpStatusMessage;
    }
}
