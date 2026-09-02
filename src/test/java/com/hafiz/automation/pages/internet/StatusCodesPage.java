package com.hafiz.automation.pages.internet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /status_codes. */
public class StatusCodesPage extends BasePage {

    @FindBy(css = ".example p")
    private WebElement message;

    public StatusCodesPage open() {
        open(Configuration.theInternetBaseUrl() + "/status_codes");
        visible(message);
        return this;
    }

    public StatusCodesPage openCode(int code) {
        open(Configuration.theInternetBaseUrl() + "/status_codes/" + code);
        visible(message);
        return this;
    }

    /** The page echoes the status code it was served. */
    public String reportedCode() {
        String text = textOf(message);
        return text.replaceAll("\\D+", "").trim();
    }
}
