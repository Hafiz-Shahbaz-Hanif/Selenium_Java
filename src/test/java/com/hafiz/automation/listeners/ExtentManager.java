package com.hafiz.automation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/** Lazily-created singleton {@link ExtentReports} instance. */
public final class ExtentManager {

    private static final String REPORT_PATH = "target/extent-report/index.html";
    private static ExtentReports extent;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter(REPORT_PATH);
            reporter.config().setTheme(Theme.DARK);
            reporter.config().setDocumentTitle("Selenium Java TestNG - Automation Report");
            reporter.config().setReportName("UI Regression");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Framework", "Selenium + Java + TestNG (POM / PageFactory)");
            extent.setSystemInfo("Browser", com.hafiz.automation.config.Configuration.browser());
            extent.setSystemInfo("Headless", String.valueOf(
                    com.hafiz.automation.config.Configuration.headless()));
        }
        return extent;
    }
}
