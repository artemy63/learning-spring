package org.artemy63.exceptionhandling.controller;

import org.artemy63.exceptionhandling.dto.SuccessResponseDTO;
import org.artemy63.exceptionhandling.exception.HandledException;
import org.artemy63.exceptionhandling.exception.UnHandledException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/exception-handling/v1")
public class ExceptionHandlingV1Controller {

    @RequestMapping("/give-me-success")
    @ResponseBody
    public SuccessResponseDTO giveMeSuccess() {
        return SuccessResponseDTO.builder()
                .name("give-me-success")
                .data(SuccessResponseDTO.InnerSuccessResponseDTO.builder()
                        .name("success name")
                        .values(Collections.singletonList("one success value"))
                        .build())
                .build();
    }

    @RequestMapping("/give-me-handled-exception")
    @ResponseBody
    public SuccessResponseDTO giveMeHandledException(@RequestParam(required = false) String isThrowException) {
        if(Boolean.parseBoolean(isThrowException)) {
            throw new HandledException("ExceptionHandlingV1Controller#HandledException occurs :: ", "ExceptionHandlingV1Controller should handle it!");
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
    @ResponseBody
    public SuccessResponseDTO giveMeUnHandledException(@RequestParam(required = false) String isThrowException) {
        if(Boolean.parseBoolean(isThrowException)) {
            throw new UnHandledException("ExceptionHandlingV1Controller#UnHandledException occurs ::", "ExceptionHandlingV1Controller should not handle it!");
        }
        return SuccessResponseDTO.builder()
                .name("give-me-success")
                .data(SuccessResponseDTO.InnerSuccessResponseDTO.builder()
                        .name("success name")
                        .values(Collections.singletonList("one success value"))
                        .build())
                .build();
    }

    @ExceptionHandler(HandledException.class)
    @ResponseBody
    public SuccessResponseDTO handleException(HandledException e) {
        Map<String, String> properties = new HashMap<>();
        properties.put("class", this.getClass().toString());
        properties.put("handlerType", "@ExceptionHandler");
        return SuccessResponseDTO.builder()
                .name("HandledException has been thrown")
                .properties(properties)
                .build();
    }
}
