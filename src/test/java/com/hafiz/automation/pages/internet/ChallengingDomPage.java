package com.hafiz.automation.pages.internet;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /challenging_dom. */
public class ChallengingDomPage extends BasePage {

    @FindBy(css = ".button")
    private List<WebElement> buttons;

    @FindBy(css = "table thead th")
    private List<WebElement> headers;

    @FindBy(css = "table tbody tr")
    private List<WebElement> rows;

    public ChallengingDomPage open() {
        open(Configuration.theInternetBaseUrl() + "/challenging_dom");
        waitForAll(buttons);
        return this;
    }

    public int buttonCount() {
        return buttons.size();
    }

    public List<String> headerLabels() {
        return textsOf(headers);
    }

    public int rowCount() {
        return rows.size();
    }

    public String canvasId() {
        return attribute(driver.findElement(org.openqa.selenium.By.id("canvas")), "id");
    }
}
