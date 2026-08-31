package com.hafiz.automation.utils;

import java.util.UUID;

/** Small helpers for generating unique-per-run test data. */
public final class TestData {

    private TestData() {
    }

    public static String uniqueUsername(String prefix) {
        return prefix + "_" + UUID.randomUUID().toString().substring(0, 8);
    }
}
