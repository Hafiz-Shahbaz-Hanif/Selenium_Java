package com.hafiz.automation.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.hafiz.automation.driver.DriverFactory;
import com.hafiz.automation.listeners.TestListener;

/**
 * Common lifecycle for every test class: a fresh, thread-local WebDriver per
 * test method (so TestNG can parallelise), torn down afterwards.
 */
@Listeners(TestListener.class)
public abstract class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.create();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quit();
    }
}
