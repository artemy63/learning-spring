package org.artemy63.exceptionhandling.controller;

import org.artemy63.exceptionhandling.dto.SuccessResponseDTO;
import org.artemy63.exceptionhandling.exception.HandledException;
import org.artemy63.exceptionhandling.exception.HandledGloballyException;
import org.artemy63.exceptionhandling.exception.UnHandledException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/exception-handling/v2")
public class ExceptionHandlingV2Controller {

    @RequestMapping("/give-me-handled-exception")
//    @ResponseBody
    public SuccessResponseDTO giveMeHandledException(@RequestParam(required = false) String isThrowException) {
        if(Boolean.parseBoolean(isThrowException)) {
            throw new HandledException("ExceptionHandlingV2Controller#HandledException occurs :: ", "ExceptionHandlingV1Controller should handle it!");
        }
        return SuccessResponseDTO.builder()
                .name("give-me-success")
                .data(SuccessResponseDTO.InnerSuccessResponseDTO.builder()
                        .name("success name")
                        .values(Collections.singletonList("one success value"))
                        .build())
                .build();
    }

    @RequestMapping("/give-me-handled-globally-exception")
//    @ResponseBody
    public SuccessResponseDTO giveMeHandledGloballyException(@RequestParam(required = false) String isThrowException) {
        if(Boolean.parseBoolean(isThrowException)) {
            throw new HandledGloballyException("ExceptionHandlingV2Controller#HandledGloballyException occurs :: ", this.getClass().getName());
        }
        return SuccessResponseDTO.builder()
                .name("give-me-success")
                .data(SuccessResponseDTO.InnerSuccessResponseDTO.builder()
                        .name("success name")
                        .values(Collections.singletonList("one success value"))
                        .build())
                .build();
    }

    @RequestMapping("/give-me-unhandled-exception")
//    @ResponseBody
    public SuccessResponseDTO giveMeUnHandledException(@RequestParam(required = false) String isThrowException) {
        if(Boolean.parseBoolean(isThrowException)) {
            throw new UnHandledException("ExceptionHandlingV2Controller#UnHandledException occurs ::", "ExceptionHandling21Controller should not handle it!");
        }
        return SuccessResponseDTO.builder()
                .name("give-me-success")
                .data(SuccessResponseDTO.InnerSuccessResponseDTO.builder()
                        .name("success name")
                        .values(Collections.singletonList("one success value"))
                        .build())
                .build();
    }
}
