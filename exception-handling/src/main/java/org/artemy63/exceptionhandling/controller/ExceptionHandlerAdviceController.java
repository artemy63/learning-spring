package org.artemy63.exceptionhandling.controller;

import org.artemy63.exceptionhandling.dto.ErrorResponseDTO;
import org.artemy63.exceptionhandling.exception.HandledGloballyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionHandlerAdviceController {

    @ExceptionHandler(HandledGloballyException.class)
    public ErrorResponseDTO handleExceptionGlobally(HandledGloballyException e) {
        return ErrorResponseDTO.builder()
                .message(e.getMessage())
                .sourceClass(e.getProperty())
                .build();
    }
}
