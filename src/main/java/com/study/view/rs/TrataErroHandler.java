package com.study.view.rs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class TrataErroHandler {
          
    @ExceptionHandler(value=ClassNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(final ClassNotFoundException exception){
        return ResponseEntity.ok(exception.getMessage());
    }
 

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleConstraintViolationException(final MethodArgumentNotValidException exception){
        log.error(exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
