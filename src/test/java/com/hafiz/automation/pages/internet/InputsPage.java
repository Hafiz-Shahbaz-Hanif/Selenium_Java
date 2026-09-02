package com.hafiz.automation.pages.internet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /inputs (a single number input). */
public class InputsPage extends BasePage {

    @FindBy(css = "input[type='number']")
    private WebElement numberInput;

    public InputsPage open() {
        open(Configuration.theInternetBaseUrl() + "/inputs");
        visible(numberInput);
        return this;
    }

    public String enterAndReadBack(String value) {
        numberInput.clear();
        numberInput.sendKeys(value);
        return numberInput.getDomProperty("value");
    }
}
