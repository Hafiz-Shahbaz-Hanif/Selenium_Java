package com.hafiz.automation.config;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

/**
 * Typed access to configuration.
 *
 * <p>Resolution order for every key: JVM system property (e.g. {@code -Dbrowser=firefox})
 * &rarr; environment variable &rarr; {@code config.properties} &rarr; built-in default.
 */
public final class Configuration {

    private static final Properties FILE_PROPS = load();

    private Configuration() {
    }

    private static Properties load() {
        Properties props = new Properties();
        try (InputStream in = Configuration.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read config.properties", e);
        }
        return props;
    }

    private static String resolve(String key, String defaultValue) {
        String fromSystem = System.getProperty(key);
        if (fromSystem != null && !fromSystem.isBlank()) {
            return fromSystem;
        }
        String fromEnv = System.getenv(key.toUpperCase().replace('.', '_'));
        if (fromEnv != null && !fromEnv.isBlank()) {
            return fromEnv;
        }
        return FILE_PROPS.getProperty(key, defaultValue);
    }

    public static String browser() {
        return resolve("browser", "chrome");
    }

    public static boolean headless() {
        return Boolean.parseBoolean(resolve("headless", "true"));
    }

    public static String remoteUrl() {
        return resolve("remote.url", "");
    }

    public static Duration explicitWait() {
        return Duration.ofSeconds(Long.parseLong(resolve("wait.explicit.seconds", "15")));
    }

    public static Duration pageLoadTimeout() {
        return Duration.ofSeconds(Long.parseLong(resolve("wait.pageload.seconds", "40")));
    }

    public static String theInternetBaseUrl() {
        return resolve("the-internet.url", "https://the-internet.herokuapp.com");
    }

    public static String paraBankBaseUrl() {
        return resolve("parabank.url", "https://parabank.parasoft.com/parabank");
    }
}
