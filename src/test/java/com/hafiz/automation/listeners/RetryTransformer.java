package com.hafiz.automation.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/**
 * Wires {@link RetryAnalyzer} onto every {@code @Test} tagged {@code parabank},
 * and leaves every other test (the-internet included) alone. Registered in
 * {@code testng.xml} / {@code smoke.xml} alongside {@link TestListener}.
 */
public class RetryTransformer implements IAnnotationTransformer {

    private static final String RETRIED_GROUP = "parabank";

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
            Constructor testConstructor, Method testMethod) {
        for (String group : annotation.getGroups()) {
            if (RETRIED_GROUP.equals(group)) {
                annotation.setRetryAnalyzer(RetryAnalyzer.class);
                return;
            }
        }
    }
}
