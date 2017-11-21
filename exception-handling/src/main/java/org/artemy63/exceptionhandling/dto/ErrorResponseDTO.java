package org.artemy63.exceptionhandling.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDTO {
    String message;
    String sourceClass;
}
