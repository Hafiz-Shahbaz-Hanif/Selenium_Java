package com.hafiz.automation.pages.internet;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /context_menu (right-click triggers a JS alert). */
public class ContextMenuPage extends BasePage {

    @FindBy(id = "hot-spot")
    private WebElement hotSpot;

    public ContextMenuPage open() {
        open(Configuration.theInternetBaseUrl() + "/context_menu");
        visible(hotSpot);
        return this;
    }

    public String rightClickAndReadAlert() {
        new Actions(driver).contextClick(hotSpot).perform();
        return acceptAlert();
    }
}
