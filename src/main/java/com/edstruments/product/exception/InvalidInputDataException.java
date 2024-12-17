package com.edstruments.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputDataException extends RuntimeException{

    private static final long serialVersionUID=1L;

    private List<String> exceptionList;
    public InvalidInputDataException(String message) {
        super(message);
    }

    public InvalidInputDataException(String message,List<String> exceptionList) {
        super(message);
        this.exceptionList=exceptionList;
    }

    public List<String> getExceptionList() {
        return exceptionList;
    }

    public void setExceptionList(List<String> exceptionList) {
        this.exceptionList = exceptionList;
    }
}
