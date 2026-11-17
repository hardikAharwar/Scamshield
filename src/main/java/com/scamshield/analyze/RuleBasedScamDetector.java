package com.scamshield.analyze;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

/**
 * Strategy #1: keyword rules. Fast, free and always available, but not very smart.
 * Later it becomes the fallback when the AI detector is slow or down.
 */
@Component
public class RuleBasedScamDetector implements ScamDetector {

    static final String SOURCE = "RULES";

    /** Two or more red flags in one message = scam. */
    private static final int SCAM_THRESHOLD = 2;

    private static final List<String> RED_FLAGS = List.of(
            "otp", "kyc", "lottery", "you have won", "prize", "urgent", "immediately",
            "blocked", "suspended", "disconnected", "refund", "cashback", "click here",
            "bit.ly", "tinyurl", "work from home", "earn daily", "verify your account");

    @Override
    public ScamResult analyze(String message) {
        String text = message.toLowerCase(Locale.ROOT);

        List<String> found = RED_FLAGS.stream()
                .filter(text::contains)
                .toList();

        return new ScamResult(found.size() >= SCAM_THRESHOLD, found, SOURCE);
    }
}
