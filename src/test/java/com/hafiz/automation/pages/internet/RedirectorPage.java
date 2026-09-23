package com.hafiz.automation.pages.internet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /redirector. The link 302-redirects to /status_codes. */
public class RedirectorPage extends BasePage {

    @FindBy(linkText = "redirected")
    private WebElement redirectLink;

    public RedirectorPage open() {
        open(Configuration.theInternetBaseUrl() + "/redirector");
        visible(redirectLink);
        return this;
    }

    public RedirectorPage followRedirect() {
        click(redirectLink);
        return this;
    }

    public boolean redirectedToStatusCodes() {
        return currentUrl().contains("/status_codes");
    }
}
