package com.hafiz.automation.pages.internet;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hafiz.automation.config.Configuration;
import com.hafiz.automation.pages.BasePage;

/** the-internet &rarr; /floating_menu (menu stays visible while scrolling). */
public class FloatingMenuPage extends BasePage {

    @FindBy(id = "menu")
    private WebElement menu;

    public FloatingMenuPage open() {
        open(Configuration.theInternetBaseUrl() + "/floating_menu");
        visible(menu);
        return this;
    }

    public boolean menuStaysVisibleAfterScroll() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        return menu.isDisplayed();
    }
}
