package com.scamshield.analyze;

/**
 * Strategy interface: one goal ("is this message a scam?"), many ways to do it.
 * Rule-based today, AI-based in Week 5. Callers never change when a new detector is added.
 */
public interface ScamDetector {

    ScamResult analyze(String message);
}
