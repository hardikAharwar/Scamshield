package com.scamshield.analyze;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Request body for POST /v1/analyze/message. */
public record AnalyzeMessageRequest(
        @NotBlank(message = "text must not be empty")
        @Size(max = 2000, message = "text must be at most 2000 characters")
        String text) {
}
