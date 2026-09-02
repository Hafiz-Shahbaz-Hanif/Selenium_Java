package com.hafiz.automation.pages.internet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /hovers. */
public class HoversPage extends BasePage {

    @FindBy(css = ".figure")
    private java.util.List<WebElement> figures;

    public HoversPage open() {
        open(Configuration.theInternetBaseUrl() + "/hovers");
        waitForAll(figures);
        return this;
    }

    public String captionAfterHover(int index) {
        WebElement figure = figures.get(index);
        new Actions(driver).moveToElement(figure).perform();
        return figure.findElement(org.openqa.selenium.By.cssSelector(".figcaption")).getText().trim();
    }
}
