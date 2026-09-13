package com.hafiz.automation.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retries a failed test up to {@value #MAX_RETRIES} times. Applied only to the
 * {@code parabank} group by {@link RetryTransformer} - that public demo is
 * periodically reset and occasionally flaky under load; the-internet does not
 * need this and should never silently retry a real failure.
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private static final int MAX_RETRIES = 2;

    private int attempts = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (attempts < MAX_RETRIES) {
            attempts++;
            return true;
        }
        return false;
    }
}
