package com.hafiz.automation.pages.internet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /key_presses. */
public class KeyPressesPage extends BasePage {

    @FindBy(id = "target")
    private WebElement input;

    @FindBy(id = "result")
    private WebElement result;

    public KeyPressesPage open() {
        open(Configuration.theInternetBaseUrl() + "/key_presses");
        visible(input);
        return this;
    }

    public String pressAndReadResult(CharSequence key) {
        input.sendKeys(key);
        return textOf(result);
    }
}
