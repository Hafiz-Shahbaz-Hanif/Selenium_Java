package com.hafiz.automation.driver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Finds a chromedriver that Selenium Manager (or a previous run) already cached
 * under {@code ~/.cache/selenium/chromedriver}, so the suite does not have to
 * invoke Selenium Manager per session - it can stall under parallel first use on
 * some Windows setups.
 *
 * <p>On a clean machine (e.g. CI) the cache is empty and this returns nothing;
 * {@code BaseTest} has already run WebDriverManager to populate it and pin the
 * {@code webdriver.chrome.driver} property.
 */
final class LocalDriverCache {

    private static final Path CACHE_ROOT = Paths.get(
            System.getProperty("user.home"), ".cache", "selenium", "chromedriver");

    private LocalDriverCache() {
    }

    static Optional<String> newestChromedriver() {
        String exe = isWindows() ? "chromedriver.exe" : "chromedriver";
        if (!Files.isDirectory(CACHE_ROOT)) {
            return Optional.empty();
        }
        try (Stream<Path> platforms = Files.list(CACHE_ROOT)) {
            return platforms
                    .flatMap(LocalDriverCache::listVersionDirs)
                    .map(dir -> dir.resolve(exe))
                    .filter(Files::isRegularFile)
                    .max(Comparator.comparing(p -> versionKey(p.getParent().getFileName().toString())))
                    .map(Path::toString);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private static Stream<Path> listVersionDirs(Path platform) {
        try {
            return Files.list(platform).filter(Files::isDirectory);
        } catch (IOException e) {
            return Stream.empty();
        }
    }

    /** Zero-pads each numeric segment so plain string comparison orders versions. */
    private static String versionKey(String version) {
        StringBuilder key = new StringBuilder();
        for (String part : version.split("\\.")) {
            key.append(String.format("%010d.", parseIntSafe(part)));
        }
        return key.toString();
    }

    private static int parseIntSafe(String s) {
        try {
            return Integer.parseInt(s.replaceAll("\\D", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static boolean isWindows() {
        return System.getProperty("os.name", "").toLowerCase().contains("win");
    }
}
