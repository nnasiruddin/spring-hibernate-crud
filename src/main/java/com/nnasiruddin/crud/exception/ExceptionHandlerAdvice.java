package com.nnasiruddin.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<ExceptionStatus> handleException(ResourceException e) {
        // log exception
        return new ResponseEntity<ExceptionStatus>(new ExceptionStatus("We were unable to find the specified resource."), HttpStatus.NOT_FOUND);
    }
}
