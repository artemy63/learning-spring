package org.artemy63.exceptionhandling.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class SuccessResponseDTO {
    String name;
    Map<String, String> properties;
    InnerSuccessResponseDTO data;

    @Data
    @Builder
    public static class InnerSuccessResponseDTO {
        String name;
        List<String> values;
    }
}
