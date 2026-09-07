package com.hafiz.automation.pages.internet;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/**
 * the-internet &rarr; /infinite_scroll. Fresh blocks of text are appended
 * (class {@code jscroll-added}) each time the viewport nears the bottom.
 */
public class InfiniteScrollPage extends BasePage {

    @FindBy(css = ".jscroll-added")
    private List<WebElement> appendedBlocks;

    public InfiniteScrollPage open() {
        open(Configuration.theInternetBaseUrl() + "/infinite_scroll");
        return this;
    }

    public int appendedBlockCount() {
        return appendedBlocks.size();
    }

    public InfiniteScrollPage scrollToBottom() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");
        return this;
    }

    /** Wait until the page has appended more than {@code count} blocks. */
    public InfiniteScrollPage waitForMoreThan(int count) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> appendedBlocks.size() > count);
        return this;
    }
}
