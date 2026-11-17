package com.scamshield.analyze;

import java.util.List;

/**
 * Immutable result of a scam check.
 *
 * @param isScam   final verdict
 * @param redFlags which warning signs were found
 * @param source   which detector produced this result (e.g. "RULES", later "AI")
 */
public record ScamResult(boolean isScam, List<String> redFlags, String source) {
}
