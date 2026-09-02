package com.hafiz.automation.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.hafiz.automation.config.Configuration;

/**
 * Creates a {@link WebDriver} per thread and hands it out through a {@link ThreadLocal},
 * so TestNG can run classes/methods in parallel without sharing a session.
 *
 * <p>Selenium Manager (bundled with Selenium 4.6+) resolves the driver binary, so there
 * is nothing to download or commit.
 */
public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static WebDriver get() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("Driver not initialised for this thread");
        }
        return driver;
    }

    static {
        // Pin a cached chromedriver if one is available, so ChromeDriver never
        // has to shell out to Selenium Manager during a parallel run.
        if (System.getProperty("webdriver.chrome.driver") == null) {
            LocalDriverCache.newestChromedriver()
                    .ifPresent(path -> System.setProperty("webdriver.chrome.driver", path));
        }
    }

    public static void create() {
        DRIVER.set(build());
        get().manage().timeouts().pageLoadTimeout(Configuration.pageLoadTimeout());
    }

    public static void quit() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }

    private static WebDriver build() {
        String browser = Configuration.browser().toLowerCase();
        String remoteUrl = Configuration.remoteUrl();

        return switch (browser) {
            case "chrome", "chromium" -> remoteUrl.isBlank()
                    ? new ChromeDriver(chromeOptions())
                    : remote(remoteUrl, chromeOptions());
            case "firefox", "ff" -> remoteUrl.isBlank()
                    ? new FirefoxDriver(firefoxOptions())
                    : remote(remoteUrl, firefoxOptions());
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    private static ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (Configuration.headless()) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1920,1080", "--no-sandbox", "--disable-dev-shm-usage",
                "--disable-gpu");
        // the-internet embeds third-party analytics that can keep both `load` and
        // even DOMContentLoaded pending indefinitely. NONE hands control back as
        // soon as navigation starts; every Page Object's open() then waits on its
        // own landmark element, which is the reliable readiness signal anyway.
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        options.setExperimentalOption("excludeSwitches", java.util.List.of("enable-automation"));
        return options;
    }

    private static FirefoxOptions firefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (Configuration.headless()) {
            options.addArguments("-headless");
        }
        options.addArguments("--width=1920", "--height=1080");
        return options;
    }

    private static WebDriver remote(String url, org.openqa.selenium.MutableCapabilities caps) {
        try {
            return new RemoteWebDriver(new URL(url), caps);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid remote.url: " + url, e);
        }
    }
}
