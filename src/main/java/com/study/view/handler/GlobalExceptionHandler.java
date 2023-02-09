package com.study.view.handler;

import com.study.dto.*;
import lombok.extern.log4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;


@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleConstraintViolationException(final MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());

        return ResponseEntity
                .badRequest()
                .body(ErrorResponseDTO.createFromValidation(exception));
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handleEntityNotFoundException(final EntityNotFoundException exception) {
        log.error(exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorMessageDTO.builder()
                        .message(exception.getMessage())
                        .build());
    }
}