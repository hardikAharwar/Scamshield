package com.scamshield.analyze;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/analyze")
public class AnalyzeController {

    // Depends on the interface, not on RuleBasedScamDetector (SOLID "D").
    // Spring injects the only ScamDetector bean that exists today.
    private final ScamDetector scamDetector;

    public AnalyzeController(ScamDetector scamDetector) {
        this.scamDetector = scamDetector;
    }

    @PostMapping("/message")
    public ScamResult analyzeMessage(@Valid @RequestBody AnalyzeMessageRequest request) {
        return scamDetector.analyze(request.text());
    }
}
