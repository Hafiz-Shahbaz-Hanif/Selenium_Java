package com.hafiz.automation.pages.internet;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/**
 * the-internet &rarr; /dynamic_loading/{1,2}.
 * Example 1 hides the element; example 2 renders it only after the AJAX call.
 */
public class DynamicLoadingPage extends BasePage {

    @FindBy(css = "#start button")
    private WebElement startButton;

    @FindBy(css = "#finish h4")
    private WebElement finishText;

    public DynamicLoadingPage open(int example) {
        open(Configuration.theInternetBaseUrl() + "/dynamic_loading/" + example);
        visible(startButton);
        return this;
    }

    public DynamicLoadingPage start() {
        click(startButton);
        return this;
    }

    public String loadedText() {
        return new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(finishText))
                .getText()
                .trim();
    }
}
