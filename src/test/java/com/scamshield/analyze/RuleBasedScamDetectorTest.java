package com.scamshield.analyze;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RuleBasedScamDetectorTest {

    private final ScamDetector detector = new RuleBasedScamDetector();

    @Test
    void flagsKycOtpScam() {
        ScamResult result = detector.analyze(
                "Dear customer your KYC is pending, share OTP urgently to avoid block");

        assertThat(result.isScam()).isTrue();
        assertThat(result.redFlags()).contains("kyc", "otp", "urgent");
        assertThat(result.source()).isEqualTo("RULES");
    }

    @Test
    void ignoresNormalMessage() {
        ScamResult result = detector.analyze("Hey, are we meeting at 6?");

        assertThat(result.isScam()).isFalse();
        assertThat(result.redFlags()).isEmpty();
    }

    @Test
    void singleRedFlagIsNotEnough() {
        ScamResult result = detector.analyze("Your OTP for login is 4821. Do not share it.");

        assertThat(result.isScam()).isFalse();
        assertThat(result.redFlags()).containsExactly("otp");
    }

    @Test
    void isCaseInsensitive() {
        ScamResult result = detector.analyze("YOU HAVE WON A LOTTERY PRIZE!!!");

        assertThat(result.isScam()).isTrue();
    }
}
